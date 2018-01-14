import java.util.*;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Ball {
    public static final String BALL_IMG = "";


    private double X_DIR;
    private double Y_DIR;
    private int SPEED;
    private ImageView img_view;


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
}
