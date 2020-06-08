<template>
  <div>
    <q-tabs-route-tab-todo-list />
    <q-item-echart-tobe-delivery :site="site"
                                 style="padding:0px; height:800px;" />
  </div>
</template>

<script>
import QTabsRouteTabTodoList from 'components/QTabsRouteTabTodoList'
import QItemEchartTobeDelivery from 'components/QItemEchartTobeDelivery'
import { getCookie } from '../js/CookieTool.js'

export default {
  name: 'TodoTobeDelivery',
  components: {
    QTabsRouteTabTodoList,
    QItemEchartTobeDelivery
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
