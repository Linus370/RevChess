package chess.engine.pieces;

import chess.engine.Side;
import chess.engine.board.Board;
import chess.engine.board.BoardUtils;
import chess.engine.board.Move;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Pawn extends Piece{

    private final int[] offset = {7, 8, 9, 16};

    public Pawn(int coordinate, Side side) {
        super(coordinate, side);
    }

    @Override
    public Collection<Move> legalMoves(Board board) {
        final List<Move> listOfLegalMoves = new ArrayList<>();
        for(int possibleMove : offset){
            final int destinationCoordinate = this.coordinate + (this.getSide().getDirection());
            if(!BoardUtils.isValidCoordinate(destinationCoordinate)){
                continue;
            }
            if(possibleMove == 8 && !board.getTile(destinationCoordinate).isOccupied()){
                listOfLegalMoves.add(new Move.MajorMove(board, destinationCoordinate, this));
            }

            else if(possibleMove == 7 && !((BoardUtils.EIGHT_COLUMN[this.coordinate] && this.side.isWhite() || (BoardUtils.FIRST_COLUMN[this.coordinate] && this.side.isBlack())))) {
                if(board.getTile(destinationCoordinate).isOccupied() && board.getTile(destinationCoordinate).getPiece().getSide() != this.getSide()) {
                    listOfLegalMoves.add(new Move.AttackMove(board, destinationCoordinate, this, board.getTile(destinationCoordinate).getPiece()));
                }
            }
            else if(possibleMove == 9 && !((BoardUtils.EIGHT_COLUMN[this.coordinate] && this.side.isBlack() || (BoardUtils.FIRST_COLUMN[this.coordinate] && this.side.isWhite())))) {
                if(board.getTile(destinationCoordinate).isOccupied() && board.getTile(destinationCoordinate).getPiece().getSide() != this.getSide()) {
                    listOfLegalMoves.add(new Move.AttackMove(board, destinationCoordinate, this, board.getTile(destinationCoordinate).getPiece()));
                }
            }
            else if(possibleMove == 16 && this.isFirstMove()){
                final int behindDestinationCoordinate = this.coordinate + (this.getSide().getDirection()*8);
                if(!board.getTile(behindDestinationCoordinate).isOccupied()){
                    listOfLegalMoves.add(new Move.MajorMove(board, destinationCoordinate, this));
                }
            }

        }
        return Collections.unmodifiableCollection(listOfLegalMoves);
    }

    public String toString(){
        return PieceType.PAWN.toString();
    }
}
