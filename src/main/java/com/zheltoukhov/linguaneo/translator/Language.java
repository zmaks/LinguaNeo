package com.zheltoukhov.linguaneo.translator;

import java.util.HashMap;
import java.util.Map;

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

    private static final Map<String, Language> value = new HashMap<String, Language>();

    static {
        for (Language lang : Language.values())
            value.put(lang.toString(), lang);
    }

    public static Language get(String key) {
        return value.get(key);
    }

    @Override
    public String toString() {
        return text;
    }

}
