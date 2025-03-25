import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Key extends KeyAdapter {
    private int FRAME = 0;
    private Polkadot pd = null;

    public Key(int frame, Polkadot pd) {
        this.FRAME = frame;
        this.pd = pd;
    }

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
            case KeyEvent.VK_S:

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
            case KeyEvent.VK_D:

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