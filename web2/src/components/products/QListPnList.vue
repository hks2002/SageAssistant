<template>
  <q-list>
    <q-item dense class="q-px-sm q-pt-none" v-if="pnRoot">
      <q-item-label
        class="text-teal"
        style="font-weight: bolder; font-size: 20px"
        >Part Number Info
      </q-item-label>
    </q-item>
    <template v-for="Pn in pnsInFamily" :key="Pn.ROWID">
      <q-item dese class="q-px-sm q-pt-none">
        <q-item-section :class="labClass(Pn.Status)" style="padding: 0px">
          <q-item-label style="font-weight: bolder; font-size: 20px">{{
            Pn.PN
          }}</q-item-label>
          <q-item-label v-if="Pn.Desc1">{{ Pn.Desc1 }}</q-item-label>
          <q-item-label v-if="Pn.Desc2">{{ Pn.Desc2 }}</q-item-label>
          <q-item-label v-if="Pn.Desc3">{{ Pn.Desc3 }}</q-item-label>
          <QItemLabelFileList :pn="Pn.PN" />
        </q-item-section>
        <q-item-section :class="labClass(Pn.Status)" side top>
          <q-item-label style="font-size: 18px">{{
            Pn.CreateDate
          }}</q-item-label>
          <q-item-label style="font-size: 18px">{{ Pn.Cat }}</q-item-label>
          <q-item-label>
            <q-badge color="purple" v-if="Pn.Status === 1"
              >Active</q-badge
            > </q-item-label
          ><q-item-label v-if="Pn.Comment">{{ Pn.Comment }}</q-item-label>
        </q-item-section>
      </q-item>
    </template>
    <q-inner-loading :showing="showLoading">
      <q-spinner-ios size="50px" color="primary" />
    </q-inner-loading>
  </q-list>
</template>

<script setup>
import { axios } from '@/assets/axios'
import { notifyError } from '@/assets/common'
import QItemLabelFileList from '@/components/products/QItemLabelFileList'
import { onMounted, ref, watch } from 'vue'

const props = defineProps({
  pnRoot: String
})

const pnsInFamily = ref([])
const showLoading = ref(false)

const labClass = (status) => {
  if (status === 1) {
    return 'text-green-10'
  } else {
    return 'text-grey'
  }
}

const doUpdate = (pnRoot) => {
  showLoading.value = true

  axios
    .get('/Data/PNsInFamily?PnRoot=' + pnRoot)
    .then((response) => {
      pnsInFamily.value = response.data
    })
    .catch(() => {
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
</script>
