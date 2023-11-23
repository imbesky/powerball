package powerball.domain;

import static powerball.constant.Price.POWER_BALL;
import static powerball.constant.Price.POWER_PLAY;
import static powerball.constant.Price.PROPER_REMAINDER;

import powerball.domain.dto.PurchaseDto;
import powerball.exception.InvalidPurchaseException;
import powerball.exception.NotNumericException;

public class Purchase {
    private final int powerBallPrice;
    private final int powerPlayPrice;

    public Purchase(final String powerBallPrice, final String powerPlayPrice) {
        validatePowerBallPrice(powerBallPrice);
        this.powerBallPrice = Integer.parseInt(powerBallPrice);
        validatePowerPlayPrice(powerBallPrice);
        this.powerPlayPrice = Integer.parseInt(powerBallPrice);
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
        if (price % POWER_BALL.priceUnit() != PROPER_REMAINDER) {
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
        if (price % POWER_PLAY.priceUnit() != PROPER_REMAINDER) {
            throw new InvalidPurchaseException();
        }
        if (powerBallPrice / POWER_BALL.priceUnit() < price / POWER_PLAY.priceUnit()) {
            throw new InvalidPurchaseException();
        }
    }

    public int boughtPowerBallNumber() {
        return powerBallPrice / POWER_BALL.priceUnit();
    }

    public int boughtPowerPlayNumber() {
        return powerPlayPrice / POWER_PLAY.priceUnit();
    }

}
