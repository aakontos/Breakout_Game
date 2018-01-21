import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.time.Duration;
import javafx.scene.Group;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;



public class Powerup extends Breakout {
    public static final int brick_width = 40;
    public static final int brick_height = 20;
    public static final String POWERUP_IMG = "images/powerup.png";
    private Brick brick;
    private String powerup_type;
    private ImageView powerup;
    private boolean can_collect;
    private ArrayList<Powerup> my_powerups;

    public Powerup(Brick b) {
        Image powerup_img = new Image(getClass().getClassLoader().getResourceAsStream(POWERUP_IMG));
        powerup = new ImageView(powerup_img);
        brick = b;
        powerup_type = "";
        can_collect = true;
    }

    public ImageView get_powerup_imageview(){
        return this.powerup;
    }

    public boolean check_powerup_collected(ImageView my_paddle) {
        return this.can_collect && this.powerup.getBoundsInParent().intersects(my_paddle.getBoundsInParent());
    }


    /**
     * shuffle the arrayList and pick randomly from the list for an equal and fair chance to get all 3 powerups
     * @return
     */

    public String random_select_powerup() {
        Random rand = new Random();
        ArrayList<String> all_powerups = new ArrayList<String>();
        all_powerups.add("big_paddle");
        all_powerups.add("speed_up");
        all_powerups.add("slow_down");
        int choose = rand.nextInt(2);
        return all_powerups.get(choose);
    }

    public void activate_powerup(String powerup_type, ImageView my_paddle, Ball my_ball) {
        if (powerup_type == "slow_down") {
            slow_down(my_ball);
        }
        else if (powerup_type == "big_paddle") {
            big_paddle(my_paddle);
        }
        else if (powerup_type == "speed_up") {
            speed_up(my_ball);
        }
    }

    public void slow_down(Ball my_ball) {
        my_ball.change_ball_speed(80);
    }

    public void big_paddle(ImageView my_paddle) {
        my_paddle.setFitWidth(160);
    }

    public void speed_up(Ball my_ball) {
        my_ball.change_ball_speed(350);
    }

    public ArrayList<Powerup> create_powerups(ArrayList<Brick> my_bricks){
        my_powerups = new ArrayList<Powerup>();
        Collections.shuffle(my_bricks);
        for (int x = 0; x < 4; x++) {
            Powerup power = new Powerup(my_bricks.get(x));
            power.powerup.setFitWidth(15);
            power.powerup.setFitHeight(15);
            power.powerup.setX(my_bricks.get(x).get_brick_imageview().getX());
            power.powerup.setY(my_bricks.get(x).get_brick_imageview().getY());
            power.powerup.setVisible(false);
            power.powerup_type = random_select_powerup();
            my_bricks.get(x).add_powerup();
            my_powerups.add(power);
            }
        return my_powerups;
    }

    /**
     * method to check if a powerup has been collected and if it has
     * will activate. If not the powerup will stay invisible
     * @param elapsedTime
     * @param my_powerups
     * @param my_bricks
     * @param my_paddle
     * @param my_ball
     * @return
     */
    public ArrayList<Powerup> check_powerup_position(double elapsedTime, ArrayList<Powerup> my_powerups, ArrayList<Brick> my_bricks, ImageView my_paddle, Ball my_ball)
    {
        for (Powerup p : my_powerups) {
            if (!my_bricks.contains(p.brick)) {
                p.powerup.setVisible(true);
                p.powerup.setY(p.powerup.getY() + 100 * elapsedTime);
                if (p.check_powerup_collected(my_paddle)) {
                    p.can_collect = false;
                    p.activate_powerup(p.powerup_type, my_paddle, my_ball);
                }
            }
        }
        return my_powerups;
    }

}
