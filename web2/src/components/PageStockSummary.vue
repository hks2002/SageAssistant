<template>
  <div>
    <q-list
      bordered
      separator
    >
      <q-item>
        <q-item-section>
          <q-item-label class="text-h6">Stock count result of {{site}}, total Qty:{{sumQty}} total cost:{{sumCost}}</q-item-label>
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
                {{subitem.Description}}
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

    const dataByFirstChar = ref([])

    const doUpdate = () => {
      axios.get('/Data/StockSummary?site=' + site.value)
        .then((response) => {
          data = response.data
          dataByFirstChar.value = _groupBy(data, 'A')
          sumQty.value = _sumBy(data, 'Qty')
          sumCost.value = _sumBy(data, 'Cost')
        })
        .catch((e) => {
          console.error(e)
          notifyError('Loading StockSummary Failed!')
        }).finally(() => {
        })
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
      const header = ['G', 'PN', 'Description', 'Qty', 'Cost']
      jsonToExcel(header, data, site.value + ' Stock Count-' + nowTime)
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
      dataByFirstChar,
      showHistory,
      download,
      isAuthorised
    }
  }

})
</script>
<style lang='sass' scoped>
.q-item
</style>
