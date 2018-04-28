package com.example.selda.myfoodnotes.adapter;



import com.example.selda.myfoodnotes.R;
import com.example.selda.myfoodnotes.activity.DataBaseHandler;
import com.example.selda.myfoodnotes.model.ProductModel;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ProductAdapter extends BaseAdapter {

    List<ProductModel> productModelList;
    LayoutInflater layoutInflater;
    DataBaseHandler dataBaseHandler;
    Activity activityMy;
    public ProductAdapter(Activity activity, List<ProductModel> productModelList) {
        this.productModelList = productModelList;
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    dataBaseHandler=new DataBaseHandler(activity);
    activityMy=activity;
    }

    @Override
    public int getCount() {
        return productModelList.size();
    }

    @Override
    public Object getItem(int i) {
        return productModelList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        view = layoutInflater.inflate(R.layout.product_row_layout, null);
        ImageView ımageViewProductPic = (ImageView) view.findViewById(R.id.imageViewProductRow);
        final ImageView ımageViewProductLike = (ImageView) view.findViewById(R.id.imageViewProductLikeRow);
        TextView textViewProductRowName = (TextView) view.findViewById(R.id.textViewProductRowName);
        TextView textViewProductRowDate = (TextView) view.findViewById(R.id.textViewProductRowDate);
        TextView textViewProductRowPrice = (TextView) view.findViewById(R.id.textViewProductRowPrice);


        // if (placesModelList.get(i).getPlaces_image_path().equals(""))
        ımageViewProductPic.setImageResource(R.drawable.restaurant);

        if (productModelList.get(i).getProduct_like_dislike().equals("yes"))
            ımageViewProductLike.setImageResource(R.drawable.like);
        else
            ımageViewProductLike.setImageResource(R.drawable.dislike);



        textViewProductRowName.setText(productModelList.get(i).getProduct_name());
        textViewProductRowDate.setText(productModelList.get(i).getProduct_date());
        textViewProductRowPrice.setText(productModelList.get(i).getProduct_price()+" TL");

        ımageViewProductLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(productModelList.get(i).getProduct_like_dislike().equals("yes")){
                    ımageViewProductLike.setImageResource(R.drawable.dislike);
                    productModelList.get(i).setProduct_like_dislike("no");
                    dataBaseHandler.updateProduct(productModelList.get(i),String.valueOf(productModelList.get(i).getProduct_id()));

                }
                else
                {
                    ımageViewProductLike.setImageResource(R.drawable.like);
                    productModelList.get(i).setProduct_like_dislike("yes");
                    dataBaseHandler.updateProduct(productModelList.get(i),String.valueOf(productModelList.get(i).getProduct_id()));

                }


            }
        });

        return view;
    }


}
