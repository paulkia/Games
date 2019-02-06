import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

class fakeyNormalDifficulty extends fakeymonGAME {

	private Scanner scan = new Scanner(System.in);
	private static boolean justHadBattle = false, quitGame = false;

	fakeyNormalDifficulty() throws Exception {// NORMAL
		main(null);
	}

	fakeyNormalDifficulty(int This_Is_For_FakeyAuto) throws Exception {
		aScore(fakeyAuto.score);
		gmm().get(0).sXP(fakeyAuto.XP);
		gmm().get(0).sHP(fakeyAuto.hp);
		gmm().get(0).sTempHP(fakeyAuto.hp);
		gmm().get(0).sAttack(fakeyAuto.atk);
		gmm().get(0).sDefense(fakeyAuto.def);
		gmm().get(0).sSpeed(fakeyAuto.spe);
		gmm().get(0).sName(fakeyAuto.name);
		gmm().get(0).sMoves(fakeyAuto.moves);
		gmm().get(0).sMoves(fakeyAuto.mm.get(0).gMoves());
		ArrayList<fakeyItem> a = new ArrayList<fakeyItem>();
		a = gBag();
		a = fakeyAuto.gBag();
		aMon(fakeyAuto.gMon());
		fakeyNormalDifficulty.main(new String[5]); // COMMENT OUT THE BAG FOR LOOP IN MAIN IF USING FAUTO
	}

	fakeyNormalDifficulty(Scanner saveFile) throws Exception {// SaveFile

		loadGame(saveFile);
		
		printSlowln("\n\t-=- Welcome back, " + gName() + "! -=-\n\n\t>");
		scan.nextLine();

		fakeyNormalDifficulty.main(null);
	}

	public static void main(String[] args) throws Exception {
		boolean locationCheck = true;
		boolean newMoveCheck = true;

		if (gScore() == 0) {
			fakeymonGAME.main(null);
		}

		if (gKills() == 0)
			for (int i = 0; i < gBag().size(); i++)
				gBag().set(i, new fakeyItem());
		
		music("Theme");
		
		saveGame();

		while (!quitGame) {

			options();
			
			if (justHadBattle) {
				if (gKills() - 1 % 4 == 0 && newMoveCheck) {
					fakeyNewAttack();
					newMoveCheck = false;
				}
				if (gKills() - 2 % 3 == 0) {
					printSlow(
							"\n---\nOn your journey, you found a Fakeymon Center! It can fully heal your FakeyMon!\nWould you like to go? (yes/no)");
					if (yes()) {
						new fakeyCenter();
					}
				}
				if (gKills() < gCeus()) printSlow("\n---\nFakeyceus should appear in approximately " + (gCeus() - gKills())
						+ " battles.\n---\n");
			}
			
			if (quitGame) {
				printSlow("Are you sure you would like to quit the game? (yes/no)");
				if (!yes())
					quitGame = false;
			}
			justHadBattle = false;
		}
		quit();
	}

	private static void options() throws InterruptedException, LineUnavailableException, IOException, UnsupportedAudioFileException {
		// Gives the user options after every action

		Scanner scan = new Scanner(System.in);
		
		printSlow("\n---\nPlease type '1' to search for enemies, '2' to view the current stats of a Fakeymon, '3' to view the current attacks "
				+ gmm().get(0).gName() + " knows,"
				+ "\n'4' to invest XP, or '5' to open your bag. Or, type 'X' to quit the game." + "\n--> ");

		int option = confirmOpt(0, 5);
		if (option == 0)
			quitGame = true;
		if (option == 1)
			battlePrompt();
		if (option == 2)
			printStats();
		if (option == 3)
			printAttacks();
		if (option == 4)
			fakeyXP();
		if (option == 5)
			printBag(true, -1);

	}

	private static void battlePrompt() throws InterruptedException, LineUnavailableException, IOException, UnsupportedAudioFileException {
		boolean foundEnemy = false;
		if (gKills() == gCeus()) foundEnemy = true;
		for (int i = 1; i <= 5 && !foundEnemy; i++) {
			printSlowln("Searching for enemies...");
			Thread.sleep(500);
			if (new Random().nextInt(100) + 1 > 80) {
				foundEnemy = true;
			}
		}
		if (foundEnemy) {
			fakeyNewBattle battle = new fakeyNewBattle(1);
			gmm().get(0).sTempHP(battle.gHp());
			if (battle.gHp2() <= 0)
				aScore(battle.gAt2() + battle.gDef2());

			saveGame();
			justHadBattle = true;
		} else
			printSlowln("No enemy found...");
	}
}
