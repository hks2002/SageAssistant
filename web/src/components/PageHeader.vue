<template>
  <q-header elevated>
    <q-toolbar style="min-height: 30px;">
      <q-select v-model="site"
                style="font-family:consolas; font-size:30px"
                @input="setCookieSite"
                :options="siteList"></q-select>
      <q-btn flat
             round
             @click="handelClick"
             to="/"
             aria-label="Home"
             class="q-mr-sm">
        <q-avatar>
          <img src="~assets/logo.svg" />
        </q-avatar>
      </q-btn>
      <q-toolbar-title class="q-pa-xs">
        <q-btn flat
               dense
               @click="handelClick"
               to="/Todo"
               label="Todo"
               size="21px"
               text-color="white"
               class="q-mr-sm" />
        <q-btn flat
               dense
               @click="handelClick"
               to="/Products"
               label="Products"
               size="21px"
               text-color="white"
               class="q-mr-sm" />
        <q-btn flat
               dense
               @click="handelClick"
               to="/Reports"
               label="Reports"
               size="21px"
               text-color="white"
               class="q-mr-xs" />
        <q-btn flat
               dense
               @click="handelClick"
               to="/About"
               label="About"
               size="21px"
               text-color="white"
               class="q-mr-xs" />
      </q-toolbar-title>
      <q-space />
      <div></div>
    </q-toolbar>

  </q-header>
</template>

<script>
import { setCookie, getCookie } from '../js/CookieTool.js'
import { date } from 'quasar'
export default {
  name: 'PageHeader',
  data () {
    return {
      noformattedString: this.formatedNow(),
      activePage: 'Home',
      site: 'ZHU',
      siteList: ['ZHU', 'HKG', 'TLS', 'SGP', 'MIA']
    }
  },
  methods: {
    formatedNow () {
      let timeStamp = Date.now()
      this.noformattedString = date.formatDate(timeStamp, 'YYYY-MM-DD ddd HH:mm:ss')
    },
    setCookieSite () {
      setCookie('site', this.site, 1000)
    },
    handelClick (evt) {
      this.activePage = evt.target.innerText

      // Todo, if have new way to do it, please change it
      if (this.activePage === '') {
        let allNodes = evt.target.parentNode.parentNode.parentNode.parentNode.parentNode.nextSibling.childNodes
        for (let i = 0; i < allNodes.length; i++) {
          allNodes[i].style = 'background-color:primary; font-size:21px'
        }
      } else {
        let allNodes = evt.target.parentNode.parentNode.parentNode.parentNode.childNodes
        for (let i = 0; i < allNodes.length; i++) {
          allNodes[i].style = 'background-color:primary; font-size:21px'
        }
        evt.target.parentNode.parentNode.parentNode.style = 'background-color:orange; font-size:21px'
      }
    }
  },
  mounted () {
    // setInterval(this.formatedNow, 1000)

    // update site if have cookie
    let cookieSite = getCookie('site')
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
.q-btn
  text-transform: capitalize;

.q-select>.q-field__inner>.q-field__control>.q-field__control-container>.q-field__native>span
  color: white;

</style>
