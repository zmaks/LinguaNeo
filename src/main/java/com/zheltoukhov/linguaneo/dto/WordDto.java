package com.zheltoukhov.linguaneo.dto;

/**
 * Created by Maksim on 11.12.2016.
 */
public class WordDto {
    private String eng;
    private String rus;

    public  WordDto(){}

    public WordDto(String eng, String rus) {
        this.eng = eng;
        this.rus = rus;
    }

    public String getEng() {
        return eng;
    }

    public void setEng(String eng) {
        this.eng = eng;
    }

    public String getRus() {
        return rus;
    }

    public void setRus(String rus) {
        this.rus = rus;
    }
}
