import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class fakeyCenter extends fakeymonGAME {
	private Random rand = new Random();
	private Scanner scan = new Scanner(System.in);
	private boolean wantsLeaveCenter = false, wantsLeaveShop = false, wantsLeaveClerk = false, steal = false;
	private ArrayList<fakeyItem> clerkI = new ArrayList<fakeyItem>();

	public fakeyCenter() throws InterruptedException, LineUnavailableException, IOException, UnsupportedAudioFileException {
		setCI(); // set clerk inventory
		music("Gate");
		printSlowln("\n\n---\n\t- Welcome to the FakeyCenter! -\n");
		printSlowln("You currently have " + gMon() + " fakeyCoins!");
		while (!wantsLeaveCenter) {
			printSlowln("Press 1 to fully heal your Fakeymon, 2 to enter the shop, or 3 to leave the establishment.");
			centerOpt(confirmOpt(1, 3));
			if (wantsLeaveCenter && !steal) {
				printSlow("Are you sure you would like to leave the FakeyCenter? (yes/no)\n--> ");
				if (!yes())
					wantsLeaveCenter = false;
			}
		}
	}

	public void setCI() throws FileNotFoundException {
		for (int i = 0; i < 5; i++) {
			clerkI.add(new fakeyItem(new Random()));
		}
		while (clerkI.contains(new fakeyItem(19))) {
			clerkI.set(clerkI.indexOf(new fakeyItem(19)), new fakeyItem(new Random()));
		}
	}

	public void centerOpt(int option) throws InterruptedException, LineUnavailableException, IOException, UnsupportedAudioFileException {
		if (option == 1) {
			printSlowln("Your Fakeymon are being healed...");
			Thread.sleep(900);
			music("Healyhoo");
			printSlow("Fake");
			Thread.sleep(750);
			printSlow("-fake");
			Thread.sleep(750);
			printSlow("-fakey");
			Thread.sleep(600);
			printSlowln("-mon");
			Thread.sleep(1000);
			music("Gate");
			for (int i = 0; i < gmm().size(); i++) {
				gmm().get(i).sTempHP(gmm().get(i).gHP());
			}
			printSlowln("Your Fakeymon have been fully healed!");
		} else if (option == 2) {
			while (!wantsLeaveShop) {
				printSlow(
						"Welcome to the shop! Press 1 to speak to the clerk, press 2 to talk to the old lady in the corner,"
								+ "\npress 3 to attempt to steal an item, or press 4 to leave. --> ");
				fakeyShop(confirmOpt(1, 4));

			}
			wantsLeaveShop = false;
		} else
			wantsLeaveCenter = true;
	}

	public void fakeyShop(int option) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		if (option == 1) {
			while (!wantsLeaveClerk) {
				printSlow("Clerk: 'Hi, how may I help you?'\n[1. BUY] [2. SELL] [3. LEAVE]\n--> ");
				clerkOpt(confirmOpt(1, 3));
			}
			wantsLeaveClerk = false;
		}
		if (option == 2) {
			Scanner oldLadyScanner = new Scanner(
					new File("src/Data/Old_Lady.txt"));
			int r = rand.nextInt(22) + 1;
			for (int i = 1; i <= r; i++)
				oldLadyScanner.nextLine();
			printSlowln("Old lady in the corner: '" + oldLadyScanner.nextLine() + "' >");
			scan.nextLine();
		}
		if (option == 3) {
			Random rand = new Random();
			int stealNum = rand.nextInt(100) + 1;
			printSlow("Which FakeyMon would you like to steal something for you?");
			printMonsters();
			printSlow("Or, type 'X' to cancel --> ");
			int poke = confirmOpt(1, gmm().size()) - 1;
			if (poke != -1) {
				if (gmm().get(poke).gSpeed() >= stealNum) {
					if (clerkI.size() > 0) {
						int it = rand.nextInt(clerkI.size());
						fakeyItem stolenItem = clerkI.get(it);
						clerkI.remove(it);
						steal(stolenItem);
					} else {
						printSlow("There are no items left in the store. > ");
						enter();
					} 
				} else {
					printSlowln("- That's illegal. >");
					scan.nextLine();
					aMon((gMon() / -2));
					printSlow("You have been kicked out of the store and have lost half of your money. >");
					scan.nextLine();
					wantsLeaveShop = true;
					wantsLeaveCenter = true;
					steal = true;
				}
			}
		}
		if (option == 4)
			wantsLeaveShop = true;
	}

	public void clerkOpt(int option) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		if (option == 1) {
			if (clerkI.size() > 0) {
				printSlowln("Clerk: 'Here are the items available for purchase!' >");
				scan.nextLine();
				printClerkI();
				printSlow("-> You currently have $" + gMon() + " FakeyCoins.\n---\n"
						+ "Clerk: 'Please type the number of the item you would like to purchase, or press 'X' to cancel.'\n--> ");
				int itemChoice = confirmOpt(0, clerkI.size()) -1;
				if (itemChoice != -1) {
					if(gMon() - clerkI.get(itemChoice).gBuy() >= 0) {
						addToBag(clerkI.get(itemChoice));
						aMon(clerkI.get(itemChoice).gBuy() * -1);
						clerkI.remove(itemChoice);
						printSlowln("Your current amount of fakeyCoins is " + gMon() + ".");
					}
					else {
						printSlow("You do not have enough fakeyCoins to buy that item. >");
						enter();
					}
				}
			} else if (steal) {
				printSlowln("Clerk: 'SOMEONE STOLE ALL OF THE ITEMS IN THE STORE!!!!!!' >");
				scan.nextLine();
			}
		}
		if (option == 2) {
			printBag(false, -1);
			if (gBag().size() > 0) {
				printSlow("Clerk: 'Which item would you like to sell to me?' : ");
				int item = scan.nextInt() - 1;
				printSlowln("Clerk: 'Thank you!' >");
				scan.nextLine();
				aMon(gBag().get(item).gSell());
				printSlow("Current money : " + gMon() + " >");
				gBag().remove(item);
				scan.nextLine();
			}
		}
		if (option == 3)
			wantsLeaveClerk = true;
	}
	
	public void steal(fakeyItem item) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		Scanner scan = new Scanner(System.in);
		boolean isSatisfied = false, bagPrinted = false;

		while (!isSatisfied) {
			if (gBag().size() < gBagSize()) {
				gBag().add(item);
				isSatisfied = true;
			}
			if (gBag().size() < gBagSize()) {
				printSlow("You stole a " + item.gName() + "! >");
				enter();
			} else if (gBagSize() == gBag().size()) {
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
							printSlow("Are you sure you would like to replace your " + gBag().get(bagChoice - 1).gName()
									+ " with the " + item.gName() + "? (yes/no) --> ");
							if (yes()) {
								gBag().set(bagChoice - 1, item);
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
			printSlow("Are you sure you want to leave the " + item.gName() + "? (yes/no) --> ");
			if (yes())
				isSatisfied = true;
		}
	}

	public void printClerkI() throws InterruptedException {
		printSlowln("---");
		for (int i = 0; i < clerkI.size(); i++) {
			printSlowln((i + 1) + " | $" + clerkI.get(i).gBuy() + " | " + clerkI.get(i));
			Thread.sleep(250);
		}
		printSlow("---\n");
	}

	public void addToBag(fakeyItem item) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		Scanner scan = new Scanner(System.in);
		boolean isSatisfied = false, bagPrinted = false;

		while (!isSatisfied) {
			if (gBag().size() < gBagSize()) {
				gBag().add(item);
				isSatisfied = true;
			}
			if (gBag().size() < gBagSize()) {
				printSlowln("You bought a " + item.gName() + "! >");
				enter();
			} else if (gBagSize() == gBag().size()) {
				if (!bagPrinted) {
					printBag(false, -1);
					bagPrinted = true;
				}
				printSlowln("You bought a " + item.gName() + "! >");
				printSlow("Your bag is full. Would you like to replace an item? (yes/no) --> ");
				if (yes()) {// if yes
					printSlow("Which item would you like to replace? Or, press X to cancel. (number or X) --> ");
					int bagChoice = confirmOpt(0, gBag().size());
					while (bagChoice != 0 && !isSatisfied) {
						if (bagChoice != 0) {
							printSlow("Are you sure you would like to replace your " + gBag().get(bagChoice - 1).gName()
									+ " with the " + item.gName() + "? (yes/no) --> ");
							if (yes()) {
								gBag().set(bagChoice - 1, item);
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
			printSlow("Are you sure you want to leave the " + item.gName() + "? (yes/no) --> ");
			if (yes())
				isSatisfied = true;
		}
	}


}
