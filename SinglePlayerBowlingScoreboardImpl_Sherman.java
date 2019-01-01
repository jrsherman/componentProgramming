package bowling;
public class SinglePlayerBowlingScoreboardImpl_Sherman implements SinglePlayerBowlingScoreboard, AssignmentMetaData
{
	private static final int MAXIMUM_ROLLS = 21;	//Maximum rolls in a one player game
	private String playerName;
	private int[] pinsKnockedDownArray = new int[MAXIMUM_ROLLS];
	private int rollCount = 0;
	
	public SinglePlayerBowlingScoreboardImpl_Sherman(String playerName)
	{
		assert playerName != null : "playerName is null!";
		this.playerName = playerName;
	}
	public String getPlayerName()
	{
		return playerName;
	}
	private int strikeCounter() {
		boolean zeroFound = false;
		int strikeCount = 0;
		int i =  0;
		while(zeroFound!=true) {
			if(pinsKnockedDownArray[i]==0)
				zeroFound=true;
			else if (pinsKnockedDownArray[i]==10)
				strikeCount++;
		  i++;	
		}
		return strikeCount;
	}
	
	public int getCurrentFrame() 
	{ //get the current frame we only have pin values of 1 thrugh 9
		assert !isGameOver() : "Game is over!";
		
		int frameCount = 1;
		if(rollCount==0) {}
		else {
		Double ceilingNum = Math.ceil((double)rollCount/2);
		frameCount = (ceilingNum.intValue());
		}
		return frameCount;
	}

	public Mark getMark(int frameNumber, int boxIndex) 
	{	
		assert 1 <= frameNumber : "frameNumber = " + frameNumber + " < 1!";
		assert frameNumber <= 10 : "frameNumber = " + frameNumber + " > 10!";
		assert 1 <= boxIndex : "boxIndex = " + boxIndex + " < 1!";
		assert boxIndex <= 3 : "boxIndex = " + boxIndex + " > 3!";
//		Exercise for student: Fix
//		assert (boxIndex != 2) || (!isStrike(frameNumber) && !isSpare(frameNumber)) : "boxIndex = " + boxIndex + ", but there was a Strike! : frameNumber = " + frameNumber;
//		assert (boxIndex != 2) || (!isSpare(frameNumber)) : "boxIndex = " + boxIndex + ", but there was a Spare! : frameNumber = " + frameNumber;
		Mark mark = null;
		
		//replace stuff here with below stuff
		if(frameNumber < 10) mark = getMarkSingleDigitFrameNumber(frameNumber, boxIndex);
		else mark = getMarkTenthFrame(boxIndex);
		assert mark != null : "mark is null!";
		return mark;
	}
		
	private Mark getMarkSingleDigitFrameNumber(int frameNumber, int boxIndex)
	{
		assert 1 <= frameNumber : "frameNumber = " + frameNumber + " < 1!";
		assert frameNumber <= 9 : "frameNumber = " + frameNumber + " > 9!";
		assert 1 <= boxIndex : "boxIndex = " + boxIndex + " < 1!";
		assert boxIndex <= 2 : "boxIndex = " + boxIndex + " > 2!";
		//box index can only be one or 2
		//looking for what mark is in the box index we return a mark value
		//lets say they give us frame 1 and index 2
		//frame 1 box 2
		int rollCountAdjustedForZeroBasedArray = (frameNumber*2)-1;
		//roll count till box is 2
		Mark markedBox;
		if(boxIndex==1) {//offset b/c of array being 0 based and frame*2 grabbing second box val
			markedBox = Mark.translate(pinsKnockedDownArray[rollCountAdjustedForZeroBasedArray-1]);	
		}
		else{
			markedBox = Mark.translate(pinsKnockedDownArray[rollCountAdjustedForZeroBasedArray]);
		}
		return markedBox;
	}
	
	private Mark getMarkTenthFrame(int boxIndex)
	{
		assert 1 <= boxIndex : "boxIndex = " + boxIndex + " < 1!";
		assert boxIndex <= 3 : "boxIndex = " + boxIndex + " > 3!";
		return Mark.NINE;
	}

