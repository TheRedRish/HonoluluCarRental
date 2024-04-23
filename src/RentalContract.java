import java.time.LocalDateTime;

public class RentalContract {
    private Renter renter;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private int maxKmAllowed;
    private Car car;

    public RentalContract(Renter renter, LocalDateTime fromDate, LocalDateTime toDate, int maxKmAllowed, Car car) {
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

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDateTime getToDate() {
        return toDate;
    }

    public void setToDate(LocalDateTime toDate) {
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
}
