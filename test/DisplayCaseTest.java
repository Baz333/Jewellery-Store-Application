import com.example.demo.DisplayCase;
import com.example.demo.DisplayTray;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DisplayCaseTest {

    private DisplayCase dc1, dc2;
    private DisplayTray dt;

    @BeforeEach
    void setUp() {

        dc1 = new DisplayCase(null, null, "101", "freestanding", true);
        dc2 = new DisplayCase(null, null, "102", "wall-mounted", false);
        dt = new DisplayTray(null, null, dc1, "A01", "black", 5, 5, 5);

        dc1.setNextCase(dc2);
        dt.setNextTray(dt.getParent().getHead());
        dt.getParent().setHead(dt);

    }

    @AfterEach
    void tearDown() {

        dc1 = dc2 = null;
        dt = null;

    }

    @Nested
    class Getters {

        @Test
        void getHead() {

            assertEquals(dc1.getHead(), dt);
            assertNull(dc2.getHead());

        }

        @Test
        void getNextCase() {

            assertEquals(dc1.getNextCase(), dc2);
            assertNull(dc2.getNextCase());

        }

        @Test
        void getUID() {

            assertEquals(dc1.getUid(), "101");
            assertEquals(dc2.getUid(), "102");

        }

        @Test
        void getType() {

            assertEquals(dc1.getType(), "freestanding");
            assertEquals(dc2.getType(), "wall-mounted");

        }

        @Test
        void isLit() {

            assertTrue(dc1.isLit());
            assertFalse(dc2.isLit());

        }

    }

    @Nested
    class Setters {

        @Test
        void setHead() {

            assertEquals(dc1.getHead(), dt);
            assertNull(dc2.getHead());

            dc1.setHead(null);
            dc2.setHead(dt);

            assertNull(dc1.getHead());
            assertEquals(dc2.getHead(), dt);

        }

        @Test
        void setNextCase() {

            assertEquals(dc1.getNextCase(), dc2);
            assertNull(dc2.getNextCase());

            dc1.setNextCase(null);
            dc2.setNextCase(dc1);

            assertNull(dc1.getNextCase());
            assertEquals(dc2.getNextCase(), dc1);

        }

        @Test
        void setUID() {

            assertEquals(dc1.getUid(), "101");
            assertEquals(dc2.getUid(), "102");

            dc1.setUid("103");
            dc2.setUid("10");

            assertEquals(dc1.getUid(), "103");
            assertEquals(dc2.getUid(), "102");

            dc2.setUid("1001");

            assertEquals(dc2.getUid(), "100");

        }

        @Test
        void setType() {

            assertEquals(dc1.getType(), "freestanding");
            assertEquals(dc2.getType(), "wall-mounted");

            dc1.setType("wall-mounted");
            dc2.setType("freestanding");

            assertEquals(dc1.getType(), "wall-mounted");
            assertEquals(dc2.getType(), "freestanding");

            dc2.setType("wallmounted");
            assertEquals(dc2.getType(), "freestanding");

        }

        @Test
        void setLit() {

            assertTrue(dc1.isLit());
            assertFalse(dc2.isLit());

            dc1.setLit(false);
            dc2.setLit(true);

            assertFalse(dc1.isLit());
            assertTrue(dc2.isLit());

        }

    }

    @Test
    void numberOfDisplayTrays() {

        assertEquals(dc1.numberOfDisplayTrays(), 1);
        assertEquals(dc2.numberOfDisplayTrays(), 0);

    }

}
