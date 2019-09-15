package mapeditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsFrame extends JFrame {

    JPanel panel = new JPanel();

    JTextField imageSizeField;
    JTextField extensionField;

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
        lightblue = new JRadioButton("Light Blue", false);
        darkblue = new JRadioButton("Dark Blue", false);
        lightgray = new JRadioButton("Light Gray", false);
        darkgray = new JRadioButton("Dark Gray", false);
        lightgreen = new JRadioButton("Light Green", true);
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

        JLabel l1 = new JLabel("Size of Tile Image");
        imageSizeField = new JTextField("64");

        JLabel l2 = new JLabel("Extension of Tile Image");
        extensionField = new JTextField(".png");

        JLabel l0 = new JLabel("Editor Background Color");

        JButton button = new JButton("Save Configurations");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MapEditor.settings.setTileSize(Integer.parseInt(imageSizeField.getText()));
                MapEditor.settings.setImageExtension(extensionField.getText());
                MapEditor.settings.saveConfig();
            }
        });

        JLabel label = new JLabel("Restart the Application after Saving!");
        panel.add(label);
        panel.add(button);
        panel.add(l0);
        panel.add(white);
        panel.add(black);
        panel.add(lightred);
        panel.add(darkred);
        panel.add(lightblue);
        panel.add(darkblue);
        panel.add(lightgray);
        panel.add(darkgray);
        panel.add(lightgreen);
        panel.add(darkgreen);
        panel.add(l1);
        panel.add(imageSizeField);
        panel.add(l2);
        panel.add(extensionField);

        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.setBounds(0,0, 600, 600);
        this.add(panel);

        this.setLayout(null);
        this.setSize(600, 600);
        this.setResizable(false);
        this.setTitle("Bambuu Settings");
        ImageIcon img = new ImageIcon("icon.png");
        this.setIconImage(img.getImage());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }



}
