package powerball;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import powerball.domain.Purchase;
import powerball.exception.InvalidPurchaseException;
import powerball.exception.NotNumericException;

public class PurchaseTest {

    @Test
    @DisplayName("숫자가 아닌 파워볼 구매 금액: 문자")
    void characterPowerBallPrice() {
        final String ballInput = "a34t";
        final String playInput = "14532";

        assertThatThrownBy(() -> {
            new Purchase(ballInput, playInput);
        }).isInstanceOf(NotNumericException.class);
    }

    @Test
    @DisplayName("숫자가 아닌 파워 플레이 금액: 문자")
    void characterPowerPlayPrice() {
        final String ballInput = "46";
        final String playInput = "j";

        assertThatThrownBy(() -> {
            new Purchase(ballInput, playInput);
        }).isInstanceOf(NotNumericException.class);
    }

    @Test
    @DisplayName("숫자가 아닌 파워볼 구매 금액: 공백")
    void blankPowerBallPrice() {
        final String ballInput = " ";
        final String playInput = "9546";

        assertThatThrownBy(() -> {
            new Purchase(ballInput, playInput);
        }).isInstanceOf(NotNumericException.class);

    }

    @Test
    @DisplayName("숫자가 아닌 파워 플레이 금액: 공백")
    void blankPowerPlayPrice() {
        final String ballInput = "68";
        final String playInput = "              ";

        assertThatThrownBy(() -> {
            new Purchase(ballInput, playInput);
        }).isInstanceOf(NotNumericException.class);
    }

    @Test
    @DisplayName("금액 단위로 나누어 떨어지지 않는 파워볼 구매 금액")
    void powerBallPriceNotMultipleOfPriceUnit() {
        final String ballInput = "21";
        final String playInput = "1";

        assertThatThrownBy(() -> {
            new Purchase(ballInput, playInput);
        }).isInstanceOf(InvalidPurchaseException.class);
    }

    @Test
    @DisplayName("금액 단위로 나누어 떨어지지 않는 파워 플레이 금액(소수점 포함시)")
    void powerPlayPriceNotMultipleOfPriceUnit() {
        final String ballInput = "42";
        final String playInput = "1.5";

        assertThatThrownBy(() -> {
            new Purchase(ballInput, playInput);
        }).isInstanceOf(NotNumericException.class);
    }

    @Test
    @DisplayName("양수가 아닌 파워볼 구매 금액")
    void powerBallPriceUnderMin() {
        final String ballInput = "0";
        final String playInput = "1";

        assertThatThrownBy(() -> {
            new Purchase(ballInput, playInput);
        }).isInstanceOf(InvalidPurchaseException.class);
    }

    @Test
    @DisplayName("양수가 아닌 파워 플레이 금액")
    void powerPlayPriceNUnderMin() {
        final String ballInput = "52";
        final String playInput = "0";

        assertThatThrownBy(() -> {
            new Purchase(ballInput, playInput);
        }).isInstanceOf(InvalidPurchaseException.class);
    }

    @Test
    @DisplayName("파워볼 횟수보다 큰 파워 플레이 횟수")
    void powerPlayNumberOverPowerBallNumber() {
        final String ballInput = "12";
        final String playInput = "7";

        assertThatThrownBy(() -> {
            new Purchase(ballInput, playInput);
        }).isInstanceOf(InvalidPurchaseException.class);
    }

    @Test
    @DisplayName("파워볼 수량 및 파워 플레이 횟수 계산")
    void calculateNumbers() {
        final String ballInput = "10";
        final String playInput = "4";

        final Purchase purchase = new Purchase(ballInput, playInput);

        assertThat(purchase.boughtPowerBallNumber()).isEqualTo(5);
        assertThat(purchase.boughtPowerPlayNumber()).isEqualTo(4);
    }


}
