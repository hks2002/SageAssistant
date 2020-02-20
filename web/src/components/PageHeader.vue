<template>
  <q-header elevated>
    <q-toolbar style="min-height: 30px;">
      <q-btn flat
             round
             dense
             @click="handelClick"
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
               @click="handelClick"
               to="/OpenSaleItems"
               label="Status"
               text-color="white"
               class="q-mr-sm" />
        <q-btn flat
               dense
               @click="handelClick"
               to="/Reports"
               label="Reports"
               text-color="white"
               class="q-mr-sm" />
        <q-btn flat
               dense
               @click="handelClick"
               to="/About"
               label="About"
               :style="como"
               text-color="white"
               class="q-mr-sm" />
      </q-toolbar-title>
      <q-space />
      <div></div>
    </q-toolbar>

  </q-header>
</template>

<script>
import { date } from 'quasar'
export default {
  name: 'PageHeader',
  data () {
    return {
      noformattedString: this.formatedNow(),
      activePage: 'Home'
    }
  },
  methods: {
    formatedNow () {
      let timeStamp = Date.now()
      this.noformattedString = date.formatDate(timeStamp, 'YYYY-MM-DD ddd HH:mm:ss')
    },
    handelClick (evt) {
      this.activePage = evt.target.innerText

      // Todo, if have new way to do it, please change it
      if (this.activePage === '') {
        let allNodes = evt.target.parentNode.parentNode.parentNode.parentNode.parentNode.nextSibling.childNodes
        for (let i = 0; i < allNodes.length; i++) {
          allNodes[i].style = 'background-color:primary'
        }
      } else {
        let allNodes = evt.target.parentNode.parentNode.parentNode.parentNode.childNodes
        for (let i = 0; i < allNodes.length; i++) {
          allNodes[i].style = 'background-color:primary'
        }
        evt.target.parentNode.parentNode.parentNode.style = 'background-color:orange'
      }
    }
  },
  mounted () {
    // setInterval(this.formatedNow, 1000)
  },
  computed: {

  }
}
</script>

<style scoped>
.q-btn {
  text-transform: capitalize;
}
</style>
