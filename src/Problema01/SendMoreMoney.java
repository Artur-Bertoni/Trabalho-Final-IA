package Problema01;

public class SendMoreMoney {

    public static void main(String[] args) {
        int count = 0;

        for (int Y = 0; Y < 10; Y++)
            for (int E = 0; E < 10; E++)
                for (int N = 0; N < 10; N++)
                    for (int D = 0; D < 10; D++)
                        for (int S = 0; S < 10; S++)
                            for (int O = 0; O < 10; O++)
                                for (int R = 0; R < 10; R++)
                                    for (int M = 1; M < 10; M++) {
                                        int SEND = (S * 1000) + (E * 100) + (N * 10) + (D);
                                        int MORE = (M * 1000) + (O * 100) + (R * 10) + (E);
                                        int MONEY = (M * 10000) + (O * 1000) + (N * 100) + (E * 10) + (Y);

                                        count++;

                                        if ((S != E && S != N && S != D && S != M && S != O && S != R && S != Y)
                                                && (E != N && E != D && E != M && E != O && E != R && E != Y)
                                                && (N != D && N != M && N != O && N != R && N != Y)
                                                && (D != M && D != O && D != R && D != Y)
                                                && (M != O && M != R && M != Y)
                                                && (O != R && O != Y)
                                                && (SEND + MORE == MONEY))

                                            //86 103 083
                                            //5 679 083
                                            //23 111 173

                                            System.out.println("\n SEND     " + SEND +
                                                    "\n MORE     " + MORE +
                                                    "\n-----    ----- " +
                                                    "\nMONEY    " + MONEY + "\n\n" +
                                                    "Iterações: " + count + "\n");
                                    }
    }
}