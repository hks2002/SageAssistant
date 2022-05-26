const jsonToTable = function (headers, jsonData, title) {
  let table =
    '<div class="q-markup-table q-table__container q-table__card q-table--horizontal-separator q-table--dense q-table--no-wrap q-pr-sm q-pl-sm"><table class="q-table">'
  table += '<thead style="position: sticky; top: 0px; z-index: 1;">'
  table += '<tr><th colspan="' + headers.length + '" style="padding: 0px;">'
  table +=
    '<div class="q-toolbar row no-wrap items-center bg-teal text-white shadow-2"><div class="q-toolbar__title ellipsis">' +
    title +
    '</div></div>'
  table += '</th></tr>'
  table += '<tr class="bg-primary text-white text-left">'
  for (let i = 0, l = headers.length; i < l; i++) {
    table += '<th>' + headers[i] + '</th>'
  }
  table += '</tr></thead><tbody>'
  for (let i2 = 0, l2 = jsonData.length; i2 < l2; i2++) {
    table += '<tr>'
    for (let i3 = 0, l3 = headers.length; i3 < l3; i3++) {
      table +=
        '<td style="white-space: nowrap">' + jsonData[i2][headers[i3]] + '</td>'
    }
    table += '</tr>'
  }
  table += '</tbody></table><div>'
  return table
}

const jsonToExcel = function (headers, jsonData, filename) {
  let str = '<tr>'
  for (let i = 0, l = headers.length; i < l; i++) {
    str += '<td style="background:#00B0F0;color:white">' + headers[i] + '</td>'
  }
  str += '</tr>'

  for (let i2 = 0, l2 = jsonData.length; i2 < l2; i2++) {
    str += '<tr>'
    for (let i3 = 0, l3 = headers.length; i3 < l3; i3++) {
      // add \t to avoid number diaplay format change in excel
      jsonData[i2][headers[i3]]
        ? (str += `<td>${jsonData[i2][headers[i3]] + '\t'}</td>`)
        : (str += '<td></td>')
    }
    str += '</tr>'
  }

  const worksheet = 'Sheet1'
  const uri = 'data:application/vnd.ms-excel;base64,'

  const template = `<html xmlns:o="urn:schemas-microsoft-com:office:office" 
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
  link.setAttribute('download', filename)
  document.body.appendChild(link)
  link.click()
}
function base64(s) {
  return window.btoa(unescape(encodeURIComponent(s)))
}

const jsonToMultLine = function (fields, jsonObj) {
  let s = '<table>'
  for (let i = 0, l = fields.length; i < l; i++) {
    s += '<tr><td>' + fields[i] + ':</td><td>' + jsonObj[fields[i]] + '</tr>'
  }
  s += '</table>'
  return s
}

export { jsonToTable, jsonToExcel, jsonToMultLine }
