import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FractalRecursive extends JPanel{
    private static double max_length = 600;
    private static double length;
    private static ArrayList<Character> order = new ArrayList<Character>();

    public static void main(String[] args){
        // Get the fractal that the user wants to be shown
    	Scanner scan = new Scanner(System.in);
        System.out.println("(Recursive) Please input the fractal order that you want.");
        while(scan.hasNext()){
            if(scan.hasNextInt()){
            	int fracOrder = scan.nextInt();
            	if(fracOrder < 0)
            		fracOrder = 0;
                scan.close();
                FractalRecursive fractal = setupJFrame();
                drawFrac(fracOrder); // draw the fractal
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

    private static FractalRecursive setupJFrame(){
    	// setup the JFrame
        JFrame frame = new JFrame("Fractal Jigsaw");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FractalRecursive fractal = new FractalRecursive();
        frame.getContentPane().add(fractal);
        frame.setSize(800,800);
        frame.setPreferredSize(new Dimension(1200, 800));
        frame.pack();
        frame.setVisible(true);
        frame.toFront();
        return fractal;
    }
    
    // takes the fractal that the user inputs 
    private static void drawFrac(int fracOrder) {
    	// create the 0 fractal (cube)
        order.add('r'); // right
        order.add('d'); // down
        order.add('l'); // left
        order.add('u'); // up
        if(fracOrder > 0){
            length = max_length/(Math.pow(3,(double)fracOrder));
            if(length < 1)
                length = 1;
            // do recursion.
            drawFracRecursive(fracOrder);
        }else{ // cube
            length = max_length;
        }
        System.out.println("Done.");
    }
    
    private static void drawFracRecursive(int current){
    	if(current-- != 0)
    	{
			for(int position = order.size()-1; position >= 0; position --){
				int next = position;
				if(order.get(position) == 'r'){
					order.add(++next, 'd'); // {right, down}
	                order.add(++next, 'r'); // {right, down, right}
	                order.add(++next, 'u'); // {right, down, right, up}
	                order.add(++next, 'r'); // {right, down, right, up, right}
				}else if(order.get(position) == 'd'){
	                order.add(++next, 'r'); // {down, right}
	                order.add(++next, 'd'); // {down, right, down}
	                order.add(++next, 'l'); // {down, right, down, left}
	                order.add(++next, 'd'); // {down, right, down, left, down}
				}else if(order.get(position) == 'l'){
	                order.add(++next, 'u'); // {left, up}
	                order.add(++next, 'l'); // {left, up, left}
	                order.add(++next, 'd'); // {left, up, left, down}
	                order.add(++next, 'l'); // {left, up, left, down, left}
				}else{
	                order.add(++next, 'l'); // {up, left}
	                order.add(++next, 'u'); // {up, left, up}
	                order.add(++next, 'r'); // {up, left, up, right}
	                order.add(++next, 'u'); // {up, left, up, right, up}
				}
			}
			drawFracRecursive(current);
    	}
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
