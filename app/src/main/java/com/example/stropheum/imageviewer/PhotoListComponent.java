package com.example.stropheum.imageviewer;

/**
 * Created by Dale Diaz on 7/31/2017.
 * This class serves as a data aggregate for easy transfer of data to the photo_list_component.xml view
 */
public class PhotoListComponent
{
    // Default non-specified image URL's to this image
    private final String mDefaultURL = "http://i.imgur.com/bwAGifP.jpg";

    private String mTitle;
    private String mSubtitle;
    private String mImageURL;
    private String mUploader;


    /**
     * Constructor for Photo List Element Data
     * @param title The title of the photo list element
     * @param subtitle The subtitle of the photo list element
     * @param imageURL The url of the image associated with the component
     */
    public PhotoListComponent(String title, String subtitle, String imageURL, String description)
    {
        mTitle = title;
        mSubtitle = subtitle;
        mImageURL = (imageURL.isEmpty()) ? mDefaultURL : imageURL;
        mUploader = description;
    }

    /**
     * Accessor method for the title of the data element
     * @return The title of the photo list element
     */
    public String Title()
    {
        return mTitle;
    }

    /**
     * Mutator method for the title of the data element
     * @param title The new element's title
     */
    public void SetTitle(String title)
    {
        mTitle = title;
    }

    /**
     * Accessor method for the subtitle of the data element
     * @return The subtitle of the photo list element
     */
    public String Subtitle()
    {
        return mSubtitle;
    }

    /**
     * Mutator method for the subtitle of the data element
     * @param subtitle The new element's subtitle
     */
    public void SetSubtitle(String subtitle)
    {
        mSubtitle = subtitle;
    }

    /**
     * Accessor method for the image URL of the data element
     * @return The URL of the photo
     */
    public String ImageURL()
    {
        return mImageURL;
    }

    /**
     * Mutator method for the image URL of the data element
     * @param imageURL The new URL of the photo
     */
    public void SetImageURL(String imageURL)
    {
        mImageURL = imageURL;
    }

    /**
     * Accessor method for the description of the data element
     * @return The description of the data element
     */
    public String Uploader()
    {
        return mUploader;
    }

    /**
     * Mutator method for the description of the data element
     * @param uploader The new description of the data element
     */
    public void SetUploader(String uploader)
    {
        mUploader = uploader;
    }

}
