<template>
  <q-header elevated>
    <q-toolbar style="min-height: 30px;">
      <q-select v-model="site"
                style="font-family:consolas; font-size:30px"
                @input="setCookieSite"
                :options="siteList"></q-select>
      <q-btn flat
             round
             to="/"
             aria-label="Home"
             class="q-mr-sm">
        <q-avatar>
          <img src="~assets/logo.svg" />
        </q-avatar>
      </q-btn>
      <q-tabs class="q-pa-xs bg-primary text-white"
              v-model="activePage">
        <q-route-tab
               align="left"
               to="/Todo"
               label="Todo"/>
        <q-route-tab
               align="left"
               to="/Products"
               label="Products"/>
        <q-route-tab
               align="left"
               to="/Supplies"
               label="Supplies"/>
        <q-route-tab
               align="left"
               to="/Reports"
               label="Reports"/>
        <q-route-tab
               align="left"
               to="/About"
               label="About"/>
      </q-tabs>
      <q-space />
      <div></div>
    </q-toolbar>

  </q-header>
</template>

<script>
import { setCookie, getCookie } from '../js/CookieTool.js'
import { date } from 'quasar'
if (process.env.DEV) {
  require('../mock/Sites')
}

export default {
  name: 'PageHeader',
  data () {
    return {
      noformattedString: this.formatedNow(),
      activePage: 'Home',
      site: 'ZHU',
      siteList: []
    }
  },
  methods: {
    formatedNow () {
      const timeStamp = Date.now()
      this.noformattedString = date.formatDate(timeStamp, 'YYYY-MM-DD ddd HH:mm:ss')
    },
    setCookieSite () {
      setCookie('site', this.site, 1000)
    }
  },
  mounted () {
    // setInterval(this.formatedNow, 1000)
    this.$axios.get('/Data/Sites')
      .then((response) => {
        console.debug('[axios] ' + response.status + ' ' + response.statusText + ' ' + response.config.url)
        console.debug(JSON.stringify(response.data))

        this.siteList = response.data
      })
      .catch((e) => {
        this.$q.notify({
          color: 'negative',
          position: 'top',
          message: 'Loading Sites Failed!',
          icon: 'fas fa-exclamation-triangle'
        })
      })
    // update site if have cookie
    const cookieSite = getCookie('site')
    if (cookieSite) {
      this.site = cookieSite
    } else {
      this.setCookieSite()
    }
  },
  computed: {

  }
}
</script>

<style lang="sass" >
.q-select>.q-field__inner>.q-field__control>.q-field__control-container>.q-field__native>span
  color: white;

.q-tab__label
  font-size: 24px;
  text-transform: capitalize;
</style>
