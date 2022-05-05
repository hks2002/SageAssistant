<template>
  <q-card>
    <template
      v-for="(info, index) in customerInfo"
      :key="info['CustomerCode']"
    >
      <q-item
        class="text-h6 text-weight-bold q-pr-md q-pb-xs"
        no-wrap
        v-if=" index ===0 "
      >
        <q-icon
          name="fas fa-address-book"
          class="q-pr-md q-pt-xs"
          color="teal"
        />
        {{info['CustomerCode']}}
        {{info['CustomerName0']}}
        {{info['CustomerName1']}}
      </q-item>
    </template>
    <template
      v-for="(info) in customerInfo"
      :key="info['CustomerCode']"
    >
      <q-item class="q-pt-xs q-pb-xs">
        <q-icon
          name="fas fa-phone"
          class="q-pr-md q-pt-xs"
          color="teal"
          v-if=" info['Tel0'] !=' ' || info['Tel1'] !=' ' || info['Tel2'] !=' ' || info['Tel3'] !=' ' || info['Tel4'] !=' ' || info['Mobile0'] !=' ' "
        />
        {{info['Tel0']}}
        {{info['Tel1']}}
        {{info['Tel2']}}
        {{info['Tel3']}}
        {{info['Tel4']}}
        {{info['Mobile0']}}
        <q-icon
          name="fas fa-fax"
          class="q-pr-md q-pl-md q-pt-xs"
          color="teal"
          v-if=" info['Fax0'] != ' ' "
        />
        {{info['Fax0']}}

        <q-icon
          name="fas fa-envelope"
          class="q-pr-md q-pl-md q-pt-xs"
          color="teal"
          v-if=" info['Email0'] !=' ' || info['Email1'] !=' ' || info['Email2'] !=' ' || info['Email3'] !=' ' || info['Email4'] !=' '"
        />
        {{info['Email0']}}
        {{info['Email1']}}
        {{info['Email2']}}
        {{info['Email3']}}
        {{info['Email4']}}

        <q-icon
          name="fas fa-map-marker"
          class="q-pr-md q-pl-md q-pt-xs"
          color="teal"
          v-if=" info['PostCode'] != ' ' || info['Contry'] !=' ' || info['State'] !=' ' || info['City'] !=' ' || info['Address0'] !=' ' || info['Address1'] !=' '"
        />
        {{info['PostCode']}}{{info['Contry']}} {{info['State']}} {{info['City']}}
        {{info['Address0']}}{{info['Address1']}}

        <q-icon
          name="fas fa-internet-explorer"
          class="q-pr-md q-pl-md q-pt-xs"
          color="teal"
          v-if="info['Website'] != ' ' "
        />
        <a
          target="_blank"
          :href="WebSiteUrl(info['Website'])"
          v-if="info['Website'] != ' ' "
        >
          {{ WebSiteUrl(info['Website']) }}
        </a>
      </q-item>
    </template>
    <q-inner-loading :showing="showLoading">
      <q-spinner-ios
        size="50px"
        color="primary"
      />
    </q-inner-loading>
  </q-card>
</template>

<script setup>
import { defineComponent, onMounted, ref, watch, computed } from 'vue'
import { notifyError } from 'assets/common'
import { axios } from 'boot/axios'

const props = defineProps({
  customerCode: String
})

const customerInfo = ref([])
const showLoading = ref(false)

const doUpdate = () => {
  showLoading.value = true

  axios
    .get('/Data/CustomerDetails?CustomerCode=' + props.customerCode)
    .then((response) => {
      customerInfo.value = response.data
    })
    .catch((e) => {
      console.error(e)
      notifyError('Loading Customer Info Failed!')
    })
    .finally(() => {
      showLoading.value = false
    })
}

const WebSiteUrl = (url) => {
  if (url.toLowerCase().substr(0, 4) === 'http') {
    return url
  } else {
    return 'http://' + url
  }
}

onMounted(() => {
  console.debug('onMounted QItemCustomerInfo')
  if (props.customerCode) {
    doUpdate()
  }
})

watch(
  () => [props.customerCode],
  (...newAndold) => {
    // newAndold[1]:old
    // newAndold[0]:new
    console.debug('watch:' + newAndold[1] + ' ---> ' + newAndold[0])
    if (newAndold[0][0]) {
      doUpdate()
    }
  }
)

</script>
<style lang="sass">
.q-item
  min-height: 24px
</style>
