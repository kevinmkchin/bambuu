package engine;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Texture {


    public final int SIZE;
    public int[] pixels;
    private String loc;
    private BufferedImage image;
    private String textureName;


    public Texture(String location, int size){
        this.loc = location;
        this.SIZE = size;
        pixels = new int[SIZE * SIZE];
        load();
    }

    private void load(){
        try {
            File f = new File(loc);
            image = ImageIO.read(f);
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0,0,w,h,pixels,0,w);
            textureName = f.getName();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage(){
        return image;
    }

    @Override
    public String toString() {
        return textureName;
    }
}
