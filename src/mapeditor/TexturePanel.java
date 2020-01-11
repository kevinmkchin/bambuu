package mapeditor;

import javax.swing.*;
import java.awt.*;

public class TexturePanel extends JPanel {

    MapEditor god;

    public TexturePanel(MapEditor god){
        this.god = god;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        char [] carray = { 'e', 'm', 'p','t', 'y'};
        g.setColor(Color.YELLOW);
        g.drawChars(carray, 0, carray.length, 105,530);

        try {
            MapEditor.TextureEntry curTex = (MapEditor.TextureEntry) god.getCharList().getSelectedValue();
            g.drawImage(curTex.getTexture().getImage(), 81, 495, 100, 100, null);
        } catch (Exception e){
            System.out.println(e);
        }

        g.drawLine(80,494,80,596);
        g.drawLine(80,596,182,596);
        g.drawLine(182,494,182,596);
        g.drawLine(80,494,182,494);
    }
}
