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
                        <img :src="getImageUrlForEvent(event.eventName)" :alt="event.name">
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
                        <img :src="getImageUrlForEvent(event.eventName)" @error="setDefaultImage" :alt="event.name">
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
                        <img :src="getImageUrlForVenue(venue)"  @error="setDefaultImage" :alt="venue.name">
                        <h3>{{ venue }}</h3>
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
            venuesList: [],


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
                    // Assuming each venue has a unique 'id'
                    const uniqueVenues = new Map();
                    console.log(response.data)
                    response.data.forEach(eventVenue => {
                        // If the venue isn't already in the map, add it
                        if (!uniqueVenues.has(eventVenue.eventVenue)) {
                            uniqueVenues.set(eventVenue.eventVenue, eventVenue.eventVenue);
                            console.log(uniqueVenues)
                        }
                    });

                    // Convert the Map values back to an array and update venuesList

                    this.venuesList = Array.from(uniqueVenues.values());
                    console.log(this.venuesList)
                })
                .catch(error => {
                    console.error('Error fetching venues:', error);
                });
        },
        getImageUrlForEvent(eventName) {

            return require(`@/img/events/${eventName}.jpeg`);
        },
        getImageUrlForVenue(eventVenue) {

            return require(`@/img/Venues/${eventVenue}.jpeg`); // Fallback image
        },
        setDefaultImage(event) {
        // Set the src attribute of the event target (the img element) to a default image path
        event.target.src = require('@/assets/default-img.jpeg'); // Adjust the path to your default image
    },

    }
}
</script>

<style scoped>
.home {
    text-align: center;
    margin-top: 50px;
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

.event img,
.venue img {
    width: 100%;
    /* Make the image fully occupy its container's width */
    height: 200px;
    /* Set a fixed height for the images */
    object-fit: cover;
    /* Cover the container with the image without losing its aspect ratio */
    border-radius: 8px;
    /* Optional: Adds rounded corners to the images */
}
</style>
