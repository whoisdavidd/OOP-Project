<template>
    <button @click="handlePayment">Pay</button>
</template>

<script>
import { loadStripe } from '@stripe/stripe-js'

export default {
    methods: {
        async handlePayment() {
            const stripe = await loadStripe('pk_test_51OsmSsKX6HP397yNF7cZjHYGBRitXBsykAKrwFCRW9kLMgPhHY9cCNsMDRk2QhLHraes63h0It3ixiKDXwXcdAbd00W9ZX1FKi') // min key

            const { error } = await stripe.redirectToCheckout({
                lineItems: [{ price: 'price_1OsnikKX6HP397yN81vfOypC', quantity: 1 }],
                mode: 'payment',
                successUrl: `${window.location.origin}/payment-success`,
                cancelUrl: `${window.location.origin}/payment-cancel`,
            })

            if (error) {
                console.error(error)
            }
        },
    },
}
</script>