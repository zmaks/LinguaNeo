package com.zheltoukhov.linguaneo.translator;

/**
 * Created by Maksim on 08.12.2016.
 */
public enum Language {
    ENG("en"),
    RUS("ru");

    private final String text;

    private Language(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
