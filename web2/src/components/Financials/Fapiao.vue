<template>
  <div class="row">
    <q-card flat class="col-6 q-pa-xs">
      <q-card-section class="row">
        <q-select-input
          class="col-5 q-pr-md"
          data-url="/Fapiao/Lbdm?Lbdm="
          emit-to="searchFapiaoLbdm"
          :label="$t('发票代码')"
          :default-value="Lbdm"
          popup-content-class="text-secondary"
          :disable="!isAuthorised('GESSIH')"
        />
        <q-select-input
          class="col-5 q-pr-md"
          data-url="/Fapiao/Fphm?Fphm="
          emit-to="searchFapiaoFphm"
          :label="$t('发票号码')"
          :default-value="Fphm"
          popup-content-class="text-secondary"
          :disable="!isAuthorised('GESSIH')"
        />
      </q-card-section>
      <q-separator inset />
      <q-card-section class="q-pa-none">
        <q-list dense>
          <q-item v-ripple>
            <q-item-section> 发票种类 </q-item-section>
            <q-item-section>
              {{ FapiaoHeader.fpzl }}
            </q-item-section>
            <q-item-section> 开票日期 </q-item-section>
            <q-item-section>
              {{ FapiaoHeader.kprq.split(' ')[0] }}
            </q-item-section>
            <q-item-section> 发票状态 </q-item-section>
            <q-item-section>
              {{ FapiaoHeader.fpzt }}
            </q-item-section>
          </q-item>
          <q-item v-ripple>
            <q-item-section> 收款人 </q-item-section>
            <q-item-section>
              {{ FapiaoHeader.skr }}
            </q-item-section>
            <q-item-section> 复核人 </q-item-section>
            <q-item-section>
              {{ FapiaoHeader.fhr }}
            </q-item-section>
            <q-item-section> 开票人 </q-item-section>
            <q-item-section>
              {{ FapiaoHeader.kpr }}
            </q-item-section>
          </q-item>
          <q-item v-ripple>
            <q-item-section> 合计金额 </q-item-section>
            <q-item-section
              :class="
                notMatchClass(
                  NMN(
                    InvoiceHeader.AmountTaxNotInclude,
                    InvoiceHeader.CurrRate
                  ),
                  S2N(FapiaoHeader.hjje)
                )
              "
            >
              {{ S2N(FapiaoHeader.hjje) }}
            </q-item-section>
            <q-item-section> 合计税额 </q-item-section>
            <q-item-section
              :class="
                notMatchClass(
                  NMN(InvoiceHeader.AmountTax, InvoiceHeader.CurrRate),
                  S2N(FapiaoHeader.hjse)
                )
              "
            >
              {{ S2N(FapiaoHeader.hjse) }}
            </q-item-section>
            <q-item-section> 总计 </q-item-section>
            <q-item-section
              :class="
                notMatchClass(
                  NMN(InvoiceHeader.AmountTaxInclude, InvoiceHeader.CurrRate),
                  NPN(FapiaoHeader.hjje, FapiaoHeader.hjse)
                )
              "
            >
              {{ NPN(FapiaoHeader.hjje, FapiaoHeader.hjse) }}
            </q-item-section>
          </q-item>
          <q-item>
            <q-item-section side> 备注 </q-item-section>
            <q-item-section>
              {{ FapiaoHeader.bz }}
            </q-item-section>
          </q-item>

          <q-separator inset />

          <q-item v-ripple v-if="FapiaoHeader.gfsh != '914404006981669765'">
            <q-item-section side> 购方名称 </q-item-section>
            <q-item-section>
              {{ FapiaoHeader.gfmc }}
            </q-item-section>
          </q-item>
          <q-item v-ripple v-if="FapiaoHeader.gfsh != '914404006981669765'">
            <q-item-section side> 购方税号 </q-item-section>
            <q-item-section>
              {{ FapiaoHeader.gfsh }}
            </q-item-section>
          </q-item>
          <q-item v-ripple v-if="FapiaoHeader.gfsh != '914404006981669765'">
            <q-item-section side> 购方地址电话 </q-item-section>
            <q-item-section>
              {{ FapiaoHeader.gfdzdh }}
            </q-item-section>
          </q-item>
          <q-item v-ripple v-if="FapiaoHeader.gfsh != '914404006981669765'">
            <q-item-section side> 购方银行账号 </q-item-section>
            <q-item-section>
              {{ FapiaoHeader.gfyhzh }}
            </q-item-section>
          </q-item>

          <q-separator inset />

          <q-item
            v-ripple
            v-if="
              FapiaoHeader.xfsh != '914404006981669765' &&
              FapiaoHeader.xfsh != ''
            "
          >
            <q-item-section side> 销方名称 </q-item-section>
            <q-item-section>
              {{ FapiaoHeader.xfmc }}
            </q-item-section>
          </q-item>
          <q-item
            v-ripple
            v-if="
              FapiaoHeader.xfsh != '914404006981669765' &&
              FapiaoHeader.xfsh != ''
            "
          >
            <q-item-section side> 销方税号 </q-item-section>
            <q-item-section>
              {{ FapiaoHeader.xfsh }}
            </q-item-section>
          </q-item>
          <q-item
            v-ripple
            v-if="
              FapiaoHeader.xfsh != '914404006981669765' &&
              FapiaoHeader.xfsh != ''
            "
          >
            <q-item-section side> 销方地址电话 </q-item-section>
            <q-item-section>
              {{ FapiaoHeader.xfdzdh }}
            </q-item-section>
          </q-item>
          <q-item
            v-ripple
            v-if="
              FapiaoHeader.xfsh != '914404006981669765' &&
              FapiaoHeader.xfsh != ''
            "
          >
            <q-item-section side> 销方银行账号 </q-item-section>
            <q-item-section>
              {{ FapiaoHeader.xfyhzh }}
            </q-item-section>
          </q-item>
        </q-list>
        <q-inner-loading :showing="showLoadingFapiaoHeader">
          <q-spinner-ios size="50px" color="primary" />
        </q-inner-loading>
      </q-card-section>
      <q-card-section class="q-pa-none">
        <q-markup-table dense class="col-4">
          <thead>
            <tr>
              <th class="text-left">项号</th>
              <th class="text-left">货物、服务名称</th>
              <th class="text-left">规则型号</th>
              <th class="text-center">单位</th>
              <th class="text-center">数量</th>
              <th class="text-right">单价</th>
              <th class="text-right">金额</th>
              <th class="text-center">税率</th>
              <th class="text-right">税额</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(item, index) in FapiaoBody" :key="index">
              <td class="text-left">{{ item.xh }}</td>
              <td class="text-left">{{ item.spmc }}</td>
              <td class="text-left">{{ item.ggxh }}</td>
              <td class="text-center">{{ item.jldw }}</td>
              <td class="text-center">{{ item.sl }}</td>
              <td class="text-right">{{ S2N(item.dj) }}</td>
              <td
                class="text-right"
                :class="
                  notMatchClass(
                    NMN(
                      ANN(InvoiceBody, index, 'AmountNoTax'),
                      ANN(InvoiceBody, index, 'TaxRate')
                    ),
                    S2N(item.je)
                  )
                "
              >
                {{ S2N(item.je) }}
              </td>
              <td class="text-center">{{ item.slv }}</td>
              <td
                class="text-right"
                :class="
                  notMatchClass(
                    NMN(
                      ANN(InvoiceBody, index, 'AmountTax'),
                      ANN(InvoiceBody, index, 'TaxRate')
                    ),
                    S2N(item.se)
                  )
                "
              >
                {{ S2N(item.se) }}
              </td>
            </tr>
          </tbody>
        </q-markup-table>
        <q-inner-loading :showing="showLoadingFapiaoBody">
          <q-spinner-ios size="50px" color="primary" />
        </q-inner-loading>
      </q-card-section>
    </q-card>

    <q-card flat class="col-6 q-pa-xs">
      <q-card-section class="row">
        <q-select-input
          class="col-5 q-pr-md"
          data-url="/Data/InvoiceNO?InvoiceNO="
          emit-to="searchInvoiceNO"
          :label="$t('InvoiceNO')"
          :default-value="InvoiceNO"
          popup-content-class="text-secondary"
          :disable="!isAuthorised('GESSIH')"
        />
      </q-card-section>
      <q-separator inset />

      <q-card-section class="q-pa-none">
        <q-list dense>
          <q-item v-ripple>
            <q-item-section> CreateUser </q-item-section>
            <q-item-section>
              {{ InvoiceHeader.CreateUser }}
            </q-item-section>
            <q-item-section> CreateDate </q-item-section>
            <q-item-section>
              {{ InvoiceHeader.CreateDate }}
            </q-item-section>
            <q-item-section> Status </q-item-section>
            <q-item-section>
              {{ InvoiceHeader.InvoiceStatus }}
            </q-item-section>
          </q-item>
          <q-item v-ripple>
            <q-item-section> Fapiao </q-item-section>
            <q-item-section>
              {{ InvoiceHeader.FaPiao }}
            </q-item-section>
            <q-item-section> Currency </q-item-section>
            <q-item-section>
              {{ InvoiceHeader.Currency }}
            </q-item-section>
            <q-item-section> Rate </q-item-section>
            <q-item-section>
              {{ InvoiceHeader.CurrRate }}
            </q-item-section>
          </q-item>
          <q-item v-ripple>
            <q-item-section> AmountNoTax </q-item-section>
            <q-item-section
              :class="
                notMatchClass(
                  NMN(
                    InvoiceHeader.AmountTaxNotInclude,
                    InvoiceHeader.CurrRate
                  ),
                  S2N(FapiaoHeader.hjje)
                )
              "
            >
              {{ S2N(InvoiceHeader.AmountTaxNotInclude) }}
            </q-item-section>
            <q-item-section> AmountTax </q-item-section>
            <q-item-section
              :class="
                notMatchClass(
                  NMN(InvoiceHeader.AmountTax, InvoiceHeader.CurrRate),
                  S2N(FapiaoHeader.hjse)
                )
              "
            >
              {{ S2N(InvoiceHeader.AmountTax) }}
            </q-item-section>
            <q-item-section> AmountTaxInclude </q-item-section>
            <q-item-section
              :class="
                notMatchClass(
                  NMN(InvoiceHeader.AmountTaxInclude, InvoiceHeader.CurrRate),
                  NPN(FapiaoHeader.hjje, FapiaoHeader.hjse)
                )
              "
            >
              {{ S2N(InvoiceHeader.AmountTaxInclude) }}
            </q-item-section>
          </q-item>
          <q-item>
            <q-item-section side> Note </q-item-section>
            <q-item-section>
              {{ InvoiceHeader.Note }}
            </q-item-section>
          </q-item>
        </q-list>

        <q-separator inset />
        <q-list dense>
          <q-item v-ripple>
            <q-item-section side> Customer </q-item-section>
            <q-item-section>
              {{ InvoiceHeader.Customer }}
            </q-item-section>
          </q-item>
          <q-item v-ripple> </q-item>
          <q-item v-ripple>
            <q-item-section side> Address </q-item-section>
            <q-item-section>
              {{ InvoiceHeader.Address }}
            </q-item-section>
          </q-item>
          <q-item v-ripple> </q-item>
        </q-list>
        <q-inner-loading :showing="showLoadingInvoiceHeader">
          <q-spinner-ios size="50px" color="primary" />
        </q-inner-loading>
      </q-card-section>

      <q-separator inset />
      <q-card-section class="q-pa-none">
        <q-markup-table dense class="col-4">
          <thead>
            <tr>
              <th class="text-left">Line</th>
              <th class="text-left">Description</th>
              <th class="text-left">PN</th>
              <th class="text-center">Unit</th>
              <th class="text-center">Qty</th>
              <th class="text-right">NetPrice</th>
              <th class="text-right">AmountNoTax</th>
              <th class="text-center">TaxRate</th>
              <th class="text-right">AmountTax</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(item, index) in InvoiceBody" :key="index">
              <td class="text-left">{{ item.Line }}</td>
              <td class="text-left">{{ item.Description }}</td>
              <td class="text-left">{{ item.PN }}</td>
              <td class="text-center">{{ item.Unit }}</td>
              <td class="text-center">{{ item.Qty }}</td>
              <td class="text-right">{{ S2N(item.NetPrice) }}</td>
              <td
                class="text-right"
                :class="
                  notMatchClass(
                    NMN(item.AmountNoTax, InvoiceHeader.CurrRate),
                    S2N(ANN(FapiaoBody, index, 'je'))
                  )
                "
              >
                {{ S2N(item.AmountNoTax) }}
              </td>
              <td class="text-center">{{ item.TaxRate }}</td>
              <td
                class="text-right"
                :class="
                  notMatchClass(
                    NMN(item.AmountTax, InvoiceHeader.CurrRate),
                    S2N(ANN(FapiaoBody, index, 'se'))
                  )
                "
              >
                {{ S2N(item.AmountTax) }}
              </td>
            </tr>
          </tbody>
        </q-markup-table>
        <q-inner-loading :showing="showLoadingInvoiceBody">
          <q-spinner-ios size="50px" color="primary" />
        </q-inner-loading>
      </q-card-section>
    </q-card>
  </div>
