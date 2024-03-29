<template>
    <div style="margin-top:56px">
      <div class="tabs">
        <button @click="currentTab = 'all'">All</button>
        <button @click="currentTab = 'concerts'">Concerts</button>
        <button @click="currentTab = 'movies'">Movies</button>
        <button @click="currentTab = 'sportsEvents'">Sports Events</button>
      </div>
      <div v-if="currentTab === 'all'">
        <h2>All Events</h2>
        <ul>
          <li v-for="event in events" :key="event.id">{{ event.name }}</li>
        </ul>
      </div>
      <div v-else-if="currentTab === 'concerts'">
        <h2>Concerts</h2>
        <ul>
          <li v-for="concert in concerts" :key="concert.id">{{ concert.name }}</li>
        </ul>
      </div>
      <div v-else-if="currentTab === 'movies'">
        <h2>Movies</h2>
        <ul>
          <li v-for="movie in movies" :key="movie.id">{{ movie.name }}</li>
        </ul>
      </div>
      <div v-else-if="currentTab === 'sportsEvents'">
        <h2>Sports Events</h2>
        <ul>
          <li v-for="sportsEvent in sportsEvents" :key="sportsEvent.id">{{ sportsEvent.name }}</li>
        </ul>
      </div>
    </div>
  </template>

<script>
import axios from 'axios';

export default {
  name: "AllEvents",
  data() {
    return {
      currentTab: "all",
      events: [],
      concerts: [],
      movies: [],
      sportsEvents: [],
    }
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
        this.events = response.data;
        
      } catch (error) {
        console.error('Error fetching events:', error);
      }
    },
   async getAllConcerts() {
    try {
        const response = await axios.get('http://localhost:8080/api/concert');
        this.concerts = response.data
    } catch (error) {
        console.error('Error fetching concerts', error);
    }
   },
   async getAllMovies() {
    try {
        const response = await axios.get('http://localhost:8080/api/movie');
        this.movies = response.data
    } catch (error) {
        console.error('Error fetching movies', error);
    }
   },
   async getAllSportsEvent() {
    try {
        const response = await axios.get('http://localhost:8080/api/SportsEvent');
        this.sportsEvents = response.data
    } catch (error) {
        console.error('Error fetching sports events', error);
    }
   },
  }
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