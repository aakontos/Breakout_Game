import java.util.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.effect.Reflection;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 * Class that manages levels and how they are switched to and from
 */

public class Level extends Breakout {
    public static final String TITLE = "Breakout Game";
    public static final Paint BACKGROUND = Color.ANTIQUEWHITE;
    public static final double SPEED_GROWTH = 1.2; //may be too fast, not sure just test later once its working
    public int max_level = 3;
    private int level_num;

    /**
     * Constructor for the level
     * @param level
     */

    public Level(int level) {
        level_num = level;
    }

    /**
     * method to return level number
     */

    public int return_level_num() {
        return this.level_num;
    }


    /**
     * method to change levels when all blocks are destroyed and also to end the game when the 3 levels have been beaten
     * @param stage
     * @param ball_speed
     * @param winner
     * @param animation
     */
    public void change_level(Stage stage, int ball_speed, Text winner, Timeline animation) {
        level_num++;
        if (level_num > max_level) {
            animation.stop();
            winner.setVisible(true);
            return;
        }
        Breakout new_game = new Breakout();
        Scene scene = new_game.setup_game(X_SIZE, Y_SIZE, BACKGROUND, stage, level_num, ball_speed);
        stage.setScene(scene);
        stage.setTitle(TITLE);
        stage.show();
        new_game.setup_animation(stage, level_num);
        //set up new level creating new scene, new title, etc.


    }

}
