import { Notify, Dialog, QSpinnerIos, Loading } from 'quasar'
import _ from 'lodash'

function resetObj (obj) {
  const objClone = _.clone(obj)
  Object.keys(objClone).forEach((key) => {
    objClone[key] = null
  })
  return objClone
}

function resetArray (array) {
  if (array && array instanceof Array) {
    const arrayClone = _.clone(array)
    arrayClone.splice(0, arrayClone.length)
    return arrayClone
  }
  return array
}

/* notify - begin */
function notifySuccess (message) {
  Notify.create({
    color: 'white',
    textColor: 'positive',
    icon: 'check_circle',
    position: 'top',
    message: message
  })
}

function notifyError (message) {
  Notify.create({
    color: 'white',
    textColor: 'negative',
    icon: 'error',
    position: 'top',
    message: message
  })
}

function notifyWaring (message) {
  Notify.create({
    color: 'white',
    textColor: 'warning',
    icon: 'warning',
    position: 'top',
    message: message
  })
}

function notifyAlert (message) {
  Notify.create({
    color: 'white',
    textColor: 'negative',
    icon: 'add_alert',
    position: 'top',
    message: message
  })
}

function notifyInfo (message) {
  Notify.create({
    color: 'white',
    textColor: 'info',
    icon: 'info',
    position: 'top',
    message: message
  })
}

/* notify - end */

/* confirm - begin */
function confirm (message, title) {
  if (!title) {
    title = 'Confirm'
  }
  return Dialog.create({
    title: title,
    message: message,
    cancel: {
      label: 'Cancel',
      color: 'warning',
      unelevated: true
    },
    ok: {
      label: 'Ok',
      color: 'primary',
      unelevated: true
    },
    persistent: true
  })
}

/* alert - end */

/* confirm - begin */
function alert (message, title) {
  if (!title) {
    title = 'Alert'
  }
  return Dialog.create({
    title: title,
    message: message,
    ok: 'Ok',
    persistent: true
  })
}

/* alert - end */

function getUrlRootPath () {
  return window.location.protocol + '//' + location.host
}

function showLoading (msg) {
  if (!msg || msg === '') {
    msg = 'Loading...'
  }
  const spinner = QSpinnerIos
  Loading.show({
    spinner,
    spinnerSize: 40,
    spinnerColor: 'blue',
    backgroundColor: 'white',
    message: msg,
    messageColor: 'blue'
  })
}

function hideLoading () {
  Loading.hide()
}

export default {
  getUrlRootPath,
  notifySuccess,
  notifyInfo,
  notifyWaring,
  notifyAlert,
  notifyError,
  confirm,
  alert,
  resetObj,
  resetArray,
  showLoading,
  hideLoading
}
