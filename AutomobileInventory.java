import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;

// Stores the details for one automobile.
class Automobile {

    private String make;
    private String model;
    private String color;
    private int year;
    private int mileage;

    // Default Constructor
    public Automobile() {
        try {
            clearVehicle();
        } catch (Exception e) {
            System.out.println("Error creating vehicle: " + e.getMessage());
        }
    }

    // Parameterized Constructor
    public Automobile(String make, String model, String color, int year, int mileage) {
        this();
        try {
            setVehicleDetails(make, model, color, year, mileage);
        } catch (Exception e) {
            System.out.println("Error creating vehicle: " + e.getMessage());
        }
    }

    // Check and stores the vehicle's information.
    private void setVehicleDetails(String make, String model, String color,
                                   int year, int mileage) {
        try {
            if (make == null || make.trim().isEmpty()
                    || model == null || model.trim().isEmpty()
                    || color == null || color.trim().isEmpty()
                    || year <= 0 || mileage < 0) {
                throw new IllegalArgumentException("Invalid vehicle information.");
            }

            this.make = make.trim();
            this.model = model.trim();
            this.color = color.trim();
            this.year = year;
            this.mileage = mileage;
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to set vehicle details: "
                    + e.getMessage(), e);
        }
    }

    // Clears the attributes for one vehicle.
    private void clearVehicle() {
        try {
            make = "";
            model = "";
            color = "";
            year = 0;
            mileage = 0;
        } catch (Exception e) {
            throw new IllegalStateException("Unable to clear vehicle: " + e.getMessage(), e);
        }
    }

    // Adds details and information to an empty Automobile object.
    public String addVehicle(String autoMake, String autoModel, String autoColor,
                             int autoYear, int autoMileage) {
        try {
            if (!make.isEmpty()) {
                return "Vehicle could not be added. This vehicle record is already in use.";
            }

            setVehicleDetails(autoMake, autoModel, autoColor, autoYear, autoMileage);
            return "Vehicle added successfully.";
        } catch (Exception e) {
            return "Vehicle could not be added: " + e.getMessage();
        }
    }

    // Removes the selected vehicle's details and information.
    public String removeVehicle() {
        try {
            if (make.isEmpty()) {
                return "Vehicle could not be removed. No vehicle information exists.";
            }

            clearVehicle();
            return "Vehicle removed successfully.";
        } catch (Exception e) {
            return "Vehicle could not be removed: " + e.getMessage();
        }
    }

    // Updates an existing vehicle's details and information that is stored.
    public String updateVehicle(String autoMake, String autoModel, String autoColor,
                                int autoYear, int autoMileage) {
        try {
            if (make.isEmpty()) {
                return "Vehicle could not be updated. No vehicle information exists.";
            }

            setVehicleDetails(autoMake, autoModel, autoColor, autoYear, autoMileage);
            return "Vehicle updated successfully.";
        } catch (Exception e) {
            return "Vehicle could not be updated: " + e.getMessage();
        }
    }

    // Returns the vehicle's information as a String array.
    public String[] listVehicle() {
        try {
            if (make.isEmpty()) {
                return new String[] { "No vehicle information available." };
            }

            return new String[] {
                "Make: " + make,
                "Model: " + model,
                "Color: " + color,
                "Year: " + year,
                "Mileage: " + mileage
            };
        } catch (Exception e) {
            return new String[] { "Error listing vehicle: " + e.getMessage() };
        }
    }
}

// The main class that controls the inventory program for the made-up dealership.
public class AutomobileInventory {

    private static final int MAX_VEHICLES = 6;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            ArrayList<Automobile> inventory = new ArrayList<>();

            // Vehicle List: created with the parameterized constructor.
            inventory.add(new Automobile("Toyota", "Corolla", "Silver", 2021, 68000));
            inventory.add(new Automobile("Toyota", "Tacoma", "Red", 2025, 38000));
            inventory.add(new Automobile("Honda", "Pilot", "Black", 2009, 120000));
            inventory.add(new Automobile("Subaru", "Impreza", "Silver", 2020, 72000));
            inventory.add(new Automobile("Ford", "Escape", "Blue", 2022, 35000));

