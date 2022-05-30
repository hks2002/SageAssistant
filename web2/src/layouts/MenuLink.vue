<!--
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-25 11:01:23
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-05-29 01:23:01
 * @FilePath       : \web2\src\layouts\MenuLink.vue
 * @CopyRight      : Dedienne Aerospace China ZhuHai
-->
<template>
  <div>
    <q-separator v-if="split" />
    <!-- with sub menu items -->
    <q-expansion-item
      v-if="enable && expansion"
      expand-separator
      expand-icon-toggle
      header-class="q-pa-sm"
    >
      <template v-slot:header>
        <q-item-section
          v-if="icon"
          avatar
          class="p-pa-sm"
          @click="showPages(children)"
        >
          <q-icon :name="icon" color="primary" />
        </q-item-section>

        <q-item-section @click="showPages(children)">
          <q-item-label class="text-primary text-bold">
            {{ title }}
          </q-item-label>
          <q-item-label class="text-primary" caption>
            {{ caption }}
          </q-item-label>
        </q-item-section>
      </template>

      <q-item
        v-for="child in children"
        :key="child.path"
        v-bind="child"
        clickable
        @click="showPage(child.name)"
        class="q-pa-sm"
      >
        <q-item-section v-if="child.icon" class="q-pa-sm" avatar>
          <q-icon :name="child.icon" color="primary" />
        </q-item-section>

        <q-item-section>
          <q-item-label class="text-primary text-bold">
            {{ child.title }}
          </q-item-label>
          <q-item-label class="text-primary" caption>
            {{ child.caption }}
          </q-item-label>
        </q-item-section>
      </q-item>
    </q-expansion-item>

    <!-- without sub menu items -->
    <q-item
      v-if="enable && !expansion"
      clickable
      @click="showPage(name)"
      class="q-pa-none q-pl-sm"
    >
      <q-item-section v-if="icon" avatar class="q-pa-none">
        <q-icon :name="icon" color="primary" />
      </q-item-section>

      <q-item-section>
        <q-item-label class="text-primary text-bold">
          {{ title }}
        </q-item-label>
        <q-item-label class="text-primary" caption>
          {{ caption }}
        </q-item-label>
      </q-item-section>
    </q-item>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'

/* eslint-disable */
const props = defineProps({
  title: {
    type: String,
    required: true
  },
  name: {
    type: String,
    required: true
  },
  path: {
    type: String,
    default: '#'
  },
  caption: {
    type: String,
    default: ''
  },
  icon: {
    type: String,
    default: ''
  },
  split: {
    type: Boolean,
    default: false
  },
  expansion: {
    type: Boolean,
    default: false
  },
  children: {
    type: Array,
    default: []
  },

  enable: {
    type: Boolean,
    default: false
  }
})

// common vars
const $router = useRouter()

// actions
const showPage = (name) => {
  $router.push({ name: name })
}

const showPages = async (pages) => {
  for (const idx in pages) {
    await $router.push({ name: pages[idx].name })
  }
}
</script>

<style lang="sass" scoped>
// min-width decide the distance between icon and title
.q-item
  &__section
    &--avatar
      min-width: 36px
</style>
