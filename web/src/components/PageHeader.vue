<template>
  <q-header elevated>
    <q-toolbar style="min-height: 30px;">
      <q-btn flat
             round
             dense
             to="/"
             aria-label="Home"
             class="q-mr-sm">
        <q-avatar>
          <img src="~assets/logo.svg" />
        </q-avatar>
      </q-btn>
      <q-toolbar-title>
        <q-btn flat
               dense
               to="/OpenSaleItems"
               label="Status"
               color="primary"
               text-color="white"
               class="q-mr-sm" />
        <q-btn flat
               dense
               to="/Reports"
               label="Reports"
               color="primary"
               text-color="white"
               class="q-mr-sm" />
          <q-btn flat
                 dense
                 to="/About"
                 label="About"
                 color="primary"
                 text-color="white"
                 class="q-mr-sm" />
      </q-toolbar-title>
    </q-toolbar>
  </q-header>
</template>

<script>
export default {
  name: 'PageHeader'
}
</script>

<style scoped>
.q-btn {
  text-transform: capitalize;
}
</style>
