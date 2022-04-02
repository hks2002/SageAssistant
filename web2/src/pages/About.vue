<template>
  <q-page class="flex flex-center">
    <q-card-about-vue />
  </q-page>
</template>

<script>
import { defineComponent } from 'vue'
import QCardAboutVue from 'src/components/about/QCardAbout.vue'

export default defineComponent({
  name: 'About',
  components: {
    QCardAboutVue
  },
  setup() {}
})
</script>
