package powerball.domain;

import static powerball.constant.Price.POWER_BALL;
import static powerball.constant.Price.POWER_PLAY;
import static powerball.constant.Price.PROPER_REMAINDER;

import powerball.domain.dto.PurchaseDto;
import powerball.domain.dto.PurchaseResultDto;
import powerball.exception.InvalidPurchaseException;
import powerball.exception.NotNumericException;

public class Purchase {
    private final static int MIN = 0;
    private final int powerBallPrice;
    private final int powerPlayPrice;

    public Purchase(final String powerBallPrice, final String powerPlayPrice) {
        validatePowerBallPrice(powerBallPrice);
        this.powerBallPrice = Integer.parseInt(powerBallPrice);
        validatePowerPlayPrice(powerPlayPrice);
        this.powerPlayPrice = Integer.parseInt(powerPlayPrice);
    }

    public static Purchase from(final PurchaseDto purchaseDto) {
        return new Purchase(purchaseDto.powerBallPrice(), purchaseDto.powerPlayPrice());
    }

    private void validatePowerBallPrice(final String price) {
        try {
            validPowerBallPrice(Integer.parseInt(price));
        } catch (NumberFormatException e) {
            throw new NotNumericException();
        }
    }

    private void validPowerBallPrice(final int price) {
        if (price <= MIN
                || price % POWER_BALL.priceUnit() != PROPER_REMAINDER) {
            throw new InvalidPurchaseException();
        }
    }

    private void validatePowerPlayPrice(final String price) {
        try {
            validPowerPlayPrice(Integer.parseInt(price));
        } catch (NumberFormatException e) {
            throw new NotNumericException();
        }
    }

    private void validPowerPlayPrice(final int price) {
        if (price <= MIN
                || price % POWER_PLAY.priceUnit() != PROPER_REMAINDER
                || powerBallPrice / POWER_BALL.priceUnit() < price / POWER_PLAY.priceUnit()) {
            throw new InvalidPurchaseException();
        }
    }

    public int boughtPowerBallNumber() {
        return powerBallPrice / POWER_BALL.priceUnit();
    }

    public int boughtPowerPlayNumber() {
        return powerPlayPrice / POWER_PLAY.priceUnit();
    }

    public PurchaseResultDto toPurchaseResultDto() {
        return new PurchaseResultDto(boughtPowerBallNumber(), boughtPowerPlayNumber());
    }

}
