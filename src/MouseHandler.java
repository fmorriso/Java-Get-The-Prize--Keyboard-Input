import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {

    private Ball ball = null;
    private Polkadot pd = null;

    private MouseHandler(Ball ball, Polkadot pd) {
        this.ball = ball;
        this.pd = pd;
    }

    public static MouseHandler getInstance(Ball ball, Polkadot pd) {
        return new MouseHandler(ball, pd);
    }

    public void mousePressed(MouseEvent e) {
        final int MIN_SPEED = 4;
        final int MAX_SPEED = 10;
        final int SPEED_RANGE = MAX_SPEED - MIN_SPEED + 1;

        // Right Click - move the polkadot
        if (e.isMetaDown()) {
            pd.setX(e.getX());
            pd.setY(e.getY());
        }
        // Shift + Left Click - change the speed of the ball
        else if (e.isShiftDown()) {
            ball.setdx(Math.random() * SPEED_RANGE + MIN_SPEED);
            ball.setdy(Math.random() * SPEED_RANGE + MIN_SPEED);
        }
        // otherwise, assume it's a Left Click
        // Left Click - move the ball
        else {
            ball.setX(e.getX());
            ball.setY(e.getY());
        }
    }
}
