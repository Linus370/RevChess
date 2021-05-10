package chess.engine.board;

import chess.engine.pieces.Piece;

public class Move {
    private Board board;
    private int destinationCoordinate;
    private Piece piece;

    private Move(final Board board, final int destinationCoordinate, final Piece piece){
        this.board = board;
        this.destinationCoordinate = destinationCoordinate;
        this.piece = piece;
    }

    public static final class MajorMove extends Move{
        public MajorMove(final Board board, final int destinationCoordinate, final Piece piece){
            super(board, destinationCoordinate, piece);
        }
    }

    public static final class AttackMove extends Move{
        private Piece attackedPiece;
        public AttackMove(final Board board, final int destinationCoordinate, final Piece piece, final Piece attackedPiece){
            super(board, destinationCoordinate, piece);
            this.attackedPiece = attackedPiece;
        }

    }

}
