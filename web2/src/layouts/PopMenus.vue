<!--
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-25 11:01:23
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-05-28 23:02:22
 * @FilePath       : \web2\src\layouts\PopMenus.vue
 * @CopyRight      : Dedienne Aerospace China ZhuHai
-->
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
import ebus from '@/assets/ebus'
import { useQuasar } from 'quasar'
import { onBeforeUnmount } from 'vue'
import { useRoute, useRouter } from 'vue-router'

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
