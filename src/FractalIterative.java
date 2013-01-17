import java.util.ArrayList;
import java.util.Arrays;


public class FractalIterative {
	private ArrayList<Character> jigsaw;
	private double max_length = 600;
	private double length = max_length;
	
	FractalIterative(int order)
	{
		createFractalJigsaw(order);
		createLength(order);
	}
	
	public ArrayList<Character> getFractalJigsaw(){
		return jigsaw;
	}
	
	private void createFractalJigsaw(int order){
		//create size of arraylist to fit order
		jigsaw = new ArrayList<Character>(order*5);
		jigsaw.addAll(Arrays.asList('r', 'd', 'l', 'u'));

		while(order-- != 0){
			for(int position = jigsaw.size()-1; position >= 0; position --){
				int next = position;
				if(jigsaw.get(position) == 'r'){
                	//
                	//  ____  goes to  _   _
                	//                  |_|
                	//
					jigsaw.add(++next, 'd'); // {right, down}
	                jigsaw.add(++next, 'r'); // {right, down, right}
	                jigsaw.add(++next, 'u'); // {right, down, right, up}
	                jigsaw.add(++next, 'r'); // {right, down, right, up, right}
				}else if(jigsaw.get(position) == 'd'){
                	//
            		//  |           |_
                	//  |  goes to   _|
                	//  |           |
                	//
	                jigsaw.add(++next, 'r'); // {down, right}
	                jigsaw.add(++next, 'd'); // {down, right, down}
	                jigsaw.add(++next, 'l'); // {down, right, down, left}
	                jigsaw.add(++next, 'd'); // {down, right, down, left, down}
				}else if(jigsaw.get(position) == 'l'){
                	//                  _
                	//  ___  goes to  _| |_
                	//
	                jigsaw.add(++next, 'u'); // {left, up}
	                jigsaw.add(++next, 'l'); // {left, up, left}
	                jigsaw.add(++next, 'd'); // {left, up, left, down}
	                jigsaw.add(++next, 'l'); // {left, up, left, down, left}
				}else{
                	//
                	//  |            _|
                	//  |  goes to  |_
                	//  |             |
                	//
	                jigsaw.add(++next, 'l'); // {up, left}
	                jigsaw.add(++next, 'u'); // {up, left, up}
	                jigsaw.add(++next, 'r'); // {up, left, up, right}
	                jigsaw.add(++next, 'u'); // {up, left, up, right, up}
				}
			}
		}
		System.out.println("Creating jigsaw. Done.");
	}
	
	private void createLength(int order){
		if(order > 0){
            length = max_length/(Math.pow(3,(double)order));
            if(length < 1)
                length = 1;
		}
	}
	
	public double getLength(){
		return length;
	}
	
}
