package com.example.selda.neredeneyedim.activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



import java.util.ArrayList;
import java.util.List;

import com.example.selda.neredeneyedim.model.PlacesModel;
import com.example.selda.neredeneyedim.model.ProductCommentModel;
import com.example.selda.neredeneyedim.model.ProductModel;

/**
 * Created by Lab08-ogretmen on 12.04.2018.
 */

public class DataBaseHandler extends SQLiteOpenHelper {

    private static final int dataBaseVersion=1;
    private static final String dataBaseName="foodRecordManager";

    private static final String tablePlaces="places";
    private static final String tableProducts="products";
    private static final String tableProductComment="product_comment";



    private static final String keyPlacesId="places_id";
    private static final String keyPlacesImagePath="places_image_path";
    private static final String keyPlacesName="places_name";
    private static final String keyPlacesLocLang="places_loc_lang";
    private static final String keyPlacesLocLong="places_loc_long";
    private static final String keyPlacesLikeDislike="places_like_dislike";

    private static final String keyProductId="product_id";
    private static final String keyProductPlacesId="places_id";
    private static final String keyProductDate="product_date";
    private static final String keyProductImagePath="product_image_path";
    private static final String keyProductName="product_name";
    private static final String keyProductPrice="product_price";
    private static final String keyProductLikeDislike="product_like_dislike";

    private static final String keyCommentProductId="product_id";
    private static final String keyCommentDate="comment_date";
    private static final String keyCommentDegree="comment_degree";
    private static final String keyCommentContent="comment_content";






