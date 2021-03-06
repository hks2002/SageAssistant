import { Notify, Dialog, QSpinnerBars, Loading } from 'quasar'
import _ from 'lodash'

export function resetObj(obj) {
  const objClone = _.clone(obj)
  Object.keys(objClone).forEach((key) => {
    objClone[key] = null
  })
  return objClone
}

export function resetArray(array) {
  if (array && array instanceof Array) {
    const arrayClone = _.clone(array)
    arrayClone.splice(0, arrayClone.length)
    return arrayClone
  }
  return array
}

export function redirect(url) {
  window.location = url
}

// Loading
export function showLoading(msg) {
  if (!msg || msg === '') {
    msg = 'Loading...'
  }
  Loading.show({
    spinner: QSpinnerBars,
    spinnerSize: 40,
    message: msg
  })
}

export function hideLoading() {
  Loading.hide()
}
// Loading

// Notify
export function notifySuccess(message) {
  Notify.create({
    icon: 'fas fa-check-square',
    type: 'positive',
    position: 'top',
    message: message
  })
}

export function notifyError(message) {
  Notify.create({
    icon: 'fas fa-exclamation-triangle',
    type: 'negative',
    position: 'top',
    message: message
  })
}

export function notifyWaring(message) {
  Notify.create({
    icon: 'fas fa-exclamation-circle',
    type: 'warning',
    position: 'top',
    message: message
  })
}

export function notifyInfo(message) {
  Notify.create({
    icon: 'fas fa-exclamation-square',
    type: 'info',
    position: 'top',
    message: message
  })
}

export function notifyOnging(message) {
  Notify.create({
    icon: 'fas fa-spinner',
    type: 'ongoing',
    position: 'top',
    message: message
  })
}
// Notify

// Dialog
export function confirmDialog(message, title) {
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

export function infoDialog (message, title) {
  if (!title) {
    title = 'Info'
  }
  return Dialog.create({
    title: title,
    message: message,
    ok: {
      label: 'Ok',
      color: 'primary',
      unelevated: true
    },
    persistent: true
  })
}

export function alertDialog(message, title) {
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
// Dialog

export function getUrlRootPath() {
  return window.location.protocol + '//' + location.host
}

export default {
  redirect,
  showLoading,
  hideLoading,
  notifySuccess,
  notifyError,
  notifyWaring,
  notifyInfo,
  notifyOnging,
  confirmDialog,
  alertDialog,
  infoDialog
}
