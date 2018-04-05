package com.example.selda.customlist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Lab08-ogretmen on 5.04.2018.
 */

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
        TextView textViewAge=(TextView)view.findViewById(R.id.textViewAge);
        TextView textViewHomeTown=(TextView)view.findViewById(R.id.textViewhomeTown);
        TextView textViewGraduated=(TextView)view.findViewById(R.id.textViewGraduated);
        ImageView imageViewGender=(ImageView)view.findViewById(R.id.imageViewGenderLogo);
        ImageView imageViewTeam=(ImageView)view.findViewById(R.id.imageViewTeamLogo);

        textViewName.setText(mPersonModelList.get(i).getName());
        textViewAge.setText(mPersonModelList.get(i).getAge());
        textViewHomeTown.setText(mPersonModelList.get(i).getHomeTown());
        textViewGraduated.setText(mPersonModelList.get(i).getGraduated());

        if (mPersonModelList.get(i).getGender().equals("female")){
            imageViewGender.setImageResource(R.drawable.kadin);
        }
        else
            imageViewGender.setImageResource(R.drawable.erkek);

        if (mPersonModelList.get(i).getFunTeam().equalsIgnoreCase("fenerbahçe"))
            imageViewTeam.setImageResource(R.drawable.fb);
        else if(mPersonModelList.get(i).getFunTeam().equalsIgnoreCase("cincon"))
            imageViewTeam.setImageResource(R.drawable.gs);
        else if(mPersonModelList.get(i).getFunTeam().equalsIgnoreCase("beşiktaş"))
            imageViewTeam.setImageResource(R.drawable.besiktas);
        else if(mPersonModelList.get(i).getFunTeam().equalsIgnoreCase("karşı yaka"))
            imageViewTeam.setImageResource(R.drawable.kyaka);
        else if(mPersonModelList.get(i).getFunTeam().equalsIgnoreCase("göztepe"))
            imageViewTeam.setImageResource(R.drawable.goztepe);
        else
            imageViewTeam.setImageResource(R.mipmap.ic_launcher);

        return view;
    }
}
