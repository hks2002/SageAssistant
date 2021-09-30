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
  // exception
  {
    path: '/exception/403',
    name: '/exception/403',
    component: () => import('layouts/DefaultLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/Error403.vue')
      }
    ]
  },
  {
    path: '/exception/404',
    name: '/exception/404',
    component: () => import('layouts/DefaultLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/Error404.vue')
      }
    ]
  },
  {
    path: '/exception/500',
    name: '/exception/500',
    component: () => import('layouts/DefaultLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/Error500.vue')
      }
    ]
  },
  // exception

  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/Error404.vue')
      }
    ]
  }
]

export default routes
