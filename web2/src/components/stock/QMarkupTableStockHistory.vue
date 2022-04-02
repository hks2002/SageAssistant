<template>
  <q-markup-table dense bordered class="col-grow">
    <thead style="position: sticky; top: 0px; z-index: 1">
      <tr>
        <td colspan="17" class="bg-teal text-white shadow-2">
          {{ site }} Stock History
          <span v-if="PnOrName">of {{ PnOrName }} </span> from {{ dateFrom }} to
          {{ dateTo }}
          <q-btn dense flat icon="fas fa-download" @click="download()" />
        </td>
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
      <tr v-for="(item, index) in stockHistory" :key="index">
        <td class="text-center">{{ index }}</td>
        <td>{{ item['Location'] }}</td>
        <td>{{ item['Seq'] }}</td>
        <td>{{ item['PN'] }}</td>
        <td>{{ item['Description'] }}</td>
        <td class="text-center">{{ item['Qty'] }}</td>
        <td class="text-center">{{ item['Unit'] }}</td>
        <td class="text-right">{{ item['Cost'] }}</td>
        <td>{{ item['ProjectNO'] }}</td>
        <td>{{ item['SourceNO'] }}</td>
        <td>{{ item['SourceLine'] }}</td>
        <td>{{ item['EntryNO'] }}</td>
        <td>{{ item['EntryLine'] }}</td>
        <td class="text-center">{{ item['CreateUser'] }}</td>
        <td class="text-center">{{ item['CreateDate'] }}</td>
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
  onBeforeUnmount,
  onBeforeMount
} from 'vue'
import { useQuasar } from 'quasar'
import { axios } from 'boot/axios'
import { notifyError } from 'assets/common'
import { getCookies } from 'assets/storage'

import { ebus } from 'boot/ebus'
import { jsonToExcel, jsonToTable } from 'assets/dataUtils'
import { isAuthorised } from 'assets/auth'

import _groupBy from 'lodash/groupBy'
import _sumBy from 'lodash/sumBy'
import _uniq from 'lodash/uniq'
import _map from 'lodash/map'

export default defineComponent({
  name: 'QMarkupTableStockHistory',
  props: {
    PnOrName: { type: String, require: false, default: '' },
    dateFrom: String,
    dateTo: String
  },
  setup(props, ctx) {
    const $q = useQuasar()

    const site = ref(getCookies('site'))
    const stockHistory = ref([])

    const doUpdate = () => {
      axios
        .get(
          '/Data/StockHistory?Site=' +
            site.value +
            '&PnOrName=' +
            props.PnOrName +
            '&DateFrom=' +
            props.dateFrom +
            '&DateTo=' +
            props.dateTo
        )
        .then((response) => {
          stockHistory.value = response.data
        })
        .catch((e) => {
          console.error(e)
          notifyError('Loading StockHistory Failed!')
        })
        .finally(() => {})
    }

    const download = () => {
      const header = [
        'Location',
        'Seq',
        'PN',
        'Description',
        'Qty',
        'Unit',
        'Cost',
        'ProjectNO',
        'SourceNO',
        'SourceLine',
        'EntryNO',
        'EntryLine',
        'CreateUser',
        'CreateDate'
      ]
      const strPNData = data.value
      // PN with #
      _forEach(strPNData, (value, index) => {
        value.PN = '#' + value.PN
      })
      jsonToExcel(
        header,
        strPNData,
        site.value + ' Stock History-' + props.dateFrom + '_' + props.dateTo
      )
    }

    onBeforeMount(() => {
      console.debug('onBeforeMount QMarkupTableStockHistory')
    })

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
      () => [props.PnOrName, props.dateFrom, props.dateTo],
      (...newAndold) => {
        // newAndold[1]:old
        // newAndold[0]:new
        console.debug('watch:' + newAndold[1] + ' ---> ' + newAndold[0])
        doUpdate()
      }
    )

    return {
      site,
      stockHistory,
      doUpdate,
      download,
      isAuthorised
    }
  }
})
</script>
<style lang="scss" scoped></style>
