package kmiecik.michal.earningscalculator.infrastructure.exchangerateservices;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("exchange")
public class ExchangeServicesConfig {

    private Api nbp;

    public Api getNbp() {
        return nbp;
    }

    public void setNbp(final Api nbp) {
        this.nbp = nbp;
    }

    public static class Api {

        private String baseUrl;

        private String uri;

        public String getBaseUrl() {
            return baseUrl;
        }

        public void setBaseUrl(final String baseUrl) {
            this.baseUrl = baseUrl;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(final String uri) {
            this.uri = uri;
        }
    }

}
