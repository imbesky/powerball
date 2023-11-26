package powerball.controller;

import static powerball.constant.Format.BOUGHT_NUMBER_RESULT;
import static powerball.constant.Format.BOUGHT_POWER_PLAY;
import static powerball.constant.Format.BOUGHT_RED_POWER_BALL;
import static powerball.constant.Format.BOUGHT_WHITE_BALL;
import static powerball.constant.Format.EARNING_RATIO;
import static powerball.constant.Format.INPUT_AND_INQUIRE;
import static powerball.constant.Format.PRICE_FORMAT;
import static powerball.constant.Format.WIN_STATISTICS;
import static powerball.constant.PowerPlay.DEFAULT_MULTIPLIER_NUMBER;

import java.text.DecimalFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import powerball.domain.dto.PurchaseResultDto;
import powerball.service.ResultService;

@Controller
public class ResultController {
    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping("/purchase-result")
    public void boughtResult(final Model model) {
        final PurchaseResultDto purchaseResult = resultService.purchaseResult();
        model.addAttribute("purchaseResult", String.format(
                BOUGHT_NUMBER_RESULT,
                purchaseResult.boughtPowerBallNumber(),
                purchaseResult.boughtPowerPlayNumber()));
        model.addAttribute("boughtBalls", resultService.boughtResult().boughtPowerBalls());
        model.addAttribute("whiteBall", BOUGHT_WHITE_BALL);
        model.addAttribute("redPowerBall", BOUGHT_RED_POWER_BALL);
        model.addAttribute("powerPlay", BOUGHT_POWER_PLAY);
        model.addAttribute("initialValue", DEFAULT_MULTIPLIER_NUMBER);
        model.addAttribute("inputToInquire", INPUT_AND_INQUIRE);
    }

    @GetMapping("/result")
    public void finalResult(final Model model) {
        resultService.saveResult();
        model.addAttribute("header", WIN_STATISTICS);
        model.addAttribute("results", resultService.winResult().results());
        final DecimalFormat decimalFormat = new DecimalFormat(PRICE_FORMAT);
        model.addAttribute("earningRatio", String.format(
                EARNING_RATIO,
                decimalFormat.format(resultService.earningRatio())));
    }

}
