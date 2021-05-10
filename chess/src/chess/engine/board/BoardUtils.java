package chess.engine.board;

public class BoardUtils {

    public static final boolean FIRST_COLUMN[] = init(0);
    public static final boolean SECOND_COLUMN[] = init(1);
    public static final boolean SEVEN_COLUMN[] = init(6);
    public static final boolean EIGHT_COLUMN[] = init(7);

    private BoardUtils(){
        throw new RuntimeException("error");
    }

    public static boolean isValidCoordinate(final int coordinate){
        return coordinate >= 0 && coordinate < 64;
    }

    private static boolean[] init(int row){
        boolean[] column = new boolean[64];
        do{
            column[row] = true;
            row += 8;
        }while(row < 64);
        return column;
    }
}