	public int getScore(int frameNumber) {
		assert 1 <= frameNumber : "frameNumber = " + frameNumber + " < 1!";
		assert frameNumber <= 10 : "frameNumber = " + frameNumber + " > 10!";
		int totalCurrentScore = 0;//get the score to the point we are at in the game
		//get the score up to a certain frame number
		int numRollsToFrame = (frameNumber*2);
		for(int i = 0; i < numRollsToFrame; i++){
			totalCurrentScore += pinsKnockedDownArray[i];
        }
		return totalCurrentScore;
		
		
		

	}
	public boolean isGameOver() {
		boolean isGameOver = false;
		if((MAXIMUM_ROLLS-strikeCounter())==rollCount) {
			isGameOver = true;
		}
		return isGameOver;
	}

	public void recordRoll(int pinsKnockedDown) 
	{
		assert 0 <= pinsKnockedDown : "pinsKnockedDown = " + pinsKnockedDown + " < 0!";
		assert pinsKnockedDown <= 10 : "pinsKnockedDown = " + pinsKnockedDown + " > 10!";
		assert (getCurrentBall() == 1) || 
				((getCurrentBall() == 2) && (((getCurrentFrame() == 10) && isStrikeOrSpare(getMark(10, 1))) || ((pinsKnockedDownArray[rollCount - 1] + pinsKnockedDown) <= 10))) || 
				((getCurrentBall() == 3) && (((getCurrentFrame() == 10) && isStrikeOrSpare(getMark(10, 2))) || ((pinsKnockedDownArray[rollCount - 1] + pinsKnockedDown) <= 10)));
		assert !isGameOver() : "Game is over!";

		pinsKnockedDownArray[rollCount] = pinsKnockedDown;
		rollCount++;
	}

	public int getCurrentBall() {
		assert !isGameOver() : "Game is over!";//get the roll we are on
		int current = (rollCount%2)+1;
		return current;
	}

