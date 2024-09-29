package entity;

import java.util.UUID;

public class Renter {
    private final UUID id;
    private String name;
    private String address;
    private String zipCode;
    private String city;
    private String phoneNumber;
    private String phone;
    private String email;

    public Renter(String name, String address, String zipCode, String city, String phoneNumber, String phone, String email) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.phone = phone;
        this.email = email;
    }

    public Renter(UUID id, String name, String address, String zipCode, String city, String phoneNumber, String phone, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void updateValues(Renter otherRenter) {
        this.name = otherRenter.getName();
        this.address = otherRenter.getAddress();
        this.zipCode = otherRenter.getZipCode();
        this.city = otherRenter.getCity();
        this.phoneNumber = otherRenter.getPhoneNumber();
        this.phone = otherRenter.getPhone();
        this.email = otherRenter.getEmail();
    }

    public String getConsoleSaveString(String argSeparator) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(id.toString()).append(argSeparator);
        stringBuilder.append(name).append(argSeparator);
        stringBuilder.append(address).append(argSeparator);
        stringBuilder.append(zipCode).append(argSeparator);
        stringBuilder.append(city).append(argSeparator);
        stringBuilder.append(phoneNumber).append(argSeparator);
        stringBuilder.append(phone).append(argSeparator);
        stringBuilder.append(email);
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return  "Name: " + name + "\n" +
                "Address: " + address + "\n" +
                "Zip Code: " + zipCode + "\n" +
                "City: " + city + "\n" +
                "Phone Number: " + phoneNumber + "\n" +
                "Phone: " + phone + "\n" +
                "Email: " + email;
    }

}
