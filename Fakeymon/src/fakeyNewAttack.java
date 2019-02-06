import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class fakeyNewAttack {

	private int kills, power, accuracy;
	private String name, info;

	public fakeyNewAttack(String n, String i, int p, int a) {
		name = n;
		info = i;
		power = p;
		accuracy = a;
	}

	public fakeyNewAttack(String punch) { // Just for the start of the game
		Random rand = new Random();
		power = 4 + (rand).nextInt(2);
		accuracy = 100;
		name = "punch";
		info = "A basic attack with low strength.";
	}

	public void moveGen() throws FileNotFoundException {// Selects from fakeyAttacks.txt
		kills = fakeymonGAME.gKills() - 1;
		Scanner scan = new Scanner(new File("src/Data/fakeyAttacks.txt"));
		Random rand = new Random();
		String line;
		int a = new Random().nextInt(3);
		if (kills < 1) {
			for (int i = 0; i < a; i++) {
				scan.nextLine();
			}
			line = scan.nextLine();
		} else if (kills < 5) {
			for (int i = 0; i < a + 3; i++) {
				scan.nextLine();
			}
			line = scan.nextLine();
		} else if (kills < 10) {
			for (int i = 0; i < a + 6; i++) {
				scan.nextLine();
			}
			line = scan.nextLine();
		} else {
			for (int i = 0; i < 9; i++) {
				scan.nextLine();
			}
			line = scan.nextLine();
		}
		Scanner lineScan = new Scanner(line);
		name = lineScan.next();
		power = lineScan.nextInt();
		accuracy = lineScan.nextInt();
		String ifo = lineScan.nextLine();
		info = ifo.substring(1, ifo.length());
	}

	public String gName() {
		return name;
	}

	public String gInfo() {
		return info;
	}

	public int gPower() {
		return power;
	}

	public int gAccuracy() {
		return accuracy;
	}

	public void sPower(int Power) {
		power = Power;
	}

	public void sAccuracy(int Accuracy) {
		accuracy = Accuracy;
	}

	public void sName(String Name) {
		name = Name;
	}

}
