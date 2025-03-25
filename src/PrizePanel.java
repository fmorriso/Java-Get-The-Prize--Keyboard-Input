// Computer Math - Unit 2 - Lab 17 - Keyboard Input

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class PrizePanel extends JPanel {

    public static final Color BACKGROUND = new Color(222, 222, 222);
    private static final Color BALL_COLOR = ColorExtensions.getRandomDarkColor();

    public static int FRAME = 400;
    private BufferedImage myImage = null;
    private Graphics myBuffer = null;
    private Ball ball = null;
    private Polkadot pd = null;
    private JFrame parentFrame = null;
    private int hits = 0;

    private PrizePanel(JFrame parent) {
        parentFrame = parent;
        FRAME = parentFrame.getHeight();

        // make ball diameter proportional to frame size
        double ballDiameter = FRAME * 1.0 / 8;

        myImage = new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB);
        myBuffer = myImage.getGraphics();

        final double ballLocation = FRAME / 4.0;
        ball = new Ball(ballLocation, ballLocation, ballDiameter, BALL_COLOR);
        ball.jump(FRAME, FRAME);

        pd = new Polkadot();
        pd.setColor(ColorExtensions.getRandomDarkColor(ball.getColor(), 128));
        // make polkadot diameter proportional to ball diameter
        pd.setDiameter(ball.getDiameter() / 2.0);
        pd.jump(FRAME, FRAME);

        PanelActionListener panelActionListener = PanelActionListener.getInstance(this);
        Timer t = new Timer(5, panelActionListener);
        t.start();

        // Add a "Listener" where the mouse controls some of the output
        addMouseListener(MouseHandler.getInstance(ball, pd));

        // Add a "Listener" for the keyboard controls
        addKeyListener(new Key(FRAME, pd));
        setFocusable(true);
    }

    @SuppressWarnings("unused")
    private PrizePanel() {
        /* prevent uninitialized instances */
    }

    /**
     * Factory method for creating an instance of this class
     *
     * @param parent - the parent JFrame of this JPanel subclass
     * @return - an instance of PrizePanel
     */
    public static PrizePanel createInstance(JFrame parent) {
        return new PrizePanel(parent);
    }

    public Graphics getMyBuffer() {
        return myBuffer;
    }

    public Ball getBall() {
        return ball;
    }

    public Polkadot getPolkadot() {
        return pd;
    }

    public JFrame getParentFrame() {
        return parentFrame;
    }

    public int getHits() {
        return hits;
    }

    public void paintComponent(Graphics g) {
        g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
    }

    public void incrementHits() {
        hits++;
    }

}