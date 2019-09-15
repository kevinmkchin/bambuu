package mapeditor;

import java.awt.*;
import java.io.*;

public class Settings {

    private int tileSize;
    private String imageExtension;
    private Color editorBackgroundColor;
    private String layerDelimiter;
    private String lineDelimiter;

    public Settings(){
        loadConfig();
    }

    //read from settings.cfg
    public void saveConfig(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("settings.cfg"));
            writer.write(editorBackgroundColor.getRGB() + "`" +
                    tileSize + "`" +
                    imageExtension + "`" +
                    lineDelimiter + "`" +
                    layerDelimiter);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadConfig(){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("settings.cfg"));
            String data = reader.readLine();
            String[] dataArray = data.split("`");

            editorBackgroundColor = new Color(Integer.parseInt(dataArray[0]));
            tileSize = Integer.parseInt(dataArray[1]);
            imageExtension = dataArray[2];
            lineDelimiter = dataArray[3];
            layerDelimiter = dataArray[4];

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

    }


    public int getTileSize() {
        return tileSize;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    public String getImageExtension() {
        return imageExtension;
    }

    public void setImageExtension(String imageExtension) {
        this.imageExtension = imageExtension;
    }

    public Color getEditorBackgroundColor() {
        return editorBackgroundColor;
    }

    public void setEditorBackgroundColor(Color editorBackgroundColor) {
        this.editorBackgroundColor = editorBackgroundColor;
    }

    public String getLayerDelimiter() {
        return layerDelimiter;
    }

    public void setLayerDelimiter(String layerDelimiter) {
        this.layerDelimiter = layerDelimiter;
    }

    public String getLineDelimiter() {
        return lineDelimiter;
    }

    public void setLineDelimiter(String lineDelimiter) {
        this.lineDelimiter = lineDelimiter;
    }
}
