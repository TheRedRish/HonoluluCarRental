package repository;

import entity.Car;
import entity.RentalContract;
import entity.Renter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class FileRentalContractRepository implements IRentalContractRepository {
    private static final String FILE_PATH = "src/repository.repositoryFiles/rentalContractRepository.txt";
    private static final String argSeparator = "//##//";
    private final IRenterRepository renterRepository;
    private final ICarRepository carRepository;

    public FileRentalContractRepository(IRenterRepository renterRepository, ICarRepository carRepository) {
        this.renterRepository = renterRepository;
        this.carRepository = carRepository;
    }

    @Override
    public void addRentalContract(RentalContract rentalContract) {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            // Have to save subclass type in file
            writer.write(rentalContract.getConsoleSaveString(argSeparator) + "\n");
            writer.close(); // It is not redundant to close the writer, it wont write if its not closed.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<RentalContract> getAllRentalContracts() {
        List<RentalContract> rentalContractList = new ArrayList<>();
        try (Scanner reader = new Scanner(new File(FILE_PATH))) {
            String line;
            while (reader.hasNextLine()) {
                line = reader.nextLine();
                String[] parts = line.split(argSeparator);

                // Order of parts seen in Renter class and subclasses in getConsoleSaveString method
                UUID id = UUID.fromString(parts[0]);
                UUID renterId = UUID.fromString(parts[1]);
                UUID carId = UUID.fromString(parts[2]);
                LocalDate fromDate = LocalDate.parse(parts[3]);
                LocalDate toDate = LocalDate.parse(parts[4]);
                int zipCode = Integer.parseInt(parts[5]);

                Renter renter = renterRepository.getRenterById(renterId);
                Car car = carRepository.getCarById(carId);

                rentalContractList.add(new RentalContract(id, renter, car, fromDate, toDate, zipCode));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rentalContractList;
    }

    @Override
    public List<RentalContract> getRentalContractsByRenter(Renter renter) {
        List<RentalContract> allRentalContracts = getAllRentalContracts();
        List<RentalContract> rentalContractList = new ArrayList<>();
        for (RentalContract rentalContract : allRentalContracts) {
            if (rentalContract.getRenter().getId().equals(renter.getId())) {
                rentalContractList.add(rentalContract);
            }
        }
        return rentalContractList;
    }

    @Override
    public RentalContract getRentalContractById(UUID id) {
        List<RentalContract> allRentalContracts = getAllRentalContracts();
        for (RentalContract rentalContract : allRentalContracts) {
            if (rentalContract.getId().equals(id)) {
                return rentalContract;
            }
        }
        return null;
    }

    @Override
    public List<RentalContract> getRentalContractByDateRange(LocalDate fromDate, LocalDate endDate) {
        List<RentalContract> allRentalContracts = getAllRentalContracts();
        List<RentalContract> rentalContractMatches = new ArrayList<>();
        for (RentalContract rentalContract : allRentalContracts) {
            // Check if toDate or fromDate is in the range. So the rental is ongoing in the range
            if ((rentalContract.getFromDate().isAfter(fromDate) && rentalContract.getFromDate().isBefore(endDate)) || (rentalContract.getToDate().isAfter(fromDate) && rentalContract.getToDate().isBefore(endDate))) {
                rentalContractMatches.add(rentalContract);
            }
        }
        return rentalContractMatches;
    }

    @Override
    public void updateRentalContract(RentalContract updatedRentalContract) {
        List<RentalContract> allRentalContracts = getAllRentalContracts();
        for (RentalContract rentalContract : allRentalContracts) {
            if (rentalContract.getId().equals(updatedRentalContract.getId())) {
                rentalContract.updateValues(updatedRentalContract);
                break;
            }
        }
        saveAllRentalContracts(allRentalContracts);
    }

    @Override
    public void deleteRentalContractByRenter(Renter renter) {
        List<RentalContract> allRentalContracts = getAllRentalContracts();
        if (allRentalContracts.removeIf(rentalContract -> rentalContract.getRenter().getId().equals(renter.getId()))) {
            saveAllRentalContracts(allRentalContracts);
        }
    }

    @Override
    public void deleteRentalContractById(UUID id) {
        List<RentalContract> allRentalContracts = getAllRentalContracts();
        if (allRentalContracts.removeIf(rentalContract -> rentalContract.getId().equals(id))) {
            saveAllRentalContracts(allRentalContracts);
        }
    }

    private void saveAllRentalContracts(List<RentalContract> rentalContractList) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (RentalContract rentalContract : rentalContractList) {
                writer.write(rentalContract.getConsoleSaveString(argSeparator) + "\n");
            }
            writer.close(); // Have to close writer to actually write
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
