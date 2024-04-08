<template>
    <div class="home">
        <!-- Display the welcome message with the username -->
        <h1>Welcome, {{ username }}!</h1>
        <div class="events-page">
            <section class="top-picks">
                <h2>Top Picks</h2>
                <div class="events-grid">
                    <!-- Event items looped through a list -->
                    <div class="event" v-for="event in topPicks" :key="event.id">
                        <img :src="event.imageUrl" :alt="event.name">
                        <h3>{{ event.eventName }}</h3>
                        <!-- additional event details -->
                    </div>
                </div>
            </section>

            <section class="popular-events">
                <h2>Popular Events</h2>
                <div class="events-grid">
                    <!-- Event items looped through a list -->
                    <div class="event" v-for="event in popularEvents" :key="event.id">
                        <img :src="event.imageUrl" :alt="event.name">
                        <h3>{{ event.eventName }}</h3>
                        <!-- additional event details -->
                    </div>
                </div>
            </section>

            <section class="venues">
                <h2>Venues</h2>
                <div class="venues-grid">
                    <!-- Venue items looped through a list -->
                    <div class="venue" v-for="venue in venuesList" :key="venue.id">
                        <img :src="venue.imageUrl" :alt="venue.name">
                        <h3>{{ venue.eventVenue }}</h3>
                        <!-- additional venue details -->
                    </div>
                </div>
            </section>

        </div>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    name: 'HomePage',
    data() {
        return {
            // Initialize username with an empty string
            username: '',
            topPicks: [],
            popularEvents: [],
            venuesList: []
        };
    },
    mounted() {
        // Retrieve the username from sessionStorage when the component mounts
        this.username = sessionStorage.getItem('username');
        // 'Guest' is a fallback in case there's no username in sessionStorage
        this.fetchTopPicks();
        this.fetchPopularEvents();
        this.fetchVenues();
    },
    methods: {
        fetchTopPicks() {
            axios.get('http://localhost:8080/api/event')
                .then(response => {
                    console.log(response.data);
                    this.topPicks = response.data;
                })
                .catch(error => {
                    console.error('Error fetching top picks:', error);
                });
        },
        fetchPopularEvents() {
            axios.get('http://localhost:8080/api/event')
                .then(response => {
                    this.popularEvents = response.data;
                })
                .catch(error => {
                    console.error('Error fetching popular events:', error);
                });
        },
        fetchVenues() {
            axios.get('http://localhost:8080/api/event')
                .then(response => {
                    this.venuesList = response.data;
                })
                .catch(error => {
                    console.error('Error fetching venues:', error);
                });
        },
    }
}
</script>

<style scoped>
.home {
    text-align: center;
    margin-top: 50px;
}

.events-page {
    /* Add your CSS styles here */
}

.events-grid {
    display: flex;
    flex-wrap: wrap;

}

.venues-grid {
    display: flex;
    flex-wrap: wrap;

}

.event {
    width: 20%;
    margin: 10px;

}

.venue {
    width: 20%;
    margin: 10px;

}
</style>
