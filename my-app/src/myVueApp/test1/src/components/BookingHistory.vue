<template>
    <div class="BookingHistory" style="margin-top: 40px; margin-bottom: 40px; color:palegoldenrod">
        <h1 class="text-center">Your Booking History</h1>
        <div v-if="!tickets" style="color:teal">
            You do not have any current bookings.
        </div>
        <div v-for="eventInfo in tickets" :key="eventInfo.eventName" class="event-info" style="background-color: rgba(128, 128, 128, 0.7);">
            <h2>{{ eventInfo.eventName }} - Total Tickets: {{ eventInfo.totalTickets }}</h2>
            <div v-for="(count, tierName) in eventInfo.tiers" :key="tierName" class="tier-info">
                <strong>Tier Name:</strong> {{ tierName }} - <strong>Tickets:</strong> {{ count }}
            </div>
            <div>
                <strong>Booking Dates:</strong>
                <ul>
                    <li v-for="date in eventInfo.bookingDates" :key="date">{{ date }}</li>
                </ul>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios';


export default {
    name: 'BookingHistory',
    data() {
        return {
            tickets: [],
            // Assuming you will use these or update them as per your component's need

        };
    },
    created() {
        this.fetchTickets();
    },
    methods: {
        async fetchTickets() {
            try {
                const Username = sessionStorage.getItem('username');
                const response = await axios.get(`http://localhost:8080/ticket/getTicketsByCustomer/${Username}`);
                console.log(response.data);
                this.tickets = response.data;

                const eventTicketCounts = {};

                // Iterate over each ticket to accumulate data
                this.tickets.forEach(ticket => {
                    const eventName = ticket.event.eventName;
                    const tierName = ticket.ticketingOption.tierName;
                    const BookingDate = ticket.event.eventDate;


                    // Initialize event in the accumulator if not present
                    if (!eventTicketCounts[eventName]) {
                        eventTicketCounts[eventName] = {
                            count: 0,
                            tiers: {},
                            bookingDates: new Set() // Use a Set to automatically handle unique values
                        };
                    }

                    // Add the booking date to the Set of dates for the event
                    eventTicketCounts[eventName].bookingDates.add(BookingDate);

                    // Increment the total count for the event
                    eventTicketCounts[eventName].count++;

                    // Increment the count for the tier within the event
                    if (!eventTicketCounts[eventName].tiers[tierName]) {
                        eventTicketCounts[eventName].tiers[tierName] = 1;
                    } else {
                        eventTicketCounts[eventName].tiers[tierName]++;
                    }
                });

                // Convert the accumulated data into an array format for display
                // Also convert the Set of booking dates back into an array
                const formattedTicketsData = Object.entries(eventTicketCounts).map(([eventName, data]) => ({
                    eventName,
                    totalTickets: data.count,
                    tiers: data.tiers,
                    bookingDates: [...data.bookingDates], // Convert Set to Array
                }));
                // You can assign it to a data property to use in your template
                console.log(formattedTicketsData);
                this.tickets = formattedTicketsData;
            } catch (error) {
                console.error('Failed to fetch tickets:', error);
            }
        },
        async cancelTicket(ticketId) {
            try {
                await axios.delete(`http://localhost:8080/ticket/Cancellation/${ticketId}`);
                this.tickets = this.tickets.filter((ticket) => ticket.id !== ticketId);
                alert('Ticket canceled successfully');
            } catch (error) {
                console.error('Failed to cancel ticket:', error);
            }
        },
    },
};
</script>

<style>
.BookingHistory {
    margin: 20px;
}

.event-info {
    margin-bottom: 20px;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
}

.event-info h2 {
    margin-top: 0;
}

.tier-info {
    margin-top: 10px;
}
</style>