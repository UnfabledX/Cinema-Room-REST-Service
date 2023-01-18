package cinema.Config;

import cinema.model.Seat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static cinema.util.Constants.TOTAL_COLUMNS;
import static cinema.util.Constants.TOTAL_ROWS;

@Configuration
public class CinemaConfig {
    @Bean
    public int getTotalRows() {
        return TOTAL_ROWS;
    }

    @Bean
    public int getTotalColumns() {
        return TOTAL_COLUMNS;
    }

    @Bean
    public Seat[] getSeats() {
        int totalSeats = TOTAL_ROWS * TOTAL_COLUMNS;
        Seat[] array = new Seat[totalSeats];
        int k = 1, l = 1;
        for (int i = 0; i < totalSeats; i++) {
            if (l != TOTAL_ROWS) {
                array[i] = new Seat(k, l++, k <= 4 ? 10 : 8);
            } else {
                array[i] = new Seat(k++, l, k <= 4 ? 10 : 8);
                l = 1;
            }
        }
        return array;
    }
}
