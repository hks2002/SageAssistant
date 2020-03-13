<template>
  <q-page class="row">
    <div class="column col-sm-4 col-lg-3 ">

      <q-item>
        <q-item-section avatar>
          <q-avatar color="primary"
                    text-color="white">
            C
          </q-avatar>
        </q-item-section>
        <q-item-section>
          <q-input label="COC By Project"
                   v-model="COCProj"
                   outlined
                   clearable
                   hint="e.g.: ZCC190001-1 or ZCC190001-11 or ZDSRP190001"
                   :hide-hint="true"
                   @input="showCOC"
                   input-class="text-uppercase"
                   class="q-pa-xs">
            <q-checkbox v-model="CustomerOri"
                        @input="showCOC"
                        label="Ori" />
          </q-input>
        </q-item-section>
        <q-item-section side>
          <q-btn text-color="indigo-7"
                 dense
                 @click="exportWordCOC"
                 icon="fas fa-file-word" />
        </q-item-section>
        <q-item-section side>
          <q-btn text-color="deep-orange-10"
                 dense
                 @click="exportPdfCOC"
                 icon="fas fa-file-pdf" />
        </q-item-section>
      </q-item>

      <q-item>
        <q-item-section avatar>
          <q-avatar color="primary"
                    text-color="white">
            D
          </q-avatar>
        </q-item-section>
        <q-item-section>
          <q-input label="Delivery NO"
                   v-model="DeliveryNO"
                   outlined
                   clearable
                   hint="e.g.: ZBL1901001"
                   :hide-hint="true"
                   @input="showDelivery"
                   input-class="text-uppercase"
                   class="q-pa-xs">
          </q-input>
        </q-item-section>
        <q-item-section side>
          <q-btn text-color="indigo-7"
                 dense
                 @click="exportWordDelivery"
                 icon="fas fa-file-word" />
        </q-item-section>
        <q-item-section side>
          <q-btn text-color="deep-orange-10"
                 dense
                 @click="exportPdfDelivery"
                 icon="fas fa-file-pdf" />
        </q-item-section>
      </q-item>

      <q-item>
        <q-item-section avatar>
          <q-avatar color="primary"
                    text-color="white">
            I
          </q-avatar>
        </q-item-section>
        <q-item-section>
          <q-input label="Invoice NO"
                   v-model="InvoiceNO"
                   outlined
                   clearable
                   hint="e.g.: ZFC1901001 or ZPC190001"
                   :hide-hint="true"
                   @input="showInvoice"
                   input-class="text-uppercase"
                   class="q-pa-xs">
          </q-input>
        </q-item-section>
        <q-item-section side>
          <q-btn text-color="indigo-7"
                 dense
                 @click="exportWordInvoice"
                 icon="fas fa-file-word" />
        </q-item-section>
        <q-item-section side>
          <q-btn text-color="deep-orange-10"
                 dense
                 @click="exportPdfInvoice"
                 icon="fas fa-file-pdf" />
        </q-item-section>
      </q-item>
      <q-item>
        <q-item-section avatar>
          <q-avatar color="primary"
                    text-color="white">
            P
          </q-avatar>
        </q-item-section>
        <q-item-section>
          <q-input label="Purchase NO"
                   v-model="PurchaseNO"
                   outlined
                   clearable
                   hint="e.g.: ZCF1901001"
                   :hide-hint="true"
                   @input="showPO"
                   input-class="text-uppercase"
                   class="q-pa-xs">
            <q-checkbox v-model="POtax"
                        @input="showPO"
                        label="Tax" />
          </q-input>
        </q-item-section>
        <q-item-section side>
          <q-btn text-color="indigo-7"
                 dense
                 @click="exportWordPO"
                 icon="fas fa-file-word" />
        </q-item-section>
        <q-item-section side>
          <q-btn text-color="deep-orange-10"
                 dense
                 @click="exportPdfPO"
                 icon="fas fa-file-pdf" />
        </q-item-section>
      </q-item>
      <q-item>
        <q-item-section avatar>
          <q-avatar color="primary"
                    text-color="white">
            W
          </q-avatar>
        </q-item-section>
        <q-item-section>
          <q-input label="Work Order NO"
                   v-model="WorkOrderNO"
                   outlined
                   clearable
                   hint="e.g.: ZOF1901001"
                   :hide-hint="true"
                   @input="showWO"
                   input-class="text-uppercase"
                   class="q-pa-xs">
          </q-input>
        </q-item-section>
        <q-item-section side>
          <q-btn text-color="indigo-7"
                 dense
                 @click="exportWordWO"
                 icon="fas fa-file-word" />
        </q-item-section>
        <q-item-section side>
          <q-btn text-color="deep-orange-10"
                 dense
                 @click="exportPdfWO"
                 icon="fas fa-file-pdf" />
        </q-item-section>
      </q-item>

    </div>
    <q-separator vertical
                 inset />
    <div class="column col q-pa-xs">
      <iframe :src="UrlShow"
              frameborder="0"
              @load="onLoad"
              class="fit" />
      <iframe :src="UrlExport"
              frameborder="0"
              width=0
              height=0 />
    </div>
  </q-page>

