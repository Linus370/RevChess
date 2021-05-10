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

public class Rook extends Piece{

    private final int[] offset = {-8, -1, 1, 8,};

    public Rook(final int coordinate, final Side side){
        super(coordinate, side);
    }

    @Override
    public Collection<Move> legalMoves(Board board) {
        final List<Move> listOfLegalMoves = new ArrayList<>();
        int destinationCoordinate = this.coordinate;
        for(int possibleMove : offset){
            destinationCoordinate += possibleMove;
            while(BoardUtils.isValidCoordinate(destinationCoordinate)){
                if(firstColumn(this.coordinate, possibleMove) || eightColumn(this.coordinate, possibleMove)){
                    continue;
                }
                final Tile destinationCoordinateTile = board.getTile(destinationCoordinate);
                if(!destinationCoordinateTile.isOccupied()){
                    listOfLegalMoves.add(new Move.MajorMove(board, destinationCoordinate, this));
                }
                else{
                    if(destinationCoordinateTile.getPiece().getSide() != this.side){
                        listOfLegalMoves.add(new Move.AttackMove(board, destinationCoordinate, this, destinationCoordinateTile.getPiece()));
                    }
                }
            }
        }
        return Collections.unmodifiableCollection(listOfLegalMoves);
    }

    public String toString(){
        return PieceType.ROOK.toString();
    }

    private static boolean firstColumn(final int coordinate, final int possibleMove){
        return BoardUtils.FIRST_COLUMN[coordinate] && possibleMove == -1;
    }

    private static boolean eightColumn(final int coordinate, final int possibleMove){
        return BoardUtils.EIGHT_COLUMN[coordinate] && possibleMove == 1;
    }
}
