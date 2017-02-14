package core.settings;

import core.Game;

/**
 * Created by jilek on 09.02.2017.
 */
public class Configuration {

    public static int size = 20;
    public static int countBombs = 20;
    public static int tileSize = 25;
    public static int offset = 5;
    public static String picture = "/img/bombs/bomb1.png";
    public static Game game = new Game(Configuration.size);

    public static void loadDefaultConfiguration(){
        Configuration.size = 20;
        Configuration.countBombs = 20;
        Configuration.tileSize = 25;
        Configuration.offset = 5;
        Configuration.game = new Game(Configuration.size);
    }

    public static void loadEasyConfiguration(){
        Configuration.size = 10;
        Configuration.countBombs = 10;
        Configuration.game = new Game(Configuration.size);
    }

    public static void loadDifficultConfiguration(){
        Configuration.size = 20;
        Configuration.countBombs = 40;
        Configuration.game = new Game(Configuration.size);
    }

    public static void loadExpertConfiguration(){
        Configuration.size = 20;
        Configuration.countBombs = 70;
        Configuration.game = new Game(Configuration.size);
    }

    public static void loadUserConfiguration(int userSize, int userCountBombs, int userTileSize, String userPicture){
        Configuration.size = userSize;
        Configuration.countBombs = userCountBombs;
        Configuration.tileSize = userTileSize;
        Configuration.picture = userPicture;
        Configuration.offset = (int) Math.floor(Configuration.tileSize/5);
        Configuration.game = new Game(Configuration.size);
    }
}
