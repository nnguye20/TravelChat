package edu.brown.cs.student.chat.gui.calendar;

import com.google.firebase.database.*;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

public class PostCalendarEventHandler implements Route {

  @Override
  public String handle(Request request, Response response) {


    QueryParamsMap qm = request.queryMap();
    String uniqueEventID = qm.value("id");
    String chatID = qm.value("chatID");
    String title = qm.value("title");
    String startTime = qm.value("start");
    String endTime = qm.value("end");
    String location = qm.value("location");
    String price = qm.value("price");
    String description = qm.value("description");


    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference roomRef = database.getReference("chat/room-metadata").child(chatID);
    DatabaseReference eventsRef = roomRef.child("events");

    eventsRef.child(uniqueEventID).setValueAsync(
          new CalendarEvent(uniqueEventID, title, startTime, endTime, location, price, description));


    return "";
  }



}
