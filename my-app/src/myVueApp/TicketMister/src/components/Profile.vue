<template>
    <div class="Profile">
        <form @submit.prevent="submitForm">
            <div class="account-section">
                <h2>My Account</h2>
                <div class="form-group">
                    <label>Email</label>
                    <input type="email" v-model="profile.emailAddress"  />
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input type="password" v-model="profile.password" disabled />
                    <button type="button">Reset Password</button>
                </div>
                <div class="form-group">
                    <label>Mobile No.</label>
                    <input type="tel" v-model="profile.mobileNumber" />
                    <button type="button">Change My Mobile No.</button>
                </div>
            </div>

        
            <div class="marketing-preferences-section">
                <h2>Marketing Preferences</h2>
                <div class="form-group">
                    <input type="checkbox" id="keep-in-touch" v-model="profile.marketingPreferences" />
                    <label for="keep-in-touch">Let us keep you in the know about what's coming up - including exclusive
                        presales and offers - by electronic means (e.g., email, SMS etc.) You can always change how we
                        contact you via your Ticketmaster account.</label>
                </div>
                <div class="form-group">
                    <input type="checkbox" id="terms-and-conditions" v-model="profile.agreedToTerms" />
                    <label for="terms-and-conditions">By checking this box, you agree to our Terms of Use and understand
                        your information will be used as described in our Privacy Policy.</label>
                </div>
                <button type="submit" class="confirm-button">Confirm</button>
            </div>
        </form>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    name: 'ProfileDetails',
    data() {
        return {
            profile: {
                email: '',
                password: '',
                mobileNumber: '',
                personalDetails: {
                    // Include all the fields from the personal details section
                },
                deliveryAddress: {
                    // Include all the fields from the delivery address section
                },
                marketingPreferences: false,
                agreedToTerms: false
            }
        };
    },
    created() {
        this.fetchProfileData();
    },
    methods: {
        async fetchProfileData() {
            try {
                // Make sure to adjust the URL to your API endpoint
                const username = sessionStorage.getItem('username');
                const response = await axios.get(`http://localhost:8080/api/user/${username}`);
                
                this.profile = response.data;
                console.log(this.profile)
                // After the profile data is fetched, update isLoading to false
                this.isLoading = false;
            } catch (error) {
                console.error('Failed to fetch profile data', error);
                // Handle error appropriately
                // Consider setting isLoading to false here as well to show an error message or a retry button
            }
        },
        submitForm() {
            // Handle form submission
            console.log(this.profile);
            // Make an API call or perform other actions
        }
    }
};
</script>

<style scoped>
.Profile {
    max-width: 600px;
    margin: auto;
}

.form-group {
    margin-bottom: 1em;
}

.confirm-button {
    /* Style your button as needed */
}
</style>