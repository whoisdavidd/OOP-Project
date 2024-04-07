// index.js
import { createRouter, createWebHistory } from 'vue-router'
import HelloWorld from '../components/HelloWorld.vue'
import CheckoutRedirect from '../components/CheckoutRedirect.vue' // Import the component
import StripePayment from '../components/StripePayment.vue' // Import the component
import PaymentSuccess from '../components/PaymentSuccess.vue' // Import the new components
import PaymentCancel from '../components/PaymentCancel.vue'
import Login from '../components/Login.vue'
import homepage from '../components/HomePage.vue'
import Events from '../components/Events.vue'
import CustomerHomepage from '@/components/CustomerHomepage.vue'
import RegistrationPage from '@/components/Registration.vue'
import VerifyTicket from '@/components/VerifyTicket.vue'
import ManageEventPage from '@/components/ManageEventPage.vue'

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
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/homepage',
      name: 'HomePage',
      component: homepage
    },
    {
      path: '/events',
      name: 'Events',
      component: Events
    },
    {
      path: '/CustomerHomepage',
      name: 'CustomerHomepage',
      component: CustomerHomepage
    },
    {
      path: '/RegistrationPage',
      name: 'RegistrationPage',
      component: RegistrationPage
    },
    {
      path: '/ManageEvents',
      name: 'ManageEventPage',
      component: ManageEventPage
    }
    ,
    {
      path: '/VerifyTicket',
      name: 'VerifyTicket',
      component: VerifyTicket
    }
    
  ]
})

export default router