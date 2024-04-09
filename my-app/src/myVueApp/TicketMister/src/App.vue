


<template>
  <div class="App">
    <div class="header">
      <!-- nav bar -->
      <nav class="navbar navbar-expand-lg py-2 fixed-top position-fixed justify-content-between w-100"
        style="z-index:1000;">
        <div class="container-fluid">
          <router-link to="/" class="navbar-brand badge rounded-pill bg-dark fs-6">Ticket Mister</router-link>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <div class="navbar-nav ms-auto my-3">
              <ul class="navbar-nav">
                <li class="nav-item px-5">
                  <router-link to="/" style="text-decoration: none; color: teal;" class="fs-4">Home</router-link>
                </li>
                <li class="nav-item px-5">
                  <router-link to="/events" style="text-decoration: none;  color: teal;"
                    class="fs-4">Events</router-link>
                </li>
                <li class="nav-item px-5" v-if="isEventManager">
  <router-link to="/EventReports" style="text-decoration: none; color: teal;" class="fs-4">Reports</router-link>
</li>
<li class="nav-item px-5" v-if="isEventManager">
  <router-link to="/TicketingOfficer" style="text-decoration: none; color: teal;" class="fs-4">Add TO</router-link>
</li>
                <li class="nav-item px-5">
                  <div v-if="isLoggedIn">
                    <div class="dropdown">
                      <button class="btn btn-info dropdown-toggle" type="button" id="dropdownMenuButton"
                        data-bs-toggle="dropdown" aria-expanded="false">
                        Profile
                      </button>
                      <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <li><a class="dropdown-item" href="Profile">View Details</a></li>
                        <li><a class="dropdown-item" href="BookingHistory">Booking History</a></li>
                        <li><a class="dropdown-item" href="Login" @click.prevent="logout" >Logout</a></li>
                      </ul>
                    </div>
                  </div>
                  <router-link v-else to="/login" style="text-decoration: none; color: teal"
                    class="fs-5">Login</router-link>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </nav>
    </div>
    <!-- page content -->
    <div class="content" style="margin-top: 56px; margin-bottom: 100px; overflow-y: auto">
      <router-view></router-view>
    </div>
    <!-- footer -->
    <footer class="footer mt-auto py-2">
      <div class="container">
        <div class="row">
          <div class="col-md-1"></div>
          <div class="col-md-5">
            <h4>Contact Information</h4>
            <p class="">Address: 80 Flora Road</p>
            <p>Phone: +65 93294821</p>
            <p>Email: ticketmister8888@gmail.com</p>
          </div>
          <!-- <div class="col-md-4">
            <h5>Social Media</h5>
            <ul>
              <li>Facebook</li>
              <li>Twitter</li>
              <li>Instagram</li>
              <li>LinkedIn</li>
            </ul>
          </div> -->
          <div class="col-md-5">
            <h5>Quick Links</h5>
            <ul>
              <li>Home</li>
              <li>Events</li>
              <li>Contact Us</li>
            </ul>
          </div>
          <div class="col-md-1"></div>
        </div>
      </div>
      <div class="text-center text-primary">
        <p style="color: teal">&copy; 2022  Ticket Mister. All Rights Reserved.</p>
      </div>
    </footer>
  </div>
</template>

<script>
export default {
  name: "App",
  data() {
    return {
      isLoggedIn: sessionStorage.getItem('username') !== null,
      username: sessionStorage.getItem('username'),
      isEventManager: sessionStorage.getItem('userType') === 'EVENT_MANAGER',
    };
  },
  watch: {
    isLoggedIn: {
      handler(newValue) {
        this.isLoggedIn = newValue;
      },
      immediate: true,
    },
    username(newUsername) {
    sessionStorage.setItem('username', newUsername);
  },
    isEventManager: {
      handler(newValue) {
        this.isEventManager = newValue;
      },
      immediate: true,
    },
  },
  methods: {
    logout() {
      sessionStorage.removeItem('username');
      this.isLoggedIn = false;
      this.username = null;
      this.isEventManager = false;
    },
    login() {
      // After successful login
      this.isLoggedIn = true;
      this.username = sessionStorage.getItem('username');
      this.isEventManager = sessionStorage.getItem('userType') === 'EVENT_MANAGER';
    },
  },
};
</script>
<style>
nav {
  background: white;
  top: 0;
}

footer {
  position: relative;
  bottom: 0;
  width: 100%;
}

.background {
  background-image: url("img/background.jpg");
  /* Path to your background image */
  background-size: cover;
  background-position: center;
  color: white;
  /* Set text color to white for better readability */
  padding: 20px;
  /* Add padding to the container */
}
.btn-teal {
  background-color: teal; /* Teal background */
  color: white; /* White text */
  border-color: teal; /* Teal border */
}

.btn-teal:hover {
  background-color: darkcyan; 
  border-color: darkcyan;
}

</style>


