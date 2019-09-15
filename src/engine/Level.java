package engine;

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
        mapWidth = w;
        mapHeight = h;

        wallArray = new Character[mapWidth][mapHeight];
        floorArray = new Character[mapWidth][mapHeight];
        ceilArray = new Character[mapWidth][mapHeight];

        for(int i=0; i<w; i++){
            for(int j=0; j<h; j++){
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
            String[] dataArray = data.split("/");

            mapWidth = (int) Math.sqrt(dataArray[0].length());
            mapHeight = mapWidth;

            wallArray = new Character[mapWidth][mapHeight];
            floorArray = new Character[mapWidth][mapHeight];
            ceilArray = new Character[mapWidth][mapHeight];

            for(int i=0; i<mapWidth; i++){
                for(int j=0; j<mapHeight; j++){
                    wallArray[j][i] = "0".charAt(0);
                    floorArray[j][i] = "0".charAt(0);
                    ceilArray[j][i] = "0".charAt(0);
                }
            }

            if(dataArray.length >= 1){
                loadWalls(dataArray[0]);
            }
            if(dataArray.length >= 2){
                loadFloors(dataArray[1]);
            }
            if(dataArray.length == 3){
                loadCeils(dataArray[2]);
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
        for(int i=0; i<mapWidth; i++){
            for(int j=0; j<mapHeight; j++){
                mapArray[j][i] = arrayData.charAt(j*mapWidth + i);
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
