package cinema;

import cinema.model.Cinema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {
    private final Cinema cinema;

    public Runner(@Autowired Cinema cinema) {
        this.cinema = cinema;
    }

    @Override
    public void run(String... args) {
        System.out.println(cinema);
    }
}