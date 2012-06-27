package ca.pjones.devreader.models;


import com.google.gson.annotations.SerializedName;

public class LinksRoot {
	
	public Result[] results;
	
	@SerializedName("source")
	public String source;

}
