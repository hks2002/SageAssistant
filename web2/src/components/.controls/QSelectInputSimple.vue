<template >
  <q-select
    clearable
    outlined
    use-input
    fill-input
    hide-selected
    hide-dropdown-icon
    emit-value
    input-debounce="500"
    input-class="text-uppercase"
    input-style="font-weight:bolder;font-size:25px;"
    popup-content-style="font-weight:bold;font-size:25px"
    :label="label"
    v-model="model"
    :options="options"
    @update:model-value="inputVal"
    @filter="filterFnAutoselect"
    @filter-abort="abortFilterFn"
  >
    <template v-slot:no-option>
      <q-item>
        <q-item-section class="text-red">
          No results
        </q-item-section>
      </q-item>
    </template>
  </q-select>
</template>

<script>
import { defineComponent, ref, watch, onBeforeUnmount } from 'vue'
import { notifyError } from 'assets/common'
import { ebus } from 'boot/ebus'
import { axios } from 'boot/axios'
import _findIndex from 'lodash/findIndex'

export default defineComponent({
  name: 'QSelectInputSimple',

  props: {
    dataUrl: {
      type: String,
      required: true
    },
    emitTo: {
      type: String,
      required: true
    },
    defaultValue: {
      type: String,
      default: '',
      required: false
    },
    label: {
      type: String,
      default: 'label',
      required: false
    },
    minQueryLen: {
      type: Number,
      default: 2,
      required: false
    },
    identification: {
      type: String,
      required: false,
      default: ''
    }
  },

  setup(props, ctx) {
    const model = ref(props.defaultValue)
    // Bug? Must initial enough spaces
    const options = ref([])

    const filterFnAutoselect = (val, update, abort) => {
      // This event before input event, call abort() at any time if you can't retrieve data somehow
      console.debug('filterFnAutoselect:' + val)

      if (val.length < props.minQueryLen) {
        abort()
        return
      }

      update(
        () => {
          // Callback to call to make the actual updates, you can filter the options
        },
        (ref) => {
          // Callback to call at the end after the update has been fully processed by QSelect
          // "ref" is the Vue reference to the QSelect
          //
          axios
            .get(props.dataUrl + val.toUpperCase())
            .then((response) => {
              options.value = response.data

              const o = {}
              Object.defineProperty(o, props.optionValue, {
                enumerable: true,
                value: val.toUpperCase()
              })

              const optionIndex = _findIndex(options.value, o)
              console.debug('optionIndex:' + optionIndex)

              if (optionIndex > -1 && ref.options.length > 0) {
                ref.setOptionIndex(optionIndex)
              } else if (optionIndex === -1 && ref.options.length > 0) {
                // ref.scrollTo(0)
                ref.moveOptionSelection(1, true) // focus the first selectable option and do not update the input-value
              }
            })
            .catch((e) => {
              console.debug(e)
              notifyError('Loading ' + props.identification + ' failed!')
            })
        }
      )
    }

    const abortFilterFn = () => {
      console.log('delayed filter aborted')
    }

    const inputVal = () => {
      if (props.emitTo) {
        console.debug('emit...')
        ebus.emit(props.emitTo, model.value)
      }
    }

    // Don't use watchEffect, it run before Mounted.
    watch(
      () => [props.defaultValue],
      (...newAndold) => {
        // newAndold[1]:old
        // newAndold[0]:new
        console.debug('watch:' + newAndold[1] + ' ---> ' + newAndold[0])
        if (newAndold[0][0]) {
          model.value = newAndold[0]
        }
      }
    )

    return {
      model,
      options,
      filterFnAutoselect,
      abortFilterFn,
      inputVal
    }
  }
})
</script>
<style lang="sass">
</style>