</template>

<script setup>
import { ref, onBeforeUnmount, toRaw } from 'vue'
import { axios } from 'boot/axios'
import { notifyError } from 'assets/common'
import { ebus } from 'boot/ebus'
import { isAuthorised } from 'assets/auth'
import QSelectInput from 'components/.controls/QSelectInputSimple.vue'

const Lbdm = ref('')
const Fphm = ref('')
const InvoiceNO = ref('')
const FapiaoHeader = ref({
  fpzl: '',
  lbdm: '',
  fphm: '',
  kprq: '',
  gfmc: '',
  gfsh: '',
  gfyhzh: '',
  gfdzdh: '',
  xfmc: '',
  xfsh: '',
  xfyhzh: '',
  xfdzdh: '',
  hjje: '',
  hjse: '',
  bz: '',
  kpr: '',
  fhr: '',
  skr: '',
  fpzt: ''
})
const FapiaoBody = ref([
  {
    xh: '',
    spmc: '',
    ggxh: '',
    jldw: '',
    dj: '',
    sl: '',
    je: '',
    slv: '',
    se: ''
  }
])
const InvoiceHeader = ref({
  Facility: '',
  Currency: '',
  InvoiceNO: '',
  InvoiceStatus: '',
  CreateDate: '',
  CreateUser: '',
  Note: '',
  FaPiao: '',
  Customer: '',
  Address: '',
  AmountTaxInclude: '',
  AmountTaxNotInclude: '',
  AmountTax: '',
  CurrRate: ''
})
const InvoiceBody = ref([
  {
    Line: '',
    PN: '',
    Description: '',
    Qty: '',
    Unit: '',
    NetPrice: '',
    AmountNoTax: '',
    AmountTaxInclude: '',
    AmountTax: '',
    TaxRate: ''
  }
])
const showLoadingFapiaoHeader = ref(false)
const showLoadingFapiaoBody = ref(false)
const showLoadingInvoiceHeader = ref(false)
const showLoadingInvoiceBody = ref(false)

