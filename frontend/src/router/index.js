import { createRouter, createWebHistory } from 'vue-router'
import SviKoncertiView from '../views/SviKoncertiView.vue'
import NoviKoncertView from '../views/NoviKoncertView.vue'
import KoncertView from '../views/KoncertView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: SviKoncertiView,
    },
    {
      path: '/koncert/:id',
      name: 'koncert',
      component: KoncertView,
    },
    {
      path: '/novi-koncert',
      name: 'noviKoncert',
      component: NoviKoncertView,
    },
  ],
})

export default router
