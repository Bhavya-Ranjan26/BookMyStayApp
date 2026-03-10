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
 * Room Search Service - read-only access to inventory
 * @version 4.0
 */
class RoomSearchService {

    private RoomInventory inventory;
    private Room[] rooms;

    public RoomSearchService(RoomInventory inventory, Room[] rooms) {
        this.inventory = inventory;
        this.rooms = rooms;
    }

    // Search and display only available rooms
    public void displayAvailableRooms() {
        System.out.println("\nAvailable Rooms for Guests:\n");
        boolean anyAvailable = false;
        for (Room room : rooms) {
            int count = inventory.getAvailability(room.getRoomType());
            if (count > 0) {
                room.displayRoomDetails();
                System.out.println("Available: " + count + " rooms\n");
                anyAvailable = true;
            }
        }
        if (!anyAvailable) {
            System.out.println("No rooms available at the moment.");
        }
    }
}

/**
 * Book My Stay Application
 * Combines Use Cases 2, 3, and 4
 *
 * @version 4.1
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("       Book My Stay App");
        System.out.println("   Hotel Booking System v4.1");
        System.out.println("=================================");

        // Initialize room objects
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();
        Room[] allRooms = {single, doubleRoom, suite};

        // Display room details (Use Case 2)
        System.out.println("\nRoom Details:");
        for (Room room : allRooms) {
            room.displayRoomDetails();
            System.out.println();
        }

        // Initialize centralized inventory (Use Case 3)
        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType(single.getRoomType(), 10);
        inventory.addRoomType(doubleRoom.getRoomType(), 5);
        inventory.addRoomType(suite.getRoomType(), 0); // Suite unavailable

        // Display inventory
        inventory.displayInventory();

        // Initialize Search Service (Use Case 4)
        RoomSearchService searchService = new RoomSearchService(inventory, allRooms);

        // Guest searches for available rooms (read-only)
        searchService.displayAvailableRooms();

        // Verify inventory not modified
        System.out.println("\nInventory after search (should be unchanged):");
        inventory.displayInventory();
    }
}