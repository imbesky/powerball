package powerball.constant;

public enum Price {
    POWER_BALL(2),
    POWER_PLAY(1);
    private final int priceUnit;

    Price(int priceUnit) {
        this.priceUnit = priceUnit;
    }

    public static final int PROPER_REMAINDER = 0;

    public int priceUnit() {
        return priceUnit;
    }
}
