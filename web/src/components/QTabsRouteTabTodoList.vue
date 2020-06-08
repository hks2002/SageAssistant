<template>
  <q-tabs dense
          class="bg-orange text-white shadow-2">
    <q-route-tab label="Delivery"
                 to="/Todo/Delivery"
                 exact
                 append />
    <q-route-tab label="Receive"
                 to="/Todo/Receive"
                 exact
                 append />
    <q-route-tab label="NewOrder"
                 to="/Todo/NewOrder"
                 exact
                 append />
    <q-route-tab label="NewBom"
                 to="/Todo/NewBom"
                 exact
                 append />
  </q-tabs>
</template>

<script>
export default {
  name: 'QTabsRouteTabTodoList',
  data () {
    return {
    }
  }
}
</script>
