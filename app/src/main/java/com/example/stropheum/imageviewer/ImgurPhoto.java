package com.example.stropheum.imageviewer;

/**
 * Created by Stropheum on 8/2/2017.
 */

public class ImgurPhoto
{
    private String mId;
    private String mTitle;
    private String mUplaoder;

    /**
     * Constructor
     */
    public ImgurPhoto()
    {
    }

    /**
     * Accessor method for the photo id
     * @return The id of the photo
     */
    public String Id()
    {
        return mId;
    }

    /**
     * Mutator method for the photo id
     * @param id The new id of the photo
     */
    public void SetId(String id)
    {
        mId = id;
    }

    /**
     * Accessor method for the photo title
     * @return The title of the photo
     */
    public String Title()
    {
        return mTitle;
    }

    /**
     * Mutator method for the photo title
     * @param title The new title of the photo
     */
    public void SetTitle(String title)
    {
        mTitle = title;
    }

    /**
     * Accessor method for the photo uploader
     * @return The uploader of the photo
     */
    public String Uploader()
    {
        return mUplaoder;
    }

    /**
     * Mutator method for the photo uploader
     * @param uploader The new uploader of the photo
     */
    public void SetUploader(String uploader)
    {
        mUplaoder = uploader;
    }
}
