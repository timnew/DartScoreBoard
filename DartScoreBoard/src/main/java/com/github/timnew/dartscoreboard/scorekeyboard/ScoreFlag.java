package com.github.timnew.dartscoreboard.scorekeyboard;

public enum ScoreFlag {
    NORMAL {
        @Override
        public int applyToBaseScore(int baseScore) {
            return baseScore;
        }
    },
    DOUBLE {
        @Override
        public int applyToBaseScore(int baseScore) {
            return baseScore * 2;
        }
    },
    TRIPLE {
        @Override
        public int applyToBaseScore(int baseScore) {
            return baseScore * 3;
        }
    },
    BUSTED {
        @Override
        public int applyToBaseScore(int baseScore) {
            return 0;
        }
    };

    public abstract int applyToBaseScore(int baseScore);
}
