package Problema01;

public class SendMoreMoney {

    private static void generateEightDigitsToChecker(int[] digits, int count) {
        if (count >= 8) {
            checkAndPrint(digits[0], digits[1], digits[2], digits[3], digits[4], digits[5], digits[6], digits[7]);
            return;
        }
        outer:
        for (int digit=0; digit <= 9; digit++) {
            digits[count] = digit;
            for (int i = 0; i < count; i++)
                if (digits[i] == digit) continue outer;
            generateEightDigitsToChecker(digits, count+1);
        }
    }

    private static void checkAndPrint(int s, int e, int n, int d, int m, int o, int r, int y) {
        int send = (s * 1000) + (e * 100) + (n * 10) + (d);
        int more = (m * 1000) + (o * 100) + (r * 10) + (e);
        int money = (m * 10000) + (o * 1000) + (n * 100) + (e * 10) + (y);

        if ((m != 0) && (send + more == money)) {
            System.out.println(" SEND     " + send);
            System.out.println(" MORE     " + more);
            System.out.println("-----    ----- ");
            System.out.println("MONEY    " + money + "\n");
        }
    }

    public static void main(String[] args) {
        generateEightDigitsToChecker(new int[10], 0);
    }
}