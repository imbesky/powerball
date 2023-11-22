package powerball.domain;

import static powerball.constant.Ball.INDEX_DIFFERENCE;
import static powerball.constant.Ball.RED_POWER_BALL;

import java.util.Random;
import powerball.exception.NotNumericException;
import powerball.exception.OutOfRangeException;

public class RedPowerBall {
    private final int redPowerBall;

    public RedPowerBall(final String number) {
        validate(number);
        redPowerBall = Integer.parseInt(number);
    }

    public RedPowerBall() {
        redPowerBall = generateRedPowerBall();
    }

    private void validate(final String number) {
        try {
            isRange(Integer.parseInt(number));
        } catch (NumberFormatException e) {
            throw new NotNumericException();
        }
    }

    private void isRange(final int number) {
        if (number < RED_POWER_BALL.getMin() || number > RED_POWER_BALL.getMax()) {
            throw new OutOfRangeException();
        }
    }

    private int generateRedPowerBall() {
        final Random random = new Random();
        return random.nextInt(RED_POWER_BALL.getMax() + INDEX_DIFFERENCE);
    }

    public int inquireDetail() {
        return redPowerBall;
    }

}
