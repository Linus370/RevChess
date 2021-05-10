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


public class Knight extends Piece{

    private final int[] offset = {-17, -15, -10, -6, 6, 10, 15, 17};

    public Knight(final int coordinate, final Side side){
        super(coordinate, side);
    }

    public Collection<Move> legalMoves(final Board board){
        final List<Move> listOfMoves = new ArrayList<>();
        for(int possibleMove : offset) {
            int destinationCoordinate = this.coordinate + possibleMove;
            if(BoardUtils.isValidCoordinate(destinationCoordinate)){
                if(firstColumn(this.coordinate, possibleMove)
                        || secondColumn(this.coordinate, possibleMove)
                        || sevenColumn(this.coordinate, possibleMove)
                        || eightColumn(this.coordinate, possibleMove)){
                    continue;
                }
                final Tile destinationCoordinateTile = board.getTile(destinationCoordinate);
                if(!destinationCoordinateTile.isOccupied()){
                    listOfMoves.add(new Move.MajorMove(board, destinationCoordinate, this));
                }
                else {
                    if (destinationCoordinateTile.getPiece().getSide() != this.side) {
                        listOfMoves.add(new Move.AttackMove(board, destinationCoordinate, this, destinationCoordinateTile.getPiece()));
                    }
                }
            }
        }
        return Collections.unmodifiableCollection(listOfMoves);
    }

    public String toString(){
        return PieceType.KNIGHT.toString();
    }

    private static boolean firstColumn(final int piecePosition, final int possibleMove){
        return BoardUtils.FIRST_COLUMN[piecePosition] && (possibleMove == -17 || possibleMove == -10 || possibleMove == 6 || possibleMove == 15);
    }

    private static boolean secondColumn(final int piecePosition, final int possibleMove){
        return BoardUtils.SECOND_COLUMN[piecePosition] && (possibleMove == -17 || possibleMove == -10 || possibleMove == 6);
    }

    private static boolean sevenColumn(final int piecePosition, final int possibleMove) {
        return BoardUtils.SEVEN_COLUMN[piecePosition] && ((possibleMove == 10) || (possibleMove == -6));
    }

    private static boolean eightColumn(final int piecePosition, final int possibleMove) {
        return BoardUtils.EIGHT_COLUMN[piecePosition] && ((possibleMove == 17) || (possibleMove == 10) || (possibleMove == -6) || (possibleMove == -15));
    }

}
