import java.util.*;
import javafx.scene.text.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Breakout extends Application {
    public static final String TITLE = "Breakout Game";
    public static final int SIZE = 400;
    public static final int MS_DELAY = 1000 / FPS;
    public static final double SECOND_DELAY = 1.0 / FPS;
    public static final Paint BACKGROUND = Color.LAVENDER;
    public static final Paint HIGHLIGHT = Color.BLUE;
    public static final String PADDLE_IMG = "images/paddle.gif";
    public static final String BALL_IMG = "images/ball.gif";
    public static final String BACKGROUND_IMG = "";
    public static int BALL_VEL_X = 30;
    public static int BALL_VEL_Y = 30;
    public static final int FPS = 60;


    //things to remember in the game
    private Scene myScene;
    private ImageView myPaddle;
    private Ball myBall;


}
