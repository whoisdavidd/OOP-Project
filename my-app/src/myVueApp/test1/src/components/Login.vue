<template>
  <div class="UserLogin">
    <div class="container-fluid px-5" style="margin-top: 30px">
      <div class="row">
        <div class="col-3"></div>
        <div class="col-6">
          <h2 class="text-center" style="color: palegoldenrod">Login</h2>
        </div>
        <div class="col-3"></div>
      </div>
      <div class="row">
        <div class="col-3"></div>
        <div class="col-6">
          <form @submit.prevent="loginUser">
            <div class="form-group">
              <label for="email" class="p-1">Username</label>
              <input type="username" class="form-control" id="email" name="email" v-model="loginForm.username"
                placeholder="Enter username" required="true" />
            </div>
            <div class="form-group">
              <label for="pwd" class="p-1">Password</label>
              <input
                type="password"
                class="form-control"
                id="pwd"
                v-model="loginForm.password"
                name="password"
                placeholder="Enter password"
                required="true"
              />
            </div>

            <div class="text-left mt-3 d-flex justify-content-end">
              <button
                type="submit"
                class="btn btn-light rounded-pill btn-outline-dark"
              >
                Login
              </button>
            </div>
          </form>
          <div class="mt-4 d-flex align-items-center justify-content-center">
            <span class="px-1">Don't have an account? </span>
            <div class="justify-content-end d-flex">
            <button @click="navigateRegistrationPage" class="btn btn-light rounded-pill btn-outline-dark ml-2">
              Register
            </button>
          </div>
          </div>
        </div>
        <div class="col-3"></div>
        <!-- <div class="col"></div>      -->
      </div>
    </div>
    <div></div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "UserLogin",
  data() {
    return {
      loginForm: {
        username: "",
        password: "",
      },
    };
  },
  methods: {
    async loginUser() {
      try {
        const response = await axios.post('http://localhost:8080/api/user/login', {
          username: this.loginForm.username, // Replace with actual data from form
          password: this.loginForm.password  // Replace with actual data from form
        });
        console.log('Login successful', response.data);
        this.username = this.loginForm.username;
        sessionStorage.setItem('username', this.loginForm.username);
        sessionStorage.setItem("userType", response.data.userType);
        const userType = response.data.userType;

        if (userType === 'CUSTOMER') {
          this.$router.push('/events'); // Replace '/customerPage' with the actual path
        } else {
          this.$router.push('/homepage'); // Default redirection for other user types
        }
      } catch (error) {
        console.error("Login failed", error);
        alert("Login failed. Try again.");
      }
    },
    navigateRegistrationPage() {
      this.$router.push('/RegistrationPage'); // Make sure the path matches your router configuration
    }
  }
};
</script>
