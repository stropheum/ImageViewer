package com.example.stropheum.imageviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ActivityPhotoDisplay extends AppCompatActivity
{
    private TextView mTitle;
    private TextView mId;
    private TextView mURL;
    private TextView mDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_display);

        InitializeDisplayComponents();
    }

    /**
     * Initializes all local members to their associated XML view components
     */
    private void InitializeDisplayComponents()
    {
        mTitle = (TextView) findViewById(R.id.photodisplay_title);
        mId = (TextView) findViewById(R.id.photodisplay_id);
        mURL = (TextView) findViewById(R.id.photodisplay_url);
        mDescription = (TextView) findViewById(R.id.photodisplay_uploader);

        Picasso.with(getApplicationContext()).load(getIntent().getStringExtra("photo_url"))
                .into((ImageView) findViewById(R.id.photodisplay_image), new Callback()
                {
                    @Override
                    public void onSuccess()
                    {
                        findViewById(R.id.photodisplay_progressbar).setVisibility(View.GONE);
                    }

                    @Override
                    public void onError()
                    {
                        Toast.makeText(getApplicationContext(), "Error loading image", Toast.LENGTH_SHORT).show();
                    }
                });

        mTitle.setText(Html.fromHtml("<b>Title:</b> " + getIntent().getStringExtra("photo_title")));
        mId.setText(Html.fromHtml("<b>ID:</b> " + getIntent().getStringExtra("photo_id")));
        mURL.setText(Html.fromHtml("<b>URL:</b> " + getIntent().getStringExtra("photo_url")));
        mDescription.setText(Html.fromHtml("<b>Uploader:</b> " + getIntent().getStringExtra("photo_uploader")));
    }
}
