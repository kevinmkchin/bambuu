package engine;

import mapeditor.MapEditor;

import java.io.*;

public class Level {


    //maps are divided into wallTileMaps, floorTileMaps, ceilingTileMaps, staticSpriteTileMaps?
    /*first string of characters must be wallTileMap
    * second string of characters must be floorTileMap
    * third string of characters must be ceilTileMap
    * fourth string of characters must be map information
    * every following string must be entity.Sprite information*/


    public Character[][] wallArray, floorArray, ceilArray;
    public int mapWidth;
    public int mapHeight;
    public static final String OPEN_SPACE = "0"; //same for walls, floors, ceilings

    public Level(int w, int h){
        mapWidth = h;
        mapHeight = w;

        wallArray = new Character[mapHeight][mapWidth];
        floorArray = new Character[mapHeight][mapWidth];
        ceilArray = new Character[mapHeight][mapWidth];

        for(int i=0; i<mapHeight; i++){
            for(int j=0; j<mapWidth; j++){
                wallArray[i][j] = OPEN_SPACE.charAt(0);
                floorArray[i][j] = OPEN_SPACE.charAt(0);
                ceilArray[i][j] = OPEN_SPACE.charAt(0);
            }
        }
    }

    public Level(File file){
        loadMap(file);
    }

    private void loadMap(File file){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String data = reader.readLine();
            String[] dataArray = data.split(MapEditor.settings.getLayerDelimiter());

            if(dataArray[0].contains(MapEditor.settings.getLineDelimiter())) {
                String[] a = dataArray[0].split("[" + MapEditor.settings.getLineDelimiter() + "]");
                mapWidth = a[0].length();
            }else{
                mapWidth = (int) Math.sqrt(dataArray[0].length());
            }
            mapHeight = dataArray[0].length() / mapWidth;

            wallArray = new Character[mapHeight][mapWidth];
            floorArray = new Character[mapHeight][mapWidth];
            ceilArray = new Character[mapHeight][mapWidth];

            for(int i=0; i<mapHeight; i++){
                for(int j=0; j<mapWidth; j++){
                    wallArray[i][j] = "0".charAt(0);
                    floorArray[i][j] = "0".charAt(0);
                    ceilArray[i][j] = "0".charAt(0);
                }
            }

            if(dataArray.length >= 1){
                String str = dataArray[0].replaceAll("[" + MapEditor.settings.getLineDelimiter() + "]", "");
                loadWalls(str);
            }
            if(dataArray.length >= 2){
                String str = dataArray[1].replaceAll("[" + MapEditor.settings.getLineDelimiter() + "]", "");
                loadFloors(str);
            }
            if(dataArray.length == 3){
                String str = dataArray[2].replaceAll("[" + MapEditor.settings.getLineDelimiter() + "]", "");
                loadCeils(str);
            }


        } catch (FileNotFoundException e) {
            System.out.println("Couldn't locate the map file.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadWalls(String wallData){
        loadToArray(wallArray, wallData);
    }

    private void loadFloors(String floorData){
        loadToArray(floorArray, floorData);
    }

    private void loadCeils(String ceilData){
        loadToArray(ceilArray, ceilData);
    }

    private void loadToArray(Character[][] mapArray, String arrayData){
        for(int i=0; i<mapHeight; i++){
            for(int j=0; j<mapWidth; j++){
                mapArray[i][j] = arrayData.charAt(i*mapWidth + j);
            }
        }
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public Character[][] getWallArray() {
        return wallArray;
    }

    public Character[][] getFloorArray() {
        return floorArray;
    }

    public Character[][] getCeilArray() {
        return ceilArray;
    }

}
