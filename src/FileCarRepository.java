import java.time.LocalDateTime;
import java.util.List;

public class FileCarRepository implements ICarRepository {
    @Override
    public void addCar(Car car) {

    }

    @Override
    public List<Car> getAllCars() {
        return null;
    }

    @Override
    public Car getCarByRegistrationNumber(String registrationNumber) {
        return null;
    }

    @Override
    public List<Car> getCarsByBrand(CarBrand carBrand) {
        return null;
    }

    @Override
    public List<Car> getCarsByFuelType(FuelType fuelType) {
        return null;
    }

    @Override
    public List<Car> getCarsByModel(String model) {
        return null;
    }

    @Override
    public List<Car> getCarsByRegistrationDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return null;
    }

    @Override
    public void updateCar(Car updatedCar) {

    }

    @Override
    public void deleteCarByRegistrationNumber(String registrationNumber) {

    }
}
