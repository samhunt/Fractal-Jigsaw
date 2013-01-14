import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class Fractal extends JPanel{
    private static double max_length = 600;
    private static int fractal = 0;
    private static double length;
    private static ArrayList<Character> order = new ArrayList<Character>();

    public static void main(String[] args){
    	// setup the JFrame
        JFrame frame = new JFrame("Fractal Jigsaw");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Fractal());
        frame.setSize(800,800);
        frame.setPreferredSize(new Dimension(1200, 800));
        frame.pack();
        frame.setVisible(true);

        // Get the fractal that the user wants to be shown
    	Scanner scan = new Scanner(System.in);
        System.out.println("Please input the fractal that you want.");
        try{
            if(scan.hasNext()){
            	fractal = Integer.parseInt(scan.next());
            }
        }catch(NumberFormatException e){
            System.out.println("You must input a number.");
        }
        scan.close();
        
        drawFract(fractal); // draw the fractal
    }

    // takes the fractal that the user inputs 
    private static void drawFract(int fractal)
    {
    	// create the 0 fractal (cube)
        order.add('r'); // right
        order.add('d'); // down
        order.add('l'); // left
        order.add('u'); // up
        if(fractal > 0){
            length = max_length/(Math.pow(3,(double)fractal));
            if(length < 1)
                length = 1;
	        for(int i = 1; i <= fractal; i++){
	            for(int j= order.toArray().length-1; j>=0; j--){
	                int pos = j+1;
	                char store = order.get(j);
	                if(store == 'r'){ 
	                	//
	                	//  ____  goes to  _   _
	                	//                  |_|
	                	//
	                    order.add(pos++, 'd'); // {right, down}
	                    order.add(pos++, 'r'); // {right, down, right}
	                    order.add(pos++, 'u'); // {right, down, right, up}
	                    order.add(pos++, 'r'); // {right, down, right, up, right}
	                }else if(store == 'd'){
	                	//
						//  |           |_
						//  |  goes to   _|
						//  |           |
	                	//
	                    order.add(pos++, 'r'); // {down, right}
	                    order.add(pos++, 'd'); // {down, right, down}
	                    order.add(pos++, 'l'); // {down, right, down, left}
	                    order.add(pos++, 'd'); // {down, right, down, left, down}
	                }else if(store == 'l'){
	                	//                  _
					  	//  ___  goes to  _| |_
	                	//
	                    order.add(pos++, 'u'); // {left, up}
	                    order.add(pos++, 'l'); // {left, up, left}
	                    order.add(pos++, 'd'); // {left, up, left, down}
	                    order.add(pos++, 'l'); // {left, up, left, down, left}
	                }else if(store == 'u'){
	                	//
					  	//  |            _|
					  	//  |  goes to  |_
					  	//  |             |
	                	//
	                    order.add(pos++, 'l'); // {up, left}
	                    order.add(pos++, 'u'); // {up, left, up}
	                    order.add(pos++, 'r'); // {up, left, up, right}
	                    order.add(pos++, 'u'); // {up, left, up, right, up}
	                }
	            }
	        }
        }else{ // cube
            length = max_length;
        }
        System.out.println("Done.");
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
