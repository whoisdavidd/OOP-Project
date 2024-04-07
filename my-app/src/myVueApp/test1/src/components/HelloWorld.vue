<template>
  <div class="hello">
    <div>
      <div class="container-fluid">
        <div class="d-flex p-5 ms-5 row mx-5">
          <!-- content -->
          <div class="col-md-6"></div>
          <div class="p-2 mt-5 col-md-6">
            <h5>INTRODUCING TICKET MISTER</h5>
            <h1 style="color: palegoldenrod">Buy your tickets now.</h1>
            <p>
              Ticket Mister is your one-stop destination for all your event and
              concert ticket needs. Whether you're looking to attend a music
              concert, a sports event, or a theater show, Ticket Mister has got
              you covered. With a wide range of events to choose from and easy
              booking options, finding and booking tickets for your favorite
              events has never been easier.
            </p>
            <!-- search -->
            <form class="py-3">
              <div class="input-group input-group-md mb-2 mt-4">
                <input
                  class="form-control w-75 me-2 rounded-pill"
                  type="search"
                  placeholder="Search for upcoming events..."
                />
                <button
                  class="btn btn-light rounded-pill btn-outline-dark"
                  type="submit"
                >
                  Search
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: "HelloWorld",
  props: {
    msg: String,
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.container-fluid {
  background-image: url("../img/background.jpg"); /* Path to your background image */
  background-size: cover;
  background-position: center;
  color: white; /* Set text color to white for better readability */
  padding: 20px; /* Add padding to the container */
}

input,
button,
img {
  box-shadow: 0 4px 8px 0 rgba(255, 255, 255, 0.6), 0 6px 20px 0 rgba(255, 255, 255, 0.19);
}
</style>
