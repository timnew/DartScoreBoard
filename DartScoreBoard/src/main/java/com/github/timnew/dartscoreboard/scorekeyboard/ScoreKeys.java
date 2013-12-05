package com.github.timnew.dartscoreboard.scorekeyboard;

import com.github.timnew.dartscoreboard.scorekeyboard.segments.InputSegment;

public enum ScoreKeys {
    D1 {
        @Override
        public SegmentKeyResult applyTo(InputSegment segment) {
            return segment.onDigit(1);
        }
    },
    D2 {
        @Override
        public SegmentKeyResult applyTo(InputSegment segment) {
            return segment.onDigit(2);
        }
    },
    D3 {
        @Override
        public SegmentKeyResult applyTo(InputSegment segment) {
            return segment.onDigit(3);
        }
    },
    D4 {
        @Override
        public SegmentKeyResult applyTo(InputSegment segment) {
            return segment.onDigit(4);
        }
    },
    D5 {
        @Override
        public SegmentKeyResult applyTo(InputSegment segment) {
            return segment.onDigit(5);
        }
    },
    D6 {
        @Override
        public SegmentKeyResult applyTo(InputSegment segment) {
            return segment.onDigit(6);
        }
    },
    D7 {
        @Override
        public SegmentKeyResult applyTo(InputSegment segment) {
            return segment.onDigit(7);
        }
    },
    D8 {
        @Override
        public SegmentKeyResult applyTo(InputSegment segment) {
            return segment.onDigit(8);
        }
    },
    D9 {
        @Override
        public SegmentKeyResult applyTo(InputSegment segment) {
            return segment.onDigit(9);
        }
    },
    D0 {
        @Override
        public SegmentKeyResult applyTo(InputSegment segment) {
            return segment.onDigit(0);
        }
    },
    DOUBLE {
        @Override
        public SegmentKeyResult applyTo(InputSegment segment) {
            return segment.onTimes(ScoreFlag.DOUBLE);
        }
    },
    TRIPLE {
        @Override
        public SegmentKeyResult applyTo(InputSegment segment) {
            return segment.onTimes(ScoreFlag.TRIPLE);
        }
    },
    PLUS {
        @Override
        public SegmentKeyResult applyTo(InputSegment segment) {
            return segment.onPlus();
        }
    },
    BUST {
        @Override
        public SegmentKeyResult applyTo(InputSegment segment) {
            return segment.onBust();
        }
    },
    BACKSPACE {
        @Override
        public SegmentKeyResult applyTo(InputSegment segment) {
            return segment.onBackspace();
        }
    },
    ENTER {
        @Override
        public SegmentKeyResult applyTo(InputSegment segment) {
            return segment.onEnter();
        }
    };

    public abstract SegmentKeyResult applyTo(InputSegment segment);
}
