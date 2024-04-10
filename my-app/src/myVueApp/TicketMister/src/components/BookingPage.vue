<template>
  <div class="BookingPage" >
    <div >
    <h1 style="color:palegoldenrod">Book Your Event</h1>
    <p style="color: teal">
      Select an event from the dropdown and choose your dates.
    </p>
    <div class="event-selection form-group d-flex align-items-center">
      <label for="eventDropdown" class="me-2">Event:</label>
      <select id="eventDropdown" v-model="selectedEvent" class="form-control">
        <option disabled selected value="">Please select one</option>
        <option v-for="event in events" :key="event.id" :value="event.eventID">
          {{ event.eventName }}
        </option>
      </select>
    </div>
    <!-- Form for registration -->
    <form @submit.prevent="submitForm">
      <!-- <div class="form-group py-2 d-flex align-items-center">
        <label for="username" class="me-2">Username:</label>
        <input
          type="text"
          id="username"
          v-model="username"
          placeholder="Enter username"
          class="form-control"
        />
      </div> -->
      <!-- Additional inputs -->
      <div>
        <div class="form-group d-flex align-items-center py-2">
          <label for="ticketOption" class="me-2">Ticket Option:</label>
          <select
            id="ticketOption"
            v-model="ticketingOptionID"
            class="form-control"
          >
            <option disabled selected value="">Select Ticket Option</option>
            <option
              v-for="option in ticketOptions"
              :key="option.ticketingOptionID"
              :value="option.ticketingOptionID"
            >
              {{ option.tierName }} - {{ option.tierPrice }}
            </option>
          </select>
        </div>

        <div class="form-group d-flex align-items-center py-2">
          <label for="numTickets">Number of Tickets:</label>
          <input
            type="number"
            id="numTickets"
            v-model.number="numTickets"
            min="1"
            max="5"
            placeholder="1"
            class="form-control"
          />
        </div>

        <div class="form-group py-2 d-flex align-items-center">
          <label for="personName" class="me-2">Name:</label>
          <input
            type="text"
            id="personName"
            v-model="personName"
            placeholder="Your name"
            class="form-control"
          />
        </div>
        <div class="form-group d-flex align-items-center py-2">
          <label for="phoneNumber" class="me-2">Phone Number:</label>
          <input
            type="tel"
            id="phoneNumber"
            v-model="phoneNumber"
            placeholder="Your phone number"
            class="form-control"
          />
        </div>
        <div class="d-flex justify-content-end">
          <button
            type="submit"
            class="btn btn-light btn-outline-dark rounded-pill"
          >
            Book Tickets
          </button>
        </div>
      </div>
      <!-- Submit button and other parts remain unchanged -->
    </form>
</div>
    <div class="py-4 mt-5 px-3 rounded-4" style="background-color: rgba(128, 128, 128, 0.7);">
      <p>
        Add and remove tickets or options as you please. Checkout when you are ready and we'll be in touch.
      </p>
      <p>
        Customers can book tickets up to 6 months in advance and no later than 24 hours before the event start time. Customers can cancel their booking up to 48 hours before the event.
      </p>
    </div>
    <div v-if="formErrors.length" class="form-errors">
      <ul>
        <li v-for="error in formErrors" :key="error">{{ error }}</li>
      </ul>
    </div>
  </div>
</template>
<script>
import axios from "axios";

