import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

/**
 * The implementation of the three-dimensional vector in Java.
 * Required to describe axis in quaternion.{@link Quaternion}
 *
 * @author Andrey Matveets
 */
public class Vector3 {
    private double x, y, z;

    /**
     * Constructs and initializes axis with 3 input parameters
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     */
    public Vector3(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Gets the x coordinate
     *
     * @return x coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the y coordinate
     *
     * @return y coordinate
     */
    public double getY() {
        return y;
    }

    /**
     * Gets the z coordinate
     *
     * @return z coordinate
     */
    public double getZ() {
        return z;
    }

    /**
     * Performs multiplication of vector and scalar number
     *
     * @param scalar a constant
     * @return vector, which is the result of multiplication of vector and scalar
     */
    public Vector3 times(double scalar) {
        return new Vector3(scalar * x, scalar * y, scalar * z);
    }

    /**
     * Performs the addition of two vectors
     *
     * @param other another vector involved in addition
     * @return vector which is the result of addition
     */
    public Vector3 plus(Vector3 other) {
        return new Vector3(x + other.x, y + other.y, z + other.z);
    }

    /**
     * Performs subtraction of two vectors
     *
     * @param other another vector involved in subtraction
     * @return vector which is the result of subtraction
     */
    public Vector3 minus(Vector3 other) {
        return new Vector3(x - other.x, y - other.y, z - other.z);
    }

    /**
     * Returns the length of this vector.
     *
     * @return the length of this vector
     */
    public double length() {
        return sqrt(x * x + y * y + z * z);
    }

    @Override
    public boolean equals(Object o) {
        double delta = 0.001;

        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Vector3 that = (Vector3) o;

        return abs(x - that.x) < delta &&
                abs(y - that.y) < delta &&
                abs(z - that.z) < delta;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}
