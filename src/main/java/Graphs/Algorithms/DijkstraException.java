package Graphs.Algorithms;

public class DijkstraException extends Exception {

    public static final String NO_PATH_MSG = "pas de chemin";


    public DijkstraException(String msg) {
        super(msg);
    }

}
