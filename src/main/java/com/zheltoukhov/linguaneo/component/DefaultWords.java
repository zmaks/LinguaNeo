package com.zheltoukhov.linguaneo.component;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

@Component
public class DefaultWords {
    private static Queue<String> defaultWords;

    @PostConstruct
    private void init(){
        defaultWords = new LinkedList<String>();
        defaultWords.add("дом");
        defaultWords.add("окно");
        defaultWords.add("компьютер");
        defaultWords.add("стол");
        defaultWords.add("крыша");
        defaultWords.add("экран");
        defaultWords.add("ручка");
        defaultWords.add("карандаш");
        defaultWords.add("вода");
        defaultWords.add("огонь");
        defaultWords.add("земля");
        defaultWords.add("воздух");
    }

    public static String getWord(){
        String word = defaultWords.poll();
        defaultWords.add(word);
        return word;
    }
}
