<template >
  <q-select filled
            clearable
            use-input
            fill-input
            hide-selected
            v-model="CodeOrName"
            input-debounce="500"
            label="Input Code or Name"
            option-label="SupplyName"
            option-value="SupplyCode"
            :options="supplies"
            ref="selectSupply"
            @input="searchSupply"
            input-class="text-uppercase"
            input-style="font-weight:bolder;font-size:25px"
            popup-content-style="font-weight:bold;font-size:25px"
            class="relative-position"
            @filter="filterFnAutoselect"
            @filter-abort="abortFilterFn">
    <template v-slot:no-option>
      <q-item>
        <q-item-section class="text-grey">
          No results
        </q-item-section>
      </q-item>
    </template>
  </q-select>
</template>

<script>
if (process.env.DEV) {
  require('../mock/SupplyHelper')
}

import _findIndex from 'lodash/findIndex'

export default {
  name: 'QSelectInputSupply',
  data () {
    return {
      CodeOrName: '',
      supplies: [{ SupplyCode: '', SupplyName: 'Searching Supply...' }]
    }
  },
  methods: {
    doAjax (codeOrName) {
      this.$axios.get('/Data/SupplyHelper?SupplyCodeOrName=' + codeOrName)
        .then((response) => {
          console.debug('[axios] ' + response.status + ' ' + response.statusText + ' ' + response.config.url)
          console.debug(JSON.stringify(response.data))

          this.supplies = Object.freeze(response.data)
        }).catch((e) => {
          this.$q.notify({
            color: 'negative',
            position: 'top',
            message: 'Loading Supplies Failed!',
            icon: 'fas fa-exclamation-triangle'
          })
        })
    },

    autoSelect (codeOrName) {
      const optionIndex = _findIndex(this.supplies, o => { return (o.SupplyCode === codeOrName || o.SupplyName === codeOrName) })
      if (optionIndex === -1) {
      } else {
        this.$refs.searchSupply.scrollTo(optionIndex) // moveOptionSelection() // scrollTo(optionIndex)
        this.$refs.searchSupply.toggleOption(this.$refs.searchSupply.options[this.$refs.searchSupply.optionIndex], true) // toggle the focused option
      }
    },

    filterFnAutoselect (val, update, abort) {
      // This function run befor input
      // call abort() at any time if you can't retrieve data somehow
      if (val.length < 2) {
        abort()
        return
      }
      setTimeout(() => {
        update(
          async () => {
            const codeOrName = val.toUpperCase()
            await this.doAjax(codeOrName)
            this.autoSelect(codeOrName)
          }
        )
      }, 0)
    },

    abortFilterFn () {
      // console.log('delayed filter aborted')
    },

    searchSupply () {
      console.debug('[emit] searchSupply: ' + JSON.stringify(this.CodeOrName))
      if (this.CodeOrName) {
        this.$emit('searchSupply', this.CodeOrName.SupplyCode)
      } else {
        this.$emit('searchSupply', null)
      }
    }
  },
  mounted: function () {
  }
}
</script>
