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
import BookingPage from '@/components/BookingPage.vue'
import BookingHistory from '@/components/BookingHistory.vue'
import Profile from '@/components/Profile.vue'
import EventReports from '@/components/EventReports.vue'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: "/",
      name: "home",
      component: HelloWorld,
    },
    {
      path: "/checkout-redirect", // Define the path
      name: "checkoutRedirect", // Give the route a name
      component: CheckoutRedirect, // Associate the component with this route
    },

    {
      path: '/checkoutPage',
      name: 'checkoutPage',
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
      path: '/BookingPage',
      name: 'BookingPage',
      component: BookingPage
    }
    ,
    {
      path: '/VerifyTicket',
      name: 'VerifyTicket',
      component: VerifyTicket
    },
    {
      path: '/BookingHistory',
      name: 'BookingHistory',
      component: BookingHistory
    },
    {
      path: '/Profile',
      name: 'Profile',
      component: Profile
    },
    {
      path: '/EventReports',
      name: 'EventReports',
      component: EventReports
    }
    
    
  ]
})

export default router;
