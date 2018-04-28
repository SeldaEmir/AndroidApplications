package com.example.selda.myfoodnotes.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.example.selda.myfoodnotes.R;
import com.example.selda.myfoodnotes.adapter.PlacesAdapter;
import com.example.selda.myfoodnotes.adapter.ProductAdapter;
import com.example.selda.myfoodnotes.model.PlacesModel;
import com.example.selda.myfoodnotes.model.ProductCommentModel;
import com.example.selda.myfoodnotes.model.ProductModel;

public class ProductActivity extends AppCompatActivity {

    DataBaseHandler db=new DataBaseHandler(this);
    ListView listView;
    List<ProductModel> productModelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView=(ListView)findViewById(R.id.listViewProducts);
        final String id=getIntent().getExtras().getString("id");
        productModelList=db.getAllProductsByPlaceId(id);
        ProductAdapter productAdapter=new ProductAdapter(ProductActivity.this,productModelList);
        listView.setAdapter(productAdapter);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                addProduct(Integer.parseInt(id));
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ProductActivity.this, "Clicked on the list", Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int pos, final long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProductActivity.this);
                builder.setTitle("Updates");
                CharSequence charSequence[] = {"Add Comment","View Comments","Add New Food/Drink", "Update Food/Drink", "Delete Food/Drink"};
                builder.setItems(charSequence, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0) {
                            addComment(productModelList.get(pos).getProduct_id());

                        } else if (i == 1) {
                            //Toast.makeText(ProductActivity.this, "abc", Toast.LENGTH_SHORT).show();
                            Intent ıntent = new Intent(ProductActivity.this,
                                    CommentActivity.class);
                            ıntent.putExtra("id", String.valueOf(productModelList.get(pos).getProduct_id()));
                            startActivity(ıntent);



                        } else if (i == 2) {
                            addProduct(Integer.parseInt( id));


                        } else if (i == 3) {
                            showUpdateDiolog(String.valueOf(productModelList.get(pos).getProduct_id()));


                        } else if (i==4 )
                        {
                            db.deleteProduct(String.valueOf(productModelList.get(pos).getProduct_id())) ;
                            showProductsList(String.valueOf(id));
                        }
                        else
                            Toast.makeText(ProductActivity.this, "Yakında Açılacaktır", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.create().show();
                return true;
            }
        });
    }

    private void addProduct(final int placeId) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(ProductActivity.this);
        builder.setTitle("Ne Yedin Ne İçtin");
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.add_product_diolog, null);
        builder.setView(view);
        final EditText editTextProName=(EditText)view.findViewById(R.id.editTextAddProdcutName);
        final EditText editTextProPrice=(EditText)view.findViewById(R.id.editTextAddProductPrice);
        final CheckBox checkBox=(CheckBox)view.findViewById(R.id.checkBoxAddProductLike);
        Button buttonSave=(Button)view.findViewById(R.id.buttonSaveProduct);
        Button buttonAddImage=(Button)view.findViewById(R.id.buttonAddProductImage);

        final AlertDialog alertDialog  = builder.create();
        alertDialog.show();
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sCheck;
                if (checkBox.isChecked() == true)
                    sCheck = "yes";
                else
                    sCheck = "no";

                db.addProducts(new ProductModel(placeId,getDate(),
                        "",editTextProName.getText().toString(),
                        editTextProPrice.getText().toString(),sCheck));
                showProductsList(String.valueOf( placeId));
                alertDialog.dismiss();

            }
        });



    }


    private void addComment(final int productId) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(ProductActivity.this);
        builder.setTitle("Comments of your food");
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.add_comment_dialog, null);
        builder.setView(view);

        final EditText contentView = view.findViewById(R.id.contentTextView);
        final RatingBar degreeView = view.findViewById(R.id.degreeRatingBar);
        final Button addCommentButton = view.findViewById(R.id.btnAddComment);

        degreeView.setMax(5);
        final AlertDialog addCommentDialog = builder.create();
        addCommentDialog.show();

        addCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductCommentModel commentModel = new ProductCommentModel();

                commentModel.setProduct_id(productId);
                commentModel.setComment_content(contentView.getText().toString());
                commentModel.setComment_date(getDate());
                commentModel.setComment_degree(String.valueOf(degreeView.getRating()));

                db.addComment(commentModel);


                addCommentDialog.dismiss();
            }
        });
    }


    public String getDate(){
        String date = null;

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        date=dateFormat.format(new java.util.Date());

        return date;
    }

    private void showProductsList(String id) {
        productModelList=new ArrayList<>();
        productModelList=db.getAllProducts();
        ProductAdapter productAdapter = new ProductAdapter(ProductActivity.this,db.getAllProductsByPlaceId(id) );
        listView.setAdapter(productAdapter);
        productAdapter.notifyDataSetChanged();
        //Toast.makeText(this, db.getAllPlaces().get(db.getCountPlaces()-1).getPlaces_like_dislike(), Toast.LENGTH_SHORT).show();

    }

    private void showUpdateDiolog(final String id) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Food/Drink Update Page");
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.update_product_diolog, null);
        final EditText editTextProductName = (EditText) view.findViewById(R.id.editTextUpdateProdcutName);
        final EditText editTextProductPrice=(EditText)view.findViewById(R.id.editTextUpdateProductPrice);
        final CheckBox checkBoxProduct=(CheckBox)view.findViewById(R.id.checkBoxUpdateProductLike);
        Button buttonUpdateProduct=(Button)view.findViewById(R.id.buttonUpdateProduct);
        Button buttonUpdateProductImage=(Button)view.findViewById(R.id.buttonUpdateProductImage);
        builder.setView(view);


        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        editTextProductName.setText(db.getProductById(id).getProduct_name()  );
        editTextProductPrice.setText(db.getProductById(id).getProduct_price());


        if(db.getProductById(id).getProduct_like_dislike().equals("yes"))
            checkBoxProduct.setChecked(true);
        else
            checkBoxProduct.setChecked(false);

        buttonUpdateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sCheck;
                if (checkBoxProduct.isChecked() == true)
                    sCheck = "yes";
                else
                    sCheck = "no";

                //(int product_id, int places_id, String product_date, String product_image_path, String product_name, String product_price, String product_like_dislike)
                db.updateProduct(new ProductModel(Integer.parseInt( id),db.getProductById(id).getPlaces_id(),getDate(),""
                        ,editTextProductName.getText().toString(),editTextProductPrice.getText().toString(),sCheck),id) ;
                //.updatePlaces(new PlacesModel("", editTextName.getText().toString(), "", "", sCheck),id);
                showProductsList(String.valueOf(db.getProductById(id).getPlaces_id()));
                Toast.makeText(ProductActivity.this , "Food/Drink Updated", Toast.LENGTH_SHORT).show();

                alertDialog.dismiss();

            }
        });


    }

}
