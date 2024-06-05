public class Player{ 
    private int playerPosition = 0;
    private boolean death = false;
    private int num_arrows; 

    
    public Player(int playerPos) {
        this.playerPosition = playerPos; 
    }
   
    public int getPosition(){ return playerPosition; }
    public void setPosition(int p){ playerPosition = p; }
}
