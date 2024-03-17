<template>
  <div class="hello">
    <head>
      <!-- bootstrap link -->
      <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
        crossorigin="anonymous"
      />
    </head>
    <div>
      <div class="container-fluid">
        <div class="d-flex p-5 ms-5 row">
          <!-- content -->
          <div class="p-2 mt-5 col-md-6">
            <h6 style="color: white">INTRODUCING TICKET MISTER</h6>
            <h1>Buy your tickets now.</h1>
            <p>
              Ticket Mister is your one-stop destination for all your event and
              concert ticket needs. Whether you're looking to attend a music
              concert, a sports event, or a theater show, Ticket Mister has got
              you covered. With a wide range of events to choose from and easy
              booking options, finding and booking tickets for your favorite
              events has never been easier.
            </p>
            <!-- search -->
            <form class="">
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

          <!-- pic -->
          <div class="p-3 block mx-auto col-md-6">
            <div
              id="carouselExampleControls"
              class="carousel slide mx-auto"
              style="width: 400px; height: 300px"
              data-bs-ride="carousel"
            >
              <div class="carousel-inner">
                <div class="carousel-item active">
                  <img
                    class="d-block w-100 h-100 object-fit-cover rounded-4"
                    src="#"
                    alt="Coldplay"
                  />
                </div>
                <div class="carousel-item">
                  <img
                    class="d-block w-100 h-100 object-fit-cover rounded-4"
                    src="#"
                    alt="Bruno"
                  />
                </div>
              </div>
              <button
                class="carousel-control-prev bg-transparent"
                type="button"
                data-bs-target="#carouselExampleControls"
                data-bs-slide="prev"
              >
                <span
                  class="carousel-control-prev-icon"
                  aria-hidden="true"
                ></span>
                <span class="visually-hidden">Previous</span>
              </button>
              <button
                class="carousel-control-next bg-transparent"
                type="button"
                data-bs-target="#carouselExampleControls"
                data-bs-slide="next"
              >
                <span
                  class="carousel-control-next-icon"
                  aria-hidden="true"
                ></span>
                <span class="visually-hidden">Next</span>
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min.js";

export default {
  name: "HelloWorld",
  props: {
    msg: String,
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
html,
header,
body {
  background-image: linear-gradient(
    to right,
    rgb(147, 147, 223),
    rgb(172, 135, 190) 30%,
    white 100%
  );
}

.navbar {
  background-color: transparent;
}

input,
button {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}
</style>
