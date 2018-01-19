public class Strong_Brick extends Brick {


    /**
     * Class that creates a Strong_Brick using
     * the super() method in java to invoke the parent class Brick
     *
     * @param brick_type
     * @param height
     * @param width
     * @param num_hits
     * @param has_powerup
     */

    public Strong_Brick(String brick_type, int height, int width, int num_hits, boolean has_powerup, int brick_speed) {
        super(brick_type, height, width, num_hits, has_powerup, brick_speed);
    }

}
