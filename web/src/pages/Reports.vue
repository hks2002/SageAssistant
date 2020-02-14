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
                 :to="urlCOCWord"
                 icon="fas fa-file-word" />
        </q-item-section>
        <q-item-section side>
          <q-btn text-color="deep-orange-10"
                 dense
                 :to="urlCOCPdf"
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
                 :to="urlDeliveryWord"
                 icon="fas fa-file-word" />
        </q-item-section>
        <q-item-section side>
          <q-btn text-color="deep-orange-10"
                 dense
                 :to="urlDeliveryPdf"
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
                 :to="urlInvoiceWord"
                 icon="fas fa-file-word" />
        </q-item-section>
        <q-item-section side>
          <q-btn text-color="deep-orange-10"
                 dense
                 :to="urlInvoicePdf"
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
                 :to="urlPOWord"
                 icon="fas fa-file-word" />
        </q-item-section>
        <q-item-section side>
          <q-btn text-color="deep-orange-10"
                 dense
                 :to="urlPOPdf"
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
                   v-model="WorkorderNO"
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
                 :to="urlWOWord"
                 icon="fas fa-file-word" />
        </q-item-section>
        <q-item-section side>
          <q-btn text-color="deep-orange-10"
                 dense
                 :to="urlWOPdf"
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
      WorkorderNO: '',
      UrlShow: 'about:blank'
    }
  },
  methods: {
    showCOC: function (value) {
      if (this.COCProj && (this.COCProj.length === 11 || this.COCProj.length === 12) && (this.COCProj.slice(1, 3).toUpperCase() === 'CC' || this.COCProj.slice(1, 5).toUpperCase() === 'DSRP')) {
        this.UrlShow = 'Report/COC/showPdf?ProjectNO=' + this.COCProj.toUpperCase() + '&CustomerOri=' + this.CustomerOri

        this.$q.loading.show({
          message: '<h3>Generating COC ' + this.COCProj.toUpperCase() + '</h3>'
        })
      } else {
        this.UrlShow = 'about:blank'
      }
    },
    showDelivery: function (value) {
      if (value && value.length === 9 && value.slice(1, 3).toUpperCase() === 'BL') {
        this.UrlShow = 'Report/Delivery/showPdf?DeliveryNO=' + value.toUpperCase()

        this.$q.loading.show({
          message: '<h3>Generating Delivery ' + value.toUpperCase() + '</h3>'
        })
      } else {
        this.UrlShow = 'about:blank'
      }
    },
    showInvoice: function (value) {
      if (value && (value.length === 10 || value.length === 9) && (value.slice(1, 3).toUpperCase() === 'FC' || value.slice(1, 3).toUpperCase() === 'PC')) {
        this.UrlShow = 'Report/Invoice/showPdf?InvoiceNO=' + value.toUpperCase()

        this.$q.loading.show({
          message: '<h3>Generating Invoice ' + value.toUpperCase() + '</h3>'
        })
      } else {
        this.UrlShow = 'about:blank'
      }
    },
    showPO: function (value) {
      if (this.PurchaseNO && this.PurchaseNO.length === 10 && this.PurchaseNO.slice(1, 3).toUpperCase() === 'CF') {
        this.UrlShow = 'Report/PurchaseOrder/showPdf?PurchaseNO=' + this.PurchaseNO.toUpperCase() + '&TaxInclude=' + this.POtax

        this.$q.loading.show({
          message: '<h3>Generating Purchase ' + value.toUpperCase() + '</h3>'
        })
      } else {
        this.UrlShow = 'about:blank'
      }
    },
    showWO: function (value) {
      if (value && value.length === 11 && value.slice(1, 3).toUpperCase() === 'OF') {
        this.UrlShow = 'Report/WorkorderNO/showPdf?WorkorderNO=' + value

        this.$q.loading.show({
          message: '<h3>Generating WO ' + value.toUpperCase() + '</h3>'
        })
      } else {
        this.UrlShow = 'about:blank'
      }
    },
    onLoad: function () {
      this.$q.loading.hide()
    }
  },
  computed: {
    urlCOCWord: function () {
      if (this.COCProj) {
        return 'Report/COC/exportWord?DeliveryNO=' + this.COCProj.toUpperCase() + '&CustomerOri=' + this.CustomerOri
      } else {
        return ''
      }
    },
    urlDeliveryWord: function () {
      if (this.DeliveryNO) {
        return 'Report/Delivery/exportWord?DeliveryNO=' + this.DeliveryNO.toUpperCase()
      } else {
        return ''
      }
    },
    urlInvoiceWord: function () {
      if (this.InvoiceNO) {
        return 'Report/Invoice/exportWord?InvoiceNO=' + this.InvoiceNO.toUpperCase()
      } else {
        return ''
      }
    },
    urlPOWord: function () {
      if (this.PurchaseNO) {
        return 'Report/PurchaseOrder/exportWord?PurchaseNO=' + this.PurchaseNO.toUpperCase() + '&TaxInclude=' + this.POtax
      } else {
        return ''
      }
    },
    urlWOWord: function () {
      if (this.WorkorderNO) {
        return 'Report/WorkOrder/exportWord?WorkorderNO=' + this.WorkorderNO.toUpperCase()
      } else {
        return ''
      }
    },
    urlCOCPdf: function () {
      if (this.COCProj) {
        return 'Report/COC/exportWord?DeliveryNO=' + this.COCProj.toUpperCase() + '&COCori=' + this.COCori
      } else {
        return ''
      }
    },
    urlDeliveryPdf: function () {
      if (this.DeliveryNO) {
        return 'Report/Delivery/exportPdf?DeliveryNO=' + this.DeliveryNO.toUpperCase()
      } else {
        return ''
      }
    },
    urlInvoicePdf: function () {
      if (this.InvoiceNO) {
        return 'Report/Invoice/exportPdf?InvoiceNO=' + this.InvoiceNO.toUpperCase()
      } else {
        return ''
      }
    },
    urlPOPdf: function () {
      if (this.PurchaseNO) {
        return 'Report/PurchaseOrder/exportPdf?PurchaseNO=' + this.PurchaseNO.toUpperCase() + '&TaxInclude=' + this.POtax
      } else {
        return ''
      }
    },
    urlWOPdf: function () {
      if (this.WorkorderNO) {
        return 'Report/WorkOrder/exportPdf?WorkorderNO=' + this.WorkorderNO.toUpperCase()
      } else {
        return ''
      }
    }
  }
}
</script>
