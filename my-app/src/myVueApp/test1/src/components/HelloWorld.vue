<template>
  <div class="hello">
    <head>
      <!-- bootstrap link -->
      <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
        crossorigin="anonymous"
      />
    </head>
    <div>
      <div class="container-fluid">
        <div class="d-flex p-5 ms-5 row m-5">
          <!-- content -->
          <div class="p-2 mt-5 col-md-6">
            <h5 style="color: teal">INTRODUCING TICKET MISTER</h5>
            <h1 style="color: mediumturquoise">Buy your tickets now.</h1>
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

          <!-- pic -->
          <div class="mx-auto col-md-6 d-flex justify-content-center">
            <img src="../img/images.png" width="400px" height="470px"/>
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
input,
button, img {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}
</style>
