import java.util.ArrayList;

public class fakeyMyMonster {

	private int XP = 100, HP = 1, TempHP = 1, Attack = 1, Defense = 1, Speed = 1, recursiveHeal = 0;;
	private String Name;
	private ArrayList<fakeyNewAttack> Moves = new ArrayList<fakeyNewAttack>() {{add(new fakeyNewAttack(""));}};
	public fakeyMyMonster() {}
	public fakeyMyMonster(String name, int xp, int hp, int tempHP, int attack, int defense,
			int speed, int fakeyKills, ArrayList<fakeyNewAttack> moves) {
		Name = name;
		XP = xp;
		HP = hp;
		TempHP = tempHP;
		Attack = attack;
		Defense = defense;
		Speed = speed;
		Moves = moves;
	}
	
	//Getters

	public String gName() {
		return Name;
	}
	public int gXP() {
		return XP;
	}
	public int gHP() {
		return HP;
	}
	public int gTempHP() {
		return TempHP;
	}
	public int gAttack() {
		return Attack;
	}
	public int gDefense() {
		return Defense;
	}
	public int gSpeed() {
		return Speed;
	}
	public int gHeal() {
		return recursiveHeal;
	}
	public ArrayList<fakeyNewAttack> gMoves() {
		return Moves;
	}
	
	//Setters
	
	public void sName(String name) {
		Name = name;
	}
	public void sXP(int xp) {
		XP = xp;
	}
	public void sHP(int hp) {
		HP = hp;
	}
	public void sTempHP(int thp) {
		TempHP = thp;
	}
	public void sAttack(int atk) {
		Attack = atk;
	}
	public void sDefense(int def) {
		Defense = def;
	}
	public void sSpeed(int spe) {
		Speed = spe;
	}
	public void aHeal(int i) {
		recursiveHeal += i;
	}
	public void sMoves(ArrayList<fakeyNewAttack> moves) {
		Moves = moves;
	}
	
	
}
