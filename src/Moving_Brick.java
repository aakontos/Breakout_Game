
public class Moving_Brick extends Brick {

    /**
     * Class to create a moving brick that calls the method super()
     * to invoke the parent class Brick
     * @param brick_type
     * @param height
     * @param width
     * @param num_hits
     * @param has_powerup
     * @param brick_speed
     */
    public Moving_Brick(String brick_type, int height, int width, int num_hits, boolean has_powerup, int brick_speed) {
        super(brick_type, height, width, num_hits, has_powerup, brick_speed);
    }

}
