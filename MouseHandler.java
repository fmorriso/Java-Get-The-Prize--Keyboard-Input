import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {

    private Ball ball = null;
    private Polkadot pd = null;

    public MouseHandler(Ball ball, Polkadot pd) {
        this.ball = ball;
        this.pd = pd;
    }

    public void mousePressed(MouseEvent e) {
        // Right Click - move the polkadot
        if (e.isMetaDown()) {
            pd.setX(e.getX());
            pd.setY(e.getY());
        }
        // Shift + Left Click - change the speed of the ball
        else if (e.isShiftDown()) {
            ball.setdx(Math.random() * 12 - 6);
            ball.setdy(Math.random() * 12 - 6);
        }
        // otherwise, assume it's a Left Click
        // Left Click - move the ball
        else {
            ball.setX(e.getX());
            ball.setY(e.getY());
        }
    }
}
