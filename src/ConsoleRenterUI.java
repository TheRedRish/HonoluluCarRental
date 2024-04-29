import utils.ConsoleMenu;
import utils.Utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConsoleRenterUI implements IRenterUI {
    private final RenterService renterService;
    private static final String spacerString = "--------------------------------------";

    public ConsoleRenterUI(RenterService renterService) {
        this.renterService = renterService;
    }
    //<editor-fold desc="renter">
    public void displayRenterMenu() {
        ConsoleMenu renterMenu = new ConsoleMenu();
        renterMenu.addItem("Add renter", this::displayAddRenter);
        renterMenu.addItem("Search renters", this::displaySearchRenters);
        renterMenu.addItem("Update renter", this::displayUpdateRenter);
        renterMenu.addItem("Delete renter", this::displayDeleteRenter);

        renterMenu.run();
    }

    @Override
    public void displayAddRenter() {
        System.out.println("<------Add Renter------>");
        Renter renter = createAndAddRenter();

        if (renter == null) {
            System.out.println("Something went wrong try again.");
            return;
        }

        System.out.println(spacerString);
        System.out.println("New renter has been added: ");
        System.out.println(renter);
        System.out.println();
    }

    public Renter createAndAddRenter() {
        // Find out which renter to create
        System.out.println("Select renter type");
        System.out.println("1. Private renter");
        System.out.println("2. Commercial renter");
        int userSelection = Utils.getIntInput("Enter the number of your choice: ", "Invalid selection", 1, 2);
        Renter renter = null;
        String name = Utils.getStringInput("Name: ");
        String address = Utils.getStringInput("Address: ");
        String zipCode = Utils.getStringInput("ZipCode: ");
        String city = Utils.getStringInput("City: ");
        String phoneNumber = Utils.getStringInput("Phone number: ");
        String phone = Utils.getStringInput("Phone: ");
        String email = Utils.getStringInput("Email: ");

        if (userSelection == 1) {
            String driversLicenceNumber = Utils.getStringInput("Drivers licence number: ");
            System.out.println("Date of acquired licence");
            LocalDate dateOfAcquiredLicense = Utils.getLocalDateInput();
            renter = new PrivateRenter(name, address, zipCode, city, phoneNumber, phone, email, driversLicenceNumber, dateOfAcquiredLicense);
        } else if (userSelection == 2) {
            String companyName = Utils.getStringInput("Company name: ");
            String companyAddress = Utils.getStringInput("Company address: ");
            String companyPhoneNumber = Utils.getStringInput("Company phone number: ");
            int companyRegistrationNumber = Utils.getIntInput("Company registration number: ");
            renter = new CompanyRenter(name, address, zipCode, city, phoneNumber, phone, email, companyName, companyAddress, companyPhoneNumber, companyRegistrationNumber);
        }
        renterService.addRenter(renter);
        return renter;
    }

    @Override
    public void displaySearchRenters() {
        System.out.println("<------Search Renter------>");
        List<Renter> renterMatches = getRenterListFromSearch();

        System.out.println("Here is the result of your search: ");
        for (Renter renter : renterMatches) {
            System.out.println(spacerString);
            System.out.println(renter);
        }
        System.out.println();
    }

    private List<Renter> getRenterListFromSearch() {
        System.out.println("What do you want to search by?");
        System.out.println("1. Name");
        System.out.println("2. Phone number");

        List<Renter> renterMatches = new ArrayList<>();

        int userSelection = Utils.getIntInput("Enter the number of your choice: ", "Invalid selection", 1, 2);
        switch (userSelection) {
            case 1:
                String name = Utils.getStringInput("Enter name: ");
                List<Renter> renterByNameList = renterService.getRentersByName(name);
                if (renterByNameList != null) {
                    renterMatches.addAll(renterByNameList);
                }
                break;
            case 2:
                String phoneNumber = Utils.getStringInput("Enter phoneNumber: ");
                List<Renter> renterByPhoneNumberList = renterService.getRentersByPhoneNumber(phoneNumber);
                if (renterByPhoneNumberList != null) {
                    renterMatches.addAll(renterByPhoneNumberList);
                }
                break;
            default:
                break;
        }
        return renterMatches;
    }

    public Renter getRenterFromSearch() {
        List<Renter> renterList = getRenterListFromSearch();
        return Utils.selectObject(renterList);
    }

    @Override
    public void displayUpdateRenter() {
        System.out.println("<------Update Renter------>");
        System.out.println("Select renter to update");
        Renter renterToUpdate = getRenterFromSearch();
        if (renterToUpdate == null) {
            System.out.println("Renter not found in system");
            return;
        }
        boolean updatingRenter;
        do {
            System.out.println("Select what to update on the car:");
            System.out.println("1. Name");
            System.out.println("2. Address");
            System.out.println("3. Zipcode");
            System.out.println("4. City");
            System.out.println("5. Phone number");
            System.out.println("6. Phone");
            System.out.println("7. Email");
            int userSelection = 0;
            if (renterToUpdate instanceof PrivateRenter) {
                System.out.println("8. Drivers licence number");
                System.out.println("9. Date of acquired licence");
                userSelection = Utils.getIntInput("Enter the number of your choice (0 to exit): ", "Invalid selection", 0, 9);
            } else if (renterToUpdate instanceof CompanyRenter) {
                System.out.println("8. Company name");
                System.out.println("9. Company address");
                System.out.println("10. Company phone number");
                System.out.println("11. Company registration number");
                userSelection = Utils.getIntInput("Enter the number of your choice (0 to exit): ", "Invalid selection", 0, 11);
            }

            switch (userSelection) {
                case 0:
                    return;
                case 1:
                    String name = Utils.getStringInput("Name: ");
                    renterToUpdate.setName(name);
                    break;
                case 2:
                    String address = Utils.getStringInput("Address: ");
                    renterToUpdate.setAddress(address);
                    break;
                case 3:
                    String zipCode = Utils.getStringInput("ZipCode: ");
                    renterToUpdate.setZipCode(zipCode);
                    break;
                case 4:
                    String city = Utils.getStringInput("City: ");
                    renterToUpdate.setCity(city);
                    break;
                case 5:
                    String phoneNumber = Utils.getStringInput("Phone number: ");
                    renterToUpdate.setPhoneNumber(phoneNumber);
                    break;
                case 6:
                    String phone = Utils.getStringInput("Phone: ");
                    renterToUpdate.setPhone(phone);
                    break;
                case 7:
                    String email = Utils.getStringInput("Email: ");
                    renterToUpdate.setEmail(email);
                    break;
                case 8:
                    if (renterToUpdate instanceof PrivateRenter) {
                        String driversLicenceNumber = Utils.getStringInput("Drivers licence number: ");

                        ((PrivateRenter) renterToUpdate).setDriversLicenceNumber(driversLicenceNumber);
                    } else if (renterToUpdate instanceof CompanyRenter) {
                        String companyName = Utils.getStringInput("Company name: ");
                        ((CompanyRenter) renterToUpdate).setCompanyName(companyName);
                    }
                    break;
                case 9:
                    if (renterToUpdate instanceof PrivateRenter) {
                        System.out.println("Date of acquired licence");
                        LocalDate dateOfAcquiredLicense = Utils.getLocalDateInput();
                        ((PrivateRenter) renterToUpdate).setDateOfAcquiredLicense(dateOfAcquiredLicense);
                    } else if (renterToUpdate instanceof CompanyRenter) {
                        String companyAddress = Utils.getStringInput("Company address: ");
                        ((CompanyRenter) renterToUpdate).setCompanyAddress(companyAddress);
                    }
                    break;
                case 10:
                    if (renterToUpdate instanceof CompanyRenter) {
                        String companyPhoneNumber = Utils.getStringInput("Company phone number: ");
                        ((CompanyRenter) renterToUpdate).setCompanyPhoneNumber(companyPhoneNumber);
                    }
                    break;
                case 11:
                    if (renterToUpdate instanceof CompanyRenter) {
                        int companyRegistrationNumber = Utils.getIntInput("Company registration number: ");
                        ((CompanyRenter) renterToUpdate).setCompanyRegistrationNumber(companyRegistrationNumber);
                    }
                    break;
                default:
                    break;
            }
            updatingRenter = Utils.getStringInput("Update more on the system? y/n ", "Please answer y/n", new String[]{"y", "n"}).equalsIgnoreCase("y");
        } while (updatingRenter);

        renterService.updateRenter(renterToUpdate);
        System.out.println("The renter has been updated.");
        System.out.println();
    }

    @Override
    public void displayDeleteRenter() {
        System.out.println("<------Delete Renter------>");
        System.out.println("Select renter to delete");
        Renter renterToDelete = getRenterFromSearch();
        if (renterToDelete == null) {
            System.out.println("Renter not found in system");
            return;
        }
        renterService.deleteRenterById(renterToDelete.getId());
        System.out.println("The renter has been deleted");
        System.out.println();
    }
    //</editor-fold>

}
