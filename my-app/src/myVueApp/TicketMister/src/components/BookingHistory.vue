<template>
  <div class="BookingHistory">
    <h1 class="text-center" style="color: palegoldenrod">
      Your Booking History
    </h1>
    <div
      v-for="eventInfo in tickets"
      :key="eventInfo.eventName"
      class="event-info rounded-4"
      style="
        background-color: rgba(128, 128, 128, 0.7);
        margin-right: 200px;
        margin-left: 200px;
      "
    >
      <h2>
        {{ eventInfo.eventName }} - Total Tickets: {{ eventInfo.totalTickets }}
      </h2>
      <div
        v-for="(count, tierName) in eventInfo.tiers"
        :key="tierName"
        class="tier-info"
      >
        <strong>Tier Name:</strong> {{ tierName }} - <strong>Tickets:</strong>
        {{ count.count }}
        <ul>
          <li v-for="ticketID in count.ticketid" :key="ticketID">
            Ticket ID: {{ ticketID }}
            <button
              @click="cancelTicket(ticketID)"
              class="btn-light btn btn-outline-dark rounded-pill btn-sm"
            >
              Cancel Tickets
            </button>
          </li>
        </ul>
      </div>
      <div>
        <strong>Booking Dates:</strong>
        <ul>
          <li v-for="date in eventInfo.bookingDates" :key="date">{{ date }}</li>
        </ul>
        <!-- Cancel Button -->
        <!-- <div class="d-flex justify-content-end">
          <button
            @click="cancelBooking(eventInfo.eventID)"
            class="btn btn-outline-dark btn-light rounded-pill"
          >
            Cancel Booking
          </button>
        </div> -->
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "BookingHistory",
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
        const Username = sessionStorage.getItem("username");
        const response = await axios.get(
          `http://localhost:8080/ticket/getTicketsByCustomer/${Username}`
        );
        console.log(response.data);
        this.tickets = response.data;

        const eventTicketCounts = {};

        // Iterate over each ticket to accumulate data
        this.tickets.forEach((ticket) => {
          const eventName = ticket.event.eventName;
          const tierName = ticket.ticketingOption.tierName;
          const BookingDate = ticket.event.eventDate;
          const ticketID = ticket.ticketID;

          // Initialize event in the accumulator if not present
          if (!eventTicketCounts[eventName]) {
            eventTicketCounts[eventName] = {
              count: 0,
              tiers: {},
              bookingDates: new Set(), // Use a Set to automatically handle unique values
              ticketid: [],
            };
          }
          eventTicketCounts[eventName].ticketid.push(ticketID);
          // Add the booking date to the Set of dates for the event
          eventTicketCounts[eventName].bookingDates.add(BookingDate);

          // Increment the total count for the event
          eventTicketCounts[eventName].count++;

          // Increment the count for the tier within the event
          if (!eventTicketCounts[eventName].tiers[tierName]) {
            eventTicketCounts[eventName].tiers[tierName] = {
              count: 1,
              ticketid: [ticketID],
            };
          } else {
            eventTicketCounts[eventName].tiers[tierName].count++;
            if (!eventTicketCounts[eventName].tiers[tierName].ticketid) {
              eventTicketCounts[eventName].tiers[tierName].ticketid = []; // Ensure initialization
            }
            eventTicketCounts[eventName].tiers[tierName].ticketid.push(
              ticketID
            ); // Correctly pushing to tier's ticketid
          }
        });

        // Convert the accumulated data into an array format for display
        // Also convert the Set of booking dates back into an array
        const formattedTicketsData = Object.entries(eventTicketCounts).map(
          ([eventName, data]) => ({
            eventName,
            totalTickets: data.count,
            tiers: data.tiers,
            bookingDates: [...data.bookingDates], // Convert Set to Array
            ticketIDs: data.ticketid,
          })
        );
        // You can assign it to a data property to use in your template
        console.log(formattedTicketsData);
        this.tickets = formattedTicketsData;
      } catch (error) {
        console.error("Failed to fetch tickets:", error);
      }
    },
    async cancelTicket(ticketId) {
      try {
        console.log(ticketId);
        ticketId = parseInt(ticketId, 10);
        await axios.delete(
          `http://localhost:8080/ticket/Cancellation/${ticketId}`
        );
        this.tickets = this.tickets.filter((ticket) => {
          return !ticket.ticketIDs.includes(ticketId);
        });
        alert("Ticket canceled successfully");
      } catch (error) {
        console.error("Failed to cancel ticket:", error);
      }
    },
    // async cancelBooking(eventId) {
    //   try {
    //     console.log(eventId);
    //     await axios.delete(
    //       `http://localhost:8080/ticket/CancellationByEvent/${eventID}`
    //     );
    //     this.tickets = this.tickets.filter((ticket) => {
    //       return !ticket.ticketIDs.includes(eventId);
    //     }); //isit correct
    //     alert("Event booking canceled successfully");
    //   } catch (error) {
    //     console.error("Failed to cancel booking:", error);
    //   }
    // },
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
}

.event-info h2 {
  margin-top: 0;
}

.tier-info {
  margin-top: 10px;
}
</style>
