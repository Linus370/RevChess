package chess.engine.pieces;

import chess.engine.Side;
import chess.engine.board.Board;
import chess.engine.board.Move;

import java.util.Collection;

public abstract class Piece {
    protected int coordinate;
    protected final Side side;
    protected boolean isFirstMove;

    Piece(final int coordinate, final Side side){
        this.coordinate = coordinate;
        this.side = side;
    }

    public Side getSide(){
        return side;
    }

    public int getCoordinate(){
        return this.coordinate;
    }

    public boolean isFirstMove(){
        return isFirstMove;
    }

    public abstract Collection<Move> legalMoves(final Board board);

    public enum PieceType{
        PAWN("P"),
        KNIGHT("K"),
        BISHOP("B"),
        ROOK("R"),
        KING("K"),
        QUEEN("Q");

        private String pieceName;

        PieceType(final String pieceName){
            this.pieceName = pieceName;
        }

        @Override
        public String toString(){
            return this.pieceName;
        }
    }
}
