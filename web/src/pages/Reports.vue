<template>
  <q-page class="row">
    <div class="column col-md-4 col-lg-4 col-xl-3 q-pa-md">

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
                   input-class="text-uppercase"
                   class="q-pa-xs">
          </q-input>
        </q-item-section>
        <q-item-section side>
          <q-btn text-color="orange-10"
                 dense
                 @click="showCOC"
                 icon="fas fa-file-pdf" />
        </q-item-section>
        <q-item-section side>
          <q-btn text-color="indigo-7"
                 dense
                 @click="exportWordCOC"
                 icon="fas fa-file-word" />
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
                   hint="e.g.: ZBL190001"
                   :hide-hint="true"
                   input-class="text-uppercase"
                   class="q-pa-xs">
          </q-input>
        </q-item-section>
        <q-item-section side>
          <q-btn text-color="orange-10"
                 dense
                 @click="showDelivery"
                 icon="fas fa-file-pdf" />
        </q-item-section>
        <q-item-section side>
          <q-btn text-color="indigo-7"
                 dense
                 @click="exportWordDelivery"
                 icon="fas fa-file-word" />
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
                   input-class="text-uppercase"
                   class="q-pa-xs">
          </q-input>
        </q-item-section>
        <q-item-section side>
          <q-btn text-color="orange-10"
                 dense
                 @click="showInvoice"
                 icon="fas fa-file-pdf" />
        </q-item-section>
        <q-item-section side>
          <q-btn text-color="indigo-7"
                 dense
                 @click="exportWordInvoice"
                 icon="fas fa-file-word" />
        </q-item-section>
      </q-item>
            <q-item>
        <q-item-section avatar>
          <q-avatar color="primary"
                    text-color="white">
            I2
          </q-avatar>
        </q-item-section>
        <q-item-section>
          <q-input label="Order NO"
                   v-model="OrderNO"
                   outlined
                   clearable
                   hint="e.g.: ZCC180020 or customer order"
                   :hide-hint="true"
                   input-class="text-uppercase"
                   class="q-pa-xs">
          </q-input>
        </q-item-section>
        <q-item-section side>
          <q-btn text-color="orange-10"
                 dense
                 @click="showInvoice2"
                 icon="fas fa-file-pdf" />
        </q-item-section>
        <q-item-section side>
          <q-btn text-color="indigo-7"
                 dense
                 @click="exportWordInvoice2"
                 icon="fas fa-file-word" />
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
                   input-class="text-uppercase"
                   class="q-pa-xs">
            <q-checkbox v-model="POtax"
                        label="Tax" />
          </q-input>
        </q-item-section>
        <q-item-section side>
          <q-btn text-color="orange-10"
                 dense
                 @click="showPO"
                 icon="fas fa-file-pdf" />
        </q-item-section>
        <q-item-section side>
          <q-btn text-color="indigo-7"
                 dense
                 @click="exportWordPO"
                 icon="fas fa-file-word" />
        </q-item-section>
      </q-item>
      <q-item>
        <q-item-section avatar>
          <q-avatar color="primary"
                    text-color="white">
            R
          </q-avatar>
        </q-item-section>
        <q-item-section>
          <q-input label="Receipt NO"
                   v-model="ReceiptNO"
                   outlined
                   clearable
                   hint="e.g.: ZRA1900607"
                   :hide-hint="true"
                   input-class="text-uppercase"
                   class="q-pa-xs">
          </q-input>
        </q-item-section>
        <q-item-section side>
          <q-btn text-color="orange-10"
                 dense
                 @click="showReceipt"
                 icon="fas fa-file-pdf" />
        </q-item-section>
        <q-item-section side>
          <q-btn text-color="indigo-7"
                 dense
                 @click="exportWordReceipt"
                 icon="fas fa-file-word" />
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
          <q-input label="Project/Work Order NO"
                   v-model="ProjectOrWorkOrderNO"
                   outlined
                   clearable
                   hint="e.g.:HCC200033-1 OR ZOF1901001"
                   :hide-hint="true"
                   input-class="text-uppercase"
                   class="q-pa-xs">
          </q-input>
        </q-item-section>
        <q-item-section side>
          <q-btn text-color="orange-10"
                 dense
                 @click="showWO"
                 icon="fas fa-file-pdf" />
        </q-item-section>
        <q-item-section side>
          <q-btn text-color="indigo-7"
                 dense
                 @click="exportWordWO"
                 icon="fas fa-file-word" />
        </q-item-section>
      </q-item>

    </div>
    <q-dialog v-model="showAlert">
      <q-card>
        <q-card-section>
          <div class="text-h6">Alert</div>
        </q-card-section>
        <q-card-section class="q-pt-none">
          Wrong Input !
        </q-card-section>
        <q-card-actions align="right">
          <q-btn flat
                 label="OK"
                 color="primary"
                 v-close-popup />
        </q-card-actions>
      </q-card>
    </q-dialog>
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
      DeliveryNO: '',
      InvoiceNO: '',
      OrderNO: '',
      PurchaseNO: '',
      POtax: true,
      ReceiptNO: '',
      ProjectOrWorkOrderNO: '',
      UrlShow: 'about:blank',
      UrlExport: 'about:blank',
      showAlert: false
    }
  },
  methods: {
    showCOC: function (value) {
      if (this.validateCOC()) {
        if (this.UrlShow === this.UrlCOC) {
          return
        }
        this.UrlShow = this.UrlCOC
        // this.$q.loading.show({
        // message: '<h3>Generating COC ' + this.COCProj.toUpperCase() + '</h3>'
        // })
      } else {
        this.UrlShow = 'about:blank'
      }
    },
    showDelivery: function (value) {
      if (this.validateDelivery()) {
        if (this.UrlShow === this.UrlDelivery) {
          return
        }
        this.UrlShow = this.UrlDelivery

        // this.$q.loading.show({
        //  message: '<h3>Generating Delivery ' + this.DeliveryNO.toUpperCase() + '</h3>'
        // })
      } else {
        this.UrlShow = 'about:blank'
      }
    },
    showInvoice: function (value) {
      if (this.validateInvoice()) {
        if (this.UrlShow === this.UrlInvoice) {
          return
        }
        this.UrlShow = this.UrlInvoice

        // this.$q.loading.show({
        //  message: '<h3>Generating Invoice ' + this.InvoiceNO.toUpperCase() + '</h3>'
        // })
      } else {
        this.UrlShow = 'about:blank'
      }
    },
    showInvoice2: function (value) {
      if (this.validateInvoice2()) {
        if (this.UrlShow === this.UrlInvoice2) {
          return
        }
        this.UrlShow = this.UrlInvoice2

        this.$q.loading.show({
          message: '<h3>Generating Invoice for Order ' + this.OrderNO.toUpperCase() + '</h3>'
        })
      } else {
        this.UrlShow = 'about:blank'
      }
    },
    showPO: function (value) {
      if (this.validatePO()) {
        if (this.UrlShow === this.UrlPurchaseOrder) {
          return
        }
        this.UrlShow = this.UrlPurchaseOrder

        // this.$q.loading.show({
        //  message: '<h3>Generating Purchase ' + this.PurchaseNO.toUpperCase() + '</h3>'
        // })
      } else {
        this.UrlShow = 'about:blank'
      }
    },
    showReceipt: function (value) {
      if (this.validateReceipt()) {
        if (this.UrlShow === this.UrlReceipt) {
          return
        }
        this.UrlShow = this.UrlReceipt

        // this.$q.loading.show({
        //  message: '<h3>Generating Receipt ' + this.ReceiptNO.toUpperCase() + '</h3>'
        // })
      } else {
        this.UrlShow = 'about:blank'
      }
    },
    showWO: function (value) {
      if (this.validateWO()) {
        if (this.UrlShow === this.UrlWorkOrder) {
          return
        }
        this.UrlShow = this.UrlWorkOrder
        // this.$q.loading.show({
        // message: '<h3>Generating WO ' + this.WorkOrderNO.toUpperCase() + '</h3>'
        // })
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
    exportWordInvoice2: function () {
      if (this.validateInvoice2()) {
        this.UrlExport = this.UrlInvoice2.replace('showPdf', 'exportWord')
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
    exportWordReceipt: function () {
      if (this.validateReceipt()) {
        this.UrlExport = this.UrlReceipt.replace('showPdf', 'exportWord')
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
    onLoad: function () {
      this.$q.loading.hide()
    },
    validateCOC: function () {
      if (this.COCProj &&
        (this.COCProj.length === 11 || this.COCProj.length === 12) &&
        (this.COCProj.slice(1, 3).toUpperCase() === 'CC' || this.COCProj.slice(1, 5).toUpperCase() === 'DSRP')) {
        return true
      } else {
        this.showAlert = true
        return false
      }
    },
    validateDelivery: function () {
      if (this.DeliveryNO && this.DeliveryNO.length === 9 &&
        this.DeliveryNO.slice(1, 3).toUpperCase() === 'BL') {
        return true
      } else {
        this.showAlert = true
        return false
      }
    },
    validateInvoice: function () {
      if (this.InvoiceNO &&
        (this.InvoiceNO.length === 10 || this.InvoiceNO.length === 9) &&
        (this.InvoiceNO.slice(1, 3).toUpperCase() === 'FC' || this.InvoiceNO.slice(1, 3).toUpperCase() === 'PC')) {
        return true
      } else {
        this.showAlert = true
        return false
      }
    },
    validateInvoice2: function () {
      if (this.OrderNO) {
        return true
      } else {
        this.showAlert = true
        return false
      }
    },
    validatePO: function () {
      if (this.PurchaseNO &&
        this.PurchaseNO.length === 10 &&
        this.PurchaseNO.slice(1, 3).toUpperCase() === 'CF') {
        return true
      } else {
        this.showAlert = true
        return false
      }
    },
    validateReceipt: function () {
      if (this.ReceiptNO &&
        this.ReceiptNO.length === 10 &&
        this.ReceiptNO.slice(1, 3).toUpperCase() === 'RA') {
        return true
      } else {
        this.showAlert = true
        return false
      }
    },
    validateWO: function () {
      if (this.ProjectOrWorkOrderNO && this.ProjectOrWorkOrderNO.length === 11 &&
        this.ProjectOrWorkOrderNO.slice(1, 3).toUpperCase() === 'OF') {
        return true
      } else if (this.ProjectOrWorkOrderNO && this.ProjectOrWorkOrderNO.length >= 11 &&
        this.ProjectOrWorkOrderNO.length <= 13 &&
        this.ProjectOrWorkOrderNO.slice(1, 3).toUpperCase() === 'CC' &&
        this.ProjectOrWorkOrderNO.slice(9, 10).toUpperCase() === '-') {
        return true
      } else {
        this.showAlert = true
        return false
      }
    }
  },
  computed: {
    UrlCOC: function () {
      return '/Report/COC/showPdf?ProjectNO=' + this.COCProj.toUpperCase()
    },
    UrlDelivery: function () {
      return '/Report/Delivery/showPdf?DeliveryNO=' + this.DeliveryNO.toUpperCase()
    },
    UrlInvoice: function () {
      return '/Report/Invoice/showPdf?InvoiceNO=' + this.InvoiceNO.toUpperCase()
    },
    UrlInvoice2: function () {
      return '/Report/Invoice2/showPdf?OrderNO=' + this.OrderNO.toUpperCase()
    },
    UrlPurchaseOrder: function () {
      return '/Report/PurchaseOrder/showPdf?PurchaseNO=' + this.PurchaseNO.toUpperCase() + '&TaxInclude=' + this.POtax
    },
    UrlReceipt: function () {
      return '/Report/Receipt/showPdf?ReceiptNO=' + this.ReceiptNO.toUpperCase()
    },
    UrlWorkOrder: function () {
      return '/Report/WorkOrder/showPdf?ProjectOrWorkOrderNO=' + this.ProjectOrWorkOrderNO.toUpperCase()
    }
  }
}
</script>
