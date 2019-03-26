package com.html5test;

import android.app.ProgressDialog;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.URLUtil;
import android.view.View.OnTouchListener;
import android.view.View;
import android.view.GestureDetector;
import android.content.Context;
import android.view.GestureDetector.SimpleOnGestureListener;


public class MainActivity extends Activity {
	private final static String TAG = "GS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final WebView webView = (WebView) findViewById(R.id.webView);
        final WebSettings settings = webView.getSettings();

        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);

        final ProgressDialog progDailog = ProgressDialog.show(this, "Loading", "Please wait...", true);
        progDailog.setCancelable(false);

        final GestureDetector gd = new GestureDetector(this, new SimpleOnGestureListener(){
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                try {
                    final float diffY = e2.getY() - e1.getY();
                    final int scroll = webView.getScrollY();
                    
                    Log.e(TAG, "onFling");
                    Log.e(TAG, "scroll: " + scroll);
                    Log.e(TAG, "diff: " + diffY);
                    if(scroll == 0 && diffY > 200){
                        Log.e(TAG, "Loading...");
                        webView.loadUrl(webView.getUrl());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return false;
            }
        });

        webView.setOnTouchListener(new OnTouchListener(){
            public boolean onTouch(final View view, final MotionEvent e) {
                return gd.onTouchEvent(e);
            }
        });

        webView.setWebViewClient(new WebViewClient() {
            
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (URLUtil.isNetworkUrl(url)) {
                    progDailog.show();
                    view.loadUrl(url);
                } else {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                }
                return true;
            }

            @Override
            public void onPageFinished(WebView view, final String url) {
                progDailog.dismiss();
            }
        });
        webView.loadUrl("https://html5test.com/");
    }
}