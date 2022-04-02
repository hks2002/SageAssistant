<template>
  <q-page
    class="flex flex-center"
    style="height:100%"
  >
    <login-Main-vue />
  </q-page>
</template>

<script>
import { defineComponent } from 'vue'
import LoginMainVue from 'src/components/login/LoginMain.vue'

export default defineComponent({
  name: 'Login',
  components: {
    LoginMainVue
  },
  setup(props, ctx) {}
})
</script>
