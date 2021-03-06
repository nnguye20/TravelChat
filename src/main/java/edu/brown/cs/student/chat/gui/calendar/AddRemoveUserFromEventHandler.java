package edu.brown.cs.student.chat.gui.calendar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

import javax.xml.crypto.Data;

public class AddRemoveUserFromEventHandler implements Route {

  @Override
  public Object handle(Request request, Response response) {
    try {
      QueryParamsMap qm = request.queryMap();
      String chatID = qm.value("chatID");
      String eventID = qm.value("eventID");
      String userID = qm.value("userID");
      String addRemove = qm.value("addRemove");

      final FirebaseDatabase database = FirebaseDatabase.getInstance();
      DatabaseReference roomRef = database.getReference("chat/room-metadata").child(chatID);
      DatabaseReference eventRef = roomRef.child("events").child(eventID);
      DatabaseReference userRef = eventRef.child("participants").child(userID);

      if (addRemove.equals("add")) {
        userRef.setValueAsync("true");
      } else if (addRemove.equals("remove")) {
        userRef.removeValueAsync();
      } else {
        System.out.println("ERROR: Invalid input for addRemove of ./addRemoveUserFromEventHandler");
      }


    } catch (Exception ex) {
      System.err.println("ERROR: An error occurred posting calendar event. Printing stack trace:");
      ex.printStackTrace();
    }
    return "";
  }

}
