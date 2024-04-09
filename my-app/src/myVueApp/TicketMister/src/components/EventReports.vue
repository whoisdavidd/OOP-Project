<template>
    <div class="reports-page" style="margin-top:50px; margin-bottom: 50px">
      <h1 class="text-center" style="color: palegoldenrod">Event Reports</h1>
      <div class="d-flex justify-content-center">
      <button @click="downloadReport" class="btn btn-outline-dark rounded-pill btn-light">Download CSV Report</button>
    </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    name: 'EventReports',
    methods: {
      downloadReport() {
        axios({
          url: 'http://localhost:8080/reports/export', // Update to match your Spring Boot application's URL
          method: 'GET',
          responseType: 'blob', // Important: This tells axios to download the response body as a blob
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
  /* Your styles here */
  </style>
  