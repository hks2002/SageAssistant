<template>
  <div class="row  q-ml-md q-mr-md">
    <q-input
      :label="$t('Filter result by PN or Name')"
      v-model="PnOrName"
      clearable
      debounce="1000"
      hint="example: MS 856A NUT SCREW"
      :hide-hint="true"
      input-class="text-uppercase"
      class="col-6 q-pr-md"
      @update:model-value="doUpdate"
    >
    </q-input>
    <div class="col-3">
      <q-input
        v-model="dateFrom"
        debounce="1000"
        mask="date"
        type="date"
        :label="$t('From')"
        class="q-pr-md"
        @update:model-value="doUpdate"
      />
    </div>
    <div class="col-3">
      <q-input
        v-model="dateTo"
        debounce="1000"
        mask="date"
        :label="$t('To')"
        type="date"
        class="q-pl-md"
        @update:model-value="doUpdate"
      />
    </div>

    <q-markup-table
      dense
      class="col-12 q-pr-sm q-pl-sm"
    >
      <thead>
        <tr>
          <th
            colspan="17"
            style="padding:0px"
          >
            <q-toolbar class="bg-teal text-white shadow-2">
              <q-toolbar-title class="text-left">{{site}} Stock History <span v-if="PnOrName">of {{PnOrName}} </span> from {{dateFrom}} to {{dateTo}}
                <q-btn
                  dense
                  flat
                  icon="fas fa-download"
                  @click="download()"
                />
              </q-toolbar-title>
            </q-toolbar>
          </th>
        </tr>
        <tr class="bg-primary text-white">
          <th class="text-center text-caption">#</th>
          <th class="text-left">Location</th>
          <th class="text-left">Seq</th>
          <th class="text-left">PN</th>
          <th class="text-left">Description</th>
          <th class="text-center">Qty</th>
          <th class="text-center">Unit</th>
          <th class="text-right">Cost</th>
          <th class="text-left">ProjectNO</th>
          <th class="text-left">SourceNO</th>
          <th class="text-left">SourceLine</th>
          <th class="text-left">EntryNO</th>
          <th class="text-left">EntryLine</th>
          <th class="text-center">CreateUser</th>
          <th class="text-center">CreateDate</th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="(item, index) in stockHistory"
          :key="index"
        >
          <td class="text-center">{{index}}</td>
          <td>{{item.Location}}</td>
          <td>{{item.Seq}}</td>
          <td>{{item.PN}}</td>
          <td>{{item.Description}}</td>
          <td class="text-center">{{item.Qty}}</td>
          <td class="text-center">{{item.Unit}}</td>
          <td class="text-right">{{item.Cost}}</td>
          <td>{{item.ProjectNO}}</td>
          <td>{{item.SourceNO}}</td>
          <td>{{item.SourceLine}}</td>
          <td>{{item.EntryNO}}</td>
          <td>{{item.EntryLine}}</td>
          <td class="text-center">{{item.CreateUser}}</td>
          <td class="text-center">{{item.CreateDate}}</td>
        </tr>
      </tbody>
    </q-markup-table>

  </div>
</template>

<script>
import { defineComponent, ref, onMounted, onBeforeUnmount } from 'vue'
import { axios } from 'boot/axios'
import { notifyError } from 'assets/common'
import { getCookies } from 'assets/storage'
import { date } from 'quasar'
import { ebus } from 'boot/ebus'
import { jsonToExcel, jsonToTable } from 'assets/dataUtils'
import { isAuthorised } from 'assets/auth'

import _groupBy from 'lodash/groupBy'
import _sumBy from 'lodash/sumBy'
import _uniq from 'lodash/uniq'
import _map from 'lodash/map'

export default defineComponent({
  name: 'PageStockHistory',

  setup(props, ctx) {
    const { formatDate, addToDate } = date
    const nowTimeStamp = Date.now()
    const fromTimeStamp = addToDate(nowTimeStamp, { months: -1 })
    const dateFrom = ref(formatDate(fromTimeStamp, 'YYYY-MM-DD'))
    const dateTo = ref(formatDate(nowTimeStamp, 'YYYY-MM-DD'))

    const site = ref('')
    site.value = getCookies('site')

    const PnOrName = ref('')
    const stockHistory = ref([])

    const doUpdate = () => {
      axios.get('/Data/StockHistory?site=' + site.value + '&PnOrName=' + PnOrName.value + '&DateFrom=' + dateFrom.value + '&DateTo=' + dateTo.value)
        .then((response) => {
          stockHistory.value = response.data
        })
        .catch((e) => {
          console.error(e)
          notifyError('Loading StockHistory Failed!')
        }).finally(() => {
        })
    }

    const download = () => {
      const header = ['Location', 'Seq', 'PN', 'Description', 'Qty', 'Unit', 'Cost', 'ProjectNO', 'SourceNO', 'SourceLine', 'EntryNO', 'EntryLine', 'CreateUser', 'CreateDate']
      jsonToExcel(header, data.value, site.value + ' Stock History-' + dateFrom.value + '_' + dateTo.value)
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
      PnOrName,
      dateTo,
      dateFrom,
      stockHistory,
      doUpdate,
      download,
      isAuthorised
    }
  }

})
</script>
<style lang='sass' scoped>
</style>
