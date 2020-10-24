
/**
 * Write a description of class Matriz here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Matriz
{
    //private static Matriz m = null;
    private int type;
    private int hoyoProfundidad;
    
    public Matriz() {
        type = 0;
        hoyoProfundidad = 0;
    }
    
    // private Matriz(){
        // type = 0;
        // hoyoProfundidad = 0;
    // }
    
    // public static Matriz getInstance() {
    
        // if (m==null) m = new Matriz();
        // return m;
        
    // }
    
    public void setType(int n) {
        type = n;
        hoyoProfundidad = 0;
    }
    
    public int getType() {
        return type;
    }
    
    public void setHoyo() {
         hoyoProfundidad = (int)(Math.random()*3+1);
    }
    
    public int getHoyo() {
        return hoyoProfundidad;
    }
    
    public void decHoyo() {
        hoyoProfundidad--;
    }
    
    public static void main(String[] args) {
        
    
    }
    
    
}
