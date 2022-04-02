<!--  -->
<template>
  <q-footer
    class="footer"
    :style="'height:' + height +'px'"
  >
    <q-bar class="fit flex-center footer">
      <div>
        <span class="q-pa-ms">{{ $t("Developed by Dedienne China") }} </span>
      </div>
    </q-bar>
  </q-footer>
</template>

<script>
import { defineComponent, onMounted } from 'vue'

// import axios from 'axios'

export default defineComponent({
  name: 'PageFooter',
  props: {
    height: {
      type: Number,
      required: false,
      default: 18
    }
  },
  setup() {
    onMounted(() => {
      console.debug('onMounted PageFooter')
    })
    return {}
  }
})
</script>
<style lang="scss" scoped>
.footer {
  background-color: grey;
}
.footer div {
  color: white;
  font-size: 10px;
}
</style>