    public DataBaseHandler(Context context) {
        super(context, dataBaseName, null, dataBaseVersion);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String createTablePlaces="CREATE TABLE "+tablePlaces+"("+
                keyPlacesId+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                keyPlacesImagePath+" TEXT,"+
                keyPlacesName+" TEXT NOT NULL,"+
                keyPlacesLocLang+" TEXT,"+
                keyPlacesLocLong+" TEXT,"+
                keyPlacesLikeDislike+" TEXT)";

        String createTableProducts="CREATE TABLE "+tableProducts+"("+
                keyProductId+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                keyProductPlacesId+" INTEGER,"+
                keyProductDate+" TEXT,"+
                keyProductImagePath+" TEXT,"+
                keyProductName+" TEXT,"+
                keyProductPrice+" TEXT,"+
                keyProductLikeDislike+" TEXT)";

        String createTableCommentProduct="CREATE TABLE "+tableProductComment+"("+
                keyCommentProductId+" INTEGER,"+
                keyCommentDate+" TEXT,"+
                keyCommentDegree+" TEXT,"+
                keyCommentContent+" TEXT)";

        sqLiteDatabase.execSQL(createTablePlaces);
        sqLiteDatabase.execSQL(createTableProducts);
        sqLiteDatabase.execSQL(createTableCommentProduct);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+tablePlaces);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+tableProducts);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+tableProductComment);
        onCreate(sqLiteDatabase);

    }
    public void addPlaces(PlacesModel placesModel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(keyPlacesImagePath,placesModel.getPlaces_image_path());
        values.put(keyPlacesName,placesModel.getPlaces_name());
        values.put(keyPlacesLocLang,placesModel.getPlaces_loc_lang());
        values.put(keyPlacesLocLong,placesModel.getPlaces_loc_long());
        values.put(keyPlacesLikeDislike,placesModel.getPlaces_like_dislike());
        db.insert(tablePlaces,null,values);

        db.close();
    }
    public void updatePlaces(PlacesModel placesModel,String id){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(keyPlacesImagePath,placesModel.getPlaces_image_path());
        values.put(keyPlacesName,placesModel.getPlaces_name());
        values.put(keyPlacesLocLang,placesModel.getPlaces_loc_lang());
        values.put(keyPlacesLocLong,placesModel.getPlaces_loc_long());
        values.put(keyPlacesLikeDislike,placesModel.getPlaces_like_dislike());
        db.update(tablePlaces,values,keyPlacesId+"="+id,null);
        db.close();
    }
    public void deletePlaces(String id){
        String deleteQuery="delete from "+tablePlaces+" where "+keyPlacesId+"="+id;
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL(deleteQuery);
        db.close();
    }
    public List<PlacesModel> getAllPlaces(){
        List<PlacesModel>placesModelList=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        String selectQuery="select * from "+tablePlaces;
        Cursor cursor=db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst())
        do {
            PlacesModel placesModel=new PlacesModel();
            placesModel.setPlaces_id(cursor.getInt(0));
            placesModel.setPlaces_image_path(cursor.getString(1));
            placesModel.setPlaces_name(cursor.getString(2));
            placesModel.setPlaces_loc_lang(cursor.getString(3));
            placesModel.setPlaces_loc_long(cursor.getString(4));
            placesModel.setPlaces_like_dislike(cursor.getString(5));
            placesModelList.add(placesModel);

        }while (cursor.moveToNext());


        return placesModelList;
    }
    public int getCountPlaces(){

        SQLiteDatabase db=this.getReadableDatabase();
        String query="select * from "+tablePlaces;
        Cursor cursor=db.rawQuery(query,null);
        return cursor.getCount();
    }
       public PlacesModel getPlace(String id){

        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();

        Cursor cursor=sqLiteDatabase.query(tablePlaces,new String[]{keyPlacesId,keyPlacesImagePath,keyPlacesName,keyPlacesLocLang,keyPlacesLocLong,keyPlacesLikeDislike},keyPlacesId+"="+id,null,null,null,null);
       if(cursor!=null)
           cursor.moveToFirst();
           PlacesModel placesModel=new PlacesModel(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
           return placesModel;


    }

    public void addProducts(ProductModel pm){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(keyProductId,pm.getProduct_id());
        values.put(keyProductPlacesId,pm.getPlaces_id());
        values.put(keyProductDate,pm.getProduct_date());
        values.put(keyProductImagePath,pm.getProduct_image_path());
        values.put(keyProductName,pm.getProduct_name());
        values.put(keyProductPrice,pm.getProduct_price());
        values.put(keyProductLikeDislike,pm.getProduct_like_dislike());
        db.insert(tableProducts,null,values);
        db.close();
    }

    public void updateProduct(ProductModel pm,String id){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(keyProductId,pm.getProduct_id());
        values.put(keyProductPlacesId,pm.getPlaces_id());
        values.put(keyProductDate,pm.getProduct_date());
        values.put(keyProductImagePath,pm.getProduct_image_path());
        values.put(keyProductName,pm.getProduct_name());
        values.put(keyProductPrice,pm.getProduct_price());
        values.put(keyProductLikeDislike,pm.getProduct_like_dislike());
        db.update(tableProducts,values,keyProductId+"="+id,null);
        db.close();
    }

    public void deleteProduct(String id){
        String deleteQuery="delete from "+tableProducts+" where "+keyProductId+"="+id;
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL(deleteQuery);
        db.close();
    }
    public List<ProductModel> getAllProducts(){
        List<ProductModel>productsModelList=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        String selectQuery="select * from "+tableProducts;
        Cursor cursor=db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst())
            do {
                ProductModel productModel=new ProductModel();
                productModel.setProduct_id(cursor.getInt(0));
                productModel.setPlaces_id(cursor.getInt(1));
                productModel.setProduct_date(cursor.getString(2));
                productModel.setProduct_image_path(cursor.getString(3));
                productModel.setProduct_name(cursor.getString(4));
                productModel.setProduct_price(cursor.getString(5));
                productModel.setProduct_like_dislike(cursor.getString(6));
                productsModelList.add(productModel);
            }while (cursor.moveToNext());


        return productsModelList;
    }



    public void addComment(ProductCommentModel productCommentModel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(keyCommentProductId,productCommentModel.getProduct_id());
        values.put(keyCommentDate,productCommentModel.getComment_date());
        values.put(keyCommentDegree,productCommentModel.getComment_degree());
        values.put(keyCommentContent,productCommentModel.getComment_content());
        db.insert(tableProductComment,null,values);
        db.close();

    }
    public void deleteComment(String id){
        String deleteQuery="delete from "+tableProductComment+" where "+keyCommentProductId+"="+id;
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL(deleteQuery);
        db.close();
    }
public List<ProductCommentModel> getAllComments(){
    List<ProductCommentModel>commentsModelList=new ArrayList<>();
    SQLiteDatabase db=this.getReadableDatabase();
    String selectQuery="select * from "+tableProductComment;
    Cursor cursor=db.rawQuery(selectQuery,null);
    if(cursor.moveToFirst())
        do {
            ProductCommentModel productCommentModel=new ProductCommentModel();
            productCommentModel.setProduct_id(cursor.getInt(0));
            productCommentModel.setComment_date(cursor.getString(1));
            productCommentModel.setComment_degree(cursor.getString(2));
            productCommentModel.setComment_content(cursor.getString(3));
            commentsModelList.add(productCommentModel);

        }while (cursor.moveToNext());


    return commentsModelList;
}
    public void updateComment(ProductCommentModel productCommentModel, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(keyProductId,productCommentModel.getProduct_id());
        values.put(keyCommentDate,productCommentModel.getComment_date());
        values.put(keyCommentDegree,productCommentModel.getComment_degree());
        values.put(keyCommentContent,productCommentModel.getComment_content());
        db.update(tableProductComment,values,keyProductDate+"="+date,null);
        db.close();
    }
}
