<template>
    <div class="VerifyTicket">
      <div class="container" style="margin-top: 56px; margin-bottom:50px">
        <div class="row">
          <div class="col-3"></div>
          <div class="col-6">
            <h2 class="text-center" style="color:palegoldenrod">Verify Ticket</h2>
          </div>
          <div class="col-3"></div>
        </div>
        <div class="row">
          <div class="col-3"></div>
          <div class="col-6">
            <form @submit.prevent="verifyTicket">
              <div class="form-group">
                <label for="ticketCode">Ticket Code:</label>
                <input type="text" class="form-control" id="ticketCode" v-model="ticketCode" required>
              </div>
              <div class="form-group">
                <label for="eventID">Event ID:</label>
                <input type="text" class="form-control" id="eventID" v-model="eventID" required>
              </div>
              <div class="text-left mt-3 d-flex justify-content-end">
                <button type="submit" class="btn btn-light rounded-pill btn-outline-dark">
                  Verify
                </button>
                <button type="button" class="btn btn-light rounded-pill btn-outline-dark" @click="markAttendance" v-if="isTicketVerified">
                  Mark Attendance
                </button>
              </div>
            </form>
            <div v-if="verificationResult" class="mt-4 d-flex align-items-center">
              <h2>Verification Result:</h2>
              <div class="verification-result">
                <p>{{ verificationResult }}</p>
              </div>
            </div>
          </div>
          <div class="col-3"></div>
        </div>
      </div>
    </div>
</template>
  
<script>
import axios from 'axios';

export default {
  data() {
    return {
      ticketCode: '',
      eventID: '',
      verificationResult: '',
      isTicketVerified: false
    };
  },
  methods: {
    async verifyTicket() {
      try {
        const response = await axios.get(`http://localhost:8080/ticket/checkTicket/${this.ticketCode}/${this.eventID}`);
        this.verificationResult = response.data;
        console.log(response.data);
        if (response.data === 'Ticket is valid') {
          this.isTicketVerified = true;
        }
        
      } catch (error) {
        this.verificationResult = 'Ticket is invalid';
      }
    },
    markAttendance() {
      axios.put(`http://localhost:8080/ticket/markAttendance/${this.ticketCode}`)
        .then(response => {
          console.log(response);
          // Handle the response here
          alert('Attendance marked successfully')
        })
        .catch(error => {
          console.error(error);
          // Handle the error here
          alert('Error marking attendance, please try again!')
        });
    },
  }    
    
};
</script>

<style scoped>
.verification-result {
  border: 1px solid #000;
  padding: 10px;
  font-size: 1.5em; /* Adjust this value to match the size of your h2 */
}
</style>