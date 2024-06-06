public class Player{ 
    private int playerPosition = 0;
    private boolean death = false;
    private int num_arrows = 0;
    private int gold = 100; 


    public Player(){}
    public Player(int playerPos, int g) {
        this.playerPosition = playerPos; 
        this.gold = g; 
    }
   
    public int getPosition(){ return playerPosition; }
    public void setPosition(int p){ playerPosition = p; }
    public int getGold(){return gold;}
    public int getArrows(){return num_arrows;}
    public void decGold(){gold--;}
    public void addArrow(){num_arrows++;}
}
