import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class fakeyNewBattle extends fakeymonGAME {
	private boolean run = false;

	private int hp2, spe2, atk2, def2, score, currentMon = 0;
	private String name2, at2Name;
	private fakeyItem monsterItem = new fakeyItem();
	
	private fakeyNewMonster enemy;

	public fakeyNewBattle(int difficulty) throws InterruptedException, LineUnavailableException, IOException, UnsupportedAudioFileException {
		
		fakeyNewMonster monster = new fakeyNewMonster(gKills(), difficulty);
		monsterItem = monster.gMonsterItem();
		setStats(monster);
		enemy = monster;
		
		Scanner scan = new Scanner(System.in);
		Random rand = new Random();

		if(monster.gNAME().equals("Fakeyceus")) music("Fakeyceus");
		else music("Wild");
		printSlowln("\n\nA wild " + monster.gNAME() + " appeared! >");
		enter();

		
		
		printEnemyStats();
		printSlow("It is holding a " + monsterItem.gName().toLowerCase() + "!\n---\n");
		
		if (gmm().size() > 1) {
			printSlow("Which Fakeymon would you like to send out? >");
			enter();
			printMonsters();
			currentMon = confirmOpt(1, gmm().size()) - 1;
		}
		
		gmm().get(currentMon).aHeal(gmm().get(currentMon).gHeal()*-1);
		
		if (gmm().get(currentMon).gSpeed() >= spe2) {
			battleOptions();
		}

		while (hp2 > 0 && gmm().get(currentMon).gTempHP() > 0 && !run) {			
			// Thread.sleep(500);
			printSlow("\nEnemy " + name2 + " is planning to make a move... >");
			enter();

			// Name of enemy attack below
			int chooser = rand.nextInt(7) + 1;
			Scanner file = new Scanner(new File("src/Data/fakeyEnemyAttacks.txt"));
			String attackName = "";
			for (int i = 1; i <= chooser; i++) {
				String line = file.nextLine();
				Scanner lineScan = new Scanner(line);
				attackName = lineScan.nextLine();
			}
			printSlowln("Enemy " + name2 + " used " + attackName + "! >");
			
			
			
			at2Name = attackName;
			// Name of enemy attack above
			enter();
			enemyAttack();
			if (gmm().get(currentMon).gTempHP() > 0 && !run) {
				printSlowln(gmm().get(currentMon).gName() + "'s HP is at: " + gmm().get(currentMon).gTempHP() + "/" + gmm().get(currentMon).gHP() + ". >");
				enter();
			}
			if (gmm().get(currentMon).gTempHP() <= 0 && !run) {
				printSlowln(gmm().get(currentMon).gName() + " has died! >");
				enter();
				gmm().remove(gmm().get(currentMon));
				if (gmm().size() > 0) {
					printSlowln("Who would you like to send into battle?\n");
					printMonsters();
					printSlowln("--> ");
					currentMon = confirmOpt(1, gmm().size())-1;
					printSlowln("Fake-em-out, " + gmm().get(currentMon).gName() + "! >");
					enter();
				}
				else quit();
			}
			battleOptions();
			if (hp2 > 0 && !run) {
				printSlowln(name2 + "'s HP is at: " + hp2 + ". >");
				enter();
			}
		}
		if (!run) {
			printSlowln(name2 + " has died! >");
			enter();
			music("Theme");
			aKills();
			gmm().get(currentMon).sXP(gmm().get(currentMon).gXP() + monster.gGainedXP());
			printSlowln(
					"You received " + monster.gGainedXP() + " XP points! You now have " + gmm().get(currentMon).gXP() + " XP points! >");
			enter();
			int itemChance = rand.nextInt(100) + 1;
			if(gmm().get(currentMon).gSpeed() * 2 >= itemChance) {
				gotMonsterItem(monsterItem);
			}
		}
		if (hp2 <= 0) score += def2 + atk2;
		System.out.println("\n-------------------------------------------------------------------");
		if (new Random().nextDouble() < (1.0*gmm().get(currentMon).gAttack()/(5.0*monster.gAtk())) || monster.gNAME().equals("Fakeyceus")) {
			printSlowln(monster.gNAME() + " wants to join the team.\n---");
			Thread.sleep(300);
			
			printEnemyStats();
			printSlow("Will you allow " + monster.gNAME() + " to join your team? (yes/no) --> ");
			if (yes()) {
				printSlow(monster.gNAME() + " joined the team! > ");
				enter();
				gmm().add(new fakeyMyMonster(setFakeyName(monster.gNAME()), 0, monster.gOHP(), monster.gOHP(),
						monster.gAtk(), monster.gDef(), monster.gSpe(), 0, new ArrayList<fakeyNewAttack>() {{add(new fakeyNewAttack(""));}}));
			}
		}
		if (gKills() == 21) {
			printSlow("\n---\n~~~> Congratulations! You won Fakeymon! Would you like to keep playing? (yes/no) ~> ");
			if (!yes()) {
				saveGame();
				quit();
			}
		}
	}

	public void battleOptions() throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		fakeyMyMonster mm = gmm().get(currentMon);
		
		if (mm.gHeal() > 0) {
			if (mm.gTempHP() + mm.gHeal() <= mm.gHP()) {
				mm.sTempHP(mm.gTempHP() + mm.gHeal());
				printSlow(mm.gName() + " regained " + mm.gHeal() + " HP! > ");
				enter();
			}
			else if (mm.gTempHP() < mm.gHP()){
				mm.sTempHP(mm.gHP());
				printSlow(mm.gName() + " regained full HP! > ");
				enter();
			}	
		}
			
		Scanner scan = new Scanner(System.in);
		int battleOption;
		do {
			printSlowln(
					"Press 1 to choose an attack, press 2 to see stats, press 3 to view current attacks, press 4 to use a different Fakeymon,\n"
					+ "press 5 to use an item, and press X to attempt to run.");
			battleOption = confirmOpt(0, 5);
			
			if (battleOption == 1) {
				int atkOption = useAttack(gmm().get(currentMon).gMoves());
				if (atkOption == 0) battleOption = -1;
				else attack(gmm().get(currentMon).gMoves().get(atkOption-1));
			}
			if (battleOption == 2)
				printStats();
			if (battleOption == 3)
				printAttacks();
			if (battleOption == 4) {
				currentMon = switchOut(currentMon);
				mm = gmm().get(currentMon);
			}
			if (battleOption == 5) {
				printBag(true, currentMon);
			}
			if (battleOption == 0) {
				if (gmm().get(currentMon).gSpeed() >= spe2) {
					run = true;
					int lostScore = score / 10;
					score -= lostScore;
					printSlowln("You successfully ran from the enemy " + name2 + ". Your score decreased by "
							+ lostScore + ". > ");
					enter();
				} else {
					if (new Random().nextInt(100) + 1 <= (gmm().get(currentMon).gSpeed() / spe2) * 100) {
						run = true;
					} else {
						printSlowln(gmm().get(currentMon).gName() + " was not able to run from the enemy " + name2 + ".");
						battleOption = 1;
					}
				}
			}
		} while (battleOption != 1 && !run);
	}

	public void attack(fakeyNewAttack move) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		printSlowln(gmm().get(currentMon).gName() + " used " + move.gName() + "!");

		sfx("Punch");
		
		int hp2Old = hp2;
		Random rand = new Random();

		int damage = move.gPower();

		if (damage > 0) {
			int crit = rand.nextInt(100) + 1;
			if (crit <= 10)
				damage += damage / 2;
			else if (crit <= 20)
				damage -= damage / 2;

			int acc = rand.nextInt(100) + 1;
			if (move.gAccuracy() >= acc) {
				hp2 -= (damage + (int) (Math.ceil((move.gPower()
						* (((double) gmm().get(currentMon).gAttack()) / (((double) def2) + 1) + (gmm().get(currentMon).gSpeed() / (def2 * def2)))))));
				printSlowln(name2 + " took " + (hp2Old - hp2) + " damage!");
				if (damage > move.gPower())
					printSlowln("It was a critical hit!");
				if (damage < move.gPower())
					printSlowln(name2 + " defended well and took less damage!");
			} else {
				printSlowln(
						gmm().get(currentMon).gName() + " missed " + move.gName() + " on enemy fakeyMon " + name2 + "!");
			}
		}
		
		checkMoveEffects(move);
		
	}
	private void checkMoveEffects(fakeyNewAttack move) throws InterruptedException {
		if (move.gName().equals("stoic")) {
			effect(new String[]{"hp", "def"}, 15, 0, 15, 0);
		}
		if (move.gName().equals("dragon-energy")) {
			effect(new String[]{"atk"}, 0, 40, 0, 0);
		}
		if (move.gName().equals("demonic-aura")) {
			effect(new String[]{"atk"}, 0, 50, 0, 0);
		}
		if (move.gName().equals("crystallize")) {
			effect(new String[]{"def"}, 0, 0, 40, 0);
		}
		if (move.gName().equals("holy-hoo")) {
			effect(new String[]{"hp"}, 35, 0, 0, 0);
		}
		if (move.gName().equals("aggressive-smack") && (new Random()).nextInt(100) + 1 >= 80) {
			effect(new String[]{"atk"}, 0, 15, 0, 0);
		}
		if (move.gName().equals("chaotic-smack")) {
			effect(new String[]{"hp"}, -10, 0, 0, 0);
		}
		if (move.gName().equals("hydra's-dance")) {
			effect(new String[]{"hp, def"}, 25, 0, 25, 0);
		}
		if (move.gName().equals("wyvern's-dance")) {
			effect(new String[]{"atk, spe"}, 0, 25, 0, 25);
		}
		if (move.gName().equals("legend's-dance")) {
			effect(new String[]{"atk, def, spe"}, 0, 25, 25, 25);
		}
	}
	private void effect(String[] stat, int e1, int e2, int e3, int e4) throws InterruptedException {
		/*aHP (boolean below) means AFFECT HP. This is true if stat[] contains "hp", signaling to the system that
		 * hp must be affected.
		 * 
		 * If you want to make an attack that will raise Atk by 30 and Spe by 30, for example,
		 * call effect(new String[]{"atk, spe"}, 30, 0, 0, 30).
		 * e1 = HP, e2 = atk, e3 = def, e4 = spe
		*/
		boolean aHP = false;
		boolean aAtk = false;
		boolean aDef = false;
		boolean aSpe = false;
		
		int oldTHP = gmm().get(currentMon).gTempHP();
		int oldAtk = gmm().get(currentMon).gAttack();
		int oldDef = gmm().get(currentMon).gDefense();
		int oldSpe = gmm().get(currentMon).gSpeed();

		for (int i = 0; i < stat.length; i++) {
			if (stat[i].equals("hp"))
				aHP = true;
			if (stat[i].equals("atk"))
				aAtk = true;
			if (stat[i].equals("def"))
				aDef = true;
			if (stat[i].equals("spe"))
				aSpe = true;
		}
		if (aHP) {
			if (gmm().get(currentMon).gTempHP() < gmm().get(currentMon).gHP() - (int)Math.ceil((double) gmm().get(currentMon).gHP() * e1 / 100)) {
				gmm().get(currentMon).sTempHP(gmm().get(currentMon).gTempHP() + (int)Math.ceil(((double) gmm().get(currentMon).gHP() * e1 / 100)));
				printSlowln(gmm().get(currentMon).gName() + " healed " + (int)(Math.ceil((double) gmm().get(currentMon).gHP() * e1 / 100)) + " HP! (" + oldTHP + " --> " + gmm().get(currentMon).gTempHP() + ")");
			} else if (gmm().get(currentMon).gTempHP() < gmm().get(currentMon).gHP()) {
				gmm().get(currentMon).sTempHP(gmm().get(currentMon).gHP());
				printSlow(gmm().get(currentMon).gName() + " fully healed its HP!");
			}
		}
		if (aAtk) {
			gmm().get(currentMon).sAttack(gmm().get(currentMon).gAttack() + (int)Math.ceil(((double) gmm().get(currentMon).gAttack() * e2 / 100)));
			printSlowln(gmm().get(currentMon).gName() + "'s attack rose by " + (int)Math.ceil(((double) gmm().get(currentMon).gAttack() * e2 / 100)) + " points! (" + oldAtk + " --> " + gmm().get(currentMon).gAttack() + ")");
		}
		if (aDef) {
			gmm().get(currentMon).sDefense(gmm().get(currentMon).gDefense() + (int)Math.ceil(((double) gmm().get(currentMon).gDefense() * e3 / 100)));
			printSlowln(gmm().get(currentMon).gName() + "'s defense rose by " + (int)Math.ceil(((double) gmm().get(currentMon).gDefense() * e3 / 100)) + " points! (" + oldDef + " --> " + gmm().get(currentMon).gDefense() + ")");
		}
		if (aSpe) {
			gmm().get(currentMon).sSpeed(gmm().get(currentMon).gSpeed() + (int)Math.ceil(((double) gmm().get(currentMon).gSpeed() * e4 / 100)));
			printSlowln(gmm().get(currentMon).gName() + "'s speed rose by " + (int)(Math.ceil((double) gmm().get(currentMon).gSpeed() * e4 / 100)) + " points! (" + oldSpe + " --> " + gmm().get(currentMon).gSpeed() + ")");
		}
	}
	public void enemyAttack() throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		int hpOld = gmm().get(currentMon).gTempHP();
		Random rand = new Random();

		int damage = 5;
		if (atk2 <= 10)
			damage = atk2 / 2;

		int crit = rand.nextInt(100) + 1;
		if ((crit <= (((double) gmm().get(currentMon).gSpeed()) / 100) * 40))
			damage += damage / 2;
		else if (crit >= 90)
			damage -= damage / 2;

		int acc = rand.nextInt(100) + 1;
		if (acc <= 90) {
			sfx("Punch");
			gmm().get(currentMon).sTempHP(gmm().get(currentMon).gTempHP() - (damage + (int) (Math.ceil((damage * (((double) atk2) / (((double) gmm().get(currentMon).gDefense()) + 1)))))));
			printSlowln(gmm().get(currentMon).gName() + " took " + (hpOld - gmm().get(currentMon).gTempHP()) + " damage!");
			if (damage > 5)
				printSlowln("A critical hit!");
			if (damage < 5)
				printSlowln(gmm().get(currentMon).gName() + " defended well and took less damage!");
		} else {
			printSlowln(name2 + " missed " + at2Name + " on " + gmm().get(currentMon).gName() + "!");
		}

	}

	public void setStats(fakeyNewMonster monster) {
		name2 = monster.gNAME();
		hp2 = monster.gHP();
		atk2 = monster.gAtk();
		def2 = monster.gDef();
		spe2 = monster.gSpe();
	}
	public int gHp2() {
		return hp2;
	}
	public int gHp() {
		return gmm().get(currentMon).gTempHP();
	}
	public int gAt2() {
		return atk2;
	}
	public int gDef2() {
		return def2;
	}

	public void printEnemyStats() throws InterruptedException {
		printSlow(
				name2 + "'s stats:" + "\nHP:\t" + enemy.gOHP() + "\nAtk:\t" + enemy.gAtk() + "\nDef:\t"
						+ enemy.gDef() + "\nSpeed:\t" + enemy.gSpe() + "\n---\n");
	}
}