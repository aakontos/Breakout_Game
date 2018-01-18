import java.util.*;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * Class to manage the Ball
 * and all of its properties
 */


public class Ball extends Breakout {
    public static final String BALL_IMG = "images/ball.gif"; //CHANGE LATER
    private Integer num_lives = 3;
    private double X_DIR;
    private double Y_DIR;
    private int SPEED;
    private ImageView img_view;
    private Ball my_ball;


    /**  Constructor for the Ball
     *  speed & image as parameters
     *  to create the ball
     */
    public Ball(Image img, int speed) {
        X_DIR = 1;
        Y_DIR = 1;
        img_view = new ImageView(img);
        SPEED = speed;
    }

    /**
     * function to create the ball
     */
    public Ball create_ball(ImageView myImage, int speed) {
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(BALL_IMG));
        my_ball = new Ball (image, speed);
        my_ball.img_view.setFitHeight();
        my_ball.img_view.setFitWidth();
        //Create method to set ball in middle and figure out background image to figure out the ball's location
        return my_ball;
    }

    /**
     * functions to return existing values of the ball
     */

    public double get_x_direction() {
        return this.X_DIR;
    }

    public double get_y_direction() {
        return this.Y_DIR;
    }

    public ImageView get_ball_imageview() {
        return this.img_view;
    }

    public Integer get_num_lives() {
        return this.num_lives;
    }

    /**
     * functions to change the values of the ball
     * @param speed
     */
    public void change_ball_speed(int speed) {
        this.SPEED = speed;
    }

    public void change_x_direction() {
        this.X_DIR = this.X_DIR * -1;
    }

    public void change_y_direction() {
        this.Y_DIR = this.Y_DIR * -1;
    }

    public void add_life() {
        this.num_lives++;
    }

    /**
     * functions to adjust position of ball and check for collisions on walls, bricks, & paddle
     */

    public



}
