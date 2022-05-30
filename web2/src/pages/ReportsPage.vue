<template>
  <q-page class="row">
    <div class="column col-md-4 col-lg-4 col-xl-3 q-pa-xs">
      <q-item>
        <q-item-section avatar>
          <q-avatar color="primary" text-color="white"> A </q-avatar>
          <q-tooltip>{{ $t('SalesOrder Acknowledge') }}</q-tooltip>
        </q-item-section>
        <q-item-section>
          <q-input
            v-model="OrderNO4SA"
            :label="$t('OrderNO')"
            outlined
            clearable
            hint="ZCC200002 ZREP2019001"
            :hide-hint="true"
            input-class="text-uppercase"
            class="q-pa-xs"
            @keydown="clickTarget($event, 'showSA')"
          >
          </q-input>
        </q-item-section>
        <q-item-section side>
          <q-btn dense flat disable icon="fas fa-file-pdf" />
        </q-item-section>
        <q-item-section side>
          <q-btn dense flat disable icon="fas fa-file-word" />
        </q-item-section>
        <q-item-section side>
          <q-btn
            id="showSA"
            text-color="light-green-7"
            dense
            icon="fas fa-file-pdf"
            @click="showPdf('SA', 'sage')"
          />
        </q-item-section>
      </q-item>

      <q-item>
        <q-item-section avatar>
          <q-avatar color="primary" text-color="white"> C </q-avatar>
          <q-tooltip>{{ $t('COC') }}</q-tooltip>
        </q-item-section>
        <q-item-section>
          <q-input
            v-model="COCProj"
            :label="$t('ProjectNO & Order Line')"
            outlined
            clearable
            hint="ZCC200002-1 ZCC190001-11 ZREP2019001-1 ZDSRP190001"
            :hide-hint="true"
            input-class="text-uppercase"
            class="q-pa-xs"
            @keydown="clickTarget($event, 'showCOC')"
          >
          </q-input>
        </q-item-section>
        <q-item-section side>
          <q-btn
            id="showCOC"
            text-color="orange-10"
            dense
            icon="fas fa-file-pdf"
            @click="showPdf('COC')"
          />
        </q-item-section>
        <q-item-section side>
          <q-btn
            text-color="indigo-7"
            dense
            icon="fas fa-file-word"
            @click="exportWord('COC')"
          />
        </q-item-section>
        <q-item-section side>
          <q-btn dense flat disable icon="fas fa-file-pdf" />
        </q-item-section>
      </q-item>

      <q-item>
        <q-item-section avatar>
          <q-avatar color="primary" text-color="white"> D </q-avatar>
          <q-tooltip>{{ $t('Delivery') }}</q-tooltip>
        </q-item-section>
        <q-item-section>
          <q-input
            :label="$t('Delivery NO')"
            v-model="DeliveryNO"
            outlined
            clearable
            hint="ZBL190001"
            :hide-hint="true"
            input-class="text-uppercase"
            @keydown="clickTarget($event, 'showDelivery')"
            class="q-pa-xs"
          >
          </q-input>
        </q-item-section>
        <q-item-section side>
          <q-btn
            id="showDelivery"
            text-color="orange-10"
            dense
            icon="fas fa-file-pdf"
            @click="showPdf('Delivery')"
          />
        </q-item-section>
        <q-item-section side>
          <q-btn
            text-color="indigo-7"
            dense
            icon="fas fa-file-word"
            @click="exportWord('Delivery')"
          />
        </q-item-section>
        <q-item-section side>
          <q-btn
            id="showDeliverySage"
            text-color="light-green-7"
            dense
            icon="fas fa-file-pdf"
            @click="showPdf('Delivery', 'sage')"
          />
        </q-item-section>
      </q-item>

      <q-item>
        <q-item-section avatar>
          <q-avatar color="primary" text-color="white"> I </q-avatar>
          <q-tooltip>{{ $t('Invoice') }}</q-tooltip>
        </q-item-section>
        <q-item-section>
          <q-input
            :label="$t('Invoice NO')"
            v-model="InvoiceNO"
            outlined
            clearable
            hint="ZFC1901001 ZPC190001"
            :hide-hint="true"
            input-class="text-uppercase"
            @keydown="clickTarget($event, 'showInvoice')"
            class="q-pa-xs"
          >
          </q-input>
        </q-item-section>
        <q-item-section side>
          <q-btn
            id="showInvoice"
            text-color="orange-10"
            dense
            icon="fas fa-file-pdf"
            @click="showPdf('Invoice')"
          />
        </q-item-section>
        <q-item-section side>
          <q-btn
            text-color="indigo-7"
            dense
            icon="fas fa-file-word"
            @click="exportWord('Invoice')"
          />
        </q-item-section>
        <q-item-section side>
          <q-btn
            id="showInvoiceSage"
            text-color="light-green-7"
            dense
            icon="fas fa-file-pdf"
            @click="showPdf('Invoice', 'sage')"
          />
        </q-item-section>
      </q-item>

      <q-item>
        <q-item-section avatar>
          <q-avatar color="primary" text-color="white"> I2 </q-avatar>

          <q-tooltip>{{ $t('Invoice by Sales Order') }}</q-tooltip>
        </q-item-section>
        <q-item-section>
          <q-input
            :label="$t('Order NO')"
            v-model="OrderNO4Invoice"
            outlined
            clearable
            hint="ZCC180020 or customer order"
            :hide-hint="true"
            input-class="text-uppercase"
            @keydown="clickTarget($event, 'showInvoice2')"
            class="q-pa-xs"
          >
          </q-input>
        </q-item-section>
        <q-item-section side>
          <q-btn
            id="showInvoice2"
            text-color="orange-10"
            dense
            icon="fas fa-file-pdf"
            @click="showPdf('Invoice2')"
          />
        </q-item-section>
        <q-item-section side>
          <q-btn
            text-color="indigo-7"
            dense
            icon="fas fa-file-word"
            @click="exportWord('Invoice2')"
          />
        </q-item-section>
        <q-item-section side>
          <q-btn dense flat disable icon="fas fa-file-pdf" />
        </q-item-section>
      </q-item>

      <q-item>
        <q-item-section avatar>
          <q-avatar color="primary" text-color="white"> P </q-avatar>
          <q-tooltip>{{ $t('Purchase Order') }}</q-tooltip>
        </q-item-section>
        <q-item-section>
          <q-input
            :label="$t('Purchase NO')"
            v-model="PurchaseNO"
            outlined
            clearable
            hint="HCF2100015"
            :hide-hint="true"
            input-class="text-uppercase"
            @keydown="clickTarget($event, 'showPO')"
            class="q-pa-xs"
          >
            <q-checkbox v-model="POtax" label="Tax" />
          </q-input>
        </q-item-section>
        <q-item-section side>
          <q-btn
            id="showPO"
            text-color="orange-10"
            dense
            icon="fas fa-file-pdf"
            @click="showPdf('PurchaseOrder')"
          />
        </q-item-section>
        <q-item-section side>
          <q-btn
            text-color="indigo-7"
            dense
            icon="fas fa-file-word"
            @click="exportWord('Receipt')"
          />
        </q-item-section>
        <q-item-section side>
          <q-btn dense flat disable icon="fas fa-file-pdf" />
        </q-item-section>
      </q-item>

      <q-item>
        <q-item-section avatar>
          <q-avatar color="primary" text-color="white"> R </q-avatar>
          <q-tooltip>{{ $t('Receiptation') }}</q-tooltip>
        </q-item-section>
        <q-item-section>
          <q-input
            :label="$t('Receipt NO')"
            v-model="ReceiptNO"
            outlined
            clearable
            hint="ZRA1900607"
            :hide-hint="true"
            input-class="text-uppercase"
            @keydown="clickTarget($event, 'showReceipt')"
            class="q-pa-xs"
          >
          </q-input>
        </q-item-section>
        <q-item-section side>
          <q-btn
            id="showReceipt"
            text-color="orange-10"
            dense
            icon="fas fa-file-pdf"
            @click="showPdf('Receipt')"
          />
        </q-item-section>
        <q-item-section side>
          <q-btn
            text-color="indigo-7"
            dense
            icon="fas fa-file-word"
            @click="exportWord('Receipt')"
          />
        </q-item-section>
        <q-item-section side>
          <q-btn dense flat disable icon="fas fa-file-pdf" />
        </q-item-section>
      </q-item>

      <q-item>
        <q-item-section avatar>
          <q-avatar color="primary" text-color="white"> R2 </q-avatar>
          <q-tooltip>{{
            $t('Receiptation By Site & Vendor & Duration')
          }}</q-tooltip>
        </q-item-section>
        <q-item-section>
          <q-input
            :label="$t('PurchaseSite-VendorCode-Duration')"
            v-model="PurchaseSiteVendorCodeDuration"
            outlined
            clearable
            hint="ZHU-20715-20201001-20201031"
            :hide-hint="true"
            input-class="text-uppercase"
            @keydown="clickTarget($event, 'showReceipt2')"
            class="q-pa-xs"
          >
          </q-input>
        </q-item-section>
        <q-item-section side>
          <q-btn
            id="showReceipt2"
            text-color="orange-10"
            dense
            icon="fas fa-file-pdf"
            @click="showPdf('Receipt2')"
          />
        </q-item-section>
        <q-item-section side>
          <q-btn
            text-color="indigo-7"
            dense
            icon="fas fa-file-word"
            @click="exportWord('Receipt2')"
          />
        </q-item-section>
        <q-item-section side>
          <q-btn dense flat disable icon="fas fa-file-pdf" />
        </q-item-section>
      </q-item>

      <q-item>
        <q-item-section avatar>
          <q-avatar color="primary" text-color="white"> W </q-avatar>
          <q-tooltip>{{ $t('Work Order') }}</q-tooltip>
        </q-item-section>
        <q-item-section>
          <q-input
            :label="$t('Project/Work Order NO')"
            v-model="ProjectOrWorkOrderNO"
            outlined
            clearable
            hint="HCC200033-1 OR ZOF1901001"
            :hide-hint="true"
            input-class="text-uppercase"
            @keydown="clickTarget($event, 'showWO')"
            class="q-pa-xs"
          >
          </q-input>
        </q-item-section>
        <q-item-section side>
          <q-btn
            id="showWO"
            text-color="orange-10"
            dense
            icon="fas fa-file-pdf"
            @click="showPdf('WorkOrder')"
          />
        </q-item-section>
        <q-item-section side>
          <q-btn
            text-color="indigo-7"
            dense
            icon="fas fa-file-word"
            @click="exportWord('WorkOrder')"
          />
        </q-item-section>
        <q-item-section side>
          <q-btn dense flat disable icon="fas fa-file-pdf" />
        </q-item-section>
      </q-item>

      <q-item>
        <q-item-section avatar>
          <q-avatar color="primary" text-color="white"> S </q-avatar>
          <q-tooltip>{{ $t('Statement Of Account') }}</q-tooltip>
        </q-item-section>
        <q-item-section>
          <q-input
            :label="$t('Site&CustomerCode')"
            v-model="SiteAndBPCode"
            outlined
            clearable
            hint="ZHU00870 OR ZHU"
            :hide-hint="true"
            input-class="text-uppercase"
            @keydown="clickTarget($event, 'showSOA')"
            class="q-pa-xs"
          >
          </q-input>
        </q-item-section>
        <q-item-section side>
          <q-btn
            id="showSOA"
            text-color="orange-10"
            dense
            icon="fas fa-file-pdf"
            @click="showPdf('SOA')"
          />
        </q-item-section>
        <q-item-section side>
          <q-btn
            text-color="indigo-7"
            dense
            icon="fas fa-file-word"
            @click="exportWord('SOA')"
          />
        </q-item-section>
        <q-item-section side>
          <q-btn dense flat disable icon="fas fa-file-pdf" />
        </q-item-section>
      </q-item>
    </div>
    <q-separator vertical inset />
    <div class="column col q-pa-xs">
      <iframe
        id="pdfwin"
        frameborder="0"
        class="fit"
        :src="UrlShow"
        @load="onLoad"
      />
      <iframe frameborder="0" width="0" height="0" :src="UrlExport" />
    </div>
  </q-page>
