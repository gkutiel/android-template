package com.funky;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      WebView webView = (WebView) findViewById(R.id.webView);
      // WebSettings settings = webView.getSettings();

      //webView.setWebViewClient(new WebViewClient());
      // webView.setInitialScale(100);
      // webView.setInitialScale(0);

      // settings.setTextZoom(50);
      // settings.setUseWideViewPort(true);
      // settings.setLoadWithOverviewMode(true);
      // settings.setMinimumFontSize(1);
      // settings.setMinimumLogicalFontSize(1);
      // settings.setJavaScriptEnabled(true);

      // webView.loadUrl("https://www.funkymsg.com/app/");
      webView.loadUrl("http://www.cs.technion.ac.il/~gkutiel/test/");
   }
}
