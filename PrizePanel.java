// Computer Math - Unit 2 - Lab 17 - Keyboard Input

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class PrizePanel extends JPanel {
    private static final Color BACKGROUND = new Color(222, 222, 222);
    private static final Font TEXT_FONT = new Font("Monospaced", Font.BOLD, 48);
    private static final Color BALL_COLOR = ColorExtensions.getRandomDarkColor();
    private static int FRAME = 400;
    private static double BALL_DIAM = 50;
    private final BufferedImage myImage;
    private final Graphics myBuffer;
    private final Ball ball;
    private final Polkadot pd;
    private final Timer t;
    private final JFrame parentFrame;
    private int hits;

    public PrizePanel(JFrame parent) {
        this.parentFrame = parent;
        FRAME = parentFrame.getHeight();

        // make ball diameter proportional to frame size
        BALL_DIAM = FRAME * 1.0 / 9;

        myImage = new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB);
        myBuffer = myImage.getGraphics();

        final double ballLocation = FRAME / 4.0;
        ball = new Ball(ballLocation, ballLocation, BALL_DIAM, BALL_COLOR);
        ball.jump(FRAME, FRAME);

        pd = new Polkadot();
        pd.setColor(ColorExtensions.getRandomDarkColor(ball.getColor(), 128));
        // make polkadot diameter proportional to ball diameter
        pd.setDiameter(ball.getDiameter() / 2.0);
        pd.jump(FRAME, FRAME);

        hits = 0;

        t = new Timer(5, new Listener());
        t.start();

        // Add a "Listener" where the mouse controls some of the output
        addMouseListener(new Mouse());

        // Add a "Listener" for the keyboard controls
        addKeyListener(new Key());
        setFocusable(true);
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

    public void paintComponent(Graphics g) {
        g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
    }

    private void collide(Ball b, Polkadot pd) {
        double d = distance(b.getX(), b.getY(), pd.getX(), pd.getY());

        if (d <= (b.getRadius() + pd.getRadius())) {
            pd.jump(FRAME, FRAME);

            hits++;

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

    /**
     * The screen is refreshed every time the Timer updates
     */
    private class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // draw the background
            myBuffer.setColor(BACKGROUND);
            myBuffer.fillRect(0, 0, FRAME, FRAME);

            // move the ball and check to see if it collided with the Polkadot
            ball.move(FRAME, FRAME);
            collide(ball, pd);

            ball.draw(myBuffer);
            pd.draw(myBuffer);

            // keep score
            myBuffer.setColor(Color.BLACK);
            myBuffer.setFont(TEXT_FONT);
            drawMessage(myBuffer, "Count: " + hits, 1);
            // myBuffer.drawString("Count: " + hits, FRAME - 150, 25);
            repaint();
        }
    }

    private class Mouse extends MouseAdapter {
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

    private class Key extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            final int increment = FRAME / 25;
            final double polkaDotRadius = pd.getRadius();

            final int whichKeyWasPressed = e.getKeyCode();
            switch (whichKeyWasPressed) {
                // Up arrow key - move the polkadot up towards the top of the screen
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:

                    pd.setY(pd.getY() - increment);

                    // ensure polkadot doesn't go past the top of the screen
                    if (pd.getY() <= polkaDotRadius) pd.setY(polkaDotRadius);

                    break;

                // Down arrow key - move the polkadot towards the bottom of the screen
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_Z:

                    pd.setY(pd.getY() + increment);

                    // ensure polkadot doesn't go past the bottom of the screen
                    if (pd.getY() >= (FRAME - polkaDotRadius)) pd.setY(FRAME - polkaDotRadius);

                    break;

                // Left arrow key - move the polkadot towards the left side of the screen
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:

                    pd.setX(pd.getX() - increment);

                    // ensure polkadot doesn't go past the left side of the screen
                    if (pd.getX() <= polkaDotRadius) pd.setX(polkaDotRadius);

                    break;

                // Right arrow key - move the polkadot towards the right side of the screen
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_S:

                    pd.setX(pd.getX() + increment);

                    // ensure polkadot doesn't go past the right side of the screen
                    if (pd.getX() >= FRAME - polkaDotRadius) pd.setX(FRAME - polkaDotRadius);

                    break;

                default:
                    // ignore any other keys that are pressed
                    break;
            }

        }

    }
}