</template>

<script setup>
import { getFncForRpt, validateInput } from '@/assets/reportUtils'
import {
  doActionsForSagePrint,
  getSagePrintUUID,
  getSageReportUrl,
  getSageSessionUrl
} from '@/assets/sageSvcs'
import { QSpinnerGears, SessionStorage, useQuasar } from 'quasar'
import { ref } from 'vue'

const $q = useQuasar()

const UrlShow = ref('/WaitInput')
const UrlExport = ref('about:blank')

const COCProj = ref('')
const DeliveryNO = ref('')
const InvoiceNO = ref('')
const OrderNO4SA = ref('')
const OrderNO4Invoice = ref('')
const PurchaseNO = ref('')
const POtax = ref(true)
const ReceiptNO = ref('')
const PurchaseSiteVendorCodeDuration = ref('')
const ProjectOrWorkOrderNO = ref('')
const SiteAndBPCode = ref('')

// clean last session status
SessionStorage.set('lastSessionSuccessed', false)

const clickTarget = (evt, clickTarget) => {
  try {
    if (evt.key === 'Enter') {
      const btn = document.querySelector('#' + clickTarget)
      btn.click()
      return false
    }
  } catch (error) {
    console.log(error)
  }
}

const showPdf = async (rpt, fromWhere) => {
  let isValid = false
  let val = ''
  switch (rpt) {
    case 'SA':
      isValid = validateInput('SA', OrderNO4SA.value)
      val = OrderNO4SA.value.toUpperCase()
      break
    case 'COC':
      isValid = validateInput('COC', COCProj.value)
      break
    case 'Delivery':
      isValid = validateInput('Delivery', DeliveryNO.value)
      val = DeliveryNO.value.toUpperCase()
      break
    case 'Invoice':
      isValid = validateInput('Invoice', InvoiceNO.value)
      val = InvoiceNO.value.toUpperCase()
      break
    case 'Invoice2':
      isValid = validateInput('Invoice2', OrderNO4Invoice.value)
      break
    case 'PurchaseOrder':
      isValid = validateInput('PurchaseOrder', PurchaseNO.value)
      break
    case 'Receipt':
      isValid = validateInput('Receipt', ReceiptNO.value)
      break
    case 'Receipt2':
      isValid = validateInput('Receipt2', PurchaseSiteVendorCodeDuration.value)
      break
    case 'WorkOrder':
      isValid = validateInput('WorkOrder', ProjectOrWorkOrderNO.value)
      break
    case 'SOA':
      isValid = validateInput('SOA', SiteAndBPCode.value)
      break
    default:
      isValid = false
  }
  if (isValid === false) {
    UrlShow.value = '/WaitInput'
    return
  }

  $q.loadingBar.start()

  if (fromWhere === 'sage') {
    UrlShow.value =
      (await getSageUrl(rpt, val)) +
      '?disposition=inline&filename=' +
      val +
      '.pdf'
  } else {
    UrlShow.value = getSageAssistantUrl(rpt, 'showPdf')
  }
  $q.loadingBar.stop()
  $q.loading.hide()
}

