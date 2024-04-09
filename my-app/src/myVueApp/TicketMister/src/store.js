import { createStore } from 'vuex';

export default createStore({
  state: {
    isLoggedIn: !!sessionStorage.getItem('username'),
    userType: sessionStorage.getItem('userType'),
  },
  mutations: {
    login(state, payload) {
        state.isLoggedIn = true;
        state.userType = payload.userType;
        sessionStorage.setItem('username', payload.username);
        sessionStorage.setItem('userType', payload.userType);
      },
      logout(state) {
        state.isLoggedIn = false;
        state.userType = null;
        sessionStorage.removeItem('username');
        sessionStorage.removeItem('userType');
      }
  }
});