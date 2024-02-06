public class Cave extends Base{
	private int CELLCOUNT = 0;
	private Cell[] cavern = new Cell[30];
	
	public class Cell{
		private final int CELLID;
		private final int[] NEIGHBORIDS;
		private final boolean[] BASEDOORLAYOUT;
		private boolean[] doors = new boolean[6];
		public int rotation = 0;

		public Cell(int id, int[] neighbor){
			CELLID = id;
			NEIGHBORIDS = neighbor;
			BASEDOORLAYOUT = new boolean[] {false, false, true, false, true, false};
		}

		public boolean[] doorState(){
		  for(int i=0;i<6;i++) doors[i]=BASEDOORLAYOUT[(i+rotation)%6+((i+rotation)%6<0?6:0)];
			return doors;
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