const exportWord = (rpt) => {
  $q.loadingBar.start()
  UrlExport.value = getSageAssistantUrl(rpt, 'exportWord')
}

// It also caintains some old reports, they are changed to SageUrl, stop use them now.
const getSageAssistantUrl = (rpt, type) => {
  let url = '/WaitInput'
  switch (rpt) {
    case 'COC':
      url = '/Report/COC/' + type + '?ProjectNO=' + COCProj.value.toUpperCase()
      break
    case 'Delivery':
      url =
        '/Report/Delivery/' +
        type +
        '?DeliveryNO=' +
        DeliveryNO.value.toUpperCase()
      break
    case 'Invoice':
      url =
        '/Report/Invoice/' +
        type +
        '?InvoiceNO=' +
        InvoiceNO.value.toUpperCase()
      break
    case 'Invoice2':
      url =
        '/Report/Invoice2/' +
        type +
        '?OrderNO=' +
        OrderNO4Invoice.value.toUpperCase()
      break
    case 'PurchaseOrder':
      url =
        '/Report/PurchaseOrder/' +
        type +
        '?PurchaseNO=' +
        PurchaseNO.value.toUpperCase() +
        '&TaxInclude=' +
        POtax.value
      break
    case 'Receipt':
      url =
        '/Report/Receipt/' +
        type +
        '?ReceiptNO=' +
        ReceiptNO.value.toUpperCase()
      break
    case 'Receipt2':
      url =
        '/Report/Receipt2/' +
        type +
        '?PurchaseSite=' +
        PurchaseSiteVendorCodeDuration.value.slice(0, 3).toUpperCase() +
        '&VendorCode=' +
        PurchaseSiteVendorCodeDuration.value.slice(4, 9) +
        '&StartDay=' +
        PurchaseSiteVendorCodeDuration.value.slice(10, 18) +
        '&EndDay=' +
        PurchaseSiteVendorCodeDuration.value.slice(19, 27)
      break
    case 'WorkOrder':
      url =
        '/Report/WorkOrder/' +
        type +
        '?ProjectOrWorkOrderNO=' +
        ProjectOrWorkOrderNO.value.toUpperCase()
      break
    case 'SOA':
      url =
        '/Report/SOA/' +
        type +
        '?Site=' +
        SiteAndBPCode.value.slice(0, 3).toUpperCase() +
        '&BPCode=' +
        SiteAndBPCode.value.slice(3, 8).toUpperCase()
      break
    default:
      url = '/WaitInput'
  }
  return url
}

