package com.example.demo.controller;

import com.example.demo.entityFile.Events.PaymentRecord;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Refund;
import com.stripe.param.PaymentIntentCreateParams;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.param.checkout.SessionCreateParams;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PaymentController {

    

    @PostMapping("/refund")
    public ResponseEntity<String> refundPayments(@RequestBody List<String> chargeIds) throws StripeException {
        Stripe.apiKey = "sk_test_51OsmSsKX6HP397yN2ScwPeVleE0fxiCE7yoacha0dcoGGgf66qb0PNO8VC7vmLtWFW9Qzw1Rb2kxTIbvbih1hEIh00T4bSnNBe"; // replace
                                                                                                                                       // with
                                                                                                                                       // your
                                                                                                                                       // secret
                                                                                                                                       // key

        for (String chargeId : chargeIds) {
            Map<String, Object> params = new HashMap<>();
            params.put("charge", chargeId);

            Refund refund = Refund.create(params);
        }

        return ResponseEntity.ok("Refunds successful");
    }

    @PostMapping("/webhook-payment-intent-succeeded")
    public ResponseEntity<String> handleStripeEvent(@RequestBody String payload,
            @RequestHeader("Stripe-Signature") String sigHeader) {
        //TODO: implemetn verification with strpe signing secret

        // event object here is not our one, its stripe model class 
        // Event event = Webhook.constructEvent(payload, sigHeader, yourWebhookSecret);

        if ("payment_intent.succeeded".equals(event.getType())) {
            PaymentIntent intent = (PaymentIntent) event.getData().getObject();
            PaymentRecord record = new PaymentRecord();
            record.setChargeId(intent.getId()); // Use Charge ID for charges API
            record.setAmount(new BigDecimal(intent.getAmountReceived())); // Convert amount as needed
            record.setPaymentDate(LocalDateTime.now()); // Set the payment date as now or use the timestamp from the
                                                        // event

            // Save to database
            // paymentRecordRepository.save(record);
        }

        return ResponseEntity.ok().build();
    }

    // i have no idea how this code works hopefulyl we dont need it, need like some
    // proxy config and gson builders in spring boot or smth
    // @PostMapping("/create-checkout-session")
    // public Map<String, String> createCheckoutSession(@RequestBody Map<String,
    // Object> data) throws StripeException {
    // Stripe.apiKey =
    // "sk_test_51OsmSsKX6HP397yN2ScwPeVleE0fxiCE7yoacha0dcoGGgf66qb0PNO8VC7vmLtWFW9Qzw1Rb2kxTIbvbih1hEIh00T4bSnNBe";
    // // min secret key

    // SessionCreateParams params = SessionCreateParams.builder()
    // .addLineItem(
    // SessionCreateParams.LineItem.builder()
    // .setPrice("price_1Hh1SWHipyrrth0oFHN5Zgds")
    // .setQuantity(1L)
    // .build())
    // .setMode(SessionCreateParams.Mode.PAYMENT)
    // .setSuccessUrl("https://your-website.com/payment-success")
    // .setCancelUrl("https://your-website.com/payment-cancel")
    // .build();

    // // Create a Checkout Session
    // Session session = Session.create(params);
    // Map<String, String> responseData = new HashMap<>();
    // responseData.put("sessionId", session.getId());

    // return responseData;
    // }
}