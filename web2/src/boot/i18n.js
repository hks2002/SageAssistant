import messages from '@/i18n/index'
import { Cookies, Quasar } from 'quasar'
import { boot } from 'quasar/wrappers'
import { createI18n } from 'vue-i18n'

const langIso = Cookies.get('locale') || 'en-US'
console.debug('\u001b[35m' + '[Boot] ', 'i18n', langIso)

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
 * import { i18n } from '/src/boot/i18n'
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

export { i18n }

export default boot(({ app } /* { app, router, ... } */) => {
  // Set i18n instance on app
  app.use(i18n)

  // Set Quasar lang pack
  Promise.resolve(require('quasar/lang/' + langIso))
    .then((lang) => {
      Quasar.lang.set(lang.default)
    })
    .catch((err) => {
      // Requested Quasar Language Pack does not exist,
      // let's not break the app, so catching error
      console.debug(err)
    })
})
