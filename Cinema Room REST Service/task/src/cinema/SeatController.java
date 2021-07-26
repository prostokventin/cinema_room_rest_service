package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class SeatController {

    private Cinema cinema = new Cinema(9, 9);

    @GetMapping("/seats")
    public Cinema getSeats() {
        return cinema;
    }

    @PostMapping("/purchase")
    public @ResponseBody ResponseEntity<?> purchaseTicket(
            @RequestBody Seat seat
    ) {
        try {
            Purchase purchasedSeat = cinema.purchaseSeat(seat);
            return ResponseEntity.ok(purchasedSeat);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/return")
    public @ResponseBody ResponseEntity<?> returnTicket (@RequestBody Token token) {
        try {
            Seat returnedSeat = cinema.returnTicket(UUID.fromString(token.getToken()));
            Map<String, Seat> response = Map.of("returned_ticket", returnedSeat);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/stats")
    public @ResponseBody ResponseEntity<?> getStatistic (
            @RequestParam(value = "password", required = false) String password
    ) {
        if (!"super_secret".equals(password)) {
            return new ResponseEntity(Map.of("error", "The password is wrong!"), HttpStatus.UNAUTHORIZED);
        }
        Map<String, Integer> response = new HashMap<>();
        response.put("current_income", cinema.getCurrentIncome());
        response.put("number_of_available_seats", cinema.getNumberOfAvailableSeats());
        response.put("number_of_purchased_tickets", cinema.getNumberOfPurchasedTickets());
        return ResponseEntity.ok(response);
    }

}
