<template>
    <div class="BookingPage">
        <h1>Book Your Event</h1>
        <p>Select an event from the dropdown and choose your dates.</p>
        <div class="event-selection">
            <label for="eventDropdown">Event:</label>
            <select id="eventDropdown" v-model="selectedEvent">
                <option disabled value="">Please select one</option>
                <option v-for="event in events" :key="event.id" :value="event.eventID">
                    {{ event.eventName }}
                </option>
            </select>
        </div>
        <!-- Form for registration -->
        <form @submit.prevent="submitForm">
            <div>
                <label for="username">Username:</label>
                <input type="text" id="username" v-model="username" placeholder="Enter username">
            </div>
            <!-- Additional inputs -->
            <div>
                <label for="ticketOption">Ticket Option:</label>
                <select id="ticketOption" v-model="ticketingOptionID">
                    <option disabled value="">Select Ticket Option</option>
                    <option v-for="option in ticketOptions" :key="option.ticketingOptionID" :value="option.ticketingOptionID">
                        {{ option.tierName }} - {{ option.tierPrice }}
                    </option>
                </select>

                <div>
                    <label for="numTickets">Number of Tickets:</label>
                    <input type="number" id="numTickets" v-model.number="numTickets" min="1" placeholder="1">
                </div>

                <div>
                    <label for="personName">Name:</label>
                    <input type="text" id="personName" v-model="personName" placeholder="Your name">
                </div>
                <div>
                    <label for="phoneNumber">Phone Number:</label>
                    <input type="tel" id="phoneNumber" v-model="phoneNumber" placeholder="Your phone number">
                </div>
                <button type="submit">Book Tickets</button>
            </div>
            <!-- Submit button and other parts remain unchanged -->
        </form>
        <p>
            Your shopping cart is shown in the bottom right of the screen. Add and remove tickets or options as you
            please - you can also change dates in the cart. Checkout when you are ready and we'll be in touch.
        </p>
        <p>
            The minimum booking period is 3 days. During peak months (July and August) we have a 7 day minimum booking
            policy. Collections are between 14:00-18:00 and returns between 08:30-11:00. Early access/late departure can
            be arranged after booking.
        </p>
    </div>
</template>
<script>
import axios from 'axios';

export default {
    name: "BookingPage",
    data() {
        return {
            selectedEvent: '',
            events: [],
            numTickets: 1, // Default to 1 ticket
            concerts: [],
            movies: [],
            sportsEvents: [],
            pickupDate: '',
            returnDate: '',
            ticketOptions: [],
            ticketingOptionID:"",
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
                const response = await axios.get('http://localhost:8080/api/event');
                this.events = response.data;
            } catch (error) {
                console.error('Error fetching events:', error);
            }
        },
        async getAllConcerts() {
            try {
                const response = await axios.get('http://localhost:8080/api/concert');
                this.concerts = response.data;
            } catch (error) {
                console.error('Error fetching concerts', error);
            }
        },
        async getAllMovies() {
            try {
                const response = await axios.get('http://localhost:8080/api/movie');
                this.movies = response.data;
            } catch (error) {
                console.error('Error fetching movies', error);
            }
        },
        async getAllSportsEvent() {
            try {
                const response = await axios.get('http://localhost:8080/api/SportsEvent');
                this.sportsEvents = response.data;
            } catch (error) {
                console.error('Error fetching sports events', error);
            }
        },
        submitForm() {
            // Ensure all required fields are selected
            if (!this.selectedEvent || !this.ticketingOptionID || !this.username) {
                console.log(this.ticketingOptionID)
                alert('Please fill in all fields.');
                return;
            }

            const url = `http://localhost:8080/ticket/createTicket/${this.numTickets}/${this.selectedEvent}/${this.ticketingOptionID}/${this.username}`;

            axios.post(url, {
                // Assuming you need to send additional data in the request body, adjust accordingly.
                // If not, you can make the POST request without a body or with other required fields.
            }).then(response => {
                console.log('Form submitted successfully', response);
                // Further actions upon success
            }).catch(error => {
                console.error('Error submitting form', error);
            });
        },
        async getTicketOptions(eventID) {
            try {
                const response = await axios.get(`http://localhost:8080/api/event/${eventID}`);
                console.log(response);
                this.ticketOptions = response.data.ticketingOptions;
                
            } catch (error) {
                console.error('Error fetching ticket options:', error);
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
    border: 1px solid #ccc;
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