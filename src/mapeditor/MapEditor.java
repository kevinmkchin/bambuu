package mapeditor;

import engine.Level;
import engine.Texture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Vector;

import static java.awt.Font.PLAIN;

public class MapEditor extends JFrame implements ActionListener, MouseMotionListener, MouseListener {

    public static Settings settings;
    private Level level;

    public int winW = 1600;
    public int winH = 900;
    public int editorW = 800;
    public int editorH = 800;

    public Color bgColor = Color.DARK_GRAY;
    public Color textColor = Color.WHITE;
    public static Font font1 = new Font("Century Gothic", PLAIN, 18);
    public static Font font2 = new Font("Century Gothic", Font.BOLD, 22);

    private JMenuBar menuBar;
    private JMenu fileMenu, settingMenu, howtoMenu;
    private JMenuItem newFile, saveFile, loadFile, settingItem, howtoItem;
    private JFileChooser fc = new JFileChooser();
    private int menuBarHeight = 25;

    private LinkedHashMap<String, Texture> masterCharacterMap;
    private JList charList;
    private EditorPanel editorPanel;
    private JPanel toolPanel, bottomPanel, zoomPanel, texturePanel;
    private JLabel tpLabel;
    ButtonGroup editorSelect;
    private JRadioButton wallEdit, floorEdit, ceilEdit;
    private JButton fillScreenButton;
    private JCheckBox use1, use2, use3;
    public boolean wallEditing, floorEditing, ceilEditing;

    class TextureEntry {
        private String character;
        private Texture texture;

        TextureEntry(String c, Texture t){
            character = c;
            texture = t;
        }

        public String getCharacter() {
            return character;
        }

        public Texture getTexture() {
            return texture;
        }

        @Override
        public String toString() {
            if(texture == null){
                return "Tile: " + character + " | " + "empty";
            }else {
                return "Tile: " + character + " | " + texture.toString();
            }
        }
    }

