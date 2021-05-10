package chess.engine.pieces;

import chess.engine.Side;
import chess.engine.board.Board;
import chess.engine.board.BoardUtils;
import chess.engine.board.Move;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class King extends Piece{

    private final int[] offset = {-9, -8, -7, -1, 1, 7, 8, 9};

    public King(int coordinate, Side side) {
        super(coordinate, side);
    }

    @Override
    public Collection<Move> legalMoves(Board board) {
        final List<Move> listOfLegalMoves = new ArrayList<>();
        for(int possibleMove : offset){
            final int destinationCoordinate = this.coordinate + possibleMove;
            if(BoardUtils.isValidCoordinate(destinationCoordinate)){
                if(firstColumn(this.coordinate, possibleMove) && eightColumn(this.coordinate, possibleMove)){
                    continue;
                }
                if(!board.getTile(destinationCoordinate).isOccupied()){
                    listOfLegalMoves.add(new Move.MajorMove(board, destinationCoordinate, this));
                }
                else{
                    if(board.getTile(destinationCoordinate).getPiece().getSide() != this.getSide()){
                        listOfLegalMoves.add(new Move.AttackMove(board, destinationCoordinate, this, board.getTile(destinationCoordinate).getPiece()));
                    }
                }
            }
        }
        return Collections.unmodifiableCollection(listOfLegalMoves);
    }

    public String toString(){
        return PieceType.KING.toString();
    }

    private boolean firstColumn(final int coordinate, final int possibleMove){
        return BoardUtils.FIRST_COLUMN[coordinate] && ((possibleMove == -9) || (possibleMove == -1) || (possibleMove == 7));
    }

    private boolean eightColumn(final int coordinate, final int possibleMove){
        return BoardUtils.EIGHT_COLUMN[coordinate] && ((possibleMove == -7) || (possibleMove == 1) || (possibleMove == 9));
    }
}
