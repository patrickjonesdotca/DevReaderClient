package ca.pjones.devreader;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class PageView extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.pageview);
        WebView wr = (WebView) findViewById(R.id.webView1);
        
        WebSettings webSettings = wr.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(false);
        webSettings.setBuiltInZoomControls(true);
        
        Bundle b = getIntent().getExtras();
        String location = b.getString("link");
        wr.loadUrl(location);
    }

}
