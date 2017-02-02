package com.example.aggnimodule1;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bluehyena.aggnimodule1.R;
import com.example.aggnimodule1.VO.EventList;
import com.example.aggnimodule1.utilities.ImageLoader;

class EventImagesAdapter extends PagerAdapter {

	Context mContext;
	LayoutInflater mLayoutInflater;
	ArrayList<String> mEventImageList;

	public EventImagesAdapter(Context context, int position) {
		mContext = context;
		mEventImageList = EventList.getSingletonInstance().getEventDetailList()
				.get(position).getmImageList();
		Log.i("Vaibhavs", "" + mEventImageList.size());
		mLayoutInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return mEventImageList.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == ((LinearLayout) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View itemView = mLayoutInflater.inflate(R.layout.image_pager_item,
				container, false);

		ImageView eventImage = (ImageView) itemView
				.findViewById(R.id.eventImage);

		eventImage.setImageResource(R.drawable.aggni_orange_bg);
		
		int loader = R.drawable.aggni_orange_bg;
		
		String image_url = mEventImageList.get(position);
		ImageLoader imgLoader = new ImageLoader(mContext);
        
        // whenever you want to load an image from url
        // call DisplayImage function
        // url - image url to load
        // loader - loader image, will be displayed before getting image
        // image - ImageView 
        imgLoader.DisplayImage(image_url, loader, eventImage);
		
		container.addView(itemView);
		return itemView;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((LinearLayout) object);
	}

	
	public Bitmap getBitmapFromUrl(String url)
	{
	Bitmap bm = null;
	InputStream is = null;
	BufferedInputStream bis = null;
	try 
	{
	    URLConnection conn = new URL(url).openConnection();
	    conn.connect();
	    is = conn.getInputStream();
	    bis = new BufferedInputStream(is, 8192);
	    bm = BitmapFactory.decodeStream(bis);
	}
	catch (Exception e) 
	{
	    e.printStackTrace();
	}
	finally {
	    if (bis != null) 
	    {
	        try 
	        {
	            bis.close();
	        }
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	        }
	    }
	    if (is != null) 
	    {
	        try 
	        {
	            is.close();
	        }
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	        }
	    }
	}
	return bm;
	}
	


}