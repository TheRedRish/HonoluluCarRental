import java.time.LocalDateTime;
import java.util.UUID;

public class RentalContract {
    private UUID id;
    private Renter renter;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private int maxKmAllowed;
    private Car car;

    public RentalContract(Renter renter, LocalDateTime fromDate, LocalDateTime toDate, int maxKmAllowed, Car car) {
        this.id = UUID.randomUUID();
        this.renter = renter;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.maxKmAllowed = maxKmAllowed;
        this.car = car;
    }

    public RentalContract(UUID id, Renter renter, LocalDateTime fromDate, LocalDateTime toDate, int maxKmAllowed, Car car) {
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

    public UUID getId() {
        return id;
    }
}
