<template>
  <div>
    <q-list
      bordered
      separator
    >
      <q-item>
        <q-item-section>
          <q-item-label class="text-h6">Stock count result of {{site}},
            Total Qty:{{S2N(sumQty,0)}} Total cost:{{S2N(sumCost)}},
            Products Qty:{{S2N(sumProductQty,0)}} Products cost:{{S2N(sumProductCost)}},
            Others Qty:{{S2N(sumOtherQty,0)}} Others cost:{{S2N(sumOtherCost)}}</q-item-label>
          <q-item-label caption>Click PN to see its history</q-item-label>
        </q-item-section>
        <q-item-section side>
          <q-btn
            dense
            flat
            text-color="indigo-7"
            icon="fas fa-download"
            @click="download()"
          />
        </q-item-section>
      </q-item>
      <q-item>
        <q-input
          :label="$t('Input supplier\'s PN to find stock PN')"
          v-model="PNfilter"
          clearable
          filled
          debounce="1000"
          hint="All special character (',' '.' '/' '\' '-' '_') will be ignored to match."
          :hide-hint="true"
          input-class="text-uppercase"
          class="col-12"
          @update:model-value="doFilterPN"
        >
        </q-input>
      </q-item>
      <q-item
        v-for="(item, index) in dataByFirstChar"
        :key="index"
      >
        <!-- PN index  -->
        <q-item-section avatar>
          <q-avatar
            color="primary"
            text-color="white"
          >
            {{index}}
          </q-avatar>
        </q-item-section>

        <!-- PN list  -->
        <q-item-section>
          <div class="row">
            <div
              v-for="(subitem, subindex) in item"
              :key="subindex"
              style="padding-right:16px"
              @click="showHistory(subitem.PN)"
            >
              <q-tooltip>
                {{subitem.Description}}<div v-if="subitem.OptionPN">Option PN:{{subitem.OptionPN}}</div>
              </q-tooltip>
              <span>{{subitem.PN}}[{{subitem.Qty}}]</span><span v-if="false">{{[subitem.Cost]}}</span>
            </div>
          </div>
        </q-item-section>
      </q-item>
    </q-list>

  </div>
</template>

<script>
import { defineComponent, ref, onMounted, onBeforeUnmount } from 'vue'
import { axios } from 'boot/axios'
import { notifyError } from 'assets/common'
import { getCookies } from 'assets/storage'
import { Dialog, date } from 'quasar'
import { ebus } from 'boot/ebus'
import { jsonToExcel, jsonToTable } from 'assets/dataUtils'
import { isAuthorised } from 'assets/auth'

import _groupBy from 'lodash/groupBy'
import _forEach from 'lodash/forEach'
import _filter from 'lodash/filter'
import _sumBy from 'lodash/sumBy'
import _uniq from 'lodash/uniq'
import _map from 'lodash/map'

export default defineComponent({
  name: 'PageStockSummary',

  setup(props, ctx) {
    const { formatDate } = date

    const site = ref('')
    site.value = getCookies('site')

    let data = []
    const sumQty = ref('')
    const sumCost = ref('')
    const sumProductQty = ref('')
    const sumProductCost = ref('')
    const sumOtherQty = ref('')
    const sumOtherCost = ref('')

    const dataByFirstChar = ref([])

    const PNfilter = ref('')

    const doUpdate = () => {
      axios.get('/Data/StockSummary?site=' + site.value)
        .then((response) => {
          data = response.data
          dataByFirstChar.value = _groupBy(data, 'A')
          sumQty.value = _sumBy(data, 'Qty')
          sumCost.value = _sumBy(data, 'Cost')
          sumProductQty.value = _sumBy(data, function(o) { if (o.G === 'P') { return o.Qty } })
          sumProductCost.value = _sumBy(data, function(o) { if (o.G === 'P') { return o.Cost } })
          sumOtherQty.value = _sumBy(data, function(o) { if (o.G !== 'P') { return o.Qty } })
          sumOtherCost.value = _sumBy(data, function(o) { if (o.G !== 'P') { return o.Cost } })
        })
        .catch((e) => {
          console.error(e)
          notifyError('Loading StockSummary Failed!')
        }).finally(() => {
        })
    }

    const doFilterPN = () => {
      if (PNfilter.value) {
        console.debug(PNfilter.value)

        const filterData = _filter(data, function(item) {
          const pn = item.PN.replace(/,|\.| |-|_|\/|\\/g, '')
          const pnfilter = PNfilter.value.replace(/,|\.| |-|_|\/|\\/g, '').toUpperCase()
          if (item.OptionPN) { // item maybe doesn't have OptionPN field
            const pnoption = item.OptionPN.replace(/,|\.| |-|_|\/|\\/g, '')
            return pn.indexOf(pnfilter) !== -1 || pnoption.indexOf(pnfilter) !== -1
          } else {
            return pn.indexOf(pnfilter) !== -1
          }
        })
        dataByFirstChar.value = _groupBy(filterData, 'A')
      } else {
        dataByFirstChar.value = _groupBy(data, 'A')
      }
    }

    const showHistory = (pn) => {
      axios.get('/Data/StockHistory?site=' + site.value + '&PnOrName=' + pn)
        .then((response) => {
          const history = response.data
          const header = ['Location', 'Seq', 'Qty', 'Cost', 'ProjectNO', 'SourceNO', 'SourceLine', 'EntryNO', 'EntryLine', 'CreateUser', 'CreateDate']
          const message = jsonToTable(header, history, pn + ' Stock History at ' + site.value)

          Dialog.create({
            message: message,
            html: true,
            fullWidth: true
          }).onOk(() => {
            // console.log('OK')
          }).onCancel(() => {
            // console.log('Cancel')
          }).onDismiss(() => {
            // console.log('I am triggered on both OK and Cancel')
          })
        })
        .catch((e) => {
          console.error(e)
          notifyError('Loading StockSummary Failed!')
        }).finally(() => {
        })
    }

    const download = () => {
      const nowTimeStamp = Date.now()
      const nowTime = formatDate(nowTimeStamp, 'YYYY-MM-DD')
      const header = ['G', 'PN', 'Description', 'OptionPN', 'Qty', 'Cost']
      const strPNData = data
      // PN with #
      _forEach(strPNData, (value, index) => {
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
    onBeforeUnmount(() => { ebus.off('changeSite') })

    return {
      site,
      sumQty,
      sumCost,
      sumProductQty,
      sumProductCost,
      sumOtherQty,
      sumOtherCost,
      dataByFirstChar,
      S2N,
      PNfilter,
      doFilterPN,
      showHistory,
      download,
      isAuthorised
    }
  }

})
</script>
<style lang='sass' scoped>
</style>
