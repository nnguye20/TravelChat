package edu.brown.cs.student.test;

import org.junit.Test;

public class FoodPreferenceTest {

  @Test
  public void test() {
    // Outputs perfectly match.

    // HttpResponse<String> response = Unirest.get(
    // "https://tripadvisor1.p.rapidapi.com/restaurants/list-by-latlng?limit=10&open_now=true&min_rating=4&currency=USD&distance=2&lunit=km&lang=en_US&latitude=12.91285&longitude=100.87808")
    // .header("x-rapidapi-host", "tripadvisor1.p.rapidapi.com")
    // .header("x-rapidapi-key",
    // "9ab9c1d3bdmsha453182e940dd58p105f14jsna2fade8f7b4d").asString();
    // System.out.println(response.getBody());

    // FoodPreference fp = new FoodPreference();
    // Map<String, Object> fields = new HashMap<>();
    // fields.put("limit", 10); // adjust
    // fields.put("lang", "en_US"); // adjust
    // fields.put("currency", "USD"); // adjust
    // fields.put("lunit", "km"); // adjust
    // fields.put("min_rating", 4);
    // fields.put("dietary_restrictions", "");
    // fields.put("open_now", true);
    // fields.put("distance", 2);
    // fields.put("restaurant_dining_options", "");
    // fields.put("prices_restaurants", "");
    // fields.put("restaurant_styles", "");
    // fields.put("combined_food", "");
    // fields.put("restaurant_mealtype", "");
    // fields.put("latitude", 12.91285);
    // fields.put("longitude", 100.87808);
    // fp.setFields(fields);
    // System.out.println(fp.getQueryString());

    // List<Item> restaurantsList = fp.parseResult();
    // System.out.println(restaurantsList.size());
    // for (Item restaurant : restaurantsList) {
    // System.out.println(restaurant.getName());
    // }
  }

}