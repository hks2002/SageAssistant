<template>
  <q-page class="flex flex-center">
    <q-card>
      <div>
        <div class="row text-h6 bg-primary text-white q-pl-sm">
          {{ $t('About') }} {{ productName }}
          <q-chip dense color="orange" text-color="white">
            {{ version }}
          </q-chip>
          <q-separator vertical />
          {{ srvName }}
          <q-chip dense color="purple" text-color="white">
            {{ srvVersion }}
          </q-chip>
        </div>
      </div>
      <q-separator />
      <div />
      <div class="text-h6 q-pa-sm">
        {{
          $t(
            "It's a tool to get information from sage,to save your time, and increase productivity."
          )
        }}
      </div>
      <div class="row">
        <ul>
          <div class="text-bold">Powered by:</div>
          <li v-for="(ver, dep) in dependencies" :key="dep" class="">
            {{ dep }} : {{ ver }}
          </li>
        </ul>
        <ul>
          <div class="text-bold">Powered by:</div>
          <li v-for="dep in srvProjectDependencies" :key="dep" class="">
            {{ dep.artifactId }} : {{ dep.version }}
          </li>
        </ul>
      </div>
    </q-card>
  </q-page>
</template>

<script>
import { defineComponent, ref } from 'vue'
import { useQuasar } from 'quasar'
import { axios } from 'boot/axios'
import _remove from 'lodash/remove'

export default defineComponent({
  name: 'About',
  setup() {
    const $q = useQuasar()
    const srvName = ref('')
    const srvVersion = ref('')
    const srvProjectDependencies = ref([])

    axios
      .get('/Data/SrvInfo')
      .then((response) => {
        srvName.value = response.data.name
        srvVersion.value = response.data.version
      })
      .finally(() => {
        $q.loadingBar.stop()
      })

    axios
      .get('/Data/SrvProjectDependencies')
      .then((response) => {
        _remove(response.data, function (dep) {
          return dep.groupId === 'SAP'
        })
        srvProjectDependencies.value = response.data
      })
      .finally(() => {
        $q.loadingBar.stop()
      })

    return {
      productName: require('@/../package.json').productName,
      version: require('@/../package.json').version,
      author: require('@/../package.json').author,
      dependencies: require('@/../package.json').dependencies,
      srvProjectDependencies,
      srvName: srvName,
      srvVersion: srvVersion
    }
  }
})
</script>
