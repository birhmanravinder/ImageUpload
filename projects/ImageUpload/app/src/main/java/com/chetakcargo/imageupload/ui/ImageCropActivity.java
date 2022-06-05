package com.chetakcargo.imageupload.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.chetakcargo.imageupload.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Created by Ravinder Birhman on 05,June,2022
 * Country India,
 * State Haryana,
 * Email ravinder.birhman2000@gmail.com
 **/
public class ImageCropActivity extends AppCompatActivity {
    private CropImageView mCropImageView;
    ImageView image;
    FloatingActionButton rotate_image;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_crop);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Image Crop");

        toolbar.setNavigationOnClickListener(v -> finish());

        mCropImageView = (CropImageView) findViewById(R.id.CropImageView);
        image = (ImageView) findViewById(R.id.image);
        rotate_image = (FloatingActionButton) findViewById(R.id.rotate_image);

        imageUri = Uri.parse(getIntent().getStringExtra("imageUri"));
        setUriImages(imageUri);

        rotate_image.setOnClickListener(v -> {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(ImageCropActivity.this.getContentResolver(), imageUri);
                Matrix matrix = new Matrix();
                matrix.postRotate(90);
                Bitmap rotated = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
                mCropImageView.setImageBitmap(rotated);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void setUriImages(Uri imageUri){
        mCropImageView.setImageUriAsync(imageUri);
    }

    public void onCropImageClick(View view) {
        Bitmap cropped = mCropImageView.getCroppedImage(300, 300);
        if (cropped != null){
            try{
                File imgPath =  saveBitmapIntoSDCardImage(cropped);
                Uri mCropImageUri = Uri.parse(imgPath.getAbsolutePath());

                Intent intent=new Intent();
                intent.putExtra("imgUri", mCropImageUri.toString());
                setResult(RESULT_OK,intent);
                finish();
            } catch (Exception e) {
                finish();
            }
        }
    }

    public  File saveBitmapIntoSDCardImage(Bitmap finalBitmap) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File myDir = new File(getCacheDir(), timeStamp+"-image");
        if (!myDir.exists()) {
            myDir.mkdirs();
        }
        String fname = timeStamp + ".jpg";
        File file = new File(myDir, fname);

        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 60, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return file;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
