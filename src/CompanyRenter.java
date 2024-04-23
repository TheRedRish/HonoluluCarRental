import java.util.UUID;

public class CompanyRenter extends Renter {
    private String companyName;
    private String companyAddress;
    private String companyPhoneNumber;
    private int companyRegistrationNumber;

    public CompanyRenter(String name, String address, String zipCode, String city, String phoneNumber, String phone, String email, String companyName, String companyAddress, String companyPhoneNumber, int companyRegistrationNumber) {
        super(name, address, zipCode, city, phoneNumber, phone, email);
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyPhoneNumber = companyPhoneNumber;
        this.companyRegistrationNumber = companyRegistrationNumber;
    }

    public CompanyRenter(UUID id, String name, String address, String zipCode, String city, String phoneNumber, String phone, String email, String companyName, String companyAddress, String companyPhoneNumber, int companyRegistrationNumber) {
        super(id, name, address, zipCode, city, phoneNumber, phone, email);
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyPhoneNumber = companyPhoneNumber;
        this.companyRegistrationNumber = companyRegistrationNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyPhoneNumber() {
        return companyPhoneNumber;
    }

    public void setCompanyPhoneNumber(String companyPhoneNumber) {
        this.companyPhoneNumber = companyPhoneNumber;
    }

    public int getCompanyRegistrationNumber() {
        return companyRegistrationNumber;
    }

    public void setCompanyRegistrationNumber(int companyRegistrationNumber) {
        this.companyRegistrationNumber = companyRegistrationNumber;
    }
}
