<template>
    <div class="booking-page p-3 rounded-4" style="background-color: rgba(128, 128, 128, 0.6); margin-right: 300px; margin-left: 300px">
        <div class="booking-details">
            <h2 class="text-center" style="color:palegoldenrod">Booking Details</h2>
            <ul class="flex-column d-flex justify-content-center align-items-center list-unstyled lis">
                <li><strong>Event:</strong> {{ selectedEventName }}</li>
                <li><strong>Number of Tickets:</strong> {{ numTickets }}</li>
                <li><strong>Ticket Option:</strong> {{ ticketingOptionName }}</li>
                <li><strong>Username:</strong> {{ username }}</li>
                <li><strong>Price: </strong>{{ price }}</li>
                <!-- Add more details as needed -->
            </ul>
        </div>
        <div class="payment-button d-flex justify-content-center">
            <button @click="handlePayment" class="btn btn-outline-dark btn-light rounded-pill">Pay</button>
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
            price:0,
            // Add other necessary data properties like eventId, price, etc.
        };
    },
    mounted() {
       
        const bookingDetails = this.$route.query;
        console.log(bookingDetails)
        this.price = bookingDetails.price;
        this.selectedEventName = bookingDetails.selectedEventName; // You need to pass this as a query param
        this.numTickets = bookingDetails.numTickets;
        this.ticketingOptionName = bookingDetails.ticketOptionName; // You need to pass this as a query param
        this.username = bookingDetails.username;// Contains { numTickets, selectedEvent, ticketingOptionID, username }
    },
    methods: {
        async handlePayment() {
            const order = {
                eventName: this.selectedEventName,
                price: parseInt(this.price,10)* parseInt(this.numTickets),
                username: this.username,
                // email: this.userEmail,
                ticketingOptionId: this.ticketingOptionName,
                numTickets: this.numTickets
                
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
            console.log(JSON.stringify(order)); // Log the order object to debug its structure


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