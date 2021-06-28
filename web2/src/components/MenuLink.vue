<template>
  <q-separator v-if="split" />
  <q-item
    v-if="enable"
    clickable
    :to="link"
    class="menu-link"
  >
    <q-item-section
      v-if="icon"
      class="q-pa-sm"
      avatar
    >
      <q-icon
        :name="icon"
        color="primary"
        class="mini-icon"
      />
    </q-item-section>

    <q-item-section>
      <q-item-label class="text-primary text-bold">
        {{ title }}
      </q-item-label>
      <q-item-label
        class="text-primary"
        caption
      >
        {{ caption }}
      </q-item-label>
    </q-item-section>
  </q-item>
</template>

<script>
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'MenuLink',
  props: {
    title: {
      type: String,
      required: true
    },

    caption: {
      type: String,
      default: ''
    },

    link: {
      type: String,
      default: '#'
    },

    icon: {
      type: String,
      default: ''
    },

    split: {
      type: Boolean,
      default: false
    },

    enable: {
      type: Boolean,
      default: false
    }
  }
})
</script>
<style lang="scss" scoped>
.menu-link {
  padding: 0;
}
.q-item__section--avatar {
  min-width: 50px;
}
</style>
