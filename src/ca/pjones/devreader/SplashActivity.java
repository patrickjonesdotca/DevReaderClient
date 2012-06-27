package ca.pjones.devreader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SplashActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void launchApp(View v) {
    	Intent i = new Intent(SplashActivity.this, DevReaderActivity.class);
    	startActivity(i);
    }

}
