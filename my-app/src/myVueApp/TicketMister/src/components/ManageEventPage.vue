<template>
    <div class="eventsPage" style="background-color:white;margin-top:10vw; margin-bottom:10vw">
        <h1 class="text-black">Events Page</h1>
        <div class="accordion mt-5" id="accordionEvents">
            <div class="accordion-item">
                <h2 class="accordion-header">
                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                    Adding a new event
                </button>
                </h2>
                <div id="collapseOne" class="accordion-collapse collapse show" data-bs-parent="#accordionEvents">
                <div class="accordion-body">
                    <form @submit.prevent="register">
                        <div class="mb-1 fw-bold">Event type:</div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="eventType" id="eventType1" v-model.lazy="eventType" value="concert" checked>
                            <label class="form-check-label" for="eventType1">
                                Concert
                            </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="eventType" id="eventType2" v-model.lazy="eventType" value="movie">
                            <label class="form-check-label" for="eventType2">
                                Movie
                            </label>
                        </div>
                        <div class="form-check mb-4">
                            <input class="form-check-input" type="radio" name="eventType" id="eventType3" v-model.lazy="eventType" value="sportsEvent">
                            <label class="form-check-label" for="eventType3">
                                Sports Event
                            </label>
                        </div>
                        <strong>Event Name:</strong>
                        <div class="mb-3">
                            <input type="text" name="eventName" class="form-control" v-model.lazy="eventName" placeholder="Taylor Swift Concert" required>
                        </div>
                        <strong>Event Venue:</strong>
                        <div class="mb-3">
                            <input type="text" name="eventVenue" class="form-control" v-model.lazy="eventVenue" placeholder="National Stadium" required>
                        </div>
                        <strong>Event Date and Time:</strong>
                        <div class="mb-3">
                            <input type="datetime-local" name="eventDateAndTime" class="form-control" v-model.lazy="eventDateAndTime" required>
                        </div>
                        <strong>Cancellation Fee (if desired):</strong>
                        <div class="mb-3">
                            <input type="number" name="cancellationFee" class="form-control" v-model.lazy="cancellationFee">
                        </div>
                        <hr style="border-width:3px; color:blue">
                        <strong>Ticketing Options: <button class="ms-3 btn btn-warning btn-sm" @click="addTicketingOption">Add ticketing option</button></strong> 
                        <div v-for="(to,index) in ticketingOptions" :key="to.tierName">
                            <div class="mt-3">Tier {{index+1}} Name:</div>
                            <input type="text" :name="'tier'+ (index+1) + 'Name'" v-model.lazy="to.tierName" placeholder="CAT 1" class="form-control">
                            <div class="mt-3">Tier {{index+1}} Price ($):</div>
                            <input type="number" :name="'tier'+ (index+1) + 'Price'" v-model.lazy="to.tierPrice" placeholder="300" class="form-control">
                            <div class="mt-3">Number of tickets available for tier {{index+1}}:</div>
                            <input type="number" :name="'tier'+ (index+1) + 'Quantity'" v-model.lazy="to.tierQuantity" placeholder="50" class="form-control">
                            <hr v-if="index + 1 !== ticketingOptions.length" class="mt-4 mb-4" style="border-width:1px">
                        </div>
                        <hr style="border-width:3px; color:blue">
                        <div v-if="eventType == 'concert'">
                            <strong>Performers: <button class="ms-3 btn btn-warning btn-sm" @click="addPerformer">Add performer</button></strong>
                            <div v-for="(p,index) in performers" :key="p.performerName">
                                <div class="mt-3">Performer {{index+1}} Name:</div>
                                <input type="text" :name="'performer'+ (index+1) + 'Name'" v-model.lazy="p.performerName" placeholder="Taylor Swift" class="form-control">
                                <hr v-if="index + 1 !== performers.length" class="mt-4 mb-4" style="border-width:1px">
                            </div>
                            <hr style="border-width:3px; color:blue">
                        </div>
                        <div v-if="eventType == 'movie'">
                            <strong>Main Cast Members: <button class="ms-3 btn btn-warning btn-sm" @click="addMainCast">Add cast member</button></strong>
                            <div v-for="(mc,index) in mainCast" :key="mc.castMemberName">
                                <div class="mt-3">Cast Member {{index+1}} Name:</div>
                                <input type="text" :name="'cast'+ (index+1) + 'Name'" v-model.lazy="mc.castMemberName" placeholder="Brad Pitt" class="form-control">
                                <hr v-if="index + 1 !== mainCast.length" class="mt-4 mb-4" style="border-width:1px">
                            </div>
                            <hr style="border-width:3px; color:blue">
                            <strong>Movie Rating:</strong>
                            <div class="mb-3">
                                <input type="text" name="rating" class="form-control" v-model.lazy="rating" placeholder="PG13" required>
                            </div>
                            <strong>Movie Duration:</strong>
                            <div class="mb-3">
                                <input type="number" name="duration" class="form-control" v-model.lazy="duration" required>
                            </div>
                        </div>
                        <div v-if="eventType == 'sportsEvent'">
                            <strong>Participants: <button class="ms-3 btn btn-warning btn-sm" @click="addParticipant">Add participant</button></strong>
                            <div v-for="(p,index) in participants" :key="p.participantName">
                                <div class="mt-3">Participant {{index+1}} Name:</div>
                                <input type="text" :name="'participant'+ (index+1) + 'Name'" v-model.lazy="p.participantName" placeholder="Liverpool" class="form-control">
                                <hr v-if="index + 1 !== participants.length" class="mt-4 mb-4" style="border-width:1px">
                            </div>
                            <strong>Sport:</strong>
                            <div class="mb-3">
                                <input type="text" name="sport" class="form-control" v-model.lazy="sport" placeholder="Football" required>
                            </div>
                        </div>
                        <div class="mt-3">
                            <button type="submit" @click="addEvent" class="btn btn-primary">Add Event</button>
                        </div>
                    </form>
                </div>
                </div>
                <div class="modal fade" data-bs-backdrop="static" id="addEventSuccessModal" tabindex="-1" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5">Adding/editing event result</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body" id="addEventSuccessModalBody">
                                The event was successfully added/edited.
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal fade" data-bs-backdrop="static" id="addEventFailureModal" tabindex="-1" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5">Adding/editing event result</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body" id="addEventFailureModalBody">
                            <div>
                                The event was not added/edited.
                            </div>
                            <div v-for="(error,index) in addEventErrors" :key="error">
                                Error {{index+1}} :  {{ error }}
                            </div>
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
            <div id="collapseTwo" class="accordion-collapse collapse" data-bs-parent="#accordionEvents">
            <div class="accordion-body">
                <div class="d-flex flex-wrap justify-content-evenly align-items-stretch">
                    <div v-for="event in events" :key="event.eventID">
                        <div class="card mt-3 pb-5 ps-1" style="width: 18rem; height:90%">
                            <div class="card-body" style="height:100%">
                                <h6 class="card-title text-decoration-underline">
                                    Event ID {{event.eventID}} - {{ event.eventName }}
                                </h6>
                                <p class="card-text">
                                    <strong>What:</strong> <span v-if="event.eventType == 'SPORTS_EVENT'">Sports Event</span>
                                    <span v-else>{{ event.eventType.slice(0,1) + event.eventType.slice(1,).toLowerCase() }}</span><br>
                                    <strong>When:</strong> {{ event.eventDate.slice(0,2)}} {{ months[event.eventDate.slice(2,4)] }} {{ event.eventDate.slice(4,8)}}, {{ event.eventTime }}H <br>
                                    <strong>Where:</strong> {{ event.eventVenue }} <br>
                                    <span v-if="event.cancellationFee > 0"> <strong>Cancellation Fee:</strong> {{ event.cancellationFee }} <br></span>
                                    <strong>Ticketing Options:</strong>
                                    <ul class="mb-0">
                                        <li v-for="to in event.ticketingOptions" :key="to.tierName"> {{ to.tierName }} - ${{ to.tierPrice }} - {{ to.tierQuantity }} tickets</li>
                                    </ul>
                                    <span v-if="event.eventType == 'CONCERT'"> <strong>Performers:</strong> 
                                        <ul class="mb-0">
                                            <li v-for="performer in event.performers" :key="performer"> {{ performer }} </li>
                                        </ul><br>
                                    </span> 
                                    <span v-if="event.eventType == 'MOVIE'"> <strong>Main Cast:</strong> 
                                        <ul class="mb-0">
                                            <li v-for="mainCast in event.mainCast" :key="mainCast"> {{ mainCast }} </li>
                                        </ul>
                                        <strong>Rating:</strong> {{ event.rating }} <br>
                                        <strong>Duration:</strong> {{ event.duration }} minutes
                                    </span>
                                    <span v-if="event.eventType == 'SPORTS_EVENT'"> <strong>Participants:</strong> 
                                        <ul class="mb-0">
                                            <li v-for="participants in event.participants" :key="participants"> {{ participants }} </li>
                                        </ul>
                                        <strong>Sport:</strong> {{ event.sport }} <br>
                                    </span>
                                </p>
                                <div><button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#cancelEventModal" @click="setEventToCancel(event)">
                                    Cancel Event
                                </button><button class="btn btn-link float-end" @click="selectEvent(event)">Edit</button></div>
                                
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </div>
            <div class="modal modal-lg fade" data-bs-backdrop="static" id="editEventModal" tabindex="-1" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5">Edit event</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body" id="editEventModalBody" v-if="Object.keys(selectedEvent).length > 0">
                                <div>
                                    <strong>Event ID:</strong> {{ selectedEvent.eventID }}
                                </div>
                                <div class="mt-2">
                                    <strong>Event Name:</strong> <span v-if="selectedEvent.eventName == ''" class="text-danger">Missing event name</span>
                                    <input type="text" name="newEventName" class="form-control w-75" v-model.lazy="selectedEvent.eventName"> 
                                </div>
                                <div class="mt-2">
                                    <strong>Event Venue:</strong>  <span v-if="selectedEvent.eventVenue == ''" class="text-danger">Missing event venue</span>
                                    <input type="text" name="newEventVenue" class="form-control w-75" v-model.lazy="selectedEvent.eventVenue">
                                </div>
                                <div class="mt-2"> 
                                    <strong>Event Date (DDMMYYYY): </strong>  <span v-if="!validateEventDate()" class="text-danger">Missing/invalid event date</span>
                                    <input type="text" name="newEventDate" class="form-control w-75" v-model.lazy="selectedEvent.eventDate">
                                </div>
                                <div class="mt-2">
                                    <strong>Event Time (24H): </strong>  <span v-if="!validateEventTime()" class="text-danger">Missing/invalid event time</span>
                                    <input type="text" name="newEventTime" class="form-control w-75" v-model.lazy="selectedEvent.eventTime">
                                </div>
                                <div class="mt-2">
                                    <strong>Cancellation Fee ($): </strong> <input type="number" name="newCancellationFee" class="form-control w-75" v-model.lazy="selectedEvent.cancellationFee">
                                </div>
                                <hr style="border-width:3px; color:blue">
                                <div class="mt-2">
                                    <strong>Ticketing Options: </strong><button class="btn btn-dark" @click="selectedEvent.ticketingOptions.push({'tierName' : '', 'tierPrice' : 0, 'tierQuantity' : 0})"
                                    style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;"> Add</button>  
                                    <div class="mt-2" v-for="(to,index) in selectedEvent.ticketingOptions" :key="to.tierName"> 
                                        <span class="d-block">Tier {{index+1}} Name: <span v-if="to.tierName == ''" class="text-danger">Missing tier name</span> </span>
                                        <input type="text" :name="'newTier'+ (index+1) + 'Name'" v-model.lazy="to.tierName"  class="form-control w-50 me-2 d-inline-block">
                                        <a @click="selectedEvent.ticketingOptions.splice(index,1);untouchedTicketingOptions--">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="red" class="bi bi-x-square-fill d-inline-block" viewBox="0 0 16 16">
                                            <path d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2zm3.354 4.646L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 1 1 .708-.708"/>
                                            </svg>
                                        </a>
                                        <span class="d-block">Tier {{index+1}} Price:</span> <input type="number" :name="'newTier'+ (index+1) + 'Price'" v-model.lazy="to.tierPrice" class="form-control w-50">
                                        Tier {{index+1}} Quantity: <input type="number" :name="'newTier'+ (index+1) + 'Quantity'" v-model.lazy="to.tierQuantity" class="form-control w-50">
                                    </div>
                                    
                                    <hr style="border-width:3px; color:blue">
                                </div>
                                <div v-if="selectedEvent.eventType == 'CONCERT'">
                                    <strong>Performers: </strong> <button class="btn btn-dark" @click="selectedEvent.performers.push({'performerName' : ''})" style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;"> Add </button>
                                    <div class="mt-2" v-for="(performer,index) in selectedEvent.performers" :key="performer.performerName">
                                        Performer {{ index+1 }}: <span v-if="performer.performerName == ''" class="text-danger">Missing performer name</span>
                                        <input type="text" :name="'newPerformer'+ (index+1) + 'Name'" v-model.lazy="performer.performerName" class="form-control w-50 d-inline-block me-2"> 
                                        <a @click="selectedEvent.performers.splice(index,1)">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="red" class="bi bi-x-square-fill d-inline-block" viewBox="0 0 16 16">
                                            <path d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2zm3.354 4.646L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 1 1 .708-.708"/>
                                            </svg>
                                        </a>
                                    </div>
                                    <hr style="border-width:3px; color:blue">
                                </div>
                                <div v-if="selectedEvent.eventType == 'MOVIE'">
                                    <strong>Main Cast: </strong> <button class="btn btn-dark" @click="selectedEvent.mainCast.push({'castMemberName' : ''})" style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;"> Add </button>
                                    <div class="mt-2" v-for="(mainCast,index) in selectedEvent.mainCast" :key="mainCast.castMemberName">
                                        <div class="d-block">Cast Member {{ index+1 }}: <span v-if="mainCast.castMemberName == ''" class="text-danger">Missing cast member name</span></div>
                                        <input type="text" :name="'newCast'+ (index+1) + 'Name'" v-model.lazy="mainCast.castMemberName" class="form-control w-50 d-inline-block me-2">
                                        <a @click="selectedEvent.mainCast.splice(index,1)">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="red" class="bi bi-x-square-fill d-inline-block" viewBox="0 0 16 16">
                                            <path d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2zm3.354 4.646L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 1 1 .708-.708"/>
                                            </svg>
                                    </a> 
                                    </div>
                                    <hr style="border-width:3px; color:blue">
                                    <div class="mt-2">
                                        <strong>Rating:</strong> <span v-if="selectedEvent.rating == ''" class="text-danger">Missing rating</span>
                                        <input type="text" name="newRating" class="form-control w-75" v-model.lazy="selectedEvent.rating">
                                    </div>
                                    <div class="mt-2">
                                        <strong>Duration:</strong> <input type="number" name="newDuration" class="form-control w-75" v-model.lazy="selectedEvent.duration">
                                    </div>     
                                </div>
                                <div v-if="selectedEvent.eventType == 'SPORTS_EVENT'">
                                    <strong>Participants: </strong> <button class="btn btn-dark" @click="selectedEvent.participants.push({'participantName' : ''})" style="--bs-btn-padding-y: .25rem; --bs-btn-padding-x: .5rem; --bs-btn-font-size: .75rem;"> Add </button>
                                    <div v-for="(participant, index) in selectedEvent.participants" :key="participant.participantName">
                                        <div class="d-block">Participant {{ index+1 }}:  <span v-if="participant.participantName == ''" class="text-danger">Missing participant name</span></div>
                                        <input type="text" :name="'newParticipant'+ (index+1) + 'Name'" v-model.lazy="participant.participantName" class="form-control w-50 d-inline-block me-2">
                                        <a @click="selectedEvent.participants.splice(index,1)">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="red" class="bi bi-x-square-fill d-inline-block" viewBox="0 0 16 16">
                                            <path d="M2 0a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2zm3.354 4.646L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 1 1 .708-.708"/>
                                            </svg>
                                    </a>
                                    </div>
                                    
                                    <hr style="border-width:3px; color:blue">
                                    <div class="mt-2">
                                        <strong>Sport:</strong> <span v-if="selectedEvent.sport == ''" class="text-danger">Missing sport</span>
                                         <input type="text" name="newSport" class="form-control w-75" v-model.lazy="selectedEvent.sport">
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer" v-if="Object.keys(selectedEvent).length > 0">
                                <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancel</button>
                                <button type="button" class="btn btn-success" :disabled="!validateEventChanges()" @click="makeChanges()" data-bs-dismiss="modal">Save changes</button>
                            </div>
                        </div>
                    </div>
                </div>
        </div>
        <div class="modal fade" id="cancelEventModal" tabindex="-1" aria-labelledby="cancelEventModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title text-black" id="cancelEventModalLabel">Cancel Event</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body text-black">
                        Are you sure you want to cancel this event? This action cannot be reversed.
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-danger" @click="cancelEvent">Confirm Cancel</button>
                    </div>
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
            performers: [{"performerName" : ""}],
            mainCast: [{"castMemberName" : ""}],
            rating: "",
            duration: 0,
            participants: [{"participantName" : ""}],
            sport: "",
            addEventErrors: [],
            events: [],
            months: {
                "01": "January",
                "02": "February",
                "03": "March",
                "04": "April",
                "05": "May",
                "06": "June",
                "07": "July",
                "08": "August",
                "09": "September",
                "10": "October",
                "11": "November",
                "12": "December"
            },
            selectedEvent: {},
            originalEvent: {},
            eventToCancel: null,
            untouchedTicketingOptions: 0,
        }
    },
    mounted(){
        this.getEvents();
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
            this.performers.push({"performerName" : ""})
        },
        addMainCast(){
            this.mainCast.push({"castMemberName" : ""})
        },
        addParticipant(){
            this.participants.push({"participantName" : ""})
        },
        addEvent(){
            this.handleDateTime();
            if (!bootstrap.Modal.getInstance('#addEventSuccessModal')){
                var modal1 = new bootstrap.Modal('#addEventSuccessModal');    
            }else{
                modal1 = bootstrap.Modal.getInstance('#addEventSuccessModal')
            }
            if (!bootstrap.Modal.getInstance('#addEventFailureModal')){
                var modal2 = new bootstrap.Modal('#addEventFailureModal');    
            }else{
                modal2 = bootstrap.Modal.getInstance('#addEventFailureModal')
            }
            this.addEventErrors = []
            this.validateAddEvent();
            if (this.addEventErrors.length > 0){
                modal2.show()
            }else{
                let url = ""
                let json = {}
                if (this.eventType == 'concert'){
                    url = 'http://localhost:8080/api/concert'
                    let performers = []
                    for (let p of this.performers){
                        performers.push(p.performerName)
                    }
                    json = {
                        eventName: this.eventName,
                        eventVenue: this.eventVenue,
                        eventDate: this.eventDate,
                        eventTime: this.eventTime,
                        cancellationFee: this.cancellationFee,
                        ticketingOptions: this.ticketingOptions,
                        performers : performers
                    }
                }else if (this.eventType == 'movie'){
                    url = 'http://localhost:8080/api/movie'
                    let mainCast = []
                    for (let p of this.mainCast){
                        mainCast.push(p.castMemberName)
                    }
                    json = {
                        eventName: this.eventName,
                        eventVenue: this.eventVenue,
                        eventDate: this.eventDate,
                        eventTime: this.eventTime,
                        cancellationFee: this.cancellationFee,
                        ticketingOptions: this.ticketingOptions,
                        mainCast : mainCast,
                        rating: this.rating,
                        duration: this.duration
                    }
                }else{
                    url = 'http://localhost:8080/api/SportsEvent'
                    let participants = []
                    for (let p of this.participants){
                        participants.push(p.participantName)
                    }
                    json = {
                        eventName: this.eventName,
                        eventVenue: this.eventVenue,
                        eventDate: this.eventDate,
                        eventTime: this.eventTime,
                        cancellationFee: this.cancellationFee,
                        ticketingOptions: this.ticketingOptions,
                        participants : participants,
                        sport: this.sport
                    }
                }
                this.addEventErrors = []
                let ref = this
                axios.post(url, json)
                .then(function(){
                    modal1.show()
                    ref.eventType = "concert"
                    ref.eventName = ""
                    ref.eventVenue = ""
                    ref.eventDateAndTime = ""
                    ref.eventDate = ""
                    ref.eventTime = ""
                    ref.cancellationFee = 0.0
                    ref.ticketingOptions = [{"tierName": "", "tierPrice": 0, "tierQuantity": 0}]
                    ref.performers = [{"performerName" : ""}]
                    ref.mainCast = [{"castMemberName" : ""}]
                    ref.rating = ""
                    ref.duration = 0
                    ref.participants = [{"participantName" : ""}]
                    ref.sport = ""
                })
                .catch(function (error) {
                    console.log(error)
                    modal2.show()
                    let body = document.getElementById('addEventFailureModalBody')
                    body.innerText = "The adding of event has failed unexpectedly: " + error.response.data.message
                });
            }
        },
        handleDateTime(){
            let date = this.eventDateAndTime.toString().split('T')[0]
            let time = this.eventDateAndTime.toString().split('T')[1]
            let dateSplit = date.split('-')
            let timeSplit = time.split(":")
            this.eventDate = dateSplit[2] + dateSplit[1] + dateSplit[0]
            this.eventTime = timeSplit[0] + timeSplit[1];
        },
        validateAddEvent(){
            if (this.eventName == ""){
                this.addEventErrors.push("Event name missing")
            }
            if (this.eventVenue == ""){
                this.addEventErrors.push("Event venue missing")
            }
            if (this.eventDateAndTime == ""){
                this.addEventErrors.push("Event date and time missing")
            }
            for (let i=0; i<this.ticketingOptions.length;i++){
                if (this.ticketingOptions[i].tierName == ""){
                    this.addEventErrors.push("Tier name for tier " + parseFloat(i+1).toString() + " missing")
                }
                if (this.ticketingOptions[i].tierPrice == ""){
                    this.addEventErrors.push("Tier price for tier " + parseFloat(i+1).toString() + " missing")
                }
                if (this.ticketingOptions[i].tierQuantity == ""){
                    this.addEventErrors.push("Tier quantity for tier " + parseFloat(i+1).toString() + " missing")
                }
            }
            if (this.eventType == 'concert'){
                for (let i=0; i<this.performers.length;i++){
                    if (this.performers[i].performerName == ""){
                        this.addEventErrors.push("Name for performer " + parseFloat(i+1).toString() + " missing")
                    }
                }
            }
            else if (this.eventType =='movie'){
                for (let i=0; i<this.mainCast.length;i++){
                    if (this.mainCast[i].castMemberName == ""){
                        this.addEventErrors.push("Name for cast member " + parseFloat(i+1).toString() + " missing")
                    }
                }
                if (this.rating == ""){
                    this.addEventErrors.push("Movie rating missing")
                }
                if (this.duration == ""){
                    this.addEventErrors.push("Movie duration missing")
                }
            }
            else if (this.eventType =='sportsEvent'){
                for (let i=0; i<this.participants.length;i++){
                    if (this.participants[i].participantName == ""){
                        this.addEventErrors.push("Name for participant " + parseFloat(i+1).toString() + " missing")
                    }
                }
                if (this.sport == ""){
                    this.addEventErrors.push("Sport missing")
                }
            }
        },
        getEvents(){
            let ref = this
            axios.get('http://localhost:8080/api/event')
            .then(function(response){
                ref.events = response.data
                console.log(ref.events)
            })
            .catch(function (error) {
                console.log(error)
            });
        },
        selectEvent(event){
            this.originalEvent = event;
            this.selectedEvent = JSON.parse(JSON.stringify(event))
            this.untouchedTicketingOptions = this.selectedEvent.ticketingOptions.length;
            if (event.eventType == 'CONCERT'){
                let performers = []
                for (let p of event.performers){
                    performers.push({"performerName" : p})
                }
                this.selectedEvent.performers = performers
            }else if (event.eventType == 'MOVIE'){
                let mainCast = []
                for (let p of event.mainCast){
                    mainCast.push({"castMemberName" : p})
                }
                this.selectedEvent.mainCast = mainCast
            } else{
                let participants = []
                for (let p of event.participants){
                    participants.push({"participantName" : p})
                }
                this.selectedEvent.participants = participants
            }
            if (!bootstrap.Modal.getInstance('#editEventModal')){
                var modal = new bootstrap.Modal('#editEventModal');
            }else{
                var modal = bootstrap.Modal.getInstance('#editEventModal')
            }
            modal.show()
        },
        validateEventDate(){
            let eventDate = this.selectedEvent.eventDate
            if (eventDate.length == 8){
                let day = eventDate.slice(0,2)
                let month = eventDate.slice(2,4)
                let year = eventDate.slice(4,8)
                let todayDay = new Date().getDate()
                let todayMonth = new Date().getMonth()
                let todayYear = new Date().getFullYear()
                if (parseInt(day) > 0 && parseInt(day) < 32 && parseInt(month) > 0 && parseInt(month) < 13 && parseInt(year) > 2023){
                    if (parseInt(year) >= todayYear && parseInt(month) >= todayMonth && parseInt(day) >= todayDay){
                        return true
                    }
                }
            }
            return false
        },
        validateEventTime(){
            let eventTime = this.selectedEvent.eventTime
            if (eventTime.length == 4){
                let hour = eventTime.slice(0,2)
                let minute = eventTime.slice(2,4)
                if (parseInt(hour) >= 0 && parseInt(hour) < 24 && parseInt(minute) >= 0 && parseInt(minute) < 60){
                    return true
                }
            }
            return false
        },
        validateEventChanges(){
            if (this.selectedEvent.eventName == ""){
                return false
            }
            if (this.selectedEvent.eventVenue == ""){
                return false
            }
            if (!this.validateEventDate()){
                return false
            }
            if (!this.validateEventTime()){
                return false
            }
            for (let i=0; i<this.selectedEvent.ticketingOptions.length;i++){
                if (this.selectedEvent.ticketingOptions[i].tierName == ""){
                    return false
                }
            }
            if (this.selectedEvent.eventType == 'CONCERT'){
                for (let i=0; i<this.selectedEvent.performers.length;i++){
                    if (this.selectedEvent.performers[i].performerName == ""){
                        return false
                    }
                }
            }
            else if (this.selectedEvent.eventType =='MOVIE'){
                for (let i=0; i<this.selectedEvent.mainCast.length;i++){
                    if (this.selectedEvent.mainCast[i].castMemberName == ""){
                        return false
                    }
                }
                if (this.selectedEvent.rating == ""){
                    return false
                }
                if (this.selectedEvent.duration == ""){
                    return false
                }
            }
            else if (this.selectedEvent.eventType =='SPORTS_EVENT'){
                for (let i=0; i<this.selectedEvent.participants.length;i++){
                    if (this.selectedEvent.participants[i].participantName == ""){
                        return false
                    }
                }
                if (this.selectedEvent.sport == ""){
                    return false
                }
            }
            return true
        },
        makeChanges(){
            if (!bootstrap.Modal.getInstance('#addEventSuccessModal')){
                var modal1 = new bootstrap.Modal('#addEventSuccessModal');    
            }else{
                modal1 = bootstrap.Modal.getInstance('#addEventSuccessModal')
            }
            if (!bootstrap.Modal.getInstance('#addEventFailureModal')){
                var modal2 = new bootstrap.Modal('#addEventFailureModal');    
            }else{
                modal2 = bootstrap.Modal.getInstance('#addEventFailureModal')
            }
            let url = ""
            let json = {}
            let eventID = this.selectedEvent.eventID;
            if (this.selectedEvent.eventType == 'CONCERT'){
                url = 'http://localhost:8080/api/concert/' + eventID
                let performers = []
                for (let p of this.selectedEvent.performers){
                    performers.push(p.performerName)
                }
                json = {
                    eventName: this.selectedEvent.eventName,
                    eventVenue: this.selectedEvent.eventVenue,
                    eventDate: this.selectedEvent.eventDate,
                    eventTime: this.selectedEvent.eventTime,
                    cancellationFee: this.selectedEvent.cancellationFee,
                    performers : performers
                }
            }else if (this.selectedEvent.eventType == 'MOVIE'){
                url = 'http://localhost:8080/api/movie/' + eventID
                let mainCast = []
                for (let p of this.selectedEvent.mainCast){
                    mainCast.push(p.castMemberName)
                }
                json = {
                    eventName: this.selectedEvent.eventName,
                    eventVenue: this.selectedEvent.eventVenue,
                    eventDate: this.selectedEvent.eventDate,
                    eventTime: this.selectedEvent.eventTime,
                    cancellationFee: this.selectedEvent.cancellationFee,
                    mainCast : mainCast,
                    rating: this.selectedEvent.rating,
                    duration: this.selectedEvent.duration
                }
            }else{
                url = 'http://localhost:8080/api/SportsEvent/' + eventID
                let participants = []
                for (let p of this.selectedEvent.participants){
                    participants.push(p.participantName)
                }
                json = {
                    eventName: this.selectedEvent.eventName,
                    eventVenue: this.selectedEvent.eventVenue,
                    eventDate: this.selectedEvent.eventDate,
                    eventTime: this.selectedEvent.eventTime,
                    cancellationFee: this.selectedEvent.cancellationFee,
                    participants : participants,
                    sport: this.selectedEvent.sport
                }
            }
            this.addEventErrors = []
            let oldTicketingOptions = this.originalEvent.ticketingOptions
            let newTicketingOptions = this.selectedEvent.ticketingOptions
            for (let i=0;i<this.untouchedTicketingOptions;i++){
                if (oldTicketingOptions[i].tierName != newTicketingOptions[i].tierName || oldTicketingOptions[i].tierPrice != newTicketingOptions[i].tierPrice || oldTicketingOptions[i].tierQuantity != newTicketingOptions[i].tierQuantity){
                    axios.put('http://localhost:8080/api/ticketingOption/' + oldTicketingOptions[i].ticketingOptionID, newTicketingOptions[i])
                    .then( (response) =>{
                        console.log("Ticketing option" + parseFloat(i+1) + "updated")
                    })
                    .catch( (error) => {
                        console.log("Ticketing option" + parseFloat(i+1) + "update failed")
                    })
                }
            }
            for (let i=this.untouchedTicketingOptions;i<oldTicketingOptions.length;i++){
                console.log(oldTicketingOptions[i].ticketingOptionID)
                axios.delete('http://localhost:8080/api/ticketingOption/' + oldTicketingOptions[i].ticketingOptionID)
                .then( (response) =>{
                    console.log("Ticketing option" + parseFloat(i+1) + "deleted")
                })
                .catch( (error) => {
                    console.log(error)
                    console.log("Ticketing option" + parseFloat(i+1) + "deletion failed")
                })
            }
            for (let i=this.untouchedTicketingOptions;i<newTicketingOptions.length;i++){
                axios.post('http://localhost:8080/api/ticketingOption/' + this.selectedEvent.eventID, newTicketingOptions[i])
                .then( (response) =>{
                    console.log("Ticketing option" + parseFloat(i+1) + "added")
                })
                .catch( (error) => {
                    console.log("Ticketing option" + parseFloat(i+1) + "addition failed")
                })
            }
            let ref = this
            axios.put(url, json)
            .then(function(){
                modal1.show()
                ref.selectedEvent = {}
            })
            .catch(function (){
                this.addEventErrors = []
                let body = document.getElementById('addEventFailureModalBody')
                body.innerText = "The editing of event has failed unexpectedly:" + error.response.data.message
                modal2.show()
            });
        },
        async cancelEvent() {
            // Call the first API endpoint
            axios.delete(`http://localhost:8080/ticket/CancellationByEvent/${this.eventToCancel.eventID}`)
            .then((response) =>{
                    console.log(response.data)
                })

            // Call the second API endpoint
            axios.delete(`http://localhost:8080/api/event/${this.eventToCancel.eventID}`)
            .then((response) =>{
                    console.log(response.data)
                })

            // Close the modal
            var myModal = new bootstrap.Modal(document.getElementById('cancelEventModal'));
            myModal.hide();
            alert('Event has been cancelled!');
        },
        setEventToCancel(event) {  // renamed from setCancelEvent
            console.log(event);
            this.eventToCancel = event;
        },
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