import org.junit.Test;

import static org.junit.Assert.*;

public class Tests {
    private Quaternion q1 = new Quaternion(1.0, 1.0, 1.0, 1.0); //1 + 1i + 1j + 1k
    private Quaternion q2 = new Quaternion(0.2, 134.1, 2.5, 9.8); //0.2 + 134.1i + 2.5j + 9.8k
    private Quaternion q3 = new Quaternion(-10, 2.1, -0.35, 0.0); //-10 + 2.1i - 0.35j + 0.0k

    private Vector3 v1 = new Vector3(1.0, 1.0, 1.0);
    private Vector3 v2 = new Vector3(134.1, 2.5, 9.8);
    private Vector3 v3 = new Vector3(2.1, -0.35, 0.0);

    @Test
    public void times() {
        //Quaternion * scalar
        assertEquals(new Quaternion(0.0, 0.0, 0.0, 0.0), q1.times(0.0));
        assertEquals(new Quaternion(5.0200000000000005, 3365.91, 62.75, 245.98000000000002), q2.times(25.1));
        assertEquals(new Quaternion(13, -2.7300000000000004, 0.45499999999999996, -0.0), q3.times(-1.3)); // "-1.3 * 0.0 = -0.0" is it correct?
        assertEquals(new Quaternion(0.002, 1.341, 0.025, 0.098), q2.times(0.01));

        //Quaternion * quaternion
        assertEquals(new Quaternion(-146.20000000000002, 141.6, 126.99999999999999127, -121.6), q1.times(q2));
        assertEquals(new Quaternion(-146.20000000000002, 126.99999999999999, -121.6, 141.6), q2.times(q1));
        assertEquals(new Quaternion(-282.735, -1337.1499999999999, -4.489999999999998, -150.185), q2.times(q3));

        //Vector * scalar
        assertEquals(new Vector3(0.0, 0.0, 0.0), v1.times(0.0));
        assertEquals(new Vector3(3365.91, 62.75, 245.98000000000002), v2.times(25.1));
        assertEquals(new Vector3(-2.7300000000000004, 0.45499999999999996, -0.0), v3.times(-1.3));
        assertEquals(new Vector3(1.341, 0.025, 0.098), v2.times(0.01));
    }

    @Test
    public void conjugate() {
        assertEquals(new Quaternion(1.0, -1.0, -1.0, -1.0), q1.conjugate());
        assertEquals(new Quaternion(0.2, -134.1, -2.5, -9.8), q2.conjugate());
        assertEquals(new Quaternion(-10, -2.1, 0.35, -0.0), q3.conjugate());
    }

    @Test
    public void plus() {
        assertEquals(new Quaternion(1.2, 135.1, 3.5, 10.8), q1.plus(q2));
        assertEquals(new Quaternion(-9.8, 136.2, 2.15, 9.8), q2.plus(q3));
        assertEquals(new Quaternion(-9.0, 3.1, 0.65, 1.0), q3.plus(q1));

        assertEquals(new Vector3(135.1, 3.5, 10.8), v1.plus(v2));
        assertEquals(new Vector3(136.2, 2.15, 9.8), v2.plus(v3));
        assertEquals(new Vector3(3.1, 0.65, 1.0), v3.plus(v1));
    }

    @Test
    public void minus() {
        assertEquals(new Quaternion(0.8, -133.1, -1.5, -8.8), q1.minus(q2));
        assertEquals(new Quaternion(10.2, 132.0, 2.85, 9.8), q2.minus(q3));
        assertEquals(new Quaternion(-11.0, 1.1, -1.35, -1.0), q3.minus(q1));

        assertEquals(new Vector3(-133.1, -1.5, -8.8), v1.minus(v2));
        assertEquals(new Vector3(132.0, 2.85, 9.8), v2.minus(v3));
        assertEquals(new Vector3(1.1, -1.35, -1.0), v3.minus(v1));
    }

    @Test
    public void norm() {
        //Quaternion.norm
        assertEquals(2.0, q1.norm(), 0.0);
        assertEquals(134.48100237580027, q2.norm(), 0.0);
        assertEquals(10.2241136535154, q3.norm(), 0.0);

        //Vector3.length
        assertEquals(1.7320508075688772, v1.length(), 0.0);
        assertEquals(134.48085365582716, v2.length(), 0.0);
        assertEquals(2.1289668856043766, v3.length(), 0.0);
    }

