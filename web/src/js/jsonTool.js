let jsonToTable = function (headers, jsonData, title) {
  var table = '<div class="q-table--dense"><table style="width:100%;text-align:center" class="q-table"><caption style="font-size:28px">' + title + '</caption>'
  table += '<tr>'
  for (let i = 0, l = headers.length; i < l; i++) {
    table += '<th style="background:#00B0F0;color:white">' + headers[i] + '</th>'
  }
  table += '</tr>'
  for (let i2 = 0, l2 = jsonData.length; i2 < l2; i2++) {
    table += '<tr>'
    for (let i3 = 0, l3 = headers.length; i3 < l3; i3++) {
      table += '<td style="white-space: nowrap">' + jsonData[i2][headers[i3]] + '</td>'
    }
    table += '</tr>'
  }
  table += '</table><div>'
  return table
}

let jsonToExcel = function (headers, jsonData, filename) {
  var str = '<tr>'
  for (let i = 0, l = headers.length; i < l; i++) {
    str += '<td style="background:#00B0F0;color:white">' + headers[i] + '</td>'
  }
  str += '</tr>'

  for (let i2 = 0, l2 = jsonData.length; i2 < l2; i2++) {
    str += '<tr>'
    for (let i3 = 0, l3 = headers.length; i3 < l3; i3++) {
      // add \t to avoid number diaplay format change in excel
      str += `<td>${jsonData[i2][headers[i3]] + '\t'}</td>`
    }
    str += '</tr>'
  }

  let worksheet = 'Sheet1'
  let uri = 'data:application/vnd.ms-excel;base64,'

  let template = `<html xmlns:o="urn:schemas-microsoft-com:office:office" 
      xmlns:x="urn:schemas-microsoft-com:office:excel" 
      xmlns="http://www.w3.org/TR/REC-html40">
      <head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet>
        <x:Name>${worksheet}</x:Name>
        <x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet>
        </x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]-->
        </head><body><table style="font-family:Arial;font-size:10.5px;">${str}</table></body></html>`

  const link = document.createElement('a')
  link.style.display = 'none'
  link.href = uri + base64(template)
  link.setAttribute(
    'download',
    filename
  )
  document.body.appendChild(link)
  link.click()
}
function base64 (s) { return window.btoa(unescape(encodeURIComponent(s))) }

let jsonToMultLine = function (fields, jsonObj) {
  let s = '<table>'
  for (let i = 0, l = fields.length; i < l; i++) {
    s += '<tr><td>' + fields[i] + ':</td><td>' + jsonObj[fields[i]] + '</tr>'
  }
  s += '</table>'
  return s
}

export {
  jsonToTable,
  jsonToExcel,
  jsonToMultLine
}
