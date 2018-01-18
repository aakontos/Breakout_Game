import java.lang.reflect.Array;
import java.util.*;
import java.util.Iterator;

/**
 * HTB will be used as an abbreviation for Hits To Break
 */

public class Bricks extends Breakout {
    private int WIDTH = 30;
    private int HEIGHT = 20;
    private ArrayList<Brick> all_bricks;
    private int normal_HTB = 1;
    private int strong_HTB = 2;
    private int unbreakable_HTB = 999;


    public ArrayList<Brick> create_bricks (int width, int height, int current_level, ArrayList<Brick> my_bricks){
        all_bricks = new ArrayList<Brick>();
        if (current_level == 1) {
            create_brick_rows(my_bricks, new Normal_Brick("normal", HEIGHT, WIDTH, normal_HTB, false), 8, );
        }
    }

    /**
     * Create a single row of bricks
     * @param my_bricks
     * @param start
     * @param length
     * @param width
     * @param height
     */

    public void create_bricks(ArrayList<Brick> my_bricks, Brick start, int length, double width, double height) {
        for (int j = 0; j < length; j++) {
            Brick dup = start.copy();
            dup.place_brick(width * j, height*j);
            my_bricks.add(dup);
        }
    }

    /**
     * Create all of the rows of bricks using the create_bricks method
     * @param my_bricks
     * @param start
     * @param length
     * @param width
     * @param height
     * @param max_rows
     */

    public void create_brick_rows(ArrayList<Brick> my_bricks, Brick start, int length, double width, double height, int max_rows) {
        for (int row = 0; row < max_rows; row++) {
            height = height - 20; //need to maybe mess around with this number depending on the size of the brick image
            create_bricks(my_bricks, start, length, width, height);
        }
    }

    /**
     * remove brick once it has been destroyed
     * @param iterator
     * @param brick
     */

    public void remove_bricks(Iterator<Brick> iterator, Brick brick) {
        brick.get_brick_imageview().setImage(null);
        iterator.remove();
    }


    /**
     * Method that updates the existing bricks in the game
     * @param my_ball
     * @return
     */

    public ArrayList<Brick> check_bricks(Ball my_ball) {
        Iterator<Brick> check = all_bricks.iterator();
        while (check.hasNext()) {
            Brick brick = check.next();
            if (brick.get_brick_imageview().getBoundsInParent().intersects(my_ball.get_ball_imageview().getBoundsInParent())) {
                if (my_ball.get_ball_imageview().getBoundsInParent().getMinX() >= brick.get_brick_imageview().getBoundsInParent().getMinX()
                        && my_ball.get_ball_imageview().getBoundsInParent().getMaxX() <= brick.get_brick_imageview().getBoundsInParent().getMaxX()) {
                    my_ball.change_x_direction();
                }
                brick.reduce_BHTB();
                if (brick.check_BHTB() <= 0) {
                    remove_bricks(check, brick);
                }
                else {
                    my_ball.change_y_direction();
                }
            }
        }
        return all_bricks;
    }




}
