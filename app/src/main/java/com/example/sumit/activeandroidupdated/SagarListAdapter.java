package com.example.sumit.activeandroidupdated;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sumit on 14-Aug-16.
 */
public class SagarListAdapter extends BaseAdapter {




    private ArrayList<Person> mData;
    private LayoutInflater mLayoutInflater;

    public SagarListAdapter(Context context) {
        mLayoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public ArrayList<Person> getmData() {
        return mData;
    }

    public void setmData(ArrayList<Person> mData) {
        this.mData = mData;
        if (mData != null && !mData.isEmpty()) {
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        if (mData != null && !mData.isEmpty()) {
            return mData.size();
        }
        return 0;
    }

    @Override
    public Person getItem(int position) {
        if (mData != null && !mData.isEmpty()) {
            return mData.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row=convertView;
        ViewHolder holder=null;
        if (row == null)
        {
            row = mLayoutInflater
                    .inflate(R.layout.custom_list_item, parent, false);
            holder = new ViewHolder(row);
            row.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) row.getTag();
        }
        if (mData != null && !mData.isEmpty()) {
            Person currentPerson = mData.get(position);
            initValues(holder, currentPerson);
        }

        return row;


    }

    private void initValues(ViewHolder holder, Person currentPerson) {
        holder.personName
                .setText(currentPerson.personName);

        holder.personAge
                .setText(currentPerson.personAge + "");
        holder.scorePhysics
                .setText(currentPerson.PersonScore.scorePhysics + "");
        holder.scoreChemistry
                .setText(currentPerson.PersonScore.scoreChemistry + "");
        holder.scoreMaths
                .setText(currentPerson.PersonScore.scoreMaths + "");
        holder.scoreBiology
                .setText(currentPerson.PersonScore.scoreBiology + "");
    }



    class ViewHolder {
        TextView personName;
        TextView personAge;
        TextView scorePhysics;
        TextView scoreChemistry;
        TextView scoreMaths;
        TextView scoreBiology;

        public ViewHolder(View view) {
            // TODO Auto-generated constructor stub
            personName = (TextView) view
                    .findViewById(R.id.text_name_value);

            personAge = (TextView) view
                    .findViewById(R.id.text_age_value);

            scorePhysics = (TextView) view
                    .findViewById(R.id.text_physics_value);

            scoreChemistry = (TextView) view
                    .findViewById(R.id.text_chemistry_value);

            scoreMaths = (TextView) view
                    .findViewById(R.id.text_maths_value);

            scoreBiology = (TextView) view
                    .findViewById(R.id.text_biology_value);
        }
    }
}
