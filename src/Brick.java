import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

//Class to specify generic brick

/**
 * Class to manage the properties of generic bricks
 */

public class Brick extends Breakout {
    private int WIDTH;
    private int HEIGHT;
    private int hits_to_break;
    private boolean powerup_brick;
    private String brickType;
    private ImageView brick;
    private int BRICK_SPEED;

    /**
     * Constructor for the generic brick
     * @param brick_type
     * @param height
     * @param width
     * @param num_hits
     * @param has_powerup
     */

    public Brick(String brick_type, int height, int width, int num_hits, boolean has_powerup, int brick_speed) {
        brickType = brick_type;
        HEIGHT = height;
        WIDTH = width;
        hits_to_break = num_hits;
        powerup_brick = has_powerup;
        BRICK_SPEED = brick_speed;
        Image img_brick = new Image(getClass().getClassLoader().getResourceAsStream("images/"+brickType+"_brick.gif")); //Need to adjust based on different bricks
        brick = new ImageView(img_brick);
        brick.setFitWidth(WIDTH); //
        brick.setFitHeight(HEIGHT);//
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

    /**
     * Methods to check collision for the moving brick
     */

    public Brick check_brick_collision(double elapsedTime) {
        if(this.get_brick_imageview().getBoundsInParent().getMaxX() >= X_SIZE || this.get_brick_imageview().getX() <= 0) {
            this.BRICK_SPEED = this.BRICK_SPEED * -1;
        }
        set_brick_pos(elapsedTime);
        return this;
    }

    public void set_brick_pos(double elapsedTime) {
        this.get_brick_imageview().setX(this.get_brick_imageview().getX() + this.BRICK_SPEED * elapsedTime);
    }

    /**
     * method to copy bricks for placing them into an ArrayList
     * @return
     */
    public Brick copy() {
        return new Brick(this.brickType, this.HEIGHT, this.WIDTH, this.hits_to_break, this.powerup_brick, this.BRICK_SPEED);
    }

}
