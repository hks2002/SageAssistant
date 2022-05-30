/***
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-25 11:01:23
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-05-28 23:05:57
 * @FilePath       : \web2\src\assets\sageSvcs.js
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
import { axios } from '@/assets/axios'
import { notifyError } from 'assets/common'
import { SessionStorage } from 'quasar'

const getSageSessionUrl = async (fnc) => {
  const trackngId = new Date().getTime()
  SessionStorage.set('lastTrackngId', trackngId)

  if (
    SessionStorage.getItem('lastSessionType') === fnc &&
    SessionStorage.getItem('lastSessionSuccessed')
  ) {
    return SessionStorage.getItem('lastSessionUrl')
  }

  const sessionRequestUrl =
    '/trans/x3/erp/EXPLOIT/$sessions?f=' +
    fnc +
    '/2//M' +
    '&trackngId=' +
    trackngId

  const sageSessionUrl = await axios
    .post(sessionRequestUrl, { settings: {} })
    .then((response) => {
      const location = response.headers.location.split('?')[0]
      if (response.status === 200) {
        if (response.data.sap.target.type === 'box') {
          notifyError(response.data.sap.target.box.li)
          return '/#/exception/403'
        }
        SessionStorage.set('lastSessionType', fnc)
        SessionStorage.set('lastSessionUrl', location)
        SessionStorage.set('lastSessionSuccessed', true)
        return location
      } else if (
        response.status === 202 &&
        response.data.phase === 'Tracking'
      ) {
        SessionStorage.set('lastSessionSuccessed', false)
        getSageSessionUrl(fnc)
        return location
      } else {
        SessionStorage.set('lastSessionSuccessed', false)
        return '/#/exception/500'
      }
    })
    .catch((error) => {
      console.log(error)
      SessionStorage.set('lastSessionSuccessed', false)
      return '/#/exception/500'
    })

  return sageSessionUrl
}

const doAction = async (sageSessionUrl, action, data) => {
  return await axios
    .put(
      sageSessionUrl +
        '/requestSvc?act=' +
        action +
        '&trackngId=' +
        SessionStorage.getItem('lastTrackngId'),
      data
    )
    .then(() => {
      SessionStorage.set('lastSessionSuccessed', true)
      return true
    })
    .catch((error) => {
      console.log(error)
      SessionStorage.set('lastSessionSuccessed', false)
      return false
    })
}

const selectListItemByLine = async (sageSessionUrl, line) => {
  const data = {
    act: 1052,
    fld: { fmtKind: 'SHOW', ctx: {}, notModified: true },
    param: { target: { win: 'B', xid: 'bA', nl: 0 }, std: [line] },
    tech: {}
  }

  return await axios
    .put(
      sageSessionUrl +
        '/requestSvc?act=1052&trackngId=' +
        SessionStorage.getItem('lastTrackngId'),
      data
    )
    .then((response) => {
      if (response.status === 200) {
        SessionStorage.set('lastSessionSuccessed', true)
        return true
      } else if (response.status === 202) {
        SessionStorage.set('lastSessionSuccessed', true)
        return false
      } else {
        SessionStorage.set('lastSessionSuccessed', false)
        return false
      }
    })
    .catch((error) => {
      console.log(error)
      SessionStorage.set('lastSessionSuccessed', false)
      return false
    })
}

// some times 1051 doesn't return the request record, please using 781 edit instead.
const selectListItemByValue = async (sageSessionUrl, val) => {
  const data = {
    act: 1051,
    fld: { fmtKind: 'SHOW', ctx: {}, notModified: false, v: '' },
    param: { target: { win: 'B', xid: 'bA', nl: 0 }, std: [val] },
    tech: {}
  }
  return await axios
    .put(
      sageSessionUrl +
        '/requestSvc?act=1051&trackngId=' +
        SessionStorage.getItem('lastTrackngId'),
      data
    )
    .then((response) => {
      if (response.status === 200) {
        SessionStorage.set('lastSessionSuccessed', true)
        return true
      } else if (response.status === 202) {
        SessionStorage.set('lastSessionSuccessed', true)
        return false
      } else {
        SessionStorage.set('lastSessionSuccessed', false)
        return false
      }
    })
    .catch((error) => {
      console.log(error)
      SessionStorage.set('lastSessionSuccessed', false)
      return false
    })
}

const selectListItemWithEditByValue = async (sageSessionUrl, val) => {
  const data = {
    act: 781,
    fld: { fmtKind: 'EDIT', ctx: {}, notModified: false, v: val },
    param: { target: { win: 'B', xid: 'bA', nl: 0 }, sudo: [[], ['7~' + val]] },
    tech: {}
  }
  return await axios
    .put(
      sageSessionUrl +
        '/requestSvc?act=781&trackngId=' +
        SessionStorage.getItem('lastTrackngId'),
      data
    )
    .then((response) => {
      if (response.status === 200) {
        SessionStorage.set('lastSessionSuccessed', true)
        return true
      } else if (response.status === 202) {
        SessionStorage.set('lastSessionSuccessed', true)
        return false
      } else {
        SessionStorage.set('lastSessionSuccessed', false)
        return false
      }
    })
    .catch((error) => {
      console.log(error)
      SessionStorage.set('lastSessionSuccessed', false)
      return false
    })
}

const enterPrintPage = async (sageSessionUrl) => {
  // enter the report print page
  const data = {
    act: 2820,
    fld: { fmtKind: 'SHOW', ctx: {}, notModified: false, v: '' },
    tech: {}
  }

  return await axios
    .put(
      sageSessionUrl +
        '/requestSvc?act=2820 &trackngId=' +
        SessionStorage.getItem('lastTrackngId'),
      data
    )
    .then(() => {
      SessionStorage.set('lastSessionSuccessed', true)
      return true
    })
    .catch((error) => {
      console.log(error)
      SessionStorage.set('lastSessionSuccessed', false)
      return false
    })
}

const doActionsForSagePrint = async (sageSessionUrl, rpt, val) => {
  switch (rpt) {
    case 'SA': {
      let sessionType = ''
      const reg1 = /^[A-Z](CC|DSR|REP|RCL|LC)[\d]{6,7}$/
      const reg2 = /^[A-Z]LC[\d]{6}$/
      if (reg1.test(val)) {
        sessionType = '2~1'
      } else if (reg2.test(val)) {
        sessionType = '2~2'
      } else {
        sessionType = '2~1'
      }

      if (!(await selectListItemByLine(sageSessionUrl, sessionType))) {
        return false
      }
      if (!(await selectListItemWithEditByValue(sageSessionUrl, val))) {
        return false
      }
      if (!(await enterPrintPage(sageSessionUrl))) {
        return false
      }

      return true
    }
    case 'Delivery': {
      if (!(await selectListItemWithEditByValue(sageSessionUrl, val))) {
        return false
      }
      if (!(await enterPrintPage(sageSessionUrl))) {
        return false
      }
      if (!(await selectListItemByLine(sageSessionUrl, '7~1:BONLIV!1'))) {
        return false
      }

      return true
    }
    case 'Invoice': {
      let sessionType = ''
      const reg1 = /[A-Z]FC.*/
      const reg2 = /[A-Z]PC.*/
      if (reg1.test(val)) {
        sessionType = '2~2'
      } else if (reg2.test(val)) {
        sessionType = '2~3'
      } else {
        sessionType = '2~2'
      }

      if (!(await selectListItemByLine(sageSessionUrl, sessionType))) {
        return false
      }
      if (!(await selectListItemWithEditByValue(sageSessionUrl, val))) {
        return false
      }
      if (!(await enterPrintPage(sageSessionUrl))) {
        return false
      }

      return true
    }
    case 'PurchaseOrder': {
      let reportType = ''
      if (val2) {
        reportType = '7~1:BONTTC2!1'
      } else {
        reportType = '7~1:BONCDE2!1'
      }
      // Sage need orderno with orderday, val= orday~orderno
      if (!(await selectListItemByValue(sageSessionUrl, val))) {
        return false
      }
      if (!(await selectListItemByLine(sageSessionUrl, reportType))) {
        return false
      }
      if (!(await enterPrintPage(sageSessionUrl))) {
        return false
      }

      return true
    }
    default:
      return false
  }
}

