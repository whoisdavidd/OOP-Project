<template>
  <div style="margin-top: 56px">
    <h2 class="text-center p-3">Events</h2>
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
    <!-- <div class="tabs">
        <button @click="currentTab = 'all'">All</button>
        <button @click="currentTab = 'concerts'">Concerts</button>
        <button @click="currentTab = 'movies'">Movies</button>
        <button @click="currentTab = 'sportsEvents'">Sports Events</button>
      </div> -->
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
              :src="require(`../img/events/${event.eventName}.jpg`)"
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
              <button class="btn btn-dark" @click="bookTicket(event)">
                Book now!
              </button>
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
  name: "AllEvents",
  data() {
    return {
      currentTab: "all",
      events: [],
      concerts: [],
      movies: [],
      sportsEvents: [],
      bookEvent: "",
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
    bookTicket(bookEvent) {
      const username = sessionStorage.getItem("user");
      if (!username || username == null) {
        alert("Please log into your account first");
        this.$router.push("/login");
      } else {
        try {
          this.$router.push({path: "/booking-page", params: {
            eventId: bookEvent.eventId
          } });
        } catch (err) {
          console.log("An error occurred: " + err);
        }
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
