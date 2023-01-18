package cinema.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Cinema {
    private int totalRows;
    private int totalColumns;

    private Seat[] availableSeats;

    @Autowired
    public Cinema(@Qualifier("getTotalRows") int totalRows,
                  @Qualifier("getTotalColumns") int totalColumns,
                  @Qualifier("getSeats") Seat[] availableSeats) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        this.availableSeats = availableSeats;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
    }

    public Seat[] getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Seat[] availableSeats) {
        this.availableSeats = availableSeats;
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "totalRows=" + totalRows +
                ", totalColumns=" + totalColumns +
                ", availableSeats=" + Arrays.toString(availableSeats) +
                '}';
    }
}
