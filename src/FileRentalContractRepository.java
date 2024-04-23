import java.time.LocalDateTime;
import java.util.List;

public class FileRentalContractRepository implements IRentalContractRepository{
    @Override
    public void addRentalContract(RentalContract rentalContract) {

    }

    @Override
    public List<RentalContract> getAllRentalContracts() {
        return null;
    }

    @Override
    public RentalContract getRentalContractByRenter(Renter renter) {
        return null;
    }

    @Override
    public List<RentalContract> getRentalContractByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return null;
    }

    @Override
    public void updateRentalContract(RentalContract updatedRentalContract) {

    }

    @Override
    public void deleteRentalContractByRenter(Renter renter) {

    }

    @Override
    public void deleteRentalContractByCar(Car car) {

    }
}
