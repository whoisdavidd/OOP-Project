package com.example.demo.controller;

import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StripeController {

    @Value("${stripe.secretKey}")
    private String STRIPE_SECRET_KEY;

    @Value("${stripe.webhookKey}")
    private String STRIPE_WEBHOOK_KEY;

    @PostMapping("/checkout")
    public ResponseEntity<String> createCheckoutSession(@RequestBody Map<String, Object> data) {
        System.out.println("new checkout");
        System.out.println(data + "DATAATATA");
        try {
            Stripe.apiKey = STRIPE_SECRET_KEY;

            SessionCreateParams params = SessionCreateParams.builder()
                    .addLineItem(createLineItem(data))
                    .setMode(SessionCreateParams.Mode.PAYMENT)
                    .setSuccessUrl("http://localhost:8081/payment-success") // Update with your ngrok URL
                    .setCancelUrl("http://localhost:8081/payment-cancel") // Update with your ngrok URL
                    .build();

            Session session = Session.create(params);

            return ResponseEntity.ok(session.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private SessionCreateParams.LineItem createLineItem(Map<String, Object> data) {
        SessionCreateParams.LineItem.PriceData priceData = createPriceData(data);
        return SessionCreateParams.LineItem.builder()
                .setPriceData(priceData)
                .setQuantity(1L)
                .build();
    }

    private SessionCreateParams.LineItem.PriceData createPriceData(Map<String, Object> data) {
        SessionCreateParams.LineItem.PriceData.Builder priceDataBuilder = SessionCreateParams.LineItem.PriceData
                .builder()
                .setCurrency("sgd")
                .setProductData(createProductData(data))
                .setUnitAmount(calculateAmount(data));

        return priceDataBuilder.build();
    }

    private SessionCreateParams.LineItem.PriceData.ProductData createProductData(Map<String, Object> data) {
        SessionCreateParams.LineItem.PriceData.ProductData.Builder productDataBuilder = SessionCreateParams.LineItem.PriceData.ProductData
                .builder()
                .setName((String) data.get("eventName"));

        return productDataBuilder.build();
    }

    private Long calculateAmount(Map<String, Object> data) {
        System.out.println(data + "calcamoutn");
        Integer priceInteger = (Integer) data.get("price");
        Long price = priceInteger.longValue();
        return price * 100; // Stripe expects amount in cents
    }

    @PostMapping("/webhook") // webhook endpoint calling from stripe
    public ResponseEntity<String> handleStripeWebhook(@RequestBody String payload,
            @RequestHeader("Stripe-Signature") String sigHeader) {
        // Initialize Stripe API with your secret key
        Stripe.apiKey = STRIPE_SECRET_KEY;

        String endpointSecret = STRIPE_WEBHOOK_KEY; // Obtain this from your Stripe dashboard

        Event event = null;

        try {
            event = Webhook.constructEvent(
                    payload, sigHeader, endpointSecret);
        } catch (SignatureVerificationException e) {
            // Invalid signature
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Signature verification failed");
        }

        // Handle the event
        switch (event.getType()) {
            case "checkout.session.completed":
                Session session = (Session) event.getData().getObject();
                System.out.println("Payment received for checkout " + session.getId());
                System.out.println(session);
                String customerId = session.getCustomer();
                // Retrieve the customer object from stripe
                String customerEmail = null;
                try {
                    Customer customer = Customer.retrieve(customerId);
                    // Get the customer's email
                    customerEmail = customer.getEmail();
                    System.out.println("Customer email: " + customerEmail);
                    // TODO: Handle successful checkout, save order, send confirmation email
                } catch (StripeException e) {
                    e.printStackTrace();
                    // TODO: Handle the error
                }
       
                
            
              
                // TODO: Handle successful checkout,
                // save order, send confirmation email
                break;
            // Add more cases for other event types as needed
        }

        return ResponseEntity.ok("Received");
    }
}
