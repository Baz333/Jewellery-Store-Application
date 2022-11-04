import com.example.demo.DisplayCase;
import com.example.demo.DisplayTray;
import com.example.demo.JewelleryItem;
import com.example.demo.MaterialComponent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MaterialComponentTest {

    private DisplayCase dc;
    private DisplayTray dt;
    private JewelleryItem ji1, ji2;
    private MaterialComponent mc1, mc2;

    @BeforeEach
    void setUp() {

        dc = new DisplayCase(null, null, "101", "freestanding", true);
        dt = new DisplayTray(null, null, dc, "A01", "black", 10, 10, 10);
        ji1 = new JewelleryItem(null, null, dt, "5 inch diameter", "bracelet", "female", "C:\\Users\\bazda\\Desktop\\pics\\downloads\\ezgif-4-f403f98b5f.jpg", 200);
        ji2 = new JewelleryItem(null, null, dt, "gold plated", "watch", "male", "C:\\Users\\bazda\\Desktop\\pics\\downloads\\9r1te0pipg041", 150);
        mc1 = new MaterialComponent(null, "gold", "gold chain", 24, "excellent");
        mc2 = new MaterialComponent(null, "platinum", "link", 12, "good");

        dc.setHead(dt);
        dt.setHead(ji1);
        ji1.setNextItem(ji2);
        ji1.setHead(mc1);
        mc1.setNextComponent(mc2);

    }

    @AfterEach
    void tearDown() {

        dc = null;
        dt = null;
        ji1 = ji2 = null;
        mc1 = mc2 = null;

    }

    @Nested
    class Getters {

        @Test
        void getNextComponent() {

            assertEquals(mc1.getNextComponent(), mc2);
            assertNull(mc2.getNextComponent());

        }

        @Test
        void getName() {

            assertEquals(mc1.getName(), "gold");
            assertEquals(mc2.getName(), "platinum");

        }

        @Test
        void getDesc() {

            assertEquals(mc1.getDesc(), "gold chain");
            assertEquals(mc2.getDesc(), "link");

        }

        @Test
        void getWeight() {

            assertEquals(mc1.getWeight(), 24);
            assertEquals(mc2.getWeight(), 12);

        }

        @Test
        void getQuality() {

            assertEquals(mc1.getQuality(), "excellent");
            assertEquals(mc2.getQuality(), "good");

        }

    }

    @Nested
    class Setters {

        @Test
        void setNextComponent() {

            assertEquals(mc1.getNextComponent(), mc2);
            assertNull(mc2.getNextComponent());

            mc1.setNextComponent(null);
            mc2.setNextComponent(mc1);

            assertNull(mc1.getNextComponent());
            assertEquals(mc2.getNextComponent(), mc1);

        }

        @Test
        void setName() {

            assertEquals(mc1.getName(), "gold");
            assertEquals(mc2.getName(), "platinum");

            mc1.setName("silver");
            mc2.setName("diamond");

            assertEquals(mc1.getName(), "silver");
            assertEquals(mc2.getName(), "diamond");

        }

        @Test
        void setDesc() {

            assertEquals(mc1.getDesc(), "gold chain");
            assertEquals(mc2.getDesc(), "link");

            mc1.setDesc("gold link");
            mc2.setDesc("chain");

            assertEquals(mc1.getDesc(), "gold link");
            assertEquals(mc2.getDesc(), "chain");

        }

        @Test
        void setWeight() {

            assertEquals(mc1.getWeight(), 24);
            assertEquals(mc2.getWeight(), 12);

            mc1.setWeight(12);
            mc2.setWeight(24);

            assertEquals(mc1.getWeight(), 12);
            assertEquals(mc2.getWeight(), 24);

        }

        @Test
        void setQuality() {

            assertEquals(mc1.getQuality(), "excellent");
            assertEquals(mc2.getQuality(), "good");

            mc1.setQuality("fair");
            mc2.setQuality("poor");

            assertEquals(mc1.getQuality(), "fair");
            assertEquals(mc2.getQuality(), "poor");

        }

    }

}
