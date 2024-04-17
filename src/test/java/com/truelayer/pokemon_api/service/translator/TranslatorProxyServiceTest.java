package com.truelayer.pokemon_api.service.translator;

import com.truelayer.pokemon_api.service.translator.bean.TranslationBean;
import com.truelayer.pokemon_api.utils.BaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TranslatorProxyServiceTest  extends BaseTest {

    @Mock
    private RestTemplate template;

    @InjectMocks
    private TranslatorProxyService proxyService;

    @AfterEach
    void verifyAfter() {
        Mockito.verifyNoMoreInteractions(template);
    }

    @Test
    void getYodaTranslation_shouldGet() {
        String description = random(String.class);
        TranslationBean mockedResponse = random(TranslationBean.class);
        String uri = "https://api.funtranslations.com/translate/yoda.json?text=" + description;
        when(template.getForObject(uri, TranslationBean.class)).thenReturn(mockedResponse);

        String result = proxyService.getYodaTranslation(description);

        assertThat(result).isEqualTo(mockedResponse.getContents().getTranslated());
        verify(template).getForObject(uri, TranslationBean.class);
    }

    @Test
    void getShakespeareTranslation_shouldGet() {
        String description = random(String.class);
        TranslationBean mockedResponse = random(TranslationBean.class);
        String uri = "https://api.funtranslations.com/translate/shakespeare.json?text=" + description;
        when(template.getForObject(uri, TranslationBean.class)).thenReturn(mockedResponse);

        String result = proxyService.getShakespeareTranslation(description);

        assertThat(result).isEqualTo(mockedResponse.getContents().getTranslated());
        verify(template).getForObject(uri, TranslationBean.class);
    }
}
