// Computer Math - Unit 2 - Lab 17 - Keyboard Input

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class PrizePanel extends JPanel {
    public static final Color BACKGROUND = new Color(222, 222, 222);

    private static final Color BALL_COLOR = ColorExtensions.getRandomDarkColor();
    public static int FRAME = 400;
    private BufferedImage myImage = null;

    public Graphics getMyBuffer() {
        return myBuffer;
    }

    public Ball getBall() {
        return ball;
    }

    public Polkadot getPolkadot() {
        return pd;
    }

    private Graphics myBuffer = null;
    private Ball ball = null;
    private Polkadot pd = null;

    public JFrame getParentFrame() {
        return parentFrame;
    }

    private JFrame parentFrame = null;

    public int getHits() {
        return hits;
    }

    private int hits;

    /**
     * Factory method for creating an instance of this class
     * @param parent - the parent JFrame of this JPanel subclass
     * @return - an instance of PrizePanel
     */
    public static PrizePanel createInstance(JFrame parent) {
        return new PrizePanel(parent);
    }

    private PrizePanel(JFrame parent) {
        this.parentFrame = parent;
        FRAME = parentFrame.getHeight();

        // make ball diameter proportional to frame size
        double BALL_DIAM = FRAME * 1.0 / 9;

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

        PanelActionListener panelActionListener = new PanelActionListener(this);
        //Timer t = new Timer(5, new PanelActionListener(this));
        Timer t = new Timer(5, panelActionListener);
        t.start();

        // Add a "Listener" where the mouse controls some of the output
        addMouseListener(new MouseHandler(ball, pd));

        // Add a "Listener" for the keyboard controls
        addKeyListener(new Key(FRAME, pd));
        setFocusable(true);
    }

    @SuppressWarnings("unused")
    private PrizePanel() {
        /* prevent uninitialized instances */
    }




    public void paintComponent(Graphics g) {
        g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
    }


    public void incrementHits() {
        hits++;
    }
}