package powerball.repository;

import org.springframework.stereotype.Repository;
import powerball.domain.BoughtPowerBalls;
import powerball.domain.Purchase;
import powerball.domain.Result;
import powerball.domain.WinPowerBall;

@Repository
public class DbRepository {
    // 구매 금액과 당첨 번호에 따른 일시적인 결과를 반환하는 프로그램이므로 데이터베이스 연동 없이 제작
    // 이 클래스는 repository의 역할과 정보 저장의 역할을 동시에 하고 있음
    private Purchase purchase;
    private WinPowerBall winPowerBall;
    private BoughtPowerBalls boughtPowerBalls;
    private Result result;

    public void savePurchase(final Purchase purchase) {
        this.purchase = purchase;
        boughtPowerBalls = new BoughtPowerBalls(purchase.boughtPowerBallNumber(), purchase.boughtPowerPlayNumber());
    }

    public void saveWinPowerBall(final WinPowerBall winPowerBall) {
        this.winPowerBall = winPowerBall;
    }

    public void saveResult(final Result result) {
        this.result = result;
    }

    public Purchase inquirePurchase() {
        return purchase;
    }

    public BoughtPowerBalls inquireBoughtPowerBalls() {
        return boughtPowerBalls;
    }

    public WinPowerBall inquireWinPowerBall() {
        return winPowerBall;
    }

    public Result inquireResult() {
        return result;
    }
}
