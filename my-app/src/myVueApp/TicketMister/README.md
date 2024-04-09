# TICKET MISTER

## Project setup
```
npm install
```

### Compiles and minifies for production
```
npm run build
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).


### How to run the application

### Step 1: Run the Springboot server needs to go to the my-app directory
```
mvn spring-boot:run
```
### Step 2: Run the frontend application, go to my Ticket Mister directory

```
npm run serve 
```

### Step 3: In another terminal, run NGROK for the Stripe Payment to work
```
ngrok http --domain=meet-good-aphid.ngrok-free.app 8080
```




