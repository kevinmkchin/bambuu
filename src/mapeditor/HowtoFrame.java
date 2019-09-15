package mapeditor;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import static mapeditor.MapEditor.font1;

public class HowtoFrame extends JFrame {

    private JTextPane howto;

    public HowtoFrame(){

        String content = "=== How to use Bambuu Tile Editor ===\n" +
                "--- IMPORTANT: How .bbuu File Works ---\n" +
                ".bbuu stores data as a string of text.\n" +
                "Try opening it with a text editor.\n" +
                "The format is 'LAYER1/LAYER2/LAYER3'.\n" +
                "When parsing this format in your own game,\n" +
                "split the string data at '/' to get the individual\n" +
                "LAYER1, LAYER2, LAYER3 data. \n" +
                "If using only one or two layer, the data will be\n" +
                "in the format: 'LAYER1' or 'LAYER1/LAYER2'.\n" +
                "\n"+
                "--- Using Custom Tile Images ---\n" +
                "Replace the images in the\n" +
                "'TILES' folder with your own.\n" +
                "They must be named '1.png', 'A.png', etc.\n" +
                "You can use other image types such as\n" +
                ".jpg, but you must specify it in the\n" +
                "Settings. You must also specify the\n" +
                "size of the images. (default is 64x64)\n" +
                "\n" +
                "--- LAYERS ---\n" +
                "Select which layer to view and edit.\n" +
                "You must select which layers to save\n" +
                "to the .bbuu map file.";

        howto = new JTextPane();
        StyledDocument doc = howto.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        howto.setText(content);
        howto.setBounds(0,0,600,600);
        howto.setFont(font1);
        howto.setEditable(false);
        this.add(howto);

        this.setLayout(null);
        this.setSize(600, 600);
        this.setResizable(false);
        this.setTitle("How to use Bambuu");
        ImageIcon img = new ImageIcon("icon.png");
        this.setIconImage(img.getImage());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }


}
