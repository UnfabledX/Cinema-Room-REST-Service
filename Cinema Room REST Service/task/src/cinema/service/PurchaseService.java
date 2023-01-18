package cinema.service;

import cinema.model.Seat;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;

import java.util.*;

import static cinema.util.Constants.*;

@Service
public class PurchaseService {
    //token id, purchased seat
    private final Map<String, Seat> purchasedSeats = new HashMap<>();

    public Map<String, Object> buyTicket(Seat seat) {
        Map<String, Object> map = new LinkedHashMap<>();
        if ((seat.getRow() > TOTAL_ROWS || seat.getRow() < 1)
                || (seat.getColumn() > TOTAL_COLUMNS || seat.getColumn() < 1)) {
            throw new ValidationException("The number of a row or a column is out of bounds!");
        } else if (purchasedSeats.containsValue(seat)) {
            throw new ValidationException("The ticket has been already purchased!");
        } else {
            UUID uuid = UUID.randomUUID();
            map.put(TOKEN, uuid.toString());
            map.put(TICKET, seat);
            purchasedSeats.put(uuid.toString(), seat);
            return map;
        }
    }

    public Map<String, Object> returnTicketBy(Map<String, String> token) {
        Map<String, Object> map = new HashMap<>();
        String tokenID = token.get(TOKEN);
        if (purchasedSeats.containsKey(tokenID)) {
            map.put(RETURNED_TICKET, purchasedSeats.remove(tokenID));
        } else {
            throw new ValidationException("Wrong token!");
        }
        return map;
    }

    public Map<String, Integer> showStats() {
        Map<String, Integer> map = new LinkedHashMap<>();
        int totalIncome = purchasedSeats.values()
                .stream()
                .map(Seat::getPrice)
                .reduce(Integer::sum).orElse(-1);
        map.put(CURRENT_INCOME, totalIncome);
        int purchasedTickets = purchasedSeats.size();
        int numberSeats = TOTAL_ROWS * TOTAL_COLUMNS - purchasedTickets;
        map.put(SEATS_AVAILABLE, numberSeats);
        map.put(PURCHASED_TICKET, purchasedTickets);
        return map;
    }
}
