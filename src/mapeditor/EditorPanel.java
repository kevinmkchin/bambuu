package mapeditor;

import engine.Level;
import engine.Texture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class EditorPanel extends JPanel implements KeyListener {

    public boolean showGrid = false;
    private Level level;
    private Character[][] arrayForDisplay;
    private int tileSize = 0;
    private int xOffset;
    private int yOffset;
    MapEditor frame;

    public EditorPanel(MapEditor god){
        frame = god;

        this.setFocusable(true);
        this.requestFocus();
        addKeyListener(this);
    }

    //replaces the clicked tile with the selected character
    public void drawTile(String character, int x, int y){
        int i = (x - xOffset) / tileSize;
        int j = (y - yOffset) / tileSize;
        arrayForDisplay[j][i] = character.charAt(0);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(MapEditor.settings.getEditorBackgroundColor());

        if(level != null){
            int mapW = level.getMapWidth();
            int mapH = level.getMapHeight();

            for(int i=0; i<mapH; i++){
                for(int j=0; j<mapW; j++){
                    String currentCharacter = arrayForDisplay[i][j].toString();
                    Texture matchingTexture = frame.getMasterCharacterMap().get(currentCharacter);
                    if(matchingTexture == null){ continue; }

                    BufferedImage image = matchingTexture.getImage();

                    g.drawImage(image, j*tileSize + xOffset, i*tileSize + yOffset,
                            tileSize, tileSize, null);
                }
            }
        }


        if(showGrid) {
            g.setColor(Color.RED);
            for (int i = 0; i < level.getMapWidth(); i++) {
                g.drawLine(i * tileSize + xOffset,
                        0 + yOffset,
                        i * tileSize + xOffset,
                        level.getMapHeight() * tileSize + yOffset);
            }
            for (int j = 0; j < level.getMapHeight(); j++) {
                g.drawLine(0 + xOffset,
                        j * tileSize + yOffset,
                        level.getMapWidth() * tileSize + xOffset,
                        j * tileSize + yOffset);
            }
        }

        g.setColor(frame.bgColor);
        g.fillRect(0, level.getMapHeight() * tileSize,
                frame.editorW, frame.editorH - level.getMapHeight() * tileSize);
        g.fillRect(level.getMapWidth() * tileSize,0,
                frame.editorW - level.getMapWidth() * tileSize,frame.editorH);


    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;

        int defaultTileSize = Math.min (frame.editorW / level.getMapWidth(),
                frame.editorH / level.getMapHeight()); //rounds down cuz integer
        //for zoom, just double tileSize

        if(tileSize == 0){
            tileSize = defaultTileSize;
        }

        xOffset = 0;
        yOffset = 0;
    }

    public Character[][] getArrayForDisplay() {
        return arrayForDisplay;
    }

    public void setArrayForDisplay(Character[][] arrayForDisplay) {
        this.arrayForDisplay = arrayForDisplay;
    }

    public int getTileSize() {
        return tileSize;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_Z){
            frame.restoreLastState();
        }
        if(e.getKeyCode() == KeyEvent.VK_G){
            showGrid = !showGrid;
            repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_Q){
            tileSize += tileSize * 0.2;
            repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_E){
            tileSize -= tileSize * 0.2;
            repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            xOffset += tileSize ;//* 0.1;
            repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_D){
            xOffset -= tileSize;// * 0.1;
            repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_W){
            yOffset += tileSize;// * 0.1;
            repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_S){
            yOffset -= tileSize;// * 0.1;
            repaint();
        }
        if(e.getKeyCode() == KeyEvent.VK_R){
            tileSize = Math.min (frame.editorW / level.getMapWidth(),
                    frame.editorH / level.getMapHeight());
            xOffset = 0;
            yOffset = 0;
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
