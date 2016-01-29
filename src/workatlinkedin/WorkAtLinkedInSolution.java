package workatlinkedin;

public class WorkAtLinkedInSolution {

    public static void main(String... args) {

        Solver solver = new Solver(4, 4);

        solver.setHorizontalHint(0, "[LINKED]*IN");
        solver.setHorizontalHint(1, "(ENG|INE|E|R)*");
        solver.setHorizontalHint(2, "([MBERS]*)\\1");
        solver.setHorizontalHint(3, ".(LN|K|D)*");

        solver.setVerticalHint(0, "R+D");
        solver.setVerticalHint(1, "[^WORK]*ING?");
        solver.setVerticalHint(2, ".*[IN].*");
        solver.setVerticalHint(3, "C{0}N[NECT]*");

        solver.solve();

        for (Checkerboard checkerboard : solver) {
            checkerboard.print(System.out);
        }
    }
}
