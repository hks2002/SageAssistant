const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [{ path: '', component: () => import('pages/Index.vue') }]
  },
  {
    path: '/Login',
    name: '/Login',
    component: () => import('layouts/DefaultLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/Login.vue')
      }
    ]
  },
  {
    path: '/About',
    name: '/About',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/About.vue')
      }
    ]
  },
  {
    path: '/Todo',
    name: '/Todo',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/Todo.vue')
      }
    ]
  },
  {
    path: '/Financials',
    name: '/Financials',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('src/pages/Financials.vue')
      }
    ]
  },
  {
    path: '/Analysis',
    name: '/Analysis',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('src/pages/Analysis.vue')
      }
    ]
  },
  {
    path: '/Reports',
    name: '/Reports',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/Reports.vue')
      }
    ]
  },
  {
    path: '/Products',
    name: '/Products',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/Products.vue')
      }
    ]
  },
  {
    path: '/Stock',
    name: '/Stock',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/Stock.vue')
      }
    ]
  },
  {
    path: '/Suppliers',
    name: '/Suppliers',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/Suppliers.vue')
      }
    ]
  },
  {
    path: '/Waiting',
    name: '/Waiting',
    component: () => import('layouts/DefaultLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/Waiting.vue')
      }
    ]
  },
  {
    path: '/exception',
    name: '/exception',
    component: () => import('layouts/DefaultLayout.vue'),
    children: [
      {
        path: ':ErrorCode',
        component: () => import('pages/Exception.vue'),
        props: (route) => {
          if (route.params.ErrorCode !== '403' &&
            route.params.ErrorCode !== '404' &&
            route.params.ErrorCode !== '500') {
            return { ErrorCode: '404' }
          } else {
            return { ErrorCode: route.params.ErrorCode }
          }
        }
      }
    ]
  },
  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: () => import('layouts/DefaultLayout.vue'),
    redirect: '/exception/404'
  }
]

export default routes
