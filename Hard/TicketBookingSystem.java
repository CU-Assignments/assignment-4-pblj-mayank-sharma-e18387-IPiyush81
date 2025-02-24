import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class TicketBooking implements Runnable {
    private static final int TOTAL_SEATS = 10;
    private static final List<Boolean> seats = new ArrayList<>();
    private final String customerName;
    private final boolean isVIP;

    static {
        // Initialize seats as unbooked (false)
        for (int i = 0; i < TOTAL_SEATS; i++) {
            seats.add(false);
        }
    }

    public TicketBooking(String customerName, boolean isVIP) {
        this.customerName = customerName;
        this.isVIP = isVIP;
    }

    @Override
    public void run() {
        bookTicket();
    }

    private synchronized void bookTicket() {
        for (int i = 0; i < TOTAL_SEATS; i++) {
            if (!seats.get(i)) {
                seats.set(i, true); // Book the seat
                System.out.println(customerName + " booked seat " + (i + 1));
                return;
            }
        }
        System.out.println(customerName + " could not book a seat. No seats available.");
    }
}

public class TicketBookingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Thread> threads = new ArrayList<>();

        while (true) {
            System.out.print("Enter customer name (or 'exit' to quit): ");
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.print("Is this a VIP booking? (yes/no): ");
            String vipResponse = scanner.nextLine();
            boolean isVIP = vipResponse.equalsIgnoreCase("yes");

            TicketBooking booking = new TicketBooking(name, isVIP);
            Thread thread = new Thread(booking);
            if (isVIP) {
                thread.setPriority(Thread.MAX_PRIORITY); // Set VIP thread priority
            } else {
                thread.setPriority(Thread.NORM_PRIORITY); // Set regular thread priority
            }
            threads.add(thread);
            thread.start();
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("All bookings processed.");
        scanner.close();
    }
}