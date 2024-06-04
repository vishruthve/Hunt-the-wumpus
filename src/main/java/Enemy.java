public class Enemy{
    private int enemyPosition = 0; 
    private boolean death = false; 

    public Enemy(int enemyPosition) {
        this.enemyPosition = enemyPosition; 
    }

    public int returnEnemyPosition() { 
        return enemyPosition; 
    }
    public void setEnemyPosition(int pos) {
        this.enemyPosition = pos; 
    }

}                                                      