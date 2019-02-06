import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class fakeyItem {

	private String name, info, type;
	private int value, ID, buy, sell;
	
	public fakeyItem() {
		name = "[Empty]";
		info = "[Empty]";
		type = "[Empty]";
		value = 0;
		buy = 0;
		sell = 0;
		ID = 0;
	}
	
	public fakeyItem(Random rand) throws FileNotFoundException {
		double item = Math.round(rand.nextDouble() * 10000); item /= 100;
		Scanner idScan = new Scanner(new File("src/Data/fakeyItemData.txt"));
		if (item <= 8) setItemValues(1, idScan);
		else if (item <= 13) setItemValues(2, idScan);
		else if (item <= 16.5) setItemValues(3, idScan);
		else if (item <= 18.5) setItemValues(4, idScan);
		else if (item <= 20) setItemValues(5, idScan);
		else if (item <= 21) setItemValues(6, idScan);
		else if (item <= 21.5) setItemValues(7, idScan);
		else if (item <= 41.5) setItemValues(8, idScan);
		else if (item <= 56.5) setItemValues(9, idScan);
		else if (item <= 64.5) setItemValues(10, idScan);
		else if (item <= 67.5) setItemValues(11, idScan);
		else if (item <= 69.5) setItemValues(12, idScan);
		else if (item <= 70.5) setItemValues(13, idScan);
		else if (item <= 71.5) setItemValues(14, idScan);
		else if (item <= 81.5) setItemValues(15, idScan);
		else if (item <= 89) setItemValues(16, idScan);
		else if (item <= 94) setItemValues(17, idScan);
		else if (item <= 99) setItemValues(18, idScan);
		else setItemValues(19, idScan);
		fixValues();
	}
	
	public fakeyItem(int ID) throws FileNotFoundException {
		this();
		if (ID > 0) {
			Scanner idScan = new Scanner(new File("src/Data/fakeyItemData.txt"));
			for (int i = 1; i < ID; i++) {
				idScan.nextLine();
			}
			name = idScan.next();
			type = idScan.next();
			value = idScan.nextInt();
			buy = idScan.nextInt();
			sell = idScan.nextInt();
			info = idScan.nextLine();
			this.ID = ID;
			fixValues();
		}
	}	
	
	public void setItemValues(int ID, Scanner idScan) {
		for (int i = 1; i < ID; i++) {
			idScan.nextLine();
		}
		name = idScan.next();
		type = idScan.next();
		value = idScan.nextInt();
		buy = idScan.nextInt();
		sell = idScan.nextInt();
		info = idScan.nextLine();
		this.ID = ID;
		
	}
	
	public void fixValues() {
		String nameWithSpace = "";
		for (int i = 0; i < name.length(); i++) {
			if (name.charAt(i) != '-') nameWithSpace += name.charAt(i);
			else nameWithSpace += ' ';
		}
		name = nameWithSpace;
		info = info.substring(1, info.length());
	}
	
	public String gType() {
		return type;
	}
	
	public String gName() {
		return name;
	}
	
	public String gInfo() {
		return info;
	}
	public int gID() {
		return ID;
	}
	public int gValue() {
		return value;
	}
	public int gBuy() {
		return buy;
	}
	public int gSell() {
		return sell;
	}
	
	public String toString() {
		return name + " | " + info;
	}
}