/***
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-04-04 17:23:56
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-04-11 01:40:21
 * @FilePath       : \web\src\mixins\lifeCycleDebug.js
 * @CopyRight      : Mer Bleue Aviation Limited
 */
import { getCurrentInstance } from 'vue'

/**
 * add it in your component:
 * <script>
 * import { defineComponent } from 'vue'
 * import lifeCycleDebug from '/src/mixins/lifeCycleDebug'
 * export default defineComponent({
 *   mixins: [lifeCycleDebug]
 * })
 * </script>
 */

export default {
  beforeCreate() {
    if (process.env.DEV) {
      const instance = getCurrentInstance()
      console.debug('beforeCreate ' + instance.type.name)
    }
  },
  created() {
    if (process.env.DEV) {
      const instance = getCurrentInstance()
      console.debug('created ' + instance.type.name)
    }
  },
  beforeMount() {
    if (process.env.DEV) {
      const instance = getCurrentInstance()
      console.debug('beforeMount ' + instance.type.name)
    }
  },
  mounted() {
    if (process.env.DEV) {
      const instance = getCurrentInstance()
      console.debug('mounted ' + instance.type.name)
    }
  },
  beforeUnmount() {
    if (process.env.DEV) {
      const instance = getCurrentInstance()
      console.debug('beforeUnmount ' + instance.type.name)
    }
  },
  unmounted() {
    if (process.env.DEV) {
      const instance = getCurrentInstance()
      console.debug('unmounted ' + instance.type.name)
    }
  }
}
