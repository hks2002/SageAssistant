<template>
  <q-footer reveal
            elevated
            class="bg-grey-8 text-white">
    <q-ajax-bar ref="bar"
                position="bottom"
                color="accent"
                size="5px"
                skip-hijack />
    <q-bar class="fit flex-center">
      <div class="footer">
        <span class="q-pa-ms">Powered by Quasar {{ $q.version }} & ECharts {{ eChartsVersion }} & Spring boot {{ springBootVersion }}</span>
      </div>
    </q-bar>
  </q-footer>
</template>

<script>
if (process.env.DEV) {
  require('../mock/SrvInfo.js')
}
var packageInfo = require('../../package.json')

export default {
  name: 'PageFooter',
  data () {
    return {
      springBootVersion: '',
      eChartsVersion: packageInfo.dependencies.echarts.substring(1)
    }
  },
  methods: {
    loadData () {
      this.$axios.get('/Data/SrvInfo')
        .then((response) => {
          this.springBootVersion = response.data.springBootVersion
        })
    }
  },
  mounted: function () {
    this.loadData()
  }
}
</script>

<style>
/* This is where your CSS goes */

.footer {
  font-size: 8px;
  color: #fff;
}
</style>
