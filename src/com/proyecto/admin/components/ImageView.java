package com.proyecto.admin.components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

/**
 * Image component, saque esto de otro proyecto pura y exclusivamente para poner una foto de un pato
 * @author quien va ser
 */
public class ImageView extends ImagePane {

    private static final long serialVersionUID = 1L;
    /**
     * The image is displayer at the center
     */
    public static final int CENTER = 0;
    /**
     * The image is shrinked or stretched to fill the width of the component keepeing the aspect ratio
     */
    public static final int FILL = 1;
    /**
     * The image is shrinked or stretched to fill the height of the component keepeing the aspect ratio
     */
    public static final int FIT = 2;
    /**
     * The image is shrinked or stretched to fill the entire component keeping the aspect ratio
     */
    public static final int SPAN = 3;
    /**
     * The image is stretched or shrinked to fill the entire component losing its aspect ratio
     */
    public static final int STRETCH = 4;
    /**
     * The image is used as a texture to paint the component
     */
    private static final int TILE = 5;
    private int fit = 0;

    public ImageView() {
        super();
    }

    public ImageView(BufferedImage image) {
        super(image);
    }

    public ImageView(URL image) {
        super(image);
    }

    public ImageView(File image) {
        super(image);
    }

    public void paintComponent(Graphics g) {
        getDefaultPaintMethod().accept(g);

        float ratio = image.getWidth() / (float) image.getHeight();
        int x = 0, y = 0, width = getWidth(), height = getHeight();

        switch (fit) {
        case CENTER:
            x = (getWidth() / 2) - (image.getWidth() / 2);
            y = (getHeight() / 2) - (image.getHeight() / 2);
            g.drawImage(image, x, y, image.getWidth(), image.getHeight(), null);
            break;
        case STRETCH:
            g.drawImage(image, 0, 0, width, height, 0, 0, image.getWidth(), image.getHeight(), null);
            break;
        case TILE:
            Rectangle tileArea = new Rectangle(0, 0, image.getWidth(), image.getHeight());
            Graphics2D g2 = (Graphics2D) g;
            g2.setPaint(new TexturePaint(image, tileArea));
            g2.fill(new Rectangle(0, 0, width, height));
            break;
        default:
            if(
                (fit == SPAN && image.getWidth() <= image.getHeight()) ||
                 fit == FILL
            ) {
                height = (int) (width / ratio);
                y = (getHeight() / 2) - (height / 2);
            } else
            if(
                (fit == SPAN && image.getWidth() >= image.getHeight()) ||
                 fit == FIT
            ) {
                width = (int) (height * ratio);
                x = (getWidth() / 2) - (width / 2);
            }

            g.drawImage(image, x, y, width, height, null);
            break;
        }
    }

    public int getFit() {
        return fit;
    }

    public void setFit(int fit) {
        this.fit = fit;
    }
}