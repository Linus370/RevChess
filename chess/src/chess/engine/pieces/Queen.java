package chess.engine.pieces;

import chess.engine.Side;
import chess.engine.board.Board;
import chess.engine.board.BoardUtils;
import chess.engine.board.Move;
import chess.engine.board.Tile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Queen extends Piece{
    private final int[] offset = {-9, -8, -7, -1 , 1 , 7, 8, 9};

    public Queen(int coordinate, Side side) {
        super(coordinate, side);
    }

    @Override
    public Collection<Move> legalMoves(Board board) {
        final List<Move> listOfLegalMoves = new ArrayList<>();
        int destinationCoordinate = this.coordinate;
        for (int possibleMove : offset) {
            destinationCoordinate += possibleMove;
            while (BoardUtils.isValidCoordinate(destinationCoordinate)) {
                //if exclusion ? continue
                if (firstColumn(this.coordinate, possibleMove) || eightColumn(this.coordinate, possibleMove)) {
                    continue;
                }
                final Tile destinationCoordinateTile = board.getTile(destinationCoordinate);
                if (!destinationCoordinateTile.isOccupied()) {
                    listOfLegalMoves.add(new Move.MajorMove(board, destinationCoordinate, this));
                } else {
                    if (destinationCoordinateTile.getPiece().getSide() != this.side) {
                        listOfLegalMoves.add(new Move.AttackMove(board, destinationCoordinate, this, destinationCoordinateTile.getPiece()));
                    }
                }
            }
        }
        return Collections.unmodifiableCollection(listOfLegalMoves);
    }

    public String toString(){
        return PieceType.QUEEN.toString();
    }

    private static boolean firstColumn(final int piecePosition, final int offset){
        return BoardUtils.FIRST_COLUMN[piecePosition] && offset == -9 || offset == -1;
    }

    private static boolean eightColumn(final int piecePosition, final int offset){
        return BoardUtils.EIGHT_COLUMN[piecePosition] && offset == 9 || offset == 1;
    }
}
