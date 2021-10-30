package edu.miu.cs.cs544.exercise07_1;

import edu.miu.cs.cs544.exercise07_1.model.Airline;
import edu.miu.cs.cs544.exercise07_1.model.Flight;
import lombok.SneakyThrows;

import javax.persistence.TemporalType;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static edu.miu.cs.cs544.exercise07_1.HibernateUtils.withTx;

public class App {
    static {
        Database.initialize();
    }

    public static void main(String[] args) {

        // a) Flights leaving USA capacity > 500
        withTx(session -> {
            // TODO update HQL
            @SuppressWarnings("unchecked")
            List<Flight> flights = session
                    .createQuery("from Flight as f where f.origin.country = :origin and f.airplane.capacity > :capacity")
                    .setParameter("origin", "USA")
                    .setParameter("capacity", 500)
                    .list();
            System.out.println("Flight:  Departs:     "
                    + "                  Arrives:");
            for (Flight flight : flights) {
                System.out.printf("%-7s  %-12s %7s %8s  %-12s %7s %8s\n",
                        flight.getFlightnr(), flight.getOrigin().getCity(),
                        flight.getDepartureDate(), flight.getDepartureTime(),
                        flight.getDestination().getCity(), flight
                                .getArrivalDate(), flight.getArrivalTime());
            }
        });

        // b) All airlines that use A380 airplanes
        withTx(session -> {
            // TODO update HQL
            @SuppressWarnings("unchecked")
            List<Airline> airlines = session
                    .createQuery("select a from Airline a join a.flights f where f.airplane.model = :model")
                    .setParameter("model", "A380")
                    .list();
            System.out.println("Airlines:");
            for (Airline airline : airlines) {
                System.out.printf("%-15s\n", airline.getName());
            }
        });

        // c) Flights using 747 planes that don't belong to Star Alliance
        withTx(session -> {
            // TODO update HQL
            @SuppressWarnings("unchecked")
            List<Flight> flights = session
                    .createQuery("from Flight f where f.airplane.model = :model and f.airline.name <> :airline")
                    .setParameter("model", "747")
                    .setParameter("airline", "Star Alliance")
                    .list();
            System.out.println("Flight:  Departs:     "
                    + "                  Arrives:");
            for (Flight flight : flights) {
                System.out.printf("%-7s  %-12s %7s %8s  %-12s %7s %8s\n",
                        flight.getFlightnr(), flight.getOrigin().getCity(),
                        flight.getDepartureDate(), flight.getDepartureTime(),
                        flight.getDestination().getCity(), flight
                                .getArrivalDate(), flight.getArrivalTime());
            }
        });

        // d) All flights leaving before 12pm on 08/07/2009
        withTx(session -> {
            // TODO update HQL
            @SuppressWarnings("unchecked")
            List<Flight> flights = session
                    .createQuery("from Flight f where f.departureTime < :time and f.departureDate = :date")
                    .setParameter("time", toTime("12:00 PM"), TemporalType.TIME)
                    .setParameter("date", toDate("08/07/2009"), TemporalType.DATE)
                    .list();
            System.out.println("Flight:  Departs:     "
                    + "                  Arrives:");
            for (Flight flight : flights) {
                System.out.printf("%-7s  %-12s %7s %8s  %-12s %7s %8s\n",
                        flight.getFlightnr(), flight.getOrigin().getCity(),
                        flight.getDepartureDate(), flight.getDepartureTime(),
                        flight.getDestination().getCity(), flight
                                .getArrivalDate(), flight.getArrivalTime());
            }
        });

        System.exit(0);
    }

    @SneakyThrows
    private static Date toDate(String dateString) {
        return new SimpleDateFormat("MM/dd/yyyy").parse(dateString);
    }

    @SneakyThrows
    private static Date toTime(String dateString) {
        return new SimpleDateFormat("hh:mm aa").parse(dateString);
    }
}
