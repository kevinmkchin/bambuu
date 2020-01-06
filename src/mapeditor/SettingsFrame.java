package mapeditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsFrame extends JFrame {

    JPanel panel = new JPanel();
    JPanel savePanel = new JPanel();
    JPanel colorPanel = new JPanel();
    JPanel tilePanel = new JPanel();
    JPanel exportPanel = new JPanel();

    JTextField imageSizeField;
    JTextField extensionField;
    JTextField layerDelimiterField;
    JTextField lineDelimiterField;

    ButtonGroup colorSelect;
    private JRadioButton white,
            black,
            lightred,
            darkred,
            lightblue,
            darkblue,
            lightgray,
            darkgray,
            lightgreen,
            darkgreen;

    private final MapEditor parent;

    public SettingsFrame(final MapEditor parent){

        this.parent = parent;

        colorSelect = new ButtonGroup();
        white = new JRadioButton("White", false);
        black = new JRadioButton("Black", false);
        lightred = new JRadioButton("Light Red", false);
        darkred = new JRadioButton("Dark Red", false);
        lightblue = new JRadioButton("Light Blue", true);
        darkblue = new JRadioButton("Dark Blue", false);
        lightgray = new JRadioButton("Light Gray", false);
        darkgray = new JRadioButton("Dark Gray", false);
        lightgreen = new JRadioButton("Light Green", false);
        darkgreen = new JRadioButton("Dark Green", false);
        colorSelect.add(white);
        colorSelect.add(black);
        colorSelect.add(lightred);
        colorSelect.add(darkred);
        colorSelect.add(lightblue);
        colorSelect.add(darkblue);
        colorSelect.add(lightgray);
        colorSelect.add(darkgray);
        colorSelect.add(lightgreen);
        colorSelect.add(darkgreen);
        white.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MapEditor.settings.setEditorBackgroundColor(Color.white);
                parent.resetColor();
            }
        });
        black.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MapEditor.settings.setEditorBackgroundColor(Color.BLACK);
                parent.resetColor();
            }
        });
        lightred.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MapEditor.settings.setEditorBackgroundColor(new Color(243, 153, 145));
                parent.resetColor();
            }
        });
        darkred.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MapEditor.settings.setEditorBackgroundColor(new Color(172, 51, 50));
                parent.resetColor();
            }
        });
        lightblue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MapEditor.settings.setEditorBackgroundColor(new Color(74, 108, 127));
                parent.resetColor();
            }
        });
        darkblue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MapEditor.settings.setEditorBackgroundColor(new Color(20, 48, 72));
                parent.resetColor();
            }
        });
        lightgray.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MapEditor.settings.setEditorBackgroundColor(new Color(201, 201, 201));
                parent.resetColor();
            }
        });
        darkgray.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MapEditor.settings.setEditorBackgroundColor(new Color(72, 72, 72));
                parent.resetColor();
            }
        });
        lightgreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MapEditor.settings.setEditorBackgroundColor(new Color(111, 245, 162, 255));
                parent.resetColor();
            }
        });
        darkgreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MapEditor.settings.setEditorBackgroundColor(new Color(0, 64, 23, 255));
                parent.resetColor();
            }
        });

        JLabel l1 = new JLabel("Size of Custom Tile Image File");
        imageSizeField = new JTextField(String.valueOf(MapEditor.settings.getTileSize()));

        JLabel l2 = new JLabel("Extension of Custom Tile Image File");
        extensionField = new JTextField(MapEditor.settings.getImageExtension());

        JLabel l3 = new JLabel("Delimiter for Rows in Output Level Data");
        lineDelimiterField = new JTextField(MapEditor.settings.getLineDelimiter());

        JLabel l4 = new JLabel("Delimiter for Layers in Output Level Data");
        layerDelimiterField = new JTextField(MapEditor.settings.getLayerDelimiter());

        JLabel l0 = new JLabel("EDITOR COLOR");

        JButton button = new JButton("Save Configurations");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MapEditor.settings.setLayerDelimiter(layerDelimiterField.getText());
                MapEditor.settings.setLineDelimiter(lineDelimiterField.getText());
                MapEditor.settings.setTileSize(Integer.parseInt(imageSizeField.getText()));
                MapEditor.settings.setImageExtension(extensionField.getText());
                MapEditor.settings.saveConfig();

                dispose();
            }
        });

        JLabel label = new JLabel("RESTART the Application after Saving!");
        savePanel.add(label);
        savePanel.add(button);
        colorPanel.add(l0);
        colorPanel.add(white);
        colorPanel.add(black);
        colorPanel.add(lightred);
        colorPanel.add(darkred);
        colorPanel.add(lightblue);
        colorPanel.add(darkblue);
        colorPanel.add(lightgray);
        colorPanel.add(darkgray);
        colorPanel.add(lightgreen);
        colorPanel.add(darkgreen);
        tilePanel.add(l1);
        tilePanel.add(imageSizeField);
        tilePanel.add(l2);
        tilePanel.add(extensionField);
        exportPanel.add(l3);
        exportPanel.add(lineDelimiterField);
        exportPanel.add(l4);
        exportPanel.add(layerDelimiterField);



        panel.add(colorPanel);
        //colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.LINE_AXIS));
        colorPanel.setLayout(new GridLayout(4,3));
        panel.add(tilePanel);
        tilePanel.setLayout(new BoxLayout(tilePanel, BoxLayout.PAGE_AXIS));
        panel.add(exportPanel);
        exportPanel.setLayout(new BoxLayout(exportPanel, BoxLayout.PAGE_AXIS));
        panel.add(savePanel);

        //panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setLayout(new GridLayout(4,1, 0, 40));
        panel.setBounds(0,0, 400, 500);
        this.add(panel);

        this.setLayout(null);
        this.setSize(400, 480);
        this.setResizable(false);
        this.setTitle("Bambuu Settings");
        ImageIcon img = new ImageIcon("icon.png");
        this.setIconImage(img.getImage());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }



}
