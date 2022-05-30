<!--
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-05-26 14:52:28
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-05-30 11:57:17
 * @FilePath       : \web2\src\layouts\MainTabs.vue
 * @CopyRight      : Dedienne Aerospace China ZhuHai
-->
<template>
  <q-tabs
    dense
    shrink
    no-caps
    narrow-indicator
    mobile-arrows
    align="left"
    class="text-grey"
    active-color="primary"
    indicator-color="primary"
    v-model="activeId"
    @update:model-value="onChangeTab"
  >
    <q-tab v-for="tab in tabs" :name="tab.id" :label="tab.label" :key="tab.id">
      <span class="btn-close" @click.stop="closeTab(tab.id)">&times;</span>
    </q-tab>
  </q-tabs>

  <!-- here use height style, it could keep tab header shown always -->
  <q-tab-panels
    animated
    v-model="activeId"
    :style="{
      height: tabHeight - tabHeaderHeight + 'px'
    }"
  >
    <q-tab-panel
      keep-alive
      class="q-pa-none"
      v-for="tab in tabs"
      :key="tab.id"
      :name="tab.id"
    >
      <!-- here use min-height style, it override the default <q-page> min-height, which same to <page-container> -->
      <!-- it could let scroll bar only in tab panel -->
      <component
        :is="ComponentMap[tab.name]"
        :pageHeight="tabHeight - tabHeaderHeight"
        :style="{
          minHeight: tabHeight - tabHeaderHeight + 'px'
        }"
      ></component>
    </q-tab-panel>
  </q-tab-panels>
</template>

<script setup>
import Home from '@/pages/HomePage.vue'
import { usePagesStore } from '@/stores/pageManager'
import { defineAsyncComponent, onMounted, ref } from 'vue'
import { useRouter } from 'vue-router'

/* eslint-disable */
const props = defineProps({
  tabHeight: { type: Number, default: 600 },
  tabHeaderHeight: { type: Number, default: 36 /* dense */ }
})

// common vars
const $router = useRouter()
const pagesStore = usePagesStore()

const tabs = ref(pagesStore.pages)
const activeId = ref(pagesStore.activeId || 'Home')
const activeName = ref(pagesStore.activeName || 'Home')
const ComponentMap = { Home: Home }

// actions
const importPage = (tabName) => {
  // name could have space, but template page file name doesn't have, so remove space
  const tempLatePage = tabName.replaceAll(' ', '') + 'Page'

  // Fully dynamic statements, such as import(foo), will fail.
  // Because webpack requires at least some file location information.
  // This is because foo could potentially be any path to any file in your system or project.
  // The import() must contain at least some information about where the module is located,
  // so bundling can be limited to a specific directory or set of files.
  if (!ComponentMap.hasOwnProperty(tabName)) {
    ComponentMap[activeName.value] = defineAsyncComponent(() =>
      import(`/src/pages/${tempLatePage}.vue`)
    )
  }
}

const closeTab = (tabId) => {
  if (pagesStore.pages.length > 1) {
    // aways left one page
    pagesStore.removePage(tabId)
    $router.push({
      name: pagesStore.activeName,
      query: pagesStore.activeQuery,
      params: pagesStore.activeParams
    })
  }
}

// events
onMounted(() => {
  pagesStore.setActivePage('Home')
  $router.push({
    name: pagesStore.activeName,
    query: pagesStore.activeQuery,
    params: pagesStore.activeParams
  })
})

const onChangeTab = (tabId) => {
  pagesStore.setActivePage(tabId)
  $router.push({
    name: pagesStore.activeName,
    query: pagesStore.activeQuery,
    params: pagesStore.activeParams
  })
}

pagesStore.$subscribe(() => {
  // update ui if any changes occurus
  // changes include: addTab, removeTab, resetTab, updateLabel, setActiveTab
  // changes exclude: changeTab
  tabs.value = pagesStore.pages
  activeId.value = pagesStore.activeId
  activeName.value = pagesStore.activeName
  document.title =
    require('app/package.json').productName + ' - ' + pagesStore.activeLabel
  importPage(activeName.value)
})
</script>

<style lang="scss" scoped>
.btn-close {
  position: absolute;
  display: inline-block;
  top: 6px;
  right: -12px;
  line-height: 12px;
  width: 14px;
  height: 14px;
  border-radius: 14px;
  font-size: 12px;
  transition: all 0.2s ease;

  &:hover {
    color: #333;
    transform: scale(1.5);
    cursor: pointer;
  }
}
</style>