const getSagePrintUUID = async (sageSessionUrl) => {
  // send the print action
  const sagePrintUUID = await axios
    .put(
      sageSessionUrl +
        '/requestSvc?act=2125&trackngId=' +
        SessionStorage.getItem('lastTrackngId'),
      {
        act: 2125,
        fld: { fmtKind: 'EDIT', ctx: {}, notModified: true },
        tech: {}
      }
    )
    .then(
      (response) => {
        SessionStorage.set('lastSessionSuccessed', true)
        return response.data.sap.jobs.report.submitted.pop().uuid // here need
      },
      (error) => {
        console.log(error)
        SessionStorage.set('lastSessionSuccessed', false)
        return '/#/exception/500'
      }
    )
  return sagePrintUUID
}

const getSageReportUrl = async (printUUID) => {
  function sleep(millisecond) {
    return new Promise((resolve) => {
      setTimeout(() => {
        resolve()
      }, millisecond)
    })
  }

  let status = 202
  do {
    await sleep(1000)
    status = await axios
      .get("/print/srvedi01.dedaero.lan:1890/$getState('" + printUUID + "')")
      .then(
        (response) => response.status,
        (error) => {
          console.log(error)
          return 500
        }
      )
  } while (status === 202) // 202 creating, 201 created

  if (status === 201) {
    const sageReportUrl = await axios
      .get("/print/srvedi01.dedaero.lan:1890/$getReport('" + printUUID + "')")
      .then(
        (response) => response.headers.location,
        (error) => {
          console.log(error)
          return '/#/exception/500'
        }
      )
    return sageReportUrl
  } else {
    return '/#/exception/500'
  }
}

export {
  getSageSessionUrl,
  doAction,
  doActionsForSagePrint,
  selectListItemByLine,
  selectListItemByValue,
  selectListItemWithEditByValue,
  enterPrintPage,
  getSagePrintUUID,
  getSageReportUrl
}
