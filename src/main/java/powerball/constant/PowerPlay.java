package powerball.constant;

import static powerball.constant.Prize.FIRST;
import static powerball.constant.Prize.SECOND;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public enum PowerPlay {
    TWO_TIMES(2),
    TREE_TIMES(3),
    FOUR_TIMES(4),
    FIVE_TIMES(5),
    TEN_TIMES(10);
    public static final int DEFAULT_MULTIPLIER_NUMBER = 1;
    public static final List<Prize> POWER_PLAY_EXCEPTION = Arrays.asList(FIRST, SECOND);
    private static final Random random = new Random();
    private final int rate;

    PowerPlay(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public static int randomRate() {
        return values()[random.nextInt(values().length)].getRate();
    }
}
