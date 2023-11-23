package powerball.domain;

import static powerball.constant.Ball.RED_POWER_BALL;
import static powerball.util.RandomUtil.createRandomNumber;

public record RedPowerBall(
        int number
) {

    public static RedPowerBall createRedPowerBall() {
        return new RedPowerBall(createRandomNumber(RED_POWER_BALL.getMin(), RED_POWER_BALL.getMax()));
    }

}
