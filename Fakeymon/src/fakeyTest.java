import java.io.*;
import java.util.*;

public class fakeyTest {

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		for (int b = 0; b < 99; b++) {
			int[] a = { 0, 4, 8, 10 };
			int kills = a[3];
			Scanner scan = new Scanner(new File("src/Data/fakeyAttacks.txt"));
			int move;
			if (kills < 1) {
				for (int i = 0; i < new Random().nextInt(3); i++) {
					scan.nextLine();
				}
				System.out.println(scan.next());
			}
			else if (kills < 5) {
				for (int i = 0; i < new Random().nextInt(3) + 3; i++) {
					scan.nextLine();
				}
				System.out.println(scan.next());
			}
			else if (kills < 10) {
				for (int i = 0; i < new Random().nextInt(3) + 6; i++) {
					scan.nextLine();
				}
				System.out.println(scan.next());
			}
			else {
				for (int i = 0; i < 9; i++) {
					scan.nextLine();
				}
				System.out.println(scan.next());
			}
		}
	}

	public static void test() {
		System.out.print(-4 % 3);
	}

	public static void h(double a, double d) {
		// if (d > (a/d * (a/(2)))/(d/(d/2))) System.out.println(a + " --> " + d
		// + "; damage = " + (a/d * (a/(2))));
		// else System.out.println(a + " --> " + d + "; damage = " + a);

		// System.out.println(a + " --> " + d + "; damage = " + (int)((((a/d +
		// a)))/2)); //AS DEFENSE RISES ABOVE A/2, DAMAGE DOES NOT DECREASE
		// System.out.println(a + " --> " + d + "; damage = " +
		// (int)(Math.ceil(((a*a)/(d*d))*(a/10))));
		// System.out.println(a + " --> " + d + "; damage = " +
		// (int)(Math.ceil(5*(a/d))));
	}
}