const doUpdateFapiaoHeader = async (onlyFphm) => {
  showLoadingFapiaoHeader.value = true
  let url = '/Fapiao/Header?Fphm=' + Fphm.value
  if (!onlyFphm) {
    url += '&Lbdm=' + Lbdm.value
  }

  axios
    .get(url)
    .then((response) => {
      // Fapiao aways be array, even on recorder
      if (response.data.length > 0) {
        FapiaoHeader.value = response.data[0] // Only one
      }
    })
    .catch((e) => {
      console.error(e)
      notifyError('Loading Fapiao Failed!')
    })
    .finally(() => {
      showLoadingFapiaoHeader.value = false
    })
}

const doUpdateFapiaoBody = async (onlyFphm) => {
  showLoadingFapiaoBody.value = true
  let url = '/Fapiao/Body?Fphm=' + Fphm.value
  if (!onlyFphm) {
    url += '&Lbdm=' + Lbdm.value
  }

  axios
    .get(url)
    .then((response) => {
      FapiaoBody.value = response.data
    })
    .catch((e) => {
      console.error(e)
      notifyError('Loading Fapiao Failed!')
    })
    .finally(() => {
      showLoadingFapiaoBody.value = false
    })
}

const doUpdateInvoiceHeader = async (byType) => {
  showLoadingInvoiceHeader.value = true
  let url = ''
  if (byType === 'Invoice') {
    url = '/Data/InvoiceHeaderByInvoiceNO?InvoiceNO=' + InvoiceNO.value
  }
  if (byType === 'Fapiao') {
    url = '/Data/InvoiceHeaderByFaPiao?FaPiao=' + Fphm.value
  }
  axios
    .get(url)
    .then((response) => {
      InvoiceHeader.value = response.data
    })
    .catch((e) => {
      console.error(e)
      notifyError('Loading Invoice Failed!')
    })
    .finally(() => {
      showLoadingInvoiceHeader.value = false
    })
}

