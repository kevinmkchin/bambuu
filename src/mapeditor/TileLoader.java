package mapeditor;

import engine.Texture;

public class TileLoader {

    public static Texture getTile(String c){
        if(c.length() == 1) {
            return new Texture("TILES/" + c + MapEditor.settings.getImageExtension(),
                    MapEditor.settings.getTileSize());
        }else{
            return null;
        }
    }

}
