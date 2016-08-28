package com.example.sumit.activeandroidupdated;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends ActionBarActivity implements  View.OnClickListener {



    EditText edpersonemail,edpersonage;
    EditText edphy,edche,edmat,edbio;
    ListView mListdata;


    private SagarListAdapter sagarListAdapter;

    String email;
    int age;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActiveAndroid.initialize(this);

        getSupportActionBar();
        edpersonemail=(EditText)findViewById(R.id.person_emailid);
        edpersonage=(EditText)findViewById(R.id.person_age);
        edphy=(EditText)findViewById(R.id.score_physics);
        edche=(EditText)findViewById(R.id.score_chemistry);
        edmat=(EditText)findViewById(R.id.score_maths);
        edbio=(EditText)findViewById(R.id.score_biology);

        mListdata=(ListView)findViewById(R.id.list_all);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        switch (id) {
            case R.id.add:

                AddTask();
                Toast.makeText(getApplicationContext(),"You selected add icon",Toast.LENGTH_SHORT).show();

                return true;

            case R.id.delete:

              //  CompletedList();
                //View view=null;
                //selectUpdateDelete(view);
                DeleteTask();
                Toast.makeText(getApplicationContext(),"You selected delete icon",Toast.LENGTH_SHORT).show();




            case R.id.showall:
                View view1 = null;
                showAllData(view1);
                Toast.makeText(getApplicationContext(),"You Seleted show all icon",Toast.LENGTH_SHORT).show();


                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void DeleteTask() {

        CustomDeleteDilaog c1=new CustomDeleteDilaog(MainActivity.this);
        c1.show();

    }

    private void showAllData(View view1) {
        try{
            Select select=new Select();
            List<Person> list=select.all().from(Person.class).execute();
            sagarListAdapter=new SagarListAdapter(this);
            sagarListAdapter.setmData((ArrayList<Person>) list);
            mListdata.setAdapter(sagarListAdapter);



        }catch (Exception e){
            e.printStackTrace();

        }

    }

    private void AddTask() {

        TaskDialog td=new TaskDialog(MainActivity.this);
        td.show();

    }


    public  void save(View view) {
        try {
            //let we save the sore values in to the Score Table
            email = edpersonemail.getText().toString();
            if (!isValidEmail(email)) {
                edpersonemail.setError("Invalid Email");
            } else if (edpersonage.getText().toString().equals("")) {
                edpersonage.setError("Enter the Proper Age!!!!!!");

            } else if (edphy.getText().toString().equals("")) {
                edphy.setError("Physics cannnot be empty!!!!!!");
            } else if (edche.getText().toString().equals("")) {
                edche.setError("Chemistry cannnot be empty!!!!!!");
            } else if (edmat.getText().toString().equals("")) {
                edmat.setError("Maths cannnot be empty!!!!!!");
            } else if (edbio.getText().toString().equals("")) {
                edbio.setError("Biology cannnot be empty!!!!!!");
            } else {
                Score score = new Score(Integer.parseInt(edphy.getText().toString()),
                        Integer.parseInt(edche.getText().toString()),
                        Integer.parseInt(edmat.getText().toString()),
                        Integer.parseInt(edphy.getText().toString()));

                score.save();

                age=Integer.parseInt(edpersonage.getText().toString());
                Person p1 = new Person(email, age, score);
                p1.save();
                Toast.makeText(getApplicationContext(), "Data saved", Toast.LENGTH_SHORT).show();


            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public  void showAll(View view){

        try{
            Select select=new Select();
            List<Person> list=select.all().from(Person.class).execute();
            sagarListAdapter=new SagarListAdapter(this);
            sagarListAdapter.setmData((ArrayList<Person>) list);
            mListdata.setAdapter(sagarListAdapter);



        }catch (Exception e){
            e.printStackTrace();

        }
     }


    public void selectUpdateDelete(View view) {
        Intent intent = new Intent(this, SelectDeleteActivity.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View view) {
        final String email = edpersonemail.getText().toString();
        if (!isValidEmail(email)) {
            edpersonemail.setError("Invalid Email");
        }
    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
