package main.java;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.client.util.DateTime;

import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Creates event in Google Calendar
public class CalendarQuickstart {
    /** Application name. */
    private static final String APPLICATION_NAME =
        "Google Calendar API Java Quickstart";

    /** Directory to store user credentials for this application. */
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
        System.getProperty("user.home"), ".credentials/calendar-java-quickstart.json");

    /** Global instance of the {@link FileDataStoreFactory}. */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY =
        JacksonFactory.getDefaultInstance();

    /** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT;

    /** Global instance of the scopes required by this quickstart.
     *
     * If modifying these scopes, delete your previously saved credentials
     * at ~/.credentials/calendar-java-quickstart.json
     */
    private static final List<String> SCOPES =
        Arrays.asList(CalendarScopes.CALENDAR);

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Creates an authorized Credential object.
     * @return an authorized Credential object.
     * @throws IOException
     */
    public static Credential authorize() throws IOException {
        // Load client secrets.
        InputStream in =
            CalendarQuickstart.class.getResourceAsStream("/main/resources/client_secret.json");
        System.out.println(in);
        GoogleClientSecrets clientSecrets =
            GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(DATA_STORE_FACTORY)
                .setAccessType("offline")
                .build();
        Credential credential = new AuthorizationCodeInstalledApp(
            flow, new LocalServerReceiver()).authorize("user");
        System.out.println(
                "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }

    /**
     * Build and return an authorized Calendar client service.
     * @return an authorized Calendar client service
     * @throws IOException
     */
    public static com.google.api.services.calendar.Calendar
        getCalendarService() throws IOException {
        Credential credential = authorize();
        return new com.google.api.services.calendar.Calendar.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public static void main(String[] args) throws IOException {
        // Build a new authorized API client service.
        // Note: Do not confuse this class with the
        //   com.google.api.services.calendar.model.Calendar class.
        com.google.api.services.calendar.Calendar service =
            getCalendarService();
        
        // List the next 10 events from the primary calendar.
//        DateTime now = new DateTime(System.currentTimeMillis());
//        Events events = service.events().list("primary")
//            .setMaxResults(10)
//            .setTimeMin(now)
//            .setOrderBy("startTime")
//            .setSingleEvents(true)
//            .execute();
//        List<Event> items = events.getItems();
//        if (items.size() == 0) {
//            System.out.println("No upcoming events found.");
//        } else {
//            System.out.println("Upcoming events");
//            for (Event event : items) {
//                DateTime start = event.getStart().getDateTime();
//                if (start == null) {
//                    start = event.getStart().getDate();
//                }
//                System.out.printf("%s (%s)\n", event.getSummary(), start);
//            }
//        }
//        Event event = new Event()
//        	    .setSummary("Google I/O 2015")
//        	    .setLocation("800 Howard St., San Francisco, CA 94103")
//        	    .setDescription("A chance to hear more about Google's developer products.");
//
//        	DateTime startDateTime = new DateTime("2016-06-14T09:00:00-07:00");
//        	EventDateTime start = new EventDateTime()
//        	    .setDateTime(startDateTime)
//        	    .setTimeZone("America/Los_Angeles");
//        	event.setStart(start);
//
//        	DateTime endDateTime = new DateTime("2016-06-17T17:00:00-07:00");
//        	EventDateTime end = new EventDateTime()
//        	    .setDateTime(endDateTime)
//        	    .setTimeZone("America/Los_Angeles");
//        	event.setEnd(end);
//
//        	String[] recurrence = new String[] {"RRULE:FREQ=DAILY;COUNT=2"};
//        	event.setRecurrence(Arrays.asList(recurrence));
//
//        	EventAttendee[] attendees = new EventAttendee[] {
//        	    new EventAttendee().setEmail("lpage@example.com"),
//        	    new EventAttendee().setEmail("sbrin@example.com"),
//        	};
//        	event.setAttendees(Arrays.asList(attendees));
//
//        	EventReminder[] reminderOverrides = new EventReminder[] {
//        	    new EventReminder().setMethod("email").setMinutes(24 * 60),
//        	    new EventReminder().setMethod("popup").setMinutes(10),
//        	};
//        	Event.Reminders reminders = new Event.Reminders()
//        	    .setUseDefault(false)
//        	    .setOverrides(Arrays.asList(reminderOverrides));
//        	event.setReminders(reminders);
//
//        	String calendarId = "primary";
//        	event = service.events().insert(calendarId, event).execute();
//        	System.out.printf("Event created: %s\n", event.getHtmlLink());
        
    }
    public static void createEvent(DateTime first, DateTime second) throws IOException{
    	com.google.api.services.calendar.Calendar service =
                getCalendarService();
    	Event event = new Event().setSummary("Töö");
		DateTime startDateTime = first;
		EventDateTime start = new EventDateTime()
        	    .setDateTime(startDateTime)
        	    .setTimeZone("Europe/Tallinn");
        	event.setStart(start);
//		DateTime endDateTime = second;
		DateTime incrementedendDateTime = incrementEndDate(second);
		EventDateTime end = new EventDateTime()
        	    .setDateTime(incrementedendDateTime)
        	    .setTimeZone("Europe/Tallinn");
        	event.setEnd(end);
        	
        String calendarId = "primary";
        event = service.events().insert(calendarId, event).execute();
        System.out.printf("URL for created event: %s\n", event.getHtmlLink());
    }

    //Time table only shows start hour. This will increment end date to display it correctly in the calendar.
	private static DateTime incrementEndDate(DateTime second) {
		String[] newSecond = second.toString().split("T");
		String[] newSecondHour = newSecond[1].split(":");
		String day = newSecond[0];
		String hour = String.valueOf(Integer.parseInt(newSecondHour[0])+1);
		if(hour.length() == 1){
			hour = "0" + hour;
		}
		DateTime newEndDate = new DateTime(day + "T" + hour + ":00:00.000+03:00");
		return newEndDate;
	}

}
