<template>
  <div class="row q-gutter-sm q-pa-sm">
    <Vue3Lottie
      animationLink="/json/waiting-input.json"
      :height="600"
      :width="600"
      class="fixed-center"
      v-if="!categoryCode && isAuthorised('GESPOH')"
    />
    <q-input
      v-model="categoryCode"
      class="col-grow"
      outlined
      hide-hint
      hide-bottom-space
      debounce="1000"
      :label="$t('Product Group2 Code')"
      input-class="text-uppercase"
      @update:model-value="doUpdate"
      :disable="!isAuthorised('GESPOH')"
    />
    <q-input
      v-model="dateFrom"
      outlined
      hide-hint
      hide-bottom-space
      class="col-3"
      debounce="1000"
      type="date"
      :label="$t('From')"
      @update:model-value="doUpdate"
      :disable="!isAuthorised('GESPOH')"
    />
    <q-input
      v-model="dateTo"
      outlined
      hide-hint
      hide-bottom-space
      class="col-3"
      debounce="1000"
      type="date"
      :label="$t('To')"
      @update:model-value="doUpdate"
      :disable="!isAuthorised('GESPOH')"
    />
    <q-input
      v-model="limitN"
      outlined
      hide-hint
      hide-bottom-space
      class="col-1"
      debounce="1000"
      type="number"
      :label="$t('Limit last N Years')"
      :rules="[
        (val) => (val !== null && val !== '') || 'Please type last N limmit',
        (val) => (val > 0 && val <= 10) || 'Limit between from 1 to 10'
      ]"
      @update:model-value="doUpdate"
      :disable="!isAuthorised('GESPOH')"
    />
  </div>
  <q-tabs
    v-model="tab"
    dense
    align="left"
    class="text-grey"
    active-color="primary"
    indicator-color="primary"
    narrow-indicator
    :style="'height:' + tabHeaderHeight + 'px'"
    v-if="categoryCode"
  >
    <q-tab name="YourSite" label="Your-Site" />
    <q-tab name="AllSites" label="All-Sites" />
  </q-tabs>
  <q-tab-panels v-model="tab" keep-alive v-if="categoryCode">
    <q-tab-panel name="YourSite" class="q-pa-none">
      <q-table
        v-if="isAuthorised('GESSOH') || isAuthorised('GESSQH')"
        v-model:pagination="pagination"
        row-key="index"
        dense
        :virtual-scroll-sticky-size-start="48"
        :rows="analysisQuoteSalesCost"
        :columns="columns"
        :rows-per-page-options="[0]"
        class="q-mx-sm q-mb-sm my-sticky"
        :style="
          'height:' + tableHeight + 'px;width:' + (pageBodyWidth - 16) + 'px'
        "
      >
        <template v-slot:top>
          <q-toolbar class="bg-teal text-white shadow-2">
            <q-toolbar-title class="text-left"
              >Products of group2 [{{ categoryCode }}] in {{ site }} from
              {{ dateFrom }} to {{ dateTo }}
              <q-btn dense flat icon="fas fa-download" @click="download()" />
            </q-toolbar-title>
          </q-toolbar>
        </template>
      </q-table>
      <exception :ErrorCode="403" v-else />
    </q-tab-panel>
    <q-tab-panel name="AllSites" class="q-pa-none">
      <q-table
        v-if="isAuthorised('GESSOH') || isAuthorised('GESSQH')"
        v-model:pagination="pagination"
        row-key="index"
        dense
        :virtual-scroll-sticky-size-start="48"
        :rows="analysisQuoteSalesCostAllKeyed"
        :columns="columnsAll"
        :rows-per-page-options="[0]"
        class="q-mx-sm q-mb-sm my-sticky"
        :style="
          'height:' + tableHeight + 'px;width:' + (pageBodyWidth - 16) + 'px'
        "
      >
        <template v-slot:top>
          <q-toolbar class="bg-teal text-white shadow-2">
            <q-toolbar-title class="text-left"
              >Products of group2 [{{ categoryCode }}] in all sites from
              {{ dateFrom }} to {{ dateTo }}
              <q-btn dense flat icon="fas fa-download" @click="downloadAll()" />
            </q-toolbar-title>
          </q-toolbar>
        </template>
        <template v-slot:header="props">
          <q-tr :props="props">
            <q-th class="bg-primary text-white"> </q-th>
            <q-th class="bg-primary text-white"> </q-th>
            <q-th class="bg-primary text-white"> </q-th>
            <q-th :colspan="siteN" class="bg-light-green text-white">
              QCnt
            </q-th>
            <q-th :colspan="siteN" class="bg-green text-white"> QQty </q-th>
            <q-th :colspan="siteN" class="bg-indigo-6 text-white">
              MinQPrice
            </q-th>
            <q-th :colspan="siteN" class="bg-indigo-4 text-white">
              AvgQPrice
            </q-th>
            <q-th :colspan="siteN" class="bg-indigo-2 text-white">
              MaxQPrice
            </q-th>
            <template v-for="li in limitN" :key="li">
              <q-th
                :colspan="siteN"
                :class="'bg-cyan-' + (15 - li) + ' text-white'"
              >
                LastQPrice{{ li }}
              </q-th>
            </template>
            <q-th :colspan="siteN" class="bg-light-green text-white">
              SCnt
            </q-th>
            <q-th :colspan="siteN" class="bg-green text-white"> SQty </q-th>
            <q-th :colspan="siteN" class="bg-indigo-6 text-white">
              MinSPrice
            </q-th>
            <q-th :colspan="siteN" class="bg-indigo-4 text-white">
              AvgSPrice
            </q-th>
            <q-th :colspan="siteN" class="bg-indigo-2 text-white">
              MaxSPrice
            </q-th>
            <template v-for="li in limitN" :key="li">
              <q-th
                :colspan="siteN"
                :class="'bg-blue-' + (15 - li) + ' text-white'"
              >
                LastSPrice{{ li }}
              </q-th>
            </template>
            <template v-for="li in limitN" :key="li">
              <q-th
                :colspan="siteN"
                :class="'bg-orange-' + (15 - li) + ' text-white'"
              >
                LastCost{{ li }}
              </q-th>
            </template>
          </q-tr>
          <q-tr :props="props">
            <q-th v-for="col in props.cols" :key="col['name']" :props="props">
              {{ col['label'] }}
            </q-th>
          </q-tr>
        </template>
      </q-table>
      <exception :ErrorCode="403" v-else />
    </q-tab-panel>
  </q-tab-panels>
