package Problema01;

/**
 * @author Artur Bertoni
 */
public class SendMoreMoney {

    public static void main(String[] args) {
        int count = 0;

        for (int M = 1; M < 10; M++)
            for (int S = 0; S < 10; S++)
                for (int E = 0; E < 10; E++)
                    for (int N = 0; N < 10; N++)
                        for (int D = 0; D < 10; D++)
                            for (int O = 0; O < 10; O++)
                                for (int R = 0; R < 10; R++)
                                    for (int Y = 0; Y < 10; Y++) {
                                        String SEND = String.valueOf(S) + E + N + D;
                                        String MORE = String.valueOf(M) + O + R + E;
                                        String MONEY = String.valueOf(M) + O + N + E + Y;

                                        count++;

                                        if ((S != E && S != N && S != D && S != M && S != O && S != R && S != Y)
                                                && (E != N && E != D && E != M && E != O && E != R && E != Y)
                                                && (N != D && N != M && N != O && N != R && N != Y)
                                                && (D != M && D != O && D != R && D != Y)
                                                && (M != O && M != R && M != Y)
                                                && (O != R && O != Y)
                                                && (Integer.parseInt(SEND) + Integer.parseInt(MORE) == Integer.parseInt(MONEY)))

                                            System.out.println("\n SEND     " + SEND +
                                                    "\n MORE     " + MORE +
                                                    "\n-----    ----- " +
                                                    "\nMONEY    " + MONEY + "\n\n" +
                                                    "Número de Iterações: " + count + "\n");
                                    }
    }
}