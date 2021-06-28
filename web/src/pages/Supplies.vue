<template>
  <q-page class="row">
    <div class="col-md-3 col-lg-3 col-xl-3 q-pa-md" bordered flat>
      <!-- Searching Label-->
      <q-toolbar class="bg-primary text-white shadow-2">
        <q-toolbar-title>Searching Your Supply
        </q-toolbar-title>
      </q-toolbar>
      <!-- Supply Select Input-->
      <q-select-input-supply v-on:searchSupply="searchSupply" />
      <q-item style="padding:0px">
        <q-item-section style="padding:0px">
            <q-input v-model="dateFrom" filled type="date" label="From" />
        </q-item-section>
        <q-item-section style="padding:0px">
            <q-input v-model="dateTo" filled type="date" label="To" />
        </q-item-section>
      </q-item>
    </div>
    <div class="col q-pa-md">
        <!--Searching Results Label -->
        <q-toolbar class="bg-teal text-white">
          <q-toolbar-title>Searching Results
          </q-toolbar-title>
        </q-toolbar>
        <!-- Show Supply Info-->
        <q-item-supply-info :supplyCode="supplyCode" style="padding:0px"/>
    </div>
    <div class="col-12 q-pa-md row" style="padding-top:0px">
       <!-- Show Open Items -->
      <q-markup-table-supply-open-items :supplyCode="supplyCode"
                                        :dateFrom="dateFrom"
                                        :dateTo="dateTo"
                                        class="col-12"
                                   style="padding:0px; height:250px; " />
      <!-- Summary Label-->
      <q-toolbar class="bg-teal text-white shadow-2">
        <q-toolbar-title>Summary
        </q-toolbar-title>
      </q-toolbar>
      <!-- Show Open Qty -->
      <q-item-echart-supply-open-qty :supplyCode="supplyCode"
                                   class="col-6"
                                   style="padding:0px; height:250px;" />
      <!-- Show Open Amount -->
      <q-item-echart-supply-open-amount :supplyCode="supplyCode"
                                   class="col-6"
                                   style="padding:0px; height:250px;" />
      <!-- Show Total Qty -->
      <q-item-echart-supply-total-qty :supplyCode="supplyCode"
                                        :dateFrom="dateFrom"
                                        :dateTo="dateTo"
                                   class="col-6"
                                   style="padding:0px; height:250px;" />
      <!-- Show Total Amount -->
      <q-item-echart-supply-total-amount :supplyCode="supplyCode"
                                        :dateFrom="dateFrom"
                                        :dateTo="dateTo"
                                   class="col-6"
                                   style="padding:0px; height:250px;" />
      <!-- Show Delivery History -->
      <q-item-echart-supply-delivery-history :supplyCode="supplyCode"
                                        :dateFrom="dateFrom"
                                        :dateTo="dateTo"
                                   class="col-6"
                                   style="padding:0px; height:250px;" />
      <!-- Show Delivery History -->
      <q-item-echart-supply-delay-history :supplyCode="supplyCode"
                                        :dateFrom="dateFrom"
                                        :dateTo="dateTo"
                                   class="col-6"
                                   style="padding:0px; height:250px;" />
    </div>
  </q-page>
</template>

<script>
import { date } from 'quasar'
import QItemSupplyInfo from 'components/QItemSupplyInfo'
import QSelectInputSupply from 'components/QSelectInputSupply'
import QItemEchartSupplyTotalAmount from 'components/QItemEchartSupplyTotalAmount'
import QItemEchartSupplyTotalQty from 'components/QItemEchartSupplyTotalQty'
import QItemEchartSupplyOpenAmount from 'components/QItemEchartSupplyOpenAmount'
import QItemEchartSupplyOpenQty from 'components/QItemEchartSupplyOpenQty'
import QItemEchartSupplyDeliveryHistory from 'components/QItemEchartSupplyDeliveryHistory'
import QItemEchartSupplyDelayHistory from 'components/QItemEchartSupplyDelayHistory'
import QMarkupTableSupplyOpenItems from 'components/QMarkupTableSupplyOpenItems'

export default {
  name: 'Supplies',
  components: {
    QItemSupplyInfo,
    QSelectInputSupply,
    QItemEchartSupplyTotalAmount,
    QItemEchartSupplyTotalQty,
    QItemEchartSupplyOpenAmount,
    QItemEchartSupplyOpenQty,
    QItemEchartSupplyDeliveryHistory,
    QItemEchartSupplyDelayHistory,
    QMarkupTableSupplyOpenItems
  },
  data () {
    return {
      supplyCode: '',
      dateFrom: '2000-01-01',
      dateTo: ''
    }
  },
  methods: {
    searchSupply (supplyCode) {
      this.supplyCode = supplyCode
    }
  },
  mounted: function () {
    const timeStamp = Date.now()
    this.dateFrom = date.formatDate(date.addToDate(timeStamp, { year: -3 }), 'YYYY-MM-DD')
    this.dateTo = date.formatDate(timeStamp, 'YYYY-MM-DD')
  }
}
</script>