</template>

<script>
export default {
  name: 'Reports',
  data () {
    return {
      COCProj: '',
      CustomerOri: true,
      DeliveryNO: '',
      InvoiceNO: '',
      PurchaseNO: '',
      POtax: true,
      WorkOrderNO: '',
      UrlShow: 'about:blank',
      UrlExport: 'about:blank'
    }
  },
  methods: {
    showCOC: function (value) {
      if (this.validateCOC()) {
        this.UrlShow = this.UrlCOC

        this.$q.loading.show({
          message: '<h3>Generating COC ' + this.COCProj.toUpperCase() + '</h3>'
        })
      } else {
        this.UrlShow = 'about:blank'
      }
    },
    showDelivery: function (value) {
      if (this.validateDelivery()) {
        this.UrlShow = this.UrlDelivery

        this.$q.loading.show({
          message: '<h3>Generating Delivery ' + this.DeliveryNO.toUpperCase() + '</h3>'
        })
      } else {
        this.UrlShow = 'about:blank'
      }
    },
    showInvoice: function (value) {
      if (this.validateInvoice()) {
        this.UrlShow = this.UrlInvoice

        this.$q.loading.show({
          message: '<h3>Generating Invoice ' + this.InvoiceNO.toUpperCase() + '</h3>'
        })
      } else {
        this.UrlShow = 'about:blank'
      }
    },
    showPO: function (value) {
      if (this.validatePO()) {
        this.UrlShow = this.UrlPurchaseOrder

        this.$q.loading.show({
          message: '<h3>Generating Purchase ' + this.PurchaseNO.toUpperCase() + '</h3>'
        })
      } else {
        this.UrlShow = 'about:blank'
      }
    },
    showWO: function (value) {
      if (this.validateWO()) {
        this.UrlShow = this.UrlWorkOrder
        this.$q.loading.show({
          message: '<h3>Generating WO ' + this.WorkOrderNO.toUpperCase() + '</h3>'
        })
      } else {
        this.UrlShow = 'about:blank'
      }
    },
    exportWordCOC: function () {
      if (this.validateCOC()) {
        this.UrlExport = this.UrlCOC.replace('showPdf', 'exportWord')
      } else {
        this.UrlExport = 'about:blank'
      }
    },
    exportWordDelivery: function () {
      if (this.validateDelivery()) {
        this.UrlExport = this.UrlDelivery.replace('showPdf', 'exportWord')
      } else {
        this.UrlExport = 'about:blank'
      }
    },
    exportWordInvoice: function () {
      if (this.validateInvoice()) {
        this.UrlExport = this.UrlInvoice.replace('showPdf', 'exportWord')
      } else {
        this.UrlExport = 'about:blank'
      }
    },
    exportWordPO: function () {
      if (this.validatePO()) {
        this.UrlExport = this.UrlPurchaseOrder.replace('showPdf', 'exportWord')
      } else {
        this.UrlExport = 'about:blank'
      }
    },
    exportWordWO: function () {
      if (this.validateWO()) {
        this.UrlExport = this.UrlWorkOrder.replace('showPdf', 'exportWord')
      } else {
        this.UrlExport = 'about:blank'
      }
    },
    exportPdfCOC: function () {
      if (this.validateCOC()) {
        this.UrlExport = this.UrlCOC.replace('showPdf', 'exportPdf')
      } else {
        this.UrlExport = 'about:blank'
      }
    },
    exportPdfDelivery: function () {
      if (this.validateDelivery()) {
        this.UrlExport = this.UrlDelivery.replace('showPdf', 'exportPdf')
      } else {
        this.UrlExport = 'about:blank'
      }
    },
    exportPdfInvoice: function () {
      if (this.validateInvoice()) {
        this.UrlExport = this.UrlInvoice.replace('showPdf', 'exportPdf')
      } else {
        this.UrlExport = 'about:blank'
      }
    },
    exportPdfPO: function () {
      if (this.validatePO()) {
        this.UrlExport = this.UrlPurchaseOrder.replace('showPdf', 'exportPdf')
      } else {
        this.UrlExport = 'about:blank'
      }
    },
    exportPdfWO: function () {
      if (this.validateWO()) {
        this.UrlExport = this.UrlWorkOrder.replace('showPdf', 'exportPdf')
      } else {
        this.UrlExport = 'about:blank'
      }
    },
    onLoad: function () {
      this.$q.loading.hide()
    },
    validateCOC: function () {
      if (this.COCProj &&
        (this.COCProj.length === 11 || this.COCProj.length === 12) &&
        (this.COCProj.slice(1, 3).toUpperCase() === 'CC' || this.COCProj.slice(1, 5).toUpperCase() === 'DSRP')) {
        return true
      } else {
        return false
      }
    },
    validateDelivery: function () {
      if (this.DeliveryNO && this.DeliveryNO.length === 9 &&
        this.DeliveryNO.slice(1, 3).toUpperCase() === 'BL') {
        return true
      } else {
        return false
      }
    },
    validateInvoice: function () {
      if (this.InvoiceNO &&
        (this.InvoiceNO.length === 10 || this.InvoiceNO.length === 9) &&
        (this.InvoiceNO.slice(1, 3).toUpperCase() === 'FC' || this.InvoiceNO.slice(1, 3).toUpperCase() === 'PC')) {
        return true
      } else {
        return false
      }
    },
    validatePO: function () {
      if (this.PurchaseNO &&
        this.PurchaseNO.length === 10 &&
        this.PurchaseNO.slice(1, 3).toUpperCase() === 'CF') {
        return true
      } else {
        return false
      }
    },
    validateWO: function () {
      if (this.WorkOrderNO && this.WorkOrderNO.length === 11 &&
        this.WorkOrderNO.slice(1, 3).toUpperCase() === 'OF') {
        return true
      } else {
        return false
      }
    }
  },
  computed: {
    UrlCOC: function () {
      return '/Report/COC/showPdf?ProjectNO=' + this.COCProj.toUpperCase() + '&CustomerOri=' + this.CustomerOri
    },
    UrlDelivery: function () {
      return '/Report/Delivery/showPdf?DeliveryNO=' + this.DeliveryNO.toUpperCase()
    },
    UrlInvoice: function () {
      return 'Report/Invoice/showPdf?InvoiceNO=' + this.InvoiceNO.toUpperCase()
    },
    UrlPurchaseOrder: function () {
      return '/Report/PurchaseOrder/showPdf?PurchaseNO=' + this.PurchaseNO.toUpperCase() + '&TaxInclude=' + this.POtax
    },
    UrlWorkOrder: function () {
      return '/Report/WorkOrder/showPdf?WorkOrderNO=' + this.WorkOrderNO
    }
  }
}
</script>
