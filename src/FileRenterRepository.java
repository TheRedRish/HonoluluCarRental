import enums.CarBrand;
import enums.FuelType;
import enums.GearType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class FileRenterRepository implements IRenterRepository {
    private static final String FILE_PATH = "src/repositoryFiles/renterRepository.txt";
    private static final String argSeparator = "//##//";

    @Override
    public void addRenter(Renter renter) {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            // Have to save subclass type in file
            writer.write(renter.getClass().getName() + argSeparator + renter.getConsoleSaveString(argSeparator) + "\n");
            writer.close(); // It is not redundant to close the writer, it wont write if its not closed.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Renter> getAllRenters() {
        List<Renter> renterList = new ArrayList<>();
        try (Scanner reader = new Scanner(new File(FILE_PATH))) {
            String line;
            while (reader.hasNextLine()) {
                line = reader.nextLine();
                String[] parts = line.split(argSeparator);

                // Order of parts seen in Renter class and subclasses in getConsoleSaveString method
                String classType = parts[0];
                UUID id = UUID.fromString(parts[1]);
                String name = parts[2];
                String address = parts[3];
                String zipCode = parts[4];
                String city = parts[5];
                String phoneNumber = parts[6];
                String phone = parts[7];
                String email = parts[8];

                if (PrivateRenter.class.getName().equalsIgnoreCase(classType)) {
                    String driversLicenceNumber = parts[9];
                    LocalDate dateOfAcquiredLicense = LocalDate.parse(parts[10]);
                    renterList.add(new PrivateRenter(id, name, address, zipCode, city, phoneNumber, phone, email, driversLicenceNumber, dateOfAcquiredLicense));
                } else if (CompanyRenter.class.getName().equalsIgnoreCase(classType)) {
                    String companyName = parts[9];
                    String companyAddress = parts[10];
                    String companyPhoneNumber = parts[11];
                    int companyRegistrationNumber = Integer.parseInt(parts[12]);
                    renterList.add(new CompanyRenter(id, name, address, zipCode, city, phoneNumber, phone, email, companyName, companyAddress, companyPhoneNumber, companyRegistrationNumber));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return renterList;
    }

    @Override
    public Renter getRenterByName(String name) {
        List<Renter> allRenters = getAllRenters();
        for (Renter renter : allRenters) {
            if (renter.getName().equalsIgnoreCase(name)) {
                return renter;
            }
        }
        return null;
    }

    @Override
    public Renter getRenterByPhoneNumber(String phoneNumber) {
        List<Renter> allRenters = getAllRenters();
        for (Renter renter : allRenters) {
            if (renter.getPhoneNumber().equalsIgnoreCase(phoneNumber)) {
                return renter;
            }
        }
        return null;
    }

    @Override
    public void updateRenter(Renter updatedRenter) {
        List<Renter> allRenters = getAllRenters();
        for (Renter renter : allRenters) {
            if (renter.getId().equals(updatedRenter.getId())) {
                renter.updateValues(updatedRenter);
                break;
            }
        }
        saveAllRenters(allRenters);
    }

    @Override
    public void deleteRenterByName(String name) {
        List<Renter> allRenters = getAllRenters();
        if (allRenters.removeIf(renter -> renter.getName().equalsIgnoreCase(name))) {
            saveAllRenters(allRenters);
        }
    }

    @Override
    public void deleteRenterByPhoneNumber(String phoneNumber) {
        List<Renter> allRenters = getAllRenters();
        if (allRenters.removeIf(renter -> renter.getPhoneNumber().equalsIgnoreCase(phoneNumber))) {
            saveAllRenters(allRenters);
        }
    }

    private void saveAllRenters(List<Renter> renterList) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (Renter renter : renterList) {
                // Have to save subclass type in file
                writer.write(renter.getClass().getName() + argSeparator + renter.getConsoleSaveString(argSeparator) + "\n");
            }
            writer.close(); // It is not redundant to close the writer, it wont write if its not closed.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
