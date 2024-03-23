<template>
  <div class="UserLogin">
    <div class="container" style="margin-top: 56px">
      <div class="row">
        <div class="col-6"></div>
        <div class="col-6">
          <h2>Login</h2>
        </div>
      </div>
      <div class="row">
        <div class="col-6"></div>
        <div class="col-6">
          <form @submit.prevent="loginUser">

            <div class="form-group">
              <label for="email">Email address</label>
              <input type="email" class="form-control" id="email" name="email" v-model="loginForm.username"
                placeholder="Enter a valid email address" required="true" />
            </div>
            <div class="form-group">
              <label for="pwd">Password</label>
              <input type="password" class="form-control" id="pwd" v-model="loginForm.password" name="password"
                placeholder="Enter password" required="true" />
            </div>

            <div class="text-left mt-3">
              <button type="submit" class="btn btn-light rounded-pill btn-outline-dark">
                Submit
              </button>
            </div>
          </form>
        </div>
        <!-- <div class="col"></div>      -->
      </div>
    </div>
    <div>

    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "UserLogin",
  data() {
    return {
      loginForm: {
        username: '',
        password: ''
      }
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
        this.$router.push('/homepage'); // Navigate to the homepage

      } catch (error) {
        console.error('Login failed', error);
      }
    }
  }
};
</script>
