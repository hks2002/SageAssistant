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
    component: () => import('layouts/MainLayout.vue'),
    children: [{ path: '', component: () => import('pages/Todo.vue') }
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
    path: '/Productions',
    name: '/Productions',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/Productions.vue')
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
