<template>
  <q-layout view="lHh Lpr lFf">
    <PageHeader />

    <q-page-container class="fit">
      <router-view />
    </q-page-container>

    <PageFooter />
  </q-layout>
</template>

<script>
import PageHeader from 'components/PageHeader'
import PageFooter from 'components/PageFooter'

export default {
  name: 'MyLayout',

  data () {
    return {
    }
  },
  components: { PageHeader, PageFooter }
}
</script>
