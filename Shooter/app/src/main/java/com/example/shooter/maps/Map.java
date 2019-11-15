package com.example.shooter.maps;

import com.example.shooter.objects.GameObjectContainer;

import java.util.ArrayList;

public class Map {
    /*private File mapFile;

    File folder = new File("C:/Users/Ivo/Desktop/Programy/Vytvořené/Ball shooter/src/main/resources/maps");
    File[] fileNames;
    private ArrayList<String> map;
    private GameObjectContainer gameOC;
    private int numberOfMaps;

    public Map(GameObjectContainer gameOC){

        this.fileNames = folder.listFiles();
        this.map = new ArrayList<String>();
        this.gameOC = gameOC;
        numberOfMaps = fileNames.length;

    }

    public int getNumberOfMaps() {
        return numberOfMaps;
    }

    public void getMap(int lvl) {

        map.clear();

        this.mapFile = fileNames[lvl];

        Scanner sc = null;
        try {
            sc = new Scanner(mapFile);
            while (sc.hasNext()){
                map.add(sc.next());
            }
            sc.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int row = 0;

        for(String s: map){

            for(int i = 0; i < s.length(); i++){
                if(s.charAt(i) == '1'){
                    gameOC.addObject(new SimpleBlock(i*61, row*61+8, 1));
                }
                else if(s.charAt(i) == '2'){
                    gameOC.addObject(new SimpleBlock(i*61, row*61+8,2));
                }
                else if(s.charAt(i) == '3'){
                    gameOC.addObject(new RobotEnemy(i*61, row*61+8));
                }
                else if(s.charAt(i) == '4'){
                    gameOC.addObject(new BallEnemy(i*61+20, row*61+8));
                }
                else if(s.charAt(i) == '5'){
                    gameOC.addObject(new EnemySoldier(i*61, row*61+8));
                }
                else if(s.charAt(i) == 'P'){
                    gameOC.addObject(new Player(i*61, row*61+8));
                }
                else if(s.charAt(i) == 'A'){
                    gameOC.addObject(new AmmoPack(i*61+20, row*61+60, 20));
                }
                else if(s.charAt(i) == 'M'){
                    gameOC.addObject(new AidKit(i*61+20, row*61+60, 20));
                }
                else if(s.charAt(i) == 'E'){
                    gameOC.addObject(new Exit(i*61+10, row*61+20));
                }

            }
            row++;
        }
    }*/
}
