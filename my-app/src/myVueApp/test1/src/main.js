import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

const app = createApp(App)

// Register the Vue Router instance
app.use(router)

app.mount('#app')