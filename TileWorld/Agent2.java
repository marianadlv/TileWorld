
/**
 * Write a description of class Agent2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Agent2 extends Agent
{
    private static Agent2 a = null;
    private static int dir;
    private static boolean bloque;
    private static boolean hoyo;
    private static int cont = 0;
    private static int dir2 = 0;
    private static boolean hayHoyo;
  
    private Agent2() {}
    
    public static Agent2 getInstance() {
    
        if (a == null) a = new Agent2();
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
        
        while (cont > 0) {
            if (cont == 1) actFirst(dir2,true,hayHoyo);
            else actFirst(dir2,false,false);
            cont--;
            return;
        }
 
        if (rPos!=0 && rPos!=1 && matrix[rPos-1][cPos].getType()==2) {          // si arriba es bloque
        
            if (matrix[rPos-2][cPos].getType()!=2 && matrix[rPos-2][cPos].getType()!=1) {

                if (matrix[rPos-2][cPos].getType()==0) actFirst(1,true,false);       // mover arriba el bloque
                else if (matrix[rPos-2][cPos].getType()==3) actFirst (1,true,true);      // tapar hoyo arriba
                return;
            }
        
        } if (cPos!=sizeMatrix-1 && cPos!=sizeMatrix-2 && matrix[rPos][cPos+1].getType()==2) {     // si derecha es bloque
        
            if (matrix[rPos][cPos+2].getType()!=2 && matrix[rPos][cPos+2].getType()!=1) {

                if (matrix[rPos][cPos+2].getType()==0) actFirst(4,true,false);       // mover derecha el bloque
                else if (matrix[rPos][cPos+2].getType()==3) actFirst (4,true,true);      // tapar hoyo derecha
                return;
            }

        } if (rPos!=sizeMatrix-1 && rPos!=sizeMatrix-2 && matrix[rPos+1][cPos].getType()==2) {     // si abajo es bloque
        
            if (matrix[rPos+2][cPos].getType()!=2 && matrix[rPos+2][cPos].getType()!=1) {

                if (matrix[rPos+2][cPos].getType()==0) actFirst(2,true,false);       // mover abajo el bloque
                else if (matrix[rPos+2][cPos].getType()==3) actFirst (2,true,true);      // tapar hoyo abajo
                return;
            }
        
        } if (cPos!=0 && cPos!=1 && matrix[rPos][cPos-1].getType()==2) {     // si izquierda es bloque
        
            if (matrix[rPos][cPos-2].getType()!=2 && matrix[rPos][cPos-2].getType()!=1) {
 
                if (matrix[rPos][cPos-2].getType()==0) actFirst(3,true,false);       // mover izquierda el bloque
                else if (matrix[rPos][cPos-2].getType()==3) actFirst (3,true,true);      // tapar hoyo izquierda
                return;
            }
        
        } 
        
        if ( (rPos!=0 && matrix[rPos-1][cPos].getType()==0)||(cPos!=sizeMatrix-1 && matrix[rPos][cPos+1].getType()==0)||(rPos!=sizeMatrix-1 && matrix[rPos+1][cPos].getType()==0)||(cPos!=0 && matrix[rPos][cPos-1].getType()==0) ) {
            
            // antes de random buscar bloque en row o column
            
            if (rPos!=0 && rPos!=1 && rPos!=2 && matrix[rPos-1][cPos].getType()==0) {         /// arriba
            
                for (int r=rPos-2; r>0; r--) {
                
                    if (matrix[r][cPos].getType()==3||matrix[r][cPos].getType()==1) break;
                    if (matrix[r][cPos].getType()==2) {
                        if (matrix[r-1][cPos].getType()==0 ||matrix[r-1][cPos].getType()==3 ) {
                        dir2 = 1;
                        cont = rPos - r - 1;
                        actFirst(dir2, false, false);
                        if (matrix[r-1][cPos].getType()==0) hayHoyo = false;
                        else if (matrix[r-1][cPos].getType()==3) hayHoyo = true;
                        return; 
                    } else break;
                    }
                
                }
            
            } if (rPos!=sizeMatrix-1 && rPos!=sizeMatrix-2 && rPos!=sizeMatrix-3 && matrix[rPos+1][cPos].getType()==0) {         /// abajo
            
                for (int r=rPos+2; r<sizeMatrix-1; r++) {
                
                    if (matrix[r][cPos].getType()==3||matrix[r][cPos].getType()==1) break;
                    if (matrix[r][cPos].getType()==2 ) {
                        if (matrix[r+1][cPos].getType()==0 ||matrix[r+1][cPos].getType()==3 ) {
                        dir2 = 2;
                        cont = r - rPos - 1;
                        actFirst(dir2, false, false);
                        if (matrix[r+1][cPos].getType()==0) hayHoyo = false;
                        else if (matrix[r+1][cPos].getType()==3) hayHoyo = true;
                        return;
                    } else break;
                    }
                
                }
            
            } if (cPos!=0 && cPos!=1 && cPos!=2 && matrix[rPos][cPos-1].getType()==0) {         /// izquierda
            
                for (int c=cPos-2; c>0; c--) {
                
                    if (matrix[rPos][c].getType()==3||matrix[rPos][c].getType()==1) break;
                      if (matrix[rPos][c].getType()==2) {
                        if (matrix[rPos][c-1].getType()==0 ||matrix[rPos][c-1].getType()==3 ) {
                        dir2 = 3;
                        cont = cPos - c - 1;
                        actFirst(dir2, false, false);
                        if (matrix[rPos][c-1].getType()==0) hayHoyo = false;
                        else if (matrix[rPos][c-1].getType()==3) hayHoyo = true;
                        return; 
                      } else break;
                    }
                
                }
            
            } if (cPos!=sizeMatrix-1 && cPos!=sizeMatrix-2 && cPos!=sizeMatrix-3 && matrix[rPos][cPos+1].getType()==0) {         /// derecha
            
                for (int c=cPos+2; c<sizeMatrix-1; c++) {
                
                    if (matrix[rPos][c].getType()==3||matrix[rPos][c].getType()==1) break;
                    if (matrix[rPos][c].getType()==2) {
                        if (matrix[rPos][c+1].getType()==0 ||matrix[rPos][c+1].getType()==3 ) {
                            dir2 = 4;
                            cont = c - cPos - 1;
                            actFirst(dir2, false, false);
                            if (matrix[rPos][c+1].getType()==0) hayHoyo = false;
                            else if (matrix[rPos][c+1].getType()==3) hayHoyo = true;
                            return; 
                        } else break;
                    }
                
                }
            
            }
            
            while (true) {
            
                int dir = (int)(Math.random()*4+1);
                if (rPos!=0 && dir == 1 && matrix[rPos-1][cPos].getType()==0) {          // si arriba está libre
      
                    actFirst(1,false,false);
                    return;    
                } else if (cPos!=sizeMatrix-1 && dir == 4 && matrix[rPos][cPos+1].getType()==0) {          // si derecha está libre

                    actFirst(4,false,false);
                    return;
                } else if (rPos!=sizeMatrix-1 && dir == 2 && matrix[rPos+1][cPos].getType()==0) {          // si abajo está libre

                    actFirst(2,false,false);
                    return;
                } else if (cPos!=0 && dir == 3 && matrix[rPos][cPos-1].getType()==0) {          // si izquierda está libre
      
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
