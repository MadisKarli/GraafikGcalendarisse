package main.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import com.google.api.client.util.DateTime;

//Class that takes time table from input string and passes it to Google Calendar API
public class Inputparser {
	public static void parseInput(String input, String name) throws IOException{
		
		String[] lines = removeTabsAndEmptyLines(input);
		
		ArrayList<String> dates =  new ArrayList<String>();
		for(int i=0; i<7; i++){
//			System.out.println(lines[i]);
			dates.add(lines[i]);
		}
		
		ArrayList<String> dtdates = parseDates(dates);
		System.out.println("Dates done!");
		ArrayList<String> times = getTimes(Arrays.copyOfRange(lines, 15, lines.length));
		System.out.println("Times done!");
		ArrayList<DateTime> schedule = getSchedule(Arrays.copyOfRange(lines, 15, lines.length), times, dtdates, name);
		System.out.println("Schedule compiled!");
		Collections.sort(schedule, new Comparator<DateTime>() {
		    public int compare(DateTime r1, DateTime r2) {
				if(r1.getValue() > r2.getValue()){
					return 1;
				}
				else return -1;
		    }
		});
		
		createEvents(schedule);
	}
	
	//Removes lines that contain are only tabs or completely empty
	private static String[] removeTabsAndEmptyLines(String input) {
		String[] lines = input.split("\n");
		ArrayList<String> out = new ArrayList<String>();
		for(String line: Arrays.copyOfRange(lines, 2, lines.length)){
			if(!(line.equals("	") || line.equals(""))){
				out.add(line);
			}
		}
		return out.toArray(new String[out.size()]);
	}
	
	//Finds all hours from time table
	private static ArrayList<String> getTimes(String[] input) {
		ArrayList<String> out = new ArrayList<String>();
		for(int i = 0; i < input.length; i++){
			if(input[i].contains(":00")){
				out.add(input[i]);
			}
		}
		return out;
	}

	//Takes dates from the first line of time table
	private static ArrayList<String> parseDates(ArrayList<String> input){
		
		ArrayList<String> out = new ArrayList<String>();
		
		for(String line: input){
//			System.out.println("line" + line);
			String[] parts = line.split(". ");
			String dd = parts[0];
			int ddlen = dd.length();
			if(ddlen == 1) dd = "0" + dd;
//			for(String e: parts) System.out.println(e);
			String mm = getmonthnumber(parts[1]);
			String yy = parts[2];
			out.add(yy+"-"+mm+"-"+dd);
		}
		return out;
	}

	//Finds name from time table and returns DateTimes when name has to be at work
	private static ArrayList<DateTime>  getSchedule(String[] input, ArrayList<String> hour, ArrayList<String> day, String name){
		ArrayList<DateTime> out = new ArrayList<DateTime>();
		for(int i = 0; i < input.length; i++){
			if(input[i].contains(name)){
				int a = i%8;
//				System.out.println(input[i]);
//				System.out.println(hour.get(i/8));
//				System.out.println(day.get(a-1));
				out.add(new DateTime(day.get(a-1) + "T"+ hour.get(i/8) + ":00.000+03:00"));
				
//				try{
//					out.add(new DateTime(day.get(a-1) + "T"+ hour.get((i/8)+1) + ":00.000+03:00"));
//				}catch(IndexOutOfBoundsException e){
//					
//				}
//				
//				System.out.println(out.get(out.size()-1));
				/*Super complicated math stuff:
				 * i%8-1 is the index of date as there are 8 lines per hour BUT the first is always hour string -> different by one
				 * i/8 is the hour as there are 8 lines per hour in time table - one for every day + the hour
				 * here the time zone does not matter but without it google calenar api behaved strangely
				 */
			}
		}
		return out;
	}
	
	//Gets month number from monthstring as months in time table are in ther respective name and not number
	private static String getmonthnumber(String in){
		if(in.contains("jaan")){
			return "01";
		}
		else if(in.contains("veeb")){
			return "02";
		}
		else if(in.contains("märts")){
			return "03";
		}
		else if(in.contains("apr")){
			return "04";
		}
		else if(in.contains("mai")){
			return "05";
		}
		else if(in.contains("juuni")){
			return "06";
		}
		else if(in.contains("juuli")){
			return "07";
		}
		else if(in.contains("aug")){
			return "08";
		}
		else if(in.contains("sept")){
			return "09";
		}
		else if(in.contains("okt")){
			return "10";
		}
		else if(in.contains("nov")){
			return "11";
		}
		else if(in.contains("dets")){
			return "12";
		}
		else{
			return "-1";
		}
	}

	
	private static void createEvents(ArrayList<DateTime> input) throws IOException{
		DateTime first = input.get(0);
		for(int i = 1; i < input.size(); i++){
			if(input.get(i).getValue() - input.get(i-1).getValue() == 3600000){
				
			}else{
				DateTime second = input.get(i-1);
				System.out.println("Creating event from " + first + " to " + second);
				CalendarQuickstart.createEvent(first, second);
				first = input.get(i);
			}
		}
		DateTime second = input.get(input.size()-1);
		CalendarQuickstart.createEvent(first, second);

	}

}
