<template>
  <div>
    <q-tabs-route-tab-todo-list />
    <q-item-echart-tobe-receive site="ZHU"
                                style="padding:0px; height:800px;" />

  </div>
</template>

<script>
import QTabsRouteTabTodoList from 'components/QTabsRouteTabTodoList'
import QItemEchartTobeReceive from 'components/QItemEchartTobeReceive'
import { getCookie } from '../js/CookieTool.js'

export default {
  name: 'TodoTobeReceive',
  components: {
    QTabsRouteTabTodoList,
    QItemEchartTobeReceive
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
