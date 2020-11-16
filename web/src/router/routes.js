
const routes = [
  {
    path: '/',
    component: () => import('layouts/MyLayout.vue'),
    children: [{ path: '', component: () => import('pages/Index.vue') }]
  },
  {
    path: '/Todo',
    component: () => import('layouts/MyLayout.vue'),
    children: [{ path: '', component: () => import('pages/TodoTobeDelivery.vue') },
      { path: 'Delivery', component: () => import('pages/TodoTobeDelivery.vue') },
      { path: 'Receive', component: () => import('pages/TodoTobeReceive.vue') },
      { path: 'NewOrder', component: () => import('pages/TodoTobeDealWithOrderLine.vue') },
      { path: 'NewBom', component: () => import('pages/TodoTobePurchaseBom.vue') }
    ]
  },
  {
    path: '/Products',
    component: () => import('layouts/MyLayout.vue'),
    children: [{ path: '', component: () => import('pages/Products.vue') }]
  },
  {
    path: '/Supplies',
    component: () => import('layouts/MyLayout.vue'),
    children: [{ path: '', component: () => import('pages/Supplies.vue') }]
  },
  {
    path: '/Reports',
    component: () => import('layouts/MyLayout.vue'),
    children: [{ path: '', component: () => import('pages/Reports.vue') }]
  },
  {
    path: '/About',
    component: () => import('layouts/MyLayout.vue'),
    children: [{ path: '', component: () => import('pages/About.vue') }]
  }
]

// Always leave this as last one
if (process.env.MODE !== 'ssr') {
  routes.push({
    path: '*',
    component: () => import('pages/Error404.vue')
  })
}

export default routes
