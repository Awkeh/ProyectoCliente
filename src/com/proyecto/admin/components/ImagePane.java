package com.proyecto.admin.components;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.function.Consumer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePane extends JPanel {

    private static final long serialVersionUID = 1L;
    protected BufferedImage image;

    public ImagePane() {
        super();
        setImageAsEmpty();
    }

    public ImagePane(BufferedImage image) {
        super();
        setImage(image);
    }

    public ImagePane(URL image) {
        super();
        setImage(image);
    }

    public ImagePane(File image) {
        super();
        setImage(image);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), 0, 0, image.getWidth(), image.getHeight(), null);
    }

    private void setImageAsEmpty() {
    	image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        Objects.requireNonNull(image);
        this.image = image;
    }

    public void setImage(URL url) {
        Objects.requireNonNull(url);
        try {
            image = ImageIO.read(url);
        } catch(IOException e) {
            e.printStackTrace();
            setImageAsEmpty();
        }
    }

    public void setImage(File f) {
        Objects.requireNonNull(f);
        try {
            image = ImageIO.read(f);
        } catch(IOException e) {
            e.printStackTrace();
            setImageAsEmpty();
        }
    }

    public Consumer<Graphics> getDefaultPaintMethod() {
        return super::paintComponent;
    }

}