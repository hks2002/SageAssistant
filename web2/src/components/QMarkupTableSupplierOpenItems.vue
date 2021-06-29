<template>
  <q-markup-table
    dense
    class="q-pa-sm"
    v-if="supplierOpenItems.length>0"
    style="height:250px"
  >
    <thead>
      <tr>
        <th
          colspan="13"
          style="padding:0px"
        >
          <q-toolbar
            class="bg-teal text-white shadow-2"
            rowspan="13"
          >
            <q-toolbar-title class="text-left">OpenItems(All-{{supplierOpenItems.length}})
            </q-toolbar-title>
          </q-toolbar>
        </th>
      </tr>
      <tr class="bg-primary text-white">
        <th class="text-center text-caption">#</th>
        <th>Site</th>
        <th>SupplierCode</th>
        <th>PurchaseNO</th>
        <th>ProjectNO</th>
        <th>PN</th>
        <th>Description</th>
        <th class="text-center">AckDate</th>
        <th class="text-center">ExpectDate</th>
        <th class="text-center">OrderDate</th>
        <th class="text-center">DaysDelay</th>
      </tr>
    </thead>
    <tbody>
      <tr
        v-for="(item, index) in supplierOpenItems"
        :key="index"
      >
        <td class="text-center">{{index}}</td>
        <td>{{item.Site}}</td>
        <td>{{item.SupplierCode}}</td>
        <td>{{item.PurchaseNO}}</td>
        <td>{{item.ProjectNO}}</td>
        <td>{{item.PN}}</td>
        <td>{{item.Description}}</td>
        <td class="text-center">{{item.AckDate}}</td>
        <td class="text-center">{{item.ExpectDate}}</td>
        <td class="text-center">{{item.OrderDate}}</td>
        <td class="text-center">{{item.DaysDelay}}</td>
      </tr>
    </tbody>
  </q-markup-table>
</template>

<script>
import { defineComponent, onMounted, ref, watch } from 'vue'
import { notifyError } from 'assets/common'
import { axios } from 'boot/axios'

export default defineComponent({
  name: 'QMarkupTableSupplierOpenItems',

  props: {
    supplierCode: String,
    dateFrom: String,
    dateTo: String
  },

  setup (props, ctx) {
    const supplierOpenItems = ref([])
    const showLoading = ref(false)

    const doUpdate = () => {
      showLoading.value = true

      axios.get('/Data/SupplierOpenItems?SupplierCode=' + props.supplierCode + '&DateFrom=' + props.dateFrom + '&DateTo=' + props.dateTo)
        .then((response) => {
          supplierOpenItems.value = response.data
        })
        .catch((e) => {
          console.error(e)
          notifyError('Loading Supplier Open Items Failed!')
        }).finally(() => {
          showLoading.value = false
        })
    }

    onMounted(() => {
      console.debug('onMounted SupplierOpenItems')
      if (props.supplierCode) {
        doUpdate()
      }
    })

    // Don't use watchEffect, it run before Mounted.
    watch(() => [props.supplierCode, props.dateFrom, props.dateTo], (...newAndold) => {
      // newAndold[1]:old
      // newAndold[0]:new
      console.debug('watch:' + newAndold[1] + ' ---> ' + newAndold[0])
      if (newAndold[0][0]) {
        doUpdate()
      }
    })

    return {
      supplierOpenItems,
      showLoading
    }
  }
})
</script>
