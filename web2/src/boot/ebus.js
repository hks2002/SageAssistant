import mitt from 'mitt'

console.debug('\u001b[35m' + '[Boot] ', 'Ebus')

const ebus = mitt()

// using it: import {ebus} from 'boot/ebus'
export { ebus }
