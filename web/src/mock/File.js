import Mock from 'mockjs'

Mock.setup({
  timeout: '400-1000'
})

Mock.mock(RegExp('^(/File/MockFile)' + '.*'), options => {
  let ext = options.split('.')[1]
  if (ext === 'JPG' || ext === 'JPEG' || ext === 'TIF' || ext === 'BMP') {
    return Mock.mock('@dataImage("200x100","' + options.url + '")')
  } else {
    console.log(options)
    return ''
  }
})
