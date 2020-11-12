<template>
  <q-page class="row">
    <q-list class="col-md-3 col-lg-3 col-xl-3 q-pa-md">
      <!-- Searching Label-->
      <q-toolbar class="bg-primary text-white shadow-2">
        <q-toolbar-title>Searching Your Product
        </q-toolbar-title>
      </q-toolbar>
      <!-- PN Select Input-->
      <q-select-input-pn v-on:searchPN="searchPN" />

      <q-list bordered
              class="q-mt-md">
        <!--Searching Results Label -->
        <q-item style="padding:0px">
          <q-item-section>
            <q-toolbar class="bg-teal text-white ">
              <q-toolbar-title>Searching Results
              </q-toolbar-title>
            </q-toolbar>
          </q-item-section>
        </q-item>
        <!-- Show PN Famalily-->
        <q-item>
          <q-item-section>
            <q-item-label class="text-teal "
                          style="font-weight:bolder; font-size:30px">{{pnRoot}}
            </q-item-label>
            <q-item-label-file-list :pn="pnRoot" />
          </q-item-section>
        </q-item>
        <!-- Show PN lists start-->
        <q-expansion-item-pn-list :pnRoot="pnRoot" />
        <!-- Show PN lists end-->

        <!-- Stock Summary Label -->
        <q-item style="padding:0px">
          <q-item-section>
            <q-toolbar class="bg-teal text-white ">
              <q-toolbar-title>Stock Summary
              </q-toolbar-title>
            </q-toolbar>
          </q-item-section>
        </q-item>
        <!-- Show Stock -->
        <q-item-inventory-stock :pnRoot="pnRoot" />

        <q-separator />
        <!-- Delivery Label -->
        <q-item style="padding:0px">
          <q-item-section>
            <q-toolbar class="bg-teal text-white ">
              <q-toolbar-title>Delivery(Day)
              </q-toolbar-title>
            </q-toolbar>
          </q-item-section>
        </q-item>
        <!-- Show Delivery -->
        <q-item-delivery-duration :pnRoot="pnRoot" />

      </q-list>
    </q-list>
    <q-list class="col q-pa-md column">
      <!-- History Label-->
      <q-toolbar class="bg-teal text-white shadow-2">
        <q-toolbar-title>History
        </q-toolbar-title>
      </q-toolbar>
      <!-- Show Sales History -->
      <q-item-echart-sales-history :pnRoot="pnRoot"
                                   style="padding:0px; height:350px;" />
      <!-- Show Quote History -->
      <q-item-echart-quote-history :pnRoot="pnRoot"
                                   style="padding:0px; height:350px;" />
      <!-- Show Cost History -->
      <q-item-echart-cost-history :pnRoot="pnRoot"
                                   style="padding:0px; height:350px;" />
    </q-list>
  </q-page>
</template>

<script>
import QExpansionItemPnList from 'components/QExpansionItemPnList'
import QItemLabelFileList from 'components/QItemLabelFileList'
import QSelectInputPn from 'components/QSelectInputPn'
import QItemDeliveryDuration from 'components/QItemDeliveryDuration'
import QItemInventoryStock from 'components/QItemInventoryStock'
import QItemEchartSalesHistory from 'components/QItemEchartSalesHistory'
import QItemEchartQuoteHistory from 'components/QItemEchartQuoteHistory'
import QItemEchartCostHistory from 'components/QItemEchartCostHistory'

export default {
  name: 'Products',
  components: {
    QExpansionItemPnList,
    QItemLabelFileList,
    QSelectInputPn,
    QItemDeliveryDuration,
    QItemInventoryStock,
    QItemEchartSalesHistory,
    QItemEchartQuoteHistory,
    QItemEchartCostHistory
  },
  data () {
    return {
      pnRoot: ''
    }
  },
  methods: {
    searchPN (pnRoot) {
      this.pnRoot = pnRoot
    },
    mounted: function () {
    }
  }
}
</script>

<style lang="sass" >
</style>