const getSageUrl = async (rpt, val) => {
  const regException = /^\/#\/Exception\//
  $q.loading.show({
    message: 'Preparing to print report...',
    spinner: QSpinnerGears,
    spinnerColor: 'indigo-1'
  })
  const sageSessionUrl = await getSageSessionUrl(getFncForRpt(rpt))
  if (regException.test(sageSessionUrl)) {
    return sageSessionUrl
  }

  $q.loading.show({
    message: 'Getting report data...',
    spinner: QSpinnerGears,
    spinnerColor: 'indigo-3'
  })

  const rtn = await doActionsForSagePrint(sageSessionUrl, rpt, val)
  if (!rtn) {
    return '/#/Exception/500'
  }

  $q.loading.show({
    message: 'Getting report data...',
    spinner: QSpinnerGears,
    spinnerColor: 'indigo-5'
  })
  const printUUID = await getSagePrintUUID(sageSessionUrl)
  if (regException.test(printUUID)) {
    return printUUID
  }

  $q.loading.show({
    message: 'Printing report in server...',
    spinner: QSpinnerGears,
    spinnerColor: 'indigo-7'
  })
  const reportUrl = await getSageReportUrl(printUUID)
  return reportUrl
  /*  if (regException.test(reportUrl)) {
        return reportUrl
      }

      $q.loading.show({
        message: 'Downloading report...',
        spinner: QSpinnerGears,
        spinnerColor: 'indigo-10'
      })
      return await pdfUrltoDataUri(reportUrl)
      */
}

const onLoad = () => {
  $q.loadingBar.stop()
}
</script>
<style lang="scss" scoped>
.q-item__section--side {
  padding-left: 4px;
}
</style>
