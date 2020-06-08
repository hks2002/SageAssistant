import Mock from 'mockjs'
import { getUrlParam } from '../js/getUrlParam'
Mock.setup({
  timeout: '1000-3000'
})

Mock.mock(RegExp('^(/Data/AttachmentPath)' + '.*'), options => {
  let Pn = getUrlParam(options.url, 'Pn')

  // list = {data:[{},{}]}
  let list = Mock.mock({
    'data|1-10': [
      {
        'ROWID|+1': 1,
        PN: Pn,
        'Cat|+1': [4, 5, 6, 8, 9, 12, 14],
        DocType: /(PDF|JPG|JPEG|TIF|BMP|DOC|DOCX|XLS|XLSX|PPT|PPTX|ZIP|RAR|7Z|OTHER)/,
        Path: /\/File\/MockFile\/[A-Z]*\.(PDF|JPG|JPEG|TIF|BMP|DOC|DOCX|XLS|XLSX|PPT|PPTX|ZIP|RAR|7Z|OTHER)/
      }
    ]
  })

  return list.data
})
