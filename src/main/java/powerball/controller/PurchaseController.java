package powerball.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import powerball.constant.Exception;
import powerball.domain.dto.PurchaseDto;
import powerball.exception.PowerBallException;
import powerball.service.PurchaseService;

@Controller
public class PurchaseController {
    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("/purchase")
    public String purchaseInput(@RequestParam final PurchaseDto purchaseDto) {
        try {
            purchaseService.savePurchase(purchaseDto);
        } catch (PowerBallException exception) {
            return exception.errorPage();
        }
        return "/bought";
    }

    @GetMapping("/exception")
    public void handleException(@RequestParam("type") final String type, final Model model) {
        final Exception exception = Exception.findByType(type);
        model.addAttribute("message", exception.getMessage());
    }
}
