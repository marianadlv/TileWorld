
/**
 * Write a description of class Game here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.awt.Color;
import java.awt.Font;

public class Game
{
    
    private static int size = 40;
    private static int sizeMatrix = size/2;
    private static double radiusImage = 0.5*2;
    private static double xPared = -1+radiusImage/2;
    private static double yPared = -1+radiusImage/2;
    private static double xUpdate = radiusImage;
    private static double yUpdate = radiusImage;
    private static int contTest = 1;
    private static String mode = "";
    private static int[] pointsFinal = new int[3];
    public static int maxTest = 10;
    
    public static Matriz[][] matrix = new Matriz[sizeMatrix][sizeMatrix];
    
    private static Game game = null;
    
    private Game() {}
    
    public static Game getInstance() {
    
        if (game == null) game = new Game();
        return game;
        
    }
    
    public Matriz getMatrix(int i, int j) {
    
        return matrix[i][j];
    
    }
    
    public int getSizeMatrix() {
        return sizeMatrix;
    }
    
    public void inicializarPantalla() {
    
        StdDraw.setCanvasSize((size+(2*size/3))*10,size*10);
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(-1,size+(2*size/3)+1);
        StdDraw.setYscale(size+1,-1);
    
    }
    
    public void dibujarConstantes() {
    
        // dibujo background = path
       
       StdDraw.setPenColor(Color.LIGHT_GRAY);
       StdDraw.filledRectangle(size/2,size/2,size/2,size/2);
       
       xPared = -1+radiusImage/2;
       yPared = -1+radiusImage/2;
       
       // dibujo pared de alrededor
       
       for (int r=0; r<size+2; r++) {
        
            for (int c=0; c<size+2; c++) {
            
                if(r==0||r==size+1||c==0||c==size+1) StdDraw.picture(xPared,yPared,"brick.jpg",radiusImage,radiusImage);
                xPared+=1;
            
            }
            
            xPared = -1+radiusImage/2;
            yPared+=radiusImage;
    
       }
       
       // dibujo rectángulos negros
       
       StdDraw.setPenColor(Color.BLACK);
       int x = size+(size/3)+1;
       int y = size/6;
       StdDraw.filledRectangle(x,y,size/3-2,size/6);
       y+=size/3;
       StdDraw.filledRectangle(x,y,size/3-2,size/6);
       y+=size/3;
       StdDraw.filledRectangle(x,y,size/3-2,size/6);
       
       StdDraw.setPenColor(Color.WHITE);
       StdDraw.setPenRadius(0.004);
       x = size+(size/3)+1;
       y = (int)(size*.05);
       StdDraw.text(x,y,"Agent 1");
       y+=size/3;
       StdDraw.text(x,y,"Agent 2");
       y+=size/3;
       StdDraw.text(x,y,"Agent 3");
       StdDraw.setPenRadius(0.002);
       x = size + (int)(size*.1);
       y = (int)(size*.15);
       if (mode == "AgentRandom") StdDraw.textLeft(x,y,"Movements: "+Integer.toString(AgentRandom.getInstance().getMoves()));
       y+=size/3;
       if (mode == "Agent1") StdDraw.textLeft(x,y,"Movements: "+Integer.toString(Agent1.getInstance().getMoves()));
       y+=size/3;
       if (mode == "Agent2") StdDraw.textLeft(x,y,"Movements: "+Integer.toString(Agent2.getInstance().getMoves()));
       //x = size + (int)(size*.20);
       y = (int)(size*.2);
       if (mode == "AgentRandom") StdDraw.textLeft(x,y,"Points: "+Integer.toString(AgentRandom.getInstance().getPoints()));
       y+=size/3;
       if (mode == "Agent1")StdDraw.textLeft(x,y,"Points: "+Integer.toString(Agent1.getInstance().getPoints()));
       y+=size/3;
       if (mode == "Agent2") StdDraw.textLeft(x,y,"Points: "+Integer.toString(Agent2.getInstance().getPoints()));
       //x = size + (int)(size*.19);
       y = (int)(size*.25);
       if (mode == "AgentRandom") StdDraw.textLeft(x,y,"Test: "+Integer.toString(contTest));
       y+=size/3;
       if (mode == "Agent1") StdDraw.textLeft(x,y,"Test: "+Integer.toString(contTest));
       y+=size/3;
       if (mode == "Agent2") StdDraw.textLeft(x,y,"Test: "+Integer.toString(contTest));
    
    }
    
    
    public void inicializarMatriz() {
    
        for (int r=0; r<sizeMatrix; r++) {
        
            for (int c=0; c<sizeMatrix; c++) {
            
                matrix[r][c] = new Matriz();
                //matrix[r][c].getInstance().setType(0);      // todos por default con 0 --> path
            
            }
        
        }
    
    }
    
    public void crearMapaRandom() {
    
        int i,j;
        
        for (int r=0; r<sizeMatrix; r++) {
            
            for (int c=0; c<sizeMatrix; c++) {
            
                matrix[r][c].setType(0);
            
            }
        
        }
        
        for (int r=0; r<sizeMatrix*.5; r++) {                // hoyos --> 3
        
           i = (int)(Math.random()*sizeMatrix);
           j = (int)(Math.random()*sizeMatrix);
           matrix[i][j].setType(3);
           matrix[i][j].setHoyo();
           
        
        }
        
        for (int r=0; r<sizeMatrix*.3; r++) {                // obstáculos --> 1
        
            for (int c=0; c<sizeMatrix*.3;c++) {
               i = (int)(Math.random()*sizeMatrix);
               j = (int)(Math.random()*sizeMatrix);
               matrix[i][j].setType(1);
            }
            
        }
        
        for (int r=0; r<sizeMatrix*.3; r++) {                // bloques --> 2
        
            for (int c=0; c<sizeMatrix*.2;c++) {
               i = (int)(Math.random()*sizeMatrix);
               j = (int)(Math.random()*sizeMatrix);
               matrix[i][j].setType(2);
            }
            
        }
        
        

    }
    
    public void createAgentRandom() {
    
        int i,j;
        i = (int)(Math.random()*sizeMatrix);                    // posición Agente
        j = (int)(Math.random()*sizeMatrix);
        matrix[i][j].setType(4);
        AgentRandom.getInstance().setPos(i,j);
    
    }
    
    public void createAgent1() {
    
        int i,j;
        i = (int)(Math.random()*sizeMatrix);                    // posición Agente
        j = (int)(Math.random()*sizeMatrix);
        matrix[i][j].setType(4);
        Agent1.getInstance().setPos(i,j);
    
    }
    
    public void createAgent2() {
    
        int i,j;
        i = (int)(Math.random()*sizeMatrix);                    // posición Agente
        j = (int)(Math.random()*sizeMatrix);
        matrix[i][j].setType(4);
        Agent2.getInstance().setPos(i,j);
    
    }
    
    public void updateMap() {                // dibuja mapa
        
        xUpdate = radiusImage;
        yUpdate = radiusImage;
    
        for (int r=0; r<sizeMatrix; r++) {                
        
            for (int c=0; c<sizeMatrix;c++) {
                
               if (matrix[r][c].getType() == 1) {
                   
                   StdDraw.setPenColor(Color.BLACK);
                   StdDraw.filledSquare(xUpdate,yUpdate,radiusImage);
                
               } else if (matrix[r][c].getType() == 2) {
                   
                   StdDraw.setPenColor(Color.GREEN);
                   StdDraw.filledSquare(xUpdate,yUpdate,radiusImage);
                
               } else if (matrix[r][c].getType() == 3) {
               
                  StdDraw.setPenColor(Color.RED);
                  StdDraw.filledSquare(xUpdate,yUpdate,radiusImage);
                  StdDraw.setPenColor(Color.WHITE);
                  StdDraw.text(xUpdate,yUpdate,Integer.toString(matrix[r][c].getHoyo()));
                
               } else if (matrix[r][c].getType() == 4) {
               
                  StdDraw.setPenColor(Color.BLUE);
                  StdDraw.filledSquare(xUpdate,yUpdate,radiusImage);
                  if (mode == "AgentRandom") AgentRandom.getInstance().setPos(r,c);
                  else if (mode == "Agent1") Agent1.getInstance().setPos(r,c);
                  else if (mode == "Agent2") Agent2.getInstance().setPos(r,c);
                  //AgentRandom.getInstance().getPos();
                   
               }
               xUpdate+=radiusImage*2;
            }
            yUpdate+=radiusImage*2;
            xUpdate = radiusImage;
        }
        
    }
    
    public void printResults() {
        
        int sumaPoints = 0;

        if (mode == "AgentRandom") {
        
            System.out.println("Agent 1: Random");
            
            for (int i=0; i<maxTest; i++) {
            
                System.out.println("    Test "+(i+1)+" :");
                System.out.println("        Points: "+AgentRandom.getInstance().getFinalPoints(i));
                System.out.println("        Moves: "+AgentRandom.getInstance().getFinalMoves(i));
                sumaPoints += AgentRandom.getInstance().getFinalPoints(i);
                
            }
        
            pointsFinal[0] = sumaPoints;
            System.out.println();
            System.out.println("Total points: "+sumaPoints);
            System.out.println();
        } else if (mode == "Agent1") {
        
            System.out.println("Agent 2: Smart");
            
            for (int i=0; i<maxTest; i++) {
            
                System.out.println("    Test "+(i+1)+" :");
                System.out.println("        Points: "+Agent1.getInstance().getFinalPoints(i));
                System.out.println("        Moves: "+Agent1.getInstance().getFinalMoves(i));
                sumaPoints += Agent1.getInstance().getFinalPoints(i);
                
            }
        
            pointsFinal[1] = sumaPoints;
            System.out.println();
            System.out.println("Total points: "+sumaPoints);
            System.out.println();
            
        } else if (mode == "Agent2") {
        
            System.out.println("Agent 3: Super Smart");
            
            for (int i=0; i<maxTest; i++) {
            
                System.out.println("    Test "+(i+1)+" :");
                System.out.println("        Points: "+Agent2.getInstance().getFinalPoints(i));
                System.out.println("        Moves: "+Agent2.getInstance().getFinalMoves(i));
                sumaPoints += Agent2.getInstance().getFinalPoints(i);
                
            }
        
            pointsFinal[2] = sumaPoints;
            System.out.println();
            System.out.println("Total points: "+sumaPoints);
            System.out.println();
        }
        
        
        
    }
    
    public void printFinalResults() {
    
        System.out.println("Agent 1: Random --> "+pointsFinal[0]+" points");
        System.out.println("Agent 2: Smart --> "+pointsFinal[1]+" points");
        System.out.println("Agent 3: Super Smart --> "+pointsFinal[2]+" points");
    
    }
    
    public void runEverything() {
    
        maxTest = Integer.valueOf(javax.swing.JOptionPane.showInputDialog("Number of tests per agent:"));
        
        inicializarPantalla();
        dibujarConstantes();
        inicializarMatriz();
        crearMapaRandom();
  
        updateMap();
        StdDraw.show(1);

        int i =0;
        
        mode = "AgentRandom";
        createAgentRandom();
        
        while(contTest<=maxTest) {
            i=0;
            AgentRandom.getInstance().revive();
            while(i<200 && AgentRandom.getInstance().getLife()){
                
                AgentRandom.getInstance().update();
                StdDraw.clear();
                dibujarConstantes();
                updateMap();
                StdDraw.show(60);
                i++;
                
            }
            AgentRandom.getInstance().setFinal(AgentRandom.getInstance().getMoves(), AgentRandom.getInstance().getPoints(),(contTest-1));
            crearMapaRandom();
            AgentRandom.getInstance().validarAgentRandom();
            contTest++;
        }
        
        printResults();
        
        i = 0;
        contTest = 1;
        mode = "Agent1";
        createAgent1();
      
        while(contTest<=maxTest) {
            i=0;
            Agent1.getInstance().revive();
            while(i<200 && Agent1.getInstance().getLife()){
                
                Agent1.getInstance().update();
                StdDraw.clear();
                dibujarConstantes();
                updateMap();
                StdDraw.show(60);
                i++;
                
            }
            Agent1.getInstance().setFinal(Agent1.getInstance().getMoves(), Agent1.getInstance().getPoints(),(contTest-1));
            crearMapaRandom();
            contTest++;
        }
        
        printResults();
        
        i = 0;
        contTest = 1;
        mode = "Agent2";
        createAgent2();
      
        while(contTest<=maxTest) {
            i=0;
            Agent2.getInstance().revive();
            while(i<200 && Agent2.getInstance().getLife()){
                
                Agent2.getInstance().update();
                StdDraw.clear();
                dibujarConstantes();
                updateMap();
                StdDraw.show(60);
                i++;
                
            }
            Agent2.getInstance().setFinal(Agent2.getInstance().getMoves(), Agent2.getInstance().getPoints(),(contTest-1));
            crearMapaRandom();
            contTest++;
        }
        
        printResults();
        
        printFinalResults();
        javax.swing.JOptionPane.showMessageDialog(null,"Check terminal for results.");
        
        
    }
    
    
}
