<template>
    <div class="registration">
        <h1>Create a New Account</h1>
        <form @submit.prevent="register">
            <div>
                <input type="text" v-model="user.firstName" placeholder="First Name" required>
            </div>
            <div>
                <input type="text" v-model="user.lastName" placeholder="Last Name" required>
            </div>
            <div>
                <input type="email" v-model="user.email" placeholder="Email" required>
            </div>
            <div>
                <input type="password" v-model="user.password" placeholder="New Password" required>
            </div>
            <div>
                <button type="submit">Register</button>
            </div>
        </form>
    </div>
</template>

<script>
export default {
    name: 'RegistrationPage',
    data() {
        return {
            user: {
                firstName: '',
                lastName: '',
                email: '',
                password: '',
            }
        };
    },
    methods: {
        register() {
            alert('Registration functionality to be implemented.');
            // Here, you would implement your registration logic,
            // such as validating the input and sending it to your backend server.
        }
    }
}
</script>

<style scoped>
.registration {
    max-width: 400px;
    margin: auto;
    padding: 20px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, .1);
}

input[type=text],
input[type=email],
input[type=password] {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    box-sizing: border-box;
}

button {
    background-color: blue;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 100%;
}

button:hover {
    opacity: 0.8;
}
</style>