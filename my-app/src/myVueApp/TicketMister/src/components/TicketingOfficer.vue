<template>
    <div class="ticketing-officer">
      <h1>Add Ticketing Officer</h1>
      <form @submit.prevent="addTicketingOfficer">
        <div>
          <label for="username">Username:</label>
          <input type="text" id="username" v-model="ticketingOfficer.username" required>
        </div>
        <div>
          <label for="password">Password:</label>
          <input type="password" id="password" v-model="ticketingOfficer.password" required>
        </div>
        <div>
          <label for="email">Email Address:</label>
          <input type="email" id="email" v-model="ticketingOfficer.email_address" required>
        </div>
        <button type="submit">Add Officer</button>
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
            this.ticketingOfficer.email_address = '';
       
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
  
  button {
    margin-top: 20px;
    width: 100%;
    padding: 10px;
    background-color: #5cb85c;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }
  
  button:hover {
    background-color: #4cae4c;
  }
  </style>
  