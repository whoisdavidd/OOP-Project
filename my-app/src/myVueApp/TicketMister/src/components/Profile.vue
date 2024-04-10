<template>
  <div class="Profile" style="margin-top: 56px; margin-bottom:56px">
    <form @submit.prevent="submitForm">
      <div class="account-section">
        <h2 class="text-center p-3" style="color: palegoldenrod">My Account</h2>
        <div class="form-group">
          <label class="p-1">Balance</label>
          <div class="input-group">
            <input type="text" v-model="profile.accountBalance" disabled class="form-control" />
            <button type="button" class="btn btn-light btn-outline-dark p-1">
              Top Up
            </button>
          </div>
        </div>
        <div class="form-group">
          <label class="p-1">Email</label>
          <input type="email" v-model="profile.emailAddress" class="form-control" />
        </div>
        <div class="form-group">
          <label class="p-1">Password</label>
          <div class="input-group">
            <input type="password" v-model="profile.password" disabled class="form-control" />
            <button type="button" class="btn btn-light btn-outline-dark p-1">
              Reset Password
            </button>
          </div>
        </div>
        <div class="form-group">
          <label class="p-1">Mobile No.</label>
          <div class="input-group">
            <input type="tel" v-model="profile.mobileNumber" class="form-control" />
            <button type="button" class="btn btn-outline-dark btn-light">
              Change My Mobile No.
            </button>
          </div>
        </div>
      </div>

      <div class="marketing-preferences-section py-4 mt-5 px-3 rounded-4"
        style="background-color: rgba(128, 128, 128, 0.5);">
        <h4 class="text-center">Marketing Preferences</h4>
        <div class="form-group align-items-center d-flex">
          <input type="checkbox" id="keep-in-touch" v-model="profile.marketingPreferences"
            class="me-3 form-check-input" />
          <label for="keep-in-touch">Let us keep you in the know about what's coming up - including
            exclusive presales and offers - by electronic means (e.g., email,
            SMS etc.) You can always change how we contact you via your
            Ticketmaster account.</label>
        </div>
        <div class="form-group d-flex align-items-center">
          <input type="checkbox" id="terms-and-conditions" v-model="profile.agreedToTerms"
            class="me-3 form-check-input" />
          <label for="terms-and-conditions">By checking this box, you agree to our Terms of Use and understand
            your information will be used as described in our Privacy
            Policy.</label>
        </div>
        <div class="d-flex justify-content-end">
          <button type="submit" class="confirm-button btn btn-outline-dark rounded-pill btn-light">Confirm</button>
        </div>

      </div>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "ProfileDetails",
  data() {
    return {
      profile: {
        email: "",
        password: "",
        mobileNumber: "",
        personalDetails: {
          // Include all the fields from the personal details section
        },
        deliveryAddress: {
          // Include all the fields from the delivery address section
        },
        marketingPreferences: false,
        agreedToTerms: false,
      },
    };
  },
  created() {
    this.fetchProfileData();
  },
  methods: {
    async fetchProfileData() {
      try {
        // Make sure to adjust the URL to your API endpoint
        const username = sessionStorage.getItem("username");
        const response = await axios.get(
          `http://localhost:8080/api/user/${username}`
        );

        this.profile = response.data;
        console.log(this.profile);
        // After the profile data is fetched, update isLoading to false
        this.isLoading = false;
      } catch (error) {
        console.error("Failed to fetch profile data", error);
        // Handle error appropriately
        // Consider setting isLoading to false here as well to show an error message or a retry button
      }
    },
    submitForm() {
      // Handle form submission
      console.log(this.profile);
      // Make an API call or perform other actions
    },
  },
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
