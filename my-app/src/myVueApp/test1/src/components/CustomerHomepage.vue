<template>
    <div class="customer-landing" style="margin-top:56px;margin-bottom:56px">
      <h1 class="text-center" style="color:palegoldenrod">Welcome to Your Event Booking Platform</h1>
      <p>Ready to book your next event? Click the button below to get started.</p>
      <button @click="bookEvent" class="btn btn-outline-dark btn-light rounded-pill p-2">Book an Event</button>
    </div>
  </template>
  
  <script>
  export default {
    name: 'CustomerHomepage',
    methods: {
      // Placeholder for your booking logic
      bookEvent() {
        alert('Booking functionality coming soon!');
        // Here, you might open a modal, redirect to a booking form, 
        // or execute any other logic for booking an event.
      }
    }
  }
  </script>
  
  <style scoped>
  .customer-landing {
    text-align: center;
    margin-top: 50px;
  }
  </style>
  