package powerball.service;

import static powerball.constant.Format.BLANK;
import static powerball.constant.Format.COMMA;
import static powerball.constant.Format.EMPTY;
import static powerball.constant.Format.ROUND_BRACKET;
import static powerball.constant.Price.POWER_BALL;
import static powerball.constant.Price.POWER_PLAY;
import static powerball.constant.PrizeNotice.POWER_PLAY_DETAIL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.stereotype.Service;
import powerball.constant.PowerPlay;
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
    private static final int FIRST_INDEX = 0;
    private static final int TWO_CHARS = 2;
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
            results.add(resultGenerator(prize, result));
        }
        return ResultDto.from(results);
    }

    private String resultGenerator(final Prize prize, final Result result) {
        return String.format(PrizeNotice.findNoticeByGrade(prize.getGrade()),
                        result.winNumberByPrize(prize))
                .concat(powerPlayResultGenerator(prize, result));
    }
    
    private String powerPlayResultGenerator(final Prize prize, final Result result) {
        final Map<PowerPlay, Integer> powerPlayNumber = result.winNumberByPowerPlay(prize);
        if (powerPlayNumber == null) {
            return EMPTY;
        }
        final StringBuilder powerPlayResult = new StringBuilder();
        for (Entry<PowerPlay, Integer> number : powerPlayNumber.entrySet()) {
            powerPlayResult.append(String.format(POWER_PLAY_DETAIL, number.getKey().getRate(), number.getValue()));
            powerPlayResult.append(COMMA);
            powerPlayResult.append(BLANK);
        }
        return String.format(ROUND_BRACKET,
                powerPlayResult.substring(FIRST_INDEX, powerPlayResult.length() - TWO_CHARS));
    }

    public double earningRatio() {
        final Purchase purchase = dbRepository.inquirePurchase();
        final int payed =
                purchase.boughtPowerBallNumber() * POWER_BALL.priceUnit()
                        + purchase.boughtPowerPlayNumber() * POWER_PLAY.priceUnit();
        return ((double) dbRepository.inquireResult().totalEarning()) / payed;
    }
}
