package tictactoe;
public class TicTacToeBoardImpl_Sherman implements TicTacToeBoard {
	protected static final int NO_MOVE= -1;
	protected static final int NO_MATCH = -1;
	protected int[] movesArray;
	public TicTacToeBoardImpl_Sherman()
	{
		final int CELL_COUNT = ROW_COUNT*COLUMN_COUNT;//puts out how big the array will be
		movesArray = new int[CELL_COUNT];
		for(int i = 0;i<CELL_COUNT;i++) {
			movesArray[i]=NO_MOVE; //nop move is a negative 1
		}
	}
	@Override
	public Mark getMark(int row, int column) {
		//since it returns nmark must check for is  marked first pre
		assert row>=0 && row <= 2: "error";
		assert column>=0 && column <= 2: "error";
		int cellValue = 0;
		Mark markValue;
		if(row==0&&column==0) {cellValue = 0;}
		else if (row==0&&column==1) {cellValue = 1;}
		else if (row==0&&column==2) {cellValue = 2;}
		else if (row==1&&column==0) {cellValue = 3;}
		else if (row==1&&column==1) {cellValue = 4;}
		else if (row==1&&column==2) {cellValue = 5;}
		else if (row==2&&column==0) {cellValue = 6;}
		else if (row==2&&column==1) {cellValue = 7;}
		else if (row==2&&column==2) {cellValue = 8;}
		boolean matchFound=false;
		int q = 0;
		while((q<9)&&(matchFound==false)){
			if(movesArray[q]==cellValue){
				matchFound=true;
			}
			q++;	
		}
		
		if(matchFound) {
		if(q%2==0) {
			markValue = Mark.O;
		}
		else {
			markValue = Mark.X;
		}}
		else {
			markValue = null;
		}
		
		return markValue;
		}
	@Override
	public void setMark(int row, int column) {
		assert row>=0 && row <= 2: "error";
		assert column>=0 && column <= 2: "error";
		assert !isGameOver():"Game is over. No more moves alowed";
		int cellValue = -1;
		Mark markValue;
		if(row==0&&column==0) {cellValue = 0;}
		else if (row==0&&column==1) {cellValue = 1;}
		else if (row==0&&column==2) {cellValue = 2;}
		else if (row==1&&column==0) {cellValue = 3;}
		else if (row==1&&column==1) {cellValue = 4;}
		else if (row==1&&column==2) {cellValue = 5;}
		else if (row==2&&column==0) {cellValue = 6;}
		else if (row==2&&column==1) {cellValue = 7;}
		else if (row==2&&column==2) {cellValue = 8;}
		//movesArray elems come in and we assign them to next available slot
		int u = 0;
		while(u<9) {
			assert cellValue!=movesArray[u]:"Value Already In Use";
			u++;
		}
		
		int slotFound=0;
		int i=0;
		while((i<=9)&&(slotFound)==0) {
			if(movesArray[i]==-1) {
				movesArray[i]=cellValue;
				slotFound=1;
			}
			i++;
		}
	}
	@Override
	public Mark getTurn() {
	//parse to find the first -1 if it is odd then x
		Mark markValue;
		boolean isGameOver = isGameOver();
		if(isGameOver){
			markValue = null;	
		}
		else {
		int slotFound=0;
		int i=0;
		while((i<=9)&&(slotFound)==0) {
			if(movesArray[i]==-1) {
				slotFound=1;
			}
			i++;
		}
		//isGameOver();
		
		if(slotFound==0) {
			markValue = Mark.X;
		}
		else if(i%2==0) {
			markValue = Mark.O;
		}
		else {
			markValue = Mark.X;
		}
		}
		return markValue;
	}
	@Override
	public boolean isGameOver() {
		boolean isGameOver = false;
		int k = 0;
		boolean emptySlotFound = false; //first we check for blackout board 
		while(k<9&&emptySlotFound==false) {
			if(movesArray[k]==-1) {
				emptySlotFound = true;
			}
			k++;
		}
		if(emptySlotFound==false) {//if no empty slot it is a blackout board so end game true blackout board works
			isGameOver=true;
		}
		else {// now we check to see for 3 in rows
			int z=0;
			int initialMatchValue = -1;//first we find initial pattern location integer
			boolean initValueFound = false;
			int startSearchIndex=0;
			int endSearchIndex=8;
			while(z<9&&initValueFound==false) {
				if(movesArray[z]==0||movesArray[z]==1||movesArray[z]==2||movesArray[z]==3||movesArray[z]==6) {//lets say we dont find any matches for initial then it wont b a winner
					initValueFound = true;
					}
				z++;
				}
			if(initValueFound==true) {
				int g = 0;
				while (g<9&&isGameOver==false) {// we go through all of moves array then match any elements 
					if(movesArray[g]==0||movesArray[g]==1||movesArray[g]==2||movesArray[g]==3||movesArray[g]==6) {//if its an x
						if(g%2!=0) {//it is an O value
							startSearchIndex = 1;
							endSearchIndex = 7;
						}
						if(movesArray[g]==0){//if we find a 1 in here
							int u = startSearchIndex; 
							while((u<endSearchIndex)&&isGameOver==false) {
								//we go through and check each subsequent value creating another while loop inside to check 
								if(movesArray[u]==1){
									int r = startSearchIndex;
									while(r<endSearchIndex&&isGameOver==false){
										if(movesArray[r]==2) {
											isGameOver=true;
										}
										r+=2;
									}
								}
								else if(movesArray[u]==3){
									int r = startSearchIndex;
									while(r<endSearchIndex&&isGameOver==false){
										if(movesArray[r]==6) {
											isGameOver=true;
										}
										r+=2;
									}
								}
								
								else if(movesArray[u]==4){	
									int r = startSearchIndex;
									while(r<endSearchIndex&&isGameOver==false){
										if(movesArray[r]==8) {
											isGameOver=true;
										}
										r+=2;
									}
								}
								u+=2;
								}
							  }//end matching for integer 1
						if(movesArray[g]==1){//if we find a 1 in here
							int u = startSearchIndex; 
							while((u<endSearchIndex)&&isGameOver==false) {
								//we go through and check each subsequent value creating another while loop inside to check 
								if(movesArray[u]==4){
									int r = startSearchIndex;
									while(r<endSearchIndex&&isGameOver==false){
										if(movesArray[r]==7) {
											isGameOver=true;
										}
										r+=2;
									}
								}
								u+=2;
								}
							  }//end matching for integer 2

						if(movesArray[g]==2){//if we find a 1 in here
							int u = startSearchIndex; 
							while((u<endSearchIndex)&&isGameOver==false) {
								//we go through and check each subsequent value creating another while loop inside to check 
								if(movesArray[u]==4){
									int r = startSearchIndex;
									while(r<endSearchIndex&&isGameOver==false){
										if(movesArray[r]==6) {
											isGameOver=true;
										}
										r+=2;
									}
								}
								else if(movesArray[u]==5){
									int r = startSearchIndex;
									while(r<endSearchIndex&&isGameOver==false){
										if(movesArray[r]==8) {
											isGameOver=true;
										}
										r+=2;
									}
								}
								u+=2;
								}
							  }//end matching for integer 3
						
						
						
						if(movesArray[g]==3){//if we find a 1 in here
							int u = startSearchIndex; 
							while((u<endSearchIndex)&&isGameOver==false) {
								//we go through and check each subsequent value creating another while loop inside to check 
								if(movesArray[u]==4){
									int r = startSearchIndex;
									while(r<endSearchIndex&&isGameOver==false){
										if(movesArray[r]==5) {
											isGameOver=true;
										}
										r+=2;
									}
								}
								u+=2;
								}
							  }//end matching for integer 4
						
						if(movesArray[g]==6){//if we find a 1 in here
							int u = startSearchIndex; 
							while((u<endSearchIndex)&&isGameOver==false) {
								//we go through and check each subsequent value creating another while loop inside to check 
								if(movesArray[u]==7){
									int r = startSearchIndex;
									while(r<endSearchIndex&&isGameOver==false){
										if(movesArray[r]==8) {
											isGameOver=true;
										}
										r+=2;
									}
								}
								u+=2;
								}
							  }//end matching for integer 1
					}//end if to check only start vals
				
				g++;
				}
				}//end else
		}
		return isGameOver;
	}
	@Override
	public Mark getWinner() {
		Mark markFound = null;
		assert isGameOver() :"Game is not over";
		boolean xIsPossibleWinner = true;
		boolean isGameOver=false;
		int z=0;
		int initialMatchValue = -1;//first we find initial pattern location integer
		boolean initValueFound = false;
		int startSearchIndex=0;
		int endSearchIndex=8;
		while(z<9&&initValueFound==false) {// this cuts out the set of permut that can not be winners
			//thing is if we have multiple initial match values it could be either x or O depending 
			// so we need to check for all match valuyes in the O's and the x';s chaning start search index depeding on where they are found
			if(movesArray[z]==1||movesArray[z]==2||movesArray[z]==3||movesArray[z]==4||movesArray[z]==7) {//lets say we dont find any matches for initial then it wont b a winner
				initValueFound = true;
				}
			z++;
			}
		if(initValueFound==true) {
			//what if there are 2 initial values etc.
			//159
			//147
			//123
			//System.out.print(initialMatchValue);
			//int g = startSearchIndex;
			//while(g<isGameOver==false)''
			//first we go through a while and find all the init values 
			int g = 0;
			while (g<9&&isGameOver==false) {// we go through all of moves array then match any elements 
				if(movesArray[g]==0||movesArray[g]==1||movesArray[g]==2||movesArray[g]==3||movesArray[g]==6) {//if its an x
					if(g%2!=0) {//it is an O value
						xIsPossibleWinner= false;
						startSearchIndex = 1;
						endSearchIndex = 7;
					}
					if(movesArray[g]==0){//if we find a 1 in here
						int u = startSearchIndex; 
						while((u<endSearchIndex)&&isGameOver==false) {
							//we go through and check each subsequent value creating another while loop inside to check 
							if(movesArray[u]==1){
								int r = startSearchIndex;
								while(r<endSearchIndex&&isGameOver==false){
									if(movesArray[r]==2) {
										isGameOver=true;
										
									}
									r+=2;
								}
							}
							else if(movesArray[u]==3){
								int r = startSearchIndex;
								while(r<endSearchIndex&&isGameOver==false){
									if(movesArray[r]==6) {
										isGameOver=true;
									}
									r+=2;
								}
							}
							
							else if(movesArray[u]==4){	
								int r = startSearchIndex;
								while(r<endSearchIndex&&isGameOver==false){
									if(movesArray[r]==8) {
										isGameOver=true;
									}
									r+=2;
								}
							}
							u+=2;
							}
						  }//end matching for integer 1
					if(movesArray[g]==1){//if we find a 1 in here
						int u = startSearchIndex; 
						while((u<endSearchIndex)&&isGameOver==false) {
							//we go through and check each subsequent value creating another while loop inside to check 
							if(movesArray[u]==4){
								int r = startSearchIndex;
								while(r<endSearchIndex&&isGameOver==false){
									if(movesArray[r]==7) {
										isGameOver=true;
									}
									r+=2;
								}
							}
							u+=2;
							}
						  }//end matching for integer 2
					if(movesArray[g]==2){//if we find a 1 in here
						int u = startSearchIndex; 
						while((u<endSearchIndex)&&isGameOver==false) {
							//we go through and check each subsequent value creating another while loop inside to check 
							if(movesArray[u]==4){
								int r = startSearchIndex;
								while(r<endSearchIndex&&isGameOver==false){
									if(movesArray[r]==6) {
										isGameOver=true;
									}
									r+=2;
								}
							}
							else if(movesArray[u]==5){
								int r = startSearchIndex;
								while(r<endSearchIndex&&isGameOver==false){
									if(movesArray[r]==8) {
										isGameOver=true;
									}
									r+=2;
								}
							}
							u+=2;
							}
						  }//end matching for integer 3
					if(movesArray[g]==3){//if we find a 1 in here
						int u = startSearchIndex; 
						while((u<endSearchIndex)&&isGameOver==false) {
							//we go through and check each subsequent value creating another while loop inside to check 
							if(movesArray[u]==4){
								int r = startSearchIndex;
								while(r<endSearchIndex&&isGameOver==false){
									if(movesArray[r]==5) {
										isGameOver=true;
									}
									r+=2;
								}
							}
							u+=2;
							}
						  }//end matching for integer 4
					if(movesArray[g]==6){//if we find a 1 in here
						int u = startSearchIndex; 
						while((u<endSearchIndex)&&isGameOver==false) {
							//we go through and check each subsequent value creating another while loop inside to check 
							if(movesArray[u]==7){
								int r = startSearchIndex;
								while(r<endSearchIndex&&isGameOver==false){
									if(movesArray[r]==8) {
										isGameOver=true;
									}
									r+=2;
								}
							}
							u+=2;
							}
						  }//end matching for integer 1
				}//end if to check only start vals
			
			g++;
			}
			}//end else
				if(xIsPossibleWinner==true) {
					markFound = Mark.X;
				}
				else{
					markFound = Mark.O;
				}
		return markFound;
	}
	private int helper() {return 7;}
	
	
	public String toString() {
		String x = "";
		for(int p=0; p <3; p++) {
		for(int j = 0;j<3; j++){x+="|";
			Mark back = getMark(p,j);
			if(back==Mark.X)
				x+="X";//checks row col to return a cell number then checks cell num
			else if(back==Mark.O)
				x+="O";	
			else
				
				x+=" ";
			
			x+="| ";
		}
		
		x+="\n";
		}
		/*for(int j = 0;j<9; j++){
			boolean valueFound = false;
			int i = 0;
			while((i<9)&&(!valueFound)){
				if(movesArray[i]==j){
					valueFound=true;
					x +="x";
				}
				
				else{
					x = " ";
				}
				i++;
			}
		}*/
		return x;
		
	}
}