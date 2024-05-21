// Aiden Whitlock


// So this is all big and scary and kind of fucked up
// And documenting this is going to nigh impossible
// my plan is to keep all my fuckedup cell logic in here
// and all you have to is ask "i am in cell X, can i go left?"
// and it will tell you yes or no
// etc
// dont try to understand this. ill do my best to have it easy as pie




import java.util.*;	
public class Cave extends Base{
	private final int CELLCOUNT;
	private Cell[] cavern;

	public Cave(int[][] data){
		CELLCOUNT = data.length;
		makeCaveFromInts(data);
	}

	public Cave(){
		CELLCOUNT = 30;
		makeFuckedUpCavern();
	}

	public void makeCaveFromInts(int[][] data){
		cavern = new Cell[CELLCOUNT];
		for(int i=0;i<CELLCOUNT;i++) cavern[i]=new Cell(data[i]);
	}


	public Cell[] getCavern(){
		return cavern;
	}

	public boolean canTraverse(int id, int dir){
		return cavern[id].doorState()[dir] && cavern[cavern[id].getNeighbors()[dir]].doorState()[(dir+3)%6];
	}

	public void rotateCell(int t){
		cavern[t].rotation++;
	}


	public void makeFuckedUpCavern(){
		//down, downleft, upleft, up, upright, downright
		int[][] b = new int[30][11];
		for (int i=0;i<30;i++){
			b[i] = new int[] {i, i%6, i/6, 

				(i+6)%30, 
				(i%2==1?(i+5)%30:(i%6==0?i+5:i-1)),
				(i%2==1?i-1:(i%6==0?i+29:i+23)%30),
				(i+24)%30,
				0,
				(i%2==0?i+1:(i%6==5?i+1:i+7)%30),
				
				(3*i)%8, i};
		}
		makeCaveFromInts(b);
	}

	
	
	public class Cell{
		public final int CELLID, XPOS, YPOS, SHAPE;
		public final int[] NEIGHBORIDS;
		public int rotation;

		public Cell(int[] data){
			CELLID = data[0];
			XPOS   = data[1];
			YPOS   = data[2];
			NEIGHBORIDS = Arrays.copyOfRange(data, 3, 9);
			SHAPE = data[9];
			rotation = data[10];
		}

		public int[] getNeighbors(){
			return NEIGHBORIDS;
		}
		
		// TODO: make less fucking obscured

		public boolean[] doorState(){
			boolean[] doors = new boolean[6];
			doors[0] = true;
			switch(SHAPE){
				case 1: doors[1] = true; break;
				case 2: doors[2] = true; break;
				case 3: doors[3] = true; break;
				case 4: doors[1] = true; doors[2] = true; break;
				case 5: doors[1] = true; doors[3] = true; break;
				case 6: doors[2] = true; doors[3] = true; break;
				case 7: doors[2] = true; doors[4] = true; break;
			}
			boolean[] doorsrotated = new boolean[6];
			for(int i=0;i<6;i++) doorsrotated[i]=doors[(i-rotation)%6+((i-rotation)%6<0?6:0)];
			return doorsrotated;
		}


		public String toString(){ 
			return CELLID+" "+XPOS+" "+YPOS+" "+Arrays.toString(NEIGHBORIDS)+" "+SHAPE+" "+rotation;
		}

		

		
	}

	
	// this took me two fucking hours
	/*
	neighbor linking scheme
	
    \  _  /
	  /E   F\
	--D [ID]A--
	  \C _ B/
    /     \
	 
	ID     A  B  C  D  E  F
	1  = [ 2, 4, 6, 3,12,10]
	2  = [ 3, 5, 4, 1,10,11]
	3  = [ 1, 6, 5, 2,11,12]
	4  = [ 5, 8, 7, 6, 1, 2]
	5  = [ 6, 9, 8, 4, 2, 3]
	6  = [ 4, 7, 9, 5, 3, 1]
	7  = [ 8,10,12, 9, 6, 4]
	8  = [ 9,11,10, 7, 4, 5]
	9  = [ 7,12,11, 8, 5, 6]
	10 = [11, 2, 1,12, 7, 8]
	11 = [12, 3, 2,10, 8, 9]
	12 = [10, 1,12,11, 9, 7]

			#         #         #
	# 12 10 # # 10 11 # # 11 12 #
	3  [1]  2=1  [2]  3=2  [3]  1
	# 6   4 # # 4   5 # # 5   6 #
			# \\ # // # \\ # // # \\ #
			 # 1   2 # # 2   3 # # 3   1 #
			 6  [4]  5=4  [5]  6=5  [6]  4
			 # 7   8 # # 8   9 # # 9   7 #
			# // # \\ # // # \\ # // #
	# 6   4 # # 4   5 # # 5   6 #
	9  [7]  8=7  [8]  9=8  [9]  7
	# 12 10 # # 10 11 # # 11 12 #
			# \\ # // # \\ # // # \\ #
			 # 7   8 # # 8   9 # # 9   7 #
			 12 [10]11=10 [11]12=11 [12]10
			 # 1   2 # # 2   3 # # 12  1 #
					 #         #         #

	*/
}