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
    public static final String PADDLE_IMG = "";
    public static final String BALL_IMG = "";
    public static final String BACKGROUND_IMG = "";
    public static final int HEIGHT = 400;
    public static final int WIDTH = 400;
    public static final int FPS = 60;


    //things to remember in the game
    private Scene myScene;
    private ImageView myPaddle;
    private Ball myBall;


}
