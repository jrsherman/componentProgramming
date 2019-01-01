package combinatorics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
//permutation interface in this format

//import tests.PermutationImp;
import tests.PermutationTestCasesSubset_STUDENTS;
public class PermutationImpl_Sherman<E> implements Permutation<E>{
	 private Set<E> permutationDomainSet;
	 private HashMap<E,E> hm;
	 private HashMap<E,E> hmReverse;
	public PermutationImpl_Sherman (Set<List<E>> cycles, Set<E> domain) { //constructor call
		 this.hm = new HashMap<>();
		 this.hmReverse = new HashMap<>();
		 Set<E> domainSubSet = new HashSet<E>();
		 this.permutationDomainSet = new HashSet<E>(); 
		 this.permutationDomainSet= domain;//throws domain to the set
		 ArrayList<List<E>> elementsOfCycles = new ArrayList<List<E>>();//turning set of cycles to a list to go overthem
		 elementsOfCycles.addAll(cycles);//list of <e>
		 ArrayList<E> domainSuperList = new ArrayList<E>();//our domain set 
		 domainSuperList.addAll(this.permutationDomainSet); 
		 ArrayList<List<E>> tempArrayList = new ArrayList<List<E>>();
		 for(int i =0; i < domainSuperList.size();i++) {
			 int j =0;
			 boolean numInSet = false;
			 while(j < elementsOfCycles.size()&&numInSet==false) {
				 if(elementsOfCycles.get(j).contains(domainSuperList.get(i)))
					 numInSet=true; 
				 j++;
			 }
			 if(numInSet==false) {
				 ArrayList<E> singleElementList = new ArrayList<E>();//our domain set 
				 singleElementList.add(domainSuperList.get(i));
				 tempArrayList.add(singleElementList);
			 }	
		 }

		 for(int i = 0;i<tempArrayList.size();i++) {
			 elementsOfCycles.add(tempArrayList.get(i));
		 }
		 
		 for(int i =0; i<elementsOfCycles.size();i++) {//each i goes through one array on the elements of cycles 
			 for(int j =0; j< elementsOfCycles.get(i).size();j++){
				 hm.put(elementsOfCycles.get(i).get(j),elementsOfCycles.get(i).get((j+1)%(elementsOfCycles.get(i).size())));
				 hmReverse.put(elementsOfCycles.get(i).get((j+1)%(elementsOfCycles.get(i).size())),elementsOfCycles.get(i).get(j));
			 }		 
		 }
		 System.out.print("Construct 1 List of cycyles: ");
		 System.out.println(elementsOfCycles);
	 }
		
	 public PermutationImpl_Sherman (Set<List<E>> cycles){
		 this.hm = new HashMap<>();
		 this.hmReverse = new HashMap<>();
		 this.permutationDomainSet = new HashSet<E>();  
		 ArrayList<List<E>> elementsOfCycles = new ArrayList<List<E>>();
		 elementsOfCycles.addAll(cycles);
		 this.permutationDomainSet= getAllElements(cycles);
		 for(int i =0; i<elementsOfCycles.size();i++) {
			 for(int j =0; j< elementsOfCycles.get(i).size();j++) {
				 this.hm.put(elementsOfCycles.get(i).get(j),elementsOfCycles.get(i).get((j+1)%(elementsOfCycles.get(i).size()))); //grabs size of inner list and subtracts it by 1 then when j which starts from 0 goes to 2 list will be 3 long and if we want it to loop back to zero that happens when it is completly divisable whi h will be at size 
				 this. hmReverse.put(elementsOfCycles.get(i).get((j+1)%(elementsOfCycles.get(i).size())),elementsOfCycles.get(i).get(j));
				 this.permutationDomainSet.add(elementsOfCycles.get(i).get(j));//adds 
			 }		 
		 }
		 System.out.print("Construct 2 List of cycyles: ");
		 System.out.println(elementsOfCycles);
	 }
	
	 
	@Override
	public E getImage(E element) {
		//is the mapped element A->B
		//return B
		return this.hm.get(element);
	}

	@Override
	public E getPreImage(E element) {
		return this.hmReverse.get(element);
	}

	@Override
	public Set<E> getDomain() {
		
		return this.permutationDomainSet;
	}
	private static <E> Set<E> getAllElements(Set<List<E>> setOfLists)
	{
		Set<E> allElements = new HashSet<E>();
		Iterator<List<E>> setOfListsIterator = setOfLists.iterator();
		while(setOfListsIterator.hasNext())
		{
			List<E> list = setOfListsIterator.next();
			allElements.addAll(list);
		}
		return allElements;
	}
 
	

}
