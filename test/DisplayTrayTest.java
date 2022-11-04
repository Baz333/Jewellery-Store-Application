import com.example.demo.DisplayCase;
import com.example.demo.DisplayTray;
import com.example.demo.JewelleryItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DisplayTrayTest {

    DisplayCase dc1, dc2;
    DisplayTray dt1, dt2, dt3, dt4;
    JewelleryItem ji;

    @BeforeEach
    void setUp() {

        dc1 = new DisplayCase(null, null, "101", "freestanding", true);
        dc2 = new DisplayCase(null, null, "102", "wall-mounted", false);
        dt1 = new DisplayTray(null, null, dc1, "A01", "black", 10, 10, 10);
        dt2 = new DisplayTray(null, null, dc1, "A02", "white", 20, 20, 20);
        dt3 = new DisplayTray(null, null, dc1, "A03", "green", 30, 30, 30);
        dt4 = new DisplayTray(null, null, dc2, "B01", "other", 40, 40, 40);
        ji = new JewelleryItem(null, null, dt1, "5 inch diameter", "bracelet", "female", "C:\\Users\\bazda\\Desktop\\pics\\downloads\\ezgif-4-f403f98b5f.jpg", 100);

        dc1.setNextCase(dc2);
        dc1.setHead(dt1);
        dt1.setNextTray(dt2);
        dt2.setNextTray(dt3);
        dc2.setHead(dt4);
        dt1.setHead(ji);

    }

    @AfterEach
    void tearDown() {

        dc1 = dc2 = null;
        dt1 = dt2 = dt3 = dt4 = null;
        ji = null;

    }

    @Nested
    class Getters {

        @Test
        void getHead() {

            assertEquals(dt1.getHead(), ji);
            assertNull(dt2.getHead());
            assertNull(dt3.getHead());
            assertNull(dt4.getHead());

        }

        @Test
        void getNextTray() {

            assertEquals(dt1.getNextTray(), dt2);
            assertEquals(dt2.getNextTray(),dt3);
            assertNull(dt3.getNextTray());
            assertNull(dt4.getNextTray());

        }

        @Test
        void getParent() {

            assertEquals(dt1.getParent(), dc1);
            assertEquals(dt2.getParent(), dc1);
            assertEquals(dt3.getParent(), dc1);
            assertEquals(dt4.getParent(), dc2);

        }

        @Test
        void getUID() {

            assertEquals(dt1.getUid(), "A01");
            assertEquals(dt2.getUid(), "A02");
            assertEquals(dt3.getUid(), "A03");
            assertEquals(dt4.getUid(), "B01");

        }

        @Test
        void getInlayColour() {

            assertEquals(dt1.getInlayColour(), "black");
            assertEquals(dt2.getInlayColour(), "white");
            assertEquals(dt3.getInlayColour(), "green");
            assertEquals(dt4.getInlayColour(), "other");

        }

        @Test
        void getDimensions() {

            assertArrayEquals(dt1.getDimensions(), new int[]{10, 10, 10});
            assertArrayEquals(dt2.getDimensions(), new int[]{20, 20, 20});
            assertArrayEquals(dt3.getDimensions(), new int[]{30, 30, 30});
            assertArrayEquals(dt4.getDimensions(), new int[]{40, 40, 40});

        }

    }

    @Nested
    class Setters {

        @Test
        void setHead() {

            assertEquals(dt1.getHead(), ji);
            assertNull(dt2.getHead());

            dt1.setHead(null);
            dt2.setHead(ji);

            assertNull(dt1.getHead());
            assertEquals(dt2.getHead(), ji);

        }

        @Test
        void setNextTray() {

            assertEquals(dt1.getNextTray(), dt2);
            assertEquals(dt2.getNextTray(),dt3);
            assertNull(dt3.getNextTray());

            dt3.setNextTray(dt2);
            dt2.setNextTray(dt1);
            dt1.setNextTray(null);

            assertNull(dt1.getNextTray());
            assertEquals(dt2.getNextTray(),dt1);
            assertEquals(dt3.getNextTray(), dt2);

        }

        @Test
        void setParent() {

            assertEquals(dt1.getParent(), dc1);
            assertEquals(dt4.getParent(), dc2);

            dt1.setParent(dc2);
            dt4.setParent(dc1);

            assertEquals(dt1.getParent(), dc2);
            assertEquals(dt4.getParent(), dc1);

        }

        @Test
        void setUID() {

            assertEquals(dt1.getUid(), "A01");
            assertEquals(dt2.getUid(), "A02");
            assertEquals(dt3.getUid(), "A03");

            dt1.setUid("Z01");
            dt2.setUid("A2");
            dt3.setUid("A003");

            assertEquals(dt1.getUid(), "Z01");
            assertEquals(dt2.getUid(), "A02");
            assertEquals(dt3.getUid(), "A00");

        }

        @Test
        void setInlayColour() {

            assertEquals(dt1.getInlayColour(), "black");
            assertEquals(dt2.getInlayColour(), "white");

            dt1.setInlayColour("red");
            dt2.setInlayColour("brown");

            assertEquals(dt1.getInlayColour(), "red");
            assertEquals(dt2.getInlayColour(), "white");

        }

        @Test
        void setDimensions() {

            assertArrayEquals(dt1.getDimensions(), new int[]{10, 10, 10});
            assertArrayEquals(dt2.getDimensions(), new int[]{20, 20, 20});

            dt1.setDimensions(100, 100, 100);
            dt2.setDimensions(-1, 0, 50);

            assertArrayEquals(dt1.getDimensions(), new int[]{100, 100, 100});
            assertArrayEquals(dt2.getDimensions(), new int[]{20, 20, 50});

        }

    }

}
