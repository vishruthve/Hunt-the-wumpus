// Aiden Whitlock
public class Base{
	// My idea here is that we have a "root" class
	// that every single object in the code inherits
	// so that if we find some like array operation or smth
	// thats annoying to retype or create an object to do
	// we can just put it in here and use it wherever
	// for example, im tired of typing System.out.println(str)
	// all the time, so instead we can now just type log(str)
	// ANYWHERE in our code - ezpz. hope this made sense lol

	// shorthand for System.out.println()
	public static void log(String s){
		System.out.println(s);
	}

	public enum Direction {
		RIGHT (0),
		DOWNRIGHT (1),
		DOWNLEFT (2),
		LEFT (3),
		UPLEFT (4),
		UPRIGHT (5);
		private final int id;

		public int id() {return id;}

		Direction(int dir) {
			this.id = dir;
		}

	}

}