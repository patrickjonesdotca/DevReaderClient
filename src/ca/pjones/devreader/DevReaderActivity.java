package ca.pjones.devreader;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.Reader;

import com.google.gson.Gson;

import ca.pjones.devreader.models.LinksRoot;
import ca.pjones.devreader.models.Result;

public class DevReaderActivity extends ListActivity {
	LinksRoot lr;
	String JSONURL = "http://devreader.herokuapp.com";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        generate(JSONURL);
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        	generate(JSONURL);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
      String newRoot = lr.results[position].link;
      if( newRoot.startsWith("/") ) {
    	  generate(JSONURL + newRoot);
      } else { 
    	  Bundle b = new Bundle();
    	  b.putString("link", lr.results[position].link);
    	  Intent i = new Intent(DevReaderActivity.this, PageView.class);
    	  i.putExtras(b);
    	  startActivity(i);
      }

      super.onListItemClick(l, v, position, id);
    }
    
    private void generate(String url) {
        loadJSON(url);
        String[] titles = extractTitles(lr);
        setUpList(titles);	
    }
    
    private void setUpList(String[] elements) {
    	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, elements);
        setListAdapter(adapter);
    }
    
    private void loadJSON(String url) {
    	try { 
        	InputStream input = new URL(url).openStream(); 
        	Reader reader = new InputStreamReader(input, "UTF-8");
        	lr = new Gson().fromJson(reader, LinksRoot.class);
        }
        catch(Exception e) { e.printStackTrace(); }
    };
    
    private String[] extractTitles(LinksRoot l) {
    	String[] ret = new String[l.results.length];
    	for(int i = 0; i < l.results.length; i++) {
    		ret[i] = l.results[i].title;
    	}
    	return ret;
    }
}