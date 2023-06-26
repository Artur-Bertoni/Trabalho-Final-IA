package Problema01;

public class SendMoreMoney {

    public static void main(String[] args) {
        int S, E, N, D, M, O, R, Y, SEND, MORE, MONEY;

        for (S = 1; S < 10; S++) {
            for (E = 0; E < 10; E++) {
                for (N = 0; N < 10; N++) {
                    for (D = 0; D < 10; D++) {
                        for (M = 1; M < 10; M++) {
                            for (O = 0; O < 10; O++) {
                                for (R = 0; R < 10; R++) {
                                    for (Y = 0; Y < 10; Y++) {
                                        SEND = (S * 1000) + (E * 100) + (N * 10) + (D);
                                        MORE = (M * 1000) + (O * 100) + (R * 10) + (E);
                                        MONEY = (M * 10000) + (O * 1000) + (N * 100) + (E * 10) + (Y);

                                        if ((S != E && S != N && S != D && S != M && S != O && S != R && S != Y)
                                                && (E != N && E != D && E != M && E != O && E != R && E != Y)
                                                && (N != D && N != M && N != O && N != R && N != Y)
                                                && (D != M && D != O && D != R && D != Y)
                                                && (M != O && M != R && M != Y)
                                                && (O != R && O != Y)
                                                && (SEND + MORE == MONEY)) {
                                            System.out.println("\n SEND     " + S + E + N + D +
                                                    "\n MORE     " + M + O + R + E +
                                                    "\n-----    ----- " +
                                                    "\nMONEY    " + M + O + N + E + Y + "\n");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}