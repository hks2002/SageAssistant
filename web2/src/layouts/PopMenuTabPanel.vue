<!--
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-25 11:01:23
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-05-28 23:02:10
 * @FilePath       : \web2\src\layouts\PopMenuTabPanel.vue
 * @CopyRight      : Dedienne Aerospace China ZhuHai
-->
<template>
  <q-tab-panel name="" class="q-pa-none">
    <q-list>
      <div v-for="index in []" :key="index" @click="() => {}">
        <q-item class="cursor-pointer q-ma-xs" :disable="false">
          <q-item-section avatar>
            <q-avatar />
          </q-item-section>
          <q-item-section class="q-gutter-xs">
            <q-item-label :lines="1"> {{}} </q-item-label>
            <q-item-label caption> {{}} </q-item-label>
          </q-item-section>
        </q-item>
        <q-separator v-if="index < 5" inset="item" />
      </div>
    </q-list>
  </q-tab-panel>
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
  ebus.off('toggleLeftDrawer', toggleLeftDrawer)
})
</script>
<style lang="scss" scoped></style>
