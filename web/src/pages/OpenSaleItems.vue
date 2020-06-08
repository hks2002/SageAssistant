/* eslint-disable no-tabs */
<template>
  <q-page>
    <div class="q-pa-md">
      <q-table dense
               class="my-sticky-header-column-table"
               :data="data"
               :columns="columns"
               :loading="loading"
               :pagination.sync="pagination"
               :rows-per-page-options="[200,500,1000]"
               :filter="filter"
               :filter-method="myFilterMethod"
               row-key="index1">
        <!-- header ---------------------------------------------------------------------------------->
        <template v-slot:top>
          <!-- show option ---------------------------------------------------------------------------------->
          <div class="q-table__control">
            <div class="q-table__title">Open Sale Itmes Status</div>
            <q-toggle v-model="mergeCell"
                      label="Merge Cells" />
            <q-toggle v-model="showMore"
                      label="Show More" />
          </div>
          <!-- search sales order ---------------------------------------------------------------------------------->
          <q-space />
          <q-input v-model="searchCustomer"
                   label="Customer"
                   outlined
                   clearable
                   class="q-pa-xs"
                   debounce="200"
                   style="width:80px;"
                   standout="bg-primary text-white"
                   input-class="text-uppercase"
                   dense>
          </q-input>
          <q-input v-model="searchSalesProject"
                   label="Sales Project"
                   outlined
                   clearable
                   class="q-pa-xs"
                   debounce="200"
                   style="width:120px;"
                   standout="bg-primary text-white"
                   input-class="text-uppercase"
                   dense>
          </q-input>
          <q-input v-model="searchSalesOrder"
                   label="Sales Order"
                   outlined
                   clearable
                   class="q-pa-xs"
                   debounce="200"
                   style="width:120px;"
                   standout="bg-primary text-white"
                   input-class="text-uppercase"
                   dense>
          </q-input>
          <q-input v-model="searchSalesPN"
                   label="Sales PN"
                   outlined
                   clearable
                   class="q-pa-xs"
                   debounce="200"
                   style="width:120px;"
                   standout="bg-primary text-white"
                   input-class="text-uppercase"
                   dense>
          </q-input>
          <q-input v-model="searchSalesDeliDate"
                   label="Deli Date Before"
                   outlined
                   clearable
                   debounce="200"
                   class="q-pa-xs"
                   standout="bg-primary text-white"
                   style="width:150px"
                   mask="####-##-##"
                   dense>
            <template v-slot:append>
              <q-icon name="fas fa-calendar">
                <q-popup-proxy ref="searchSalesDeliDateProxy">
                  <q-date v-model="searchSalesDeliDate"
                          minimal
                          first-day-of-week="1"
                          mask="YYYY-MM-DD"
                          @input="() => $refs.searchSalesDeliDateProxy.hide()" />
                </q-popup-proxy>
              </q-icon>
            </template>
          </q-input>
          <q-input v-model="searchSalesSaleDate"
                   label="Sale Date After"
                   outlined
                   clearable
                   debounce="200"
                   class="q-pa-xs"
                   standout="bg-primary text-white"
                   style="width:150px"
                   mask="####-##-##"
                   dense>
            <template v-slot:append>
              <q-icon name="fas fa-calendar">
                <q-popup-proxy ref="searchSalesSaleDateProxy">
                  <q-date v-model="searchSalesSaleDate"
                          minimal
                          first-day-of-week="1"
                          mask="YYYY-MM-DD"
                          @input="() => $refs.searchSalesSaleDateProxy.hide()" />
                </q-popup-proxy>
              </q-icon>
            </template>
          </q-input>
          <!-- search purchase ---------------------------------------------------------------------------------->
          <q-separator vertical
                       inset />
          <q-input v-model="searchPurchaseOrder"
                   label="Pur. Order"
                   outlined
                   clearable
                   class="q-pa-xs"
                   debounce="200"
                   standout="bg-indigo text-white"
                   input-class="text-uppercase"
                   style="width:120px;"
                   dense>
          </q-input>
          <q-input v-model="searchPurchaseProject"
                   label="Pur. Project"
                   outlined
                   clearable
                   class="q-pa-xs"
                   debounce="200"
                   standout="bg-indigo text-white"
                   input-class="text-uppercase"
                   style="width:120px;"
                   dense>
          </q-input>
          <q-input v-model="searchPurchasePN"
                   label="Purchase PN"
                   outlined
                   clearable
                   class="q-pa-xs"
                   debounce="200"
                   standout="bg-indigo text-white"
                   input-class="text-uppercase"
                   style="width:120px;"
                   dense>
          </q-input>
          <q-input v-model="searchPurchaseVendor"
                   label="Vendor"
                   outlined
                   clearable
                   class="q-pa-xs"
                   debounce="200"
                   standout="bg-indigo text-white"
                   input-class="text-uppercase"
                   style="width:80px;"
                   mask="#####"
                   dense>
          </q-input>
          <q-input v-model="searchPurchaseReturnDate"
                   label="Return Date before"
                   outlined
                   clearable
                   class="q-pa-xs"
                   debounce="200"
                   standout="bg-indigo text-white"
                   input-class="text-uppercase"
                   style="width:150px;"
                   mask="####-##-##"
                   dense>
            <template v-slot:append>
              <q-icon name="fas fa-calendar">
                <q-popup-proxy ref="searchPurchaseReturnDateProxy">
                  <q-date v-model="searchPurchaseReturnDate"
                          minimal
                          first-day-of-week="1"
                          mask="YYYY-MM-DD"
                          @input="() => $refs.searchPurchaseReturnDateProxy.hide()" />
                </q-popup-proxy>
              </q-icon>
            </template>
          </q-input>
        </template>
        <template v-slot:body="props">
          <q-tr :props="props">
            <!-- index------------------------------------------------------------------------------------>
            <q-td key="index1"
                  q-pa-xs>
              {{props.row.index}}
            </q-td>

            <q-td key="index2"
                  v-if="props.row.displayso"
                  :rowspan="props.row.rowspanso"
                  q-pa-xs>
              {{props.row.projectIndex}}
            </q-td>

            <!--so---------------------------------------------------------------------------------------->
            <q-td key="soproject"
                  v-if="props.row.displayso"
                  :rowspan="props.row.rowspanso"
                  q-pa-xs>
              <div>
                {{ props.row.SO_YSOH_PJT}}
              </div>
            </q-td>

            <q-td key="salesorder"
                  v-if="props.row.displayso"
                  :rowspan="props.row.rowspanso"
                  q-pa-xs>
              <div>
                {{ props.row.SO_SOHNUM }}
                <q-badge v-if="props.row.SO_YSOQ_STA === 6"
                         color="primary"
                         floating
                         transparent>Trading</q-badge>
                <q-tooltip>Customer:{{props.row.SO_BPCORD}}<br />Line:{{ props.row.SO_SOPLIN }}</q-tooltip>
              </div>
            </q-td>

            <q-td key="sopn"
                  v-if="props.row.displayso"
                  :rowspan="props.row.rowspanso"
                  q-pa-xs>
              <div>
                {{ props.row.SO_ITMREF }}<q-tooltip>{{ props.row.SO_ITMDESC }}</q-tooltip>
                <div v-show="showMore">{{ props.row.SO_QTY }} {{ props.row.SO_SAU }}</div>
              </div>
            </q-td>

            <q-td key="sodeliplan"
                  v-if="props.row.displayso"
                  :rowspan="props.row.rowspanso"
                  q-pa-xs>
              <div v-if="((new Date(props.row.SO_SHIDATP)).getTime() - (new Date()).getTime())/(1000*60*60*24) < 0 ">
                <q-badge color="red">{{props.row.SO_SHIDATP}}</q-badge>
                <q-tooltip>Request Date:{{ props.row.SO_DEMDLVDAT }}</q-tooltip>
              </div>
              <div v-else-if="((new Date(props.row.SO_SHIDATP)).getTime() - (new Date()).getTime())/(1000*60*60*24) <=  7 ">
                <q-badge color="orange">{{props.row.SO_SHIDATP}}</q-badge>
                <q-tooltip>Request Date:{{ props.row.SO_DEMDLVDAT }}</q-tooltip>
              </div>
              <div v-else-if="((new Date(props.row.SO_SHIDATP)).getTime() - (new Date()).getTime())/(1000*60*60*24) <= 14 ">
                <q-badge color="teal">{{props.row.SO_SHIDATP}}</q-badge>
                <q-tooltip>Request Date:{{ props.row.SO_DEMDLVDAT }}</q-tooltip>
              </div>
              <div v-else="">{{ props.row.SO_SHIDATP }}<q-tooltip>Request Date:{{ props.row.SO_DEMDLVDAT }}</q-tooltip>
              </div>
            </q-td>

            <q-td key="soatby"
                  v-if="props.row.displayso"
                  :rowspan="props.row.rowspanso"
                  q-pa-xs>
              <div>
                {{ props.row.SO_CREDAT }}<div v-show="showMore">{{ props.row.SO_CREUSR }}</div>
              </div>
            </q-td>
            <!--wo---------------------------------------------------------------------------------------->
            <q-td key="workorder"
                  v-if="props.row.displaywo"
                  :rowspan="props.row.rowspanwo"
                  q-pa-xs>
              <div>
                {{ props.row.WO_MFGNUM }}
              </div>
            </q-td>

            <q-td key="bomseq"
                  v-if="props.row.displaywo"
                  :rowspan="props.row.rowspanwo"
                  q-pa-xs>
              <div>
                {{ props.row.WO_BOMSEQ }}
              </div>
            </q-td>

            <q-td key="wopn"
                  v-if="props.row.displaywo"
                  :rowspan="props.row.rowspanwo"
                  q-pa-xs>
              <div>
                {{ props.row.WO_ITMREF }}
                <div v-show="showMore">{{ props.row.WO_BOMQTY }} {{ props.row.WO_BOMUOM }}</div>
              </div>
            </q-td>

            <q-td key="woshortqty"
                  v-if="props.row.displaywo"
                  :rowspan="props.row.rowspanwo"
                  q-pa-xs>
              <div>
                {{ props.row.WO_SHIQTY }}
                <div v-show="props.row.WO_ALLQTY"
                     style="display:inline"> : {{ props.row.WO_ALLQTY }}</div>
              </div>
            </q-td>

            <q-td key="wocreate"
                  v-if="props.row.displaywo"
                  :rowspan="props.row.rowspanwo"
                  q-pa-xs>
              <div>
                {{ props.row.WO_CREDAT }}<div v-show="showMore">{{ props.row.WO_CREUSR }}</div>
              </div>
            </q-td>
            <!--po---------------------------------------------------------------------------------------->
            <q-td key="pochaseorder"
                  v-if="props.row.displaypo"
                  :rowspan="props.row.rowspanpo"
                  q-pa-xs>
              <div>
                {{ props.row.PO_POHNUM }}<q-tooltip><br />Line:{{ props.row.PO_POPLIN }}</q-tooltip>
              </div>
            </q-td>

            <q-td key="poproject"
                  v-if="props.row.displaypo"
                  :rowspan="props.row.rowspanpo"
                  q-pa-xs>
              <div>
                {{ props.row.PO_PJT }}
              </div>
            </q-td>

            <q-td key="popn"
                  v-if="props.row.displaypo"
                  :rowspan="props.row.rowspanpo"
                  q-pa-xs>
              <div>
                {{ props.row.PO_ITMREF }}<q-tooltip>{{ props.row.PO_ITMDESC }}</q-tooltip>
                <div v-show="showMore">{{ props.row.PO_QTYPUU}} {{props.row.PO_PUU}}</div>
              </div>
            </q-td>

            <q-td key="povender"
                  v-if="props.row.displaypo"
                  :rowspan="props.row.rowspanpo"
                  q-pa-xs>
              <div>
                {{ props.row.PO_BPSNUM }}
              </div>
            </q-td>

            <q-td key="porequestdate"
                  v-if="props.row.displaypo"
                  :rowspan="props.row.rowspanpo"
                  q-pa-xs>
              <div v-if="((new Date(props.row.PO_EXTRCPDAT)).getTime() - (new Date(props.row.SO_SHIDATP)).getTime()) > 0">
                <q-badge color="red">{{props.row.PO_EXTRCPDAT}}</q-badge>
              </div>
              <div v-else-if="((new Date(props.row.PO_EXTRCPDAT)).getTime() - (new Date(props.row.SO_SHIDATP)).getTime()) == 0">
                <q-badge color="orange">{{props.row.PO_EXTRCPDAT}}</q-badge>
              </div>
              <div v-else-if="((new Date(props.row.PO_EXTRCPDAT)).getTime() - (new Date()).getTime()) < 0 && !props.row.PT_PTHNUM && props.value">
                <q-badge color="red">{{props.row.PO_EXTRCPDAT}}</q-badge>
              </div>
              <div v-else>
                {{ props.row.PO_EXTRCPDAT }}
              </div>
            </q-td>

            <q-td key="poackdate"
                  v-if="props.row.displaypo"
                  :rowspan="props.row.rowspanpo"
                  q-pa-xs>
              <div v-if="((new Date(props.row.PO_LINOCNDATA)).getTime() - (new Date(props.row.SO_SHIDATP)).getTime()) > 0">
                <q-badge color="red">{{props.row.PO_LINOCNDATA}}</q-badge>
              </div>
              <div v-else-if="((new Date(props.row.PO_LINOCNDATA)).getTime() - (new Date(props.row.SO_SHIDATP)).getTime()) == 0">
                <q-badge color="orange">{{props.row.PO_LINOCNDATA}}</q-badge>
              </div>
              <div v-else-if="((new Date(props.row.PO_LINOCNDATA)).getTime() - (new Date()).getTime()) < 0 && !props.row.PT_PTHNUM && props.row.PO_LINOCNDATA">
                <q-badge color="red">{{props.row.PO_LINOCNDATA}}</q-badge>
              </div>
              <div v-else>
                {{ props.row.PO_LINOCNDATA }}
              </div>
            </q-td>

            <q-td key="poatby"
                  v-if="props.row.displaypo"
                  :rowspan="props.row.rowspanpo"
                  q-pa-xs>
              <div>
                {{ props.row.PO_CREDAT }}
                <div v-show="showMore">{{ props.row.PO_CREUSR }}</div>
              </div>
            </q-td>
            <!--pt---------------------------------------------------------------------------------------->
            <q-td key="ptreceipt"
                  q-pa-xs>
              <div>
                {{ props.row.PT_PTHNUM }}<q-tooltip>Line:{{ props.row.PT_PTDLIN }}</q-tooltip>
                <div v-show="showMore">{{ props.row.PT_QTYPUU}} {{props.row.PT_PUU}}</div>
              </div>
            </q-td>

            <q-td key="ptatby"
                  q-pa-xs>
              <div>
                {{ props.row.PT_REPDAT }}
                <div v-show="showMore">{{ props.row.PT_CREUSR }}</div>
              </div>
            </q-td>
            <!--end---------------------------------------------------------------------------------------->
          </q-tr>
        </template>

      </q-table>
    </div>
  </q-page>
