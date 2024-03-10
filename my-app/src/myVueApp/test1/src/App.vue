<template>
  <div id="app">
    <nav>
      <!-- Add your navigation links here -->
      <router-link to="/">Home</router-link>
      <!-- Other navigation links -->
    </nav>
    <img alt="Vue logo" src="./assets/logo.png">
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>