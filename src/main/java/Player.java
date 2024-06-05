public class Player{ 
    private int playerPosition = 0;
    private boolean death = false;
    private int num_arrows;
    private int gold; 


    
    public Player(int playerPos, int g) {
        this.playerPosition = playerPos; 
        this.gold = g; 
    }
   
    public int getPosition(){ return playerPosition; }
    public void setPosition(int p){ playerPosition = p; }
}
