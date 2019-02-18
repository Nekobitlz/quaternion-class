import org.junit.Test;

import static org.junit.Assert.*;

public class QuaternionTests {
    private double delta = 0.001;

    private Quaternion q1 = new Quaternion(1.0, 1.0, 1.0, 1.0); //1 + 1i + 1j + 1k
    private Quaternion q2 = new Quaternion(0.2, 134.1, 2.5, 9.8); //0.2 + 134.1i + 2.5j + 9.8k
    private Quaternion q3 = new Quaternion(-10, 2.1, -0.35, 0.0); //-10 + 2.1i - 0.35j + 0.0k

    @Test
    public void times() {
        //Quaternion * scalar
        assertEquals(new Quaternion(0.0, 0.0, 0.0, 0.0), q1.times(0.0));
        assertEquals(new Quaternion(5.02, 3365.91, 62.75, 245.98), q2.times(25.1));
        assertEquals(new Quaternion(13.0, -2.73, 0.454, 0.0), q3.times(-1.3));
        assertEquals(new Quaternion(0.002, 1.341, 0.025, 0.098), q2.times(0.01));

        //Quaternion * quaternion
        assertEquals(new Quaternion(-146.2, 141.6, 126.999, -121.6), q1.times(q2));
        assertEquals(new Quaternion(-146.2, 126.999, -121.6, 141.6), q2.times(q1));
        assertEquals(new Quaternion(-282.735, -1337.149, -4.489, -150.185), q2.times(q3));
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
    }

    @Test
    public void minus() {
        assertEquals(new Quaternion(0.8, -133.1, -1.5, -8.8), q1.minus(q2));
        assertEquals(new Quaternion(10.2, 132.0, 2.85, 9.8), q2.minus(q3));
        assertEquals(new Quaternion(-11.0, 1.1, -1.35, -1.0), q3.minus(q1));
    }

    @Test
    public void norm() {
        assertEquals(2.0, q1.norm(), delta);
        assertEquals(134.481, q2.norm(), delta);
        assertEquals(10.224, q3.norm(), delta);
    }

    @Test
    public void inverse() {
        assertEquals(new Quaternion(0.25, -0.25, -0.25, -0.25), q1.inverse());
        assertEquals(new Quaternion(0.00001, -0.00741, -0.00014, -0.00054), q2.inverse());
        assertEquals(new Quaternion(-0.096, -0.02, 0.003, 0.0), q3.inverse());
    }

    @Test
    public void normalize() {
        assertEquals(new Quaternion(1.0/7.0, 4.0/7.0, 4.0/7.0, -4.0/7.0),
                new Quaternion(1.0, 4.0, 4.0, -4.0).normalize());
        assertEquals(new Quaternion(0.5, 0.5, 0.5, 0.5), q1.normalize());
        assertEquals(new Quaternion(0.001, 0.997, 0.018, 0.072), q2.normalize());
        assertEquals(new Quaternion(-0.978, 0.205, -0.034, 0.0), q3.normalize());
    }

    @Test
    public void getScalar() {
        assertEquals(1.0, q1.getScalar(), delta);
        assertEquals(0.2, q2.getScalar(), delta);
        assertEquals(-10, q3.getScalar(), delta);
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
        assertEquals(new Quaternion(0.877, 0.479, 0.479, 0.479),
                Quaternion.build(1.0, 1.0, 1.0, 1.0));
        assertEquals(new Quaternion(0.995, 13.387, 0.249, 0.978),
                Quaternion.build(0.2, 134.1, 2.5, 9.8));
        assertEquals(new Quaternion(0.283, 2.013, -0.335, 0.0),
                Quaternion.build(-10.0, 2.1, -0.35, 0.0));

        assertEquals(new Quaternion(0.877, new Vector3(0.479, 0.479, 0.479)),
                Quaternion.build(1.0, new Vector3(1.0, 1.0, 1.0)));
        assertEquals(new Quaternion(0.995, new Vector3(13.387, 0.249, 0.978)),
                Quaternion.build(0.2, new Vector3(134.1, 2.5, 9.8)));
        assertEquals(new Quaternion(0.283, new Vector3(2.013, -0.335, 0.0)),
                Quaternion.build(-10.0, new Vector3(2.1, -0.35, 0.0)));
    }

    @Test
    public void getAngle() {
        assertEquals(1.002, new Quaternion(0.877, 0.479, 0.479, 0.479)
                .getAngle(), delta);
        assertEquals(0.2, new Quaternion(0.995, 13.387, 0.249, 0.978)
                .getAngle(), delta);
    }
}
