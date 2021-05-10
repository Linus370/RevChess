package chess.engine.board;

import chess.engine.Side;
import chess.engine.pieces.*;

import java.util.*;

public class Board {
    private List<Tile> gameBoard;
    private Collection<Piece> whitePiece;
    private Collection<Piece> blackPiece;


    private Board(Builder builder){
        this.gameBoard = createGameBoard(builder);
        this.whitePiece = calculateWhite(this.gameBoard, Side.WHITE);
        this.blackPiece = calculateBlack(this.gameBoard, Side.BLACK);

        final Collection<Move> whiteMoves = calculateMoves(this.whitePiece);
        final Collection<Move> blackMoves = calculateMoves(this.blackPiece);
    }

    @Override
    public String toString(){
        final StringBuilder printedBoard = new StringBuilder();
        for(int i = 0; i < 64; i++){
            final String text = this.gameBoard.get(i).toString();
            printedBoard.append(String.format("%3s", text));
            if((i+1) % 8 == 0){
                printedBoard.append("\n");
            }
        }
        return printedBoard.toString();
    }

    private Collection<Move> calculateMoves(Collection<Piece> pieces) {
        final List<Move> legalMoves = new ArrayList<>();
        for(final Piece piece : pieces){
            legalMoves.addAll(piece.legalMoves(this));
        }
        return Collections.unmodifiableCollection(legalMoves);
    }

    private static Collection<Piece> calculateWhite(List<Tile> gameBoard, Side side){
        final List<Piece> white = new ArrayList<>();
        for(Tile tile : gameBoard){
            if(tile.isOccupied() && tile.getPiece().getSide() == side){
                white.add(tile.getPiece());
            }
        }
        return Collections.unmodifiableCollection(white);
    }

    private static Collection<Piece> calculateBlack(List<Tile> gameBoard, Side side){
        final List<Piece> black = new ArrayList<>();
        for(Tile tile : gameBoard){
            if(tile.isOccupied() && tile.getPiece().getSide() == side){
                black.add(tile.getPiece());
            }
        }
        return Collections.unmodifiableCollection(black);
    }

    private static List<Tile> createGameBoard(Builder builder){
        final List<Tile> tiles = new ArrayList<>();
        for(int i = 0; i < 64; i++){
            tiles.add(Tile.createTile(i, builder.board.get(i)));
        }
        return Collections.unmodifiableList(tiles);
    }

    public static Board createInitPosition(){
        final Builder builder = new Builder();
        builder.setPiece(new Rook(0, Side.BLACK));
        builder.setPiece(new Knight(1, Side.BLACK));
        builder.setPiece(new Bishop(2, Side.BLACK));
        builder.setPiece(new Queen(3, Side.BLACK));
        builder.setPiece(new King(4, Side.BLACK));
        builder.setPiece(new Bishop(5, Side.BLACK));
        builder.setPiece(new Knight(6, Side.BLACK));
        builder.setPiece(new Rook(7, Side.BLACK));
        builder.setPiece(new Pawn(8, Side.BLACK));
        builder.setPiece(new Pawn(9, Side.BLACK));
        builder.setPiece(new Pawn(10, Side.BLACK));
        builder.setPiece(new Pawn(11, Side.BLACK));
        builder.setPiece(new Pawn(12, Side.BLACK));
        builder.setPiece(new Pawn(13, Side.BLACK));
        builder.setPiece(new Pawn(14, Side.BLACK));
        builder.setPiece(new Pawn(15, Side.BLACK));
        builder.setPiece(new Pawn(48, Side.WHITE));
        builder.setPiece(new Pawn(49, Side.WHITE));
        builder.setPiece(new Pawn(50, Side.WHITE));
        builder.setPiece(new Pawn(51, Side.WHITE));
        builder.setPiece(new Pawn(52, Side.WHITE));
        builder.setPiece(new Pawn(53, Side.WHITE));
        builder.setPiece(new Pawn(54, Side.WHITE));
        builder.setPiece(new Pawn(55, Side.WHITE));
        builder.setPiece(new Rook(56, Side.WHITE));
        builder.setPiece(new Knight(57, Side.WHITE));
        builder.setPiece(new Bishop(58, Side.WHITE));
        builder.setPiece(new Queen(59, Side.WHITE));
        builder.setPiece(new King(60, Side.WHITE));
        builder.setPiece(new Bishop(61, Side.WHITE));
        builder.setPiece(new Knight(62, Side.WHITE));
        builder.setPiece(new Rook(63, Side.WHITE));
        builder.setMove(Side.WHITE);

        return builder.build();
    }

    public Tile getTile(int coordinate){
        return gameBoard.get(coordinate);
    }

    public static class Builder{
        private Map<Integer, Piece> board;
        private Side nextMove;

        public Builder setPiece(Piece piece){
            this.board.put(piece.getCoordinate(), piece);
            return this;
        }

        public Builder setMove(Side side){
            this.nextMove = side;
            return this;
        }

        public Builder(){
            this.board = new HashMap<>(32, 1.0f);
        }

        public Board build(){
            return new Board(this);
        }
    }

}
