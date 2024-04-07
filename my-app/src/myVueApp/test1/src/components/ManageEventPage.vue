<template>
    <div class="eventsPage" style="margin-top:10vw; margin-bottom:10vw">
        <h1>Events Page</h1>
        <div class="accordion mt-5" id="accordion">
            <div class="accordion-item">
                <h2 class="accordion-header">
                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                    Adding a new event
                </button>
                </h2>
                <div id="collapseOne" class="accordion-collapse collapse show" data-bs-parent="#accordion">
                <div class="accordion-body">
                    <form @submit.prevent="register">
                        <div class="mb-1 fw-bold">Event type:</div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="eventType" id="eventType1" v-model="eventType" value="concert" checked>
                            <label class="form-check-label" for="eventType1">
                                Concert
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="eventType" id="eventType2" v-model="eventType" value="movie">
                            <label class="form-check-label" for="eventType2">
                                Movie
                            </label>
                        </div>
                        <div class="form-check mb-4">
                            <input class="form-check-input" type="radio" name="eventType" id="eventType3" v-model="eventType" value="sportsEvent">
                            <label class="form-check-label" for="eventType3">
                                Sports Event
                            </label>
                        </div>
                        <strong>Event Name:</strong>
                        <div class="mb-3">
                            <input type="text" name="eventName" class="form-control" v-model="eventName" placeholder="Taylor Swift Concert" required>
                        </div>
                        <strong>Event Venue:</strong>
                        <div class="mb-3">
                            <input type="text" name="eventVenue" class="form-control" v-model="eventVenue" placeholder="National Stadium" required>
                        </div>
                        <strong>Event Date and Time:</strong>
                        <div class="mb-3">
                            <input type="datetime-local" name="eventDateAndTime" class="form-control" v-model="eventDateAndTime" required>
                        </div>
                        <strong>Cancellation Fee (if desired):</strong>
                        <div class="mb-3">
                            <input type="number" name="cancellationFee" class="form-control" v-model="cancellationFee">
                        </div>
                        <strong>Ticketing Options: <button class="ms-3 btn btn-warning btn-sm" @click="addTicketingOption">Add ticketing option</button></strong> 
                        <div v-for="(to,index) in ticketingOptions" :key="to.tierName">
                            <div class="mt-3">Tier {{index+1}} Name:</div>
                            <input type="text" :name="'tier'+ (index+1) + 'Name'" :v-model="to.tierName" placeholder="CAT 1" class="form-control">
                            <div class="mt-3">Tier {{index+1}} Price ($):</div>
                            <input type="number" :name="'tier'+ (index+1) + 'Price'" :v-model="to.tierPrice" placeholder="300" class="form-control">
                            <div class="mt-3">Number of tickets available for tier {{index+1}}:</div>
                            <input type="number" :name="'tier'+ (index+1) + 'Quantity'" :v-model="to.tier" placeholder="50" class="form-control">
                            <hr v-if="index + 1 !== ticketingOptions.length" class="mt-4 mb-4" style="border-width:3px">
                        </div>
                        <div v-if="eventType == 'concert'">
                            <strong>Performers: <button class="ms-3 btn btn-warning btn-sm" @click="addPerformer">Add performer</button></strong>
                            <div v-for="(p,index) in performers" :key="p">
                                <div class="mt-3">Performer {{index+1}} Name:</div>
                                <input type="text" :name="'performer'+ (index+1) + 'Name'" :v-model="p" placeholder="Taylor Swift" class="form-control">
                                <hr v-if="index + 1 !== performers.length" class="mt-4 mb-4" style="border-width:3px">
                            </div>
                        </div>
                        <div v-if="eventType == 'movie'">
                            <strong>Main Cast Members: <button class="ms-3 btn btn-warning btn-sm" @click="addMainCast">Add cast member</button></strong>
                            <div v-for="(mc,index) in mainCast" :key="mc">
                                <div class="mt-3">Cast Member {{index+1}} Name:</div>
                                <input type="text" :name="'cast'+ (index+1) + 'Name'" :v-model="mc" placeholder="Brad Pitt" class="form-control">
                                <hr v-if="index + 1 !== mainCast.length" class="mt-4 mb-4" style="border-width:3px">
                            </div>
                            Movie Rating:
                            <div class="mb-3">
                                <input type="text" name="rating" class="form-control" v-model="rating" placeholder="PG13" required>
                            </div>
                            Movie Duration:
                            <div class="mb-3">
                                <input type="number" name="duration" class="form-control" v-model="duration" required>
                            </div>
                        </div>
                        <div v-if="eventType == 'sportsEvent'">
                            <strong>Participants: <button class="ms-3 btn btn-warning btn-sm" @click="addParticipant">Add participant</button></strong>
                            <div v-for="(p,index) in participants" :key="p">
                                <div class="mt-3">Participant {{index+1}} Name:</div>
                                <input type="text" :name="'participant'+ (index+1) + 'Name'" :v-model="p" placeholder="Liverpool" class="form-control">
                                <hr v-if="index + 1 !== participants.length" class="mt-4 mb-4" style="border-width:3px">
                            </div>
                            Sport:
                            <div class="mb-3">
                                <input type="text" name="sport" class="form-control" v-model="sport" placeholder="Football" required>
                            </div>
                        </div>
                        <div class="mt-3">
                            <button type="submit" @click="addEvent" class="btn btn-primary">Add Event</button>
                        </div>
                    </form>
                </div>
                </div>
                <div class="modal fade" data-bs-backdrop="static" id="addEventModal" tabindex="-1" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5">Adding event result</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body" id="addEventModalBody">

                        </div>
                        </div>
                    </div>
                </div>
        </div>
        <div class="accordion-item">
            <h2 class="accordion-header">
            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                Manage events
            </button>
            </h2>
            <div id="collapseTwo" class="accordion-collapse collapse" data-bs-parent="#accordion">
            <div class="accordion-body">
                <strong>This is the second item's accordion body.</strong> It is hidden by default, until the collapse plugin adds the appropriate classes that we use to style each element. These classes control the overall appearance, as well as the showing and hiding via CSS transitions. You can modify any of this with custom CSS or overriding our default variables. It's also worth noting that just about any HTML can go within the <code>.accordion-body</code>, though the transition does limit overflow.
            </div>
            </div>
        </div>
