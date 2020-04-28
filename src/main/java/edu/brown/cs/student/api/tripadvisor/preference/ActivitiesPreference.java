package edu.brown.cs.student.api.tripadvisor.preference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import edu.brown.cs.student.api.tripadvisor.objects.Attraction;
import edu.brown.cs.student.api.tripadvisor.objects.Item;

public class ActivitiesPreference implements Preference {
  private double longitude;
  private double latitude;
  private double distance;
  private Map<String, Object> fields;

  public ActivitiesPreference(double longitude, double latitude, double distance) {
    super();
    this.longitude = longitude;
    this.latitude = latitude;
    this.distance = distance;
    this.buildFields();
  }

  @Override
  public void buildFields() {
    fields = new HashMap<>();
    fields.put("limit", 10); // adjust
    fields.put("lang", "en_US"); // adjust
    fields.put("currency", "USD"); // adjust
    fields.put("lunit", "km"); // length unit; adjust
    fields.put("latitude", latitude);
    fields.put("longitude", longitude);
    fields.put("distance", distance);
  }

  @Override
  public String getQueryString() throws UnirestException {
    if (latitude >= -90 && latitude <= 90 && longitude >= -180 && longitude <= 180) {
      System.err.println("ERROR: Latitude or longitude invalid");
      return null;
    }

    HttpResponse<String> response = Unirest
        .get("https://tripadvisor1.p.rapidapi.com/attractions/list-by-latlng?")
        .header("x-rapidapi-host", "tripadvisor1.p.rapidapi.com")
        .header("x-rapidapi-key", "9ab9c1d3bdmsha453182e940dd58p105f14jsna2fade8f7b4d")
        .queryString(fields).asString();
    return response.getBody();
  }

  @Override
  public List<Item> parseResult() throws JSONException, UnirestException {
    JSONObject obj = new JSONObject(this.getQueryString());
    if (obj.get("error") != null) {
      System.err.println("ERROR: Error in query");
      return null;
    }

    List<Item> attractionsList = new ArrayList<Item>();
    JSONArray attractionsArr = (JSONArray) obj.get("data");
    // goes through all of the attractions recommended
    for (int i = 0; i < attractionsArr.length(); i++) {
      Attraction attraction = new Attraction();
      JSONObject attractionObj = (JSONObject) attractionsArr.get(i);

      JSONObject photoObj = (JSONObject) attractionObj.get("photo");
      JSONObject imagesObj = (JSONObject) photoObj.get("images");
      JSONObject smallObj = (JSONObject) imagesObj.get("small");

      attraction.setPhotoUrl(smallObj.getString("url")); // "https://media-cdn.tripadvisor.com/media/photo-l/15/19/d6/c1/the-jouney-begins-kaan.jpg"
      attraction.setName(attractionObj.getString("name")); // "Performances"
      attraction.setLatitude(attractionObj.getDouble("latitude")); // 12.906674
      attraction.setLongitude(attractionObj.getDouble("longitude")); // 100.87785
      attraction.setDistance(attractionObj.getDouble("distance")); // 1.0886330230315118
      attraction.setDistanceString(attractionObj.getString("distance_string")); // "1.1 km"
      attraction.setNumReviews(attractionObj.getInt("num_reviews")); // 120
      attraction.setLocationString(attractionObj.getString("location_string")); // "Pattaya,
                                                                                // Chonburi
      // Province"
      attraction.setRating(attractionObj.getDouble("rating")); // 4.0
      attraction.setPriceRange(attractionObj.getString("price")); // "$16.23"
      attraction.setRanking(attractionObj.getInt("ranking_position")); // 7
      attraction.setRankingString(attractionObj.getString("ranking")); // "#7 of 108 things to do in
                                                                       // Pattaya"
      attraction.setClosed(attractionObj.getBoolean("is_closed")); // false

      attractionsList.add(attraction);
    }

    return attractionsList;
  }

  @Override
  public double getLongitude() {
    return longitude;
  }

  @Override
  public double getLatitude() {
    return latitude;
  }

  public double getDistance() {
    return distance;
  }

  @Override
  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  @Override
  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public void setDistance(double distance) {
    this.distance = distance;
  }

  @Override
  public Map<String, Object> getFields() {
    return fields;
  }

  @Override
  public void setFields(Map<String, Object> fields) {
    this.fields = fields;
  }
}