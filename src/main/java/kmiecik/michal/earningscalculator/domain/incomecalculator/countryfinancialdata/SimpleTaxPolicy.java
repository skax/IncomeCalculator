package kmiecik.michal.earningscalculator.domain.incomecalculator.countryfinancialdata;

import kmiecik.michal.earningscalculator.domain.incomecalculator.TaxPolicy;

import java.math.BigDecimal;
import java.math.RoundingMode;

class SimpleTaxPolicy implements TaxPolicy {

    private static final BigDecimal HUNDRED = new BigDecimal(100);

    private final BigDecimal taxValue;
    private final BigDecimal fixedCosts;

    SimpleTaxPolicy(final BigDecimal taxValue, final BigDecimal fixedCosts) {
        this.taxValue = taxValue;
        this.fixedCosts = fixedCosts;
    }

    @Override
    public BigDecimal apply(final BigDecimal earningsGross) {

        final BigDecimal afterSubtraction = earningsGross.subtract(fixedCosts);

        if(afterSubtraction.compareTo(BigDecimal.ZERO) < 0)
            return BigDecimal.ZERO;

        return calculateAfterTax(afterSubtraction);
    }

    private BigDecimal calculateAfterTax(final BigDecimal incomeGross) {
        return incomeGross.multiply(calculatePercentAfterTax()).divide(HUNDRED, 2, RoundingMode.HALF_EVEN);
    }

    private BigDecimal calculatePercentAfterTax() {
        return (HUNDRED.subtract(taxValue));
    }

}
