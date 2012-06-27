package ca.pjones.devreader.models;

import com.google.gson.annotations.SerializedName;

public class Result {
	
	@SerializedName("text")
	public String title;
	
	@SerializedName("href")
	public String link;

}