    public MapEditor(){

        makeMasterCharacterMap();

        initializeGUI();

        addMouseListener(this);
        addMouseMotionListener(this);

        newFile(16,16); //By default, start a new map
        update();

        this.setJMenuBar(menuBar);
        this.setLayout(null);
        this.setSize(winW, winH);
        this.setResizable(false);
        this.setTitle("Bambuu Tilemap Editor");
        ImageIcon img = new ImageIcon("icon.png");
        this.setIconImage(img.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

// ================================================================

    /** ALL TILE CHARACTER & TEXTURE DATA GO HERE
     *  creates a HashMap with tile character as key
     *  and corresponding Texture object as value.*/
    private void makeMasterCharacterMap(){
        masterCharacterMap = new LinkedHashMap<>();
        masterCharacterMap.put(Level.OPEN_SPACE, null);
        masterCharacterMap.put("1", TileLoader.getTile("1"));
        masterCharacterMap.put("2", TileLoader.getTile("2"));
        masterCharacterMap.put("3", TileLoader.getTile("3"));
        masterCharacterMap.put("4", TileLoader.getTile("4"));
        masterCharacterMap.put("5", TileLoader.getTile("5"));
        masterCharacterMap.put("6", TileLoader.getTile("6"));
        masterCharacterMap.put("7", TileLoader.getTile("7"));
        masterCharacterMap.put("8", TileLoader.getTile("8"));
        masterCharacterMap.put("9", TileLoader.getTile("9"));
        masterCharacterMap.put("A", TileLoader.getTile("A"));
        masterCharacterMap.put("B", TileLoader.getTile("B"));
        masterCharacterMap.put("C", TileLoader.getTile("C"));
        masterCharacterMap.put("D", TileLoader.getTile("D"));
        masterCharacterMap.put("E", TileLoader.getTile("E"));
        masterCharacterMap.put("F", TileLoader.getTile("F"));
        masterCharacterMap.put("G", TileLoader.getTile("G"));
        masterCharacterMap.put("H", TileLoader.getTile("H"));
        masterCharacterMap.put("I", TileLoader.getTile("I"));
        masterCharacterMap.put("J", TileLoader.getTile("J"));
        masterCharacterMap.put("K", TileLoader.getTile("K"));
        masterCharacterMap.put("L", TileLoader.getTile("L"));
        masterCharacterMap.put("M", TileLoader.getTile("M"));
        masterCharacterMap.put("N", TileLoader.getTile("N"));
        masterCharacterMap.put("O", TileLoader.getTile("O"));
        masterCharacterMap.put("P", TileLoader.getTile("P"));
        masterCharacterMap.put("Q", TileLoader.getTile("Q"));
        masterCharacterMap.put("R", TileLoader.getTile("R"));
        masterCharacterMap.put("S", TileLoader.getTile("S"));
        masterCharacterMap.put("T", TileLoader.getTile("T"));
        masterCharacterMap.put("U", TileLoader.getTile("U"));
        masterCharacterMap.put("V", TileLoader.getTile("V"));
        masterCharacterMap.put("W", TileLoader.getTile("W"));
        masterCharacterMap.put("X", TileLoader.getTile("X"));
        masterCharacterMap.put("Y", TileLoader.getTile("Y"));
        masterCharacterMap.put("Z", TileLoader.getTile("Z"));

    }

// ================================================================

    private void initializeGUI(){

        // === Panels ===
        editorPanel = new EditorPanel(editorW, this);
        editorPanel.setSize(editorW,editorH);

        toolPanel = new JPanel();
        toolPanel.setBackground(bgColor);
        toolPanel.setLayout(new BorderLayout());
        toolPanel.setBounds(editorW,0,winW-editorW,editorH);

        bottomPanel = new JPanel();
        bottomPanel.setBackground(bgColor);
        bottomPanel.setBounds(0,editorH,winW,winH);

        zoomPanel = new JPanel();
        zoomPanel.setBackground(bgColor);
        zoomPanel.setLayout(new GridLayout(3, 2));

        texturePanel = new JPanel();
        texturePanel.setBackground(bgColor);

        // === Radio Buttons ===
        wallEdit = new JRadioButton("View LAYER 1", true);
        wallEditing = true;
        floorEdit = new JRadioButton("View LAYER 2", false);
        ceilEdit = new JRadioButton("View LAYER 3", false);
        wallEdit.setFont(font1);
        wallEdit.setForeground(Color.WHITE);
        wallEdit.setBackground(bgColor);
        ceilEdit.setFont(font1);
        ceilEdit.setForeground(Color.WHITE);
        ceilEdit.setBackground(bgColor);
        floorEdit.setFont(font1);
        floorEdit.setForeground(Color.WHITE);
        floorEdit.setBackground(bgColor);
        wallEdit.addActionListener(this);
        floorEdit.addActionListener(this);
        ceilEdit.addActionListener(this);
        wallEdit.addActionListener(this);
        floorEdit.addActionListener(this);
        ceilEdit.addActionListener(this);
        editorSelect = new ButtonGroup();
        editorSelect.add(wallEdit);
        editorSelect.add(floorEdit);
        editorSelect.add(ceilEdit);

        use1 = new JCheckBox("Use LAYER-1", true);
        use2 = new JCheckBox("Use LAYER-2", false);
        use3 = new JCheckBox("Use LAYER-3", false);
        use1.setFont(font1);
        use2.setFont(font1);
        use3.setFont(font1);
        use1.setBackground(bgColor);
        use2.setBackground(bgColor);
        use3.setBackground(bgColor);
        use1.setForeground(Color.WHITE);
        use2.setForeground(Color.WHITE);
        use3.setForeground(Color.WHITE);

        bottomPanel.add(wallEdit);
        bottomPanel.add(floorEdit);
        bottomPanel.add(ceilEdit);
        bottomPanel.add(use1);
        bottomPanel.add(use2);
        bottomPanel.add(use3);

        tpLabel = new JLabel("Select Tile");
        tpLabel.setFont(font2);
        tpLabel.setForeground(textColor);
        tpLabel.setHorizontalAlignment(SwingConstants.CENTER);
        toolPanel.add(tpLabel, BorderLayout.PAGE_START);
        toolPanel.add(zoomPanel, BorderLayout.PAGE_END);

        toolPanel.add(texturePanel, BorderLayout.CENTER);

        this.add(editorPanel);
        this.add(toolPanel);
        this.add(bottomPanel);

        //=== MENU BAR ===
        menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(winW, menuBarHeight));
        fileMenu = new JMenu("File");
        fileMenu.getAccessibleContext().setAccessibleDescription("File menu");
        newFile = new JMenuItem("New Map");
        newFile.addActionListener(this);
        saveFile = new JMenuItem("Save Map As...");
        saveFile.addActionListener(this);
        loadFile = new JMenuItem("Load Map");
        loadFile.addActionListener(this);
        fileMenu.add(newFile);
        fileMenu.add(saveFile);
        fileMenu.add(loadFile);

        settingMenu = new JMenu("Settings");
        settingItem = new JMenuItem("Settings");
        settingItem.addActionListener(this);
        settingMenu.add(settingItem);

        howtoMenu = new JMenu("How-To-Use");
        howtoItem = new JMenuItem("How-To-Use");
        howtoItem.addActionListener(this);
        howtoMenu.add(howtoItem);

        menuBar.add(fileMenu);
        menuBar.add(settingMenu);
        menuBar.add(howtoMenu);


        //Texture/Character selector List
        Vector<TextureEntry> textureEntries = new Vector<>();
        for(HashMap.Entry<String, Texture> entry : masterCharacterMap.entrySet()){
            textureEntries.add(new TextureEntry(entry.getKey(), entry.getValue()));
        }

        charList = new JList(textureEntries);
        charList.setFont(font1);
        charList.setSelectedIndex(0);
        JScrollPane charListScroller = new JScrollPane(charList);
        charListScroller.setPreferredSize(new Dimension(500,700));
        texturePanel.add(charListScroller);

        fillScreenButton = new JButton("Fill Map with Texture");
        fillScreenButton.addActionListener(this);
        texturePanel.add(fillScreenButton);

    }

    public static void main(String[] args){

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        catch (Exception e) {
            System.out.println("Look and Feel not set");
        }

        settings = new Settings();

        new MapEditor();
    }

    private void fillScreenWithCharacter(String c){
        for(int i=0; i<editorPanel.getLevel().getMapWidth(); i++){
            for(int j=0; j<editorPanel.getLevel().getMapHeight(); j++){
                if(wallEditing) {
                    editorPanel.getLevel().getWallArray()[i][j] = c.charAt(0);
                }else if(floorEditing){
                    editorPanel.getLevel().getFloorArray()[i][j] = c.charAt(0);
                }else if(ceilEditing){
                    editorPanel.getLevel().getCeilArray()[i][j] = c.charAt(0);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == fillScreenButton){
            TextureEntry selectedEntry = (TextureEntry) charList.getSelectedValue();
            String c = selectedEntry.getCharacter();
            fillScreenWithCharacter(c);
        }

        //sets wallEditing, floorEditing, ceilEditing true or false
        if(e.getSource() == wallEdit){
            wallEditing = true;
            floorEditing = false;
            ceilEditing = false;
        }
        if(e.getSource() == floorEdit){
            wallEditing = false;
            floorEditing = true;
            ceilEditing = false;
        }
        if(e.getSource() == ceilEdit){
            wallEditing = false;
            floorEditing = false;
            ceilEditing = true;
        }

        if(e.getSource() == newFile){
            String width = JOptionPane.showInputDialog(this,
                    "Enter the width of the new map",
                    "New Map (width)", JOptionPane.PLAIN_MESSAGE);
            int w = Integer.parseInt(width);
            String height = JOptionPane.showInputDialog(this,
                    "Enter the height of the new map",
                    "New Map (height)", JOptionPane.PLAIN_MESSAGE);
            int h = Integer.parseInt(height);

            newFile(h, w);
        }
        if(e.getSource() == saveFile){
            saveFile();
        }
        if(e.getSource() == loadFile){
            loadFile();
        }

        if(e.getSource() == settingItem){
            new SettingsFrame(this);
        }

        if(e.getSource() == howtoItem){
            new HowtoFrame();
        }

        update();
    }

    public void update(){
        if(wallEditing){
            editorPanel.setLevel(level);
            editorPanel.setArrayForDisplay(level.getWallArray());
        }else if(floorEditing){
            editorPanel.setLevel(level);
            editorPanel.setArrayForDisplay(level.getFloorArray());
        }else if(ceilEditing){
            editorPanel.setLevel(level);
            editorPanel.setArrayForDisplay(level.getCeilArray());
        }

        displayArray();
    }

    public void resetColor(){
        editorPanel.setBackground(settings.getEditorBackgroundColor());
    }

    private void displayArray(){
        repaint();
    }

    //new empty level
    private void newFile(int w, int h){
        level = new Level(w, h);
    }

    //sets level
    private void loadFile(){
        int returnVal = fc.showOpenDialog(MapEditor.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();

//            String extension = getExtension(file);
//            if(!extension.equals("bbuu")){
//                JOptionPane.showMessageDialog(this, "File isn't a .bbuu");
//                return;
//            }


            level = new Level(file);
            System.out.println("Loaded: " + file.getName());

        }
    }
    private String getExtension(File file){
        String ext = null;
        String s = file.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }

    private void saveFile(){
        Character[][] wallArrayToSave = editorPanel.getLevel().getWallArray();
        Character[][] floorArrayToSave = editorPanel.getLevel().getFloorArray();
        Character[][] ceilArrayToSave = editorPanel.getLevel().getCeilArray();

        String mapData = "";

        if(use1.isSelected()) {
            mapData = writeFromArray(mapData, wallArrayToSave);
        }
        if(use2.isSelected()) {
            mapData += "/";
            mapData = writeFromArray(mapData, floorArrayToSave);
        }
        if(use3.isSelected()) {
            mapData += "/";
            mapData = writeFromArray(mapData, ceilArrayToSave);
        }

        fc.setDialogTitle("Save bambuu map as... (extension must be .bbuu or .txt)");
        int userSelection = fc.showSaveDialog(this);
        if(userSelection == JFileChooser.APPROVE_OPTION){
            File fileToSave = fc.getSelectedFile();
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave));
                writer.write(mapData);
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    private String writeFromArray(String string, Character[][] array){
        for(int i=0; i<editorPanel.getLevel().getMapWidth(); i++){
            for(int j=0; j<editorPanel.getLevel().getMapHeight(); j++){
                string += array[i][j].toString();
            }
        }
        return string;
    }

    private void drawTileAtMouse(MouseEvent e){
        TextureEntry selectedEntry = (TextureEntry) charList.getSelectedValue();
        String c = selectedEntry.getCharacter();
        editorPanel.drawTile(c, e.getX(), e.getY() - menuBarHeight - this.getInsets().top);
        update();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        drawTileAtMouse(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        drawTileAtMouse(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public HashMap<String, Texture> getMasterCharacterMap() {
        return masterCharacterMap;
    }

    public Settings getSettings() {
        return settings;
    }
}
