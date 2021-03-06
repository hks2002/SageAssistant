// Configuration for your app
// https://quasar.dev/quasar-cli/quasar-conf-js
const fs = require('fs')
const moment = require('moment')

module.exports = function (ctx) {
  return {
    // app boot file (/src/boot)
    // --> boot files are part of 'main.js'
    // https://quasar.dev/quasar-cli/cli-documentation/boot-files
    boot: ['axios'],

    // https://quasar.dev/quasar-cli/quasar-conf-js#Property%3A-css
    css: ['app.sass'],

    // https://github.com/quasarframework/quasar/tree/dev/extras
    extras: [
      // 'ionicons-v4',
      // 'mdi-v4',
      // 'fontawesome-v5',
      // 'eva-icons',
      // 'themify',
      // 'roboto-font-latin-ext', // this or either 'roboto-font', NEVER both!
      // 'roboto-font', // optional, you are not bound to it
      // 'material-icons' // optional, you are not bound to it
      'fontawesome-v5'
    ],

    // https://quasar.dev/quasar-cli/quasar-conf-js#Property%3A-framework
    framework: {
      iconSet: 'fontawesome-v5', // Quasar icon set
      lang: 'en-us', // Quasar language pack

      // Possible values for 'all':
      // * 'auto' - Auto-import needed Quasar components & directives
      //            (slightly higher compile time; next to minimum bundle size; most convenient)
      // * false  - Manually specify what to import
      //            (fastest compile time; minimum bundle size; most tedious)
      // * true   - Import everything from Quasar
      //            (not treeshaking Quasar; biggest bundle size; convenient)
      all: 'auto',

      components: [],
      directives: [],

      // Quasar plugins
      plugins: ['Notify', 'Loading', 'Meta']
    },

    // https://quasar.dev/quasar-cli/cli-documentation/supporting-ie
    supportIE: true,

    // Full list of options: https://quasar.dev/quasar-cli/quasar-conf-js#Property%3A-build
    build: {
      scopeHoisting: true,
      vueRouterMode: 'hash', // available values: 'hash', 'history'
      showProgress: true,
      gzip: true,
      analyze: true,
      beforeDev () {
        let pkg = fs.readFileSync('package.json')
        const timeStamp = moment().format('MMDDHHmmss')
        pkg = JSON.parse(pkg)
        pkg.version = pkg.version.replace(/^(\d+\.\d+\.\d+)(\S*)/, '$1')
        pkg.version = pkg.version + '.' + timeStamp
        fs.writeFileSync('package.json', JSON.stringify(pkg, null, 2))
        console.log('update version with timestamp ---->' + pkg.version)
      },
      beforeBuild () {
        let pkg = fs.readFileSync('package.json')
        const timeStamp = moment().format('MMDDHHmmss')
        pkg = JSON.parse(pkg)

        pkg.version = pkg.version.replace(/^(\d+\.\d+\.\d+)(\S*)/, '$1')
        pkg.version = pkg.version + '.' + timeStamp

        console.log('update version with timestamp: ' + pkg.version)
        fs.writeFileSync('package.json', JSON.stringify(pkg, null, 2))
      },
      uglifyOptions: {
        compress: {
          drop_console: true,
          drop_debugger: true
        }
      },
      // Options below are automatically set depending on the env, set them if you want to override
      // preloadChunks: false,
      // extractCSS: false,

      // https://quasar.dev/quasar-cli/cli-documentation/handling-webpack
      extendWebpack (cfg) {
        cfg.module.rules.push({
          enforce: 'pre',
          test: /\.(js|vue)$/,
          loader: 'eslint-loader',
          exclude: /node_modules/,
          options: {
            formatter: require('eslint').CLIEngine.getFormatter('stylish')
          }
        })
      }
    },

    // Full list of options: https://quasar.dev/quasar-cli/quasar-conf-js#Property%3A-devServer
    devServer: {
      https: false,
      port: 8080,
      open: false // opens browser window automatically
    },

    // animations: 'all', // --- includes all animations
    // https://quasar.dev/options/animations
    animations: [],

    // https://quasar.dev/quasar-cli/developing-ssr/configuring-ssr
    ssr: {
      pwa: false
    },

    // https://quasar.dev/quasar-cli/developing-pwa/configuring-pwa
    pwa: {
      workboxPluginMode: 'GenerateSW', // 'GenerateSW' or 'InjectManifest'
      workboxOptions: {}, // only for GenerateSW
      manifest: {
        name: require('./package.json').productName,
        short_name: require('./package.json').name,
        description: require('./package.json').description,
        display: 'standalone',
        orientation: 'portrait',
        background_color: '#ffffff',
        theme_color: '#027be3',
        icons: [
          {
            src: 'statics/icons/icon-128x128.png',
            sizes: '128x128',
            type: 'image/png'
          },
          {
            src: 'statics/icons/icon-192x192.png',
            sizes: '192x192',
            type: 'image/png'
          },
          {
            src: 'statics/icons/icon-256x256.png',
            sizes: '256x256',
            type: 'image/png'
          },
          {
            src: 'statics/icons/icon-384x384.png',
            sizes: '384x384',
            type: 'image/png'
          },
          {
            src: 'statics/icons/icon-512x512.png',
            sizes: '512x512',
            type: 'image/png'
          }
        ]
      }
    }
  }
}
