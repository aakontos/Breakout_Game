public class Normal_Brick extends Brick {


    /**
     * Class to create a normal brick that calls
     * the method super() to invoke the parent class Brick
     *
     * The values for the brick will be specified in Breakout.java
     *
     * @param brick_type
     * @param height
     * @param width
     * @param num_hits
     * @param has_powerup
     */
    public Normal_Brick(String brick_type, int height, int width, int num_hits, boolean has_powerup) {
        super(brick_type, height, width, num_hits, has_powerup);
    }
}
