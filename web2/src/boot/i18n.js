import { boot } from 'quasar/wrappers'
import { createI18n } from 'vue-i18n'
import messages from 'src/i18n'

const i18n = createI18n({
  locale: 'en-US',
  messages,
  silentTranslationWarn: true,
  silentFallbackWarn: true
})

export default boot(({ app }) => {
  // Set i18n instance on app
  app.use(i18n)
})

export { i18n }

// In vue template:
// derectly using $t
//
// In script setup():
// import { useI18n } from 'vue-i18n'
// const { t } = useI18n()
// t('xxx')
