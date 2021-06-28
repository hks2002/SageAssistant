<!--  -->
<template>
  <q-footer class="footer">
    <q-bar class="fit flex-center footer">
      <div>
        <span class="q-pa-ms">{{ $t("Developed by Dedienne China") }} </span>
      </div>
    </q-bar>
  </q-footer>
</template>

<script>
import { defineComponent } from 'vue'
// import axios from 'axios'

export default defineComponent({
  name: 'PageFooter',
  setup() {
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
  font-size: 10px
}
</style>
