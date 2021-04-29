package TP01;

import edu.princeton.cs.algs4.Point2D;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Locations extends JLabel {
    private BufferedImage img = null;
    private int posX, posY;

    public Locations(double lat, double lon) {
        Point2D p = new Point2D(lat, lon);
        initializer(p);
    }

    public Locations(Point2D p) {
        initializer(p);
    }

    // the reason behind the confusing twist from Y and X is because the lattitude is the first coordinate
    // Lattitude is the up and down looking at a graph
    private void initializer(Point2D p) {
        new JLabel();
        setText(null);
        //Each gps horizontal coordinate = 18 pixels
        //Each gps verticle coordinate = 24 pixels
        posX = (int) ((17.33) * (360- p.y() - 235.5));
        posY = (int) ((23.57) * (90 - p.x() - 41));
        setBounds(posX, posY, 16, 29);

        try {
            img = ImageIO.read(new File("resources/ping.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image dimg;
        ImageIcon imageIcon;
        if (img != null) {
            dimg = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_FAST);
            imageIcon = new ImageIcon(dimg);
            setIcon(imageIcon);
            setText(null);
        }
    }
}
