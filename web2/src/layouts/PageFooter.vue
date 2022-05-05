<!--  -->
<template>
  <q-footer class="footer" :style="'height:' + height + 'px'">
    <q-bar class="fit flex-center footer">
      <div>
        <span class="q-pa-ms">{{ $t('Developed by Dedienne China') }} </span>
      </div>
    </q-bar>
  </q-footer>
</template>

<script setup>
import { onMounted } from 'vue'

// import axios from 'axios'

const props = defineProps({
  height: {
    type: Number,
    required: false,
    default: 18
  }
})

onMounted(() => {
  console.debug('onMounted PageFooter')
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
