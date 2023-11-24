package powerball.controller;

import static powerball.constant.Format.BOUGHT_NUMBER_RESULT;
import static powerball.constant.Format.BOUGHT_RESULT_DETAIL;
import static powerball.constant.Format.EARNING_RATIO;
import static powerball.constant.Format.INPUT_AND_INQUIRE;
import static powerball.constant.Format.PRICE_FORMAT;
import static powerball.constant.Format.WIN_STATISTICS;

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
        model.addAttribute("ballsFormat", BOUGHT_RESULT_DETAIL);
        model.addAttribute("inputToInquire", INPUT_AND_INQUIRE);
    }

    @GetMapping("/result")
    public void finalResult(final Model model) {
        resultService.saveResult();
        model.addAttribute("header", WIN_STATISTICS);
        model.addAttribute("results", resultService.winResult());
        final DecimalFormat decimalFormat = new DecimalFormat(PRICE_FORMAT);
        model.addAttribute("earningRatio", String.format(
                EARNING_RATIO,
                decimalFormat.format(resultService.earningRatio())));
    }

}
