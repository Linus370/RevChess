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

public class Bishop extends Piece{
    private final int[] offset = {-9, -7, 9, 7};

    public Bishop(final int coordinate, final Side side){
        super(coordinate, side);
    }

    public Collection<Move> legalMoves(final Board board) {
        final List<Move> listOfMoves = new ArrayList<>();
        for (int possibleMove : offset) {
            int destinationCoordinate = this.coordinate + possibleMove;
            final Tile destinationCoordinateTile = board.getTile(destinationCoordinate);
            if (!destinationCoordinateTile.isOccupied()) {
                listOfMoves.add(new Move.MajorMove(board, destinationCoordinate, this));
            } else {
                if (destinationCoordinateTile.getPiece().getSide() != this.side) {
                    listOfMoves.add(new Move.AttackMove(board, destinationCoordinate, this, destinationCoordinateTile.getPiece()));
                }
            }
        }
        return Collections.unmodifiableCollection(listOfMoves);
    }

    @Override
    public String toString(){
        return PieceType.BISHOP.toString();
    }

}
