import java.util.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class fakeymonGAME extends fakeyMyTrainer {
	private static Scanner scan = new Scanner(System.in);
	private static Clip music;
	private static Clip sfx;
	public static void main(String[] args) throws Exception {
		music("Gate");
		printSlow("\t-=- Welcome to Fakeymon Adventures -=-\n");
		start();
	}
	private static void start() throws Exception {
		boolean wantsNewGame = false;
		do {// first Options
			printSlow(
					"---\nPress 1 to start a new game, press 2 to open a save file, press 3 to view credits, or press 4 to quit the game.\n--> ");
			int option = confirmOpt(1, 4);
			if (option == 1) {
				printSlow("Are you sure you would like to start a new adventure? (yes/no) --> ");
				if (yes())
					wantsNewGame = true;
			}
			if (option == 2)
				loadName("");
			if (option == 3)
				printCredits();
			if (option == 4) {
				System.out.print("Are you sure you would like to quit? (yes/no) --> ");
				if (yes()) {
					printCredits();
					System.exit(0);
				}
			}
		} while (!wantsNewGame);
		// Below is IF STARTS NEW GAME
		setName();
		aScore(5);

		printSlow("-------------------------------------------------------------------"
				+ "\nWhenever you see a '>' character at the end of a statement, please press enter to continue. >");
		enter();

		// personalityTest();

		aScore(50);

		printSlowln("-------------------------------------------------------------------");
		gmm().get(0).sName(setFakeyName("Slave"));

		printSlow("-------------------------------------------------------------------"
				+ "\nYou get to start the game with a total of " + gmm().get(0).gXP()
				+ " experience points! You may choose what " + gmm().get(0).gName()
				+ "'s stats are!\nThe four categories are: >");
		enter();
		printSlow("-> Health points >");
		enter();
		printSlow("-> Attack points >");
		enter();
		printSlow("-> Defense points >");
		enter();
		printSlow("-> Speed points >");
		enter();
		printSlow("You may invest your experience points throughout the four categories. >");
		enter();

		fakeyXP();

		aScore(100);

		System.out.println("-------------------------------------------------------------------" + "\nCongratulations! "
				+ gmm().get(0).gName() + " is now ready to fight!\n"
				+ "FakeymonGAME is a video game all about using your Fakeymon to fight in battle as a tool,"
				+ "\nuntil it dies or you use it to defeat Fakeyceus. >");
		enter();
		
		//printSlow("---\n[1.Normal][2.Insane][3.Godlike]" + "\nChoose your difficulty! (Type '1', '2', or '3')\n--> ");
		//confirmOpt(1, 3);

		new fakeyNormalDifficulty();
	}
	private static void setName() throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		String Name = "";
		boolean confirm = false;
		printSlow("What is your name? (Cannot contain \\/:*?\"<>|) --> ");
		while (!confirm) {
			Name = new Scanner(System.in).next();
			if (Name.indexOf("/") != -1 || Name.indexOf("\\") != -1 || Name.indexOf(":") != -1
					|| Name.indexOf("*") != -1 || Name.indexOf("?") != -1 || Name.indexOf("\\") != -1
					|| Name.indexOf("\"") != -1 || Name.indexOf("<") != -1 || Name.indexOf(">") != -1
					|| Name.indexOf("|") != -1) {
				System.err.print("That is an invalid name. Try again.\n--> ");
				Name = "";
			} else {
				sfx("Enter");
				printSlow("Your name is " + Name + ". Confirm? (yes/no) ");
				if (yes()) {
					confirm = true;
					sName(Name);
				} else {
					printSlow("What would you like to name yourself instead? --> ");
					Name = "";
				}
			}
		}
	}
	static String setFakeyName(String Name) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		boolean confirm = false;
		String name = Name;
		while (!confirm) {
			printSlow("Would you like to give your Fakeymon a new name? (yes/no) --> ");
			if (yes()) {
				printSlow("What would you like to name your Fakeymon? Type 'X' to cancel. --> ");
				while (!confirm) {
					name = new Scanner(System.in).next();
					sfx("Enter");
					if (!name.toUpperCase().equals("X")) {
						printSlow("Your Fakeymon will be called " + name
								+ " for the rest of the game. Confirm? (yes/no) ");
						if (!yes()) {
							printSlow("What would you like to name it instead? Type 'X' to cancel. --> --> ");
						} else {
							confirm = true;
						}
					} else {
						printSlow("Do you want to cancel giving this Fakeymon a new name? (yes/no) --> ");
						if (yes())
							confirm = true;
						else
							printSlow("What would you like to name your Fakeymon? Type 'X' to cancel. --> ");
					}
				}
			} else
				confirm = true;
		}
		return name;
	}

	// UTILITIES

	// confirm

	static boolean yes() throws UnsupportedAudioFileException, IOException, LineUnavailableException {// makes sure that whatever the person type is either yes or no
		String a = "";
		while (!a.toLowerCase().equals("yes") && !a.toLowerCase().equals("no")) {
			a = scan.next();
			sfx("Enter");
			if (!a.toLowerCase().equals("yes") && !a.toLowerCase().equals("no"))
				System.err.print("Please state only 'Yes' or 'No'.\n--> ");
		}
		if (a.toLowerCase().equals("yes"))
			return true;
		return false;
	}
	static int confirmOpt(int min, int max) {// makes sure that whatever the user types will be in between min and max
		while (1 > 0) {
			int option = 0;
			try {
				String a = "";
				a = new Scanner(System.in).next();
				sfx("Enter");
				if (a.toUpperCase().equals("X") && min == 0)
					return 0;
				else
					option = Integer.parseInt(a);
				if (option < min || option > max || option == 0)
					throw new Exception();
				return option;
			} catch (Exception e) {
				System.err.print("Please type an actual whole number or letter listed as an option.\n--> ");
			}
		}
	}

	// printers

	static void printSlow(String print) throws InterruptedException {// slowly prints text passed in
		for (int i = 0; i < print.length(); i++) {
			System.out.print(print.charAt(i));
			Thread.sleep(10);
		}
	}
	static void printSlowln(String print) throws InterruptedException {// slowly println text passed in
		if (!print.equals(null)) {

			for (int i = 0; i < print.length(); i++) {
				System.out.print(print.charAt(i));
				Thread.sleep(10);
			}
		}
		System.out.println();
	}
	static void printBag(boolean useBag, int currentMon) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		if (gBag().size() == 0) {
			printSlow("\n---\nYour bag is empty. >");
			enter();
		} else {
			printSlow("\n----------\nBAG (" + gBag().size() + "/" + gBagSize() + " items):\n---\n");
			for (int i = 0; i < gBag().size(); i++) {
				printSlowln((i + 1) + " | " + gBag().get(i) + " | Sells for $" + gBag().get(i).gSell() + " FakeyCoins");
				Thread.sleep(250);
			}
			printSlow("---\n");
			if (useBag)
				useBagOpt(true, currentMon);
		}
	}
	static void printStats() throws InterruptedException {// Prints game stats
		int i = 0;
		if (gmm().size() > 1) {
			printSlow("Which Fakey's stats would you like to view?\n");
			printMonsters();
			i = confirmOpt(1, gmm().size()) - 1;
		}

		printSlowln("---" + "\n[" + gmm().get(i).gName() + "'s Stats]" + "\n-> XP: " + gmm().get(i).gXP() + "\n-> HP: "
				+ gmm().get(i).gTempHP() + "/" + gmm().get(i).gHP() + "\n-> Atk: " + gmm().get(i).gAttack()
				+ "\n-> Def: " + gmm().get(i).gDefense() + "\n-> Speed: " + gmm().get(i).gSpeed() + "\n---");
	}
	static String printMonsters() throws InterruptedException {
		String fakeyList = "";
		for (int i = 0; i < gmm().size(); i++)
			fakeyList += "(Type " + (i + 1) + " for " + gmm().get(i).gName() + ")" + System.lineSeparator();
		printSlow(fakeyList);
		return fakeyList;
	}
	static void printAttacks() throws InterruptedException {// Prints all attacks
		int i = 0;
		if (gmm().size() > 1) {
			printSlow("Which Fakey's attacks would you like to view?\n");
			printMonsters();
			i = confirmOpt(1, gmm().size()) - 1;
		}
		printSlowln("---" + "\n[" + gmm().get(i).gName() + "'s attacks]");
		for (int j = 0; j < gmm().get(i).gMoves().size(); j++)
			printSlowln(gmm().get(i).gMoves().get(j).gName().toUpperCase().charAt(0)
					+ gmm().get(i).gMoves().get(j).gName().substring(1, gmm().get(i).gMoves().get(j).gName().length())
					+ " | PWR " + gmm().get(i).gMoves().get(j).gPower() + " | ACC "
					+ gmm().get(i).gMoves().get(j).gAccuracy() + " | " + gmm().get(i).gMoves().get(j).gInfo());
	}
	static void printCredits() throws InterruptedException {// Prints the credits
		printSlowln("\n-	-	-	-	-	-\n\t       -=- Credits -=-");
		Thread.sleep(500);
		printSlowln("-> Director\t\t//   Paul Karmel");
		Thread.sleep(500);
		printSlowln("-> Lead Programmer\t//   Paul Karmel");
		Thread.sleep(500);
		printSlowln("-> Sound Design\t\t//   Shivansh Padhi");
		Thread.sleep(500);
		printSlowln("-> Data Manager\t\t//   Shivansh Padhi");
		Thread.sleep(500);
		printSlowln("-> Assist. Programmer\t//   Shivansh Padhi & Maxime Dahan");
		Thread.sleep(500);
		printSlowln("-> Battle Director\t//   Maxime Dahan");
		Thread.sleep(500);
		printSlowln("-> Creature Design\t//   Maxime Dahan");
		Thread.sleep(500);
		printSlowln("-	-	-	-	-	-");
		System.out.println();
		Thread.sleep(500);
	}

	// save files

	static void saveGame() // Saves game
			throws FileNotFoundException, InterruptedException {
		String bagString = "";
		PrintStream saveOut = new PrintStream(new File("src/Data/SaveFiles/" + gName().toUpperCase() + ".txt"));
		saveOut.print(gName() + System.lineSeparator() + gKills() +  System.lineSeparator() 
				+ gScore() + System.lineSeparator()
				+ gmm().size() + System.lineSeparator()
				+ System.lineSeparator());
		for (int i = 0; i < gmm().size(); i++) {
			String movesString = "";
			for (int j = 0; j < gmm().get(i).gMoves().size(); j++) {
				movesString += System.lineSeparator() + gmm().get(i).gMoves().get(j).gName() + " "
						+ gmm().get(i).gMoves().get(j).gPower() + " " + gmm().get(i).gMoves().get(j).gAccuracy();
			}

			saveOut.print(gmm().get(i).gName() + System.lineSeparator() + gmm().get(i).gXP() + System.lineSeparator()
					+ gmm().get(i).gHP() + System.lineSeparator() + gmm().get(i).gTempHP() + System.lineSeparator()
					+ gmm().get(i).gAttack() + System.lineSeparator() + gmm().get(i).gDefense() + System.lineSeparator()
					+ gmm().get(i).gSpeed() + System.lineSeparator() + gmm().get(i).gMoves().size() + movesString
					+ System.lineSeparator());
		}

		for (int j = 0; j < gBag().size(); j++) {
			bagString += System.lineSeparator() + gBag().get(j).gID();
		}
		saveOut.print(System.lineSeparator() + bagString);

		System.out.println("\n---");
		printSlow("Autosaving");
		for (int i = 0; i < 3; i++) {
			System.out.print(".");
			// Thread.sleep(750);
		}
		System.out.println("\n---");

	}
	static void loadName(String name) throws Exception {// Loads game
		String Name = name;
		boolean foundFile = false;
		Scanner saveFile = new Scanner(Name);

		if (name == "") {
			printSlow(
					"Please type the name of the character in the save file you would like to access. To cancel, type 'X'. --> ");
		}
		Name = "";
		for (boolean isSatisfied = false; !isSatisfied && !foundFile;) {
			try {
				while (Name.isEmpty())
					Name = (new Scanner(System.in)).nextLine().toUpperCase();
				saveFile = new Scanner(new File("src/Data/SaveFiles/" + Name + ".txt"));
				new fakeyNormalDifficulty(saveFile);
			} catch (Exception e) {
				if (!Name.toUpperCase().equals("X")) {
					System.err.print(
							"That character does not exist. Please try to rewrite the name exactly as you spelled it. To cancel, type 'X'.\n--> ");
					Thread.sleep(1);
					Name = "";
				}
				if (Name.toUpperCase().equals("X"))
					isSatisfied = true;
			}
		}
		if (Name.toUpperCase().equals("X")) start();
	}
	static void loadGame(Scanner saveFile) throws NumberFormatException, FileNotFoundException {
		try {
			sName(saveFile.nextLine());
			int kills = saveFile.nextInt();
			for (int i = 0; i < kills; i++)
				aKills();
			aScore(saveFile.nextInt());
			int numMons = saveFile.nextInt();
			saveFile.nextLine();
			saveFile.nextLine();
			ArrayList<fakeyMyMonster> mm = gmm();
			mm.remove(0);
			for (int i = 0; i < numMons; i++)
				mm.add(new fakeyMyMonster());
			for (int i = 0; i < mm.size(); i++) {
				String name = saveFile.nextLine();
				mm.get(i).sName(name);
				mm.get(i).sXP(saveFile.nextInt());
				mm.get(i).sHP(saveFile.nextInt());
				mm.get(i).sTempHP(saveFile.nextInt());
				mm.get(i).sAttack(saveFile.nextInt());
				mm.get(i).sDefense(saveFile.nextInt());
				mm.get(i).sSpeed(saveFile.nextInt());
				int amountOfMoves = saveFile.nextInt();

				ArrayList<fakeyNewAttack> moves = new ArrayList<fakeyNewAttack>();
				for (int k = 0; k < amountOfMoves; k++) {
					moves.add(new fakeyNewAttack(""));
				}
				for (int j = 0; j < amountOfMoves; j++) {// setting each move
					moves.get(j).sName(saveFile.next());
					moves.get(j).sPower(saveFile.nextInt());
					String acc = saveFile.nextLine();
					moves.get(j).sAccuracy(Integer.parseInt(acc.substring(1, acc.length())));
				}
				gmm().get(i).sMoves(moves);
			}
			while (saveFile.hasNextInt()) {
				gBag().add(new fakeyItem(saveFile.nextInt()));
			}

		} catch (Exception e) {
			System.err.print(
					"That character does not exist. Please try to rewrite the name exactly as you spelled it. To cancel, type 'X'.\n--> ");
		}
	}

	// miscellaneous

	static void fakeyXP() throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {

		int i = 0;
		if (gmm().size() > 1) {
			printSlow("Which Fakey's stats would you like to change?\n");
			printMonsters();
			i = confirmOpt(1, gmm().size()) - 1;
		}

		int hp = gmm().get(i).gHP();
		int tempHP = gmm().get(i).gTempHP();
		int XP = gmm().get(i).gXP();
		int atk = gmm().get(i).gAttack();
		int def = gmm().get(i).gDefense();
		int spe = gmm().get(i).gSpeed();

		if (gmm().get(i).gXP() < 1) {
			printSlow("\nYou are unable to use XP, as you currently have none. >");
			enter();
		} else {
			boolean acceptStats = false;

			int difHp = 0;
			int difAtk = 0;
			int difDef = 0;
			int difSpe = 0;

			while (!acceptStats) {
				printSlow("\nYou have " + gmm().get(i).gXP() + " experience points remaining."
						+ "\nHow many HEALTH POINTS would you like to invest?" + "\n(current HP: "
						+ gmm().get(i).gTempHP() + "/" + gmm().get(i).gHP() + ") --> ");
				hp += difHp = investCheck(i);
				sfx("Enter");
				tempHP += difHp;
				XP -= difHp;
				if (XP > 0) {
					printSlow("\nYou have " + XP + " experience points remaining."
							+ "\nHow many ATTACK POINTS would you like to invest?" + "\n(current attack: "
							+ gmm().get(i).gAttack() + ") --> ");
					atk += difAtk = investCheck(i);
					sfx("Enter");
					XP -= difAtk;
				}
				if (XP > 0) {
					printSlow("\nYou have " + XP + " experience points remaining."
							+ "\nHow many DEFENSE POINTS would you like to invest?" + "\n(current defense: "
							+ gmm().get(i).gDefense() + ") --> ");
					def += difDef = investCheck(i);
					sfx("Enter");
					XP -= difDef;
				}
				if (XP > 0) {
					printSlow("\nYou have " + XP + " experience points remaining."
							+ "\nHow many SPEED POINTS would you like to invest?" + "\n(current speed: "
							+ gmm().get(i).gSpeed() + ") --> ");
					spe += difSpe = investCheck(i);
					sfx("Enter");
					XP -= difSpe;
				}
				printSlow("---\n[Old Stat --> New Stat]\nHP:\t" + gmm().get(i).gHP() + " --> " + hp + "\nAtk:\t"
						+ gmm().get(i).gAttack() + " --> " + atk + "\nDef:\t" + gmm().get(i).gDefense() + " --> " + def
						+ "\nSpeed:\t" + gmm().get(i).gSpeed() + " --> " + spe + "\n" + "Remaining XP: " + XP + "\n"
						+ "Confirm change? (yes/no) ");
				if (!yes()) {
					XP = gmm().get(i).gXP();
					hp = gmm().get(i).gHP();
					atk = gmm().get(i).gAttack();
					def = gmm().get(i).gDefense();
					spe = gmm().get(i).gSpeed();
					tempHP = gmm().get(i).gTempHP();
				} else
					acceptStats = true;
			}
			gmm().get(i).sXP(XP);
			gmm().get(i).sHP(hp);
			gmm().get(i).sAttack(atk);
			gmm().get(i).sDefense(def);
			gmm().get(i).sSpeed(spe);
			gmm().get(i).sTempHP(tempHP);
		}
	}
	private static int investCheck(int i) throws InterruptedException {
		Scanner invest = new Scanner(System.in);
		int a = -1;
		while (a > gmm().get(i).gXP() || a < 0) {
			while (!invest.hasNextInt()) {
				System.err
						.println("Please return a whole number that is less than or equal to your current amount of XP"
								+ " (current XP: " + gmm().get(i).gXP() + ")");
				printSlow("New points --> ");
				invest.next();
			}
			a = invest.nextInt();
			if (a > gmm().get(i).gXP() || a < 0) {
				System.err
						.println("Please return a whole number that is less than or equal to your current amount of XP"
								+ " (current XP: " + gmm().get(i).gXP() + ")");
				printSlow("New points --> ");
			}
		}
		return a;
	}
	static void fakeyNewAttack() throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		// "Your fakeyMon wants to learn a new move."

		Scanner scan = new Scanner(System.in);
		fakeyNewAttack nm = new fakeyNewAttack("punch");

		printSlowln("\nYou have assembled enough energy - in kills - to teach your Fakeymon a new move.");
		nm.moveGen();
		printSlow("The new attack is called " + nm.gName() + ". " + nm.gInfo() + "\nAre you interested in teaching "
				+ nm.gName() + " to a Fakeymon? (yes/no) --> ");
		if (yes()) {
			for (boolean satisfied = false; !satisfied;) {
				int i = 0;
				if (gmm().size() > 1) {
					printSlowln("Who would you like to teach " + nm.gName() + " to?");
					printMonsters();
					printSlow("\nType 'X' to cancel.\n--> ");
					i = confirmOpt(0, gmm().size()) - 1;
				}
				if (i >= 0) {
					if (gmm().get(i).gMoves().size() < 5) {
						gmm().get(i).gMoves().add(nm);
						printSlow(gmm().get(i).gName() + " learned " + nm.gName() + "!");
						satisfied = true;
					} else {
						printSlow(gmm().get(i).gName() + " does not have the brains to learn more than 5 moves. > ");
						enter();
						printAttacks();
						printSlow("\nWhich move would you like to replace? Or, press 'X' to cancel." + "\n--> ");
						int moveToReplace = confirmOpt(0, 5) - 1;
						if (moveToReplace != -1) {
							printSlow("Are you sure you would like to replace "
									+ gmm().get(i).gMoves().get(moveToReplace).gName() + " with " + nm.gName()
									+ "? (yes/no)\n--> ");
							if (yes()) {
								gmm().get(i).gMoves().set(moveToReplace, nm);
								satisfied = true;
							}
						} else {
							printSlow("Are you sure you would like to cancel learning this new move? (yes/no)\n--> ");
							if (yes())
								satisfied = true;
						}
					}
				} else {
					printSlow("Are you sure you want to cancel learning this new move?");
					if (yes())
						satisfied = true;
				}
			}
		}
	}
	static String enter() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		String a = new Scanner(System.in).nextLine();
		sfx("Enter");
		return a;
	}
	static int switchOut(int currentMon) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		if (gmm().size() < 2) {
			printSlow("You cannot use a different Fakeymon, as you only have one. >");
			enter();
		} else {
			printSlow("Which Fakeymon would you like to use instead of " + gmm().get(currentMon) + "? >");
			enter();
			printMonsters();
			printSlow("Or, type 'X' to cancel.\n--> ");
			int mChosen = confirmOpt(0, gmm().size());
			if (mChosen != 0) {
				printSlow("Come back, " + gmm().get(currentMon) + "!");
				Thread.sleep(300);
				currentMon = mChosen;
				printSlow("Fake-em-out, " + gmm().get(currentMon) + "! > ");
				enter();
			}
		}
		return currentMon;
	}
	static int useAttack(ArrayList<fakeyNewAttack> moves) throws InterruptedException {// To use an attack
		printSlow("You may choose");
		int i = 0;
		while (i < moves.size()) {

			if (i == moves.size() - 1 && moves.size() > 1)
				printSlow(" or");
			printSlow(" " + moves.get(i).gName());
			if (i < moves.size() - 1 && moves.size() > 2)
				printSlow(",");

			i++;
		}
		printSlow(".\n(please type"); // (Please type 1, 2, 3, or 4)
		for (int j = 1; j <= i; j++) {
			if (j == i && i > 1)
				printSlow(" or");
			printSlow(" " + j);
			if (j < i - 1 && i > 1 && moves.size() > 2)
				printSlow(",");
		}
		printSlowln(". Or, press 'X' to cancel)\n--> ");
		int option = confirmOpt(0, moves.size());
		return option;
	}
	static void gotMonsterItem(fakeyItem monsterItem) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {// User pick up monster drop item?
		Scanner scan = new Scanner(System.in);
		printSlow("The enemy dropped a " + monsterItem.gName() + "! Would you like to pick it up? (yes/no) --> ");
		if (yes()) {
			boolean isSatisfied = false, bagPrinted = false;
			while (!isSatisfied) {
				if (gBag().size() < gBagSize()) {
					gBag().add(monsterItem);
					isSatisfied = true;
					printSlow("You obtained a " + monsterItem.gName() + "!");
				} else if (gBag().size() == gBagSize()) {
					if (!bagPrinted) {
						printBag(false, -1);
						bagPrinted = true;
					}
					printSlow("Your bag is full. Would you like to replace an item? (yes/no) --> ");
					if (yes()) {// if yes
						printSlow("Which item would you like to replace? Or, press X to cancel. (number or X) --> ");
						int bagChoice = confirmOpt(0, gBag().size());
						while (bagChoice != 0 && !isSatisfied) {
							if (bagChoice != 0) {
								printSlow("Are you sure you would like to replace your "
										+ gBag().get(bagChoice - 1).gName() + " with the " + monsterItem.gName()
										+ "? (yes/no) --> ");
								if (yes()) {
									gBag().set(bagChoice - 1, monsterItem);
									isSatisfied = true;
								} else {
									printSlow(
											"Which item would you like to replace? Or, press X to cancel. (number or X) --> ");
									bagChoice = confirmOpt(0, gBag().size());
								}
							}
						}
					}
				}
			}
			if (!isSatisfied) {
				printSlow("Are you sure you want to leave the " + monsterItem.gName() + "? (yes/no) --> ");
				if (yes())
					isSatisfied = true;
			}
		}
	}
	static void useBagOpt(boolean useBag, int currentMon) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {// Gives the text for using an
																						// item
		printSlow("Would you like to use an item? (yes/no) --> ");
		if (yes()) {
			for (boolean satisfied = false; !satisfied;) {
				printSlow("Which item would you like to use? 'X' to cancel. --> ");
				int choice = confirmOpt(0, gBag().size()) - 1;
				if (choice > -1) {
					int mChoice = 0;
					if (gmm().size() > 1) {
						printMonsters();
						printSlow("Which monster would you like to use it on? --> ");
						mChoice = confirmOpt(1, gmm().size()) - 1;
					}
					printSlow("Are you sure you would like to use the " + gBag().get(choice).gName() + " on/with "
							+ gmm().get(mChoice).gName() + "? (yes/no) --> ");

					if (yes()) {
						uBagItem(gBag().get(choice), choice, mChoice, currentMon);
						satisfied = true;
					}

				} else
					satisfied = true;

			}
		}

	}
	static void uBagItem(fakeyItem item, int itemIndex, int fakeyChosen, int currentMon) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		// use an item in the bag

		if (item.gType().equals("Sell")) {
			printSlow("The only way to use this item is by selling it at the shop of a FakeyCenter.");
		}
		if (item.gType().equals("Heal")) {
			if (item.gName().equals("Draco hoo")) {
				if (gmm().get(fakeyChosen).gTempHP() < 1.2 * gmm().get(fakeyChosen).gHP()) {
					gmm().get(fakeyChosen).sTempHP((int) (gmm().get(fakeyChosen).gHP() * 1.2));
					printSlow(gmm().get(fakeyChosen).gName() + "'s HP rose to 120% of its max HP! > ");
					gBag().remove(itemIndex);
					enter();
				} else {
					printSlow(gmm().get(fakeyChosen).gName() + "'s HP is already at 120% of its max HP! > ");
					enter();
				}
			} else if (gmm().get(fakeyChosen).gTempHP() + item.gValue() < gmm().get(fakeyChosen).gHP()) {
				gmm().get(fakeyChosen).sTempHP(gmm().get(fakeyChosen).gTempHP() + item.gValue());
				printSlow(gmm().get(fakeyChosen).gName() + " regained HP! > ");
				gBag().remove(itemIndex);
				enter();
			} else if (gmm().get(fakeyChosen).gTempHP() < gmm().get(fakeyChosen).gHP()) {
				gmm().get(fakeyChosen).sTempHP(gmm().get(fakeyChosen).gHP());
				printSlow(gmm().get(fakeyChosen).gName() + " fully regained HP! > ");
				gBag().remove(itemIndex);
				enter();
			} else {
				printSlow(gmm().get(fakeyChosen).gName() + " already has maximum HP! > ");
				enter();
			}
		}
		if (item.gType().equals("Heal2")) {
			if (currentMon < 0)
				printSlow("That item can only be used in battle.");
			else if (currentMon != fakeyChosen)
				printSlow(gmm().get(fakeyChosen).gName() + " is not currently in battle. "
						+ "This item can only be used for mons in battle.");
			else {
				gmm().get(fakeyChosen).aHeal(item.gValue());
				printSlow("You used the " + item.gName() + " on " + gmm().get(fakeyChosen).gName() + "! > ");
				enter();
				gBag().remove(itemIndex);
			}
		}

		if (item.gType().equals("dam")) {
			printSlow("You cannot use this item here.");
		}

		if (item.gType().equals("dam2")) {
			printSlow("You cannot use this item here.");
		}

		if (item.gType().equals("dam3")) {
			printSlow("You cannot use this item here.");
		}

		if (item.gType().equals("heal2")) {
			printSlow("You cannot use this item here.");
		}

		if (item.gType().equals("Acc")) {
			printSlow("You cannot use this item here.");
		}

		if (item.gType().equals("Esc")) {
			printSlow("You cannot use this item here.");
		}

		if (item.gType().equals("Tch") && item.gValue() == 1) {
			printSlow("This has not yet been implemented.");
		}

		if (item.gType().equals("Tch") && item.gValue() == 2) {
			printSlow("This has not yet been implemented.");
		}

	}
	static void music(String songName) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		if (music != null) music.stop();
		AudioInputStream sound = AudioSystem.getAudioInputStream(new File("src/Data/Music/" + songName + ".wav"));
		music = AudioSystem.getClip();
		music.open(sound);
		music.loop(Clip.LOOP_CONTINUOUSLY);
	}
	static void sfx(String sfxName) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		AudioInputStream sound = AudioSystem.getAudioInputStream(new File("src/Data/Music/" + sfxName + ".wav"));
		sfx = AudioSystem.getClip();
		sfx.open(sound);
		sfx.start();
	}
	static void quit() throws FileNotFoundException, InterruptedException {
		PrintStream out = new PrintStream(new File("src/Data/HighScore.txt"));
		Scanner file = new Scanner(new File("src/Data/HighScore.txt"));
		int HighScore = 0;
		try {
			HighScore = file.nextInt();
		} catch (Exception e) {
			out.print("This is your best game's score: " + gScore() + "\nName of Fakeytrainer - " + gName());
		}

		if (HighScore < gScore()) {
			HighScore = gScore();
			out.print("This is your best game's score: " + gScore() + "\nName of Fakeytrainer - " + gName());
		}

		Thread.sleep(500);
		printSlowln("\n\n\nThank you for playing Fakeymon.");
		Thread.sleep(500);
		printSlowln("\nNAME: " + gName());
		Thread.sleep(500);
		printSlowln("TOTAL GAME SCORE: " + gScore());
		Thread.sleep(500);
		printCredits();
		System.exit(0);
	}
}