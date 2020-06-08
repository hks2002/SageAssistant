<template>
  <q-item style="padding:0px">
    <q-item-section class="relative-position">
      <q-markup-table>
        <thead>
          <tr>
            <td class="text-left">Site</td>
            <td class="text-left">PN</td>
            <td class="text-right">Qty</td>
          </tr>
        </thead>
        <tbody v-for="inventoryStock in inventoryStock"
               :key="inventoryStock.ROWID">
          <tr>
            <td class="text-left">{{inventoryStock.StockSite}}</td>
            <td class="text-left">{{inventoryStock.PN}}</td>
            <td class="text-right">{{inventoryStock.Qty}}</td>
          </tr>
        </tbody>
      </q-markup-table>
      <q-inner-loading :showing="showLoadingInventory">
        <q-spinner-ios size="50px"
                       color="primary" />
      </q-inner-loading>
    </q-item-section>
  </q-item>
</template>

<script>
if (process.env.DEV) {
  require('../mock/InventoryStock')
}
export default {
  name: 'QItemDeliveryDuration',
  props: {
    pnRoot: String
  },
  data () {
    return {
      inventoryStock: [],
      showLoadingInventory: false
    }
  },
  methods: {
    doUpdate (pnRoot) {
      this.showLoadingInventory = true

      this.$axios.get('/Data/InventoryStock?PnRoot=' + pnRoot)
        .then((response) => {
          console.info('[axios] ' + response.status + ' ' + response.statusText + ' ' + response.config.url)
          console.debug(JSON.stringify(response.data))

          this.inventoryStock = response.data
          this.showLoadingInventory = false
        })
        .catch((e) => {
          this.showLoadingInventory = false

          this.$q.notify({
            color: 'negative',
            position: 'top',
            message: 'Loading Inventory Stock Summary Failed!',
            icon: 'fas fa-exclamation-triangle'
          })
        })
    },
    doReset () {
      this.inventoryStock = []
      this.showLoadingInventory = false
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
