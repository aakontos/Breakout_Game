import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

//Class to specify generic brick

/**
 * Class to manage the properties of generic bricks
 */

public class Bricks extends Breakout {
    private int WIDTH;
    private int HEIGHT;
    private int hits_to_break;
    private boolean powerup_brick;
    private String brickType;
    private ImageView brick;

    /**
     * Constructor for the generic brick
     * @param brick_type
     * @param height
     * @param width
     * @param num_hits
     * @param has_powerup
     */

    public Bricks(String brick_type, int height, int width, int num_hits, boolean has_powerup) {
        brickType = brick_type;
        HEIGHT = height;
        WIDTH = width;
        hits_to_break = num_hits;
        powerup_brick = has_powerup;
        Image img_brick = new Image(getClass().getClassLoader().getResourceAsStream(brickType)); //Need to adjust based on different bricks
        brick = new ImageView(img_brick);
        brick.setFitWidth(); // i need to read up on setFitWidth and Height
        brick.setFitHeight();// ^^^
    }

    /**
     * For the remainder of this section any variable with BHTB will stand for "brick hits to break"
     */

    /**
     * methods to check and return existing brick values
     */

    public int check_BHTB() {
        return this.hits_to_break;
    }

    public String check_brick_type() {
        return this.brickType;
    }

    public boolean brick_has_powerup() {
        return this.powerup_brick;
    }

    public ImageView get_brick_imageview() {
        return this.brick;
    }


    /**
     * methods to adjust existing brick values
     */

    public void reduce_BHTB() {
        this.hits_to_break--;
    }

    public void add_powerup() {
        this.powerup_brick = true;
    }

    /**
     * method to place bricks
     */

    public void place_brick(double x, double y) {
        this.brick.setX(x);
        this.brick.setY(y);
    }

}
