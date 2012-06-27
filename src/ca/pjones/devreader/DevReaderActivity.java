package ca.pjones.devreader;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.Reader;

import com.google.gson.Gson;

import ca.pjones.devreader.models.LinksRoot;
import ca.pjones.devreader.models.Result;

public class DevReaderActivity extends ListActivity {
	LinksRoot lr;
	String JSONURL = "http://devreader.herokuapp.com/";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadJSON(JSONURL);
        String[] titles = extractTitles(lr);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, titles);
        setListAdapter(adapter);
    }
    
    private void loadJSON(String url) {
    	try { 
        	InputStream input = new URL(url).openStream(); 
        	Reader reader = new InputStreamReader(input, "UTF-8");
        	lr = new Gson().fromJson(reader, LinksRoot.class);
        }
        catch(Exception e) { e.printStackTrace(); }
    }
    
    private String[] extractTitles(LinksRoot l) {
    	String[] ret = new String[l.results.length];
    	for(int i = 0; i < l.results.length; i++) {
    		ret[i] = l.results[i].title;
    	}
    	return ret;
    }
}