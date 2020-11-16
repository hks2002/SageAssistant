
const getUrlParam = function (url, name) {
  const reg = new RegExp('[?&]' + name + '=([^&#]+)')
  const query = url.match(reg)
  return query ? query[1] : ''
}

export {
  getUrlParam
}
