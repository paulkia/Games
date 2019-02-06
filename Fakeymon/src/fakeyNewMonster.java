import java.io.*;
import java.util.*;

public class fakeyNewMonster {
	private int originHP = 1, hp = 1, def = 1, at = 1, sp = 1, fakeyKill, randXP;
	private String name;
	private fakeyItem monsterItem = new fakeyItem(new Random());
	private Random rand = new Random();

	// creates monster, fakeyKill used to determine which one to make, and
	// difficulty for stats of fakeyMon
	public fakeyNewMonster(int fakeyKill, int difficulty) throws FileNotFoundException {

		if (fakeyKill == fakeymonGAME.gCeus()) {
			name = "Fakeyceus";
			this.fakeyKill = fakeyKill - 5;
			stats(rand);
			hp += 100;
			def += 100;
			at += 100;
			sp += 100;
			randXP = 100 + rand.nextInt(hp + def) / 2;
			originHP = hp;
			monsterItem = new fakeyItem(12);
		} else {
			this.fakeyKill = fakeyKill;

			int lines = 0;
			Scanner file = new Scanner(new File("src/Data/fakeyMonNames.txt"));
			while (file.hasNextLine()) {
				lines++;
				file.nextLine();
			}
			file = new Scanner(new File("src/Data/fakeyMonNames.txt"));
			// finds name of FM
			for (int i = 1; i <= (rand.nextInt(lines) + 1); i++) {
				String line = file.nextLine();
				Scanner lineScan = new Scanner(line);
				this.name = lineScan.next();
			}

			stats(rand);
			randXP = rand.nextInt(hp + def) / 2;
			originHP = hp;
		}
	}

	public int gOHP() {
		return originHP;
	}

	public int gHP() {
		return this.hp;
	}

	public int gAtk() {
		return this.at;
	}

	public int gDef() {
		return this.def;
	}

	public int gSpe() {
		return this.sp;
	}

	public int gGainedXP() {
		return ((this.at + this.hp) / 2 + randXP) / 2;
	}

	public String gNAME() {
		return this.name;
	}

	// determines stats of FM
	public void stats(Random rand) {
		int ov = this.fakeyKill * this.fakeyKill + 75;// the overall Xp left for the stats //FAKEYKILL * FAKEYKILL
		// assigns other stats
		while (this.hp + this.def + this.at + this.sp != ov) {
			this.hp = rand.nextInt(ov) + 1;
			this.def = rand.nextInt(ov) + 1;
			this.at = rand.nextInt(ov) + 1;
			this.sp = rand.nextInt(ov) + 1;
		}
	}

	public fakeyItem gMonsterItem() {

		return monsterItem;
	}
}
