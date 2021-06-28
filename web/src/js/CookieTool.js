function setCookie (name, value, expires) {
  const exp = new Date()
  exp.setTime(exp.getTime() + expires * 1000)
  document.cookie = name + '=' + escape(value) + ';expires=' + exp.toGMTString()
}
function getCookie (name) {
  const arr = document.cookie.match(new RegExp('(^| )' + name + '=([^;]*)(;|$)'))
  if (arr != null) return unescape(arr[2])
  return false
}
export {
  setCookie, getCookie
}
