import javax.swing.*;
import java.awt.*;

public class Board {

    private Cell[][] mapPlayer = new Cell[10][10];
    private static Cell[][] map = new Cell[10][10];
    private static JPanel startCell = new JPanel();
    public static Treasure[] questItems = new Treasure[8];

    public Board(boolean isPlayer) {
        if (isPlayer) {
            createPlayerMap();
        } else
            createMap();
    }

    private void createPlayerMap() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                mapPlayer[i][j]=new Cell(false,Color.lightGray,i,j);
            }
        }
    }

    private void createMap() {
        setCastle();
        setTreasures();
        setLoot();
        setMarkets();
        setWalls();
        setTraps();
        setBlocks();
        setStartCell(new JPanel());
    }

    public static JPanel getStartCell() {
        return startCell;
    }

    public static void setStartCell(JPanel startCell) {
        startCell.setBounds(0,500,50,32);
        startCell.setBackground(new Color(0xD3B10A));
        Board.startCell = startCell;
    }

    private void setCastle() {
        int x, y;
        do {
            x = createRandomNumber();
            y = createRandomNumber();
        } while ((x != 4 && x != 5) || (y != 4 && y != 5));
        map[x][y] = new Castle(true, Color.yellow, x, y);
    }

    private void setTreasures() {
        int x, y;
        for (int i = 0; i < 2; i++) {
            do {
                x = createRandomNumber();
                y = createRandomNumber();
            } while (x > 4 || y > 4);
            if (map[x][y] == null) {
                map[x][y] = new Treasure(true, x, y);
            } else i--;
        }
        for (int i = 0; i < 2; i++) {
            do {
                x = createRandomNumber();
                y = createRandomNumber();
            } while (x > 4 || y < 5);
            if (map[x][y] == null) {
                map[x][y] = new Treasure(true, x, y);
            } else i--;
        }
        for (int i = 0; i < 2; i++) {
            do {
                x = createRandomNumber();
                y = createRandomNumber();
            } while (x < 5 || y > 4);
            if (map[x][y] == null) {
                map[x][y] = new Treasure(true, x, y);
            } else i--;
        }
        for (int i = 0; i < 2; i++) {
            do {
                x = createRandomNumber();
                y = createRandomNumber();
            } while (x < 5 || y < 5);
            if (map[x][y] == null) {
                map[x][y] = new Treasure(true, x, y);
            } else i--;
        }
        for (int i = 0; i < 8; i++){
            do {
                x= createRandomNumber();
                y = createRandomNumber();
            }while (!(map[x][y]instanceof Treasure));
            if (!((Treasure) map[x][y]).checked) {
                questItems[i] = (Treasure) map[x][y];
                ((Treasure) map[x][y]).checked = true;
            }
            else i--;
        }
    }

    private void setLoot() {
        int x, y;
        for (int i = 0; i < 13; i++) {
            x = createRandomNumber();
            y = createRandomNumber();
            if (map[x][y] == null) {
                map[x][y] = new Loot(true, Color.blue, x, y);
            } else i--;
        }
    }

    private void setMarkets() {
        int x, y;
        for (int i = 0; i < 5; i++) {
            x = createRandomNumber();
            y = createRandomNumber();
            if (map[x][y] == null) {
                map[x][y] = new Market(true, Color.orange, x, y);
            } else i--;
        }
    }

    private void setWalls() {
        int x, y;
        int randomNumber = createRandomNumber();
        for (int i = 0; i < randomNumber; i++) {
            x = createRandomNumber();
            y = createRandomNumber();
            if (map[x][y] == null && (x!=0 || y!=0)) {
                map[x][y] = new Wall(true, Color.black, x, y);
            } else i--;
        }
    }

    private void setTraps() {
        int x, y;
        int randomNumber = createRandomNumber();
        for (int i = 0; i < randomNumber; i++) {
            x = createRandomNumber();
            y = createRandomNumber();
            if (map[x][y] == null) {
                map[x][y] = new Traps(true, Color.red, x, y);
            } else i--;
        }
    }

    private void setBlocks() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (map[i][j] == null) {
                    map[i][j] = new Block(true, Color.white, i, j);
                }
            }
        }
    }

    public static int createRandomNumber() {
        int x;
        x = (int) (Math.random() * 10);
        return x;
    }

    public static Cell[][] getMap() {
        return map;
    }

    public Cell[][] getMapPlayer() {
        return mapPlayer;
    }

    class Treasure extends Cell{
        String name;
        static int num=0;
        int value;
        boolean checked =false;
        public Treasure(boolean showSw, int x, int y) {
            super(showSw, Color.green, x, y);
            switch (num) {
                case 0 -> {
                    name = "Diamond Ring";
                    value = 500;
                }
                case 1 -> {
                    name = "Jeweled Sword";
                    value = 550;
                }
                case 2 -> {
                    name = "Golden Glass";
                    value = 480;
                }
                case 3 -> {
                    name = "Glass Cup";
                    value = 450;
                }
                case 4 -> {
                    name = "Wooden Bow";
                    value = 410;
                }
                case 5 -> {
                    name = "Steel Shield";
                    value = 460;
                }
                case 6 -> {
                    name = "Golden Key";
                    value = 520;
                }
                case 7 -> {
                    name = "Dragon Scroll";
                    value = 540;
                }
            }
            num++;
            if (num == 8)
                num=0;
        }
    }
    class Wall extends Cell{

        public Wall(boolean showSw, Color color, int x, int y) {
            super(showSw, color, x, y);
        }
    }
    class Castle extends Cell{

        public Castle(boolean showSw, Color color, int x, int y) {
            super(showSw, color, x, y);
        }
    }
    class Loot extends Cell{
        private int value;
        public Loot(boolean showSw, Color color, int x, int y) {
            super(showSw, color, x, y);
            setValue();
        }
        public void setValue(){
            int x;
            int y = createRandomNumber();
            do {
                x= createRandomNumber();
            }while (x<5);
            value = (x * 20) + (y * 5);
        }
        public int getValue(){
            return value;
        }
    }
    class Market extends Cell{

        public Market(boolean showSw, Color color, int x, int y) {
            super(showSw, color, x, y);
        }
    }
    class Traps extends Cell {
        private boolean isCostMoney;
        private boolean isCostPower;
        private int costMoneyValue;
        private int costPowerValue;

        public Traps(boolean showSw, Color color, int x, int y) {
            super(showSw, color, x, y);
            setCostMoney();
            setCostPower();
            if (isCostMoney)
                setCostMoneyValue();
            if (isCostPower)
                setCostPowerValue();
        }

        private void setCostMoney() {
            int x = createRandomNumber();
            isCostMoney = x > 4;
        }

        private void setCostPower() {
            int x = createRandomNumber();
            isCostPower = x > 4;
        }
        private void setCostMoneyValue(){
            int x;
            do {
                x= createRandomNumber();
            }while (x>4);
            costMoneyValue = (x+1)*15;
        }
        private void setCostPowerValue(){
            int x;
            do {
                x= createRandomNumber();
            }while (x>4);
            costPowerValue = (x+1) * 12;
        }

        public int getCostMoneyValue() {
            return costMoneyValue;
        }

        public int getCostPowerValue() {
            return costPowerValue;
        }
    }
    static class Block extends Cell{

        public Block(boolean showSw, Color color, int x, int y) {
            super(showSw, color, x, y);
        }
    }
}
