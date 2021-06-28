import Mock from 'mockjs'

/* Add http status support */
// https://github.com/trek/FakeXMLHttpRequest/blob/master/fake_xml_http_request.js#L32
const HTTP_STATUS_CODES = {
  100: 'Continue',
  101: 'Switching Protocols',
  200: 'OK',
  201: 'Created',
  202: 'Accepted',
  203: 'Non-Authoritative Information',
  204: 'No Content',
  205: 'Reset Content',
  206: 'Partial Content',
  300: 'Multiple Choice',
  301: 'Moved Permanently',
  302: 'Found',
  303: 'See Other',
  304: 'Not Modified',
  305: 'Use Proxy',
  307: 'Temporary Redirect',
  400: 'Bad Request',
  401: 'Unauthorized',
  402: 'Payment Required',
  403: 'Forbidden',
  404: 'Not Found',
  405: 'Method Not Allowed',
  406: 'Not Acceptable',
  407: 'Proxy Authentication Required',
  408: 'Request Timeout',
  409: 'Conflict',
  410: 'Gone',
  411: 'Length Required',
  412: 'Precondition Failed',
  413: 'Request Entity Too Large',
  414: 'Request-URI Too Long',
  415: 'Unsupported Media Type',
  416: 'Requested Range Not Satisfiable',
  417: 'Expectation Failed',
  422: 'Unprocessable Entity',
  500: 'Internal Server Error',
  501: 'Not Implemented',
  502: 'Bad Gateway',
  503: 'Service Unavailable',
  504: 'Gateway Timeout',
  505: 'HTTP Version Not Supported'
}

const Util = Mock.Util

Util.extend(Mock.XHR.prototype, {
  send: function (data) {
    const that = this
    // responseType: "arraybuffer"
    if (this.responseType === 'arraybuffer') {
      this.custom.xhr.responseType = 'arraybuffer'
      this.custom.xhr.send(data)
      return
    }

    // Original XHR
    if (!this.match) {
      this.custom.xhr.send(data)
      return
    }

    // data contains post request data, pass it to options
    that.custom.options.data = data

    // data template ＝> data response
    // function convert(item, options) {
    //    return Util.isFunction(item.template) ?
    //       item.template(options) : MockXMLHttpRequest.Mock.mock(item.template)
    // }
    // mockdata may have {data} and {status}
    const mockdata = Util.isFunction(that.custom.template.template)
      ? that.custom.template.template(that.custom.options)
      : Mock.mock(that.custom.template.template)

    // Intercept XHR
    this.setRequestHeader('X-Requested-With', 'MockXMLHttpRequest')

    // loadstart The fetch initiates.
    this.dispatchEvent(new Event('loadstart'))

    if (this.custom.async) setTimeout(done, this.custom.timeout)
    else done()

    function done() {
      that.readyState = Mock.XHR.HEADERS_RECEIVED
      that.dispatchEvent(new Event('readystatechange'))
      that.readyState = Mock.XHR.LOADING
      that.dispatchEvent(new Event('readystatechange'))

      if (mockdata.status) {
        that.status = mockdata.status
        that.statusText = HTTP_STATUS_CODES[mockdata.status]
      } else {
        that.status = 200
        that.statusText = HTTP_STATUS_CODES[200]
      }

      if (mockdata.data) {
        that.custom.options.body = that.response = that.responseText = JSON.stringify(
          mockdata.data,
          null,
          4
        )
      } else {
        that.custom.options.body = that.response = that.responseText = JSON.stringify(
          mockdata,
          null,
          4
        )
      }

      that.readyState = Mock.XHR.DONE
      that.dispatchEvent(new Event('readystatechange'))
      that.dispatchEvent(new Event('load'))
      that.dispatchEvent(new Event('loadend'))
    }
  }
})

export { Mock }

export const mockData = (mkdata, mkstatus) => {
  const mockDataTemplate = {
    data: '',
    status: 200
  }
  if (mkdata) {
    mockDataTemplate.data = mkdata
  }
  if (mkstatus) {
    mockDataTemplate.status = mkstatus
  }
  return mockDataTemplate
}

// Use getQueryParameters as bellow:
// const param = getQueryParameters(options, param)
// param
export const getQueryParameter = (options, param) => {
  const search = options.url.split('?')[1]
  if (!search) {
    return {}
  }
  const urlParams = new URLSearchParams(search)
  return urlParams.get(param)
}

// Use getQueryParameters as bellow:
// const params = getQueryParameters(options)
// params.xx  params.yy
export const getQueryParameters = (options) => {
  const url = options.url
  const search = url.split('?')[1]
  if (!search) {
    return {}
  }
  return JSON.parse(
    '{"' +
    decodeURIComponent(search)
      .replace(/"/g, '\\"')
      .replace(/&/g, '","')
      .replace(/=/g, '":"') +
    '"}'
  )
}

// Use getPostParameters as bellow:
// const params = getPostParameters(options)
// params.xx  params.yy
export const getPostParameters = (options) => {
  const data = options.data
  if (!data) {
    return {}
  }
  return JSON.parse(decodeURIComponent(data))
}

// Use getBody as bellow:
// const body = getBody(options)
// body.xx  body.yy
export const getBody = (options) => {
  return options.body && JSON.parse(options.body)
}
