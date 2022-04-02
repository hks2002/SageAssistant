<template>
  <q-list>
    <q-item v-if="pnRoot">
      <q-item-section>
        <q-item-label
          class="text-teal "
          style="font-weight:bolder; font-size:30px"
        >Part Number Info
        </q-item-label>
      </q-item-section>
    </q-item>
    <template
      v-for="Pn in pnsInFamily"
      :key="Pn.ROWID"
    >
      <q-item>
        <q-item-section
          :class="labClass(Pn.Status)"
          style="padding:0px"
        >
          <q-item-label style="font-weight:bolder; font-size:25px">{{Pn.PN}}</q-item-label>
          <q-item-label v-if="Pn.Desc1">{{Pn.Desc1}}</q-item-label>
          <q-item-label v-if="Pn.Desc2">{{Pn.Desc2}}</q-item-label>
          <q-item-label v-if="Pn.Desc3">{{Pn.Desc3}}</q-item-label>
          <q-item-label v-if="Pn.Comment">{{Pn.Comment}}</q-item-label>
          <q-item-label-file-list :pn="Pn.PN" />
        </q-item-section>
        <q-item-section
          :class="labClass(Pn.Status)"
          side
          top
        >
          <q-item-label style="font-size:18px">{{Pn.CreateDate}}</q-item-label>
          <q-item-label style="font-size:18px">{{Pn.Cat}}</q-item-label>
          <q-item-label>
            <q-badge
              color="purple"
              v-if="Pn.Status === 1"
            >Active</q-badge>
          </q-item-label>
        </q-item-section>
      </q-item>
    </template>
    <q-inner-loading :showing="showLoading">
      <q-spinner-ios
        size="50px"
        color="primary"
      />
    </q-inner-loading>
  </q-list>
</template>

<script>
import { defineComponent, ref, onMounted, watch } from 'vue'
import { notifyError } from 'assets/common'
import { axios } from 'boot/axios'

import QItemLabelFileList from 'components/products/QItemLabelFileList'

export default defineComponent({
  name: 'QListPnList',

  components: {
    QItemLabelFileList
  },

  props: {
    pnRoot: String
  },
  setup(props, ctx) {
    const pnsInFamily = ref([])
    const showLoading = ref(false)

    const labClass = (status) => {
      if (status === 1) {
        return 'text-green-10'
      } else {
        return 'text-grey'
      }
    }

    const btnColor = (status) => {
      if (status === 1) {
        return '#4caf50'
      } else {
        return '#858c85'
      }
    }
    const doUpdate = (pnRoot) => {
      showLoading.value = true

      axios
        .get('/Data/PNsInFamily?PnRoot=' + pnRoot)
        .then((response) => {
          pnsInFamily.value = response.data
        })
        .catch((e) => {
          notifyError('Loading PN Failed!')
        })
        .finally(() => {
          showLoading.value = false
        })
    }

    onMounted(() => {
      console.debug('onMounted')
      if (props.pnRoot) {
        doUpdate(props.pnRoot)
      }
    })

    // Don't use watchEffect, it run before Mounted.
    watch(
      () => props.pnRoot,
      (newVal, oldVal) => {
        console.debug('watch:' + oldVal + ' ---> ' + newVal)
        if (newVal) {
          doUpdate(newVal)
        }
      }
    )

    return {
      pnsInFamily,
      labClass,
      btnColor,
      showLoading
    }
  }
})
</script>
