package com.example.sumit.activeandroidupdated;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by sumit on 13-Aug-16.
 */
@Table(name = "Person")
public class Person  extends Model {
    // Notice how we specified the name of our column here
    @Column(name = "personName")
    public String personName;

    // Notice how we specified the name of our column here
    @Column(name = "personAge")
    public int personAge;

    @Column(name="PersonScore")
    public Score PersonScore;







    public Person() {
        // Notice how super() has been called to perform default initialization
        // of our Model subclass
        super();
    }

    public Person(String personName, int personAge, Score personScore) {
        super();
        this.personName = personName;
        this.personAge = personAge;
        this.PersonScore = personScore;
    }
}
