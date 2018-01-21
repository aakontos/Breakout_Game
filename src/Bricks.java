import java.lang.reflect.Array;
import java.util.*;
import java.util.Iterator;

/**
 * HTB will be used as an abbreviation for Hits To Break
 */

public class Bricks extends Breakout {
    private int brick_width = 40;
    private int brick_height = 20;
    private ArrayList<Brick> all_bricks;
    private int normal_HTB = 1;
    private int strong_HTB = 2;
    private int moving_HTB = 4;
    private int unbreakable_HTB = 999;


    public ArrayList<Brick> create_bricks(int width, int height, int current_level){
        all_bricks = new ArrayList<Brick>();
        if (current_level == 1) {
            create_brick_rows(all_bricks, new Normal_Brick("normal", brick_height, brick_width, normal_HTB, false, 0),
            8, 28, 180, 3);
            create_brick_rows(all_bricks, new Strong_Brick("strong", brick_height, brick_width, strong_HTB, false, 0),
                    8, 28, 100, 1);
            create_brick_rows(all_bricks, new Normal_Brick("normal", brick_height, brick_width, normal_HTB, false, 0),
                    8, 28, 70, 1);
        }
        else if (current_level == 2) {
            create_brick_rows(all_bricks, new Normal_Brick("normal", brick_height, brick_width, normal_HTB, false, 0),
                    8, 28, 180, 2);
            create_brick_row(all_bricks, new Strong_Brick("strong", brick_height, brick_width, strong_HTB, false, 0),
                    5, 28, 100, 1, 79, 0);
            create_brick_row(all_bricks, new Unbreakable_Brick("unbreakable", brick_height, brick_width, unbreakable_HTB, false, 0),
                    3, 28, 185, 1, 158, 0);
            create_brick_rows(all_bricks, new Normal_Brick("normal", brick_height, brick_width, normal_HTB, false, 0),
                    8, 28, 95, 2);
            create_brick_row(all_bricks, new Moving_Brick("moving", brick_height, brick_width, moving_HTB, false, 100),
                    1, 28, 250, 1, 0, 0);

        }
        else if (current_level == 3) {
            create_brick_row(all_bricks, new Moving_Brick("moving", brick_height, brick_width, moving_HTB, false, 100),
                    1, 28, 300, 1, 0, 0);
            create_brick_row(all_bricks, new Moving_Brick("moving", brick_height, brick_width, moving_HTB, false, 120),
                    1, 200, 270, 1, 0, 0);
            create_brick_row(all_bricks, new Moving_Brick("moving", brick_height, brick_width, moving_HTB, false, 140),
                    1, 350, 240, 1, 0, 0);
            create_brick_row(all_bricks, new Moving_Brick("moving", brick_height, brick_width, strong_HTB, false, 500),
                    1, 200, 15, 1, 0, 0);
            create_brick_rows(all_bricks, new Strong_Brick("strong", brick_height, brick_width, strong_HTB, false, 0),
                    8, 28, 100, 1);
            create_brick_row(all_bricks, new Normal_Brick("normal", brick_height, brick_width, normal_HTB, false, 0),
                    8, 28, 45, 1, 45, 0);
            create_brick_row(all_bricks, new Strong_Brick("strong", brick_height, brick_width, strong_HTB, false, 0),
                    5, 28, 105, 1, 79, 0);
            create_brick_row(all_bricks, new Unbreakable_Brick("unbreakable", brick_height, brick_width, unbreakable_HTB, false, 0),
                    3, 28, 135, 1, 158, 0);
            create_brick_rows(all_bricks, new Normal_Brick("normal", brick_height, brick_width, normal_HTB, false, 0),
                    8, 28, 210, 2);
        }
        return all_bricks;
    }

    /**
     * Create a single row of bricks
     *
     * @param my_bricks = arrayList of bricks
     * @param start = placeholder brick to duplicate and place over and over
     * @param length = number of bricks per row
     * @param width = width of specific brick
     * @param height = height of specific brick
     */

    public void create_brick_row(ArrayList<Brick> my_bricks, Brick start, int length, double width, double height, int direction, int horiz_gap, int vert_gap) {
        for (int j = 0; j < length; j++) {
            Brick dup = start.copy();
            dup.place_brick(width + direction * j * horiz_gap, height + j * vert_gap);
            my_bricks.add(dup);
        }
    }

    /**
     * Create all of the rows of bricks using the create_bricks method
     *
     *
     * @param my_bricks
     * @param start
     * @param length
     * @param width
     * @param height
     * @param max_rows
     */

    public void create_brick_rows(ArrayList<Brick> my_bricks, Brick start, int length, double width, double height, int max_rows) {
        for (int row = 0; row < max_rows; row++) {
            height = height - 25; //need to maybe mess around with this number depending on the size of the brick image
            create_brick_row(my_bricks, start, length, width, height, 1, 45, 0);
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
