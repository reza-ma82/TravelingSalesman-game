public class Player {

    private String name;
    private Board map ;
    private int score;
    private int money;
    private Piece piece;
    private int power;
    boolean findTreasure;
    int location[]= {-1,-1};
    private Dice dice=new Dice();
    public static int turn = 1;
    public static int winner;
    static int remainingMoves;
    public Player(String name,Board map, Piece piece,int score,int money,int power) {
        this.name = name;
        this.map = map;
        this.piece = piece;
        this.score = score;
        this.money = money;
        this.power =power;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Board getMap() {
        return map;
    }

    public void setMap(Board map) {
        this.map = map;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Dice getDice() {
        return dice;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