export default {
  name: "BookingPage",
  data() {
    return {
      selectedEvent: "",
      price: 0,
      events: [],
      numTickets: 1, // Default to 1 ticket
      concerts: [],
      movies: [],
      sportsEvents: [],
      pickupDate: "",
      returnDate: "",
      ticketOptions: [],
      ticketingOptionID: "",
      formErrors: [],
      username: sessionStorage.getItem("username"),
    };
  },
  mounted() {
    this.getEvents();
    this.getAllConcerts();
    this.getAllMovies();
    this.getAllSportsEvent();
  },
  watch: {
    async selectedEvent(selectedEvent) {
      if (selectedEvent) {
        console.log(selectedEvent);
        await this.getTicketOptions(selectedEvent);
      } else {
        this.ticketOptions = [];
      }
    },
  },
  methods: {
    async getEvents() {
      try {
        const response = await axios.get("http://localhost:8080/api/event");
        this.events = response.data;
      } catch (error) {
        console.error("Error fetching events:", error);
      }
    },
    async getAllConcerts() {
      try {
        const response = await axios.get("http://localhost:8080/api/concert");
        this.concerts = response.data;
      } catch (error) {
        console.error("Error fetching concerts", error);
      }
    },
    async getAllMovies() {
      try {
        const response = await axios.get("http://localhost:8080/api/movie");
        this.movies = response.data;
      } catch (error) {
        console.error("Error fetching movies", error);
      }
    },
    async getAllSportsEvent() {
      try {
        const response = await axios.get(
          "http://localhost:8080/api/SportsEvent"
        );
        this.sportsEvents = response.data;
      } catch (error) {
        console.error("Error fetching sports events", error);
      }
    },
    validateForm() {
      this.formErrors = []; // Reset errors

      if (!this.selectedEvent) {
        this.formErrors.push("Please select an event.");
      }
      if (!this.ticketingOptionID) {
        this.formErrors.push("Please select a ticketing option.");
      }
      // if (!this.username) {
      //   this.formErrors.push("Please enter a username.");
      // }
      console.log(this.formErrors);
      return this.formErrors.length == 0; // Form is valid if no errors
    },
    submitForm() {
      // Ensure all required fields are selected
      if (!this.selectedEvent || !this.ticketingOptionID) {
        // If the form is invalid, stop here
        return;
      }
      const selectedEventObj = this.events.find(
        (event) => event.eventID === this.selectedEvent
      );
      const selectedTicketOptionObj = this.ticketOptions.find(
        (option) => option.ticketingOptionID === this.ticketingOptionID
      );

      // const selectedTicketPrice = this.ticketOptions.find(option => option.ticketingOptionID === this.ticketingOptionID);

      const url = `http://localhost:8080/ticket/createTicket/${this.numTickets}/${this.selectedEvent}/${this.ticketingOptionID}/${this.username}`;
      
      const bookingDetails = {
        numTickets: this.numTickets,
        selectedEvent: this.selectedEvent, // Keeping the event ID if needed for the backend
        selectedEventName: selectedEventObj.eventName, // The event name for display purposes
        ticketingOptionID: this.ticketingOptionID, // Keeping the ticket option ID if needed for the backend
        ticketOptionName: selectedTicketOptionObj.tierName, // The ticket option name for display purposes
        username: sessionStorage.getItem("username") || this.username,
        price: selectedTicketOptionObj.tierPrice,
      };
      this.$router.push({
        name: "checkoutPage",
        query: bookingDetails,
      });
      axios
        .post(url, {
          // Assuming you need to send additional data in the request body, adjust accordingly.
          // If not, you can make the POST request without a body or with other required fields.
        })
        .then((response) => {
          console.log("Form submitted successfully", response);
          // Further actions upon success
        })
        .catch((error) => {
          console.error("Error submitting form", error);
        });
    },
    async getTicketOptions(eventID) {
      try {
        const response = await axios.get(
          `http://localhost:8080/api/event/${eventID}`
        );
        console.log(response);
        this.ticketOptions = response.data.ticketingOptions;
      } catch (error) {
        console.error("Error fetching ticket options:", error);
        this.ticketOptions = [];
      }
    },
  },
};
</script>

<!-- Existing styles can remain the same -->

<style scoped>
.BookingPage {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  text-align: center;
}

.event-selection,
.date-selection {
  margin: 20px 0;
}

.event-selection label,
.date-selection input[type="date"] {
  padding: 10px;
  border-radius: 5px;
}

.event-selection select {
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  appearance: none;
  /* This is to remove default styling, may need additional styling */
}
</style>
r
