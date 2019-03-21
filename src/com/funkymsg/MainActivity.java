package com.funkymsg;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings;

public class MainActivity extends Activity {
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      final WebView webView = (WebView) findViewById(R.id.webView);
      final WebSettings settings = webView.getSettings();

      settings.setJavaScriptEnabled(true);

      webView.setWebViewClient(new WebViewClient());
      webView.loadUrl("https://www.funkymsg.com/app/");
   }
}
