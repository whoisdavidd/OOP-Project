<template>
  <div class="home">
    <h1>Booking Page</h1>

  </div>
</template>

<script>
import axios from "axios";


export default {
  name: "BookingPage",
  data() {
    return {
      event: null,
    };
  },
  created() {
    const eventId = this.$route.params.eventId;
    this.fetchEvent(eventId);
  },
  methods: {
    async fetchEvent(eventId) {
        try {
            const response = await axios.get(`http://localhost:8080/api/event/${eventId}`);
            this.event = response.data;
        } catch (error) {
            console.log("Error fetching event: ", error);
        }
    }
  }
};
</script>

<style scoped>
.home {
  text-align: center;
  margin-top: 50px;
}
</style>
