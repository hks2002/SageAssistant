<template>
  <q-page :key="timer">
    <analysis-main-vue />
  </q-page>
</template>

<script>
import { defineComponent } from 'vue'
import { ebus } from 'boot/ebus'
import AnalysisMainVue from 'src/components/analysis/AnalysisMain.vue'

export default defineComponent({
  name: 'Analysis',

  components: {
    AnalysisMainVue
  },

  setup(props, ctx) {
    ebus.emit('closeLeftDrawer')
    ebus.emit('activePage', 'Analysis')

    return {
      timer: new Date().getTime()
    }
  }
})
</script>
<style lang='scss' scoped>
</style>
