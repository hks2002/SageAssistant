<template >
  <q-select filled
            clearable
            use-input
            fill-input
            hide-selected
            v-model="pnAndPnRoot"
            input-debounce="500"
            label="Input PN or PN Root"
            option-label="PN"
            option-value="PNROOT"
            :options="pns"
            ref="selectPN"
            @input="searchPN"
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
  require('../mock/PNHelper')
}

import _findIndex from 'lodash/findIndex'

export default {
  name: 'QSelectInputPn',
  data () {
    return {
      pnAndPnRoot: '',
      pns: [{ PNROOT: '', PN: 'Searching PN...' }]
    }
  },
  methods: {
    doAjax (pnOrPnRoot) {
      this.$axios.get('/Data/PNHelper?PnOrPnRoot=' + pnOrPnRoot)
        .then((response) => {
          console.debug('[axios] ' + response.status + ' ' + response.statusText + ' ' + response.config.url)
          console.debug(JSON.stringify(response.data))

          this.pns = Object.freeze(response.data)
        }).catch((e) => {
          this.$q.notify({
            color: 'negative',
            position: 'top',
            message: 'Loading PN Failed!',
            icon: 'fas fa-exclamation-triangle'
          })
        })
    },

    autoSelect (pnOrPnRoot) {
      let optionIndex = _findIndex(this.pns, o => { return o.PN === pnOrPnRoot })
      if (optionIndex === -1) {
      } else {
        this.$refs.selectPN.scrollTo(optionIndex) // moveOptionSelection() // scrollTo(optionIndex)
        this.$refs.selectPN.toggleOption(this.$refs.selectPN.options[this.$refs.selectPN.optionIndex], true) // toggle the focused option
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
            let pnOrPnRoot = val.toUpperCase()
            await this.doAjax(pnOrPnRoot)
            this.autoSelect(pnOrPnRoot)
          }
        )
      }, 0)
    },

    abortFilterFn () {
      // console.log('delayed filter aborted')
    },

    searchPN () {
      console.debug('[emit] searchPN: ' + JSON.stringify(this.pnAndPnRoot))
      if (this.pnAndPnRoot) {
        this.$emit('searchPN', this.pnAndPnRoot.PNROOT)
      } else {
        this.$emit('searchPN', null)
      }
    }
  },
  mounted: function () {
  }
}
</script>
