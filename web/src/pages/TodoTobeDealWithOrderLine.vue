<template>
  <div>
    <q-tabs-route-tab-todo-list />
    <q-item-echart-tobe-deal-with-order-line :site="site"
                                             style="padding:0px; height:600px;" />
  </div>
</template>

<script>
import QTabsRouteTabTodoList from 'components/QTabsRouteTabTodoList'
import QItemEchartTobeDealWithOrderLine from 'components/QItemEchartTobeDealWithOrderLine'
import { getCookie } from '../js/CookieTool.js'

export default {
  name: 'TodoTobeDealWithOrderLine',
  components: {
    QTabsRouteTabTodoList,
    QItemEchartTobeDealWithOrderLine
  },
  data () {
    return {
      site: getCookie('site')
    }
  },
  methods: {
    updateSite () {
      const site = getCookie('site')
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
