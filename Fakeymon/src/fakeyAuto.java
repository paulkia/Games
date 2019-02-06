import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class fakeyAuto extends fakeymonGAME{
	//DEVELOPER BOIS
	static Scanner scan = new Scanner(System.in);
	static ArrayList<fakeyItem> bag = new ArrayList<fakeyItem>();
	static ArrayList<fakeyMyMonster> mm = new ArrayList<fakeyMyMonster>() {{add(new fakeyMyMonster());}};
	static ArrayList<fakeyNewAttack> moves = new ArrayList<fakeyNewAttack>() {{add(new fakeyNewAttack(""));}};
	static fakeyNewAttack mov1 = null, mov2 = null, mov3 = null, mov4 = null;
	static int score = 100, hp = 300, atk = 100, def = 100, spe = 50000, XP = 50, fakeyKills = 1;
	static boolean isSave = false;
	static String name = "Developer", p1 = null, p2 = null, p3 = null, p4 = null;
	

	public static void main(String[] args) throws Exception {
		aMon(10000);
		fakeySet();
		new fakeyNormalDifficulty(1);
	}

	private static void fakeySet() {//sets all of the stats
		mm.get(0).sAttack(atk);
		mm.get(0).sDefense(def);
		for (int i = 0; i < fakeyKills; i++) aKills();
		mm.get(0).sHP(hp);
		mm.get(0).sMoves(moves);
		mm.get(0).sName(name);
		mm.get(0).sSpeed(spe);
		mm.get(0).sTempHP(hp);
		mm.get(0).sXP(XP);
	}
}
