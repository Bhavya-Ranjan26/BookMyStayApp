import java.util.HashMap;
import java.util.Map;

/**
 * Abstract Room class representing common properties of rooms.
 * @version 3.0
 */
abstract class Room {

    protected String roomType;
    protected int beds;
    protected double price;

    public Room(String roomType, int beds, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.price = price;
    }

    public void displayRoomDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Beds: " + beds);
        System.out.println("Price per night: ₹" + price);
    }

    public String getRoomType() {
        return roomType;
    }
}


/* Single Room */
class SingleRoom extends Room {

    public SingleRoom() {
        super("Single Room", 1, 1000);
    }
}


/* Double Room */
class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 2, 1800);
    }
}


/* Suite Room */
class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 3, 3500);
    }
}


/**
 * Centralized Room Inventory using HashMap
 * @version 3.0
 */
class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    // Register room type
    public void addRoomType(String type, int count) {
        inventory.put(type, count);
    }

    // Get availability
    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    // Update availability
    public void updateAvailability(String type, int count) {
        inventory.put(type, count);
    }

    // Display inventory
    public void displayInventory() {
        System.out.println("\nCurrent Room Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue() + " rooms available");
        }
    }
}


/**
 * Book My Stay Application
 * Use Case 2 + Use Case 3 Combined
 *
 * @version 3.1
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("       Book My Stay App");
        System.out.println("   Hotel Booking System v3.1");
        System.out.println("=================================");

        // Create room objects (Polymorphism)
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Display room details
        System.out.println("\nRoom Details\n");

        single.displayRoomDetails();
        System.out.println();

        doubleRoom.displayRoomDetails();
        System.out.println();

        suite.displayRoomDetails();

        // Initialize centralized inventory
        RoomInventory inventory = new RoomInventory();

        inventory.addRoomType(single.getRoomType(), 10);
        inventory.addRoomType(doubleRoom.getRoomType(), 5);
        inventory.addRoomType(suite.getRoomType(), 2);

        // Display inventory
        inventory.displayInventory();

        // Example lookup
        System.out.println("\nChecking Double Room availability...");
        System.out.println("Available Double Rooms: "
                + inventory.getAvailability("Double Room"));

        // Example update
        System.out.println("\nUpdating Double Room availability to 4...");
        inventory.updateAvailability("Double Room", 4);

        inventory.displayInventory();
    }
}