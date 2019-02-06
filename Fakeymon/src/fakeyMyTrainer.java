import java.util.ArrayList;

class fakeyMyTrainer {

	private static int score = 0, aggression = 0, defensiveness = 0, haste = 0, location = 0, money = 0, kills = 0,
			bagSize = 10, ceus = 10;
	private static String name = "";
	private static ArrayList<fakeyItem> bag = new ArrayList<fakeyItem>();
	private static ArrayList<fakeyMyMonster> mm = new ArrayList<fakeyMyMonster>() {
		{
			add(new fakeyMyMonster());
		}
	};

	fakeyMyTrainer() {
	}

	fakeyMyTrainer(int s, String n, int A, int D, int H, int a, int l, int m, int f) {
		score = s;
		name = n;
		aggression = A;
		defensiveness = D;
		haste = H;
		location = l;
		money = m;
		kills = f;
	}

	// Getters

	static ArrayList<fakeyMyMonster> gmm() {
		return mm;
	}
	static String gName() {
		return name;
	}
	static int gScore() {
		return score;
	}
	static int gAggression() {
		return aggression;
	}
	static int gDefensiveness() {
		return defensiveness;
	}
	static int gHaste() {
		return haste;
	}
	static int gLoc() {
		return location;
	}
	static int gMon() {
		return money;
	}
	static int gKills() {
		return kills;
	}
	static int gBagSize() {
		return bagSize;
	}
	static int gCeus() {
		return ceus;
	}
	static ArrayList<fakeyItem> gBag() {
		return bag;
	}

	// Setters

	static void sName(String Name) {
		name = Name;
	}
	static void sAggression(int z) {
		aggression += z;
	}
	static void sDefensiveness(int z) {
		defensiveness += z;
	}
	static void sHaste(int z) {
		haste += z;
	}
	static void sLoc(int z) {
		location = z;
	}
	static void aMon(int z) {
		money += z;
	}
	static void aScore(int z) {
		score += z;
	}
	static void aKills() {
		kills++;
	}
	static void sBagSize(int bs) {
		bagSize = bs;
	}
}
