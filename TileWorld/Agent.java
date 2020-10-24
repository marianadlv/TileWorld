/**
 * Abstract class Agent - write a description of the class here
 * 
 * @author: 
 * Date: 
 */
public abstract class Agent
{
    protected static int sizeMatrix = Game.getInstance().getSizeMatrix();
    protected int dir = 0;
    protected boolean alive = true;
    protected int rPos;
    protected int cPos;
    protected Matriz[][] matrix = new Matriz[sizeMatrix][sizeMatrix];
    protected int moves = 0;
    protected int points = 0;
    protected int[] finalMoves = new int[Game.getInstance().maxTest];
    protected int[] finalPoints = new int[Game.getInstance().maxTest];
    
    public void setFinal(int moves, int points, int test) {
    
        finalMoves[test] = moves;
        finalPoints[test] = points;
    
    }
    
    public int getFinalMoves(int i) {
        return finalMoves[i];
    }
    
    public int getFinalPoints(int i) {
        return finalPoints[i];
    }
    
    public void setPos(int r, int c) {
    
        rPos = r;
        cPos = c;
    
    }
    
    public boolean getLife() {
    
        return alive;
    
    }
    
    public int getPosR() {
    
       return rPos;
        
    }
    
    public int getPosC() {
    
       return cPos;
        
    }
    
    public int getMoves() {
    
        return moves;
    
    }
    
    public int getPoints() {
    
        return points;
    
    }
    
    public void revive() {
        alive = true;
        moves = 0;
        points = 0;
    }
    
    public void update() {}
    
    public void sense() {}
    
    public void decide() {}
    
    public void act() {}
    
}
