import enums.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DBCarRepository implements ICarRepository {
    private final Connection connection;

    public DBCarRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addCar(Car car) {
        try {
            // Prepare call statement
            CallableStatement callableStatement = connection.prepareCall("{call AddCar(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");

            // Set input parameters if required
            setValuesOfCarOnStatement(car, callableStatement);

            // Execute stored procedure
            callableStatement.execute();

            // Handle any result sets or output parameters

            // Close resources
            callableStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        try (CallableStatement statement = connection.prepareCall("{CALL getAllCars()}")) {
            ResultSet resultSet = statement.executeQuery();
            // No rows because no result.
            if (!resultSet.isBeforeFirst()) {
                return cars;
            }
            while (resultSet.next()) {
                cars.add(mapResultSetToCar(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cars;
    }

    @Override
    public Car getCarById(UUID id) {
        Car car = null;
        try (CallableStatement statement = connection.prepareCall("{CALL getCarById(?)}")) {
            statement.setString(1, id.toString());
            ResultSet resultSet = statement.executeQuery();
            // No rows because no result.
            if (!resultSet.isBeforeFirst()) {
                return car;
            }
            if (resultSet.next()) {
                car = mapResultSetToCar(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return car;
    }

    @Override
    public Car getCarByRegistrationNumber(String registrationNumber) {
        Car car = null;
        try (CallableStatement statement = connection.prepareCall("{CALL getCarByRegistrationNumber(?)}")) {
            statement.setString(1, registrationNumber);
            ResultSet resultSet = statement.executeQuery();
            // No rows because no result.
            if (!resultSet.isBeforeFirst()) {
                return car;
            }
            if (resultSet.next()) {
                car = mapResultSetToCar(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return car;
    }

    @Override
    public List<Car> getCarsByBrand(CarBrand carBrand) {
        List<Car> cars = new ArrayList<>();
        try (CallableStatement statement = connection.prepareCall("{CALL getCarsByBrand(?)}")) {
            statement.setString(1, carBrand.toString());
            ResultSet resultSet = statement.executeQuery();
            // No rows because no result.
            if (!resultSet.isBeforeFirst()) {
                return cars;
            }
            while (resultSet.next()) {
                cars.add(mapResultSetToCar(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cars;
    }

    @Override
    public List<Car> getCarsByFuelType(FuelType fuelType) {
        List<Car> cars = new ArrayList<>();
        try (CallableStatement statement = connection.prepareCall("{CALL getCarsByFuelType(?)}")) {
            statement.setString(1, fuelType.toString());
            ResultSet resultSet = statement.executeQuery();
            // No rows because no result.
            if (!resultSet.isBeforeFirst()) {
                return cars;
            }
            while (resultSet.next()) {
                cars.add(mapResultSetToCar(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cars;
    }

    @Override
    public List<Car> getCarsByModel(String model) {
        List<Car> cars = new ArrayList<>();
        try (CallableStatement statement = connection.prepareCall("{CALL getCarsByModel(?)}")) {
            statement.setString(1, model);
            ResultSet resultSet = statement.executeQuery();
            // No rows because no result.
            if (!resultSet.isBeforeFirst()) {
                return cars;
            }
            while (resultSet.next()) {
                cars.add(mapResultSetToCar(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cars;
    }

    @Override
    public List<Car> getCarsByRegistrationDateRange(LocalDate startDate, LocalDate endDate) {
        List<Car> cars = new ArrayList<>();
        try (CallableStatement statement = connection.prepareCall("{CALL getCarsByRegistrationDateRange(?, ?)}")) {
            statement.setDate(1, Date.valueOf(startDate));
            statement.setDate(2, Date.valueOf(endDate));
            ResultSet resultSet = statement.executeQuery();
            // No rows because no result.
            if (!resultSet.isBeforeFirst()) {
                return cars;
            }
            while (resultSet.next()) {
                cars.add(mapResultSetToCar(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cars;
    }

    @Override
    public void updateCar(Car updatedCar) {
        try (CallableStatement statement = connection.prepareCall("{CALL updateCar(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}")) {
            setValuesOfCarOnStatement(updatedCar, statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCarByRegistrationNumber(String registrationNumber) {
        try (CallableStatement statement = connection.prepareCall("{CALL deleteCarByRegistrationNumber(?)}")) {
            statement.setString(1, registrationNumber);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCarById(UUID id) {
        try (CallableStatement statement = connection.prepareCall("{CALL deleteCarById(?)}")) {
            statement.setString(1, id.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Helper method to map ResultSet to Car object
    private Car mapResultSetToCar(ResultSet resultSet) throws SQLException {
        // Retrieve ID as bytes
        byte[] idBytes = resultSet.getBytes("id");
        // Convert bytes to UUID
        UUID id = Utils.convertBytesToUUID(idBytes);

        CarBrand brand = CarBrand.valueOf(resultSet.getString("carBrand"));
        String model = resultSet.getString("model");
        FuelType fuelType = FuelType.valueOf(resultSet.getString("fuelType"));
        LocalDate firstRegistrationDate = resultSet.getDate("firstRegistrationDate").toLocalDate();
        int odometer = resultSet.getInt("odometer");
        int motorSize = resultSet.getInt("motorSize");
        GearType gearType = GearType.valueOf(resultSet.getString("gearType"));
        boolean hasAircondition = resultSet.getBoolean("hasAircondition");
        boolean hasCruiseControl = resultSet.getBoolean("hasCruiseControl");
        SeatType seatType = SeatType.valueOf(resultSet.getString("seatType"));
        int seatAmount = resultSet.getInt("seatAmount");
        int horsePower = resultSet.getInt("horsePower");
        String registrationNumber = resultSet.getString("registrationNumber");

        return new Car(id, brand, model, fuelType, firstRegistrationDate, odometer, motorSize, gearType, hasAircondition, hasCruiseControl, seatType, seatAmount, horsePower, registrationNumber);
    }

    private void setValuesOfCarOnStatement(Car car, CallableStatement callableStatement) throws SQLException {
        callableStatement.setString(1, car.getId().toString());
        callableStatement.setString(2, car.getCarBrand().toString());
        callableStatement.setString(3, car.getModel());
        callableStatement.setString(4, car.getFuelType().toString());
        callableStatement.setDate(5, Date.valueOf(car.getFirstRegistrationDate()));
        callableStatement.setInt(6, car.getOdometer());
        callableStatement.setInt(7, car.getMotorSize());
        callableStatement.setString(8, car.getGearType().toString());
        callableStatement.setBoolean(9, car.hasAircondition());
        callableStatement.setBoolean(10, car.hasCruiseControl());
        callableStatement.setString(11, car.getSeatType().toString());
        callableStatement.setInt(12, car.getSeatAmount());
        callableStatement.setInt(13, car.getHorsePower());
        callableStatement.setString(14, car.getRegistrationNumber());
    }
}
