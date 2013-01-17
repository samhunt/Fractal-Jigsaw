import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FractalIterativeApp extends JPanel{
    private static double length;
    private static ArrayList<Character> order = new ArrayList<Character>();

    public static void main(String[] args){
        // Get the fractal that the user wants to be shown
    	Scanner scan = new Scanner(System.in);
        System.out.println("(Iterative) Please input the fractal order that you want.");
        while(scan.hasNext()){
            if(scan.hasNextInt()){
            	int fracOrder = scan.nextInt();
            	if(fracOrder < 0)
            		fracOrder = 0;
                scan.close();
                FractalIterativeApp fractal = setupJFrame();
                //drawFrac(fracOrder); // draw the fractal
                FractalIterative fj = new FractalIterative(fracOrder);
                length = fj.getLength();
                order = fj.getFractalJigsaw();
                fractal.repaint(); //paint
                break;
            }
            else // you did not enter a string.
            {
            	scan.next(); // make sure to move the counter so that you
            				 // don't get stuck in an infinite loop.
                System.out.println("You must input a number.");
            }
        }
    }

    private static FractalIterativeApp setupJFrame(){
    	// setup the JFrame
        JFrame frame = new JFrame("Fractal Jigsaw");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FractalIterativeApp fractal = new FractalIterativeApp();
        frame.getContentPane().add(fractal);
        frame.setSize(800,800);
        frame.setPreferredSize(new Dimension(1200, 800));
        frame.pack();
        frame.setVisible(true);
        frame.toFront();
        return fractal;
    }
    
    //
    // Prints out the fractal
    //
    public void paintComponent(Graphics g){
        double xpos, ypos, xend, yend;
        xpos = xend = 300; //starting x point
        ypos = yend = 50; // starting y point
        super.paintComponent(g);
        for(int i=0; i < order.toArray().length; i++){
            if(order.get(i) == 'r'){ // get coordinated for a right line
                xend = xpos + length;
                yend = ypos;
            }else if(order.get(i) == 'd'){ // get coordinated for a down line
                xend = xpos;
                yend = ypos + length;
            }else if(order.get(i) == 'l'){ // get coordinated for a left line
                xend = xpos - length;
                yend = ypos;
            }else{ // get coordinated for a up line
                xend = xpos;
                yend = ypos - length;
            }
            g.drawLine((int)xpos, (int)ypos, (int)xend, (int)yend); //draw line
            xpos = xend; // get x starting point for next line
            ypos = yend; // get y starting point for next line
        }
        //return;
    }
}
