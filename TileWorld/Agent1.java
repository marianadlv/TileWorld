
/**
 * Write a description of class Agent1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Agent1 extends Agent
{
    private static Agent1 a = null;
    private static int dir;
    private static boolean bloque;
    private static boolean hoyo;
  
    private Agent1() {}
    
    public static Agent1 getInstance() {
    
        if (a == null) a = new Agent1();
        return a;
        
    }
    
    
    public void sense() {
    
        for (int i=0; i<sizeMatrix; i++) {             // tener mapa
        
            for (int j=0; j<sizeMatrix; j++) {
            
                matrix[i][j] = Game.getInstance().getMatrix(i,j);
                //System.out.println(matrix[i][j]);
            
            }
        
        }  
        
        
    
    }
    
    public void update() {
    
        sense();
        decide();
    
    }
    
    public void decide() {
        
        if (matrix[rPos][cPos].getType() == 3) {
            alive = false;
            return;
        } 
        
        matrix[rPos][cPos].setType(4);
        
        boolean mover = false;
 
        if (rPos!=0 && rPos!=1 && matrix[rPos-1][cPos].getType()==2) {          // si arriba es bloque
        
            if (matrix[rPos-2][cPos].getType()!=2 && matrix[rPos-2][cPos].getType()!=1) {
                mover = true;
                if (matrix[rPos-2][cPos].getType()==0) actFirst(1,true,false);       // mover arriba el bloque
                else if (matrix[rPos-2][cPos].getType()==3) actFirst (1,true,true);      // tapar hoyo arriba
                return;
            }
        
        } if (cPos!=sizeMatrix-1 && cPos!=sizeMatrix-2 && matrix[rPos][cPos+1].getType()==2) {     // si derecha es bloque
        
            if (matrix[rPos][cPos+2].getType()!=2 && matrix[rPos][cPos+2].getType()!=1) {
                mover = true;
                if (matrix[rPos][cPos+2].getType()==0) actFirst(4,true,false);       // mover derecha el bloque
                else if (matrix[rPos][cPos+2].getType()==3) actFirst (4,true,true);      // tapar hoyo derecha
                return;
            }

        } if (rPos!=sizeMatrix-1 && rPos!=sizeMatrix-2 && matrix[rPos+1][cPos].getType()==2) {     // si abajo es bloque
        
            if (matrix[rPos+2][cPos].getType()!=2 && matrix[rPos+2][cPos].getType()!=1) {
                mover = true;
                if (matrix[rPos+2][cPos].getType()==0) actFirst(2,true,false);       // mover abajo el bloque
                else if (matrix[rPos+2][cPos].getType()==3) actFirst (2,true,true);      // tapar hoyo abajo
                return;
            }
        
        } if (cPos!=0 && cPos!=1 && matrix[rPos][cPos-1].getType()==2) {     // si izquierda es bloque
        
            if (matrix[rPos][cPos-2].getType()!=2 && matrix[rPos][cPos-2].getType()!=1) {
                mover = true;
                if (matrix[rPos][cPos-2].getType()==0) actFirst(3,true,false);       // mover izquierda el bloque
                else if (matrix[rPos][cPos-2].getType()==3) actFirst (3,true,true);      // tapar hoyo izquierda
                return;
            }
        
        } 
        
        if ( (rPos!=0 && matrix[rPos-1][cPos].getType()==0)||(cPos!=sizeMatrix-1 && matrix[rPos][cPos+1].getType()==0)||(rPos!=sizeMatrix-1 && matrix[rPos+1][cPos].getType()==0)||(cPos!=0 && matrix[rPos][cPos-1].getType()==0) ) {
        
            while (true) {
            
                int dir = (int)(Math.random()*4+1);
                if (rPos!=0 && dir == 1 && matrix[rPos-1][cPos].getType()==0) {          // si arriba está libre
                    mover = true;
                    actFirst(1,false,false);
                    return;    
                } else if (cPos!=sizeMatrix-1 && dir == 4 && matrix[rPos][cPos+1].getType()==0) {          // si derecha está libre
                    mover = true;
                    actFirst(4,false,false);
                    return;
                } else if (rPos!=sizeMatrix-1 && dir == 2 && matrix[rPos+1][cPos].getType()==0) {          // si abajo está libre
                    mover = true;
                    actFirst(2,false,false);
                    return;
                } else if (cPos!=0 && dir == 3 && matrix[rPos][cPos-1].getType()==0) {          // si izquierda está libre
                    mover = true;
                    actFirst(3,false,false);
                    
                    return;
                }
            
            }
            
        } else alive = false;

    }
    
    // 1 = arriba, 2 = abajo, 3 = izquierda, 4 = derecha
    
    public void actFirst(int d, boolean b, boolean h) {
    
        dir = d;
        bloque = b;
        hoyo = h;
        act();
    
    }
    
    public void act() {
        
        if (dir == 1) {
        
            if (bloque && !hoyo) {
            
                Game.getInstance().matrix[rPos-2][cPos].setType(2);
                Game.getInstance().matrix[rPos-1][cPos].setType(4);
                Game.getInstance().matrix[rPos][cPos].setType(0);
                moves++;
                
            } else if (bloque && hoyo) {
            
                if (matrix[rPos-2][cPos].getHoyo() == 1) {
                   
                      Game.getInstance().matrix[rPos-2][cPos].setType(0);
                      Game.getInstance().matrix[rPos-1][cPos].setType(4);
                      Game.getInstance().matrix[rPos][cPos].setType(0);
                      Game.getInstance().matrix[rPos-2][cPos].decHoyo();
                      points++;
                      moves++;
                    
                } else {
                    
                      Game.getInstance().matrix[rPos-1][cPos].setType(4);
                      Game.getInstance().matrix[rPos][cPos].setType(0); 
                      Game.getInstance().matrix[rPos-2][cPos].decHoyo();
                      points++;
                      moves++;
            
                }
        
            } else if (!bloque && !hoyo) {
                
               Game.getInstance().matrix[rPos-1][cPos].setType(4);
               Game.getInstance().matrix[rPos][cPos].setType(0);
               moves++;
            
            }
            
        } else if (dir == 2) {          // abajo
        
            if (bloque && !hoyo) {
            
                Game.getInstance().matrix[rPos+2][cPos].setType(2);
                Game.getInstance().matrix[rPos+1][cPos].setType(4);
                Game.getInstance().matrix[rPos][cPos].setType(0);
                moves++;
                
            } else if (bloque && hoyo) {
            
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
        
            } else if (!bloque && !hoyo) {
                
               Game.getInstance().matrix[rPos+1][cPos].setType(4);
               Game.getInstance().matrix[rPos][cPos].setType(0);
               moves++;
            
            }
            
        } else if (dir == 3) {
        
            if (bloque && !hoyo) {
            
                Game.getInstance().matrix[rPos][cPos-2].setType(2);
                Game.getInstance().matrix[rPos][cPos-1].setType(4);
                Game.getInstance().matrix[rPos][cPos].setType(0);
                moves++;
                
            } else if (bloque && hoyo) {
            
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
        
            } else if (!bloque && !hoyo) {
                
               Game.getInstance().matrix[rPos][cPos-1].setType(4);
               Game.getInstance().matrix[rPos][cPos].setType(0);
               moves++;
            
            }
            
            
        
        } else if (dir == 4) {
        
            if (bloque && !hoyo) {
            
                Game.getInstance().matrix[rPos][cPos+2].setType(2);
                Game.getInstance().matrix[rPos][cPos+1].setType(4);
                Game.getInstance().matrix[rPos][cPos].setType(0);
                moves++;
                
            } else if (bloque && hoyo) {
            
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
        
            } else if (!bloque && !hoyo) {
                
               Game.getInstance().matrix[rPos][cPos+1].setType(4);
               Game.getInstance().matrix[rPos][cPos].setType(0);
               moves++;
            
            }
        
        }
        
    }
    
}
