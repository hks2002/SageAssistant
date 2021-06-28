<template>
  <q-item style="padding:0px">
    <q-item-section class="relative-position">
      <q-markup-table>
        <thead>
          <tr>
            <td class="text-left">Site</td>
            <td class="text-center">Min</td>
            <td class="text-center">Avg</td>
            <td class="text-center">Max</td>
          </tr>
        </thead>
        <tbody v-for="delivery in deliveryDuration"
               :key="delivery.ROWID">
          <tr>
            <td class="text-left">{{delivery.SalesSite}}</td>
            <td class="text-center">{{delivery.Min}}</td>
            <td class="text-center">{{delivery.Avg}}</td>
            <td class="text-center">{{delivery.Max}}</td>
          </tr>
        </tbody>
      </q-markup-table>
      <q-inner-loading :showing="showLoadingDelivery">
        <q-spinner-ios size="50px"
                       color="primary" />
      </q-inner-loading>
    </q-item-section>
  </q-item>
</template>

<script>
if (process.env.DEV) {
  require('../mock/DeliveryDuration')
}
export default {
  name: 'QItemDeliveryDuration',
  props: {
    pnRoot: String
  },
  data () {
    return {
      deliveryDuration: [],
      showLoadingDelivery: false
    }
  },
  methods: {
    doUpdate (pnRoot) {
      this.showLoadingDelivery = true

      this.$axios.get('/Data/DeliveryDuration?PnRoot=' + pnRoot)
        .then((response) => {
          console.debug('[axios] ' + response.status + ' ' + response.statusText + ' ' + response.config.url)
          console.debug(JSON.stringify(response.data))

          this.deliveryDuration = response.data
          this.showLoadingDelivery = false
        })
        .catch((e) => {
          this.showLoadingDelivery = false

          this.$q.notify({
            color: 'negative',
            position: 'top',
            message: 'Loading Delivery Duration Failed!',
            icon: 'fas fa-exclamation-triangle'
          })
        })
    },
    doReset () {
      this.deliveryDuration = []
      this.showLoadingDelivery = false
    }
  },
  watch: {
    pnRoot: {
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
  }
}
</script>
