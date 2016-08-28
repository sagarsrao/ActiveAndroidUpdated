
package com.example.sumit.activeandroidupdated;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import com.example.sumit.activeandroidupdated.Person;


import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class SelectDeleteActivity extends Activity
        implements OnClickListener {

    private ListView mListData;
    private SagarListAdapter mAdapter;

    private EditText mEditTextPersonEmail;
    private EditText mEditTextPersonAge;

    private EditText mScorePhysics;
    private EditText mScoreChemistry;
    private EditText mScoreMaths;
    private EditText mScoreBiology;

    private Button mSelect;
    private Button mDelete;
    private  Button mBacktoHome;

    private class Query
    {
        String queryForPerson;
        String queryForScore;
        ArrayList<String> queryArgumentsForPerson;
        ArrayList<String> queryArgumentsForScore;

        public Query(String queryForPerson,
                String queryForScore,
                ArrayList<String> queryArgumentsForPerson,
                ArrayList<String> queryArgumentsForScore) {
            this.queryForPerson = queryForPerson;
            this.queryForScore = queryForScore;
            this.queryArgumentsForPerson = queryArgumentsForPerson;
            this.queryArgumentsForScore = queryArgumentsForScore;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_delete);
        mListData = (ListView) findViewById(R.id.list_data1);
        mAdapter = new SagarListAdapter(this);
        mListData.setAdapter(mAdapter);

        mEditTextPersonEmail = (EditText) findViewById(R.id.person_email);
        mEditTextPersonAge = (EditText) findViewById(R.id.person_age);

        mScorePhysics = (EditText) findViewById(R.id.score_physics1);
        mScoreChemistry = (EditText) findViewById(R.id.score_chemistry1);
        mScoreMaths = (EditText) findViewById(R.id.score_maths1);
        mScoreBiology = (EditText) findViewById(R.id.score_biology1);

        mSelect = (Button) findViewById(R.id.select);
        mDelete = (Button) findViewById(R.id.delete);
        mBacktoHome=(Button)findViewById(R.id.bthome);

        mSelect.setOnClickListener(this);
        mDelete.setOnClickListener(this);
        mBacktoHome.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.select:
                ArrayList<Person> people = select(buildQuery());
                if (people != null) {
                    mAdapter.setmData(people);
                }
                break;
            case R.id.delete:
                delete(buildQuery());
                break;

            case R.id.bthome:
                backtoHome();
                break;
        }

    }

    private void backtoHome() {
        Intent i1=new Intent(SelectDeleteActivity.this,MainActivity.class);
        startActivity(i1);

    }

    private String extractPersonEmail()
    {
        return mEditTextPersonEmail
                .getText()
                .toString();
    }

    private int extractPersonAge() {
        int personAge = 0;
        try {
            personAge = Integer.parseInt(
                    mEditTextPersonAge
                            .getText()
                            .toString());
        } catch (NumberFormatException e) {
            personAge = 0;
        }
        return personAge;
    }

    private int extractScoreFrom(EditText editText) {
        int score = -1;
        try {
            score = Integer.parseInt(
                    editText
                            .getText()
                            .toString());
        } catch (NumberFormatException e) {
            score = -1;
        }
        return score;
    }

    public Query buildQuery() {
        String and = "and ".intern();
        String personEmail = extractPersonEmail();
        int personAge = extractPersonAge();
        int scorePhysics = extractScoreFrom(mScorePhysics);
        int scoreChemistry = extractScoreFrom(mScoreChemistry);
        int scoreMaths = extractScoreFrom(mScoreMaths);
        int scoreBiology = extractScoreFrom(mScoreBiology);

        StringBuilder queryForPerson = new StringBuilder();
        ArrayList<String> queryArgumentsForPerson = new ArrayList<String>();
        StringBuilder queryForScore = new StringBuilder();
        ArrayList<String> queryArgumentsForScore = new ArrayList<String>();

        if (personEmail != null && personEmail.length() > 0) {
            queryForPerson.append("personEmail=? ")
                    .append(and);
            queryArgumentsForPerson.add(personEmail);
        }
        if (personAge > 0) {
            queryForPerson.append("personAge=? ")
                    .append(and);
            queryArgumentsForPerson.add(personAge + "");
        }
        if (scorePhysics >= 0) {
            queryForScore.append("scorePhysics=? ")
                    .append(and);
            queryArgumentsForScore.add(scorePhysics + "");
        }
        if (scoreChemistry >= 0) {
            queryForScore.append("scoreChemistry=? ")
                    .append(and);
            queryArgumentsForScore.add(scoreChemistry + "");
        }
        if (scoreMaths >= 0) {
            queryForScore.append("scoreMaths=? ")
                    .append(and);
            queryArgumentsForScore.add(scoreMaths + "");
        }
        if (scoreBiology >= 0) {
            queryForScore.append("scoreBiology=? ")
                    .append(and);
            queryArgumentsForScore.add(scoreBiology + "");
        }

        if (queryForPerson.length() > 0) {
            queryForPerson.delete(
                    queryForPerson.lastIndexOf(and),
                    queryForPerson.length());
        }
        if (queryForScore.length() > 0) {
            queryForScore.delete(
                    queryForScore.lastIndexOf(and),
                    queryForScore.length());
        }

        return new Query(
                queryForPerson.toString(),
                queryForScore.toString(),
                queryArgumentsForPerson,
                queryArgumentsForScore);
    }

    public ArrayList<Person> select(Query query) {

        if (query.queryForPerson.length() > 0
                && query.queryForScore.length() > 0) {

            List<Person> people = new Select()
                    .from(Person.class)
                    .where(query.queryForPerson,
                            query.queryArgumentsForPerson.toArray())
                    .execute();

            List<Score> scores = new Select()
                    .from(Score.class)
                    .where(query.queryForScore,
                            query.queryArgumentsForScore.toArray())
                    .execute();
            boolean matchFound = false;
            for (Iterator<Person> outsideIterator = people.iterator(); outsideIterator.hasNext();)
            {
                Person person = outsideIterator.next();
                matchFound = false;
                for (Score score : scores) {
                    if (person.PersonScore.getId() == score.getId()) {
                        matchFound = true;
                    }
                }
                if (!matchFound) {
                    outsideIterator.remove();
                }

            }

            return (ArrayList<Person>) people;

        }
        else if (query.queryForPerson.length() > 0
                && query.queryForScore.length() == 0) {

            List<Person> people = new ArrayList<Person>();
            people = new Select()
                    .from(Person.class)
                    .where(query.queryForPerson,
                            query.queryArgumentsForPerson.toArray())
                    .execute();
            return (ArrayList<Person>) people;

        }
        else if (query.queryForPerson.length() == 0
                && query.queryForScore.length() > 0) {
            List<Person> people = new Select()
                    .all()
                    .from(Person.class)
                    .execute();
            List<Score> scores = new Select()
                    .from(Score.class)
                    .where(query.queryForScore,
                            query.queryArgumentsForScore.toArray())
                    .execute();
            boolean matchFound = false;
            for (Iterator<Person> outsideIterator = people.iterator(); outsideIterator.hasNext();) {

                Person person = outsideIterator.next();
                matchFound = false;
                for (Score score : scores) {
                    if (person.PersonScore.getId() == score.getId()) {
                        matchFound = true;
                    }
                }
                if (!matchFound) {
                    outsideIterator.remove();
                }
            }
            return (ArrayList<Person>) people;
        }
        return null;
    }

    public void delete(Query query) {
        ArrayList<Person> people = select(query);
        if (people != null && !people.isEmpty()) {
            ActiveAndroid.beginTransaction();
            try {
                for (Person person : people) {
                    person.delete();
                    Toast.makeText(getApplicationContext(),"Data deleted",Toast.LENGTH_LONG).show();
                }
                ActiveAndroid.setTransactionSuccessful();
            } finally {
                ActiveAndroid.endTransaction();
            }
        }
    }

}
