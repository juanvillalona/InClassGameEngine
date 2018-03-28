
public class Soldier extends Sprite{
	//all soldiers now share the same array of actions
	static String[] action = {"lt", "rt", "up", "dn"};
	
	public Soldier (int x, int y) {
		super(x, y, "g_", action, 5, 10);
	}

}
