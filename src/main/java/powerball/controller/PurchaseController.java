package powerball.controller;

import static powerball.constant.Format.GREETINGS;
import static powerball.constant.Format.INPUT_POWER_BALL_PURCHASE_PRICE;
import static powerball.constant.Format.INPUT_POWER_PLAY_PURCHASE_PRICE;
import static powerball.constant.Format.POWER_BALL_PRICE_ATTENTION;
import static powerball.constant.Format.POWER_PLAY_PRICE_ATTENTION;
import static powerball.constant.Format.PURCHASE;
import static powerball.constant.Price.POWER_BALL;
import static powerball.constant.Price.POWER_PLAY;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import powerball.constant.Exception;
import powerball.domain.dto.PurchaseDto;
import powerball.exception.PowerBallException;
import powerball.service.PurchaseService;

@Controller
public class PurchaseController {
    private final static String REDIRECT = "redirect:/";
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("/")
    public String purchaseNotice(final Model model) {
        model.addAttribute("greeting", GREETINGS);
        model.addAttribute("ball_notice", INPUT_POWER_BALL_PURCHASE_PRICE);
        model.addAttribute("play_notice", INPUT_POWER_PLAY_PURCHASE_PRICE);
        model.addAttribute("ball_attention", String.format(POWER_BALL_PRICE_ATTENTION, POWER_BALL.priceUnit()));
        model.addAttribute("play_attention", String.format(POWER_PLAY_PRICE_ATTENTION, POWER_PLAY.priceUnit()));
        model.addAttribute("purchase", PURCHASE);
        return "input-price";
    }

    @PostMapping("/purchase")
    public String purchaseInput(final PurchaseDto purchaseDto, final RedirectAttributes redirectAttributes) {
        try {
            purchaseService.savePurchase(purchaseDto);
        } catch (PowerBallException exception) {
            redirectAttributes.addAttribute("detail", exception.getMessage());
            return REDIRECT.concat(Exception.URL);
        }
        return REDIRECT.concat("purchase-result");
    }

}