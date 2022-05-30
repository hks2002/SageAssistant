/***
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-05-26 23:22:25
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-05-28 23:04:12
 * @FilePath       : \web2\src\assets\i18n.js
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
import messages from '@/i18n/index'
import { Cookies } from 'quasar'
import { createI18n } from 'vue-i18n'

const langIso = Cookies.get('locale') || 'en-US'
console.debug('\u001b[35m' + '[Load] ', 'i18n', langIso)

const i18nCfg = {
  locale: langIso,
  messages,
  silentTranslationWarn: true,
  silentFallbackWarn: true
}
const i18n = createI18n(i18nCfg)

/**
 * Use i18n outside of a Vue file:
 *
 * import { i18n } from '/src/assets/i18n'
 * const { t } = i18n.global
 * t('Sample Text')
 *
 *
 * Use i18n inside of a Vue file in template:
 *
 * {{ $t('Sample Text') }}
 *
 *
 * Use i18n inside of a vue file in script:
 *
 * import { useI18n } from 'vue-i18n'
 * const { t } = useI18n()
 * t('Sample Text')
 */

export { i18n, langIso }
