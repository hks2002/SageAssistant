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
                   hint="e.g.: ZCC190001-1 or ZCC190001-11 or ZREP2019001 or ZDSRP190001"
                   :hide-hint="true"
                   input-class="text-uppercase"
                   @keydown="checkInputKey($event,'showCOC')"
                   class="q-pa-xs">
          </q-input>
        </q-item-section>
        <q-item-section side>
          <q-btn ref="showCOC" text-color="orange-10"
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
                   @keydown="checkInputKey($event,'showDelivery')"
                   class="q-pa-xs">
          </q-input>
        </q-item-section>
        <q-item-section side>
          <q-btn ref="showDelivery" text-color="orange-10"
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
                   @keydown="checkInputKey($event,'showInvoice')"
                   class="q-pa-xs">
          </q-input>
        </q-item-section>
        <q-item-section side>
          <q-btn ref="showInvoice" text-color="orange-10"
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
                   @keydown="checkInputKey($event,'showInvoice2')"
                   class="q-pa-xs">
          </q-input>
        </q-item-section>
        <q-item-section side>
          <q-btn ref="showInvoice2" text-color="orange-10"
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
                   @keydown="checkInputKey($event,'showPO')"
                   class="q-pa-xs">
            <q-checkbox v-model="POtax"
                        label="Tax" />
          </q-input>
        </q-item-section>
        <q-item-section side>
          <q-btn ref="showPO" text-color="orange-10"
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
                   @keydown="checkInputKey($event,'showReceipt')"
                   class="q-pa-xs">
          </q-input>
        </q-item-section>
        <q-item-section side>
          <q-btn ref="showReceipt" text-color="orange-10"
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
            R2
          </q-avatar>
        </q-item-section>
        <q-item-section>
          <q-input label="PurchaseSite-VendorCode-Duration"
                   v-model="PurchaseSiteVendorCodeDuration"
                   outlined
                   clearable
                   hint="e.g.:ZHU-20715-20201001-20201031"
                   :hide-hint="true"
                   input-class="text-uppercase"
                   @keydown="checkInputKey($event,'showReceipt2')"
                   class="q-pa-xs">
          </q-input>
        </q-item-section>
        <q-item-section side>
          <q-btn ref="showReceipt2" text-color="orange-10"
                 dense
                 @click="showReceipt2"
                 icon="fas fa-file-pdf" />
        </q-item-section>
        <q-item-section side>
          <q-btn text-color="indigo-7"
                 dense
                 @click="exportWordReceipt2"
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
                   @keydown="checkInputKey($event,'showWO')"
                   class="q-pa-xs">
          </q-input>
        </q-item-section>
        <q-item-section side>
          <q-btn ref="showWO" text-color="orange-10"
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
      <q-item>
        <q-item-section avatar>
          <q-avatar color="primary"
                    text-color="white">
            S
          </q-avatar>
        </q-item-section>
        <q-item-section>
          <q-input label="Site&CustomerCode"
                   v-model="SiteAndBPCode"
                   outlined
                   clearable
                   hint="e.g.:ZHU00870 OR ZHU"
                   :hide-hint="true"
                   input-class="text-uppercase"
                   @keydown="checkInputKey($event,'showSOA')"
                   class="q-pa-xs">
          </q-input>
        </q-item-section>
        <q-item-section side>
          <q-btn ref="showSOA"
                 text-color="orange-10"
                 dense
                 @click="showSOA"
                 icon="fas fa-file-pdf" />
        </q-item-section>
        <q-item-section side>
          <q-btn text-color="indigo-7"
                 dense
                 @click="exportWordSOA"
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
      PurchaseSiteVendorCodeDuration: '',
      ProjectOrWorkOrderNO: '',
      SiteAndBPCode: '',
      UrlShow: 'about:blank',
      UrlExport: 'about:blank',
      showAlert: false
    }
  },
  methods: {
    onLoad: function () {
      this.$q.loading.hide()
    },
    checkInputKey: function (event, method) {
      if (event.keyCode === 13) {
        this.$refs[method].click()
        return false
      }
    },
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

        // this.$q.loading.show({
        //  message: '<h3>Generating Invoice for Order ' + this.OrderNO.toUpperCase() + '</h3>'
        // })
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
    showReceipt2: function (value) {
      if (this.validateReceipt2()) {
        if (this.UrlShow === this.UrlReceipt2) {
          return
        }
        this.UrlShow = this.UrlReceipt2

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
    showSOA: function (value) {
      if (this.validateSOA()) {
        if (this.UrlShow === this.UrlSOA) {
          return
        }
        this.UrlShow = this.UrlSOA
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
    exportWordReceipt2: function () {
      if (this.validateReceipt2()) {
        this.UrlExport = this.UrlReceipt2.replace('showPdf', 'exportWord')
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
    exportWordSOA: function () {
      if (this.validateSOA()) {
        this.UrlExport = this.UrlSOA.replace('showPdf', 'exportWord')
      } else {
        this.UrlExport = 'about:blank'
      }
    },
    validateCOC: function () {
      const reg1 = /^[A-Z]CC[\d]{6}-[\d]{1,3}$/
      const reg2 = /^[A-Z]DSR[P|C][\d]{6}$/
      const reg3 = /^[A-Z]REP[\d]{7}-[\d]{1,3}$/
      const reg4 = /^[A-Z]RCL[\d]{7}-[\d]{1,3}$/
      if (reg1.test(this.COCProj.toUpperCase())) {
        return true
      } else if (reg2.test(this.COCProj.toUpperCase())) {
        return true
      } else if (reg3.test(this.COCProj.toUpperCase())) {
        return true
      } else if (reg4.test(this.COCProj.toUpperCase())) {
        return true
      } else {
        this.showAlert = true
        return false
      }
    },
    validateDelivery: function () {
      const reg1 = /^[A-Z]BL[\d]{6}$/
      if (reg1.test(this.DeliveryNO.toUpperCase())) {
        return true
      } else {
        this.showAlert = true
        return false
      }
    },
    validateInvoice: function () {
      const reg1 = /^[A-Z]FC[\d]{7}$/
      const reg2 = /^[A-Z]PC[\d]{6}$/
      if (reg1.test(this.InvoiceNO.toUpperCase())) {
        return true
      } else if (reg2.test(this.InvoiceNO.toUpperCase())) {
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
      const reg1 = /^[A-Z]CF[\d]{7}$/
      if (reg1.test(this.ReceiptNO.toUpperCase())) {
        return true
      } else {
        this.showAlert = true
        return false
      }
    },
    validateReceipt: function () {
      const reg1 = /^[A-Z]RA[\d]{7}$/
      if (reg1.test(this.ReceiptNO.toUpperCase())) {
        return true
      } else {
        this.showAlert = true
        return false
      }
    },
    validateReceipt2: function () {
      const reg1 = /^[A-Z]{3}-[\d]{5}-[\d]{8}-[\d]{8}$/
      if (reg1.test(this.PurchaseSiteVendorCodeDuration.toUpperCase())) {
        return true
      } else {
        this.showAlert = true
        return false
      }
    },
    validateWO: function () {
      const reg1 = /^[A-Z]OF[\d]{8}$/
      const reg2 = /^[A-Z]CC[\d]{6}-[\d]{1,3}$/
      if (reg1.test(this.ProjectOrWorkOrderNO.toUpperCase())) {
        return true
      } else if (reg2.test(this.ProjectOrWorkOrderNO.toUpperCase())) {
        return true
      } else {
        this.showAlert = true
        return false
      }
    },
    validateSOA: function () {
      const reg1 = /^[A-Z]{3}$/
      const reg2 = /^[A-Z]{3}[\d]{5}$/
      if (reg1.test(this.SiteAndBPCode.toUpperCase())) {
        return true
      } else if (reg2.test(this.SiteAndBPCode.toUpperCase())) {
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
    UrlReceipt2: function () {
      return '/Report/Receipt2/showPdf?PurchaseSite=' + this.PurchaseSiteVendorCodeDuration.slice(0, 3).toUpperCase() + '&VendorCode=' + this.PurchaseSiteVendorCodeDuration.slice(4, 9) + '&StartDay=' + this.PurchaseSiteVendorCodeDuration.slice(10, 18) + '&EndDay=' + this.PurchaseSiteVendorCodeDuration.slice(19, 27)
    },
    UrlWorkOrder: function () {
      return '/Report/WorkOrder/showPdf?ProjectOrWorkOrderNO=' + this.ProjectOrWorkOrderNO.toUpperCase()
    },
    UrlSOA: function () {
      return '/Report/SOA/showPdf?Site=' + this.SiteAndBPCode.slice(0, 3).toUpperCase() + '&BPCode=' + this.SiteAndBPCode.slice(3, 8).toUpperCase()
    }
  }
}
</script>
