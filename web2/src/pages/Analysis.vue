<template>
  <q-page :key="timer">
    <q-tabs
      v-model="tab"
      dense
      align="left"
      class="text-grey"
      active-color="primary"
      indicator-color="primary"
      narrow-indicator
    >
      <q-tab
        name="QuoteSalesCost"
        label="Quote-Sales-Cost"
      />
    </q-tabs>

    <q-separator />

    <q-tab-panels
      v-model="tab"
      animated
      keep-alive
    >
      <q-tab-panel
        name="QuoteSalesCost"
        style="padding:0px"
      >
        <quote-sales-cost v-if="isAuthorised('GESSOH') || isAuthorised('GESSQH')" />
        <error-403 v-else />
      </q-tab-panel>
    </q-tab-panels>
  </q-page>
</template>

<script>
import { defineComponent, ref } from 'vue'
import { ebus } from 'boot/ebus'
import { isAuthorised } from 'assets/auth'
import QuoteSalesCost from 'src/components/QuoteSalesCost.vue'
import Error403 from 'pages/Error403.vue'

export default defineComponent({
  name: 'Analysis',

  components: {
    QuoteSalesCost,
    Error403
  },

  setup(props, ctx) {
    const tab = ref('QuoteSalesCost')

    ebus.emit('closeLeftDrawer')
    ebus.emit('activePage', 'Analysis')

    return {
      tab,
      timer: new Date().getTime(),
      isAuthorised
    }
  }
})
</script>
<style lang='scss' scoped>

</style>
