<template>
  <div style="margin-top: 56px">
    <h2 class="text-center p-3">Events</h2>
    <!-- filter -->
    <div class="dropdown">
      <button
        class="btn dropdown-toggle"
        type="button"
        id="filterEvents"
        data-bs-toggle="dropdown"
        aria-expanded="false"
      >
        Filter
      </button>
      <ul class="dropdown-menu" aria-labelledby="filterEvents">
        <li><h6 class="dropdown-header">Filter events</h6></li>
        <li>
          <button
            @click="currentTab = 'all'"
            class="dropdown-item form-control"
            default
          >
            All
          </button>
        </li>
        <li>
          <button
            @click="currentTab = 'concerts'"
            class="dropdown-item form-control"
            default
          >
            Concerts
          </button>
        </li>
        <li>
          <button
            @click="currentTab = 'movies'"
            class="dropdown-item form-control"
            default
          >
            Movies
          </button>
        </li>
        <li>
          <button
            @click="currentTab = 'sportsEvents'"
            class="dropdown-item form-control"
            default
          >
            Sports Event
          </button>
        </li>
      </ul>
    </div>

    <div>
      <div class="row">
        <div
          class="col-md-4 justify-content-md-between"
          v-for="event in filteredEvents"
          :key="event.id"
        >
          <div class="card mx-3 my-3" style="height: 30rem">
            <div></div>
            <img
              :src="getImage(event.eventName)"
              class="card-img-top img-fluid overflow-hidden"
              alt="..."
              style="object-fit: contain; height: 300rem; width: 200rem"
            />
            <div class="card-body">
              <h5 class="card-title text-center">{{ event.eventName }}</h5>
              <p class="card-text text-center">
                Event details:
                <br /><br />
                Date: {{ event.eventDate }}<br />
                Time: {{ event.eventTime }} <br />
                <span v-if="event.duration"
                  >Duration: {{ event.duration }}<br
                /></span>
                Venue: {{ event.eventVenue }}<br />
                <span v-if="event.rating">Rating: {{ event.rating }}</span>
              </p>
              <!-- link to a book now page -->
              <button type="button" class="btn btn-dark" data-bs-toggle="modal" data-bs-target="#sellTicketModal" @click="openModal(event)">
                Sell Tickets!
            </button>
            </div>
            <!-- Modal -->
            <div class="modal fade" id="sellTicketModal" tabindex="-1" aria-labelledby="sellTicketModalLabel" aria-hidden="true">
              <div class="modal-dialog">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title text-black" id="sellTicketModalLabel">Sell Tickets</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" @click="closeModal" aria-label="Close"></button>
                  </div>
                  <div class="modal-body text-black">
                    <div class="form-group">
                      <p>Is the customer a member?</p>
                      <div>
                        <input type="radio" id="member" :value="true" v-model="isMember">
                        <label for="member">Yes</label>
                      </div>
                      <div>
                        <input type="radio" id="non-member" :value="false" v-model="isMember">
                        <label for="non-member">No</label>
                      </div>
                    </div>
          
                    <div class="form-group" v-if="isMember">
                      <label for="username">Username:</label>
                      <input type="text" id="username" v-model="username" class="form-control">
                    </div>
          
                    <div class="form-group" v-else>
                      <label for="email">Email:</label>
                      <input type="text" id="email" v-model="email" class="form-control">
                    </div>
          
                    <div class="form-group">
                      <label for="numTickets">Number of Tickets:</label>
                      <input type="number" id="numTickets" v-model.number="numTickets" class="form-control">
                    </div>
          
                    <div class="form-group">
                      <label for="ticketingOption">Ticketing Option:</label>
                      <select id="ticketingOption" v-model="ticketingOption" class="form-control">
                        <option v-for="(option, index) in selectedEvent ? selectedEvent.ticketingOptions : []" :value="option.ticketingOptionID" :key="index">{{ option.tierName }}</option>
                      </select>
                    </div>
                    <div class="form-group">
                      <label>Subtotal:</label>
                      <p>{{ subtotal }}</p>
                    </div>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" @click="closeModal">Close</button>
                    <button type="button" class="btn btn-success" @click="sellTickets">Confirm Sell</button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  
</template>

<script>
import axios from "axios";


export default {
  name: "OnsiteSale",
  data() {
    return {
      currentTab: "all",
      events: [],
      concerts: [],
      movies: [],
      sportsEvents: [],
      bookEvent: "",
      selectedEvent: null,
      showModal: false,
      isMember: false,
      ticketingOption: "",
      numTickets: 0,
      username: "",
      email: "",
    };
  },
  mounted() {
    this.getEvents();
    this.getAllConcerts();
    this.getAllMovies();
    this.getAllSportsEvent();
  },
  methods: {
    async getEvents() {
      try {
        const response = await axios.get('http://localhost:8080/api/event');
        console.log(response.data)
        this.events = this.events.concat(response.data);
        
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
    openModal(event) {
      this.showModal = true;
      this.selectedEvent = event;
      console.log(this.selectedEvent.ticketingOptions);
      console.log(this.showModal);
    },
    closeModal() {
      this.showModal = false;
      this.selectedEvent = null;
    },
    sellTickets() {
      if(this.isMember) {
        axios.post(`http://localhost:8080/ticket/createTicket/${this.numTickets}/${this.selectedEvent.eventID}/${this.ticketingOption}/${this.username}`)
        .then(response => {
            if (response.status === 200) {
              alert('Ticket sold successfully');
            } else {
              alert(response.data);
            }
          })
          .catch(error => {
            console.error(error);
          });
      } else {
        axios.post(`http://localhost:8080/ticket/onsiteSale/${this.numTickets}/${this.selectedEvent.eventID}/${this.ticketingOption}/${this.email}`)
        .then(response => {
            if (response.status === 200) {
              alert('Ticket sold successfully');
            } else {
              alert(response.data);
            }
          })
          .catch(error => {
            console.error(error);
          });
      }
    },
    getImage(eventName) {
      try {
        return require(`../img/events/${eventName}.jpeg`);
      } catch (e) {
        return require(`../img/events/noimage.jpeg`); // path to your default image
      }
    },
  },
  computed: {
    filteredEvents() {
      if (this.currentTab == "all") {
        return this.events;
      } else if (this.currentTab == "concerts") {
        return this.concerts;
      } else if (this.currentTab == "movies") {
        return this.movies;
      } else if (this.currentTab == "sportsEvents") {
        return this.sportsEvents;
      }
      return this.events;
    },
    subtotal() {
    const numTickets = Number(this.numTickets);
    if (!this.selectedEvent) {
      return 0;
    }
    const selectedOption = this.selectedEvent.ticketingOptions.find(option => option.ticketingOptionID === this.ticketingOption);
    return selectedOption ? selectedOption.tierPrice * numTickets : 0;
    },
  },
};
</script>

<style scoped>
.tabs {
  display: flex;
  justify-content: space-around;
  margin-bottom: 20px;
}

.tabs button {
  cursor: pointer;
  padding: 5px 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.tabs button:hover {
  background-color: #f0f0f0;
}
</style>
