<template>
    <div class="eventsPage" style="margin-top:10vw; margin-bottom:10vw">
        <h1>Events Page</h1>
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
                                <h1 class="modal-title fs-5">Adding event result</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body" id="addEventSuccessModalBody">
                                The event was successfully added.
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal fade" data-bs-backdrop="static" id="addEventFailureModal" tabindex="-1" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5">Adding event result</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body" id="addEventFailureModalBody">
                            <div>
                                The event was not added.
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
            performers: [{"performerName" : ""}],
            mainCast: [{"castMemberName" : ""}],
            rating: "",
            duration: 0,
            participants: [{"participantName" : ""}],
            sport: "",
            addEventErrors: []
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
                    console.log(json)
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