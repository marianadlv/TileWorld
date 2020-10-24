
/**
 * Write a description of class Agent1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class AgentRandom extends Agent
{
    private static AgentRandom a = null;
  
    private AgentRandom() {}
    
    public static AgentRandom getInstance() {
    
        if (a == null) a = new AgentRandom();
        return a;
        
    }
    
    public void update() {
    
        sense();
        decide();
        act();
    
    }
    
    public void validarAgentRandom() {
    
        if (rPos == 0) {
        
            if (cPos == 0) {
            
                if (matrix[rPos][cPos+1].getType() == 1 && matrix[rPos+1][cPos].getType() == 1) alive = false;
                
            } else if (cPos == sizeMatrix-1) {
            
                if (matrix[rPos][cPos-1].getType() == 1 && matrix[rPos+1][cPos].getType() == 1) alive = false;
            
            } else {
            
                if (matrix[rPos][cPos+1].getType() == 1 && matrix[rPos][cPos-1].getType() == 1 && matrix[rPos+1][cPos].getType() == 1) alive = false;
            
            }
        
        } else if (rPos == sizeMatrix-1) {
        
            if (cPos == 0) {
            
                if (matrix[rPos][cPos+1].getType() == 1 && matrix[rPos-1][cPos].getType() == 1) alive = false;
                
            } else if (cPos == sizeMatrix-1) {
            
                if (matrix[rPos][cPos-1].getType() == 1 && matrix[rPos-1][cPos].getType() == 1) alive = false;
            
            } else {
            
                if (matrix[rPos][cPos+1].getType() == 1 && matrix[rPos][cPos-1].getType() == 1 && matrix[rPos-1][cPos].getType() == 1) alive = false;
            
            }
        
        } else if (cPos == 0) {
        
            if (rPos == 0) {
            
                if (matrix[rPos][cPos+1].getType() == 1 && matrix[rPos+1][cPos].getType() == 1) alive = false;
                
            } else if (rPos == sizeMatrix-1) {
            
                if (matrix[rPos][cPos+1].getType() == 1 && matrix[rPos-1][cPos].getType() == 1) alive = false;
            
            } else {
            
                if (matrix[rPos][cPos+1].getType() == 1 && matrix[rPos-1][cPos].getType() == 1 && matrix[rPos+1][cPos].getType() == 1) alive = false;
            
            }
        
        } else if (cPos == sizeMatrix-1) {
        
           if (rPos == 0) {
            
                if (matrix[rPos][cPos-1].getType() == 1 && matrix[rPos+1][cPos].getType() == 1) alive = false;
                
            } else if (rPos == sizeMatrix-1) {
            
                if (matrix[rPos][cPos-1].getType() == 1 && matrix[rPos-1][cPos].getType() == 1) alive = false;
            
            } else {
            
                if (matrix[rPos][cPos-1].getType() == 1 && matrix[rPos-1][cPos].getType() == 1 && matrix[rPos+1][cPos].getType() == 1) alive = false;
            
            } 
        
        } else {
        
            if (matrix[rPos][cPos-1].getType() == 1 && matrix[rPos][cPos+1].getType() == 1 && matrix[rPos-1][cPos].getType() == 1 && matrix[rPos+1][cPos].getType() == 1) alive = false;
        
        }
    
    }
    
    
    public void sense() {
    
        for (int i=0; i<sizeMatrix; i++) {             // tener mapa
        
            for (int j=0; j<sizeMatrix; j++) {
            
                matrix[i][j] = Game.getInstance().getMatrix(i,j);
                
            }
        
        }  
        
        
    
    }
    
    public void decide() {
    
         dir = (int)(Math.random()*4+1);             // entre 1 y 4 --> 4 direcciones

    }
    
    // 1 = arriba, 2 = abajo, 3 = izquierda, 4 = derecha
    
    public void act() {
        
        if (matrix[rPos][cPos].getType() == 3) {
            alive = false;
            return;
        } 
        
        matrix[rPos][cPos].setType(4);
    
        if (dir == 1 && rPos != 0) {
        
           if (matrix[rPos-1][cPos].getType() == 2 && rPos != 1) {  // si arriba es bloque y no está en la pared
               
               if (matrix[rPos-2][cPos].getType() == 0) {            // si es path
                
                   Game.getInstance().matrix[rPos-2][cPos].setType(2);
                   Game.getInstance().matrix[rPos-1][cPos].setType(4);
                   Game.getInstance().matrix[rPos][cPos].setType(0);
                   moves++;
                
               } else if (matrix[rPos-2][cPos].getType() == 3) {            // si es hoyo
                
                   if (matrix[rPos-2][cPos].getHoyo() == 1) {
                   
                      Game.getInstance().matrix[rPos-2][cPos].setType(0);
                      Game.getInstance().matrix[rPos-1][cPos].setType(4);
                      Game.getInstance().matrix[rPos][cPos].setType(0);
                      Game.getInstance().matrix[rPos+2][cPos].decHoyo();
                      points++;
                      moves++;
                    
                   } else {
                    
                      Game.getInstance().matrix[rPos-1][cPos].setType(4);
                      Game.getInstance().matrix[rPos][cPos].setType(0); 
                      Game.getInstance().matrix[rPos-2][cPos].decHoyo();
                      points++;
                      moves++;
                    
                   } 
                   
               }
               
           } else if (matrix[rPos-1][cPos].getType() == 0) {
           
               Game.getInstance().matrix[rPos-1][cPos].setType(4);
               Game.getInstance().matrix[rPos][cPos].setType(0);
               moves++;
               
           } else if (matrix[rPos-1][cPos].getType() == 3) {
           
               Game.getInstance().matrix[rPos][cPos].setType(0);
               alive = false;
               moves++;
            
           }
        
        } else if (dir == 2 && rPos != sizeMatrix-1) {
        
           if (matrix[rPos+1][cPos].getType() == 2 && rPos != sizeMatrix-2) {  // si arriba es bloque y no está en la pared
               
               if (matrix[rPos+2][cPos].getType() == 0) {            // si es path
                
                   Game.getInstance().matrix[rPos+2][cPos].setType(2);
                   Game.getInstance().matrix[rPos+1][cPos].setType(4);
                   Game.getInstance().matrix[rPos][cPos].setType(0);
                   moves++;
                
               } else if (matrix[rPos+2][cPos].getType() == 3) {            // si es hoyo
                
                   if (matrix[rPos+2][cPos].getHoyo() == 1) {
                   
                      Game.getInstance().matrix[rPos+2][cPos].setType(0);
                      Game.getInstance().matrix[rPos+1][cPos].setType(4);
                      Game.getInstance().matrix[rPos][cPos].setType(0);
                      Game.getInstance().matrix[rPos+2][cPos].decHoyo();
                      points++;
                      moves++;
                    
                   } else {
                    
                      Game.getInstance().matrix[rPos+1][cPos].setType(4);
                      Game.getInstance().matrix[rPos][cPos].setType(0); 
                      Game.getInstance().matrix[rPos+2][cPos].decHoyo();
                      points++;
                      moves++;
                    
                   } 
                   
               }
               
           } else if (matrix[rPos+1][cPos].getType() == 0) {
           
               Game.getInstance().matrix[rPos+1][cPos].setType(4);
               Game.getInstance().matrix[rPos][cPos].setType(0);
               moves++;
               
           } else if (matrix[rPos+1][cPos].getType() == 3) {
           
               Game.getInstance().matrix[rPos][cPos].setType(0);
               alive = false;
               moves++;
            
           }
       
        
          } else if (dir == 3 && cPos != 0) {
        
            if (matrix[rPos][cPos-1].getType() == 2 && cPos != 1) {  // si arriba es bloque y no está en la pared
               
               if (matrix[rPos][cPos-2].getType() == 0) {            // si es path
                
                   Game.getInstance().matrix[rPos][cPos-2].setType(2);
                   Game.getInstance().matrix[rPos][cPos-1].setType(4);
                   Game.getInstance().matrix[rPos][cPos].setType(0);
                   moves++;
                
               } else if (matrix[rPos][cPos-2].getType() == 3) {            // si es hoyo
                
                   if (matrix[rPos][cPos-2].getHoyo() == 1) {
                   
                      Game.getInstance().matrix[rPos][cPos-2].setType(0);
                      Game.getInstance().matrix[rPos][cPos-1].setType(4);
                      Game.getInstance().matrix[rPos][cPos].setType(0);
                      Game.getInstance().matrix[rPos][cPos-2].decHoyo();
                      points++;
                      moves++;
                    
                   } else {
                    
                      Game.getInstance().matrix[rPos][cPos-1].setType(4);
                      Game.getInstance().matrix[rPos][cPos].setType(0); 
                      Game.getInstance().matrix[rPos][cPos-2].decHoyo();
                      points++;
                      moves++;
                    
                   } 
                   
               }
               
           } else if (matrix[rPos][cPos-1].getType() == 0) {
           
               Game.getInstance().matrix[rPos][cPos-1].setType(4);
               Game.getInstance().matrix[rPos][cPos].setType(0);
               moves++;
               
           } else if (matrix[rPos][cPos-1].getType() == 3) {
           
               Game.getInstance().matrix[rPos][cPos].setType(0);
               alive = false;
               moves++;
            
           }

        } else if (dir == 4 && cPos != sizeMatrix-1) {
        
            if (matrix[rPos][cPos+1].getType() == 2 && cPos != sizeMatrix-2) {  // si arriba es bloque y no está en la pared
               
               if (matrix[rPos][cPos+2].getType() == 0) {            // si es path
                
                   Game.getInstance().matrix[rPos][cPos+2].setType(2);
                   Game.getInstance().matrix[rPos][cPos+1].setType(4);
                   Game.getInstance().matrix[rPos][cPos].setType(0);
                   moves++;
                
               } else if (matrix[rPos][cPos+2].getType() == 3) {            // si es hoyo
                
                   if (matrix[rPos][cPos+2].getHoyo() == 1) {
                   
                      Game.getInstance().matrix[rPos][cPos+2].setType(0);
                      Game.getInstance().matrix[rPos][cPos+1].setType(4);
                      Game.getInstance().matrix[rPos][cPos].setType(0);
                      Game.getInstance().matrix[rPos][cPos+2].decHoyo();
                      points++;
                      moves++;
                    
                   } else {
                    
                      Game.getInstance().matrix[rPos][cPos+1].setType(4);
                      Game.getInstance().matrix[rPos][cPos].setType(0); 
                      Game.getInstance().matrix[rPos][cPos+2].decHoyo();
                      points++;
                      moves++;
                    
                   } 
                   
               }
               
           } else if (matrix[rPos][cPos+1].getType() == 0) {
           
               Game.getInstance().matrix[rPos][cPos+1].setType(4);
               Game.getInstance().matrix[rPos][cPos].setType(0);
               moves++;
               
           } else if (matrix[rPos][cPos+1].getType() == 3) {
           
               Game.getInstance().matrix[rPos][cPos].setType(0);
               alive = false;
               moves++;
            
           }
    
        }
    
    }
    
}
