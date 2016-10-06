package com.mercury.gnusin.mygallery;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.a_gallery)
public class GalleryActivity extends Activity {

    @ViewById(R.id.image_grid)
    GalleryGridView imageGrid;

    @AfterViews
    void init() {
        imageGrid.setAdapter(new ImageAdapter(this));
    }

    @ItemClick(R.id.image_grid)
    void itemClicked(Integer imageID) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "" + imageID));
        startActivity(intent);
    }

}
