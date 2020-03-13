<template>
  <q-page class="flex flex-center">
    <q-card>
      <div>
        <div class="row text-h6 bg-primary text-white q-pl-sm">
          About {{productName}}
          <q-chip dense
                  color="orange"
                  text-color="white">
            {{version}}</q-chip>
          <q-separator vertical />
          {{srvName}}
          <q-chip dense
                  color="purple"
                  text-color="white">
            {{srvVersion}}
          </q-chip>
        </div>

      </div>
      <q-separator />
      <div class="text-h6 q-pa-sm"> It's a tool to get information from sage, to save your time, and increase productivity. </div>
    </q-card>

  </q-page>
</template>

<script>
// import '../mock/SrvInfo.js'

export default {
  name: 'About',
  data () {
    return {
      productName: require('../../package.json').productName,
      version: require('../../package.json').version,
      author: require('../../package.json').author,
      srvName: '',
      srvVersion: ''
    }
  },
  methods: {
    loadData () {
      this.$axios.get('/Data/SrvInfo')
        .then((response) => {
          this.srvName = response.data.name
          this.srvVersion = response.data.version
        })
    }
  },
  mounted: function () {
    this.loadData()
  }
}
</script>
