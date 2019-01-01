package dartboard;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import combinatorics.Permutation;
public class DartboardImpl_Sherman implements Dartboard {
	public int topMostNumber;
	private List<Integer> dartArray;
	public DartboardImpl_Sherman(Permutation <Integer> p, int topMostNumber) {//throwing in 2 first to this
		this.topMostNumber = topMostNumber;
		//System.out.println(this.topMostNumber);//looks god
		System.out.println(p.getImage(topMostNumber));
		//p.getImage(topMostNumber);
		boolean originalInput = false;
		this.dartArray = new ArrayList<Integer>();
		this.dartArray.add(topMostNumber);//adding first elem to array as Integer
		int j = 0;
		while(!originalInput) {
			if(this.dartArray.get(0)==p.getImage(this.dartArray.get(j))){
				originalInput = true;
			}
			else {
				this.dartArray.add(p.getImage(dartArray.get(j)));
			}	
		j++;
		}
		System.out.print("dart Array: ");
		System.out.println(dartArray);
	}
	@Override
	public int getTopmostNumber() {
		return this.topMostNumber;
	}//is dart array in order
	@Override
	public int getClockwiseAdjacentNumber(int number) {//get num
		int indexOfNum = this.dartArray.indexOf(number);
		return this.dartArray.get((indexOfNum+1)%this.dartArray.size());
	}
	@Override
	public Set<Integer> getNumbers() {
		Set<Integer> numbers = new HashSet<Integer>(dartArray);
		return numbers;
	}
}
