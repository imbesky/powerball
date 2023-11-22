package powerball.domain.dto;

public record PurchaseDto(
        int boughtPowerBallNumber,
        int boughtPowerPlayNumber
) {
    public static PurchaseDto of(final int boughtPowerBallNumber, final int boughtPowerPlayNumber) {
        return new PurchaseDto(boughtPowerBallNumber, boughtPowerPlayNumber);
    }
}