</template>

<script>

export default {
  name: 'OpenSaleItems',
  data () {
    return {
      loading: true,
      pagination: {
        descending: false,
        rowsPerPage: 200
        // rowsNumber: 1131
        // rowsNumber: xx if getting data from a server
      },
      columns: [
        { name: 'index1', label: '#', field: 'index', sortable: true, align: 'center', headerClasses: 'bg-primary text-white text-xs' },
        { name: 'index2', label: '#', align: 'center', headerClasses: 'bg-primary text-white' },
        { name: 'soproject', label: 'Project NO', sortable: true, align: 'left', headerClasses: 'bg-primary text-white' },
        { name: 'salesorder', label: 'Sales Order', align: 'left', headerClasses: 'bg-primary text-white' },
        { name: 'sopn', label: 'PN / Qty', align: 'left', headerClasses: 'bg-primary text-white' },
        { name: 'sodeliplan', label: 'Deli Plan Date', field: 'SO_SHIDATP', sortable: true, headerClasses: 'bg-primary text-white' },
        { name: 'soatby', label: 'Create At/By', field: 'SO_CREDAT', sortable: true, headerClasses: 'bg-primary text-white' },
        { name: 'workorder', label: 'Work Order', field: 'WO_MFGNUM', align: 'left', headerClasses: 'bg-secondary text-white' },
        { name: 'bomseq', label: 'Bom#', field: 'WO_BOMSEQ', align: 'center', headerClasses: 'bg-secondary text-white' },
        { name: 'wopn', label: 'PN / Bom Qty', align: 'left', headerClasses: 'bg-secondary text-white' },
        { name: 'woshortqty', label: 'Sht:Avl', field: 'WO_SHIQTY', align: 'center', headerClasses: 'bg-secondary text-white' },
        { name: 'wocreate', label: 'Create At/By', align: 'right', headerClasses: 'bg-secondary text-white' },
        { name: 'pochaseorder', label: 'Purchase Order', align: 'left', headerClasses: 'bg-indigo text-white' },
        { name: 'poproject', label: 'For Project', align: 'left', field: 'PO_PJT', headerClasses: 'bg-indigo text-white' },
        { name: 'popn', label: 'PN / Qty', align: 'left', headerClasses: 'bg-indigo text-white' },
        { name: 'povender', label: 'Vendor', align: 'center', field: 'PO_BPSNUM', headerClasses: 'bg-indigo text-white' },
        { name: 'porequestdate', label: 'Request Date', align: 'right', sortable: true, field: 'PO_EXTRCPDAT', headerClasses: 'bg-indigo text-white' },
        { name: 'poackdate', label: 'Ack. Date', align: 'right', sortable: true, field: 'PO_LINOCNDATA', headerClasses: 'bg-indigo text-white' },
        { name: 'poatby', label: 'Create At/By', align: 'right', headerClasses: 'bg-indigo text-white' },
        { name: 'ptreceipt', label: 'Receipt NO / Qty', align: 'left', headerClasses: 'bg-green text-white' },
        { name: 'ptatby', label: 'Create At/By', align: 'right', headerClasses: 'bg-green text-white' }
      ],
      data: [],
      showMore: false,
      mergeCell: true,
      searchCustomer: '',
      searchSalesProject: '',
      searchSalesOrder: '',
      searchSalesPN: '',
      searchSalesDeliDate: '',
      searchSalesSaleDate: '',
      searchPurchaseOrder: '',
      searchPurchaseProject: '',
      searchPurchasePN: '',
      searchPurchaseVendor: '',
      searchPurchaseReturnDate: ''
    }
  },
  computed: {
    filter () {
      let filt = {}
      if (this.searchCustomer) {
        filt.searchCustomer = this.searchCustomer
      }
      if (this.searchSalesProject) {
        filt.searchSalesProject = this.searchSalesProject
      }
      if (this.searchSalesOrder) {
        filt.searchSalesOrder = this.searchSalesOrder
      }
      if (this.searchSalesPN) {
        filt.searchSalesPN = this.searchSalesPN
      }
      if (this.searchSalesDeliDate) {
        filt.searchSalesDeliDate = this.searchSalesDeliDate
      }
      if (this.searchSalesSaleDate) {
        filt.searchSalesSaleDate = this.searchSalesSaleDate
      }
      if (this.searchPurchaseProject) {
        filt.searchPurchaseProject = this.searchPurchaseProject
      }
      if (this.searchPurchaseOrder) {
        filt.searchPurchaseOrder = this.searchPurchaseOrder
      }
      if (this.searchPurchasePN) {
        filt.searchPurchasePN = this.searchPurchasePN
      }
      if (this.searchPurchaseVendor) {
        filt.searchPurchaseVendor = this.searchPurchaseVendor
      }
      if (this.searchPurchaseReturnDate) {
        filt.searchPurchaseReturnDate = this.searchPurchaseReturnDate
      }

      if (Object.keys(filt).length === 0) {
        return ''
      } else {
        return filt
      }
    }
  },
  methods: {
    loadData () {
      this.loading = true
      this.$q.loading.show({
        message: '<h3>Loading...</h3>'
      })

      this.$axios.get('/Data/OpenSaleItems')
        .then((response) => {
          this.data = response.data

          let projectIndex = 0
          this.data.forEach((row, index) => {
            row.index = index + 1

            if (index === 0) {
              projectIndex++
              row.projectIndex = projectIndex
            } else {
              if (this.data[index].SO_YSOH_PJT !== this.data[index - 1].SO_YSOH_PJT) {
                projectIndex++
                row.projectIndex = projectIndex
              } else {
                row.projectIndex = projectIndex
              }
            }
          })
          // we are not going to change this array,
          // so why not freeze it to avoid Vue adding overhead
          // with state change detection
          Object.freeze(this.data)
          this.loading = false
          this.$q.loading.hide()
        })
        .catch(() => {
          this.loading = false
          this.$q.loading.hide()

          this.$q.notify({
            color: 'negative',
            position: 'top',
            message: 'Loading data failed!',
            icon: 'fas fa-exclamation-triangle'
          })
        })
    },
    myFilterMethod (rows, terms, cols, getCellValue) {
      let rowsTemp = rows
      if (terms.searchCustomer !== undefined) {
        rowsTemp = rowsTemp.filter(row => row.SO_BPCORD.indexOf(terms.searchCustomer.toUpperCase()) !== -1)
      }
      if (terms.searchSalesProject !== undefined) {
        rowsTemp = rowsTemp.filter(row => row.SO_YSOH_PJT.indexOf(terms.searchSalesProject.toUpperCase()) !== -1)
      }
      if (terms.searchSalesOrder !== undefined) {
        rowsTemp = rowsTemp.filter(row => row.SO_SOHNUM.indexOf(terms.searchSalesOrder.toUpperCase()) !== -1)
      }
      if (terms.searchSalesPN !== undefined) {
        rowsTemp = rowsTemp.filter(row => row.SO_ITMREF.indexOf(terms.searchSalesPN.toUpperCase()) !== -1)
      }
      if (terms.searchSalesDeliDate !== undefined) {
        rowsTemp = rowsTemp.filter(row => (new Date(row.SO_SHIDATP)).getTime() - (new Date(terms.searchSalesDeliDate)).getTime() <= 0)
      }
      if (terms.searchSalesSaleDate !== undefined) {
        rowsTemp = rowsTemp.filter(row => (new Date(row.SO_CREDAT)).getTime() - (new Date(terms.searchSalesSaleDate)).getTime() >= 0)
      }
      if (terms.searchPurchaseProject !== undefined) {
        rowsTemp = rowsTemp.filter(row => row.PO_POHNUM.indexOf(terms.searchPurchaseProject.toUpperCase()) !== -1)
      }
      if (terms.searchPurchaseProject !== undefined) {
        rowsTemp = rowsTemp.filter(row => row.PO_PJT.indexOf(terms.searchPurchaseProject.toUpperCase()) !== -1)
      }
      if (terms.searchPurchasePN !== undefined) {
        rowsTemp = rowsTemp.filter(row => row.PO_ITMREF.indexOf(terms.searchPurchasePN.toUpperCase()) !== -1)
      }
      if (terms.searchPurchaseVendor !== undefined) {
        rowsTemp = rowsTemp.filter(row => (row.PO_BPSNUM + '').indexOf(terms.searchPurchaseVendor.toUpperCase()) !== -1)
      }
      if (terms.searchPurchaseReturnDate !== undefined) {
        rowsTemp = rowsTemp.filter(row => (new Date(row.PO_EXTRCPDAT)).getTime() - (new Date(terms.searchSalesDeliDate)).getTime() <= 0 ||
          (new Date(row.PO_LINOCNDATA)).getTime() - (new Date(terms.searchSalesDeliDate)).getTime() <= 0)
      }
      return rowsTemp
    },
    spanRow () {
      let rowspanso = 0
      let rowspanwo = 0
      let rowspanpo = 0

      this.$children[0].$children[0].computedRows.forEach((row, index, rows) => {
        // fisrt row at every page
        if (index === 0 || this.mergeCell === false) {
          row.displayso = true
          row.displaywo = true
          row.displaypo = true
          rowspanso = 1
          rowspanwo = 1
          rowspanpo = 1

          row.rowspanso = rowspanso
          row.rowspanwo = rowspanwo
          row.rowspanpo = rowspanpo
        } else { // second row to end at every page
          // span so
          if (row.SO_YSOH_PJT === rows[index - 1].SO_YSOH_PJT) {
            row.displayso = false
            rows[index - rowspanso].rowspanso = rowspanso + 1
            rowspanso++
            // span wo
            if (row.WO_MFGNUM && row.WO_MFGNUM === rows[index - 1].WO_MFGNUM && row.WO_BOMSEQ === rows[index - 1].WO_BOMSEQ) {
              row.displaywo = false
              rows[index - rowspanwo].rowspanwo = rowspanwo + 1
              rowspanwo++
              // span po
              if (row.PO_POHNUM === rows[index - 1].PO_POHNUM) {
                row.displaypo = false
                rows[index - rowspanpo].rowspanpo = rowspanpo + 1
                rowspanpo++
              } else { // span po
                row.displaypo = true
                rowspanpo = 1

                row.rowspanpo = rowspanpo
              }
            } else { // span wo
              row.displaywo = true
              row.displaypo = true
              rowspanwo = 1
              rowspanpo = 1

              row.rowspanwo = rowspanwo
              row.rowspanpo = rowspanpo
            }
          } else { // span so
            row.displayso = true
            row.displaywo = true
            row.displaypo = true

            rowspanso = 1
            rowspanwo = 1
            rowspanpo = 1

            row.rowspanso = rowspanso
            row.rowspanwo = rowspanwo
            row.rowspanpo = rowspanpo
          }
        }
      })
    }
  },
  mounted: function () {
    this.loadData()
  },
  beforeUpdate: function () {

    // console.log('before updated'
  },
  updated: function () {
    this.spanRow()
    // toggle twice to active rowspan and restore status
    this.mergeCell = !this.mergeCell
    this.mergeCell = !this.mergeCell
  }
}
</script>

<style lang="sass">
.my-sticky-header-column-table
  /* specifying max-width so the example can
    highlight the sticky column on any browser window */

  /* max height is important */
  .q-table__middle
    max-height: 800px

  td:first-child
    /* bg color is important for td; just specify one */
    background-color: #c1f4cd !important

  tr th
    position: sticky
    /* higher than z-index for td below */
    z-index: 2
    /* bg color is important; just specify one */

  tr:first-child th
    top: 0
  tr:first-child th:first-child
    /* highest z-index */
    z-index: 3

  td:first-child
    z-index: 1

  td:first-child, th:first-child
    position: sticky
    left: 0

  /* this is when the loading indicator appears */
  &.q-table--loading thead tr:last-child th
    /* height of all previous header rows */
    top: 48px
    /* highest z-index */
    z-index: 3

.q-field--dense .q-field__control, .q-field--dense .q-field__marginal
  height: 30px;
.q-field--outlined .q-field__control
  padding: 0 4px

</style>
