<template>
    <div class="VerifyTicket">
      <div class="container" style="margin-top: 56px">
        <div class="row">
          <div class="col-3"></div>
          <div class="col-6">
            <h2 class="text-center">Verify Ticket</h2>
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
              <div class="text-left mt-3">
                <button type="submit" class="btn btn-light rounded-pill btn-outline-dark">
                  Verify
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
    };
  },
  methods: {
    async verifyTicket() {
      try {
        const response = await axios.get(`http://localhost:8080/ticket/checkTicket/${this.ticketCode}/${this.eventID}`);
        this.verificationResult = response.data;
        console.log(response.data);
      } catch (error) {
        this.verificationResult = 'Ticket is invalid';
      }
    },
  },
};
</script>

<style scoped>
.verification-result {
  border: 1px solid #000;
  padding: 10px;
  font-size: 1.5em; /* Adjust this value to match the size of your h2 */
}
</style>