import org.junit.Test;

import static org.junit.Assert.*;

public class Vector3Tests {
    private Vector3 v1 = new Vector3(1.0, 1.0, 1.0);
    private Vector3 v2 = new Vector3(134.1, 2.5, 9.8);
    private Vector3 v3 = new Vector3(2.1, -0.35, 0.0);

    @Test
    public void times() {
        assertEquals(new Vector3(0.0, 0.0, 0.0), v1.times(0.0));
        assertEquals(new Vector3(3365.91, 62.75, 245.98), v2.times(25.1));
        assertEquals(new Vector3(-2.73, 0.454, -0.0), v3.times(-1.3));
        assertEquals(new Vector3(1.341, 0.025, 0.098), v2.times(0.01));
    }

    @Test
    public void plus() {
        assertEquals(new Vector3(135.1, 3.5, 10.8), v1.plus(v2));
        assertEquals(new Vector3(136.2, 2.15, 9.8), v2.plus(v3));
        assertEquals(new Vector3(3.1, 0.65, 1.0), v3.plus(v1));
    }

    @Test
    public void minus() {
        assertEquals(new Vector3(-133.1, -1.5, -8.8), v1.minus(v2));
        assertEquals(new Vector3(132.0, 2.85, 9.8), v2.minus(v3));
        assertEquals(new Vector3(1.1, -1.35, -1.0), v3.minus(v1));
    }

    @Test
    public void length() {
        assertEquals(1.732, v1.length(), 0.001);
        assertEquals(134.480, v2.length(), 0.001);
        assertEquals(2.128, v3.length(), 0.001);
    }
}
