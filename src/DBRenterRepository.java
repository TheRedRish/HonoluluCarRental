import enums.CarBrand;
import enums.FuelType;
import enums.GearType;
import enums.SeatType;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DBRenterRepository implements IRenterRepository {
    private final Connection connection;

    public DBRenterRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addRenter(Renter renter) {
        try {
            CallableStatement callableStatement;
            if (renter instanceof PrivateRenter) {
                callableStatement = connection.prepareCall("{CALL AddPrivateRenter(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            } else if (renter instanceof CompanyRenter) {
                callableStatement = connection.prepareCall("{CALL AddCompanyRenter(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            } else {
                callableStatement = connection.prepareCall("{CALL AddRenter(?, ?, ?, ?, ?, ?, ?, ?)}");
            }
            setValuesOfRenterOnStatement(renter, callableStatement);

            callableStatement.execute();
            callableStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Renter> getAllRenters() {
        List<Renter> renterList = new ArrayList<>();
        try (CallableStatement statement = connection.prepareCall("{CALL getAllCars()}")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                renterList.add(mapResultSetToRenter(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return renterList;
    }

    @Override
    public Renter getRenterById(UUID id) {
        Renter renter = null;
        try (CallableStatement statement = connection.prepareCall("{CALL getRenterById(?)}")) {
            statement.setString(1, id.toString());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                renter = mapResultSetToRenter(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return renter;
    }

    @Override
    public List<Renter> getRentersByName(String name) {
        List<Renter> renterList = new ArrayList<>();
        try (CallableStatement statement = connection.prepareCall("{CALL getRenterByName(?)}")) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                renterList.add(mapResultSetToRenter(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return renterList;
    }

    @Override
    public List<Renter> getRentersByPhoneNumber(String phoneNumber) {
        List<Renter> renterList = new ArrayList<>();
        try (CallableStatement statement = connection.prepareCall("{CALL getRenterByPhoneNumber(?)}")) {
            statement.setString(1, phoneNumber);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                renterList.add(mapResultSetToRenter(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return renterList;
    }

    @Override
    public void updateRenter(Renter updatedRenter) {
        try {
            CallableStatement callableStatement;
            if (updatedRenter instanceof PrivateRenter) {
                callableStatement = connection.prepareCall("{CALL updatePrivateRenter(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            } else if (updatedRenter instanceof CompanyRenter) {
                callableStatement = connection.prepareCall("{CALL updateCompanyRenter(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            } else {
                callableStatement = connection.prepareCall("{CALL updateRenter(?, ?, ?, ?, ?, ?, ?, ?)}");
            }
            setValuesOfRenterOnStatement(updatedRenter, callableStatement);

            callableStatement.execute();
            callableStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRenterByName(String name) {
        try (CallableStatement statement = connection.prepareCall("{CALL deleteRenterByName(?)}")) {
            statement.setString(1, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRenterByPhoneNumber(String phoneNumber) {
        try (CallableStatement statement = connection.prepareCall("{CALL deleteRenterByPhoneNumber(?)}")) {
            statement.setString(1, phoneNumber);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRenterById(UUID id) {
        try (CallableStatement statement = connection.prepareCall("{CALL deleteRenterById(?)}")) {
            statement.setString(1, id.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Helper method to map ResultSet to Renter object
    private Renter mapResultSetToRenter(ResultSet resultSet) throws SQLException {
        // Retrieve ID as bytes
        byte[] idBytes = resultSet.getBytes("id");
        // Convert bytes to UUID
        UUID id = Utils.convertBytesToUUID(idBytes);

        String name = resultSet.getString("name");
        String address = resultSet.getString("address");
        String zipCode = resultSet.getString("zipCode");
        String city = resultSet.getString("city");
        String phoneNumber = resultSet.getString("phoneNumber");
        String phone = resultSet.getString("phone");
        String email = resultSet.getString("email");

        // TODO ask if this can be done smortere
        // It will always return true to driversLicenceNumber, but that might be a query problem.
        // Column name from PrivateRenter or column name from CompanyRenter
        if (resultSetContainsColumn(resultSet, "driversLicenceNumber")) {
            String driversLicenceNumber = resultSet.getString("driversLicenceNumber");
            LocalDate dateOfAcquiredLicense = resultSet.getDate("dateOfAcquiredLicense").toLocalDate();
            return new PrivateRenter(id, name, address, zipCode, city, phoneNumber, phone, email, driversLicenceNumber, dateOfAcquiredLicense);
        } else if (resultSetContainsColumn(resultSet, "companyName")) {
            String companyName = resultSet.getString("companyName");
            String companyAddress = resultSet.getString("companyAddress");
            String companyPhoneNumber = resultSet.getString("companyPhoneNumber");
            int companyRegistrationNumber = resultSet.getInt("companyRegistrationNumber");
            return new CompanyRenter(id, name, address, zipCode, city, phoneNumber, phone, email, companyName, companyAddress, companyPhoneNumber, companyRegistrationNumber);
        }
        return null;
    }

    private boolean resultSetContainsColumn(ResultSet resultSet, String columnName) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            String metaDataColumnName = metaData.getColumnName(i);
            if (metaDataColumnName.equals(columnName)) {
                return true;
            }
        }
        return false;
    }

    private void setValuesOfRenterOnStatement(Renter renter, CallableStatement callableStatement) throws SQLException {
        callableStatement.setString(1, renter.getId().toString());
        callableStatement.setString(2, renter.getName());
        callableStatement.setString(3, renter.getAddress());
        callableStatement.setString(4, renter.getZipCode());
        callableStatement.setString(5, renter.getCity());
        callableStatement.setString(6, renter.getPhoneNumber());
        callableStatement.setString(7, renter.getPhone());
        callableStatement.setString(8, renter.getEmail());
        if (renter instanceof PrivateRenter) {
            callableStatement.setString(9, ((PrivateRenter) renter).getDriversLicenceNumber());
            callableStatement.setDate(10, Date.valueOf(((PrivateRenter) renter).getDateOfAcquiredLicense()));
        } else if (renter instanceof CompanyRenter) {
            callableStatement.setString(9, ((CompanyRenter) renter).getCompanyName());
            callableStatement.setString(10, ((CompanyRenter) renter).getCompanyAddress());
            callableStatement.setString(11, ((CompanyRenter) renter).getCompanyPhoneNumber());
            callableStatement.setInt(12, ((CompanyRenter) renter).getCompanyRegistrationNumber());
        }
    }
}