</div>
    </div>
</template>

<script>
/* eslint-disable */
import * as bootstrap from 'bootstrap';
import axios from 'axios';
export default {
    name: 'ManageEventPage',
    data(){
        return {
            eventType: "concert",
            eventName: "",
            eventVenue: "",
            eventDateAndTime: "",
            eventDate: "",
            eventTime: "",
            cancellationFee: 0.0,
            ticketingOptions: [{"tierName": "", "tierPrice": 0, "tierQuantity": 0}],
            performers: [""],
            mainCast: [""],
            rating: "",
            duration: 0,
            participants: [""],
            sport: ""
        }
    },
    mounted(){
        this.eventDateAndTime = new Date();
    },
    methods: {
        addTicketingOption(){
            this.ticketingOptions.push({
                "tierName" : "",
                "tierPrice" : 0,
                "tierQuantity" : 0
            })
        },
        addPerformer(){
            this.performers.push("")
        },
        addMainCast(){
            this.mainCast.push("")
        },
        addParticipant(){
            this.participants.push("")
        },
        addEvent(){
            this.handleDateTime();
            if (this.eventType == 'concert'){
                axios.post('http://localhost:8080/api/concert', {
                    eventName: this.eventName,
                    eventVenue: this.eventVenue,
                    eventDate: this.eventDate,
                    eventTime: this.eventTime,
                    cancellationFee: this.cancellationFee,
                    ticketingOptions: this.ticketingOptions,
                    performers : this.performers
                })
                .then(function () {
                    modal1.show()
                    let body = document.getElementById('addEventModalBody')
                    body.innerText = "The adding of event was successful"
                })
                .catch(function (error) {
                    modal1.show()
                    let body = document.getElementById('addEventModalBody')
                    body.innerText = "The adding of event has failed: " + error.response.data
                });
            }else if (this.eventType == 'movie'){
                axios.post('http://localhost:8080/api/movie', {
                    eventName: this.eventName,
                    eventVenue: this.eventVenue,
                    eventDate: this.eventDate,
                    eventTime: this.eventTime,
                    cancellationFee: this.cancellationFee,
                    ticketingOptions: this.ticketingOptions,
                    mainCast : this.mainCast,
                    rating: this.rating,
                    duration: this.duration
                })
                .then(function () {
                    modal1.show()
                    let body = document.getElementById('addEventModalBody')
                    body.innerText = "The adding of event was successful"
                })
                .catch(function (error) {
                    modal1.show()
                    let body = document.getElementById('addEventModalBody')
                    body.innerText = "The adding of event has failed: " + error.response.data
                });
            }else{
                axios.post('http://localhost:8080/api/sportsEvent', {
                    eventName: this.eventName,
                    eventVenue: this.eventVenue,
                    eventDate: this.eventDate,
                    eventTime: this.eventTime,
                    cancellationFee: this.cancellationFee,
                    ticketingOptions: this.ticketingOptions,
                    participants : this.participants,
                    sport: this.sport
                })
                .then(function () {
                    modal1.show()
                    let body = document.getElementById('addEventModalBody')
                    body.innerText = "The adding of event was successful"
                })
                .catch(function (error) {
                    modal1.show()
                    let body = document.getElementById('addEventModalBody')
                    body.innerText = "The adding of event has failed: " + error.response.data
                });
            }
        },
        handleDateTime(){
            let date = this.eventDateAndTime.split('T')[0]
            let time = this.eventDateAndTime.split('T')[1]
            let dateSplit = date.split('-')
            let timeSplit = time.split(":")
            this.eventDate = dateSplit[2] + dateSplit[1] + dateSplit[0]
            this.eventTime = timeSplit[0] + timeSplit[1];
        }
    }
}
</script>

<style scoped>
.eventsPage {
    margin-left: 10vw;
    margin-right: 10vw;
    padding: 20px;
    box-shadow: 2px 2px 4px 2px gray;
}

input{
    margin-bottom:10px;
}

</style>