/***
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-25 11:01:23
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-05-28 23:11:46
 * @FilePath       : \web2\src\router\routes.js
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
const routes = [
  {
    path: '/',
    name: 'Home',
    component: () => import('layouts/MainLayout.vue'),
    children: [{ path: '', component: () => import('pages/HomePage.vue') }]
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
    path: '/Fapiao',
    name: 'Fapiao',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/FapiaoPage.vue')
      }
    ]
  },
  {
    path: '/InvoicePay',
    name: 'Invoice Pay',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/InvoicePayPage.vue')
      }
    ]
  },
  {
    path: '/Balance',
    name: 'Balance',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/BalancePage.vue')
      }
    ]
  },
  {
    path: '/QuoteSalesCost',
    name: 'Quote Sales Cost',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/QuoteSalesCostPage.vue')
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
    path: '/StockHistory',
    name: 'Stock History',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/StockHistoryPage.vue')
      }
    ]
  },
  {
    path: '/StockSummary',
    name: 'Stock Summary',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/StockSummaryPage.vue')
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
    path: '/WaitInput',
    name: 'WaitInput',
    component: () => import('layouts/DefaultLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/WaitInputPage.vue')
      }
    ]
  },
  {
    path: '/Exception',
    name: 'Exception',
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
    redirect: '/Exception/404'
  }
]

export default routes
