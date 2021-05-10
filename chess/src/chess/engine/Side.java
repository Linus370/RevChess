package chess.engine;

public enum Side {
    WHITE{
        @Override
        public int getDirection(){
            return -1;
        }
        @Override
        public boolean isBlack(){
            return false;
        }

        public boolean isWhite(){
            return true;
        }
    },
    BLACK{
        @Override
        public int getDirection(){
            return 1;
        }

        @Override
        public boolean isBlack(){
            return true;
        }

        public boolean isWhite(){
            return false;
        }
    };
    public abstract int getDirection();

    public abstract boolean isBlack();
    public abstract boolean isWhite();
}
