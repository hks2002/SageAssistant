<template>
  <!-- set height and width in parent -->
  <q-markup-table dense>
    <thead style="position: sticky; top: 0px; z-index: 1">
      <tr>
        <td :colspan="colspan" class="bg-teal text-h6 text-white shadow-2">
          AccountNO: {{ accountNO }} Balance of {{ year }}
          <q-btn dense flat icon="fas fa-download" @click="download()" />
        </td>
      </tr>
      <tr class="bg-primary text-white">
        <th class="text-center text-caption">#</th>
        <th class="text-left">Site</th>
        <th class="text-left">AccountNO</th>
        <th class="text-left">Currency</th>
        <template v-for="month in 13" :key="month">
          <template v-if="showBalance">
            <th class="text-right">B{{ month - 1 }}</th>
          </template>
        </template>
        <template v-for="month in 13" :key="month">
          <template v-if="showCredit">
            <th class="text-right">C{{ month - 1 }}</th>
          </template>
        </template>
        <template v-for="month in 13" :key="month">
          <template v-if="showDebit">
            <th class="text-right">D{{ month - 1 }}</th>
          </template>
        </template>
        <template v-for="month in 13" :key="month">
          <template v-if="showMovement">
            <th class="text-right">M{{ month - 1 }}</th>
          </template>
        </template>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(item, index) in balanceItems" :key="index">
        <td class="text-center">{{ index }}</td>
        <td>{{ item['Site'] }}</td>
        <td class="text-left">{{ item['AccountNO'] }}</td>
        <td class="text-left">{{ item['Currency'] }}</td>
        <template v-for="month in 13" :key="month">
          <template v-if="showBalance">
            <td class="text-right">{{ item['B' + (month - 1)] }}</td>
          </template>
        </template>
        <template v-for="month in 13" :key="month">
          <template v-if="showCredit">
            <td class="text-right">{{ item['C' + (month - 1)] }}</td>
          </template>
        </template>
        <template v-for="month in 13" :key="month">
          <template v-if="showDebit">
            <td class="text-right">{{ item['D' + (month - 1)] }}</td>
          </template>
        </template>
        <template v-for="month in 13" :key="month">
          <template v-if="showMovement">
            <td class="text-right">{{ item['M' + (month - 1)] }}</td>
          </template>
        </template>
      </tr>
    </tbody>
    <q-inner-loading :showing="showLoading">
      <q-spinner-hourglass size="50px" color="primary" />
    </q-inner-loading>
  </q-markup-table>
</template>

<script setup>
import { onMounted, ref, watch } from 'vue'
import { notifyError } from 'assets/common'
import { axios } from 'boot/axios'
import { jsonToExcel } from 'assets/dataUtils'

const props = defineProps({
  accountNO: { type: String, require: true, default: '' },
  year: { type: String, require: true, default: '' },
  site: { type: String, require: true, default: '' },
  showCredit: { type: Boolean, require: false, default: true },
  showDebit: { type: Boolean, require: false, default: true },
  showMovement: { type: Boolean, require: false, default: true },
  showBalance: { type: Boolean, require: false, default: true }
})

const balanceItems = ref([])
const showLoading = ref(false)
const colspan = ref(56)

const doUpdate = () => {
  showLoading.value = true
  colspan.value = 4
  colspan.value = props.showCredit ? colspan.value + 13 : colspan.value
  colspan.value = props.showDebit ? colspan.value + 13 : colspan.value
  colspan.value = props.showMovement ? colspan.value + 13 : colspan.value
  colspan.value = props.showBalance ? colspan.value + 13 : colspan.value

  axios
    .get(
      '/Data/FinancialBalance' +
        '?Site=' +
        props.site +
        '&AccountNO=' +
        props.accountNO +
        '&Year=' +
        props.year
    )
    .then((response) => {
      balanceItems.value = response.data
    })
    .catch((e) => {
      console.error(e)
      notifyError('Loading Balance Failed!')
    })
    .finally(() => {
      showLoading.value = false
    })
}
const download = () => {
  const header = ['Site', 'AccountNO', 'Currency']
  for (idx = 0; idx <= 12; idx++) {
    if (props.showBalance) {
      header.push('B' + idx)
    }
    if (props.showCredit) {
      header.push('C' + idx)
    }
    if (props.showDebit) {
      header.push('D' + idx)
    }
    if (props.showMovement) {
      header.push('M' + idx)
    }
  }

  jsonToExcel(
    header,
    balanceItems.value,
    'AccountNO:' + props.accountNO + ' Balance of ' + props.year
  )
}

onMounted(() => {
  console.debug('onMounted Balance')
  if (props.accountNO && props.year && props.site) {
    doUpdate()
  }
})

// Don't use watchEffect, it run before Mounted.
watch(
  () => [props.accountNO, props.year, props.site],
  (...newAndold) => {
    // newAndold[1]:old
    // newAndold[0]:new
    console.debug('watch:' + newAndold[1] + ' ---> ' + newAndold[0])
    doUpdate()
  }
)
</script>
