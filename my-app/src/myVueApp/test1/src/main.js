import 'bootstrap/dist/css/bootstrap.css';
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import "bootstrap"


const app = createApp(App).use(store)

// Register the Vue Router instance
app.use(router)

app.mount('#app')