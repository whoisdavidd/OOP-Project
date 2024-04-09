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

##### Here's how you can do it for Windows:

1. Go to the [ngrok download page]() in your web browser.
2. Download the Windows zip file.
3. Extract the zip file to a folder of your choice.
4. Open Command Prompt or PowerShell as an administrator.
5. Navigate to the directory where you extracted `ngrok` with the `cd` command.
6. Start `ngrok` by running: 

ngrok config add-authtoken 2bz0DOIAysUQsEJmoh4u8T7JIMR_599fQoyuhFmcByqm8xVeo

```
ngrok http --domain=meet-good-aphid.ngrok-free.app 8080
```

##### Here's how you can do it for Mac:

1. brew install ngrok/ngrok/ngrok
2. Start `ngrok` by running:

ngrok config add-authtoken 2bz0DOIAysUQsEJmoh4u8T7JIMR_599fQoyuhFmcByqm8xVeo

```
ngrok http --domain=meet-good-aphid.ngrok-free.app 8080
```
