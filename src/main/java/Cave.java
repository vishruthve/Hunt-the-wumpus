// Aiden Whitlock

// oh my god its finally done

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;	
public class Cave {
	public int CELLCOUNT, WIDTH=12, HEIGHT=5;
	private Cell[] cavern;
	private CaveRender rend;
	public Player player;

	public Cave(int[][] data){
		CELLCOUNT = data.length;
		makeCaveFromInts(data);
	}

	public Cave(){
		player = new Player();
		player.setPosition(15);
		CELLCOUNT = WIDTH*HEIGHT;
		makeDefaultUpCavern();
	}

	public Cave(String path) {
		try {
		cavern = Parser.parseCave(new File("src/main/java/cave/"+path+".txt"));
		} catch(Exception e){
			System.out.println(e);
		}
		CELLCOUNT = cavern.length;
		player = new Player(0, 100);
		player.setPosition(cavern.length/2);
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
		rend.repaint(100, rend.msq()[0], rend.msq()[1], rend.msq()[2], rend.msq()[3]);
	}

	public void attemptMove(int dir){
		if (canTraverse(player.getPosition(), dir)){
			rend.repaint(10, rend.msq()[0], rend.msq()[1], rend.msq()[2], rend.msq()[3]);
			player.setPosition(cavern[player.getPosition()].getNeighbors()[dir]);
			rend.repaint(10, rend.msq()[0], rend.msq()[1], rend.msq()[2], rend.msq()[3]);
		}
	}

	public void makeDefaultUpCavern(){
		//down, downleft, upleft, up, upright, downright
		int width = 12;
		int height = 5;
		int siz = width*height;
		int[][] b = new int[siz][10];
		for (int i=0;i<siz;i++){
			b[i] = new int[] {i%width, i/width, 

				(i+width)%siz, 
				(i%2==1?(i+width-1)%siz:(i%width==0?i+width-1:i-1)),
				(i%2==1?i-1:(i%width==0?i+siz-1:i+siz-width-1)%siz),
				(i+siz-width)%siz,
				(i%2==0?(i+siz-width+1)%siz:(i%width==width-1?i-width+1:i+1)),
				(i%2==0?i+1:(i%width==width-1?i+1:i+width+1)%siz),
				
				(3*i+i/9)%2+6, i+i%2+i%3+i%4+i%5+i%6+i%7+i%8+i%9
			};
			System.out.println(Arrays.toString(b[i]));

		}
		makeCaveFromInts(b);
	}
	
	public static class Cell{
		public final int CELLID, XPOS, YPOS, SHAPE;
		public final int[] NEIGHBORIDS;
		public static int INSTANCES = 0;
		public int rotation;

		public Cell(int[] data){
			CELLID = INSTANCES++;
			XPOS   = data[0];
			YPOS   = data[1];
			NEIGHBORIDS = Arrays.copyOfRange(data, 2, 8);
			SHAPE = data[8];
			rotation = data[9];
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