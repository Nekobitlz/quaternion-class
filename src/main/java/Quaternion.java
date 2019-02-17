//information taken from https://habr.com/ru/post/426863/
//http://www.rossprogrammproduct.com/translations/Matrix%20and%20Quaternion%20FAQ.htm#Q56

import java.util.Objects;

import static java.lang.Math.*;

/**
 * Quaternion implementation on Java. Supports many standard operations.
 *
 * @author Andrey Matveets
 */
public class Quaternion {
    //Q = S + Xi + Yj + Zk;
    private double s, x, y, z;

    /**
     * Constructs and initializes a quaternion with 4 input parameters
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     * @param s the scalar component
     */
    public Quaternion(double s, double x, double y, double z) {
        this.s = s;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Constructs and initializes a quaternion with scalar and axis
     *
     * @param vector the quaternion axis
     * @param s the scalar component
     */
    public Quaternion(double s, Vector3 vector) {
        this.s = s;
        this.x = vector.x;
        this.y = vector.y;
        this.z = vector.z;
    }

    /**
     * Performs multiplication of quaternion and scalar number
     *
     * @param scalar a constant
     * @return quaternion, which is the result of multiplication of quaternion and scalar
     */
    public Quaternion times(double scalar) {
        return new Quaternion(scalar * s, scalar * x, scalar * y, scalar * z);
    }

    /**
     * Performs multiplication of two quaternions
     *
     * @param other another quaternion involved in multiplication
     * @return quaternion, which is the result of multiplication of quaternion and other quaternion
     */
    public Quaternion times(Quaternion other) {
        double newS = s * other.s - x * other.x - y * other.y - z * other.z;
        double newX = s * other.x + other.s * x + y * other.z - other.y * z;
        double newY = s * other.y + other.s * y + z * other.x - other.z * x;
        double newZ = s * other.z + other.s * z + x * other.y - other.x * y;

        return new Quaternion(newS, newX, newY, newZ);
    }

    /**
     * Gets the conjugate of this quaternion
     *
     * @return the conjugate quaternion
     */
    public Quaternion conjugate() {
        return new Quaternion(s, -x, -y, -z);
    }

    /**
     * Performs the addition of two quaternions
     *
     * @param other another quaternion involved in addition
     * @return quaternion which is the result of addition
     */
    public Quaternion plus(Quaternion other) {
        return new Quaternion(s + other.s, x + other.x, y + other.y, z + other.z);
    }

    /**
     * Performs subtraction of two quaternions
     *
     * @param other another quaternion involved in subtraction
     * @return quaternion which is the result of subtraction
     */
    public Quaternion minus(Quaternion other) {
        return new Quaternion(s - other.s, x - other.x, y - other.y, z - other.z);
    }

    /**
     * Calculates the norm (module) of thus quaternion
     *
     * @return the norm of this quaternion
     */
    public double norm() {
        return sqrt(sqr(s) + sqr(x) + sqr(y) + sqr(z));
    }

    /**
     * Calculates the inverse quaternion
     *
     * @return inverse quaternion
     */
    public Quaternion inverse() {
        return this.conjugate().times(1 / sqr(this.norm()));
    }

    /**
     * Performs quaternion normalization
     *
     * @return normalized quaternion
     */
    public Quaternion normalize() {
        return this.times(1 / this.norm());
    }

    /**
     * Gets the scalar part of the quaternion
     *
     * @return scalar part of quaternion
     */
    public Quaternion getScalarPart() {
        return new Quaternion(s, 0.0, 0.0, 0.0);
    }

    /**
     * Gets the vector part of the quaternion
     *
     * @return vector part of quaternion
     */
    public Quaternion getVectorPart() {
        return new Quaternion(0.0, x, y, z);
    }

    /**
     * Gets the scalar component of the quaternion
     *
     * @return scalar component of the quaternion
     */
    public double getScalar() {
        return s;
    }

    /**
     * Gets the quaternion axis
     *
     * @return the quaternion axis
     */
    public Vector3 getAxis() {
        return new Vector3(x, y, z);
    }

    /**
     * Gets the angle of rotation of the quaternion
     *
     * @return angle of rotation of the quaternion
     */
    public double getAngle() {
        return acos(s) * 2;
    }

    /**
     * Builds a quaternion by angle and XYZ-coordinates
     *
     * @param angle rotation angle of quaternion
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     * @return quaternion built by angle and coordinates
     */
    public static Quaternion build(double angle, double x, double y, double z) {
        double scalar = cos(angle / 2.0);
        double sinAngle = sin(angle / 2.0);

        double newX = x * sinAngle;
        double newY = y * sinAngle;
        double newZ = z * sinAngle;

        return new Quaternion(scalar, newX, newY, newZ);
    }

    /**
     * Builds a quaternion by angle and axis
     *
     * @param angle rotation angle of quaternion
     * @param vector the quaternion axis
     * @return quaternion built by angle and axis
     */
    public static Quaternion build(double angle, Vector3 vector) {
        double scalar = cos(angle / 2.0);
        double sinAngle = sin(angle / 2.0);

        double newX = vector.x * sinAngle;
        double newY = vector.y * sinAngle;
        double newZ = vector.z * sinAngle;

        return new Quaternion(scalar, new Vector3(newX, newY, newZ));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Quaternion that = (Quaternion) o;

        return Double.compare(that.s, s) == 0 &&
                Double.compare(that.x, x) == 0 &&
                Double.compare(that.y, y) == 0 &&
                Double.compare(that.z, z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(s, x, y, z);
    }

    /**
     * Gets a string representation of this quaternion for display purposes
     *
     * @return A string contains information about this quaternion
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (s != 0.0) {
            sb.append(s);
        }

        addVector(sb, x, 'i');
        addVector(sb, y, 'j');
        addVector(sb, z, 'k');

        return sb.toString();
    }

    /*
      auxiliary function to convert complex components to a string
    */
    private void addVector(StringBuilder sb, double vector, char index) {
        if (vector == 0.0) {
            return;
        }

        //if number will be the first in the line then we don't put any sign
        if (!sb.toString().isEmpty()) {
            if (vector > 0.0) {
                sb.append(" + ").append(vector);
            } else {
                sb.append(" - ").append(vector * -1.0); //"* -1.0" just to remove minus
            }
        } else {
            sb.append(" ").append(vector);
        }

        sb.append(index);
    }

    /*
       auxiliary function for calculating the square
     */
    private static double sqr(double digit) {
        return digit * digit;
    }
}
