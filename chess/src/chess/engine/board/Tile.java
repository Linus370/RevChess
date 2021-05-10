package chess.engine.board;

import chess.engine.pieces.Piece;

import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

public abstract class Tile{
   protected int coordinate;

   private static final Map<Integer, Empty> emptyTileMap = createEmpty();

   private static Map<Integer, Empty> createEmpty(){
      Map<Integer, Empty> empty = new HashMap<>();
      for(int i = 0; i < 64; i++){
         empty.put(i, new Empty(i));
      }

      return Collections.unmodifiableMap(empty);
   }

   public static Tile createTile(final int coordinate, final Piece piece){
      return piece != null ? new Occupied(coordinate, piece) : emptyTileMap.get(coordinate);
   }

   private Tile(final int coordinate){
      this.coordinate = coordinate;
   }

   public abstract boolean isOccupied();
   public abstract Piece getPiece();

   public static final class Empty extends Tile{
      Empty(final int coordinate ) {
         super(coordinate);
      }
      @Override
      public boolean isOccupied(){
         return false;
      }
      @Override
      public Piece getPiece(){
         return null;
      }

      @Override
      public String toString(){
         return "-";
      }
   }

   public static final class Occupied extends Tile{
      private Piece piece;

      Occupied(final int coordinate, final Piece piece){
         super(coordinate);
         this.piece = piece;
      }
      @Override
      public boolean isOccupied(){
         return true;
      }
      @Override
      public Piece getPiece(){
         return piece;
      }
      @Override
      public String toString(){
         return this.piece.toString();

      }
   }
}