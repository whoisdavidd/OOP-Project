// index.js
import { createRouter, createWebHistory } from 'vue-router'
import HelloWorld from '../components/HelloWorld.vue'
import CheckoutRedirect from '../components/CheckoutRedirect.vue' // Import the component
import StripePayment from '../components/StripePayment.vue' // Import the component
import PaymentSuccess from '../components/PaymentSuccess.vue' // Import the new components
import PaymentCancel from '../components/PaymentCancel.vue'

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
      path: '/checkout',
      name: 'checkout',
      component: StripePayment
    },
    {
      path: '/payment-success',
      name: 'success',
      component: PaymentSuccess
    },
    {
      path: '/payment-cancel',
      name: 'cancel',
      component: PaymentCancel
    }
  ]
})

export default router