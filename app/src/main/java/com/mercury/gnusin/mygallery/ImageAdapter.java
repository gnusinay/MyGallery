package com.mercury.gnusin.mygallery;

import android.content.Context;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


public class ImageAdapter extends BaseAdapter {
    private Cursor thumbnailUriCursor;
    private Context context;
    private int itemsSize;

    public ImageAdapter(Context context) {
        this.context = context;
        this.thumbnailUriCursor = context.getContentResolver().query(
                MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Thumbnails._ID,
                             MediaStore.Images.Thumbnails.IMAGE_ID},
                null,
                null,
                null
        );
        this.itemsSize = getSizeItem();
    }

    @Override
    public int getCount() {
        return thumbnailUriCursor.getCount();
    }

    @Override
    public Object getItem(int i) {
        thumbnailUriCursor.moveToPosition(i);
        int columnIndex = thumbnailUriCursor.getColumnIndex(MediaStore.Images.Thumbnails.IMAGE_ID);
        return thumbnailUriCursor.getInt(columnIndex);
    }

    @Override
    public long getItemId(int i) {
        thumbnailUriCursor.moveToPosition(i);
        int columnIndex = thumbnailUriCursor.getColumnIndex(MediaStore.Images.Thumbnails._ID);
        return thumbnailUriCursor.getInt(columnIndex);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.i_thumbnail, null);
        }

        thumbnailUriCursor.moveToPosition(position);
        int columnIndex = thumbnailUriCursor.getColumnIndex(MediaStore.Images.Thumbnails._ID);
        int imageID = thumbnailUriCursor.getInt(columnIndex);

        ImageView imageView = (ImageView) view.findViewById(R.id.image_view);
        imageView.setImageURI(Uri.withAppendedPath(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, "" + imageID));
        imageView.setLayoutParams(new FrameLayout.LayoutParams(itemsSize, itemsSize));
        imageView.setRotation(90);
        imageView.setRotationY(180);

        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText("" + imageID);

        textView.setRotation(90);
        textView.setRotationY(180);

        return view;
    }

    private int getSizeItem() {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);

        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return metrics.heightPixels / 2;
        } else {
            return metrics.heightPixels / 4;
        }
    }
}