	private static final String VERTICAL_SEPARATOR = "#";
	private static final String HORIZONTAL_SEPARATOR = "#";
	private static final String LEFT_EDGE_OF_SMALL_SQUARE = "[";
	private static final String RIGHT_EDGE_OF_SMALL_SQUARE = "]";
	private String getScoreboardDisplay()
	{
		StringBuffer frameNumberLineBuffer = new StringBuffer();
		StringBuffer markLineBuffer = new StringBuffer();
		StringBuffer horizontalRuleBuffer = new StringBuffer();
		StringBuffer scoreLineBuffer = new StringBuffer();
		frameNumberLineBuffer.append(VERTICAL_SEPARATOR);
		
		markLineBuffer.append(VERTICAL_SEPARATOR);
		horizontalRuleBuffer.append(VERTICAL_SEPARATOR);
		scoreLineBuffer.append(VERTICAL_SEPARATOR);

		for(int frameNumber = 1; frameNumber <= 9; frameNumber++)
		{
			frameNumberLineBuffer.append("  " + frameNumber + "  ");
			markLineBuffer.append(" ");
			markLineBuffer.append(getMark(frameNumber, 1));
			markLineBuffer.append(LEFT_EDGE_OF_SMALL_SQUARE);
			markLineBuffer.append(getMark(frameNumber, 2));
			markLineBuffer.append(RIGHT_EDGE_OF_SMALL_SQUARE);
			
			final int CHARACTER_WIDTH_SCORE_AREA = 5;
			for(int i = 0; i < CHARACTER_WIDTH_SCORE_AREA; i++) horizontalRuleBuffer.append(HORIZONTAL_SEPARATOR);
			if(isGameOver() || frameNumber < getCurrentFrame())
			{
				int score = getScore(frameNumber);
				final int PADDING_NEEDED_BEHIND_SCORE = 1;
				final int PADDING_NEEDED_IN_FRONT_OF_SCORE = CHARACTER_WIDTH_SCORE_AREA - ("" + score).length() - PADDING_NEEDED_BEHIND_SCORE;
				for(int i = 0; i < PADDING_NEEDED_IN_FRONT_OF_SCORE; i++) scoreLineBuffer.append(" ");
				scoreLineBuffer.append(score);
				for(int i = 0; i < PADDING_NEEDED_BEHIND_SCORE; i++) scoreLineBuffer.append(" ");
			}
			else
			{
				for(int i = 0; i < CHARACTER_WIDTH_SCORE_AREA; i++) scoreLineBuffer.append(" ");
			}
			
			frameNumberLineBuffer.append(VERTICAL_SEPARATOR);
			markLineBuffer.append(VERTICAL_SEPARATOR);
			horizontalRuleBuffer.append(VERTICAL_SEPARATOR);
			scoreLineBuffer.append(VERTICAL_SEPARATOR);
		}
		//Frame 10:
		{
			final String THREE_SPACES = "   ";
			frameNumberLineBuffer.append(THREE_SPACES + 10 + THREE_SPACES);

			markLineBuffer.append(" ");
			markLineBuffer.append(getMark(10, 1));
			markLineBuffer.append(LEFT_EDGE_OF_SMALL_SQUARE);
			markLineBuffer.append(getMark(10, 2));
			markLineBuffer.append(RIGHT_EDGE_OF_SMALL_SQUARE);
			markLineBuffer.append(LEFT_EDGE_OF_SMALL_SQUARE);
			markLineBuffer.append(getMark(10, 3));
			markLineBuffer.append(RIGHT_EDGE_OF_SMALL_SQUARE);
			
			final int CHARACTER_WIDTH_SCORE_AREA = 8;
			for(int i = 0; i < CHARACTER_WIDTH_SCORE_AREA; i++) horizontalRuleBuffer.append(HORIZONTAL_SEPARATOR);
			if(isGameOver())
			{
				int score = getScore(10);
				final int PADDING_NEEDED_BEHIND_SCORE = 1;
				final int PADDING_NEEDED_IN_FRONT_OF_SCORE = CHARACTER_WIDTH_SCORE_AREA - ("" + score).length() - PADDING_NEEDED_BEHIND_SCORE;
				for(int i = 0; i < PADDING_NEEDED_IN_FRONT_OF_SCORE; i++) scoreLineBuffer.append(" ");
				scoreLineBuffer.append(score);
				for(int i = 0; i < PADDING_NEEDED_BEHIND_SCORE; i++) scoreLineBuffer.append(" ");
			}
			else
			{
				for(int i = 0; i < CHARACTER_WIDTH_SCORE_AREA; i++) scoreLineBuffer.append(" ");
			}
			
			frameNumberLineBuffer.append(VERTICAL_SEPARATOR);
			markLineBuffer.append(VERTICAL_SEPARATOR);
			horizontalRuleBuffer.append(VERTICAL_SEPARATOR);
			scoreLineBuffer.append(VERTICAL_SEPARATOR);
		}
			
		return 	getPlayerName() + "\n" +
				horizontalRuleBuffer.toString() + "\n" +
				frameNumberLineBuffer.toString() + "\n" +
				horizontalRuleBuffer.toString() + "\n" +
				markLineBuffer.toString() + "\n" +
				scoreLineBuffer.toString() + "\n" +
				horizontalRuleBuffer.toString();
	}
	
	public String toString()
	{
		return getScoreboardDisplay();
	}
	
	private int getRollIndexOfFirstBall(int frameNumber)
	{
		throw new RuntimeException("NOT IMPLEMENTED!");
	}
	
	private boolean isStrike(int frameNumber)
	{
		return false;
	}
	
	private boolean isSpare(int frameNumber)
	{
		throw new RuntimeException("NOT IMPLEMENTED!");
	}

	private boolean isStrikeOrSpare(Mark mark)
	{
		return ((mark == Mark.STRIKE) || (mark == Mark.SPARE));
	}
	
	//*************************************************
	//*************************************************
	//*************************************************
	//*********ASSIGNMENT METADATA STUFF***************
	public String getFirstNameOfSubmitter() 
	{
		return "Jake";
	}

	public String getLastNameOfSubmitter() 
	{
		return "Sherman";
	}
	
	public double getHoursSpentWorkingOnThisAssignment()
	{
		return 7.;
	}
	
	public int getScoreAgainstTestCasesSubset()
	{
		return 90;
	}
}