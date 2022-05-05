<!--  -->
<template>
  <q-menu
    :offset="[0, 36]"
    transition-show="jump-down"
    transition-hide="jump-up"
    content-style="width: 400px;"
    max-height="800px"
  >
    <q-card class="full-width">
      <q-tabs
        class="text-grey"
        active-color="primary"
        indicator-color="primary"
        align="justify"
        narrow-indicator
      >
        <q-tab v-if="true" name="Infos">
          <span>Infos({{}})</span>
        </q-tab>
      </q-tabs>
      <q-separator />
      <q-tab-panels animated />
    </q-card>
  </q-menu>
</template>

<script setup>
import { onBeforeUnmount } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import ebus from 'src/boot/ebus'
import { useQuasar } from 'quasar'
import { axios } from 'boot/axios'

const q = useQuasar()
const { route } = useRoute()
const { router } = useRouter()
q.loadingBar.stop()

// event handing
ebus.on('toggleLeftDrawer', toggleLeftDrawer)
onBeforeUnmount(() => {
  bus.off('toggleLeftDrawer', toggleLeftDrawer)
})
</script>
<style lang="scss" scoped></style>
