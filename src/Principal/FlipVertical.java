
package Principal;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

public class FlipVertical {

    public static BufferedImage getFlippedImage(BufferedImage bi) {
        BufferedImage flipped = new BufferedImage(
                bi.getWidth(),
                bi.getHeight(),
                bi.getType());
        AffineTransform tran = AffineTransform.getTranslateInstance(0, bi.getHeight());
        AffineTransform flip = AffineTransform.getScaleInstance(1d, -1d);
        tran.concatenate(flip);

        Graphics2D g = flipped.createGraphics();
        g.setTransform(tran);
        g.drawImage(bi, 0, 0, null);
        g.dispose();

        return flipped;
    }

    FlipVertical(BufferedImage bi) {
        JPanel gui = new JPanel(new GridLayout(1,2,2,2));

        gui.add(new JLabel(new ImageIcon(bi)));
        gui.add(new JLabel(new ImageIcon(getFlippedImage(bi))));

        JOptionPane.showMessageDialog(null, gui);
    }

    public static void main(String[] args) throws AWTException {
              Runnable r = new Runnable() {

            @Override
            public void run() {
                final BufferedImage bi;
                try {
                    bi = ImageIO.read(new File("E://Java//FB_IMG_1441206566702.jpg")); 
                    new FlipVertical(bi);
                } catch (IOException ex) {
                    Logger.getLogger(FlipVertical.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        };
        SwingUtilities.invokeLater(r);
    }
}