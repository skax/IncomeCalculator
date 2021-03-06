package kmiecik.michal.earningscalculator.infrastructure.rest;


import kmiecik.michal.earningscalculator.domain.incomecalculator.IncomeCalculatorFacade;
import kmiecik.michal.earningscalculator.domain.incomecalculator.dto.OfferDataDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
class IncomeCalculatorController {

    private final IncomeCalculatorFacade facade;
    private final ResponseResolver responseResolver;

    public IncomeCalculatorController(final IncomeCalculatorFacade facade, final ResponseResolver responseResolver) {
        this.facade = facade;
        this.responseResolver = responseResolver;
    }

    @GetMapping("/calculate")
    ResponseEntity<?> calculateIncome(final @ModelAttribute OfferDataDto form) {
        return responseResolver.resolve(facade.calculateMonthlyIncomeNetInPLN(form), bigDecimal -> new RestResponse(bigDecimal.toString()));
    }

}
