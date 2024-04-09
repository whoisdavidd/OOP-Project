<template>
    <div class="registration rounded-4" style="background-color: rgba(128, 128, 128, 0.7); margin-top:4vw; margin-bottom:4vw">
        <h1 class="text-center" style="color:palegoldenrod;">Create a New Account</h1>
        <form @submit.prevent="register">
            <div class="d-inline-block mb-3">
                User type:
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="userType" id="userType1" v-model="userType" value="customer" checked>
                    <label class="form-check-label" for="userType1">
                        Customer
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="userType" id="userType2" v-model="userType" value="eventManager">
                    <label class="form-check-label" for="userType2">
                        Event Manager
                    </label>
                </div>
            </div>
            <div v-if="userType!='eventManager'">
                <input type="email" name="emailAddress" class="form-control" v-model="emailAddress" placeholder="Email" required>
            </div>
            <div>
                <input type="text" name="username" class="form-control" v-model="username" placeholder="Username" required>
            </div>
            <div>
                <input type="password" name="password" class="form-control" v-model="password" placeholder="Password" required>
            </div>
            <div class="mt-3 d-flex justify-content-end">
                <button type="submit" @click="registerUser" class="btn btn-light rounded-pill btn-outline-dark">Register</button>
            </div>
        </form>
        <div class="modal fade" data-bs-backdrop="static" id="eventManagerModal" tabindex="-1" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5">Event Manager verification</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    You've requested to register as an event manager. As such, please provide the necessary admin password to authorise this action.
                    <input type="password" v-model="adminPassword" placeholder="Password" class="mt-3 form-control"> <button class="mt-1 btn btn-primary" @click="verifyEventManager">Verify</button>
                </div>
                </div>
            </div>
        </div>
        <div class="modal fade text-black" data-bs-backdrop="static" id="registerModal" tabindex="-1" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5">Registration result</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body" id="registerModalBody">

                </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import * as bootstrap from 'bootstrap';
import axios from 'axios';
export default {
    name: 'RegistrationPage',
    data() {
        return {
            userType: "",
            username: "",
            password: "",
            emailAddress: "",
            adminPassword: "",

        }
    },
    methods: {
        registerUser(){
            if (!bootstrap.Modal.getInstance('#registerModal')){
                var modal1 = new bootstrap.Modal('#registerModal');    
            }else{
                modal1 = bootstrap.Modal.getInstance('#registerModal')
            }
            if (!bootstrap.Modal.getInstance('#eventManagerModal')){
                var modal2 = new bootstrap.Modal('#eventManagerModal');
            }else{
                modal2 = bootstrap.Modal.getInstance('#eventManagerModal')
            }
            if (this.userType == "eventManager"){
                modal2.show()
            }else{
                axios.post('http://localhost:8080/api/customer', {
                    username: this.username,
                    password: this.password,
                    emailAddress: this.emailAddress
                })
                .then(function () {
                    modal1.show()
                    let body = document.getElementById('registerModalBody')
                    body.innerText = "The registration was successful"
                })
                .catch(function (error) {
                    modal1.show()
                    let body = document.getElementById('registerModalBody')
                    body.innerText = "The registration has failed: " + error.response.data
                });
            }
        },
        verifyEventManager(){
            if (!bootstrap.Modal.getInstance('#registerModal')){
                var modal1 = new bootstrap.Modal('#registerModal');    
            }else{
                modal1 = bootstrap.Modal.getInstance('#registerModal');
            }
            if (!bootstrap.Modal.getInstance('#eventManagerModal')){
                var modal2 = new bootstrap.Modal('#eventManagerModal');
            }else{
                modal2 = bootstrap.Modal.getInstance('#eventManagerModal')
            }
            if (this.adminPassword == "admin123"){
                axios.post('http://localhost:8080/api/eventManager', {
                    username: this.username,
                    password: this.password
                })
                .then(function () {
                    modal2.hide()
                    modal1.show()
                    let body = document.getElementById('registerModalBody')
                    body.innerText = "The registration was successful"
                })
                .catch(function (error) {
                    modal2.hide()
                    modal1.show()
                    let body = document.getElementById('registerModalBody')
                    body.innerText = "The registration has failed: " + error.response.data
                });
            }
        }
    }
}
</script>

<style scoped>
.registration {
    max-width: 600px;
    margin: auto;
    padding: 20px;
}

input{
    margin-bottom: 10px
}
</style>