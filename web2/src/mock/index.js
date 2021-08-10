// When in development mode, Run Mock
// This file is designed to for boot/mock.js, boot once
// Don't require this file in vue files, maybe you should import mock/service/*.js
if (process.env.DEV) {
  console.debug('\u001b[35m' + '[Mock] ', 'Mounting')

  require('./services/auth')
  require('./services/currencyRate')
  require('./services/files')
  require('./services/productions')
  require('./services/sites')
  require('./services/srvInfo')
  require('./services/suppliers')
  require('./services/todo')
  require('./services/fapiao')
  require('./services/invoice')

  console.debug('\u001b[35m' + '[Mock] ', 'Mounted')
}
