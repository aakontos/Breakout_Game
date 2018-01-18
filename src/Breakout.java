import javafx.animation.KeyFrame;
import javafx.scene.text.*;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.util.Duration;
import java.util.*;

public class Breakout extends Application {
    public static final String TITLE = "Breakout Game";
    public static final String PADDLE_IMG = "";

    public static final int X_SIZE = 400;
    public static final int Y_SIZE = 600;
    public static final int MS_DELAY = 1000 / FPS;
    public static final double SECOND_DELAY = 1.0 / FPS;
    public static final Paint BACKGROUND = Color.LAVENDER;
    public static final Paint HIGHLIGHT = Color.BLUE;
    public static final String PADDLE_IMG = "images/paddle.gif";
    public static final int FPS = 60;
    public static final int start_speed = 30;


    //things to remember in the game
    private Scene my_scene;
    private ImageView my_paddle;
    private Ball my_ball;
    private int ball_speed;
    private Brick bricks;
    private Text winner;
    private Text loser;
    private Text lives_left;
    private Text current_level;
    private Text start_game;
    private Timeline animation;
    private ArrayList<Brick> my_bricks;




    @Override
    public void start (Stage stage) {
        int current_level = 1;
        ball_speed = start_speed;
        Scene scene = start_game()
    }

    public ImageView create_imageview(ImageView image_view, double width, double height, String filename, double x, double y) {
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(filename));
        image_view = new ImageView(image);
        image_view.setFitHeight(height);
        image_view.setFitWidth(width);
        image_view.setY(y);
        image_view.setX(x);
        return image_view;
    }

    public void step(double elapsedTime, Stage stage, int current_level) {

    }

    public void setup_animation(Stage stage, int current_level) {
        KeyFrame frame = new KeyFrame(Duration.millis(MS_DELAY), e-> step(SECOND_DELAY, stage, current_level));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
    }

    public Text create_text(Text text, double x, double y, String message, int font_size) {
        text = new Text();
        text.setX(x);
        text.setY(y);
        text.setFont(Font.font("Arial", font_size));
        text.setText(message);
        text.setFill(Color.RED);
        return text;
    }

    public void setup_game(int width, int height, Paint background, Stage stage, int current_level, int ball_speed) {
        Group root = new Group();
        Scene scene = new Scene(root, width, height, background);
        //Create the paddle
        my_paddle = create_imageview(my_paddle, width, height, PADDLE_IMG, 0, 0);
        //Create the ball
        Ball ball = new Ball();
        //Create the bricks
        bricks = new Bricks();
        //Create the powerups




        Image image = new Image(getClass().getClassLoader().getResourceAsStream())
    }



    private void handleKeyInput(KeyCode code, Group root, Stage stage, )


}