</template>

<script>
import {
  defineComponent,
  ref,
  onBeforeUnmount,
  toRaw,
  onBeforeMount,
  onMounted
} from 'vue'
import { axios } from 'boot/axios'
import { ebus } from 'boot/ebus'
import { useQuasar, date } from 'quasar'
import { getCookies } from 'assets/storage'
import { jsonToExcel, jsonToTable } from 'assets/dataUtils'
import { isAuthorised } from 'assets/auth'
import Exception from 'pages/Exception.vue'
import _forEach from 'lodash/forEach'
import _groupBy from 'lodash/groupBy'
import _map from 'lodash/map'
import _values from 'lodash/values'
import { Vue3Lottie } from 'vue3-lottie'

export default defineComponent({
  name: 'QuoteSalesCost',
  props: {
    // if mount to tabpanel
    tabPanelHeight: {
      type: Number,
      required: false,
      default: 0
    }
  },
  components: {
    Exception,
    Vue3Lottie
  },

  setup(props, ctx) {
    const $q = useQuasar()
    const tabHeaderHeight = ref(36)
    const tableHeight = ref(250)
    const pageBodyWidth = ref(600)

    const { formatDate, addToDate } = date

    const site = ref('')
    site.value = getCookies('site')
    const siteList = ref([])
    siteList.value = getCookies('siteList')
    const siteN = siteList.value.length

    const tab = ref('YourSite')

    const categoryCode = ref('')

    const nowTimeStamp = Date.now()
    const fromTimeStamp = addToDate(nowTimeStamp, { years: -3 })
    const dateFrom = ref(formatDate(fromTimeStamp, 'YYYY-MM-DD'))
    const dateTo = ref(formatDate(nowTimeStamp, 'YYYY-MM-DD'))
    const limitN = ref(3)
    const analysisQuoteSalesCost = ref([])
    let analysisQuoteSalesCostAll = []
    const analysisQuoteSalesCostAllKeyed = ref([])

    const doUpdate = () => {
      categoryCode.value = categoryCode.value.toUpperCase()
      if (!checkInput()) return

      doUpdateOne()
      doUpdateAll()
    }

    const doUpdateOne = () => {
      categoryCode.value = categoryCode.value.toUpperCase()
      if (!checkInput()) return

      $q.loadingBar.start()
      axios
        .get(
          `/Data/AnalysisQuoteSalesCost?Site=${site.value}&CategoryCode=${categoryCode.value}&DateFrom=${dateFrom.value}&DateTo=${dateTo.value}&Limit=${limitN.value}`
        )
        .then((response) => {
          analysisQuoteSalesCost.value = response.data
          analysisQuoteSalesCost.value.forEach((row, index) => {
            row.index = index + 1
          })
        })
        .catch((e) => {
          console.error(e)
          notifyError('Loading AnalysisQuoteSalesCost Failed!')
        })
        .finally(() => {
          $q.loadingBar.stop()
        })
    }

    const doUpdateAll = () => {
      $q.loadingBar.start()
      axios
        .get(
          `/Data/AnalysisQuoteSalesCost?Site=ALL&CategoryCode=${categoryCode.value}&DateFrom=${dateFrom.value}&DateTo=${dateTo.value}&Limit=${limitN.value}`
        )
        .then((response) => {
          analysisQuoteSalesCostAll = response.data
          analysisQuoteSalesCostAllKeyed.value = _values(
            _groupBy(analysisQuoteSalesCostAll, 'PN')
          )
          analysisQuoteSalesCostAllKeyed.value.forEach((row, index) => {
            row.index = index + 1
          })
        })
        .catch((e) => {
          console.error(e)
          notifyError('Loading AnalysisQuoteSalesCost Failed!')
        })
        .finally(() => {
          $q.loadingBar.stop()
        })
    }

    const checkInput = () => {
      if (categoryCode.value === '') return false
      if (limitN.value <= 0 || limitN.value > 10) return false
      return true
    }

    const columns = [
      {
        name: '#',
        label: '#',
        field: (row) => row.index,
        align: 'center',
        headerClasses: 'bg-primary text-white'
      },
      {
        name: 'PN',
        label: 'PN',
        align: 'left',
        field: (row) => row.PN,
        headerClasses: 'bg-primary text-white'
      },
      {
        name: 'Description',
        label: 'Description',
        align: 'left',
        field: (row) => row.Description,
        headerClasses: 'bg-primary text-white'
      },
      {
        name: 'QCnt',
        label: 'QCnt',
        align: 'center',
        field: (row) => row.QCnt,
        headerClasses: 'bg-positive text-white'
      },
      {
        name: 'QQty',
        label: 'QQty',
        align: 'center',
        field: (row) => row.QQty,
        headerClasses: 'bg-positive text-white'
      },
      {
        name: 'MinQPrice',
        label: 'MinQPrice',
        align: 'right',
        format: (val) => (isNaN(parseInt(val, 10)) ? '' : parseInt(val, 10)),
        field: (row) => row.MinQPrice,
        headerClasses: 'bg-info text-white'
      },
      {
        name: 'AvgQPrice',
        label: 'AvgQPrice',
        align: 'right',
        format: (val) => (isNaN(parseInt(val, 10)) ? '' : parseInt(val, 10)),
        field: (row) => row.AvgQPrice,
        headerClasses: 'bg-info text-white'
      },
      {
        name: 'MaxQPrice',
        label: 'MaxQPrice',
        align: 'right',
        format: (val) => (isNaN(parseInt(val, 10)) ? '' : parseInt(val, 10)),
        field: (row) => row.MaxQPrice,
        headerClasses: 'bg-info text-white'
      }
    ]
    for (let i = 0; i < limitN.value; ++i) {
      const o = {
        name: 'LastQPrice' + (i + 1),
        label: 'LastQPrice' + (i + 1),
        align: 'right',
        format: (val) => (isNaN(parseInt(val, 10)) ? '' : parseInt(val, 10)),
        field: (row) => row['LastQPrice' + (i + 1)],
        headerClasses: 'bg-info text-white'
      }
      columns.push(o)
    }
    let o = {}
    o = {
      name: 'SCnt',
      label: 'SCnt',
      align: 'center',
      field: (row) => row.SCnt,
      headerClasses: 'bg-positive text-white'
    }
    columns.push(o)
    o = {
      name: 'SQty',
      label: 'SQty',
      align: 'center',
      field: (row) => row.SQty,
      headerClasses: 'bg-positive text-white'
    }
    columns.push(o)
    o = {
      name: 'MinSPrice',
      label: 'MinSPrice',
      align: 'right',
      format: (val) => (isNaN(parseInt(val, 10)) ? '' : parseInt(val, 10)),
      field: (row) => row.AvgSPrice,
      headerClasses: 'bg-accent text-white'
    }
    columns.push(o)
    o = {
      name: 'AvgSPrice',
      label: 'AvgSPrice',
      align: 'right',
      format: (val) => (isNaN(parseInt(val, 10)) ? '' : parseInt(val, 10)),
      field: (row) => row.AvgSPrice,
      headerClasses: 'bg-accent text-white'
    }
    columns.push(o)
    o = {
      name: 'MaxSPrice',
      label: 'MaxSPrice',
      align: 'right',
      format: (val) => (isNaN(parseInt(val, 10)) ? '' : parseInt(val, 10)),
      field: (row) => row.MaxSPrice,
      headerClasses: 'bg-accent text-white'
    }
    columns.push(o)
    for (let i = 0; i < limitN.value; ++i) {
      const o = {
        name: 'LastSPrice' + (i + 1),
        label: 'LastSPrice' + (i + 1),
        align: 'right',
        format: (val) => (isNaN(parseInt(val, 10)) ? '' : parseInt(val, 10)),
        field: (row) => row['LastSPrice' + (i + 1)],
        headerClasses: 'bg-accent text-white'
      }
      columns.push(o)
    }
    for (let i = 0; i < limitN.value; ++i) {
      const o = {
        name: 'LastPJT' + (i + 1),
        label: 'LastPJT' + (i + 1),
        align: 'right',
        field: (row) => row['LastPJT' + (i + 1)],
        headerClasses: 'bg-warning text-white'
      }
      columns.push(o)
    }
    for (let i = 0; i < limitN.value; ++i) {
      const o = {
        name: 'LastCost' + (i + 1),
        label: 'LastCost' + (i + 1),
        align: 'right',
        format: (val) => (isNaN(parseInt(val, 10)) ? '' : parseInt(val, 10)),
        field: (row) => row['LastCost' + (i + 1)],
        headerClasses: 'bg-primary text-white'
      }
      columns.push(o)
    }

    const download = () => {
      const header = _map(columns, 'name')
      jsonToExcel(
        header,
        analysisQuoteSalesCost.value,
        'Product group2 [' +
          categoryCode.value +
          '] in ' +
          site.value +
          ' from ' +
          dateFrom.value +
          ' to ' +
          dateTo.value
      )
    }

    const columnsAll = [
      {
        name: '#',
        label: '#',
        field: (row) => row.index,
        align: 'center',
        headerClasses: 'bg-primary text-white'
      },
      {
        name: 'PN',
        label: 'PN',
        align: 'left',
        field: (row) => row[0].PN,
        headerClasses: 'bg-primary text-white'
      },
      {
        name: 'Description',
        label: 'Description',
        align: 'left',
        field: (row) => row[0].Description,
        headerClasses: 'bg-primary text-white'
      }
    ]

    // get value from the array data which contains all sites
    const getSiteValue = (array, site, key) => {
      for (let i = 0; i < array.length; i++) {
        if (array[i].Site === site) {
          return array[i][key]
        }
      }
    }
    for (let i = 0; i < siteN; ++i) {
      let o = {}
      o = {
        name: 'QCnt' + siteList.value[i],
        label: siteList.value[i],
        align: 'center',
        field: (row) => getSiteValue(row, siteList.value[i], 'QCnt'),
        headerClasses: 'bg-light-green text-white'
      }
      columnsAll.push(o)
    }
    for (let i = 0; i < siteN; ++i) {
      let o = {}
      o = {
        name: 'QQty' + siteList.value[i],
        label: siteList.value[i],
        align: 'center',
        field: (row) => getSiteValue(row, siteList.value[i], 'QQty'),
        headerClasses: 'bg-green text-white'
      }
      columnsAll.push(o)
    }
    for (let i = 0; i < siteN; ++i) {
      let o = {}
      o = {
        name: 'MinQPrice' + siteList.value[i],
        label: siteList.value[i],
        align: 'right',
        format: (val) => (isNaN(parseInt(val, 10)) ? '' : parseInt(val, 10)),
        field: (row) => getSiteValue(row, siteList.value[i], 'MinQPrice'),
        headerClasses: 'bg-indigo-6 text-white'
      }
      columnsAll.push(o)
    }
    for (let i = 0; i < siteN; ++i) {
      let o = {}
      o = {
        name: 'AvgQPrice' + siteList.value[i],
        label: siteList.value[i],
        align: 'right',
        format: (val) => (isNaN(parseInt(val, 10)) ? '' : parseInt(val, 10)),
        field: (row) => getSiteValue(row, siteList.value[i], 'AvgQPrice'),
        headerClasses: 'bg-indigo-4 text-white'
      }
      columnsAll.push(o)
    }
    for (let i = 0; i < siteN; ++i) {
      let o = {}
      o = {
        name: 'MaxQPrice' + siteList.value[i],
        label: siteList.value[i],
        align: 'right',
        format: (val) => (isNaN(parseInt(val, 10)) ? '' : parseInt(val, 10)),
        field: (row) => getSiteValue(row, siteList.value[i], 'MaxQPrice'),
        headerClasses: 'bg-indigo-2 text-white'
      }
      columnsAll.push(o)
    }
    for (let ii = 0; ii < limitN.value; ++ii) {
      for (let i = 0; i < siteN; ++i) {
        let o = {}
        o = {
          name: 'LastQPrice' + (ii + 1) + siteList.value[i],
          label: siteList.value[i],
          align: 'right',
          format: (val) => (isNaN(parseInt(val, 10)) ? '' : parseInt(val, 10)),
          field: (row) =>
            getSiteValue(row, siteList.value[i], 'LastQPrice' + (ii + 1)),
          headerClasses: 'bg-cyan-' + (14 - ii) + ' text-white'
        }
        columnsAll.push(o)
      }
    }
    for (let i = 0; i < siteN; ++i) {
      let o = {}
      o = {
        name: 'SCnt' + siteList.value[i],
        label: siteList.value[i],
        align: 'center',
        field: (row) => getSiteValue(row, siteList.value[i], 'SCnt'),
        headerClasses: 'bg-light-green text-white'
      }
      columnsAll.push(o)
    }
    for (let i = 0; i < siteN; ++i) {
      let o = {}
      o = {
        name: 'SQty' + siteList.value[i],
        label: siteList.value[i],
        align: 'center',
        field: (row) => getSiteValue(row, siteList.value[i], 'SQty'),
        headerClasses: 'bg-green text-white'
      }
      columnsAll.push(o)
    }
    for (let i = 0; i < siteN; ++i) {
      let o = {}
      o = {
        name: 'MinSPrice' + siteList.value[i],
        label: siteList.value[i],
        align: 'right',
        format: (val) => (isNaN(parseInt(val, 10)) ? '' : parseInt(val, 10)),
        field: (row) => getSiteValue(row, siteList.value[i], 'MinSPrice'),
        headerClasses: 'bg-indigo-6 text-white'
      }
      columnsAll.push(o)
    }
    for (let i = 0; i < siteN; ++i) {
      let o = {}
      o = {
        name: 'AvgSPrice' + siteList.value[i],
        label: siteList.value[i],
        align: 'right',
        format: (val) => (isNaN(parseInt(val, 10)) ? '' : parseInt(val, 10)),
        field: (row) => getSiteValue(row, siteList.value[i], 'AvgSPrice'),
        headerClasses: 'bg-indigo-4 text-white'
      }
      columnsAll.push(o)
    }
    for (let i = 0; i < siteN; ++i) {
      let o = {}
      o = {
        name: 'MaxSPrice' + siteList.value[i],
        label: siteList.value[i],
        align: 'right',
        format: (val) => (isNaN(parseInt(val, 10)) ? '' : parseInt(val, 10)),
        field: (row) => getSiteValue(row, siteList.value[i], 'MaxSPrice'),
        headerClasses: 'bg-indigo-2 text-white'
      }
      columnsAll.push(o)
    }
    for (let ii = 0; ii < limitN.value; ++ii) {
      for (let i = 0; i < siteN; ++i) {
        let o = {}
        o = {
          name: 'LastSPrice' + (ii + 1) + siteList.value[i],
          label: siteList.value[i],
          align: 'right',
          format: (val) => (isNaN(parseInt(val, 10)) ? '' : parseInt(val, 10)),
          field: (row) =>
            getSiteValue(row, siteList.value[i], 'LastSPrice' + (ii + 1)),
          headerClasses: 'bg-blue-' + (14 - ii) + ' text-white'
        }
        columnsAll.push(o)
      }
    }
    for (let ii = 0; ii < limitN.value; ++ii) {
      for (let i = 0; i < siteN; ++i) {
        let o = {}
        o = {
          name: 'LastCost' + (ii + 1) + siteList.value[i],
          label: siteList.value[i],
          align: 'right',
          format: (val) => (isNaN(parseInt(val, 10)) ? '' : parseInt(val, 10)),
          field: (row) =>
            getSiteValue(row, siteList.value[i], 'LastCost' + (ii + 1)),
          headerClasses: 'bg-orange-' + (14 - ii) + ' text-white'
        }
        columnsAll.push(o)
      }
    }

    const downloadAll = () => {
      const header = [
        'Site',
        'PN',
        'Description',
        'QCnt',
        'QQty',
        'MinQPrice',
        'AvgQPrice',
        'MaxQPrice'
      ]

      for (let ii = 0; ii < limitN.value; ++ii) {
        header.push('LastQPrice' + (ii + 1))
      }
      header.push('SCnt')
      header.push('SQty')
      header.push('MinSPrice')
      header.push('AvgSPrice')
      header.push('MaxSPrice')
      for (let ii = 0; ii < limitN.value; ++ii) {
        header.push('LastSPrice' + (ii + 1))
      }
      for (let ii = 0; ii < limitN.value; ++ii) {
        header.push('LastCost' + (ii + 1))
      }
      jsonToExcel(
        header,
        analysisQuoteSalesCostAll,
        'Product group2 [' +
          categoryCode.value +
          '] in all sites' +
          ' from ' +
          dateFrom.value +
          ' to ' +
          dateTo.value
      )
    }

    onBeforeMount(() => {
      console.log('onBeforeMount:')
      pageBodyWidth.value = $q.pageBodyWidth
      console.log('tabPanelHeight:' + props.tabPanelHeight)
      console.log('tabHeaderHeight.value:' + tabHeaderHeight.value)
      console.log('$.pageBodyHeight:' + $q.pageBodyHeight)
      if (props.tabPanelHeight > 0) {
        tableHeight.value = props.tabPanelHeight - tabHeaderHeight.value - 80
      } else {
        tableHeight.value = $q.pageBodyHeight - tabHeaderHeight.value
      }
      console.log('tableHeight:' + tableHeight.value)
    })

    onMounted(() => {
      console.log('onMounted:')
    })
    // event handing
    ebus.on('changeSite', (newSite) => {
      site.value = newSite
      doUpdateOne()
    })
    onBeforeUnmount(() => {
      ebus.off('changeSite')
    })

    return {
      tab,
      tabHeaderHeight,
      tableHeight,
      pageBodyWidth,
      timer: new Date().getTime(),
      isAuthorised,
      site,
      categoryCode,
      dateFrom,
      dateTo,
      limitN,
      siteN,
      analysisQuoteSalesCost,
      analysisQuoteSalesCostAllKeyed,
      columns,
      columnsAll,
      pagination: ref({
        rowsPerPage: 0
      }),
      doUpdate,
      download,
      downloadAll
    }
  }
})
</script>
<style lang="sass">
.q-table--dense .q-table__top
  padding: 0

.q-table__top
  padding: 0px

.my-sticky
  /* set col first, then set row */
  thead tr th:first-child,
  tbody tr td:first-child
    position: sticky
    background-color: #fff
    z-index: 1
    left: 0

  thead tr th:nth-child(2),
  tbody tr td:nth-child(2)
    position: sticky
    background-color: #fff
    z-index: 1
    left: 30px

  /* set col first, then set row */
  thead tr
    position: sticky
    z-index: 2
    /* bg color is important; just specify one */
    background-color: #fff

  /* first set the last row top position, then set first, it flex to fix row
  thead tr:last-child
    top: 28px
  thead tr:first-child
    top: 0

  /* set header and col */
  thead tr:last-child th:first-child,
  thead tr:first-child th:first-child
    z-index: 3
</style>
