<template>
  <div class="flex justify-center">
    <router-view />
  </div>
</template>

<script>
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'DefaultLayout',
  setup() {}
})
</script>
