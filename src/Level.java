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
    public static final int SIZE = 400;
    public static final Paint BACKGROUND = Color.LAVENDER;
    public static final double SPEED_GROWTH = 1.3; //may be too fast, not sure just test later once its working
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
     * method to adjust existing level values (level number)
     */

    public void change_level(Stage stage, int ball_speed, Text winner, Timeline animation) {
        level_num++;
        ball_speed = (int) (ball_speed * SPEED_GROWTH);
        if (level_num > max_level) {
            animation.stop();
            winner.setVisible(true);
            return;
        }
        Breakout new_game = new Breakout();
        //set up new level creating new scene, new title, etc.
        //will need to finish other classes before we can implement this here.


    }

}