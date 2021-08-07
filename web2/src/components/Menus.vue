<template>
  <q-scroll-area class="fit">
    <q-list>
      <MenuLink
        v-for="link in Links"
        :key="link.title"
        v-bind="link"
      />
    </q-list>
  </q-scroll-area>
</template>

<script>
import { defineComponent } from 'vue'
import MenuLink from 'components/MenuLink.vue'

const menus = [
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
    title: 'Productions',
    caption: '',
    icon: 'fas fa-cubes',
    link: '/Productions',
    split: false,
    enable: true
  },
  {
    title: ' Customeres',
    caption: '',
    icon: 'fas fa-people-carry',
    link: '/Customeres',
    split: false,
    enable: false
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
    title: 'Finance',
    caption: '',
    icon: 'fas fa-dollar-sign',
    link: '/Finance',
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

export default defineComponent({
  name: 'Menus',

  components: {
    MenuLink
  },

  setup() {
    return {
      Links: menus
    }
  }
})
</script>
