<template>
    <div class="customer-landing">
      <h1>Welcome to Your Event Booking Platform</h1>
      <p>Ready to book your next event? Click the button below to get started.</p>
      <button @click="bookEvent">Book an Event</button>
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
  
  button {
    background-color: #4CAF50; /* Green */
    border: none;
    color: white;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
  }
  
  /* Add :hover state for the button for better user experience */
  button:hover {
    background-color: #45a049;
  }
  </style>
  