package powerball.domain;

import static powerball.constant.Price.POWER_BALL;
import static powerball.constant.Price.POWER_PLAY;
import static powerball.constant.Price.PROPER_REMAINDER;

import powerball.domain.dto.PurchaseDto;
import powerball.exception.InvalidPurchaseException;
import powerball.exception.NotNumericException;

public class Purchase {
    private final int powerBallPrice;
    private int powerPlayPrice;

    public Purchase(final String powerBallPrice) {
        validatePowerBallPrice(powerBallPrice);
        this.powerBallPrice = Integer.parseInt(powerBallPrice);
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

    public void doPowerPlay(final String input) {
        validatePowerPlayPrice(input);
        powerPlayPrice = Integer.parseInt(input);
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

    public PurchaseDto toPurchaseDto() {
        return PurchaseDto.of(boughtPowerBallNumber(), boughtPowerPlayNumber());
    }

    private int boughtPowerBallNumber() {
        return powerBallPrice / POWER_BALL.priceUnit();
    }

    private int boughtPowerPlayNumber() {
        return powerPlayPrice / POWER_PLAY.priceUnit();
    }
}
