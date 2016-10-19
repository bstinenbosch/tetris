package tetris;

public enum Action {
    ROTATE_RIGHT {
        @Override
        public String toString() {
            return "Rotate right";
        }
    },
    MOVE_LEFT {
        @Override
        public String toString() {
            return "Move left";
        }
    },
    MOVE_RIGHT {
        @Override
        public String toString() {
            return "Move right";
        }
    },
    ROTATE_LEFT {
        @Override
        public String toString() {
            return "Rotate left";
        }
    },
    SOFT_DROP {
        @Override
        public String toString() {
            return "Soft drop";
        }
    },
    HARD_DROP {
        @Override
        public String toString() {
            return "Hard drop";
        }
    },
    INVALID_ACTION;

}
