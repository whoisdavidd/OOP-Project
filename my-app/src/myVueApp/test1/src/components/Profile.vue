<template>
  <div class="home">
    <h1>Profile</h1>
    <h3>Welcome</h3>
    <button @click="logUserOut()">Log Out</button>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "HomePage",
  data() {
    return {
        user: null,
    }
  },
  mounted() {
    this.fetchUser()
  },
  methods: {
    logUserOut() {
      sessionStorage.removeItem("user");
      console.log("Loggingout" + sessionStorage.getItem("user"));
      this.$router.push("/login");
    },
    async fetchUser() {
      try {
        const username = sessionStorage.getItem("user");

        const response = await axios.get(
          `http://localhost:8080/api/user/${username}`
        );

        this.user = response.data;
        console.log("User data retrieved", this.user);
      } catch (error) {
        console.error("Error fetching user data", error);
      }
    },
  },
};
</script>

<style scoped>
.home {
  text-align: center;
  margin-top: 50px;
}
</style>
