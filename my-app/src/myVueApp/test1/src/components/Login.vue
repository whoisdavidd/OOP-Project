<template>
  <div class="UserLogin">
    <div class="container" style="margin-top: 56px">
      <div class="row">
        <div class="col-6"></div>
        <div class="col-6"><h2>Login</h2></div>
      </div>
      <div class="row">
        <div class="col-6"></div>
        <div class="col-6">
          <form>
            <div class="form-group">
              <label for="email">Email address</label>
              <input
                type="email"
                class="form-control"
                id="email"
                name="email"
                placeholder="Enter a valid email address"
                required="true"
              />
            </div>
            <div class="form-group">
              <label for="pwd">Password</label>
              <input
                type="password"
                class="form-control"
                id="pwd"
                name="password"
                placeholder="Enter password"
                required="true"
              />
            </div>

            <div class="text-left mt-3">
              <button
                type="submit"
                class="btn btn-light rounded-pill btn-outline-dark"
              >
                Submit
              </button>
            </div>
          </form>
        </div>
        <!-- <div class="col"></div>      -->
      </div>
    </div>
    <!-- <div>
            <h1>Login</h1>
        </div>
        <div>
            <form>
                <div>
                    <label>Email Address:</label>
                    <input type="email" placeholder="Enter a valid email address" required="true"/>
                </div>
    
                <div>
                    <label>Password:</label>
                    <input type="password" placeholder="Enter password" required="true"/>
                </div>
                
                <div>
                    <button type="submit">Login</button>
                </div>
            </form>
        </div> -->
  </div>
</template>

<script>
//  import axios from 'axios';
export default {
  name: "UserLogin",
  props: {
    msg: String,
  },
};

// const apiBaseUrl = 'http://localhost:8080/api/users';

// async function fetchUserById(userId) {
//     try {
//         const response = await axios.get(`${apiBaseUrl}/${userId}`);
//         return response.data;
//     } catch (error) {
//         console.error('Error fetching user:', error);
//         return null;
//     }
// }
</script>