const doUpdateInvoiceBody = async (byType) => {
  showLoadingInvoiceBody.value = true
  let url = ''
  if (byType === 'Invoice') {
    url = '/Data/InvoiceBodyByInvoiceNO?InvoiceNO=' + InvoiceNO.value
  }
  if (byType === 'Fapiao') {
    url = '/Data/InvoiceBodyByFaPiao?FaPiao=' + Fphm.value
  }
  axios
    .get(url)
    .then((response) => {
      InvoiceBody.value = response.data
    })
    .catch((e) => {
      console.error(e)
      notifyError('Loading Invoice Failed!')
    })
    .finally(() => {
      showLoadingInvoiceBody.value = false
    })
}

// return str to number
const S2N = (S, n) => {
  let N = 0
  if (n) {
    N = parseFloat(S).toFixed(n)
  } else {
    N = parseFloat(S).toFixed(2)
  }
  if (N === 'NaN') {
    return ''
  } else {
    return N
  }
}
// renturn N1 plus N2
const NPN = (N1, N2, n) => {
  let N3 = 0
  if (n) {
    N3 = (parseFloat(N1, 8) + parseFloat(N2, 8)).toFixed(n)
  } else {
    N3 = (parseFloat(N1, 8) + parseFloat(N2, 8)).toFixed(2)
  }
  if (N3 === 'NaN') {
    return ''
  } else {
    return N3
  }
}
// return N1 product N2 value
const NMN = (N1, N2, n) => {
  let N3 = 0
  if (n) {
    N3 = (parseFloat(N1, 8) * parseFloat(N2, 8)).toFixed(n)
  } else {
    N3 = (parseFloat(N1, 8) * parseFloat(N2, 8)).toFixed(2)
  }
  if (N3 === 'NaN') {
    return ''
  } else {
    return N3
  }
}
// return ref array item value by key
const ANN = (array, index, key) => {
  const a = toRaw(array)
  let N = 0
  if (a.length < index + 1) {
    N = 0
  } else {
    return a[index][key]
  }
  if (N === 'NaN') {
    return ''
  } else {
    return N
  }
}

