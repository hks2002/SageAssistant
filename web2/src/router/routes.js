const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [{ path: '', component: () => import('pages/IndexPage.vue') }]
  },
  {
    path: '/Login',
    name: 'Login',
    component: () => import('layouts/DefaultLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/LoginPage.vue')
      }
    ]
  },
  {
    path: '/About',
    name: 'About',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/AboutPage.vue')
      }
    ]
  },
  {
    path: '/Todo',
    name: 'Todo',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/TodoPage.vue')
      }
    ]
  },
  {
    path: '/Financials',
    name: 'Financials',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('src/pages/FinancialsPage.vue')
      }
    ]
  },
  {
    path: '/Analysis',
    name: 'Analysis',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('src/pages/AnalysisPage.vue')
      }
    ]
  },
  {
    path: '/Reports',
    name: 'Reports',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/ReportsPage.vue')
      }
    ]
  },
  {
    path: '/Products',
    name: 'Products',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/ProductsPage.vue')
      }
    ]
  },
  {
    path: '/Stock',
    name: 'Stock',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/StockPage.vue')
      }
    ]
  },
  {
    path: '/Suppliers',
    name: 'Suppliers',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/SuppliersPage.vue')
      }
    ]
  },
  {
    path: '/Customers',
    name: 'Customers',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/CustomersPage.vue')
      }
    ]
  },
  {
    path: '/exception',
    name: 'exception',
    component: () => import('layouts/DefaultLayout.vue'),
    children: [
      {
        path: ':ErrorCode',
        component: () => import('pages/ExceptionPage.vue'),
        props: (route) => {
          if (
            route.params.ErrorCode !== '403' &&
            route.params.ErrorCode !== '404' &&
            route.params.ErrorCode !== '500'
          ) {
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
