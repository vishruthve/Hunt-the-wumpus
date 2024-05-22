// Aiden Whitlock

// oh my god its finally done

import java.util.*;	
public class Cave extends Base{
	public final int CELLCOUNT;
	private Cell[] cavern;
	private CaveRender rend;
	public int playerPos = 15;

	public Cave(int[][] data){
		CELLCOUNT = data.length;
		makeCaveFromInts(data);
	}

	public Cave(){
		CELLCOUNT = 5*12;
		makeDefaultUpCavern();
	}

	public void linkRender(CaveRender c){
		rend = c;
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

	public void rotateCell(int t, int a){
		cavern[t].rotation += a;
		rend.repaint();
	}

	public void attemptMove(int dir){
		if (canTraverse(playerPos, dir)){
			playerPos = cavern[playerPos].getNeighbors()[dir];
			rend.repaint();
		}
	}
	public void makeDefaultUpCavern(){
		//down, downleft, upleft, up, upright, downright
		int width = 12;
		int height = 5;
		int siz = width*height;
		int[][] b = new int[siz][11];
		for (int i=0;i<siz;i++){
			b[i] = new int[] {i, i%width, i/width, 

				(i+width)%siz, 
				(i%2==1?(i+width-1)%siz:(i%width==0?i+width-1:i-1)),
				(i%2==1?i-1:(i%width==0?i+siz-1:i+siz-width-1)%siz),
				(i+siz-width)%siz,
				(i%2==0?(i+siz-width+1)%siz:(i%width==width-1?i-width+1:i+1)),
				(i%2==0?i+1:(i%width==width-1?i+1:i+width+1)%siz),
				
				(3*i+i/9)%2+6, i%2+i%3+i%4+i%5+i%6+i%7+i%8+i%9+i%10
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
			//doors = new boolean[] {true,true,true,true,true,true};
			boolean[] doorsrotated = new boolean[6];
			for(int i=0;i<6;i++) doorsrotated[i]=doors[(i-rotation)%6+((i-rotation)%6<0?6:0)];
			return doorsrotated;
		}
	}
}