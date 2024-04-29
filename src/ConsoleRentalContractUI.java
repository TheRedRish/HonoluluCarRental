import utils.ConsoleMenu;
import utils.Utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConsoleRentalContractUI implements IRentalContractUI {
    private final RentalContractService rentalContractService;
    private final ConsoleCarUI carUI;
    private final ConsoleRenterUI renterUI;

    private static final String spacerString = "--------------------------------------";

    public ConsoleRentalContractUI(RentalContractService rentalContractService, ConsoleCarUI carUI, ConsoleRenterUI renterUI) {
        this.rentalContractService = rentalContractService;
        this.carUI = carUI;
        this.renterUI = renterUI;
    }
    //<editor-fold desc="rental contract">

    public void displayRentalContractMenu() {
        ConsoleMenu rentalContractMenu = new ConsoleMenu();
        rentalContractMenu.addItem("Add rental contract", this::displayAddRentalContract);
        rentalContractMenu.addItem("Search rental contracts", this::displaySearchRentalContract);
        rentalContractMenu.addItem("Update rental contract", this::displayUpdateRentalContract);
        rentalContractMenu.addItem("Delete rental contract", this::displayDeleteRentalContract);

        rentalContractMenu.run();
    }

    @Override
    public void displayAddRentalContract() {
        System.out.println("<------Add Rental Contract------>");
        // Find out which renter to add to contract
        Renter renter;
        boolean selectingRenter;
        do {
            System.out.println("1. Search for renter");
            System.out.println("2. Create a new renter");
            int userSelection = Utils.getIntInput("Enter the number of your choice: ", "Invalid selection", 1, 2);
            switch (userSelection) {
                case 1:
                    System.out.println("Search for renter to add to contract");
                    renter = renterUI.getRenterFromSearch();
                    break;
                case 2:
                    renter = renterUI.createAndAddRenter();
                    break;
                default:
                    renter = null;
                    System.out.println("Invalid selection");
                    break;
            }
            if (renter == null) {
                System.out.println("Renter not found in system");
                selectingRenter = Utils.getStringInput("Try again? y/n ", "Please answer y/n", new String[]{"y", "n"}).equalsIgnoreCase("y");
            } else {
                selectingRenter = false;
            }
        } while (selectingRenter);

        // Find out which renter to add to contract
        Car car;
        boolean selectingCar;
        do {
            System.out.println("1. Search for car");
            System.out.println("2. Create a new car");
            int userSelection = Utils.getIntInput("Enter the number of your choice: ", "Invalid selection", 1, 2);
            switch (userSelection) {
                case 1:
                    System.out.println("Search for car to add to contract");
                    car = carUI.getCarFromSearch();
                    break;
                case 2:
                    car = carUI.createAndAddCar();
                    break;
                default:
                    car = null;
                    System.out.println("Invalid selection");
                    break;
            }
            if (car == null) {
                System.out.println("Car not found in system");
                selectingCar = Utils.getStringInput("Try again? y/n ", "Please answer y/n", new String[]{"y", "n"}).equalsIgnoreCase("y");
            } else {
                selectingCar = false;
            }
        } while (selectingCar);

        System.out.println("What date does the renting start from:");
        LocalDate fromDate = Utils.getLocalDateInput();
        System.out.println("What date does the renting end:");
        LocalDate toDate = Utils.getLocalDateInput();
        int maxKmAllowed = Utils.getIntInput("How many km are the renter allowed to drive? ");

        RentalContract rentalContract = new RentalContract(renter, car, fromDate, toDate, maxKmAllowed);

        rentalContractService.addRentalContract(rentalContract);
        System.out.println(spacerString);
        System.out.println("New rental contract has been added: ");
        System.out.println(rentalContract);
        System.out.println();
    }

    @Override
    public void displaySearchRentalContract() {
        System.out.println("<------Search Rental Contracts------>");
        List<RentalContract> rentalContractMatches = getRentalContractListFromSearch();

        System.out.println("Here is the result of your search: ");
        for (RentalContract rentalContract : rentalContractMatches) {
            System.out.println(spacerString);
            System.out.println(rentalContract);
        }
        System.out.println();
    }

    @Override
    public void displayUpdateRentalContract() {
        System.out.println("<------Update Rental Contract------>");
        System.out.println("Select rental contract to update");
        RentalContract rentalContractToUpdate = getRentalContractFromSearch();
        if (rentalContractToUpdate == null) {
            System.out.println("Rental contract not found in system");
            return;
        }
        boolean updatingRentalContract;
        do {
            System.out.println("Select what to update on the car:");
            System.out.println("1. Renter");
            System.out.println("2. Car");
            System.out.println("3. From date");
            System.out.println("4. To Date");
            System.out.println("5. Max km allowed");
            int userSelection = Utils.getIntInput("Enter the number of your choice (0 to exit): ", "Invalid selection", 0, 5);

            switch (userSelection) {
                case 0:
                    return;
                case 1:
                    System.out.println("Search for renter to add to contract");
                    Renter renter = renterUI.getRenterFromSearch();
                    rentalContractToUpdate.setRenter(renter);
                    break;
                case 2:
                    System.out.println("Search for car to add to contract");
                    Car car = carUI.getCarFromSearch();
                    // Handle car == null
                    rentalContractToUpdate.setCar(car);
                    break;
                case 3:
                    System.out.println("What date does the renting start from:");
                    LocalDate fromDate = Utils.getLocalDateInput();
                    rentalContractToUpdate.setFromDate(fromDate);
                    break;
                case 4:
                    System.out.println("What date does the renting end:");
                    LocalDate toDate = Utils.getLocalDateInput();
                    rentalContractToUpdate.setToDate(toDate);
                    break;
                case 5:
                    int maxKmAllowed = Utils.getIntInput("How many km are the renter allowed to drive? ");
                    rentalContractToUpdate.setMaxKmAllowed(maxKmAllowed);
                    break;
                default:
                    break;
            }
            updatingRentalContract = Utils.getStringInput("Update more on the system? y/n ", "Please answer y/n", new String[]{"y", "n"}).equalsIgnoreCase("y");
        } while (updatingRentalContract);

        rentalContractService.updateRentalContract(rentalContractToUpdate);
        System.out.println("The rental contract has been updated.");
        System.out.println();
    }

    @Override
    public void displayDeleteRentalContract() {
        System.out.println("<------Delete Rental Contract------>");
        System.out.println("Select rental contract to delete");
        RentalContract rentalContractFromSearch = getRentalContractFromSearch();
        if (rentalContractFromSearch == null) {
            System.out.println("Rental contract not found in system");
            return;
        }
        rentalContractService.deleteRentalContractById(rentalContractFromSearch.getId());
        System.out.println("The rental contract has been deleted");
        System.out.println();
    }

    private RentalContract getRentalContractFromSearch() {
        List<RentalContract> rentalContractListFromSearch = getRentalContractListFromSearch();
        return Utils.selectObject(rentalContractListFromSearch);
    }

    private List<RentalContract> getRentalContractListFromSearch() {
        System.out.println("What do you want to search by?");
        System.out.println("1. Renter");
        System.out.println("2. Date range");

        List<RentalContract> rentalContractMatches = new ArrayList<>();

        int userSelection = Utils.getIntInput("Enter the number of your choice: ", "Invalid selection", 1, 2);
        switch (userSelection) {
            case 1:
                Renter renter = renterUI.getRenterFromSearch();
                // TODO properly handle renter not existing, method still returning empty list from no search.
                if (renter == null) {
                    System.out.println("Renter not found in system");
                    break;
                }
                RentalContract rentalContractByRenter = rentalContractService.getRentalContractByRenter(renter);
                if (rentalContractByRenter != null) {
                    rentalContractMatches.add(rentalContractByRenter);
                }
                break;
            case 2:
                System.out.println("Enter start date for range");
                LocalDate fromDate = Utils.getLocalDateInput();
                System.out.println("Enter end date for range");
                LocalDate endDate = Utils.getLocalDateInput();
                List<RentalContract> rentalContractByDateRange = rentalContractService.getRentalContractByDateRange(fromDate, endDate);
                if (rentalContractByDateRange != null) {
                    rentalContractMatches.addAll(rentalContractByDateRange);
                }
                break;
            default:
                break;
        }
        return rentalContractMatches;
    }
    //</editor-fold>
}
