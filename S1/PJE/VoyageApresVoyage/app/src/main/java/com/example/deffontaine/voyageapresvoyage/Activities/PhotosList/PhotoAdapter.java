package com.example.deffontaine.voyageapresvoyage.Activities.PhotosList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.deffontaine.voyageapresvoyage.Entities.Note;

import java.util.List;

public class PhotoAdapter extends BaseAdapter {
    private List<Note> mPhotos;
    private Context mContext;

    public PhotoAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        if (mPhotos != null)
            return mPhotos.size();
        else return 0;
    }

    public Object getItem(int position) {
        return mPhotos.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        Note current = mPhotos.get(position);
        this.setPic(imageView, current.getNote_description());
        return imageView;
    }

    private void setPic(ImageView imgView, String imgPath) {

        int targetW = 300;
        int targetH = 300;

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imgPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(imgPath, bmOptions);
        bitmap = cropBitmap(bitmap);
        imgView.setImageBitmap(bitmap);
    }

    public Bitmap cropBitmap(Bitmap srcBmp){
        Bitmap dstBmp;
        if (srcBmp.getWidth() >= srcBmp.getHeight()){
            dstBmp = Bitmap.createBitmap(
                    srcBmp,
                    srcBmp.getWidth()/2 - srcBmp.getHeight()/2,
                    0,
                    srcBmp.getHeight(),
                    srcBmp.getHeight()
            );
        }else{
            dstBmp = Bitmap.createBitmap(
                    srcBmp,
                    0,
                    srcBmp.getHeight()/2 - srcBmp.getWidth()/2,
                    srcBmp.getWidth(),
                    srcBmp.getWidth()
            );
        }
        return dstBmp;
    }


    void setPhotos(List<Note> photos){
        mPhotos = photos;
        notifyDataSetChanged();
    }

    public Note getPhoto(int position){
        return mPhotos.get(position);
    }

}

