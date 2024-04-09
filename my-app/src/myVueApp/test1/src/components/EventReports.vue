<template>
    <div class="reports-page" style="margin-top:50px; margin-bottom: 50px">
      <h1 class="text-center" style="color: palegoldenrod">Event Reports</h1>
      <div class="d-flex justify-content-center">
      
      <!-- Date range inputs for report generation -->
      <div class="date-range-inputs">
        <div>
          <label for="start-date">Start Date:</label>
          <input type="date" id="start-date" v-model="startDate" />
        </div>
        <div>
          <label for="end-date">End Date:</label>
          <input type="date" id="end-date" v-model="endDate" />
        </div>
      </div>
  
      <!-- Download button -->
      <button @click="downloadReport" class="btn btn-outline-dark rounded-pill btn-light">Download CSV Report</button>
    </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    name: 'EventReports',
    data() {
      return {
        startDate: null,
        endDate: null,
      };
    },
    methods: {
      downloadReport() {
        axios({
          url: 'http://localhost:8080/reports/export', // Update to match your Spring Boot application's URL
          method: 'GET',
          responseType: 'blob', // Important: This tells axios to download the response body as a blob
          params: {
            // Passing the date range as query parameters
            startDate: this.startDate,
            endDate: this.endDate,
          },
        })
        .then((response) => {
          // Create a URL for the blob
          const url = window.URL.createObjectURL(new Blob([response.data]));
          // Create an anchor element and set the URL as its href
          const link = document.createElement('a');
          link.href = url;
          link.setAttribute('download', 'event_report.csv'); // Set the file name for the download
          document.body.appendChild(link);
          link.click(); // Trigger the download
          link.remove(); // Clean up
        })
        .catch((error) => console.error('Error downloading the report:', error));
      },
    },
  };
  </script>
  
  <style>
  .reports-page {
    margin-top: 20px;
    text-align: center;
  }
  
  .date-range-inputs {
    margin-bottom: 20px;
  }
  
  .date-range-inputs div {
    margin-bottom: 10px;
  }
  
  .date-range-inputs label {
    margin-right: 10px;
  }
  
  button {
    padding: 10px 20px;
    cursor: pointer;
    background-color: #5cb85c;
    color: white;
    border: none;
    border-radius: 5px;
    font-size: 16px;
  }
  
  button:hover {
    background-color: #4cae4c;
  }
  </style>
  