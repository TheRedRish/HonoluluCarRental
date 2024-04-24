import java.util.LinkedHashMap;
import java.util.Map;

public class ConsoleMenu {
    private final Map<String, Runnable> menuItems;

    public ConsoleMenu() {
        this.menuItems = new LinkedHashMap<>();
    }

    /**
     * Add an item to the console menu.
     * @param label The label for the menu item.
     * @param action The action associated with the menu item.
     */
    public void addItem(String label, Runnable action) {
        menuItems.put(label, action);
    }

    /**
     * Remove an item from the console menu.
     * @param label The label of the menu item to be removed.
     */
    public void removeItem(String label) {
        menuItems.remove(label);
    }

    /**
     * Display the menu on the console.
     */
    public void displayMenu() {
        System.out.println("Menu:");
        int index = 1;
        for (String label : menuItems.keySet()) {
            System.out.println(index + ". " + label);
            index++;
        }
        System.out.println();
    }

    /**
     * Run the console menu.
     */
    public void run() {
        while (true) {
            displayMenu();
            int choice = Utils.getIntInput("Enter the number of your choice (0 to exit): ", "Please enter a number from the list", 0, menuItems.size());
            if (choice == 0) {
                System.out.println("Exiting...");
                break;
            }
            int index = 1;
            for (String label : menuItems.keySet()) {
                if (index == choice) {
                    menuItems.get(label).run();
                    break;
                }
                index++;
            }
        }
    }
}