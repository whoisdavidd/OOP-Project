<template>
    <div class="ticketing-officer" style="margin-top:56px;margin-bottom:56px">
      <h1 class="text-center" style="color:palegoldenrod">Add Ticketing Officer</h1>
      <form @submit.prevent="addTicketingOfficer">
        <div>
          <label for="username">Username:</label>
          <input type="text" id="username" v-model="ticketingOfficer.username" required class="form-control">
        </div>
        <div>
          <label for="password">Password:</label>
          <input type="password" id="password" v-model="ticketingOfficer.password" required class="form-control">
        </div>
        <div class="d-flex justify-content-end">
        <button class="btn btn-light btn-outline-dark rounded-pill" type="submit">Add Officer</button>
      </div>
      </form>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    name: 'TicketingOfficer',
    data() {
      return {
        ticketingOfficer: {
          username: '',
          password: '',
          email_address: '',
        
        }
      };
    },
    methods: {
      addTicketingOfficer() {
        const API_URL = 'http://localhost:8080/api/ticketingOfficer'; // Adjust if necessary
        axios.post(API_URL, this.ticketingOfficer)
          .then(response => {
            console.log(response.data);
            alert('Ticketing Officer added successfully!');
            // Reset form
            this.ticketingOfficer.username = '';
            this.ticketingOfficer.password = '';       
          })
          .catch(error => {
            console.error('There was an error adding the Ticketing Officer:', error);
            alert('Failed to add Ticketing Officer.');
          });
      }
    }
  }
  </script>
  
  <style scoped>
  .ticketing-officer {
    /* Style your component */
    max-width: 400px;
    margin: auto;
  }
  
  .ticketing-officer label {
    display: block;
    margin-top: 10px;
  }
  
  .ticketing-officer input {
    width: 100%;
    padding: 8px;
    margin-top: 5px;
  }

  </style>
  