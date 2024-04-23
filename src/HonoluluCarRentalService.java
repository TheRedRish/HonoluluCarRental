import enums.CarBrand;
import enums.FuelType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class HonoluluCarRentalService {
    private final ICarRepository carRepository;
    private final IRentalContractRepository rentalContractRepository;
    private final IRenterRepository renterRepository;

    public HonoluluCarRentalService(ICarRepository carRepository, IRentalContractRepository rentalContractRepository, IRenterRepository renterRepository) {
        this.carRepository = carRepository;
        this.rentalContractRepository = rentalContractRepository;
        this.renterRepository = renterRepository;
    }

    //<editor-fold desc="renter">
    public void createRenter(Renter renter) {
        renterRepository.addRenter(renter);
    }

    public List<Renter> getAllRenters() {
        return renterRepository.getAllRenters();
    }

    public Renter getRenterByName(String name) {
        return renterRepository.getRenterByName(name);
    }

    public Renter getRenterByPhoneNumber(String phoneNumber) {
        return renterRepository.getRenterByPhoneNumber(phoneNumber);
    }

    public void updateRenter(Renter updatedRenter) {
        renterRepository.updateRenter(updatedRenter);
    }

    public void deleteRenterByName(String name) {
        renterRepository.deleteRenterByName(name);
    }

    public void deleteRenterByPhoneNumber(String phoneNumber) {
        renterRepository.deleteRenterByPhoneNumber(phoneNumber);
    }
    //</editor-fold>

    //<editor-fold desc="car">
    public void addCar(Car car) {
        carRepository.addCar(car);
    }

    public List<Car> getAllCars() {
        return carRepository.getAllCars();
    }

    public Car getCarByRegistrationNumber(String registrationNumber) {
        return carRepository.getCarByRegistrationNumber(registrationNumber);
    }

    public List<Car> getCarsByBrand(CarBrand carBrand) {
        return carRepository.getCarsByBrand(carBrand);
    }

    public List<Car> getCarsByFuelType(FuelType fuelType) {
        return carRepository.getCarsByFuelType(fuelType);
    }

    public List<Car> getCarsByModel(String model) {
        return carRepository.getCarsByModel(model);
    }

    public List<Car> getCarsByRegistrationDateRange(LocalDate startDate, LocalDate endDate) {
        return carRepository.getCarsByRegistrationDateRange(startDate, endDate);
    }

    public void updateCar(Car updatedCar) {
        carRepository.updateCar(updatedCar);
    }

    public void deleteCarByRegistrationNumber(String registrationNumber) {
        carRepository.deleteCarByRegistrationNumber(registrationNumber);
    }
    //</editor-fold>

    //<editor-fold desc="rental contract">
    public void addRentalContract(RentalContract rentalContract) {
        rentalContractRepository.addRentalContract(rentalContract);
    }

    public List<RentalContract> getAllRentalContracts() {
        return rentalContractRepository.getAllRentalContracts();
    }

    public RentalContract getRentalContractByRenter(Renter renter) {
        return rentalContractRepository.getRentalContractByRenter(renter);
    }

    public List<RentalContract> getRentalContractByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return rentalContractRepository.getRentalContractByDateRange(startDate, endDate);
    }

    public void updateRentalContract(RentalContract updatedRentalContract) {
        rentalContractRepository.updateRentalContract(updatedRentalContract);
    }

    public void deleteRentalContractByRenter(Renter renter) {
        rentalContractRepository.deleteRentalContractByRenter(renter);
    }

    public void deleteRentalContractByCar(Car car) {
        rentalContractRepository.deleteRentalContractByCar(car);
    }
    //</editor-fold>
}
