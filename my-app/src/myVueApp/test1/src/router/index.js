// index.js
import { createRouter, createWebHistory } from 'vue-router'
import HelloWorld from '../components/HelloWorld.vue'
import CheckoutRedirect from '../components/CheckoutRedirect.vue' // Import the component
import TestStripe from '../components/TestStripe.vue' // Import the component

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HelloWorld
    },
    {
      path: '/checkout-redirect', // Define the path
      name: 'checkoutRedirect', // Give the route a name
      component: CheckoutRedirect // Associate the component with this route
    },
    {
      path: '/test-stripe', // Define the path
      name: 'testStripe', // Give the route a name
      component: TestStripe // Associate the component with this route
    }
    // Add more routes as needed
  ]
})

export default router