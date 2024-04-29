import utils.Utils;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DBRentalContractRepository implements IRentalContractRepository {
    private final Connection connection;
    private final IRenterRepository renterRepository;
    private final ICarRepository carRepository;

    public DBRentalContractRepository(Connection connection, IRenterRepository renterRepository, ICarRepository carRepository) {
        this.connection = connection;
        this.renterRepository = renterRepository;
        this.carRepository = carRepository;
    }

    @Override
    public void addRentalContract(RentalContract rentalContract) {
        try {
            CallableStatement callableStatement = connection.prepareCall("{call AddRentalContract(?, ?, ?, ?, ?, ?)}");
            setValuesOfRentalContractOnStatement(rentalContract, callableStatement);
            callableStatement.execute();
            callableStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<RentalContract> getAllRentalContracts() {
        List<RentalContract> rentalContractList = new ArrayList<>();
        try (CallableStatement statement = connection.prepareCall("{CALL getAllRentalContracts()}")) {
            ResultSet resultSet = statement.executeQuery();
            // No rows because no result.
            if (!resultSet.isBeforeFirst()) {
                return rentalContractList;
            }
            while (resultSet.next()) {
                rentalContractList.add(mapResultSetToRentalContract(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rentalContractList;
    }

    @Override
    public RentalContract getRentalContractByRenter(Renter renter) {
        RentalContract rentalContract = null;
        try (CallableStatement statement = connection.prepareCall("{CALL getRentalContractByRenter(?)}")) {
            statement.setString(1, renter.getId().toString());
            ResultSet resultSet = statement.executeQuery();
            // No rows because no result.
            if (!resultSet.isBeforeFirst()) {
                return rentalContract;
            }
            while (resultSet.next()) {
                rentalContract = mapResultSetToRentalContract(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rentalContract;
    }

    @Override
    public RentalContract getRentalContractById(UUID id) {
        RentalContract rentalContract = null;
        try (CallableStatement statement = connection.prepareCall("{CALL getRentalContractById(?)}")) {
            statement.setString(1, id.toString());
            ResultSet resultSet = statement.executeQuery();
            // No rows because no result.
            if (!resultSet.isBeforeFirst()) {
                return rentalContract;
            }
            while (resultSet.next()) {
                rentalContract = mapResultSetToRentalContract(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rentalContract;
    }

    @Override
    public List<RentalContract> getRentalContractByDateRange(LocalDate fromDate, LocalDate endDate) {
        List<RentalContract> rentalContractList = new ArrayList<>();
        try (CallableStatement statement = connection.prepareCall("{CALL getRentalContractsByDateRange(?, ?)}")) {
            statement.setDate(1, Date.valueOf(fromDate));
            statement.setDate(2, Date.valueOf(endDate));
            ResultSet resultSet = statement.executeQuery();
            // No rows because no result.
            if (!resultSet.isBeforeFirst()) {
                return rentalContractList;
            }
            while (resultSet.next()) {
                rentalContractList.add(mapResultSetToRentalContract(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rentalContractList;
    }

    @Override
    public void updateRentalContract(RentalContract updatedRentalContract) {
        try (CallableStatement statement = connection.prepareCall("{CALL updateRentalContract(?, ?, ?, ?, ?, ?)}")) {
            setValuesOfRentalContractOnStatement(updatedRentalContract, statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRentalContractByRenter(Renter renter) {
        try (CallableStatement statement = connection.prepareCall("{CALL deleteRentalContractByRenter(?)}")) {
            statement.setString(1, renter.getId().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRentalContractById(UUID id) {
        try (CallableStatement statement = connection.prepareCall("{CALL deleteRentalContractById(?)}")) {
            statement.setString(1, id.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Helper method to map ResultSet to Car object
    private RentalContract mapResultSetToRentalContract(ResultSet resultSet) throws SQLException {
        // Retrieve ID as bytes
        byte[] idBytes = resultSet.getBytes("id");
        UUID id = Utils.convertBytesToUUID(idBytes);

        byte[] renterIdBytes = resultSet.getBytes("renterId");
        UUID renterId = Utils.convertBytesToUUID(renterIdBytes);
        Renter renter = renterRepository.getRenterById(renterId);

        byte[] carIdBytes = resultSet.getBytes("carId");
        UUID carId = Utils.convertBytesToUUID(carIdBytes);
        Car car = carRepository.getCarById(carId);

        LocalDate fromDate = resultSet.getDate("fromDate").toLocalDate();
        LocalDate toDate = resultSet.getDate("toDate").toLocalDate();
        int maxKmAllowed = resultSet.getInt("maxKmAllowed");

        return new RentalContract(id, renter, car, fromDate, toDate, maxKmAllowed);
    }

    private void setValuesOfRentalContractOnStatement(RentalContract rentalContract, CallableStatement callableStatement) throws SQLException {
        callableStatement.setString(1, rentalContract.getId().toString());
        callableStatement.setString(2, rentalContract.getRenter().getId().toString());
        callableStatement.setString(3, rentalContract.getCar().getId().toString());
        callableStatement.setDate(4, Date.valueOf(rentalContract.getFromDate()));
        callableStatement.setDate(5, Date.valueOf(rentalContract.getToDate()));
        callableStatement.setInt(6, rentalContract.getMaxKmAllowed());
    }
}
