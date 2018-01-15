import java.util.*;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Random;


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
