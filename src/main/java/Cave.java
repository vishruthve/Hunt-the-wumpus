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
	private Gui g;
	private Cell[] cavern;

	public Cave(int[][] data){
		CELLCOUNT = data.length;
		makeCaveFromInts(data);
	}

	public Cave(){
		CELLCOUNT = 30;
		makeFuckedUpCavern();
	}

	public void linkGui(Gui G){
		g = G;
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
		g.repaint();
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
				(i%2==0?(i+25)%30:(i%6==5?i-5:i+1)),
				(i%2==0?i+1:(i%6==5?i+1:i+7)%30),
				
				(3*i+i/9)%2+6, i%2+i%3+i%4+i%5
			};
			System.out.println(Arrays.toString(b[i]));

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
}