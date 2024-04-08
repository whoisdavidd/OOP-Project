<template>
    <div class="booking-page">
        <div class="booking-details">
            <h2>Booking Details</h2>
            <ul>
                <li><strong>Event:</strong> {{ selectedEventName }}</li>
                <li><strong>Number of Tickets:</strong> {{ numTickets }}</li>
                <li><strong>Ticket Option:</strong> {{ ticketingOptionName }}</li>
                <li><strong>Username:</strong> {{ username }}</li>
                <!-- Add more details as needed -->
            </ul>
        </div>
        <div class="payment-button">
            <button @click="handlePayment">Pay</button>
        </div>
    </div>
</template>
<script>
import { loadStripe } from '@stripe/stripe-js'

export default {
    name: 'checkoutPage',
    data(){
        return {
            selectedEventName: '',
            numTickets: 0,
            ticketingOptionName: '',
            username: '',
            // Add other necessary data properties like eventId, price, etc.
        };
    },
    mounted() {
       
        const bookingDetails = this.$route.query;
        console.log(bookingDetails)
        this.selectedEventName = bookingDetails.selectedEventName; // You need to pass this as a query param
        this.numTickets = bookingDetails.numTickets;
        this.ticketingOptionName = bookingDetails.ticketOptionName; // You need to pass this as a query param
        this.username = bookingDetails.username;// Contains { numTickets, selectedEvent, ticketingOptionID, username }
    },
    methods: {
        async handlePayment() {
            const order = {
                eventName: this.selectedEventName,
                // price: this.event.price,
                username: this.username,
                // email: this.userEmail,
                ticketingOptionId: this.ticketingOptionName,

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