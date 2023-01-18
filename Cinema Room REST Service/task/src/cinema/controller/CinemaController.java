package cinema.controller;

import cinema.model.Cinema;
import cinema.model.Seat;
import cinema.service.PurchaseService;
import cinema.util.Constants;
import cinema.util.ErrorMessage;
import cinema.util.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.Map;

@RestController
public class CinemaController {

    private final Cinema cinema;
    private final PurchaseService service;

    @Autowired
    public CinemaController(Cinema cinema, PurchaseService service) {
        this.cinema = cinema;
        this.service = service;
    }

    @GetMapping("/seats")
    public Cinema getCinema() {
        return cinema;
    }

    @PostMapping("/purchase")
    public Map<String, Object> buySeat(@RequestBody Seat seat) {
        seat.setPrice(seat.getRow() <= 4 ? 10 : 8);
        return service.buyTicket(seat);
    }

    @PostMapping("/return")
    public Map<String, Object> returnTicket(@RequestBody Map<String, String> token){
        return service.returnTicketBy(token);
    }

    @PostMapping("/stats")
    public Map<String, Integer> showStats(@RequestParam(required = false) String password) {
        if (Constants.SECRET.equals(password)) {
            return service.showStats();
        } else {
            throw new UnauthorizedException("The password is wrong!");
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ErrorMessage exceptionBRHandler(ValidationException e) {
        return new ErrorMessage(e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ErrorMessage exceptionUNHandler(UnauthorizedException e) {
        return new ErrorMessage(e.getMessage());
    }
}
