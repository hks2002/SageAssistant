const routes = [
  {
    path: '/',
    name: '/',
    component: () => import('layouts/DefaultLayout.vue'),
    children: [
      {
        path: ''
      }
    ]
  },
  /*   {
    path: '/result/success',
    name: '/result/success',
    component: () => import('layouts/DefaultLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/result/Success')
      }
    ]
  },
  {
    path: '/result/fail',
    name: '/result/fail',
    component: () => import('layouts/DefaultLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/result/Fail')
      }
    ]
  },
  {
    path: '/exception/403',
    name: '/exception/403',
    component: () => import('layouts/DefaultLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages//exception/403')
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
        component: () => import('pages//exception/404')
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
        component: () => import('pages//exception/500')
      }
    ]
  },
  {
    path: '/user/login',
    name: '/user/login',
    component: () => import('layouts/UserLayout.vue'),
    children: [
      {
        path: '',
        component: () => import('pages/user/Login.vue')
      }
    ]
  },
  */
  // Always leave this as last one,
  // but you can also remove it
  {
    path: '*',
    component: () => import('src/pages/Error404.vue')
  }
]

export default routes
