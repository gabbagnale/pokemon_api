package com.truelayer.pokemon_api.service.translator;

import com.truelayer.pokemon_api.service.translator.bean.TranslationBean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class TranslatorProxyService {

    private static final Logger logger = Logger.getLogger(TranslatorProxyService.class.getName());
    private static final String BASE_URL = "https://api.funtranslations.com/translate/";
    private static final String YODA_URL = "yoda.json?text=";
    private static final String SHAKESPEARE_URL = "shakespeare.json?text=";


    private final RestTemplate template;

    public TranslatorProxyService(RestTemplate template) {
        this.template = template;
    }

    public String getYodaTranslation(String description) {
        logger.log(Level.FINE, "Getting yoda translation {0}", description);
        TranslationBean translation = template.getForObject(BASE_URL + YODA_URL + description, TranslationBean.class);
        return translation.getContents().getTranslated();
    }

    public String getShakespeareTranslation(String description) {
        logger.log(Level.FINE, "Getting shakespeare translation {0}", description);
        TranslationBean translation = template.getForObject(BASE_URL + SHAKESPEARE_URL + description, TranslationBean.class);
        return translation.getContents().getTranslated();
    }
}
