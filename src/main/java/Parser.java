import java.io.*;
import java.util.*;

public class Parser{
	public Parser(){}

	public static Cave.Cell[] parseCave(File caveFile) throws FileNotFoundException{
		Scanner s = new Scanner(caveFile);
		Cave.Cell[] out = new Cave.Cell[0];
		String t;
		while (s.hasNextLine()){
			t = s.next();
			if (t.charAt(0) == '#') s.nextLine();
			else {
				out = Arrays.copyOf(out, out.length+1);
				out[out.length-1] = new Cave.Cell(Arrays.stream((t+=s.nextLine()).split("\t")).filter(x->!x.equals("")).mapToInt(Integer::parseInt).toArray());
			};
		}
		s.close();
		return out;
	}
	
}
