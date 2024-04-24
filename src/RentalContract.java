import java.time.LocalDate;
import java.util.UUID;

public class RentalContract {
    private final UUID id;
    private Renter renter;
    private LocalDate fromDate;
    private LocalDate toDate;
    private int maxKmAllowed;
    private Car car;

    public RentalContract(Renter renter, LocalDate fromDate, LocalDate toDate, int maxKmAllowed, Car car) {
        this.id = UUID.randomUUID();
        this.renter = renter;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.maxKmAllowed = maxKmAllowed;
        this.car = car;
    }

    public RentalContract(UUID id, Renter renter, LocalDate fromDate, LocalDate toDate, int maxKmAllowed, Car car) {
        this.id = id;
        this.renter = renter;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.maxKmAllowed = maxKmAllowed;
        this.car = car;
    }

    public Renter getRenter() {
        return renter;
    }

    public void setRenter(Renter renter) {
        this.renter = renter;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public int getMaxKmAllowed() {
        return maxKmAllowed;
    }

    public void setMaxKmAllowed(int maxKmAllowed) {
        this.maxKmAllowed = maxKmAllowed;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public UUID getId() {
        return id;
    }

    public void updateValues(RentalContract otherContract) {
        this.renter = otherContract.getRenter();
        this.fromDate = otherContract.getFromDate();
        this.toDate = otherContract.getToDate();
        this.maxKmAllowed = otherContract.getMaxKmAllowed();
        this.car = otherContract.getCar();
    }

    public String getConsoleSaveString(String argSeparator) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(id.toString()).append(argSeparator);
        stringBuilder.append(renter.getId().toString()).append(argSeparator);
        stringBuilder.append(fromDate).append(argSeparator);
        stringBuilder.append(toDate).append(argSeparator);
        stringBuilder.append(maxKmAllowed).append(argSeparator);
        stringBuilder.append(car.getId().toString());
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return "Renter: " + renter.getName() + "\n" +
                "Car: " + car.getCarBrand() + " " + car.getModel() + "\n" +
                "From Date: " + fromDate + "\n" +
                "To Date: " + toDate + "\n" +
                "Max Km Allowed: " + maxKmAllowed;
    }
}
