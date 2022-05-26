// When in development mode, Run Mock
// This file is designed to for boot/mock.js, boot once
// Don't require this file in vue files, maybe you should import mock/service/*.js
if (process.env.DEV) {
  console.debug('\u001b[35m' + '[Mock] ', 'Mounting')

  // auto require all files in /src/mock/services
  const mockfiles = require.context('/src/mock/services', false, /.js$/)
  mockfiles.keys().forEach(key => {
    const file = key.slice(2)
    require(`/src/mock/services/${file}`)
  })
  console.debug('\u001b[35m' + '[Mock] ', 'Mounted')
}
