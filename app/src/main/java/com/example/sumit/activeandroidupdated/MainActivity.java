package com.example.sumit.activeandroidupdated;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.query.Select;

import java.util.Iterator;
import java.util.List;

public class MainActivity extends Activity {

    EditText edpersonname,edpersonage;
    EditText edphy,edche,edmat,edbio;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActiveAndroid.initialize(this);

        edpersonname=(EditText)findViewById(R.id.person_name);
        edpersonage=(EditText)findViewById(R.id.person_age);
        edphy=(EditText)findViewById(R.id.score_physics);
        edche=(EditText)findViewById(R.id.score_chemistry);
        edmat=(EditText)findViewById(R.id.score_maths);


    }


    public  void save(View view){
        //let we save the sore values in to the Score Table

    Score score=new Score(Integer.parseInt(edphy.getText().toString()),
                          Integer.parseInt(edche.getText().toString()),
                          Integer.parseInt(edmat.getText().toString()),
                          Integer.parseInt(edphy.getText().toString()));

    score.save();





        String name=edpersonname.getText().toString();
        int age=Integer.parseInt(edpersonage.getText().toString());

        Person p1=new Person(name,age,score);
        p1.save();
        Toast.makeText(getApplicationContext(),"Data saved",Toast.LENGTH_SHORT).show();

   }


    public  void showAll(View view){

        try{

            Select select=new Select();

            List<Person> list=select.all().from(Person.class).execute();

            Iterator iterator=list.iterator();
            /*while (iterator.hasNext()){

                Toast.makeText(getApplicationContext(),"",)
            }*/


            StringBuilder builder = new StringBuilder();
            for (Person person : list) {
                builder.append("Name: ")
                        .append(person.personName)
                        .append(" Age: ")
                        .append(person.personAge)
                        .append("Score")
                        .append(person.PersonScore)
                        .append("\n");


                //score object is appended

            }

            Toast.makeText(this, builder.toString(), Toast.LENGTH_LONG).show();
        }catch (Exception e){
            e.printStackTrace();

        }



    }
}