<template>
<q-markup-table dense class="q-pa-sm" v-if="supplyOpenItems.length>0">
      <thead>
        <tr>
          <th colspan="13" style="padding:0px">
            <q-toolbar class="bg-teal text-white shadow-2" rowspan="13">
              <q-toolbar-title class="text-left">OpenItems(All)
              </q-toolbar-title>
            </q-toolbar>
          </th>
        </tr>
        <tr class="bg-primary text-white">
          <th class="text-center text-caption">#</th>
          <th>Site</th>
          <th>SupplyCode</th>
          <th>PurchaseNO</th>
          <th>ProjectNO</th>
          <th>PN</th>
          <th>Description</th>
          <th class="text-center">AckDate</th>
          <th class="text-center">ExpectDate</th>
          <th class="text-center">OrderDate</th>
          <th>ReceiptNO</th>
          <th class="text-center">ReceiptDate</th>
          <th class="text-center">DaysDelay</th>
        </tr>
      </thead>
       <tbody>
         <tr v-for="(item, index) in supplyOpenItems" :key="index">
           <td class="text-center">{{index}}</td>
           <td>{{item.Site}}</td>
           <td>{{item.SupplyCode}}</td>
           <td>{{item.PurchaseNO}}</td>
           <td>{{item.ProjectNO}}</td>
           <td>{{item.PN}}</td>
           <td>{{item.Description}}</td>
           <td class="text-center">{{item.AckDate}}</td>
           <td class="text-center">{{item.ExpectDate}}</td>
           <td class="text-center">{{item.OrderDate}}</td>
           <td >{{item.ReceiptNO}}</td>
           <td class="text-center">{{item.ReceiptDate}}</td>
           <td class="text-center">{{item.DaysDelay}}</td>
         </tr>
       </tbody>
 </q-markup-table>
</template>

<script>
if (process.env.DEV) {
  require('../mock/SupplyOpenItems')
}
export default {
  name: 'QMarkupTableSupplyOpenItems',
  props: {
    supplyCode: String,
    dateFrom: String,
    dateTo: String
  },
  data () {
    return {
      supplyOpenItems: {},
      showLoading: false
    }
  },
  methods: {
    doUpdate (supplyCode, dateFrom, dateTo) {
      this.showLoading = true

      this.$axios.get('/Data/SupplyOpenItems?SupplyCode=' + supplyCode + '&DateFrom=' + dateFrom + '&DateTo=' + dateTo)
        .then((response) => {
          console.debug('[axios] ' + response.status + ' ' + response.statusText + ' ' + response.config.url)
          console.debug(JSON.stringify(response.data))

          this.supplyOpenItems = response.data
          this.showLoading = false
        })
        .catch((e) => {
          this.showLoading = false

          this.$q.notify({
            color: 'negative',
            position: 'top',
            message: 'Loading Supply Open Items Failed!',
            icon: 'fas fa-exclamation-triangle'
          })
        })
    },
    doReset () {
      this.supplyOpenItems = {}
      this.showLoading = false
    }
  },
  watch: {
    supplyCode: {
      handler (newVal, oldVal) {
        console.debug(oldVal + ' --> ' + newVal)

        if (newVal) {
          this.doUpdate(newVal, this.dateFrom, this.dateTo)
        } else {
          this.doReset()
        }
      },
      immediate: true
    },
    dateFrom: {
      handler (newVal, oldVal) {
        console.debug(oldVal + ' --> ' + newVal)

        if (this.supplyCode) {
          this.doUpdate(this.supplyCode, newVal, this.dateTo)
        } else {
          this.doReset()
        }
      },
      immediate: true
    },
    dateTo: {
      handler (newVal, oldVal) {
        console.debug(oldVal + ' --> ' + newVal)

        if (this.supplyCode) {
          this.doUpdate(this.supplyCode, this.dateFrom, newVal)
        } else {
          this.doReset()
        }
      },
      immediate: true
    }
  },
  mounted: function () {
  }
}
</script>
