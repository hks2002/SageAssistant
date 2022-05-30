<template>
  <q-markup-table dense bordered class="col-grow">
    <thead style="position: sticky; top: 0px; z-index: 1">
      <tr>
        <td colspan="2" class="bg-teal text-white shadow-2 col-grow">
          {{ $t('Stock count result of') }} {{ site }}, {{ $t('Total Qty') }}:{{
            S2N(sumQty, 0)
          }}
          {{ $t('Total cost') }}:{{ S2N(sumCost) }}, {{ $t('Products Qty') }}:{{
            S2N(sumProductQty, 0)
          }}
          {{ $t('Products cost') }}:{{ S2N(sumProductCost) }},
          {{ $t('Others Qty') }}:{{ S2N(sumOtherQty, 0) }}
          {{ $t('Others cost') }}:{{ S2N(sumOtherCost) }}
          <q-btn
            dense
            flat
            text-color="indigo-7"
            icon="fas fa-download"
            @click="download()"
          />
        </td>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(item, index) in dataByFirstChar" :key="index">
        <td>
          <!-- PN index  -->
          <q-avatar color="primary" text-color="white">
            {{ index }}
          </q-avatar>
        </td>
        <td>
          <!-- PN list  -->
          <div class="row">
            <div
              v-for="(subitem, subindex) in item"
              :key="subindex"
              style="padding-right: 16px"
              @click="showHistory(subitem['PN'])"
            >
              <q-tooltip>
                {{ subitem['Description'] }}
                <div v-if="subitem['OptionPN']">
                  {{ $t('Option PN') }}:{{ subitem['OptionPN'] }}
                </div>
              </q-tooltip>
              <span
                >{{ subitem['PN'] }}[{{ subitem['Location'] }}:{{
                  subitem['Qty']
                }}]</span
              ><span v-if="false">{{ [subitem['Cost']] }}</span>
            </div>
          </div>
        </td>
      </tr>
    </tbody>
    <q-inner-loading :showing="showLoading">
      <q-spinner-ios size="50px" color="primary" />
    </q-inner-loading>
  </q-markup-table>
</template>

<script setup>
import { axiosGet } from '@/assets/axiosActions'
import { ebus } from '@/assets/ebus'
import { jsonToExcel, jsonToTable } from 'assets/dataUtils'
import { getCookies } from 'assets/storage'
import _forEach from 'lodash/forEach'
import _groupBy from 'lodash/groupBy'
import _sumBy from 'lodash/sumBy'
import { date, Dialog } from 'quasar'
import { onBeforeUnmount, onMounted, ref, watch } from 'vue'
import { useI18n } from 'vue-i18n'

const props = defineProps({
  PNfilter: {
    type: String,
    require: false,
    default: ''
  }
})

// common vars
const { t } = useI18n()
const showLoading = ref(false)
const site = ref(getCookies('site'))

// component vars
let data = []
const sumQty = ref('')
const sumCost = ref('')
const sumProductQty = ref('')
const sumProductCost = ref('')
const sumOtherQty = ref('')
const sumOtherCost = ref('')

const dataByFirstChar = ref([])

const { formatDate } = date

// actions
const doUpdate = () => {
  showLoading.value = true

  axiosGet('/Data/StockSummary?Site=' + site.value)
    .then((response) => {
      data = response
      dataByFirstChar.value = _groupBy(data, 'A')
      sumQty.value = _sumBy(data, 'Qty')
      sumCost.value = _sumBy(data, 'Cost')
      sumProductQty.value = _sumBy(data, function (o) {
        if (o.G === 'P') {
          return o.Qty
        }
      })
      sumProductCost.value = _sumBy(data, function (o) {
        if (o.G === 'P') {
          return o.Cost
        }
      })
      sumOtherQty.value = _sumBy(data, function (o) {
        if (o.G !== 'P') {
          return o.Qty
        }
      })
      sumOtherCost.value = _sumBy(data, function (o) {
        if (o.G !== 'P') {
          return o.Cost
        }
      })
    })
    .finally(() => {
      showLoading.value = false
    })
}

const showHistory = (pn) => {
  axiosGet('/Data/StockHistory?Site=' + site.value + '&PnOrName=' + pn).then(
    (response) => {
      const history = response
      const header = [
        'Location',
        'Seq',
        'Qty',
        'Cost',
        'ProjectNO',
        'SourceNO',
        'SourceLine',
        'EntryNO',
        'EntryLine',
        'CreateUser',
        'CreateDate'
      ]
      const message = jsonToTable(
        header,
        history,
        pn + t(' Stock History at ') + site.value
      )

      Dialog.create({
        message: message,
        html: true,
        fullWidth: true
      })
        .onOk(() => {
          // console.log('OK')
        })
        .onCancel(() => {
          // console.log('Cancel')
        })
        .onDismiss(() => {
          // console.log('I am triggered on both OK and Cancel')
        })
    }
  )
}

const download = () => {
  const nowTimeStamp = Date.now()
  const nowTime = formatDate(nowTimeStamp, 'YYYY-MM-DD')
  const header = [
    'G',
    'Location',
    'PN',
    'Description',
    'OptionPN',
    'Qty',
    'Cost'
  ]
  const strPNData = data
  // PN with #
  _forEach(strPNData, (value) => {
    value.PN = '#' + value.PN
  })
  jsonToExcel(header, strPNData, site.value + ' Stock Count-' + nowTime)
}

const S2N = (S, n) => {
  let N = 0
  if (n) {
    N = parseFloat(S).toFixed(n)
  } else if (n === 0) {
    N = parseFloat(S).toFixed(0)
  } else {
    N = parseFloat(S).toFixed(2)
  }
  if (N === 'NaN') {
    return ''
  } else {
    return N
  }
}

onMounted(() => {
  doUpdate()
})

// event handing
ebus.on('changeSite', (newSite) => {
  site.value = newSite
  doUpdate()
})

onBeforeUnmount(() => {
  ebus.off('changeSite')
})

// Don't use watchEffect, it run before Mounted.
watch(
  () => [props.PNfilter],
  (...newAndold) => {
    // newAndold[1]:old
    // newAndold[0]:new
    console.debug('watch:' + newAndold[1] + ' ---> ' + newAndold[0])
    doUpdate()
  }
)
</script>
<style lang="sass" scoped></style>
