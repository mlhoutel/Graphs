package Graphs.Algorithms;

public class BellmanException extends Exception {

    public static final String NEGATIVE_CYCLE_MSG = "Cycle négatif";
    public static final String NO_PATH_MSG = "pas de chemin";


    public BellmanException(String msg) {
        super(msg);
    }

}
