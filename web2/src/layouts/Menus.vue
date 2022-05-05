<template>
  <q-scroll-area class="fit">
    <q-list>
      <MenuLink v-for="link in Links" :key="link.title" v-bind="link" />
    </q-list>
  </q-scroll-area>
</template>

<script setup>
import MenuLink from './MenuLink.vue'

const Links = [
  {
    title: 'Dashboard',
    caption: '',
    icon: 'fas fa-chalkboard-teacher',
    link: '/',
    split: false,
    enable: false
  },
  {
    title: 'Todo',
    caption: '',
    icon: 'fas fa-tasks',
    link: '/Todo',
    split: false,
    enable: true
  },
  {
    title: 'Products',
    caption: '',
    icon: 'fas fa-cubes',
    link: '/Products',
    split: false,
    enable: true
  },
  {
    title: 'Stock',
    caption: '',
    icon: 'fas fa-th',
    link: '/Stock',
    split: false,
    enable: true
  },
  {
    title: ' Customeres',
    caption: '',
    icon: 'fas fa-users',
    link: '/Customers',
    split: false,
    enable: true
  },
  {
    title: 'Sales',
    caption: '',
    icon: 'fas fa-trophy',
    link: '/Sales',
    split: false,
    enable: false
  },
  {
    title: 'Suppliers',
    caption: '',
    icon: 'fas fa-puzzle-piece',
    link: '/Suppliers',
    split: false,
    enable: true
  },
  {
    title: 'Purchases',
    caption: '',
    icon: 'fas fa-shopping-cart',
    link: '/Purchases',
    split: false,
    enable: false
  },
  {
    title: 'Logistics',
    caption: '',
    icon: 'fas fa-truck',
    link: '/Logistics',
    split: false,
    enable: false
  },
  {
    title: 'Financials',
    caption: '',
    icon: 'fas fa-dollar-sign',
    link: '/Financials',
    split: false,
    enable: true
  },
  {
    title: 'Analysis',
    caption: '',
    icon: 'fas fa-search-dollar',
    link: '/Analysis',
    split: false,
    enable: true
  },
  {
    title: 'Reports',
    caption: '',
    icon: 'fas fa-print',
    link: '/Reports',
    split: false,
    enable: true
  },
  {
    title: 'About',
    caption: '',
    icon: 'fas fa-info-circle',
    link: '/About',
    split: true,
    enable: true
  }
]
</script>
