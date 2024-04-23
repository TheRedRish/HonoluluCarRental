import java.time.LocalDateTime;
import java.util.UUID;

public class PrivateRenter extends Renter {
    private String driversLicenceNumber;
    private LocalDateTime dateOfAcquiredLicense;

    public PrivateRenter(String name, String address, String zipCode, String city, String phoneNumber, String phone, String email, String driversLicenceNumber, LocalDateTime dateOfAcquiredLicense) {
        super(name, address, zipCode, city, phoneNumber, phone, email);
        this.driversLicenceNumber = driversLicenceNumber;
        this.dateOfAcquiredLicense = dateOfAcquiredLicense;
    }

    public PrivateRenter(UUID id, String name, String address, String zipCode, String city, String phoneNumber, String phone, String email, String driversLicenceNumber, LocalDateTime dateOfAcquiredLicense) {
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

    public LocalDateTime getDateOfAcquiredLicense() {
        return dateOfAcquiredLicense;
    }

    public void setDateOfAcquiredLicense(LocalDateTime dateOfAcquiredLicense) {
        this.dateOfAcquiredLicense = dateOfAcquiredLicense;
    }
}
