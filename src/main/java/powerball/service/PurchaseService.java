package powerball.service;

import org.springframework.stereotype.Service;
import powerball.domain.Purchase;
import powerball.domain.dto.PurchaseDto;
import powerball.repository.DbRepository;

@Service
public class PurchaseService {
    private final DbRepository dbRepository;

    public PurchaseService(final DbRepository dbRepository) {
        this.dbRepository = dbRepository;
    }

    public void savePurchase(final PurchaseDto purchaseDto) {
        dbRepository.savePurchase(Purchase.from(purchaseDto));
    }
}
