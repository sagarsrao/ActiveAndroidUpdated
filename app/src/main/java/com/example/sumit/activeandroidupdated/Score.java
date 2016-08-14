package com.example.sumit.activeandroidupdated;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by sumit on 14-Aug-16.
 */


    @Table(name = "Score")
    public class Score extends Model {
        @Column(name = "scorePhysics")
        public int scorePhysics;
        @Column(name = "scoreChemistry")
        public int scoreChemistry;
        @Column(name = "scoreMaths")
        public int scoreMaths;
        @Column(name = "scoreBiology")
        public int scoreBiology;

        public Score()
        {
            super();
        }

        public Score(int scorePhysics,
                     int scoreChemistry,
                     int scoreMaths,
                     int scoreBiology)
        {
            super();
            this.scorePhysics = scorePhysics;
            this.scoreChemistry = scoreChemistry;
            this.scoreMaths = scoreMaths;
            this.scoreBiology = scoreBiology;
        }

        @Override
        public String toString() {
            // TODO Auto-generated method stub
            return "P: "
                    + scorePhysics
                    + " C: "
                    + scoreChemistry
                    + " M: "
                    + scoreMaths
                    + " B: "
                    + scoreBiology;
        }

}
