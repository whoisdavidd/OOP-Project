<template>
    <div>
      Redirecting to Checkout...
    </div>
  </template>
  
  <script>
  import axios from 'axios'
  
  export default {
    name: 'CheckoutRedirect',
    mounted() {
      axios.post('/create-checkout-session')
        .then(response => {
          window.location.href = response.data
        })
        .catch(error => {
          console.error('Error creating Checkout Session:', error)
          // Handle the error, e.g., display an error message
        })
    }
  }
  </script>