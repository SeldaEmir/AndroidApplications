package com.example.selda.neredeneyedim.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.example.selda.neredeneyedim.R;
import com.example.selda.neredeneyedim.model.PlacesModel;

/**
 * Created by Lab08 on 19.04.2018.
 */

public class PlacesAdapter extends BaseAdapter {
    List<PlacesModel> placesModelList;
    LayoutInflater layoutInflater;

    public PlacesAdapter(Activity activity, List<PlacesModel> placesModelList) {
        this.placesModelList = placesModelList;
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return placesModelList.size();
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

        if (placesModelList.get(i).getPlaces_like_dislike().equals("yes"))
            ımageViewPlacesLike.setImageResource(R.drawable.like);
        else
            ımageViewPlacesLike.setImageResource(R.drawable.dislike);


        textView.setText(placesModelList.get(i).getPlaces_name());

        return view;
    }
}
