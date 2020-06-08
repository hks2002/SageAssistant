
let getUrlParam = function (url, name) {
  let reg = new RegExp('[?&]' + name + '=([^&#]+)')
  let query = url.match(reg)
  return query ? query[1] : ''
}

export {
  getUrlParam
}
