
const routes = [
  {
    path: '/',
    component: () => import('layouts/MyLayout.vue'),
    children: [
      { path: '', component: () => import('pages/Index.vue') }
    ]
  },
  {
    path: '/OpenSaleItems',
    component: () => import('layouts/MyLayout.vue'),
    children: [
      { path: '', component: () => import('pages/OpenSaleItems.vue') }
    ]
  },
  {
    path: '/Reports',
    component: () => import('layouts/MyLayout.vue'),
    children: [
      { path: '', component: () => import('pages/Reports.vue') }
    ]
  },
  {
    path: '/About',
    component: () => import('layouts/MyLayout.vue'),
    children: [
      { path: '', component: () => import('pages/About.vue') }
    ]
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