const notMatchClass = (val1, val2) => {
  if (val1 !== '' && val2 !== '' && val1 !== val2) {
    return 'bg-red'
  } else {
    return ''
  }
}

// event handing
ebus.on('searchInvoiceNO', async (invoiceNo) => {
  InvoiceNO.value = invoiceNo
  if (InvoiceNO.value) {
    await doUpdateInvoiceHeader('Invoice')
    Fphm.value = InvoiceHeader.value.FaPiao
    doUpdateInvoiceBody('Invoice')
    // lbdm value need get from query fapiao header, then show it.
    await doUpdateFapiaoHeader(true)
    Lbdm.value = FapiaoHeader.value.lbdm
    doUpdateFapiaoBody(false)
  }
})
onBeforeUnmount(() => {
  ebus.off('searchInvoiceNO')
})
ebus.on('searchFapiaoLbdm', async (lbdm) => {
  Lbdm.value = lbdm
  if (Lbdm.value !== '' && Fphm.value !== '') {
    await doUpdateFapiaoHeader() // top lbdm
    doUpdateFapiaoBody(false)
    await doUpdateInvoiceHeader('Fapiao')
    InvoiceNO.value = InvoiceHeader.value.InvoiceNO
    doUpdateInvoiceBody('Fapiao')
  }
})
onBeforeUnmount(() => {
  ebus.off('searchFapiaoLbdm')
})
ebus.on('searchFapiaoFphm', async (fphm) => {
  Fphm.value = fphm
  if (Lbdm.value !== '' && Fphm.value !== '') {
    await doUpdateFapiaoHeader() // top lbdm
    doUpdateFapiaoBody(false)
    await doUpdateInvoiceHeader('Fapiao')
    InvoiceNO.value = InvoiceHeader.value.InvoiceNO
    doUpdateInvoiceBody('Fapiao')
  }
})
onBeforeUnmount(() => {
  ebus.off('searchFapiaoFphm')
})
</script>
