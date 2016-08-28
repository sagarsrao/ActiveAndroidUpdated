package com.example.sumit.activeandroidupdated;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sumit on 23-Aug-16.
 */
public class TaskDialog extends Dialog {


    private final Context context;
    private EditText mPersonemailid,mPersonage,mPhy,mChe,mBio,mMaths;
    private Button mSave,mCancel;
    private String email;
    private int age;



    public TaskDialog(final Context context) {
        super(context);
        this.context = context;

        setContentView(R.layout.custom_dialog);//Inflate  the xml
//Initilize  the widget components
        mPersonemailid=(EditText)findViewById(R.id.person_emailid);
        mPersonage=(EditText)findViewById(R.id.person_age);
        mPhy=(EditText)findViewById(R.id.score_physics);
        mChe=(EditText)findViewById(R.id.score_chemistry);
        mMaths=(EditText)findViewById(R.id.score_maths);
        mBio=(EditText)findViewById(R.id.score_biology);
        mSave=(Button)findViewById(R.id.save);
        mCancel=(Button)findViewById(R.id.cancel);
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save(view);
            }

            private void save(View view) {
                try {
                    //let we save the sore values in to the Score Table
                    email = mPersonemailid.getText().toString();
                    if (!isValidEmail(email)) {
                        mPersonemailid.setError("Invalid Email");
                    } else if (mPersonage.getText().toString().equals("")) {
                        mPersonage.setError("Enter the Proper Age!!!!!!");

                    } else if (mPhy.getText().toString().equals("")) {
                        mPhy.setError("Physics cannnot be empty!!!!!!");
                    } else if (mChe.getText().toString().equals("")) {
                        mChe.setError("Chemistry cannnot be empty!!!!!!");
                    } else if (mMaths.getText().toString().equals("")) {
                        mMaths.setError("Maths cannnot be empty!!!!!!");
                    } else if (mBio.getText().toString().equals("")) {
                        mBio.setError("Biology cannnot be empty!!!!!!");
                    } else {
                        Score score = new Score(Integer.parseInt(mPhy.getText().toString()),
                                Integer.parseInt(mChe.getText().toString()),
                                Integer.parseInt(mMaths.getText().toString()),
                                Integer.parseInt(mPhy.getText().toString()));

                        score.save();

                        age=Integer.parseInt(mPersonage.getText().toString());
                        Person p1 = new Person(email, age, score);
                        p1.save();
                        Toast.makeText(context, "Data saved", Toast.LENGTH_SHORT).show();


                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            private boolean isValidEmail(String email) {
                String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

                Pattern pattern = Pattern.compile(EMAIL_PATTERN);
                Matcher matcher = pattern.matcher(email);
                return matcher.matches();
            }
        });
        
            
mCancel.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i1=new Intent(context,MainActivity.class);
        context.startActivity(i1);

    }
});



}

    public TaskDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = null;
    }

    protected TaskDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context = null;
    }
}
