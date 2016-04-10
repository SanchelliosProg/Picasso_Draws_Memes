package com.experiments.sanchellios.picasso_draws_memes;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.LinkedList;
import java.util.Queue;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Queue<String> memes = new LinkedList<>();
    private ImageView imageViewTop;
    private ImageView imageViewMiddle;
    private ImageView imageViewBottom;
    private RandomImageProvider imageProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        initImageViews();
        setOnClickListenerToImageViews();
        uploadNewImageURLs();
        loadImages();
    }

    private void initImageViews(){
        imageViewTop = (ImageView)findViewById(R.id.imageViewTop);
        imageViewMiddle = (ImageView)findViewById(R.id.imageViewMiddle);
        imageViewBottom = (ImageView)findViewById(R.id.imageViewBottom);
    }

    private void setOnClickListenerToImageViews(){
        imageViewTop.setOnClickListener(this);
        imageViewMiddle.setOnClickListener(this);
        imageViewBottom.setOnClickListener(this);
    }

    private void uploadNewImageURLs(){
        if(imageProvider == null){
            imageProvider = new RandomImageProvider(getApplicationContext());
        }
        memes = imageProvider.getPortionOfMemes();
    }

    private void loadImages(){
        uploadTheImage(imageViewTop);
        uploadTheImage(imageViewMiddle);
        uploadTheImage(imageViewBottom);
    }

    private void uploadTheImage(ImageView view){
        Picasso.with(getApplicationContext())
                .load(memes.poll())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .fit()
                .centerInside()
                .into(view);
    }

    @Override
    public void onClick(View v) {
        uploadNewImageURLs();
        loadImages();
    }
}
