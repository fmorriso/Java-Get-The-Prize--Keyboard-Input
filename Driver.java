// Computer Math - Unit 2 - Lab 17 - Keyboard Input

import javax.swing.*;
import java.awt.*;

public class Driver
{
    public static void main(String[] args)
    {
        // capture size of screen we're using
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // use a portion of the screen for the JFrame, but make sure it's square to the
        // nearest multiple of 100
        int maxSquareSize = (int) (Math.min(screenSize.width, screenSize.height) * 3.0 / 4.0) / 100 * 100;
        Dimension frameSize = new Dimension(maxSquareSize, maxSquareSize);

        JFrame frame = new JFrame("Computer Math, Unit 2, Lab 17 - Keyboard and Mouse Input");
        // note the use of setPreferredSize instead of setSize, coupled with
        // frame.pack() below:
        frame.setPreferredSize(frameSize);
        frame.setSize(frameSize);

        PrizePanel pnl = new PrizePanel(frame);
        pnl.setFocusable(true);
        pnl.requestFocusInWindow();
        frame.getContentPane().add(pnl, BorderLayout.CENTER);

        frame.pack();

        // put the JFrame in the middle of the physical screen
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
