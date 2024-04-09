


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
              <ul class="navbar-nav align-items-center">
                <li class="nav-item px-5">
                  <router-link to="/" style="text-decoration: none; color: teal;" class="fs-5">Home</router-link>
                </li>
                <li v-if="isCustomer || (!isEventManager && !isTicketingOfficer)" class="nav-item px-5">
                  <router-link to="/events" style="text-decoration: none;  color: teal;"
                    class="fs-5">Events</router-link>
                </li>
                <li v-if="isTicketingOfficer" class="nav-item px-5">
                  <router-link to="/OnsiteSale" style="text-decoration: none;  color: teal;"
                    class="fs-5">Events</router-link>
                </li>
                <li v-if="isTicketingOfficer" class="nav-item px-5">
                  <router-link to="/VerifyTicket" style="text-decoration: none;  color: teal;"
                    class="fs-5">Verify Tickets</router-link>
                </li>
                <li v-if="isEventManager" class="nav-item px-5">
                  <router-link to="/ManageEventPage" style="text-decoration: none;  color: teal;"
                    class="fs-5">Manage Events</router-link>
                </li>
                <li v-if="isEventManager" class="nav-item px-5">
                  <router-link to="/EventReports" style="text-decoration: none;  color: teal;"
                    class="fs-5">Event Reports</router-link>
                </li>
                <li v-if="isEventManager" class="nav-item px-5">
                  <router-link to="/ticketingOfficer" style="text-decoration: none;  color: teal;"
                    class="fs-5">Add Ticketing Officer</router-link>
                </li>
                <li class="nav-item px-4">
                  <div v-if="isLoggedIn">
                    <div class="dropdown">
                      <button class="btn dropdown-toggle fs-5" type="button" id="dropdownMenuButton"
                        data-bs-toggle="dropdown" aria-expanded="false" style="color: teal">
                        Profile
                      </button>
                      <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <li><a class="dropdown-item" href="Profile">View Details</a></li>
                        <li v-if="isCustomer">
                          <a class="dropdown-item" href="BookingHistory">Booking History</a>
                        </li>
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
    <div class="content background" style="margin-top: 90px; margin-bottom: 40px;">
      <router-view></router-view>
    </div>
    <!-- footer -->
    <footer class="footer py-2">
      <div class="container">
        <div class="row">
          <div class="col-md-1"></div>
          <div class="col-md-4 d-flex flex-column align-items-start ">
            <h5 style="color: teal;">Contact Information</h5>
            <p class="">Address: 80 Flora Road</p>
            <p>Phone: +65 93294821</p>
            <p>Email: ticketmister8888@gmail.com</p>
          </div>
          <div class="col-md-3 d-flex flex-column align-items-start">
            <h5 style="color:teal;">Social Media</h5>
            <ul>
              <li><a href="https://www.facebook.com/TicketmasterSG/">Facebook</a></li>
              <li><a href="https://www.instagram.com/ticketmastersg/">Instagram</a></li>
              <li><a href="https://twitter.com/Ticketmaster?ref_src=twsrc%5Egoogle%7Ctwcamp%5Eserp%7Ctwgr%5Eauthor">Twitter</a></li>
              <li><a href="https://www.linkedin.com/company/ticketmaster-singapore/?originalSubdomain=sg">LinkedIn</a></li>
            </ul>
          </div>
          <div class="col-md-3  d-flex flex-column align-items-start">
            <h5 style="color:teal" class="text-center">Quick Links</h5>
            <ul class="">
              <li style=""><router-link to="/">Home</router-link></li>
              <li style=""><router-link to="/events">Events</router-link></li>
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
import { mapState } from 'vuex';

export default {
  name: "App",
  computed: {
    ...mapState(['isLoggedIn']),
    isCustomer() {
      return this.$store.state.userType === 'CUSTOMER';
    },
    isTicketingOfficer() {
      return this.$store.state.userType === 'TICKETING_OFFICER';
    },
    isEventManager() {
      return this.$store.state.userType === 'EVENT_MANAGER';
    }
  },
  methods: {
    logout() {
      this.$store.commit('logout');
      this.$router.push('/login');
    }
    
  }
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

</style>


