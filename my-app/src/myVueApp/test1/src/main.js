import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

const app = createApp(App).use(store)

// Register the Vue Router instance
app.use(router)

app.mount('#app')