<template>
<q-item>
  <q-list>
    <q-item style="padding:0px">
      <q-icon name="fas fa-address-book" class="q-pr-md q-pt-xs" color="teal" />
      <q-item-section class="col-1 text-h6 text-weight-bold q-pr-md"><a :href="WebSiteUrl" target="_blank">{{supplyInfo.SupplyCode}}</a></q-item-section>
      <q-item-section class="col-auto text-h6 text-weight-bold q-pr-md" no-wrap>{{supplyInfo.SupplyName0}}</q-item-section>
      <q-item-section class="col-auto text-h6 text-weight-bold q-pr-md" no-wrap v-if="supplyInfo.SupplyName1">{{supplyInfo.SupplyName1}}</q-item-section>
    </q-item>
    <q-separator />
    <q-item style="padding:0px">
      <q-icon name="fas fa-phone" class="q-pr-md q-pt-xs" color="teal" />
      <q-item-section class="col-auto q-pr-md text-subtitle1" v-if="supplyInfo.Tel0">{{supplyInfo.Tel0}}</q-item-section>
      <q-item-section class="col-auto text-subtitle1 q-pr-md" v-if="supplyInfo.Tel1">{{supplyInfo.Tel1}}</q-item-section>
      <q-item-section class="col-auto text-subtitle1 q-pr-md" v-if="supplyInfo.Tel2">{{supplyInfo.Tel2}}</q-item-section>
      <q-item-section class="col-auto text-subtitle1 q-pr-md" v-if="supplyInfo.Tel3">{{supplyInfo.Tel3}}</q-item-section>
      <q-item-section class="col-auto text-subtitle1 q-pr-md" v-if="supplyInfo.Tel4">{{supplyInfo.Tel4}}</q-item-section>
      <q-item-section class="col-auto text-subtitle1 q-pr-md" v-if="supplyInfo.Mobile0">{{supplyInfo.Mobile0}}</q-item-section>
      <q-icon name="fas fa-fax" v-if="supplyInfo.Fax0" class="q-pr-md q-pt-xs" color="teal" />
      <q-item-section class="col-auto text-subtitle1 q-pr-md" v-if="supplyInfo.Fax0">{{supplyInfo.Fax0}}</q-item-section>
    </q-item>
    <q-separator />
    <q-item style="padding:0px">
          <q-icon name="fas fa-envelope" class="q-pr-md q-pt-xs" color="teal" />
          <q-item-section v-if="supplyInfo.Email0" class="col-auto text-subtitle1 q-pr-md">{{supplyInfo.Email0}}</q-item-section>
          <q-item-section v-if="supplyInfo.Email1" class="col-auto text-subtitle1 q-pr-md">{{supplyInfo.Email1}}</q-item-section>
          <q-item-section v-if="supplyInfo.Email2" class="col-auto text-subtitle1 q-pr-md">{{supplyInfo.Email2}}</q-item-section>
          <q-item-section v-if="supplyInfo.Email3" class="col-auto text-subtitle1 q-pr-md">{{supplyInfo.Email3}}</q-item-section>
          <q-item-section v-if="supplyInfo.Email4" class="col-auto text-subtitle1 q-pr-md">{{supplyInfo.Email4}}</q-item-section>
    </q-item>
    <q-separator />
    <q-item style="padding:0px">
      <q-icon name="fas fa-map-marker" class="q-pr-md q-pt-xs" color="teal" />
      <q-item-section class="text-subtitle1 q-pr-md">{{supplyInfo.Address}} {{supplyInfo.Contry}} {{supplyInfo.State}} {{supplyInfo.City}}</q-item-section>
      <q-item-section v-if="supplyInfo.Address0" class="col-auto text-subtitle1 q-pr-md" >{{supplyInfo.Address0}}</q-item-section>
      <q-item-section v-if="supplyInfo.Address1" class="col-auto text-subtitle1 q-pr-md" >{{supplyInfo.Address1}}</q-item-section>
      </q-item>
    <q-separator />
  <q-inner-loading :showing="showLoading">
    <q-spinner-ios size="50px"
                    color="primary" />
  </q-inner-loading>
  </q-list>
</q-item>
</template>

<script>
if (process.env.DEV) {
  require('../mock/SupplyDetails')
}
export default {
  name: 'QItemSupplyInfo',
  props: {
    supplyCode: String
  },
  data () {
    return {
      supplyInfo: {},
      showLoading: false
    }
  },
  methods: {
    doUpdate (code) {
      this.showLoading = true

      this.$axios.get('/Data/SupplyDetails?SupplyCode=' + code)
        .then((response) => {
          console.debug('[axios] ' + response.status + ' ' + response.statusText + ' ' + response.config.url)
          console.debug(JSON.stringify(response.data))

          this.supplyInfo = response.data
          this.showLoading = false
        })
        .catch((e) => {
          this.showLoading = false

          this.$q.notify({
            color: 'negative',
            position: 'top',
            message: 'Loading Supply Info Failed!',
            icon: 'fas fa-exclamation-triangle'
          })
        })
    },
    doReset () {
      this.supplyInfo = {}
      this.showLoading = false
    }
  },
  watch: {
    supplyCode: {
      handler (newVal, oldVal) {
        console.debug(oldVal + ' --> ' + newVal)

        if (newVal) {
          this.doUpdate(newVal)
        } else {
          this.doReset()
        }
      },
      immediate: true
    }
  },
  mounted: function () {
  },
  computed: {
    WebSiteUrl: function () {
      if (this.supplyInfo.Website && this.supplyInfo.Website.toLowerCase().substr(0, 4) === 'http') {
        return this.supplyInfo.Website
      } else {
        return 'http://' + this.supplyInfo.Website
      }
    }
  }
}
</script>
<style lang="sass">
.q-item
 min-height: 24px
</style>
