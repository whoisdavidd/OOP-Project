<template>
  <div class="App">
    <div class="header">
      <!-- <nav> -->
      <!-- Add your navigation links here -->
      <!-- <router-link to="/">Home</router-link> -->
      <!-- Other navigation links -->
      <!-- </nav> -->
      <!-- <router-view /> -->

      <!-- nav bar -->
      <nav
        class="navbar navbar-expand-lg mx-5 py-2 fixed-top z-3 position-fixed"
      >
        <div class="container-fluid">
          <router-link
            to="/"
            class="navbar-brand badge rounded-pill bg-dark fs-6"
            >Ticket Mister</router-link
          >
          <button
            class="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent"
          >
            <span class="navbar-toggler-icon"></span>
          </button>

          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <div class="navbar-nav mx-auto my-3">
              <ul class="navbar-nav">
                <li class="nav-item px-5">
                  <router-link
                    to="/"
                    style="text-decoration: none; color: teal;"
                    class="fs-4"
                    >Home</router-link
                  >
                </li>
                <li class="nav-item px-5">
                  <router-link
                    to="/events"
                    style="text-decoration: none;  color: teal;"
                    class="fs-4"
                    >Events</router-link
                  >
                </li>
                <li class="nav-item px-5">
                  <router-link
                    to="/login"
                    style="text-decoration: none;  color: teal;"
                    class="fs-4"
                    >Login</router-link
                  >
                </li>
              </ul>
            </div>
          </div>
        </div>
      </nav>
    </div>
    <!-- page content -->
    <div
      class="content"
      style="margin-top: 56px; margin-bottom: 100px; overflow-y: auto"
    >
      <router-view></router-view>
    </div>
    <!-- footer -->
    <footer class="footer mt-auto py-3">
      <div class="container">
        <div class="row" style="color: white">
          <div class="col-md-1"></div>
          <div class="col-md-5">
            <h4>Contact Information</h4>
            <p class="">Address: 123 Main Street, City, Country</p>
            <p>Phone: +1-234-567-890</p>
            <p>Email: info@example.com</p>
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
            <h4>Quick Links</h4>
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
        <p style="color: teal">&copy; 2022 Your Company Name. All Rights Reserved.</p>
      </div>
    </footer>
  </div>
</template>

<script>
export default {
  name: "App",
  data() {},
};
</script>

<style>

nav {
  background: white;
  top: 0
}

footer {
  position: relative;
  bottom: 0;
  width: 100%;
  background-color: rgba(4, 4, 4);
}
</style>
