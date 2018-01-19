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
    public static final String BALL_IMG = "images/ball.png"; //CHANGE LATER
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
        my_ball.img_view.setFitHeight(20);
        my_ball.img_view.setFitWidth(20);
        my_ball.reset_ball();
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
        this.set_ball_pos(SECOND_DELAY);
    }

    public void change_y_direction() {
        this.Y_DIR = this.Y_DIR * -1;
        this.set_ball_pos(SECOND_DELAY);
    }

    public void add_life() {
        this.num_lives++;
    }

    /**
     * check collision with the sides of the screen and with the top and bottom
     * take away lives if the ball goes off of the bottom of the screen and reset the game
     * if too many lives lost end the game
     * @param elapsedTime
     * @param my_paddle
     * @param animation
     * @param winner
     * @param loser
     * @param my_bricks
     * @param current_level
     * @param stage
     * @param ball_speed
     * @return
     */
    public Ball my_ball_position(double elapsedTime, ImageView my_paddle, Timeline animation, Text winner, Text loser,
                                 ArrayList<Brick> my_bricks, int current_level, Stage stage, int ball_speed) {
        //check if ball hits sides of the screen
        if (this.img_view.getBoundsInParent().getMaxX() >= X_SIZE || this.img_view.getX() <= 0) {
            X_DIR = X_DIR * -1;
        }
        //check if the ball hits the top of the screen or goes out of the bottom
        else if (this.img_view.getBoundsInParent().getMinY() <= 0 && my_bricks.size() <= number_unbreakable_bricks(my_bricks)) {
            animation.stop();
            Level level = new Level(current_level);
            level.change_level(stage, ball_speed, winner, animation);
        }
        else if (this.img_view.getBoundsInParent().getMaxY() >= Y_SIZE) {
            num_lives--;
            if (num_lives <= 0) {
                loser.setVisible(true);
                animation.stop();
            }
            else {
                this.reset_ball();
                reset_paddle(my_paddle);
                animation.pause();
            }
        }
        else if (this.img_view.getY() <= 0) {
            Y_DIR = Y_DIR * -1;
        }

        check_paddle_collision(my_paddle);
        set_ball_pos(elapsedTime);

        return this;




    }
    //simply just count the number of unbreakable blocks left in the game

    /**
     * method to count the number of unbreakable bricks left on the screen so that a new level may start if they
     * are the only type of brick left
     * @param my_bricks
     * @return
     */
    public int number_unbreakable_bricks(ArrayList<Brick> my_bricks) {
        int count = 0;
        for (Brick brick: my_bricks) {
            if (brick.check_brick_type() == "unbreakable") {
                count++;
            }
        }
        return count;
    }

    /**
     * method to update ball's position throughout the game
     * @param elapsedTime
     */
    public void set_ball_pos(double elapsedTime) {
        this.img_view.setY(this.img_view.getY() - Y_DIR * this.SPEED * elapsedTime);
        this.img_view.setX(this.img_view.getX() + X_DIR * this.SPEED * elapsedTime);
    }

    /**
     * method to reset the paddle back to the middle of the screen (its starting position)
     * @param my_paddle
     */
    private void reset_paddle(ImageView my_paddle) {
        my_paddle.setX(X_SIZE / 2 - my_paddle.getBoundsInLocal().getWidth() / 2); //center the middle of the paddle in the middle of the frame and
        my_paddle.setY(Y_SIZE - 60); // move paddle up a little bit
    }

    /**
     * method to reset the ball back to its original position
     */
    private void reset_ball() {
        this.img_view.setX(X_SIZE / 2 - this.img_view.getBoundsInLocal().getWidth() / 2);
        this.img_view.setY(Y_SIZE - this.img_view.getBoundsInLocal().getWidth() - 70);
    }

    /**
     * method to check the bounds of the paddle and if the ball has collided with it
     * @param my_paddle
     */
    private void check_paddle_collision(ImageView my_paddle) {
        if (this.img_view.getBoundsInLocal().intersects(my_paddle.getBoundsInParent())) {
            change_y_direction();
        }
    }





}
