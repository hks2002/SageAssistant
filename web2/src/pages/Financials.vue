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
        name="Fapiao"
        label="Fapiao"
      />
      <q-tab
        name="Profit"
        label="Profit"
      />
    </q-tabs>

    <q-separator />

    <q-tab-panels
      v-model="tab"
      animated
      keep-alive
    >
      <q-tab-panel
        name="Fapiao"
        style="padding:0px"
      >
        <fapiao v-if="isAuthorised('GESSIH')" />
        <error-403 v-else />
      </q-tab-panel>
      <q-tab-panel
        name="Profit"
        style="padding:0px"
      >
        <fapiao v-if="isAuthorised('CONSBAL')" />
        <error-403 v-else />
      </q-tab-panel>
    </q-tab-panels>
  </q-page>
</template>

<script>
import { defineComponent, ref } from 'vue'
import { ebus } from 'boot/ebus'
import { isAuthorised } from 'assets/auth'
import Fapiao from 'src/components/Fapiao.vue'
import Error403 from 'pages/Error403.vue'

export default defineComponent({
  name: 'Financials',

  components: {
    Fapiao,
    Error403
  },

  setup(props, ctx) {
    const tab = ref('Fapiao')

    ebus.emit('closeLeftDrawer')
    ebus.emit('activePage', 'Financials')

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