    @Test
    public void inverse() {
        assertEquals(new Quaternion(0.25, -0.25, -0.25, -0.25), q1.inverse());
        assertEquals(new Quaternion(1.1058802973048592E-5, -0.00741492739342908, -1.382350371631074E-4, -5.41881345679381E-4), q2.inverse());
        assertEquals(new Quaternion(-0.09566402793389617, -0.020089445866118195, 0.0033482409776863656, 0.0), q3.inverse());
    }

    @Test
    public void normalize() {
        assertEquals(new Quaternion(1.0/7.0, 4.0/7.0, 4.0/7.0, -4.0/7.0), new Quaternion(1.0, 4.0, 4.0, -4.0).normalize());
        assertEquals(new Quaternion(0.5, 0.5, 0.5, 0.5), q1.normalize());
        assertEquals(new Quaternion(0.0014871989088920548, 0.9971668684121228, 0.018589986361150685, 0.07287274653571069), q2.normalize());
        assertEquals(new Quaternion(-0.9780798941492262, 0.20539677777133752, -0.03423279629522292, 0.0), q3.normalize());
    }

    @Test
    public void getScalar() {
        assertEquals(1.0, q1.getScalar(), 0.0);
        assertEquals(0.2, q2.getScalar(), 0.0);
        assertEquals(-10, q3.getScalar(), 0.0);
    }

    @Test
    public void getAxis() {
        assertEquals(new Vector3(1.0, 1.0, 1.0), q1.getAxis());
        assertEquals(new Vector3(134.1, 2.5, 9.8), q2.getAxis());
        assertEquals(new Vector3(2.1, -0.35, 0.0), q3.getAxis());
    }

    @Test
    public void getScalarPart() {
        assertEquals(new Quaternion(1.0, 0.0, 0.0, 0.0), q1.getScalarPart());
        assertEquals(new Quaternion(0.2, 0.0, 0.0, 0.0), q2.getScalarPart());
        assertEquals(new Quaternion(-10, 0.0, 0.0, 0.0), q3.getScalarPart());
    }

    @Test
    public void getVectorPart() {
        assertEquals(new Quaternion(0.0, 1.0, 1.0, 1.0), q1.getVectorPart());
        assertEquals(new Quaternion(0.0, 134.1, 2.5, 9.8), q2.getVectorPart());
        assertEquals(new Quaternion(0.0, 2.1, -0.35, 0.0), q3.getVectorPart());
    }

    @Test
    public void build() {
        assertEquals(new Quaternion(0.8775825618903728, 0.479425538604203, 0.479425538604203, 0.479425538604203),
                Quaternion.build(1.0, 1.0, 1.0, 1.0));
        assertEquals(new Quaternion(0.9950041652780258, 13.387661172339655, 0.2495835416170704, 0.978367483138916),
                Quaternion.build(0.2, 134.1, 2.5, 9.8));
        assertEquals(new Quaternion(0.28366218546322625, 2.0137409767925907, -0.33562349613209846, 0.0),
                Quaternion.build(-10.0, 2.1, -0.35, 0.0));

        assertEquals(new Quaternion(0.8775825618903728, new Vector3(0.479425538604203, 0.479425538604203, 0.479425538604203)),
                Quaternion.build(1.0, new Vector3(1.0, 1.0, 1.0)));
        assertEquals(new Quaternion(0.9950041652780258, new Vector3(13.387661172339655, 0.2495835416170704, 0.978367483138916)),
                Quaternion.build(0.2, new Vector3(134.1, 2.5, 9.8)));
        assertEquals(new Quaternion(0.28366218546322625, new Vector3(2.0137409767925907, -0.33562349613209846, 0.0)),
                Quaternion.build(-10.0, new Vector3(2.1, -0.35, 0.0)));
    }

    @Test
    public void getAngle() {
        //assertEquals(1.0, new Quaternion(0.8775825618903728, 0.479425538604203, 0.479425538604203, 0.479425538604203).getAngle(), 0.0);
        assertEquals(0.2, new Quaternion(0.9950041652780258, 13.387661172339655, 0.2495835416170704, 0.978367483138916).getAngle(), 0.0);
    }
}
