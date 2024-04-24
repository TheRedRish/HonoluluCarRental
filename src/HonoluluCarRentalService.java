import enums.CarBrand;
import enums.FuelType;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class HonoluluCarRentalService {
    private final ICarRepository carRepository;
    private final IRenterRepository renterRepository;
    private final IRentalContractRepository rentalContractRepository;

    public HonoluluCarRentalService(ICarRepository carRepository, IRenterRepository renterRepository,IRentalContractRepository rentalContractRepository) {
        this.carRepository = carRepository;
        this.renterRepository = renterRepository;
        this.rentalContractRepository = rentalContractRepository;
    }

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

    public void deleteCarById(UUID id){
        carRepository.deleteCarById(id);
    }
    //</editor-fold>

    //<editor-fold desc="renter">
    public void addRenter(Renter renter) {
        renterRepository.addRenter(renter);
    }

    public List<Renter> getAllRenters() {
        return renterRepository.getAllRenters();
    }

    public List<Renter> getRenterByName(String name) {
        return renterRepository.getRenterByName(name);
    }

    public List<Renter> getRenterByPhoneNumber(String phoneNumber) {
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

    public void deleteRenterById(UUID id){
        renterRepository.deleteRenterById(id);
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

    public List<RentalContract> getRentalContractByDateRange(LocalDate startDate, LocalDate endDate) {
        return rentalContractRepository.getRentalContractByDateRange(startDate, endDate);
    }

    public void updateRentalContract(RentalContract updatedRentalContract) {
        rentalContractRepository.updateRentalContract(updatedRentalContract);
    }

    public void deleteRentalContractByRenter(Renter renter) {
        rentalContractRepository.deleteRentalContractByRenter(renter);
    }

    public void deleteRentalContractById(UUID id){
        rentalContractRepository.deleteRentalContractById(id);
    }
    //</editor-fold>
}
