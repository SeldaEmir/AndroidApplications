package com.example.lab08.studentgrade;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class CustomAdapter extends BaseAdapter {

    List<PersonModel>mPersonModelList;
    LayoutInflater layoutInflater;

    public CustomAdapter(Activity activity,List<PersonModel> mPersonModelList) {
        this.mPersonModelList = mPersonModelList;
        layoutInflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mPersonModelList.size();
    }

    @Override
    public Object getItem(int i) {
        return mPersonModelList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=layoutInflater.inflate(R.layout.row_layout,null);

        TextView textViewName=(TextView)view.findViewById(R.id.textViewName);
        TextView textViewStudentNumber=(TextView)view.findViewById(R.id.textViewStudentNumber);
        TextView textViewGrade=(TextView)view.findViewById(R.id.textViewGrade);
        ImageView imageViewGender=(ImageView)view.findViewById(R.id.imageViewGenderLogo);


        textViewName.setText(mPersonModelList.get(i).getName());
        textViewStudentNumber.setText(mPersonModelList.get(i).getstudentNumber ());
        textViewGrade.setText(mPersonModelList.get(i).getGrade());


        if (mPersonModelList.get(i).getGender().equals("female")){
            imageViewGender.setImageResource(R.drawable.kadin);
        }
        else if(mPersonModelList.get(i).getGender().equals("male")){
            imageViewGender.setImageResource(R.drawable.erkek);

        }else
            imageViewGender.setImageResource(R.mipmap.ic_launcher);

        return view;
    }
}