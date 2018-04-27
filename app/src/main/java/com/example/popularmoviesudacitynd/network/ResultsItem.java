package com.example.popularmoviesudacitynd.network;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResultsItem implements Parcelable{

	public static final String KEY_MOVIE_DATA = "KEY_MOVIE_DATA";

	@SerializedName("overview")
	private String overview;

	@SerializedName("original_language")
	private String originalLanguage;

	@SerializedName("original_title")
	private String originalTitle;

	@SerializedName("video")
	private boolean video;

	@SerializedName("title")
	private String title;

	@SerializedName("genre_ids")
	private List<Integer> genreIds;

	@SerializedName("poster_path")
	private String posterPath;

	@SerializedName("backdrop_path")
	private String backdropPath;

	@SerializedName("release_date")
	private String releaseDate;

	@SerializedName("vote_average")
	private double voteAverage;

	@SerializedName("popularity")
	private double popularity;

	@SerializedName("id")
	private int id;

	@SerializedName("adult")
	private boolean adult;

	@SerializedName("vote_count")
	private int voteCount;

	public ResultsItem(String overview, String originalLanguage, String originalTitle, boolean video,
					   String title, List<Integer> genreIds, String posterPath, String backdropPath,
					   String releaseDate, double voteAverage, double popularity, int id,
					   boolean adult, int voteCount) {
		this.overview = overview;
		this.originalLanguage = originalLanguage;
		this.originalTitle = originalTitle;
		this.video = video;
		this.title = title;
		this.genreIds = genreIds;
		this.posterPath = posterPath;
		this.backdropPath = backdropPath;
		this.releaseDate = releaseDate;
		this.voteAverage = voteAverage;
		this.popularity = popularity;
		this.id = id;
		this.adult = adult;
		this.voteCount = voteCount;
	}

	public ResultsItem(Parcel in) {
		overview = in.readString();
		originalLanguage = in.readString();
		originalTitle = in.readString();
		video = in.readByte() != 0;
		title = in.readString();
		posterPath = in.readString();
		backdropPath = in.readString();
		releaseDate = in.readString();
		voteAverage = in.readDouble();
		popularity = in.readDouble();
		id = in.readInt();
		adult = in.readByte() != 0;
		voteCount = in.readInt();
	}

	public static final Creator<ResultsItem> CREATOR = new Creator<ResultsItem>() {
		@Override
		public ResultsItem createFromParcel(Parcel in) {
			return new ResultsItem(in);
		}

		@Override
		public ResultsItem[] newArray(int size) {
			return new ResultsItem[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int flags) {
		parcel.writeString(overview);
		parcel.writeString(originalLanguage);
		parcel.writeString(originalTitle);
		parcel.writeByte((byte) (video ? 1 : 0));
		parcel.writeString(title);
		parcel.writeString(posterPath);
		parcel.writeString(backdropPath);
		parcel.writeString(releaseDate);
		parcel.writeDouble(voteAverage);
		parcel.writeDouble(popularity);
		parcel.writeInt(id);
		parcel.writeByte((byte) (adult ? 1 : 0));
		parcel.writeInt(voteCount);
	}

	public void setOverview(String overview){
		this.overview = overview;
	}

	public String getOverview(){
		return overview;
	}

	public void setOriginalLanguage(String originalLanguage){
		this.originalLanguage = originalLanguage;
	}

	public String getOriginalLanguage(){
		return originalLanguage;
	}

	public void setOriginalTitle(String originalTitle){
		this.originalTitle = originalTitle;
	}

	public String getOriginalTitle(){
		return originalTitle;
	}

	public void setVideo(boolean video){
		this.video = video;
	}

	public boolean isVideo(){
		return video;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setGenreIds(List<Integer> genreIds){
		this.genreIds = genreIds;
	}

	public List<Integer> getGenreIds(){
		return genreIds;
	}

	public void setPosterPath(String posterPath){
		this.posterPath = posterPath;
	}

	public String getPosterPath(){
		return posterPath;
	}

	public void setBackdropPath(String backdropPath){
		this.backdropPath = backdropPath;
	}

	public String getBackdropPath(){
		return backdropPath;
	}

	public void setReleaseDate(String releaseDate){
		this.releaseDate = releaseDate;
	}

	public String getReleaseDate(){
		return releaseDate;
	}

	public void setVoteAverage(double voteAverage){
		this.voteAverage = voteAverage;
	}

	public double getVoteAverage(){
		return voteAverage;
	}

	public void setPopularity(double popularity){
		this.popularity = popularity;
	}

	public double getPopularity(){
		return popularity;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setAdult(boolean adult){
		this.adult = adult;
	}

	public boolean isAdult(){
		return adult;
	}

	public void setVoteCount(int voteCount){
		this.voteCount = voteCount;
	}

	public int getVoteCount(){
		return voteCount;
	}

	@Override
 	public String toString(){
		return
			"ResultsItem{" +
			"overview = '" + overview + '\'' +
			",original_language = '" + originalLanguage + '\'' +
			",original_title = '" + originalTitle + '\'' +
			",video = '" + video + '\'' +
			",title = '" + title + '\'' +
			",genre_ids = '" + genreIds + '\'' +
			",poster_path = '" + posterPath + '\'' +
			",backdrop_path = '" + backdropPath + '\'' +
			",release_date = '" + releaseDate + '\'' +
			",vote_average = '" + voteAverage + '\'' +
			",popularity = '" + popularity + '\'' +
			",id = '" + id + '\'' +
			",adult = '" + adult + '\'' +
			",vote_count = '" + voteCount + '\'' +
			"}";
		}
}