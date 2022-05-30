<!--
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-05-20 12:00:06
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-05-30 01:35:46
 * @FilePath       : \web2\src\components\.controls\QSelectAxios.vue
 * @CopyRight      : Dedienne Aerospace China ZhuHai
-->
<!--
If want to set value to one object prop, set optionValue to the name,
without set it, the model value is an object.

Note: 'multiple' can't with 'fill-input', 'hide-selected'
      'multiple' suggest with 'use-chip'
-->
<template>
  <q-select
    dense
    clearable
    outlined
    use-input
    fill-input
    hide-dropdown-icon
    emit-value
    options-dense
    input-debounce="500"
    v-model="model"
    :options="options"
    :option-label="optionLabel"
    :option-value="optionValue"
    @filter="filterFn"
    @filter-abort="abortFilterFn"
  >
    <template v-slot:no-option>
      <q-item>
        <q-item-section class="text-red"> No results </q-item-section>
      </q-item>
    </template>
  </q-select>
</template>

<script setup>
import { axiosGet } from '@/assets/axiosActions'
import { ref, watch } from 'vue'

const props = defineProps({
  dataUrl: {
    type: String,
    required: true
  },
  optionLabel: {
    type: String,
    required: false
  },
  optionValue: {
    type: String,
    required: false
  },
  minQueryLen: {
    type: Number,
    default: 2,
    required: false
  }
})

const model = ref(null)
const options = ref([])

const filterFn = (inputVal, done, abort) => {
  if (inputVal.length < props.minQueryLen) {
    abort()
    return
  }

  let url = props.dataUrl
  const params = {}

  if (props.optionLabel) {
    params[props.optionLabel] = inputVal.toUpperCase()
  } else {
    url = url + inputVal.toUpperCase()
  }

  axiosGet(url, params).then((data) => {
    options.value = data
    // ref.moveOptionSelection(1, true)
  })

  done(
    () => {
      // Callback to call to make the actual updates, you can filter the options
    },
    () => {
      // Callback to call at the end after the update has been fully processed by QSelect
      // (ref) "ref" is the Vue reference to the QSelect
    }
  )
}

const abortFilterFn = () => {
  console.debug('Delayed filter aborted')
}

// Don't use watchEffect, it run before Mounted.
watch(
  () => [model.value],
  (...newAndold) => {
    // newAndold[1]:old
    // newAndold[0]:new
    if (typeof model.value === 'object') {
      console.debug(
        'watch:' +
          JSON.stringify(newAndold[1]) +
          ' ---> ' +
          JSON.stringify(newAndold[0])
      )
    } else {
      console.debug('watch:' + newAndold[1] + ' ---> ' + newAndold[0])
    }
  }
)
</script>
<style lang="sass"></style>
