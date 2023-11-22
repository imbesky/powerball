package powerball.constant;

public enum Ball {
    WHITE_BALL(1, 69, 5),
    RED_POWER_BALL(1, 26, 1);

    private final int min;
    private final int max;
    private final int properLength;

    Ball(int min, int max, int properLength) {
        this.min = min;
        this.max = max;
        this.properLength = properLength;
    }

    public static final int INDEX_DIFFERENCE = 1;

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getProperLength() {
        return properLength;
    }
}
