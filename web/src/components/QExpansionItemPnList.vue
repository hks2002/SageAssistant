<template>
  <q-list>
    <q-item v-if="pnsInFamily.length>0">
      <q-item-section :class="labClass(pnsInFamily[0].Status)">
        <q-item-label style="font-weight:bolder; font-size:25px">
          {{pnsInFamily[0].PN}}
        </q-item-label>
        <q-item-label>{{pnsInFamily[0].Desc1}}</q-item-label>
        <q-item-label>{{pnsInFamily[0].Desc2}}</q-item-label>
        <q-item-label>{{pnsInFamily[0].Desc3}}</q-item-label>
        <q-item-label-file-list :pn="pnsInFamily[0].PN" />
      </q-item-section>
      <q-item-section v-if="pnsInFamily[0]"
                      :class="labClass(pnsInFamily[0].Status)"
                      side
                      top>
        <q-item-label style="font-size:18px">{{pnsInFamily[0].CreateDate}}</q-item-label>
        <q-item-label style="font-size:18px">{{pnsInFamily[0].Cat}}</q-item-label>
        <q-item-label>
          <q-badge color="purple"
                   v-if="pnsInFamily[0].Status === 1">Active</q-badge>
        </q-item-label>
      </q-item-section>
   </q-item>
  <q-expansion-item v-if="pnsInFamily.length>1"
                    expand-separator
                    class="relative-position">
    <!-- template here is the v-slot end -->
    <!-- template here is the expand item start -->
    <template v-for="(Pn,index) in pnsInFamily">
      <q-item v-if="index>0"
              :key="Pn.ROWID">
        <q-item-section :class="labClass(Pn.Status)"
                        style="padding:0px">
          <q-item-label style="font-weight:bolder; font-size:25px">{{Pn.PN}}</q-item-label>
          <q-item-label>{{Pn.Desc1}}</q-item-label>
          <q-item-label>{{Pn.Desc2}}</q-item-label>
          <q-item-label>{{Pn.Desc3}}</q-item-label>
          <q-item-label-file-list :pn="Pn.PN" />
        </q-item-section>
        <q-item-section :class="labClass(Pn.Status)"
                        side
                        top>
          <q-item-label style="font-size:18px">{{Pn.CreateDate}}</q-item-label>
          <q-item-label style="font-size:18px">{{Pn.Cat}}</q-item-label>
          <q-item-label>
            <q-badge color="purple"
                     v-if="Pn.Status === 1">Active</q-badge>
          </q-item-label>
        </q-item-section>
      </q-item>
    </template>
  </q-expansion-item>
  <q-inner-loading :showing="showLoadingPN">
    <q-spinner-ios size="50px"
                    color="primary" />
  </q-inner-loading>
  </q-list>
</template>

<script>
import QItemLabelFileList from 'components/QItemLabelFileList'

if (process.env.DEV) {
  require('../mock/PNsInFamily')
}
export default {
  name: 'QExpansionItemPnList',
  components: {
    QItemLabelFileList
  },
  props: {
    pnRoot: String
  },
  data () {
    return {
      pnsInFamily: [],
      showLoadingPN: false
    }
  },
  methods: {
    labClass (status) {
      if (status === 1) {
        return 'text-green-10'
      } else {
        return 'text-grey'
      }
    },
    btnColor (status) {
      if (status === 1) {
        return '#4caf50'
      } else {
        return '#858c85'
      }
    },
    doUpdate (pnRoot) {
      this.showLoadingPN = true

      this.$axios.get('/Data/PNsInFamily?PnRoot=' + pnRoot)
        .then((response) => {
          console.debug('[axios] ' + response.status + ' ' + response.statusText + ' ' + response.config.url)
          console.debug(JSON.stringify(response.data))

          this.pnsInFamily = response.data
          this.showLoadingPN = false
        })
        .catch((e) => {
          this.showLoadingPN = false

          this.$q.notify({
            color: 'negative',
            position: 'top',
            message: 'Loading PN Failed!',
            icon: 'fas fa-exclamation-triangle'
          })
        })
    },
    doReset () {
      this.pnsInFamily = []
      this.showLoadingPN = false
    }
  },
  watch: {
    pnRoot: {
      handler (newVal, oldVal) {
        console.debug(oldVal + ' --> ' + newVal)

        if (newVal) {
          this.doUpdate(newVal)
        } else {
          this.doReset()
        }
      },
      immediate: true
    }
  },
  mounted: function () {
  }
}
</script>
