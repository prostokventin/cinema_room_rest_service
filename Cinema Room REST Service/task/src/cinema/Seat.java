package cinema;

import java.util.Objects;

public class Seat {
    private int row;
    private int column;
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice() {
        this.price = this.row <= 4 ? 10 : 8;
    }

    public Seat() {
        setPrice();
    };

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        setPrice();
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return row == seat.row && column == seat.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
