/***
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-05-20 12:50:44
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-05-28 23:04:21
 * @FilePath       : \web2\src\assets\mockExt.js
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
import Mock from 'mockjs'
import qs from 'qs'
const Util = Mock.Util

// overrivde send function, to support arrsybuffer, status code, staus text
Util.extend(Mock.XHR.prototype, {
  send: function (postData) {
    // eslint-disable-next-line @typescript-eslint/no-this-alias
    var that = this

    // Original XHR
    if (!this.match) {
      this.custom.xhr.send(postData)
      return
    }

    // Intercept XHR
    this.setRequestHeader('X-Requested-With', 'MockXMLHttpRequest')

    // loadstart The fetch initiates.
    this.dispatchEvent(new Event('loadstart'))

    // Add support for post params to options, when using Mock.mock
    // postData contains post request data, pass it to options
    this.custom.options.postData = postData

    // Add support for arraybuffer type
    // responseType: "arraybuffer", do nothing
    if (this.responseType === 'arraybuffer') {
      this.custom.xhr.responseType = 'arraybuffer'
      this.custom.xhr.send(postData)
      return
    }

    // mockdata may have {data} and {statusCode} and {statusText}
    const mockdata = Util.isFunction(this.custom.template.template)
      ? this.custom.template.template(this.custom.options)
      : Mock.mock(this.custom.template.template)

    if (this.custom.async) setTimeout(done, this.custom.timeout)
    else done()

    function done() {
      stateStart()
      // promise object support
      if (typeof mockdata.then === 'function') {
        mockdata
          .then((res) => {
            setResponse(res)
            stateDone()
          })
          .catch((err) => {
            setResponse({ statusCode: 204, statusText: 'Failed', data: err })
            stateDone()
          })
      } else {
        //normal object
        setResponse(mockdata)
        stateDone()
      }
    }
    function stateStart() {
      that.readyState = Mock.XHR.HEADERS_RECEIVED
      that.dispatchEvent(new Event('readystatechange'))
      that.readyState = Mock.XHR.LOADING
      that.dispatchEvent(new Event('readystatechange'))
    }
    function setResponse(data) {
      data.hasOwnProperty('statusCode')
        ? (that.status = data.statusCode) &&
          (that.statusText = data.statusText) &&
          (that.responseText = JSON.stringify(data.data, null, 4))
        : (that.status = 200) &&
          (that.statusText = 'OK') &&
          (that.responseText = JSON.stringify(data, null, 4))
    }
    function stateDone() {
      that.readyState = Mock.XHR.DONE
      that.dispatchEvent(new Event('readystatechange'))
      that.dispatchEvent(new Event('load'))
      that.dispatchEvent(new Event('loadend'))
    }
  }
})

// passing your status code and status text with your data
const dataWithStatusCode = (mockData, mockStatusCode, mockStatusText) => {
  const mockDataTemplate = {
    data: {},
    statusCode: 200,
    statusText: 'OK'
  }
  if (mockData) {
    mockDataTemplate.data = mockData
  }
  if (mockStatusCode) {
    mockDataTemplate.statusCode = mockStatusCode
  }
  if (mockStatusText) {
    mockDataTemplate.statusText = mockStatusText
  }
  return mockDataTemplate
}

const parserArray = (arr) => {
  let where = []
  for (const idx in arr) {
    const val = arr[idx]
    if (val.startsWith('{"') || val.startsWith('["')) {
      where.push(JSON.parse(arr[idx]))
    } else {
      where.push(val)
    }
  }
  return where
}

const parserObject = (obj) => {
  let where = {}
  for (const key in obj) {
    const val = obj[key]
    if (val.startsWith('{"') || val.startsWith('["')) {
      where[key] = JSON.parse(val)
    } else {
      where[key] = val
    }
  }
  return where
}

const parser = (target) => {
  if (target instanceof Object && target.constructor.name === 'Array') {
    return parserArray(target)
  } else if (target instanceof Object && target.constructor.name === 'Object') {
    return parserObject(target)
  } else {
    return JSON.parse(target)
  }
}

// Use getQueryParameters as bellow:
// const params = getQueryParameters(options)
// params.xx  params.yy
const getQueryParameters = (options) => {
  const url = options.url
  const search = url.split('?')[1]
  if (!search) {
    return {}
  }
  const queryJson = qs.parse(search)
  console.debug('\u001b[35m[mockExt] QueryParameters', queryJson)

  // without where param
  if (!queryJson.where) {
    return parser(queryJson)
  }
  // with where param
  else {
    const whereJson = queryJson.where
    queryJson.where = parser(whereJson)
    // return query.limit, query.offset, query.where
    return queryJson
  }
}

// Use getPostParameters as bellow:
// const params = getPostParameters(options)
// params.xx  params.yy
const getPostParameters = (options) => {
  const params = JSON.parse(options.postData)
  if (!params) {
    return {}
  }
  return params
}

// Use getPostParameters as bellow:
// const data = getPostData(options)
// data.xx  data.yy
const getPostData = (options) => {
  const postData = JSON.parse(options.postData)
  const data = postData.data
  if (!data) {
    return {}
  }
  return data
}

export {
  Mock,
  dataWithStatusCode,
  getQueryParameters,
  getPostParameters,
  getPostData
}
