<template>
    <div class="home">
        <!-- Display the welcome message with the username -->
        <h1>Welcome, {{ username }}!</h1>
        <p>This is a simple homepage created with Vue.js.</p>
    </div>
</template>

<script>
export default {
    name: 'HomePage',
    data() {
        return {
            // Initialize username with an empty string
            username: '',
        };
    },
    mounted() {
        // Retrieve the username from sessionStorage when the component mounts
        this.username = sessionStorage.getItem('username');
        // 'Guest' is a fallback in case there's no username in sessionStorage
    },
}
</script>

<style scoped>
.home {
    text-align: center;
    margin-top: 50px;
}
</style>
