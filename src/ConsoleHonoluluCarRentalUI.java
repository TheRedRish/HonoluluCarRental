import enums.CarBrand;
import enums.FuelType;
import enums.GearType;
import enums.SeatType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHonoluluCarRentalUI implements IHonoluluCarRentalUI {
    private final HonoluluCarRentalService honoluluCarRentalService;
    private static final String spacerString = "--------------------------------------";

    public ConsoleHonoluluCarRentalUI(HonoluluCarRentalService honoluluCarRentalService) {
        this.honoluluCarRentalService = honoluluCarRentalService;
    }

    @Override
    public void run() {
        displayMainMenu();
    }

    @Override
    public void displayMainMenu() {
        ConsoleMenu mainMenu = new ConsoleMenu();
        mainMenu.addItem("Car menu", this::displayCarMenu);
        mainMenu.addItem("Renter menu", this::displayRenterMenu);
        mainMenu.addItem("Rental contract menu", this::displayRentalContractMenu);

        mainMenu.run();
    }

    //<editor-fold desc="car">
    public void displayCarMenu() {
        ConsoleMenu carMenu = new ConsoleMenu();
        System.out.println("hello");
        carMenu.addItem("Add car", this::displayAddCar);
        carMenu.addItem("Search cars", this::displaySearchCars);
        carMenu.addItem("Update car", this::displayUpdateCar);
        carMenu.addItem("Delete car", this::displayDeleteCar);

        carMenu.run();
    }

    @Override
    public void displayAddCar() {
        System.out.println("<------Add car------>");
        System.out.println("Select car brand");
        CarBrand carBrand = Utils.selectObject(CarBrand.values());
        System.out.println("Enter model");
        String model = Utils.getStringInput("Car model: ");
        System.out.println("Select fuel type");
        FuelType fuelType = Utils.selectObject(FuelType.values());
        System.out.println("Select date of first registration: ");
        LocalDate firstRegistrationDate = Utils.getLocalDateInput();
        int odometer = Utils.getIntInput("Enter odometer reading: ");
        int moterSize = Utils.getIntInput("Enter motor size in ccm: ");
        System.out.println("Select gear type");
        GearType gearType = Utils.selectObject(GearType.values());
        boolean hasAircondition = Utils.getStringInput("Does the car have aircondition? y/n ", "Please answer y/n", new String[]{"y", "n"}).equalsIgnoreCase("y");
        boolean hasCruiseControl = Utils.getStringInput("Does the car have cruise control? y/n ", "Please answer y/n", new String[]{"y", "n"}).equalsIgnoreCase("y");
        System.out.println("Select seat type");
        SeatType seatType = Utils.selectObject(SeatType.values());
        int seatAmount = Utils.getIntInput("Enter seat amount: ");
        int horsePower = Utils.getIntInput("Enter horse power: ");
        String registrationNumber = Utils.getStringInput("Enter registration number: ");

        Car newCar = new Car(carBrand, model, fuelType, firstRegistrationDate, odometer, moterSize, gearType, hasAircondition, hasCruiseControl, seatType, seatAmount, horsePower, registrationNumber);
        honoluluCarRentalService.addCar(newCar);
        System.out.println(spacerString);
        System.out.println("New car has been added: ");
        System.out.println(newCar);
        System.out.println();
    }

    @Override
    public void displaySearchCars() {
        System.out.println("<------Search car------>");
        List<Car> carMatches = getCarListFromSearch();
        System.out.println("Here is the result of your search: ");
        for (Car car : carMatches) {
            System.out.println(spacerString);
            System.out.println(car);
        }
        System.out.println();
    }

    private List<Car> getCarListFromSearch() {
        System.out.println("What do you want to search by?");
        System.out.println("1. Registration Number");
        System.out.println("2. Car brand");
        System.out.println("3. Fuel type");
        System.out.println("4. Car model");
        System.out.println("5. Date range");

        List<Car> carMatches = new ArrayList<>();

        int userSelection = Utils.getIntInput("Enter the number of your choice (0 to exit): ", "Invalid selection", 1, 5);
        switch (userSelection) {
            case 1:
                String registrationNumber = Utils.getStringInput("Enter registration number: ");
                Car car = honoluluCarRentalService.getCarByRegistrationNumber(registrationNumber);
                if (car != null) {
                    carMatches.add(car);
                }
                break;
            case 2:
                System.out.println("Select car brand");
                CarBrand carBrand = Utils.selectObject(CarBrand.values());
                List<Car> carsByBrand = honoluluCarRentalService.getCarsByBrand(carBrand);
                if (!carsByBrand.isEmpty()) {
                    carMatches.addAll(carsByBrand);
                }
                break;
            case 3:
                System.out.println("Select fuel type");
                FuelType fuelType = Utils.selectObject(FuelType.values());
                List<Car> carsByFuelType = honoluluCarRentalService.getCarsByFuelType(fuelType);
                if (!carsByFuelType.isEmpty()) {
                    carMatches.addAll(carsByFuelType);
                }
                break;
            case 4:
                String model = Utils.getStringInput("Enter car model");
                List<Car> carsByModel = honoluluCarRentalService.getCarsByModel(model);
                if (!carsByModel.isEmpty()) {
                    carMatches.addAll(carsByModel);
                }
                break;
            case 5:
                System.out.println("Start date for search range");
                LocalDate startDate = Utils.getLocalDateInput();
                System.out.println("End date for search range");
                LocalDate endDate = Utils.getLocalDateInput();
                List<Car> carsByRegistrationDateRange = honoluluCarRentalService.getCarsByRegistrationDateRange(startDate, endDate);
                if (!carsByRegistrationDateRange.isEmpty()) {
                    carMatches.addAll(carsByRegistrationDateRange);
                }
                break;
            default:
                break;
        }
        return carMatches;
    }

    private Car getCarFromSearch() {
        List<Car> carList = getCarListFromSearch();
        return Utils.selectObject(carList);
    }

    @Override
    public void displayUpdateCar() {
        System.out.println("<------Update car------>");
        System.out.println("Select car to update");
        Car carToUpdate = getCarFromSearch();
        if (carToUpdate == null) {
            System.out.println("Car not found in system");
            return;
        }
        boolean updatingCar;
        do {
            System.out.println("Select what to update on the car:");
            System.out.println("1. Car brand");
            System.out.println("2. Model");
            System.out.println("3. Fuel type");
            System.out.println("4. First registration date");
            System.out.println("5. Odometer reading");
            System.out.println("6. Moter size");
            System.out.println("7. Gear type");
            System.out.println("8. Aircondition");
            System.out.println("9. Cruise control");
            System.out.println("10. Seat type");
            System.out.println("11. Seat amount");
            System.out.println("12. Horse power");
            System.out.println("13. Registration number");
            int userSelection = Utils.getIntInput("Enter the number of your choice (0 to exit): ", "Invalid selection", 0, 13);
            switch (userSelection) {
                case 0:
                    return;
                case 1:
                    System.out.println("Select car brand");
                    CarBrand carBrand = Utils.selectObject(CarBrand.values());
                    carToUpdate.setCarBrand(carBrand);
                    break;
                case 2:
                    System.out.println("Enter model");
                    String model = Utils.getStringInput("Car model: ");
                    carToUpdate.setModel(model);
                    break;
                case 3:
                    System.out.println("Select fuel type");
                    FuelType fuelType = Utils.selectObject(FuelType.values());
                    carToUpdate.setFuelType(fuelType);
                    break;
                case 4:
                    System.out.println("Select date of first registration: ");
                    LocalDate firstRegistrationDate = Utils.getLocalDateInput();
                    carToUpdate.setFirstRegistrationDate(firstRegistrationDate);
                    break;
                case 5:
                    int odometer = Utils.getIntInput("Enter odometer reading: ");
                    carToUpdate.setOdometer(odometer);
                    break;
                case 6:
                    int moterSize = Utils.getIntInput("Enter motor size in ccm: ");
                    carToUpdate.setMotorSize(moterSize);
                    break;
                case 7:
                    System.out.println("Select gear type");
                    GearType gearType = Utils.selectObject(GearType.values());
                    carToUpdate.setGearType(gearType);
                    break;
                case 8:
                    boolean hasAircondition = Utils.getStringInput("Does the car have aircondition? y/n ", "Please answer y/n", new String[]{"y", "n"}).equalsIgnoreCase("y");
                    carToUpdate.setHasAircondition(hasAircondition);
                    break;
                case 9:
                    boolean hasCruiseControl = Utils.getStringInput("Does the car have cruise control? y/n ", "Please answer y/n", new String[]{"y", "n"}).equalsIgnoreCase("y");
                    carToUpdate.setHasCruiseControl(hasCruiseControl);
                    break;
                case 10:
                    System.out.println("Select seat type");
                    SeatType seatType = Utils.selectObject(SeatType.values());
                    carToUpdate.setSeatType(seatType);
                    break;
                case 11:
                    int seatAmount = Utils.getIntInput("Enter seat amount: ");
                    carToUpdate.setSeatAmount(seatAmount);
                    break;
                case 12:
                    int horsePower = Utils.getIntInput("Enter horse power: ");
                    carToUpdate.setHorsePower(horsePower);
                    break;
                case 13:
                    String registrationNumber = Utils.getStringInput("Enter registration number: ");
                    carToUpdate.setRegistrationNumber(registrationNumber);
                    break;
                default:
                    break;
            }
            updatingCar = Utils.getStringInput("Update more on the system? y/n ", "Please answer y/n", new String[]{"y", "n"}).equalsIgnoreCase("y");
        } while (updatingCar);

        honoluluCarRentalService.updateCar(carToUpdate);
        System.out.println("The car has been updated.");
        System.out.println();
    }

    @Override
    public void displayDeleteCar() {
        System.out.println("<------Delete car------>");
        System.out.println("Select car to delete");
        Car carToUpdate = getCarFromSearch();
        if (carToUpdate == null) {
            System.out.println("Car not found in system");
            return;
        }
        honoluluCarRentalService.deleteCarById(carToUpdate.getId());
        System.out.println("The car has been deleted");
        System.out.println();
    }
    //</editor-fold>

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
        // Find out which renter to create
        System.out.println("Select renter type");
        System.out.println("1. Private renter");
        System.out.println("2. Commercial renter");
        int userSelection = Utils.getIntInput("Enter the number of your choice (0 to exit): ", "Invalid selection", 0, 2);
        if (userSelection == 0) {
            return;
        }
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

        if (renter == null) {
            System.out.println("Something went wrong try again.");
            return;
        }
        honoluluCarRentalService.addRenter(renter);
        System.out.println(spacerString);
        System.out.println("New renter has been added: ");
        System.out.println(renter);
        System.out.println();
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
                List<Renter> renterByNameList = honoluluCarRentalService.getRenterByName(name);
                if (renterByNameList != null) {
                    renterMatches.addAll(renterByNameList);
                }
                break;
            case 2:
                String phoneNumber = Utils.getStringInput("Enter phoneNumber: ");
                List<Renter> renterByPhoneNumberList = honoluluCarRentalService.getRenterByPhoneNumber(phoneNumber);
                if (renterByPhoneNumberList != null) {
                    renterMatches.addAll(renterByPhoneNumberList);
                }
                break;
            default:
                break;
        }
        return renterMatches;
    }

    private Renter getRenterFromSearch() {
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

        honoluluCarRentalService.updateRenter(renterToUpdate);
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
        honoluluCarRentalService.deleteRenterById(renterToDelete.getId());
        System.out.println("The renter has been deleted");
        System.out.println();
    }
    //</editor-fold>

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
        System.out.println("Search for renter to add to contract");
        Renter renter = getRenterFromSearch();

        // Find out which renter to add to contract
        System.out.println("Search for car to add to contract");
        Car car = getCarFromSearch();

        System.out.println("What date does the renting start from:");
        LocalDate fromDate = Utils.getLocalDateInput();
        System.out.println("What date does the renting end:");
        LocalDate toDate = Utils.getLocalDateInput();
        int maxKmAllowed = Utils.getIntInput("How many km are the renter allowed to drive? ");

        RentalContract rentalContract = new RentalContract(renter, fromDate, toDate, maxKmAllowed, car);

        honoluluCarRentalService.addRentalContract(rentalContract);
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
                Renter renter = getRenterFromSearch();
                RentalContract rentalContractByRenter = honoluluCarRentalService.getRentalContractByRenter(renter);
                if (rentalContractByRenter != null) {
                    rentalContractMatches.add(rentalContractByRenter);
                }
                break;
            case 2:
                System.out.println("Enter start date for range");
                LocalDate startDate = Utils.getLocalDateInput();
                System.out.println("Enter end date for range");
                LocalDate endDate = Utils.getLocalDateInput();
                List<RentalContract> rentalContractByDateRange = honoluluCarRentalService.getRentalContractByDateRange(startDate, endDate);
                if (rentalContractByDateRange != null) {
                    rentalContractMatches.addAll(rentalContractByDateRange);
                }
                break;
            default:
                break;
        }
        return rentalContractMatches;
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
                    Renter renter = getRenterFromSearch();
                    rentalContractToUpdate.setRenter(renter);
                    break;
                case 2:
                    System.out.println("Search for car to add to contract");
                    Car car = getCarFromSearch();
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

        honoluluCarRentalService.updateRentalContract(rentalContractToUpdate);
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
        honoluluCarRentalService.deleteRentalContractById(rentalContractFromSearch.getId());
        System.out.println("The rental contract has been deleted");
        System.out.println();
    }
    //</editor-fold>
}