            System.out.println("----- AUTOMOBILE INVENTORY PROGRAM -----");
            System.out.println("Manage up to six vehicles using the menu below.");

            // List the original vehicles by looping through each String array.
            displayInventory(inventory);

            boolean running = true;
            while (running) {
                System.out.println("\n----- MAIN MENU -----");
                System.out.println("1. View all vehicles");
                System.out.println("2. Add a vehicle");
                System.out.println("3. Update a vehicle");
                System.out.println("4. Remove a vehicle");
                System.out.println("5. Print inventory to file");
                System.out.println("6. Exit");

                int choice = readNumber("Enter your selection (1-6): ", 1, 6);

                switch (choice) {
                    case 1:
                        displayInventory(inventory);
                        break;
                    case 2:
                        addToInventory(inventory);
                        break;
                    case 3:
                        updateInInventory(inventory);
                        break;
                    case 4:
                        removeFromInventory(inventory);
                        break;
                    case 5:
                        askToPrintInventory(inventory);
                        break;
                    case 6:
                        System.out.println("Exiting Automobile Inventory Program.");
                        running = false;
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Program error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    // Display the String array returned by listVehicle().
    private static void displayVehicle(Automobile vehicle) {
        try {
            String[] vehicleInformation = vehicle.listVehicle();
            for (String detail : vehicleInformation) {
                System.out.println(detail);
            }
        } catch (Exception e) {
            System.out.println("Error displaying vehicle: " + e.getMessage());
        }
    }

    // Display every vehicle currently held within the list.
    private static void displayInventory(ArrayList<Automobile> inventory) {
        try {
            System.out.println("\n----- CURRENT VEHICLE INVENTORY -----");
            if (inventory.isEmpty()) {
                System.out.println("No vehicles are currently in inventory.");
                return;
            }

            for (int i = 0; i < inventory.size(); i++) {
                System.out.println("\nVehicle " + (i + 1) + ":");
                displayVehicle(inventory.get(i));
            }
            System.out.println("\nTotal vehicles: " + inventory.size());
        } catch (Exception e) {
            System.out.println("Error displaying inventory: " + e.getMessage());
        }
    }

    // Add one vehicle, up to the six vehicle limit.
    private static void addToInventory(ArrayList<Automobile> inventory) {
        try {
            if (inventory.size() >= MAX_VEHICLES) {
                System.out.println("Inventory is full (maximum six vehicles). Remove one before adding another.");
                return;
            }

            System.out.println("\n----- ADD VEHICLE -----");
            String make = readText("Enter make: ");
            String model = readText("Enter model: ");
            String color = readText("Enter color: ");
            int year = readNumber("Enter year: ", 1, Integer.MAX_VALUE);
            int mileage = readNumber("Enter mileage: ", 0, Integer.MAX_VALUE);

            Automobile vehicle = new Automobile();
            String result = vehicle.addVehicle(make, model, color, year, mileage);
            System.out.println(result);

            if (result.equals("Vehicle added successfully.")) {
                inventory.add(vehicle);
                System.out.println("\n----- NEW VEHICLE INFORMATION -----");
                displayVehicle(vehicle);
            }
        } catch (Exception e) {
            System.out.println("Error adding vehicle: " + e.getMessage());
        }
    }

    // Select a vehicle by its current listed position and update its attributes.
    private static void updateInInventory(ArrayList<Automobile> inventory) {
        try {
            if (inventory.isEmpty()) {
                System.out.println("No vehicles available to update.");
                return;
            }

            displayInventory(inventory);
            int position = readNumber("Select vehicle number to update: ", 1, inventory.size());

            System.out.println("\n----- UPDATE VEHICLE " + position + " -----");
            String make = readText("Enter new make: ");
            String model = readText("Enter new model: ");
            String color = readText("Enter new color: ");
            int year = readNumber("Enter new year: ", 1, Integer.MAX_VALUE);
            int mileage = readNumber("Enter new mileage: ", 0, Integer.MAX_VALUE);

            Automobile vehicle = inventory.get(position - 1);
            String result = vehicle.updateVehicle(make, model, color, year, mileage);
            System.out.println(result);

            if (result.equals("Vehicle updated successfully.")) {
                System.out.println("\n----- UPDATED VEHICLE INFORMATION -----");
                displayVehicle(vehicle);
            }
        } catch (Exception e) {
            System.out.println("Error updating vehicle: " + e.getMessage());
        }
    }

    // Remove one selected vehicle from the list.
    private static void removeFromInventory(ArrayList<Automobile> inventory) {
        try {
            if (inventory.isEmpty()) {
                System.out.println("No vehicles available to remove.");
                return;
            }

            displayInventory(inventory);
            int position = readNumber("Select vehicle number to remove: ", 1, inventory.size());
            System.out.print("Remove vehicle " + position + "? (Yes/No): ");
            String confirmation = scanner.nextLine().trim();

            if (confirmation.equalsIgnoreCase("Yes") || confirmation.equalsIgnoreCase("Y")) {
                String result = inventory.get(position - 1).removeVehicle();
                System.out.println(result);

                if (result.equals("Vehicle removed successfully.")) {
                    inventory.remove(position - 1);
                    displayInventory(inventory);
                }
            } else {
                System.out.println("Vehicle was not removed.");
            }
        } catch (Exception e) {
            System.out.println("Error removing vehicle: " + e.getMessage());
        }
    }

    // Ask for Yes or No before saving the inventory to a text file.
    private static void askToPrintInventory(ArrayList<Automobile> inventory) {
        try {
            System.out.print("Would you like to print the inventory to a file? (Yes/No): ");
            String answer = scanner.nextLine().trim();

            if (answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("Yes")) {
                printInventoryToFile(inventory);
            } else if (answer.equalsIgnoreCase("N") || answer.equalsIgnoreCase("No")) {
                System.out.println("Inventory will not be printed to a file.");
            } else {
                System.out.println("Invalid answer. No file was written.");
            }
        } catch (Exception e) {
            System.out.println("Error reading print selection: " + e.getMessage());
        }
    }

    // Save the entire current inventory and not just the last vehicle.
    private static void printInventoryToFile(ArrayList<Automobile> inventory) {
        try {
            File folder = new File("C:\\Temp");
            if (!folder.exists() && !folder.mkdirs()) {
                throw new IOException("Could not create C:\\Temp folder.");
            }

            File file = new File(folder, "Autos.txt");
            try (PrintWriter writer = new PrintWriter(file)) {
                writer.println("----- AUTOMOBILE INVENTORY -----");

                for (int i = 0; i < inventory.size(); i++) {
                    writer.println();
                    writer.println("Vehicle " + (i + 1) + ":");
                    for (String detail : inventory.get(i).listVehicle()) {
                        writer.println(detail);
                    }
                }

                writer.println();
                writer.println("Total vehicles: " + inventory.size());
                writer.println("--------------------------------");

                if (writer.checkError()) {
                    throw new IOException("Could not write the complete inventory.");
                }
            }

            System.out.println("Inventory successfully saved to: " + file.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }

    // Read a nonempty answer without mixing nextInt() with nextLine().
    private static String readText(String prompt) {
        try {
            while (true) {
                System.out.print(prompt);
                String answer = scanner.nextLine().trim();
                if (!answer.isEmpty()) {
                    return answer;
                }
                System.out.println("Please enter a value.");
            }
        } catch (Exception e) {
            throw new IllegalStateException("Could not read vehicle information: "
                    + e.getMessage(), e);
        }
    }

    // Keep asking until an integer within the allowed range is entered by the user.
    private static int readNumber(String prompt, int minimum, int maximum) {
        try {
            while (true) {
                System.out.print(prompt);
                try {
                    int number = Integer.parseInt(scanner.nextLine().trim());
                    if (number >= minimum && number <= maximum) {
                        return number;
                    }
                } catch (NumberFormatException e) {
                    // Input was not a whole number; ask again.
                }
                System.out.println("Please enter a valid number.");
            }
        } catch (Exception e) {
            throw new IllegalStateException("Could not read a valid number: "
                    + e.getMessage(), e);
        }
    }
}
