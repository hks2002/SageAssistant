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
        <page-fapiao v-show="isAuthorised('GESSIH')" />
      </q-tab-panel>
      <q-tab-panel
        name="Profit"
        style="padding:0px"
      >
        <page-fapiao v-show="isAuthorised('CONSBAL')" />
      </q-tab-panel>
    </q-tab-panels>
  </q-page>
</template>

<script>
import { defineComponent, ref } from 'vue'
import { ebus } from 'boot/ebus'
import { isAuthorised } from 'assets/auth'
import PageFapiao from 'src/components/pages/PageFapiao.vue'

export default defineComponent({
  name: 'Financials',

  components: {
    PageFapiao
  },

  setup(props, ctx) {
    const tab = ref('Fapiao')

    ebus.emit('closeLeftDrawer')
    ebus.emit('activePage', 'Finance')

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
