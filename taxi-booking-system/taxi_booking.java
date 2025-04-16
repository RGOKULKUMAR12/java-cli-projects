import java.util.*;

class Basics {
    String name;
    int id;

    public Basics(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Basics(int id) {
        this.id = id;
    }
}

class Taxi extends Basics {
    int earnings = 0;
    int status = 0;
    int bookId;
    char location;
    int lastBookingTime;
    List<Integer> earningsHistory = new ArrayList<>();

    public Taxi(String name, int id, char location) {
        super(name, id);
        this.location = location;
        this.lastBookingTime = 0;
    }

    public boolean isAvailable(char pickupLocation, int pickupTime) {
        return this.status == 0 && this.location == pickupLocation && this.lastBookingTime != pickupTime;
    }

    public int calculateFare(int distance) {
        int fare = 100;
        if (distance > 5) {
            fare += (distance - 5) * 10;
        }
        return fare;
    }

    public void addEarnings(int earning) {
        earnings += earning;
        earningsHistory.add(earning);
    }

    public void showEarningsHistory() {
        System.out.println("Earnings History for Taxi " + name + ": " + earningsHistory);
    }

    @Override
    public String toString() {
        return "Taxi Name: " + name + ", Taxi ID: " + id + ", Location: " + location + ", Earnings: " + earnings + ", Status: " + (status == 0 ? "Available" : "Booked");
    }
}

class Booking extends Basics {
    int custId;
    int taxiId;
    char pickup;
    char drop;
    int pickupTime;
    Taxi taxi;

    public Booking(int id, int custId, char pickup, char drop, int pickupTime, Taxi taxi) {
        super(id);
        this.custId = custId;
        this.pickup = pickup;
        this.drop = drop;
        this.pickupTime = pickupTime;
        this.taxi = taxi;
    }

    @Override
    public String toString() {
        return "Booking ID: " + id + ", Taxi Name: " + taxi.name + ", Customer ID: " + custId + ", Pickup: " + pickup + ", Drop: " + drop + ", Pickup Time: " + pickupTime;
    }
}

public class TaxiBookingSystem {
    static List<Taxi> taxis = new ArrayList<>();
    static List<Booking> bookings = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static char route[] = {'A', 'B', 'C', 'D', 'E'};
    static int bookingId = 1001;

    public static void main(String[] args) {
        taxis.add(new Taxi("Taxi_1", 1001, 'A'));
        taxis.add(new Taxi("Taxi_2", 1002, 'B'));
        taxis.add(new Taxi("Taxi_3", 1003, 'C'));

        while (true) {
            System.out.println("***********************************************************************");
            System.out.println("                         Taxi Booking System                            ");
            System.out.println("1. Book Taxi");
            System.out.println("2. Show All Taxis");
            System.out.println("3. Show Bookings");
            System.out.println("4. Complete Booking");
            System.out.println("5. Show Earnings History");
            System.out.println("6. Exit");
            System.out.println("***********************************************************************");

            System.out.println("Enter Your Choice...");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    bookTaxi();
                    break;
                case 2:
                    showTaxis();
                    break;
                case 3:
                    showBookings();
                    break;
                case 4:
                    completeBooking();
                    break;
                case 5:
                    showEarningsHistory();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        }
    }

    static void bookTaxi() {
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        System.out.print("Enter Pickup Location (A-E): ");
        char pickupLocation = scanner.next().charAt(0);
        System.out.print("Enter Drop Location (A-E): ");
        char dropLocation = scanner.next().charAt(0);
        System.out.print("Enter Pickup Time: ");
        int pickupTime = scanner.nextInt();

        Taxi availableTaxi = findAvailableTaxi(pickupLocation, pickupTime);
        if (availableTaxi == null) {
            System.out.println("No taxi available at the requested location.");
            return;
        }

        availableTaxi.status = 1;
        availableTaxi.bookId = bookingId;
        availableTaxi.lastBookingTime = pickupTime;

        int distance = calculateDistance(pickupLocation, dropLocation);
        int fare = availableTaxi.calculateFare(distance);

        Booking newBooking = new Booking(bookingId, customerId, pickupLocation, dropLocation, pickupTime, availableTaxi);
        bookings.add(newBooking);

        availableTaxi.addEarnings(fare);
        System.out.println("Booking Successful! Fare: ₹" + fare);
        bookingId++;
    }

    static Taxi findAvailableTaxi(char pickupLocation, int pickupTime) {
        for (Taxi taxi : taxis) {
            if (taxi.isAvailable(pickupLocation, pickupTime)) {
                return taxi;
            }
        }
        return null;
    }

    static int calculateDistance(char pickup, char drop) {
        int pickupIndex = Arrays.binarySearch(route, pickup);
        int dropIndex = Arrays.binarySearch(route, drop);
        return Math.abs(pickupIndex - dropIndex) * 15;
    }

    static void completeBooking() {
        System.out.print("Enter Booking ID to complete: ");
        int bookingIdToComplete = scanner.nextInt();

        Booking booking = findBookingById(bookingIdToComplete);
        if (booking != null) {
            booking.taxi.status = 0;
            System.out.println("Booking " + bookingIdToComplete + " completed. Taxi " + booking.taxi.name + " is now available.");
        } else {
            System.out.println("Booking not found.");
        }
    }

    static Booking findBookingById(int bookingId) {
        for (Booking booking : bookings) {
            if (booking.id == bookingId) {
                return booking;
            }
        }
        return null;
    }

    static void showTaxis() {
        for (Taxi taxi : taxis) {
            System.out.println(taxi);
        }
    }

    static void showBookings() {
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }

    static void showEarningsHistory() {
        System.out.print("Enter Taxi ID to view earnings history: ");
        int taxiId = scanner.nextInt();
        Taxi taxi = findTaxiById(taxiId);
        if (taxi != null) {
            taxi.showEarningsHistory();
        } else {
            System.out.println("Taxi not found.");
        }
    }

    static Taxi findTaxiById(int taxiId) {
        for (Taxi taxi : taxis) {
            if (taxi.id == taxiId) {
                return taxi;
            }
        }
        return null;
    }
}
