<template>
    <button @click="handlePayment">Pay</button>
</template>

<script>
import { loadStripe } from '@stripe/stripe-js'

export default {
    data() {
        return {
            event: {
                eventName: 'Event Name',
                _id: 'event_id',
                price: 100,
            },
            userId: 'user_id',
            userEmail: 'customer@example.com', // Replace with the user's email
        }
    },
    methods: {
        async handlePayment() {
            const order = {
                eventName: this.event.eventName,
                eventId: this.event._id,
                price: this.event.price,
                buyerId: this.userId,
                email: this.userEmail,
            };

            // Make a POST request to your Spring Boot server via ngrok
            const response = await fetch(
                "https://meet-good-aphid.ngrok-free.app/api/checkout",
                {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify(order),
                }
            );

            if (!response.ok) {
                console.error("Checkout request failed with status", response.status);
                return;
            }

            const sessionId = await response.text();
            const stripe = await loadStripe('pk_test_51OsmSsKX6HP397yNF7cZjHYGBRitXBsykAKrwFCRW9kLMgPhHY9cCNsMDRk2QhLHraes63h0It3ixiKDXwXcdAbd00W9ZX1FKi') // min key

            const { error } = await stripe.redirectToCheckout({
                sessionId,
            });

            if (error) {
                console.error("Failed to redirect to Stripe Checkout:", error);
            }
        },
    },
}
</script>