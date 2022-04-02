<template>
  <q-markup-table dense bordered class="col-grow">
    <thead style="position: sticky; top: 0px; z-index: 1">
      <tr>
        <td colspan="2" class="bg-teal text-white shadow-2 col-grow">
          Stock count result of {{ site }}, Total Qty:{{ S2N(sumQty, 0) }} Total
          cost:{{ S2N(sumCost) }}, Products Qty:{{
            S2N(sumProductQty, 0)
          }}
          Products cost:{{ S2N(sumProductCost) }}, Others Qty:{{
            S2N(sumOtherQty, 0)
          }}
          Others cost:{{ S2N(sumOtherCost) }}
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
                  Option PN:{{ subitem['OptionPN'] }}
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
  </q-markup-table>
</template>

<script>
import {
  defineComponent,
  ref,
  watch,
  onMounted,
  onBeforeMount,
  onBeforeUnmount
} from 'vue'
import { axios } from 'boot/axios'
import { notifyError } from 'assets/common'
import { getCookies } from 'assets/storage'
import { useQuasar, Dialog, date } from 'quasar'
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
  name: 'QMarkupTableStockSummary',
  props: {
    PNfilter: {
      type: String,
      require: false,
      default: ''
    }
  },
  setup(props, ctx) {
    const $q = useQuasar()
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
      axios
        .get('/Data/StockSummary?Site=' + site.value)
        .then((response) => {
          data = response.data
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
        .catch((e) => {
          console.error(e)
          notifyError('Loading StockSummary Failed!')
        })
        .finally(() => {})
    }

    const doFilterPN = () => {
      if (props.PNfilter !== '') {
        console.debug(PNfilter.value)

        const filterData = _filter(data, function (item) {
          const pn = item.PN.replace(/,|\.| |-|_|\/|\\/g, '')
          const pnfilter = props.PNfilter.replace(
            /,|\.| |-|_|\/|\\/g,
            ''
          ).toUpperCase()
          if (item.OptionPN) {
            // item maybe doesn't have OptionPN field
            const pnoption = item.OptionPN.replace(/,|\.| |-|_|\/|\\/g, '')
            return (
              pn.indexOf(pnfilter) !== -1 || pnoption.indexOf(pnfilter) !== -1
            )
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
      axios
        .get('/Data/StockHistory?Site=' + site.value + '&PnOrName=' + pn)
        .then((response) => {
          const history = response.data
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
            pn + ' Stock History at ' + site.value
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
        })
        .catch((e) => {
          console.error(e)
          notifyError('Loading StockSummary Failed!')
        })
        .finally(() => {})
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

    onBeforeMount(() => {
      console.debug('onBeforeMount QMarkupTableStockSummary')
    })
    onMounted(() => {
      console.debug('onMounted QMarkupTableStockSummary')
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
      doFilterPN,
      showHistory,
      download,
      isAuthorised
    }
  }
})
</script>
<style lang="sass" scoped></style>
