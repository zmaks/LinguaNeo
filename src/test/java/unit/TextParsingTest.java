package unit;

import com.zheltoukhov.linguaneo.service.TextService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

public class TextParsingTest {

    private TextService textService;
    private String text;

    @Before
    public void init(){
        textService = new TextService();
        text = "dog Кошка д0м      doooooooooooooooooor boooooooooooooooooooooook ok 234134 rock; x АПЕЛЬСИН HappyNewYear 100%     ";
    }

    @Test
    public void parserTest(){
        Set<String> words = textService.parseText(text);
        Assert.assertEquals(6, words.size());
        Assert.assertTrue(words.contains("dog"));
        Assert.assertTrue(words.contains("Кошка"));
        Assert.assertTrue(words.contains("doooooooooooooooooor"));
        Assert.assertTrue(words.contains("АПЕЛЬСИН"));
        Assert.assertTrue(words.contains("HappyNewYear"));
        Assert.assertTrue(words.contains("rock"));
        Assert.assertTrue(!words.contains("boooooooooooooooooooooook"));
        Assert.assertTrue(!words.contains("ok"));
        Assert.assertTrue(!words.contains("234134"));
        Assert.assertTrue(!words.contains("д0м"));
        Assert.assertTrue(!words.contains("rock;"));
        Assert.assertTrue(!words.contains("x"));
        Assert.assertTrue(!words.contains("100%"));
    }

}
