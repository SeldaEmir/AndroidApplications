package com.example.selda.myfoodnotes.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.example.selda.myfoodnotes.R;
import com.example.selda.myfoodnotes.model.PlacesModel;


public class PlacesAdapter extends BaseAdapter implements Filterable {
    private List<PlacesModel> myList;
    List<PlacesModel> placesModelList;
    LayoutInflater layoutInflater;


    public PlacesAdapter(Activity activity, List<PlacesModel> placesModelList) {
        this.placesModelList = placesModelList;
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.myList=placesModelList;

    }

    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public Object getItem(int i) {
        return placesModelList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = layoutInflater.inflate(R.layout.places_row_layout, null);
        ImageView ımageViewPlacesPic = (ImageView) view.findViewById(R.id.imageViewPlacesPic);
        ImageView ımageViewPlacesLike = (ImageView) view.findViewById(R.id.imageViewPlacesLike);
        TextView textView = (TextView) view.findViewById(R.id.textViewPlacesRowName);

       // if (placesModelList.get(i).getPlaces_image_path().equals(""))
            ımageViewPlacesPic.setImageResource(R.drawable.restaurant);

        if (myList.get(i).getPlaces_like_dislike().equals("yes"))
            ımageViewPlacesLike.setImageResource(R.drawable.like);
        else
            ımageViewPlacesLike.setImageResource(R.drawable.dislike);


        textView.setText(myList.get(i).getPlaces_name());

        return view;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        myList.clear();
        if (charText.length() == 0) {
            myList.addAll(placesModelList);
        }
        else
        {
            for (PlacesModel wp : placesModelList) {
                if (wp.getPlaces_name().toLowerCase(Locale.getDefault()).contains(charText)) {
                    myList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return null;
    }
}
