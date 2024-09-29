package entity;

import java.time.LocalDate;
import java.util.UUID;

public class PrivateRenter extends Renter {
    private String driversLicenceNumber;
    private LocalDate dateOfAcquiredLicense;

    public PrivateRenter(String name, String address, String zipCode, String city, String phoneNumber, String phone, String email, String driversLicenceNumber, LocalDate dateOfAcquiredLicense) {
        super(name, address, zipCode, city, phoneNumber, phone, email);
        this.driversLicenceNumber = driversLicenceNumber;
        this.dateOfAcquiredLicense = dateOfAcquiredLicense;
    }

    public PrivateRenter(UUID id, String name, String address, String zipCode, String city, String phoneNumber, String phone, String email, String driversLicenceNumber, LocalDate dateOfAcquiredLicense) {
        super(id, name, address, zipCode, city, phoneNumber, phone, email);
        this.driversLicenceNumber = driversLicenceNumber;
        this.dateOfAcquiredLicense = dateOfAcquiredLicense;
    }

    public String getDriversLicenceNumber() {
        return driversLicenceNumber;
    }

    public void setDriversLicenceNumber(String driversLicenceNumber) {
        this.driversLicenceNumber = driversLicenceNumber;
    }

    public LocalDate getDateOfAcquiredLicense() {
        return dateOfAcquiredLicense;
    }

    public void setDateOfAcquiredLicense(LocalDate dateOfAcquiredLicense) {
        this.dateOfAcquiredLicense = dateOfAcquiredLicense;
    }

    public String getAddress() {
        return super.getAddress();
    }

    public void updateValues(PrivateRenter otherPrivateRenter) {
        super.updateValues(otherPrivateRenter);
        this.driversLicenceNumber = otherPrivateRenter.getDriversLicenceNumber();
        this.dateOfAcquiredLicense = otherPrivateRenter.getDateOfAcquiredLicense();
    }

    @Override
    public String getConsoleSaveString(String argSeparator) {
        StringBuilder stringBuilder = new StringBuilder(super.getConsoleSaveString(argSeparator));
        stringBuilder.append(argSeparator);
        stringBuilder.append(driversLicenceNumber).append(argSeparator);
        stringBuilder.append(dateOfAcquiredLicense);
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
                "Driver's Licence Number: " + driversLicenceNumber + "\n" +
                "Date of Acquired License: " + dateOfAcquiredLicense;
    }

}
