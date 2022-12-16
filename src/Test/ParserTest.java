/*
package Test;


import Stages.Parser;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    Parser p = new Parser();

    @Test
    void readIn() {
        p.resetFile("testFile.txt");
        String[] strings = new String[]{"je", "suis","une","mite", "en", "pull-over"};
        assertArrayEquals(p.readIn("testFile.txt"), strings);
    }

    @Test
    void writeIn() {
        p.resetFile("testFile.txt");
        String[] strings = new String[]{"je", "suis","une","#mite", "en", "pull-over"};
        p.writeIn("testFile.txt", "mite");
        assertArrayEquals(p.readIn("testFile.txt"), strings);
    }

    @Test
    void resetFile() {
        String[] strings = new String[]{"je", "suis","une","mite", "en", "pull-over"};
        p.writeIn("testFile.txt", "je");
        p.writeIn("testFile.txt", "suis");
        p.writeIn("testFile.txt", "une");
        p.writeIn("testFile.txt", "en");
        p.writeIn("testFile.txt", "pull-over");
        p.resetFile("testFile.txt");
        assertArrayEquals(p.readIn("testFile.txt"), strings);
    }
}
 */