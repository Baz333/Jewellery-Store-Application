import com.example.demo.DisplayCase;
import com.example.demo.DisplayTray;
import com.example.demo.JewelleryItem;
import com.example.demo.MaterialComponent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JewelleryItemTest {

    private DisplayCase dc;
    private DisplayTray dt1, dt2;
    private JewelleryItem ji1, ji2, ji3;
    private MaterialComponent mc;

    @BeforeEach
    void setUp() {

        dc = new DisplayCase(null, null, "101", "freestanding", true);
        dt1 = new DisplayTray(null, null, dc, "A01", "black", 10, 10, 10);
        dt2 = new DisplayTray(null, null, dc, "A02", "white", 20, 20, 20);
        ji1 = new JewelleryItem(null, null, dt1, "5 inch diameter", "bracelet", "female", "C:\\Users\\bazda\\Desktop\\pics\\downloads\\ezgif-4-f403f98b5f.jpg", 100);
        ji2 = new JewelleryItem(null, null, dt1, "gold plated", "watch", "male", "C:\\Users\\bazda\\Desktop\\pics\\downloads\\9r1te0pipg041", 150);
        ji3 = new JewelleryItem(null, null, dt2, "ruby gem in each earring", "earring", "unisex", "C:\\Users\\bazda\\Desktop\\pics\\downloads\\4051507-winston-abilties-overwatch", 200);
        mc = new MaterialComponent(null, "gold", "gold chain", 24, "excellent");

        dc.setHead(dt1);
        dt1.setNextTray(dt2);
        dt1.setHead(ji1);
        ji1.setNextItem(ji2);
        dt2.setHead(ji3);
        ji1.setHead(mc);

    }

    @AfterEach
    void tearDown() {

        dc = null;
        dt1 = dt2 = null;
        ji1 = ji2 = ji3 = null;

    }

    @Nested
    class Getters {

        @Test
        void getHead() {

            assertEquals(ji1.getHead(), mc);
            assertNull(ji2.getHead());
            assertNull(ji3.getHead());

        }

        @Test
        void getNextItem() {

            assertEquals(ji1.getNextItem(), ji2);
            assertNull(ji2.getNextItem());
            assertNull(ji3.getNextItem());

        }

        @Test
        void getParent() {

            assertEquals(ji1.getParent(), dt1);
            assertEquals(ji2.getParent(), dt1);
            assertEquals(ji3.getParent(), dt2);

        }

        @Test
        void getItemDescription() {

            assertEquals(ji1.getItemDescription(), "5 inch diameter");
            assertEquals(ji2.getItemDescription(), "gold plated");
            assertEquals(ji3.getItemDescription(), "ruby gem in each earring");

        }

        @Test
        void getType() {

            assertEquals(ji1.getType(), "bracelet");
            assertEquals(ji2.getType(), "watch");
            assertEquals(ji3.getType(), "earring");

        }

        @Test
        void getTargetGender() {

            assertEquals(ji1.getTargetGender(), "female");
            assertEquals(ji2.getTargetGender(), "male");
            assertEquals(ji3.getTargetGender(), "unisex");

        }

        @Test
        void getImageURL() {

            assertEquals(ji1.getImageURL(), "C:\\Users\\bazda\\Desktop\\pics\\downloads\\ezgif-4-f403f98b5f.jpg");
            assertEquals(ji2.getImageURL(), "C:\\Users\\bazda\\Desktop\\pics\\downloads\\9r1te0pipg041");
            assertEquals(ji3.getImageURL(), "C:\\Users\\bazda\\Desktop\\pics\\downloads\\4051507-winston-abilties-overwatch");

        }

        @Test
        void getRetailPrice() {

            assertEquals(ji1.getRetailPrice(), 100);
            assertEquals(ji2.getRetailPrice(), 150);
            assertEquals(ji3.getRetailPrice(), 200);

        }

    }

    @Nested
    class Setters {

        @Test
        void setHead() {

            assertEquals(ji1.getHead(), mc);
            assertNull(ji2.getHead());

            ji1.setHead(null);
            ji2.setHead(mc);

            assertNull(ji1.getHead());
            assertEquals(ji2.getHead(), mc);

        }

        @Test
        void setNextItem() {

            assertEquals(ji1.getNextItem(), ji2);
            assertNull(ji2.getNextItem());

            ji1.setNextItem(null);
            ji2.setNextItem(ji1);

            assertNull(ji1.getNextItem());
            assertEquals(ji2.getNextItem(), ji1);

        }

        @Test
        void setParent() {

            assertEquals(ji1.getParent(), dt1);
            assertEquals(ji2.getParent(), dt1);
            assertEquals(ji3.getParent(), dt2);

            ji1.setParent(dt2);
            ji2.setParent(dt2);
            ji3.setParent(dt1);

            assertEquals(ji1.getParent(), dt2);
            assertEquals(ji2.getParent(), dt2);
            assertEquals(ji3.getParent(), dt1);

        }

        @Test
        void setItemDescription() {

            assertEquals(ji1.getItemDescription(), "5 inch diameter");

            ji1.setItemDescription("7 cm radius");

            assertEquals(ji1.getItemDescription(), "7 cm radius");

        }

        @Test
        void setType() {

            assertEquals(ji1.getType(), "bracelet");
            assertEquals(ji2.getType(), "watch");
            assertEquals(ji3.getType(), "earring");

            ji1.setType("ring");
            ji2.setType("other");
            ji3.setType("pendant");

            assertEquals(ji1.getType(), "ring");
            assertEquals(ji2.getType(), "other");
            assertEquals(ji3.getType(), "pendant");

        }

        @Test
        void setTargetGender() {

            assertEquals(ji1.getTargetGender(), "female");
            assertEquals(ji2.getTargetGender(), "male");

            ji1.setTargetGender("male");
            ji2.setTargetGender("female");

            assertEquals(ji1.getTargetGender(), "male");
            assertEquals(ji2.getTargetGender(), "female");

        }

        @Test
        void setImageURL() {

            assertEquals(ji1.getImageURL(), "C:\\Users\\bazda\\Desktop\\pics\\downloads\\ezgif-4-f403f98b5f.jpg");
            assertEquals(ji2.getImageURL(), "C:\\Users\\bazda\\Desktop\\pics\\downloads\\9r1te0pipg041");

            ji1.setImageURL("C:\\Users\\bazda\\Desktop\\pics\\downloads\\9r1te0pipg041");
            ji2.setImageURL("C:\\Users\\bazda\\Desktop\\pics\\downloads\\ezgif-4-f403f98b5f.jpg");

            assertEquals(ji1.getImageURL(), "C:\\Users\\bazda\\Desktop\\pics\\downloads\\9r1te0pipg041");
            assertEquals(ji2.getImageURL(), "C:\\Users\\bazda\\Desktop\\pics\\downloads\\ezgif-4-f403f98b5f.jpg");

        }

        @Test
        void setRetailPrice() {

            assertEquals(ji1.getRetailPrice(), 100);
            assertEquals(ji2.getRetailPrice(), 150);
            assertEquals(ji3.getRetailPrice(), 200);

            ji1.setRetailPrice(300);
            ji2.setRetailPrice(0);
            ji3.setRetailPrice(-1);

            assertEquals(ji1.getRetailPrice(), 300);
            assertEquals(ji2.getRetailPrice(), 150);
            assertEquals(ji3.getRetailPrice(), 200);

        }

    }

    @Test
    void materialsToString() {

        assertEquals(ji1.materialsToString(), "gold (gold chain), 24 carats, excellent\n");
        assertEquals(ji2.materialsToString(), "");
        assertEquals(ji3.materialsToString(), "");

    }

}
