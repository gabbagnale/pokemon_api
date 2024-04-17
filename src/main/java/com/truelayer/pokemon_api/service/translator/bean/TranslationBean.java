package com.truelayer.pokemon_api.service.translator.bean;

public class TranslationBean {
    private ContentBean contents;

    public TranslationBean() {
    }

    public TranslationBean(ContentBean contents) {
        this.contents = contents;
    }

    public ContentBean getContents() {
        return contents;
    }
}
