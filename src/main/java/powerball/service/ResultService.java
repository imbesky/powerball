package powerball.service;

import static powerball.constant.Price.POWER_BALL;
import static powerball.constant.Price.POWER_PLAY;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import powerball.constant.Prize;
import powerball.constant.PrizeNotice;
import powerball.domain.Purchase;
import powerball.domain.Result;
import powerball.domain.dto.BoughtPowerBallsDto;
import powerball.domain.dto.PurchaseResultDto;
import powerball.domain.dto.ResultDto;
import powerball.repository.DbRepository;

@Service
public class ResultService {
    private final DbRepository dbRepository;

    public ResultService(DbRepository dbRepository) {
        this.dbRepository = dbRepository;
    }

    public PurchaseResultDto purchaseResult() {
        return dbRepository.inquirePurchase().toPurchaseResultDto();
    }

    public BoughtPowerBallsDto boughtResult() {
        return dbRepository.inquireBoughtPowerBalls().toDto();
    }

    public void saveResult() {
        dbRepository.saveResult(new Result(
                dbRepository.inquireWinPowerBall(),
                dbRepository.inquireBoughtPowerBalls(),
                dbRepository.inquirePurchase().boughtPowerBallNumber()));
    }

    public ResultDto winResult() {
        final Result result = dbRepository.inquireResult();
        final List<String> results = new ArrayList<>();
        for (Prize prize : Prize.values()) {
            results.add(
                    String.format(PrizeNotice.findByGrade(prize.getGrade()).getNotice(), result.numberByPrize(prize)));
        }
        return ResultDto.from(results);
    }

    public double earningRatio() {
        final Purchase purchase = dbRepository.inquirePurchase();
        final int payed =
                purchase.boughtPowerBallNumber() * POWER_BALL.priceUnit()
                        + purchase.boughtPowerPlayNumber() * POWER_PLAY.priceUnit();
        return ((double) dbRepository.inquireResult().totalEarning()) / payed;
    }
}
