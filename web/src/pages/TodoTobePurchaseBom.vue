<template>
  <div>
    <q-tabs-route-tab-todo-list />
    <q-item-echart-tobe-purchase-bom :site="site"
                                     style="padding:0px; height:800px;" />
  </div>
</template>

<script>
import QTabsRouteTabTodoList from 'components/QTabsRouteTabTodoList'
import QItemEchartTobePurchaseBom from 'components/QItemEchartTobePurchaseBom'
import { getCookie } from '../js/CookieTool.js'

export default {
  name: 'TodoTobePurchaseBom',
  components: {
    QTabsRouteTabTodoList,
    QItemEchartTobePurchaseBom
  },
  data () {
    return {
      site: getCookie('site')
    }
  },
  methods: {
    updateSite () {
      let site = getCookie('site')
      if (!site) {
        this.site = 'ZHU'
      } else if (site !== this.site) {
        console.debug(this.site + '--->' + site)
        this.site = site
      }
    }
  },
  mounted () {
    setInterval(this.updateSite, 1000)
  }
}
</script>
