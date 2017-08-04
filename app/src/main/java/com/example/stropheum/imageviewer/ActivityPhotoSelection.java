package com.example.stropheum.imageviewer;

// Android dependencies
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

// Java dependencies
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// External dependencies
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Dale Diaz on 7/31/2017
 * This Activity displays image thumbnails and titles in a list view, populated from an image hosting
 * service specified by the user. On selection of an image, a larger image with more detailed
 * information will be displayed for the user
 */
public class ActivityPhotoSelection extends AppCompatActivity
{
    private enum API
    {
        Imgur
    }

    private API mAPI = API.Imgur;

    // Photo List Components
    private ListView mPhotoList;
    private ArrayList<PhotoListComponent> mPhotoListComponents = new ArrayList<>();
    private PhotoListAdapter mPhotoListAdapter;

    // Http request components
    private OkHttpClient httpClient = new OkHttpClient();
    private HashMap<String, String> mClientID = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_selection);

        mClientID.put("Imgur", "7dfeb2b5004ba98");

        InitializeListComponents();
        PopulatePhotoList();
    }

    /**
     * Initializes the photo list, its associated adapter, and adds a stub element to it
     */
    private void InitializeListComponents()
    {
        mPhotoList = (ListView) findViewById(R.id.photoselection_photo_list);
        mPhotoListAdapter = new PhotoListAdapter(getApplicationContext(), mPhotoListComponents);
        mPhotoList.setAdapter(mPhotoListAdapter);
        mPhotoList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Intent displayIntent = new Intent(getApplicationContext(), ActivityPhotoDisplay.class)
                        .putExtra("photo_title", mPhotoListComponents.get(i).Title())
                        .putExtra("photo_id", mPhotoListComponents.get(i).Subtitle())
                        .putExtra("photo_url", mPhotoListComponents.get(i).ImageURL())
                        .putExtra("photo_uploader", mPhotoListComponents.get(i).Uploader());
                startActivity(displayIntent);
            }
        });
    }

    /**
     * Adds an elements to the activity's list view dynamically
     * @param title The title of the list view element
     * @param subtitle The subtitle of the list view element
     */
    public void AddListElement(String title, String subtitle, String url, String description)
    {
        mPhotoListComponents.add(new PhotoListComponent(title, subtitle, url, description));
        mPhotoListAdapter.notifyDataSetChanged();
    }

    /**
     * Requests a chunk of images from the user-selected platform and displays them in a list
     */
    private void PopulatePhotoList()
    {
        switch (mAPI)
        {
            case Imgur:
                HandleImgurPopulation();
                break;
        }
    }

    /**
     * Populates the photo list for am Imgur API selection
     */
    private void HandleImgurPopulation()
    {
        String clientID = "Client-ID " + mClientID.get("Imgur");
        Request request = new Request.Builder()
                .url("https://api.imgur.com/3/g/memes/")
                .header("Authorization", clientID)
                .header("User-Agent", "Dale Diaz ImageViewer")
                .build();

        httpClient.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e)
            {
                Log.e("HTTP Request", "An error has occurred " + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                try
                {
                    JSONObject data = new JSONObject(response.body().string());
                    JSONArray items = data.getJSONArray("data");
                    final List<ImgurPhoto> images = new ArrayList<>();

                    for (int i = 0; i < items.length(); i++)
                    {
                        JSONObject item = items.getJSONObject(i);
                        ImgurPhoto image = new ImgurPhoto();

                        if (item.getBoolean("is_album"))
                        {
                            image.SetId(item.getString("cover"));
                        }
                        image.SetTitle(item.getString("title"));
                        image.SetUploader(item.getString("account_url"));

                        images.add(image);
                    }

                    runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            for (ImgurPhoto photo : images)
                            {
                                AddListElement(photo.Title(), photo.Id(), "http://i.imgur.com/" + photo.Id() + ".jpg", photo.Uploader());
                            }
                        }
                    });

                } catch (JSONException e) { e.printStackTrace(); }
            }
        });
    }
}
