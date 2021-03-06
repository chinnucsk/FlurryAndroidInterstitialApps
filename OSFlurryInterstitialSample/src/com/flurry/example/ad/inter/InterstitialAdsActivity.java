package com.flurry.example.ad.inter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.flurry.android.FlurryAdType;
import com.flurry.android.FlurryAds;
import com.flurry.android.FlurryAdSize;
import com.flurry.android.FlurryAgent;
import com.flurry.android.FlurryAdListener;

public class InterstitialAdsActivity extends Activity implements
		FlurryAdListener {
	FrameLayout adLayout;
	private final String kLogTag = "FlurryAdServingAPI";
	public static String apiKey;
	private String adSpace;
	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.interstitial_ads_activity);
		mContext = InterstitialAdsActivity.this;
		apiKey = getResources().getString(R.string.flurry_api_key);
		adSpace = getResources().getString(R.string.adSpaceName);

		Button fetchAd = (Button) findViewById(R.id.fetch);
		fetchAd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				adLayout = (FrameLayout) findViewById(R.id.adLayout);
				if (!FlurryAds.isAdReady(adSpace))
				FlurryAds.fetchAd(mContext, adSpace, adLayout,
						FlurryAdSize.FULLSCREEN);
			}
		});

	}

	@Override
	public void onStart() {
		super.onStart();
		try {

			FlurryAds.setAdListener(this);
			FlurryAgent.setLogEnabled(true);
			FlurryAgent.setLogLevel(2);
			FlurryAgent.onStartSession(mContext, apiKey);
			FlurryAds.enableTestAds(false);
			
		} catch (Exception e) {
			Log.e(kLogTag, e.getMessage());
		}
	}

	public void spaceDidReceiveAd(String adSpace) {
		// called when the ad has been prepared, ad can be displayed:
		Log.d(kLogTag, "spaceDidReceiveAd( " + adSpace + " )");

		FlurryAds.displayAd(mContext, adSpace, adLayout);
		Toast toast = Toast.makeText(mContext, "Displaying Ad",
				Toast.LENGTH_SHORT);
		toast.show();
	}

	@Override
	public void onStop() {
		super.onStop();
		FlurryAds.removeAd(mContext, adSpace, adLayout);
		FlurryAgent.onEndSession(mContext);
	}

	@Override
	public void onAdClicked(String arg0) {
		Log.d(kLogTag, "onAdClicked( " + arg0 + " )");
		Toast toast = Toast.makeText(mContext, "onAdClicked",
				Toast.LENGTH_SHORT);
		toast.show();

	}

	@Override
	public void onAdClosed(String arg0) {
		Log.d(kLogTag, "onAdClosed( " + arg0 + " )");
		Toast toast = Toast
				.makeText(mContext, "onAdClosed", Toast.LENGTH_SHORT);
		toast.show();
	}

	@Override
	public void onAdOpened(String arg0) {
		Log.d(kLogTag, "onAdOpened( " + arg0 + " )");
		Toast toast = Toast
				.makeText(mContext, "onAdOpened", Toast.LENGTH_SHORT);
		toast.show();

	}

	@Override
	public void onApplicationExit(String arg0) {
		Log.d(kLogTag, "onApplicationExit( " + arg0 + " )");
		Toast toast = Toast.makeText(mContext, "onApplicationExit",
				Toast.LENGTH_SHORT);
		toast.show();
	}

	@Override
	public void onRenderFailed(String arg0) {
		Log.d(kLogTag, "onRenderFailed( " + arg0 + " )");
		Toast toast = Toast.makeText(mContext, "onREnderFailed",
				Toast.LENGTH_SHORT);
		toast.show();
	}

	@Override
	public void onVideoCompleted(String arg0) {
		Log.d(kLogTag, "onVideoCompleted( " + arg0 + " )");
		Toast toast = Toast.makeText(mContext, "onVideoCompleted",
				Toast.LENGTH_SHORT);
		toast.show();

	}

	@Override
	public boolean shouldDisplayAd(String arg0, FlurryAdType arg1) {
		Log.d(kLogTag, "shouldDisplayAd( " + arg0 + ", " + arg1 + " )");
		return true;
	}

	@Override
	public void spaceDidFailToReceiveAd(String arg0) {
		Log.d(kLogTag, "spaceDidFailToReceiveAd(" + arg0 + " )");
		Toast toast = Toast.makeText(mContext, "spaceDidFailToReceiveAd",
				Toast.LENGTH_SHORT);
		toast.show();
	}

}
