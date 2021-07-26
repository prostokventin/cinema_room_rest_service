package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class Cinema {

    private int totalRows;
    private int totalColumns;

    private List<Seat> availableSeats = new ArrayList<>();
    private Map<UUID, Seat> purchasedSeats = new HashMap<>();

    public Cinema(int totalRows, int totalColumns) {

        this.totalRows = totalRows;
        this.totalColumns = totalColumns;

        for (int i = 1; i <= totalRows; i++) {
            for (int j = 1; j <= totalColumns; j++) {
                this.availableSeats.add(new Seat(i, j));
            }
        }
    }

    @JsonProperty("total_rows")
    public int getTotalRows() {
        return totalRows;
    }

    @JsonProperty("total_columns")
    public int getTotalColumns() {
        return totalColumns;
    }

    @JsonProperty("available_seats")
    public List<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public Purchase purchaseSeat(Seat seat) throws IllegalArgumentException{
        if (
            (seat.getColumn() <= 0) || (seat.getColumn() > this.totalColumns) ||
            (seat.getRow() <= 0) || (seat.getRow() > this.totalRows)
        ) {
            throw new IllegalArgumentException("The number of a row or a column is out of bounds!");
        }
        if (!availableSeats.contains(seat)) {
            throw new IllegalArgumentException("The ticket has been already purchased!");
        }
        availableSeats.remove(seat);
        totalRows--;
        totalColumns--;
        Purchase purchase = new Purchase(seat);
        purchasedSeats.put(purchase.getToken(), purchase.getTicket());
        return purchase;
    }

    public Seat returnTicket(UUID token) {
        if (!purchasedSeats.containsKey(token)) {
            throw new IllegalArgumentException("Wrong token!");
        }
        Seat returnedSeat = purchasedSeats.get(token);
        purchasedSeats.remove(token);
        availableSeats.add(returnedSeat);
        return returnedSeat;
    }

    @JsonIgnore
    public int getNumberOfAvailableSeats() {
        return availableSeats.size();
    }

    @JsonIgnore
    public int getNumberOfPurchasedTickets() {
        return purchasedSeats.size();
    }

    @JsonIgnore
    public int getCurrentIncome() {
        int income = 0;
        for (Map.Entry<UUID, Seat> entry : purchasedSeats.entrySet()) {
            income += entry.getValue().getPrice();
        }
        return income;
    }
}
