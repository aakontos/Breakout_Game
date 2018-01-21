import javafx.animation.KeyFrame;
import javafx.scene.text.*;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
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
    public static final int X_SIZE = 400;
    public static final int Y_SIZE = 600;
    public static final int FPS = 60;
    public static final int MS_DELAY = 1000 / FPS;
    public static final double SECOND_DELAY = 1.0 / FPS;
    public static final Paint BACKGROUND = Color.ANTIQUEWHITE;
    public static final String PADDLE_IMG = "images/paddle.gif";
    public static final int start_speed = 250;
    public static final int INPUT_SPEED = 25;


    //things to remember in the game
    private Scene my_scene;
    private ImageView my_paddle;
    private Ball my_ball;
    private int ball_speed;
    private Bricks bricks;
    private Text winner;
    private Text loser;
    private Text lives_left;
    private Text currentLevel;
    private Text start_game;
    private Text debug_menu;
    private Text debug_options;
    private Timeline animation;
    private Powerup power;
    private ArrayList<Brick> my_bricks;
    private ArrayList<Powerup> my_powerups;




    @Override
    public void start (Stage stage) {
        int current_level = 1;
        ball_speed = start_speed;
        Scene scene = setup_game(X_SIZE, Y_SIZE, BACKGROUND, stage, current_level, ball_speed);
        stage.setScene(scene);
        stage.setTitle(TITLE);
        stage.show();
        setup_animation(stage, current_level);
    }

    /**
     * Method to create an image_view of any type, will cut out repetition in defining many objects
     * @param image_view
     * @param width
     * @param height
     * @param filename
     * @param x
     * @param y
     * @return
     */

    public ImageView create_imageview(ImageView image_view, double width, double height, String filename, double x, double y) {
        Image image = new Image(getClass().getClassLoader().getResourceAsStream(filename));
        image_view = new ImageView(image);
        image_view.setFitHeight(height);
        image_view.setFitWidth(width);
        image_view.setY(y);
        image_view.setX(x);
        return image_view;
    }

    /**
     * step method to constantly update many moving variables
     * @param elapsedTime
     * @param stage
     * @param current_level
     */
    public void step(double elapsedTime, Stage stage, int current_level) {
        my_ball = my_ball.my_ball_position(elapsedTime, my_paddle, animation, winner, loser, my_bricks, current_level, stage, ball_speed);
        lives_left.setText("Lives : " + my_ball.get_num_lives());
        my_bricks = bricks.check_bricks(my_ball);
        my_powerups = power.check_powerup_position(elapsedTime, my_powerups, my_bricks, my_paddle, my_ball);
        for (Brick b : my_bricks) {                 //This is most likely incredibly wasteful looking through all of the bricks...
            if (b.check_brick_type() == "moving") {
                b.check_brick_collision(elapsedTime);
            }
        }
    }

    public void setup_animation(Stage stage, int current_level) {
        KeyFrame frame = new KeyFrame(Duration.millis(MS_DELAY), e-> step(SECOND_DELAY, stage, current_level));
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
    }

    public Text create_text(Text text, double x, double y, String message, int font_size) {
        text = new Text();
        text.setX(x);
        text.setY(y);
        text.setFont(Font.font("Impact", font_size));
        text.setText(message);
        text.setFill(Color.DARKGREEN);
        return text;
    }

    public Scene setup_game(int width, int height, Paint background, Stage stage, int current_level, int ball_speed) {
        Group root = new Group();
        my_scene = new Scene(root, width, height, background);
        //Create the paddle
        my_paddle = create_imageview(my_paddle, 80, 20, PADDLE_IMG, 0, Y_SIZE - 60);
        my_paddle.setX(X_SIZE / 2 - my_paddle.getBoundsInLocal().getWidth() / 2); // place paddle in the middle of the screen
        //Create the ball
        Ball ball = new Ball(null, ball_speed);
        my_ball = ball.create_ball(my_paddle, ball_speed);
        //Create the bricks
        bricks = new Bricks();
        my_bricks = bricks.create_bricks(X_SIZE, Y_SIZE, current_level);
        Level level = new Level(current_level);
        //Create the powerups
        power = new Powerup(null);
        my_powerups = power.create_powerups(my_bricks);
        lives_left = create_text(lives_left, 12, 590, "Lives : " + my_ball.get_num_lives(), 14);
        winner = create_text(winner, 50, Y_SIZE / 2 + 130, "Congratulations! You win!", 28);
        loser = create_text(loser, X_SIZE / 4, Y_SIZE / 4, "Sorry, better luck next time!", 20);
        currentLevel = create_text(currentLevel, 350, 590, "Level " +level.return_level_num(), 14);
        start_game = create_text(start_game, 40, Y_SIZE / 2 + 100, "Push 'Spacebar' to start the game!", 24);
        debug_menu = create_text(debug_menu, 90, Y_SIZE / 2 + 130, "Press 'D' for the debug menu options", 16);
        debug_options = create_text(debug_options, 90, Y_SIZE / 2 + 130, "Press 'L' to add a life" +"\n" +
                "Press N to skip to the next level", 16);
        debug_options.setVisible(false);
        winner.setVisible(false);
        loser.setVisible(false);
        root.getChildren().add(my_paddle);
        root.getChildren().add(my_ball.get_ball_imageview());
        for (int x = 0; x < my_bricks.size(); x++) {root.getChildren().add(my_bricks.get(x).get_brick_imageview());}
        for (int x = 0; x < my_powerups.size(); x++) {root.getChildren().add(my_powerups.get(x).get_powerup_imageview());}
        root.getChildren().add(lives_left);
        root.getChildren().add(winner);
        root.getChildren().add(loser);
        root.getChildren().add(start_game);
        root.getChildren().add(currentLevel);
        root.getChildren().add(debug_menu);
        my_scene.setOnKeyPressed(e -> handleKeyInput(e.getCode(), root, stage, current_level));
        return my_scene;

    }



    private void handleKeyInput(KeyCode code, Group root, Stage stage, int current_level) {
        if (code == KeyCode.SPACE) {
            animation.play();
            root.getChildren().remove(start_game);
            root.getChildren().remove(debug_menu);
            root.getChildren().remove(debug_options);
        }
        else if (code == KeyCode.ESCAPE) {
            Platform.exit();
            System.exit(0);
        }
        else if (code == KeyCode.D) {
            debug_options.setVisible(true);
            root.getChildren().remove(debug_menu);
        }
        else if (code == KeyCode.L) {
            my_ball.add_life();
        }
        else if (code == KeyCode.N) {
            Level level = new Level(current_level);
            level.change_level(stage, ball_speed, winner, animation);
            animation.stop();
        }
        else if (code == KeyCode.LEFT) {
            my_paddle.setX(my_paddle.getX() - INPUT_SPEED);
        }
        else if (code == KeyCode.RIGHT) {
            my_paddle.setX(my_paddle.getX() + INPUT_SPEED);
        }
    }


}
