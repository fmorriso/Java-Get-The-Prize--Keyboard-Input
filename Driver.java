// Get The Prize - Keyboard & Mouse Input

import javax.swing.*;
import java.awt.*;

public class Driver {
    public static void main(String[] args) {
        // use a portion of the screen for the JFrame, but make sure it's square to the
        // nearest multiple of 100
        Dimension frameSize = SwingScreenUtilities.getScaledSize(0.55, 100, true);

        String title = String.format("Get The Prize - Keyboard and Mouse Input - Java version %s", getJavaVersion());
        System.out.println(title);
        JFrame frame = new JFrame(title);
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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    /**
     * get the java version that is running the current program
     *
     * @return string containing the java version running the current program
     */
    private static String getJavaVersion() {
        Runtime.Version runTimeVersion = Runtime.version();
        return String.format("%s.%s.%s.%s", runTimeVersion.feature(), runTimeVersion.interim(), runTimeVersion.update(), runTimeVersion.patch());
    }
}
