/*package Test;

import Stages.Inventaire;
import Stages.Parser;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class InventaireTest {

    private Parser p = new Parser();
    private Inventaire i = new Inventaire();

    @Test
    void notSelectAll() throws IOException {
        String[] strings = new String[]{"#object1", "#object2", "#object3"};
        i.modifyInventory(strings);
        i.notSelectAll();
        assertArrayEquals(p.readIn("inventaire.txt"),new String[]{"object1", "object2", "object3"} );
    }

    @Test
    void modifyInventory() throws IOException {
        String[] strings = new String[]{"object1", "object2", "object3"};
        i.modifyInventory(strings);
        assertArrayEquals(p.readIn("inventaire.txt"), strings);
    }

    @Test
    void addInventory() throws IOException {
        String[] strings = new String[]{"object1", "object2", "object3"};
        i.modifyInventory(strings);
        i.addInventory("object4");
        assertArrayEquals(p.readIn("inventaire.txt"), new String[]{"object1", "object2", "object3", "object4"});
    }

    @Test
    void addInventory2() throws IOException {
        String[] strings = new String[]{"", "object2", "object3"};
        i.modifyInventory(strings);
        i.addInventory("object4");
        assertArrayEquals(p.readIn("inventaire.txt"), new String[]{"object4", "object2", "object3"});
    }

    @Test
    void addInventory3() throws IOException {
        String[] strings = new String[]{"", "", "object3"};
        i.modifyInventory(strings);
        i.addInventory("object4");
        assertArrayEquals(p.readIn("inventaire.txt"), new String[]{"object4", "", "object3"});
    }

    @Test
    void addInventory4() throws IOException {
        String[] strings = new String[]{"object1", "", "object3"};
        i.modifyInventory(strings);
        i.addInventory("object2");
        assertArrayEquals(p.readIn("inventaire.txt"), new String[]{"object1", "object2", "object3"});
    }

    @Test
    void resetInventory() throws IOException {
        i.resetInventory();
        assertNull(p.readIn("inventaire.txt"));
    }

    @Test
    void isSelected() throws IOException {
        String[] strings = new String[]{"#object1", "object2", "object3"};
        i.modifyInventory(strings);
        assertTrue(i.isSelected("object1"));
    }

    @Test
    void isSelected2() throws IOException {
        String[] strings = new String[]{"object1", "#object2", "object3"};
        i.modifyInventory(strings);
        assertTrue(i.isSelected("object2"));
    }

    @Test
    void isSelected3() throws IOException {
        String[] strings = new String[]{"object1", "object2", "#object3"};
        i.modifyInventory(strings);
        assertTrue(i.isSelected("object3"));
    }

    @Test
    void isSelected4() throws IOException {
        String[] strings = new String[]{"object1", "#object2", "object3"};
        i.modifyInventory(strings);
        assertFalse(i.isSelected("object1"));
    }

    @Test
    void removeInventory() throws IOException {
        String[] strings = new String[]{"object1", "#object2", "object3"};
        i.modifyInventory(strings);
        i.removeInventory("object1");
        assertArrayEquals(p.readIn("inventaire.txt"), strings);
    }

    @Test
    void removeInventory1() throws IOException {
        String[] strings = new String[]{"object1", "#object2", "object3"};
        i.modifyInventory(strings);
        i.removeInventory("object2");
        assertArrayEquals(p.readIn("inventaire.txt"), new String[]{"object1", "", "object3"});
    }

    @Test
    void removeInventory2() throws IOException {
        String[] strings = new String[]{"#object1", "#object2", "object3"};
        i.modifyInventory(strings);
        i.removeInventory("object2");
        i.removeInventory("object1");
        assertArrayEquals(p.readIn("inventaire.txt"), new String[]{"", "", "object3"});
    }

    @Test
    void removeInventory3() throws IOException {
        String[] strings = new String[]{"object1", "object2", "#object3"};
        i.modifyInventory(strings);
        i.removeInventory("object3");
        assertArrayEquals(p.readIn("inventaire.txt"), new String[]{"object1", "object2"});
    }
}

 */