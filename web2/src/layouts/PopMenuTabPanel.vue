<!--  -->
<template>
  <q-tab-panel
    name=""
    class="q-pa-none"
  >
    <q-list>
      <div
        v-for="(index) in []"
        :key="index"
        @click="()=>{}"
      >
        <q-item
          class="cursor-pointer q-ma-xs"
          :disable="false"
        >
          <q-item-section avatar>
            <q-avatar />
          </q-item-section>
          <q-item-section class="q-gutter-xs">
            <q-item-label :lines="1">
              {{ }}
            </q-item-label>
            <q-item-label caption>
              {{

              }}
            </q-item-label>
          </q-item-section>
        </q-item>
        <q-separator
          v-if="index < 5"
          inset="item"
        />
      </div>
    </q-list>
  </q-tab-panel>
</template>

<script>
import { defineComponent, ref, onBeforeUnmount } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import ebus from 'src/boot/ebus'
import { useQuasar } from 'quasar'
import { axios } from 'boot/axios'

export default defineComponent({
  name: '',
  setup() {
    const q = useQuasar()
    const { route } = useRoute()
    const { router } = useRouter()
    q.loadingBar.stop()

    // event handing
    ebus.on('toggleLeftDrawer', toggleLeftDrawer)
    onBeforeUnmount(() => {
      ebus.off('toggleLeftDrawer', toggleLeftDrawer)
    })

    // return them to vue template
    return {}
  }
})
</script>
<style lang='scss' scoped>
</style>
