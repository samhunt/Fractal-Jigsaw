import java.util.ArrayList;
import java.util.Arrays;


public class FractalRecursive {
	private ArrayList<Character> jigsaw = null;
	private double max_length = 600;
	private double length = max_length;
	
	FractalRecursive(int order)
	{
		createFractalJigsaw(order);
		createLength(order);
	}
	
	public ArrayList<Character> getFractalJigsaw(){
		return jigsaw;
	}

	private void createFractalJigsaw(int order){
		jigsaw = recursive(order, 'n');
	}
	
	private ArrayList<Character> recursive(int order, char direction){
		ArrayList<Character> frac = new ArrayList<Character>();
		ArrayList<Character> orderNotZero = new ArrayList<Character>();
		if(direction == 'n'){ // this is the start
			char[] start = {'r', 'd', 'l', 'u'};
			if(order-- != 0){
				for(int position = start.length-1; position >= 0; position --){
					int next = position;
					frac.addAll(0, recursive(order, start[position]));
				}
			}else{
				for(char c : start){
					frac.add(c);
				}
			}
		}else{
			if(direction == 'r'){
				orderNotZero.addAll(Arrays.asList('r', 'd', 'r', 'u', 'r'));
			}else if (direction == 'd'){
				orderNotZero.addAll(Arrays.asList('d', 'r', 'd', 'l', 'd'));
			}else if (direction == 'l'){
				orderNotZero.addAll(Arrays.asList('l', 'u', 'l', 'd', 'l'));
			}else{ //direction = u
				orderNotZero.addAll(Arrays.asList('u', 'l', 'u', 'r', 'u'));
			}
			if(order-- != 0){
				for(int i = orderNotZero.size()-1; i>=0; i--){
					frac.addAll(0, recursive(order, orderNotZero.get(i)));
				}
			}else{
				frac = orderNotZero;
			}
		}
		return frac;
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
