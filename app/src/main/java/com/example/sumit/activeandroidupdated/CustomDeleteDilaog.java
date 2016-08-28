package com.example.sumit.activeandroidupdated;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by sumit on 25-Aug-16.
 */
public class CustomDeleteDilaog  extends Dialog {

    Button mshwdata;
    ListView mListRemaining;

    private class Query
    {
        String queryForPerson;
       // String queryForScore;
        ArrayList<String> queryArgumentsForPerson;
       // ArrayList<String> queryArgumentsForScore;

        public Query(String queryForPerson,
                     ArrayList<String> queryArgumentsForPerson) {
            this.queryForPerson = queryForPerson;
         //   this.queryForScore = queryForScore;
            this.queryArgumentsForPerson = queryArgumentsForPerson;
          //  this.queryArgumentsForScore = queryArgumentsForScore;
        }

    }


    public CustomDeleteDilaog(Context context) {
        super(context);
    }

    public CustomDeleteDilaog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected CustomDeleteDilaog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_customdilaog);

      final EditText mPersonemail=(EditText)findViewById(R.id.person_emailid);
        CheckBox mDelete=(CheckBox)findViewById(R.id.checkBox);
       // ListView lv=(ListView)findViewById(R.id.list_data1);
        mshwdata=(Button)findViewById(R.id.btnshowdata);
        mListRemaining=(ListView)findViewById(R.id.list_dataRemaining);


      //  mDelete.setOnClickListener(new View.OnClickListener() {
           // @Override
           // public void onClick(View v) {
        mDelete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    deleteEmail(buildQuery());
                    Toast.makeText(getContext(), "DATA DELETED", Toast.LENGTH_SHORT).show();
                }

                else{
                    Toast.makeText(getContext(),"THERE IS THE ERROR",Toast.LENGTH_SHORT).show();
                }
            }

           // }

            private Query buildQuery() {
                //String and = "and ".intern();
                String personEmail = extractPersonEmail();

                StringBuilder queryForPerson = new StringBuilder();
                ArrayList<String> queryArgumentsForPerson = new ArrayList<String>();

                if (personEmail != null && personEmail.length() > 0) {
                    queryForPerson.append("personEmail=? ");
                           // .append(and);
                    queryArgumentsForPerson.add(personEmail);
                }


                return new Query(queryForPerson.toString(),
                        queryArgumentsForPerson
                       );

            }

            private String extractPersonEmail()
            {
                return mPersonemail
                        .getText()
                        .toString();
            }


            private void deleteEmail(Query query) {
                ArrayList<Person> people = select(query);//people is the list for the Person Entity
                if (people != null && !people.isEmpty()) {
                    ActiveAndroid.beginTransaction();
                    try {
                        for (Person person : people) {
                            person.delete();
                            Toast.makeText(getContext(),"Data deleted",Toast.LENGTH_LONG).show();
                        }
                        ActiveAndroid.setTransactionSuccessful();
                    } finally {
                        ActiveAndroid.endTransaction();
                    }
                }
            }

            @Nullable
            public ArrayList<Person> select(Query query) {

                List<Person> people = new ArrayList<Person>();
                if (query.queryForPerson.matches("@")) {

                    people = new Select()
                            .from(Person.class)
                            .where(query.queryForPerson,
                                    query.queryArgumentsForPerson.toArray())
                            .execute();

                   /* List<Score> scores = new Select()
                            .from(Score.class)
                            .where(query.queryForScore,
                                    query.queryArgumentsForScore.toArray())
                            .execute();*/
                  /*  boolean matchFound = false;
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

                    }*/

                    return (ArrayList<Person>) people;

                } else if (query.queryForPerson.length() > 0
                       ) {

                    people = new ArrayList<Person>();
                    people = new Select()
                            .from(Person.class)
                            .where(query.queryForPerson,
                                    query.queryArgumentsForPerson.toArray())
                            .execute();
                    return (ArrayList<Person>) people;

                } else if (query.queryForPerson.length() == 0
                      ) {
                    people = new Select()
                            .all()
                            .from(Person.class)
                            .execute();
                 /*   List<Score> scores = new Select()
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
 */                  // return (ArrayList<Person>) people;
                }
                //   return null;
                return (ArrayList<Person>) people;
            }


       // });

        });


/*        mshwdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               View v1=null;
                //ShowAllData(v1);

                MainActivity mn=new MainActivity();
                mn.showAll(v1);

            }


        });//end of button*/

    mshwdata.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showRestData(v);
        }

        private void showRestData(View view) {

            try{
                Select select=new Select();
                List<Person> list=select.all().from(Person.class).execute();
            SagarListAdapter    sagarListAdapter=new SagarListAdapter(getContext());
                sagarListAdapter.setmData((ArrayList<Person>) list);
                mListRemaining.setAdapter(sagarListAdapter);//This is the Id of the ListView of the particular XMl where data is to be displayed



            }catch (Exception e){
                e.printStackTrace();

            }

        }


    });

    }




}
