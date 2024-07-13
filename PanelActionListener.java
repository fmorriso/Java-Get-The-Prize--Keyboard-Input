import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The screen is refreshed every time the Timer updates
 */
public class PanelActionListener implements ActionListener {

    private static final Font TEXT_FONT = new Font("Monospaced", Font.BOLD, 48);

    private final JFrame parentFrame;
    private final Ball ball;
    private final Polkadot pd;

    private PrizePanel panel = null;
    private Graphics myBuffer = null;
    private int FRAME = 0;

    public PanelActionListener(PrizePanel parentPanel) {
        this.panel = parentPanel;

        this.parentFrame = parentPanel.getParentFrame();
        this.FRAME = PrizePanel.FRAME;
        this.myBuffer = parentPanel.getMyBuffer();
        this.ball = parentPanel.getBall();
        this.pd = parentPanel.getPolkadot();
    }

    /**
     * Calculates the distance between two points (x1, y1) and (x2, y2)
     *
     * @param x1 the x-coordinate of the first point
     * @param y1 the y-coordinate of the first point
     * @param x2 the x-coordinate of the second point
     * @param y2 the y-coordinate of the second point
     * @return the distance between two points (x1, y1) and (x2, y2)
     */
    private static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)); // enter the calculation here.
    }

    public void actionPerformed(ActionEvent e) {
        // draw the background
        myBuffer.setColor(PrizePanel.BACKGROUND);
        myBuffer.fillRect(0, 0, FRAME, FRAME);

        // move the ball and check to see if it collided with the Polkadot
        ball.move(FRAME, FRAME);
        collide(ball, pd);

        ball.draw(myBuffer);
        pd.draw(myBuffer);

        // keep score
        myBuffer.setColor(Color.BLACK);
        myBuffer.setFont(TEXT_FONT);
        drawMessage(myBuffer, "Count: " + panel.getHits(), 1);
        // myBuffer.drawString("Count: " + hits, FRAME - 150, 25);
        panel.repaint();
    }

    private void collide(Ball b, Polkadot pd) {
        double d = distance(b.getX(), b.getY(), pd.getX(), pd.getY());

        if (d <= (b.getRadius() + pd.getRadius())) {
            pd.jump(FRAME, FRAME);

            panel.incrementHits();

            // change color of both the ball and the polkadot after each collision
            ball.setColor(ColorExtensions.getRandomDarkColor());
            pd.setColor(ColorExtensions.getRandomDarkColor(ball.getColor(), 128));
        }

    }

    private void drawMessage(Graphics g, String message, int lineNumber) {
        g.setColor(Color.BLACK);
        FontMetrics fontMetrics = g.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(message);
        int x = this.parentFrame.getWidth() / 2 - (textWidth / 2);
        int textHeight = fontMetrics.getHeight();
        int y = (int) (textHeight * lineNumber + (this.parentFrame.getHeight() * 0.01));
        g.drawString(message, x, y);
    }
}
