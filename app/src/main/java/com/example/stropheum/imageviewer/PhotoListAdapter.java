package com.example.stropheum.imageviewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by Dale Diaz on 7/31/2017.
 * An implementation of ArrayAdapter intended to map data from PhotoListComponent arrays to a ListView
 */
public class PhotoListAdapter extends ArrayAdapter<PhotoListComponent>
{

    /**
     * Constructor for the photo list adapter
     * @param context The application context that the adapter is being constructed from
     * @param components The components being adapted from java objects into views
     */
    public PhotoListAdapter(Context context, ArrayList<PhotoListComponent> components)
    {
        super(context, 0, components);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // Store off the element we wish to represent from the list
        PhotoListComponent component = getItem(position);

        // If we're not reusing a view, inflate a new one
        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.photo_list_component, parent, false);
        }

        // We grab the views from our layout and store them off
        TextView title = (TextView) convertView.findViewById(R.id.photolistcomponent_title);
        TextView subtitle = (TextView) convertView.findViewById(R.id.photolistcomponent_subtitle);

        // We adapt our component data to the view elements
        title.setText(component.Title());
        subtitle.setText(component.Subtitle());

        final ProgressBar progressBar = (ProgressBar) convertView.findViewById(R.id.progressBar);

        // show The Image in a ImageView
        String url = component.ImageURL();
        Picasso.with(getContext()).load(url)
                .into((ImageView) convertView.findViewById(R.id.photolistcomponent_image), new Callback()
                {
                    @Override
                    public void onSuccess()
                    {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError()
                    {
                        Toast.makeText(getContext(), "Error loading image", Toast.LENGTH_SHORT).show();
                    }
                });

        // Now we've got a successfully adapted view
        return convertView;
    }

}

