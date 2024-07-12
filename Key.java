import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Key extends KeyAdapter {
    private Polkadot pd;
    private int FRAME;

    public Key() {

    }

    public void keyPressed(KeyEvent e) {
        // Up - move the polkadot up
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            pd.setY(pd.getY() - 10);

            // ensure polkadot doesn't go past the top of the screen
            if (pd.getY() <= pd.getRadius())
                pd.setY(pd.getRadius());
        }

        // Down - move the polkadot down
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            pd.setY(pd.getY() + 10);

            // ensure polkadot doesn't go past the bottom of the screen
            if (pd.getY() >= (FRAME - pd.getRadius()))
                pd.setY(FRAME - pd.getRadius());
        }

        // Left - move the polkadot to the left
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            pd.setX(pd.getX() - 10);

            // ensure polkadot doesn't go past the left side of the screen
            if (pd.getX() <= pd.getRadius())
                pd.setX(pd.getRadius());
        }

        // Right - move the polkadot to the right
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            pd.setX(pd.getX() + 10);

            // ensure polkadot doesn't go past the right side of the screen
            if (pd.getX() >= FRAME - pd.getRadius())
                pd.setX(FRAME - pd.getRadius());
        }

    }


    public Polkadot getPd() {
        return pd;
    }

    public void setPd(Polkadot pd) {
        this.pd = pd;
    }


    public int getFRAME() {
        return FRAME;
    }


    public void setFRAME(int fRAME) {
        FRAME = fRAME;
    }
}