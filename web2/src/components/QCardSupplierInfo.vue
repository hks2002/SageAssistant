<template>
  <q-card>
    <q-item
      class="text-h6 text-weight-bold q-pr-md"
      no-wrap
    >
      <q-icon
        name="fas fa-address-book"
        class="q-pr-md q-pt-xs"
        color="teal"
      />
      <a target="_blank">
        {{supplierInfo.SupplierCode}}
        {{supplierInfo.SupplierName0}}
        {{supplierInfo.SupplierName1}}
      </a>
    </q-item>
    <q-item>
      <q-icon
        name="fas fa-phone"
        class="q-pr-md q-pt-xs"
        color="teal"
      />
      {{supplierInfo.Tel0}}
      {{supplierInfo.Tel1}}
      {{supplierInfo.Tel2}}
      {{supplierInfo.Tel3}}
      {{supplierInfo.Tel4}}
      {{supplierInfo.Mobile0}}
      <q-icon
        name="fas fa-fax"
        class="q-pr-md q-pt-xs"
        color="teal"
      />
      {{supplierInfo.Fax0}}

      <q-icon
        name="fas fa-envelope"
        class="q-pr-md q-pt-xs"
        color="teal"
      />
      {{supplierInfo.Email0}}
      {{supplierInfo.Email1}}
      {{supplierInfo.Email2}}
      {{supplierInfo.Email3}}
      {{supplierInfo.Email4}}

      <q-icon
        name="fas fa-map-marker"
        class="q-pr-md q-pt-xs"
        color="teal"
      />
      {{supplierInfo.Address}} {{supplierInfo.Contry}} {{supplierInfo.State}} {{supplierInfo.City}}
      {{supplierInfo.Address0}}{{supplierInfo.Address1}}
    </q-item>

    <q-inner-loading :showing="showLoading">
      <q-spinner-ios
        size="50px"
        color="primary"
      />
    </q-inner-loading>
  </q-card>
</template>

<script>
import { defineComponent, onMounted, ref, watch, computed } from 'vue'
import { notifyError } from 'assets/common'
import { axios } from 'boot/axios'

export default defineComponent({
  name: 'QCardSupplierInfo',

  props: {
    supplierCode: String
  },

  setup(props, ctx) {
    const supplierInfo = ref({})
    const showLoading = ref(false)

    const doUpdate = () => {
      showLoading.value = true

      axios.get('/Data/SupplierDetails?SupplierCode=' + props.supplierCode)
        .then((response) => {
          supplierInfo.value = response.data
        })
        .catch((e) => {
          console.error(e)
          notifyError('Loading Supply Info Failed!')
        }).finally(() => {
          showLoading.value = false
        })
    }

    onMounted(() => {
      console.debug('onMounted QItemSupplierInfo')
      if (props.supplierCode) {
        doUpdate()
      }
    })

    watch(() => [props.supplierCode], (...newAndold) => {
      // newAndold[1]:old
      // newAndold[0]:new
      console.debug('watch:' + newAndold[1] + ' ---> ' + newAndold[0])
      if (newAndold[0][0]) {
        doUpdate()
      }
    })

    const WebSiteUrl = computed(() => {
      if (props.supplierInfo.Website && props.supplierInfo.Website.toLowerCase().substr(0, 4) === 'http') {
        return this.supplierInfo.Website
      } else {
        return 'http://' + props.supplierInfo.Website
      }
    })
    return {
      supplierInfo,
      showLoading,
      WebSiteUrl
    }
  }
})
</script>
<style lang="sass">
.q-item
  min-height: 24px
</style>
