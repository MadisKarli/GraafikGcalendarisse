package main.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import com.google.api.client.util.DateTime;

public class Inputparser {
	public static void parseInput(String input) throws IOException{
		
		String[] lines = input.split("\n");
		
		ArrayList<String> dates =  new ArrayList<String>();
		for(int i=0; i<7; i++){
//			System.out.println(lines[i]);
			dates.add(lines[i]);
		}
		
		ArrayList<String> dtdates = parseDates(dates);
		ArrayList<String> times = getTimes(Arrays.copyOfRange(lines, 15, lines.length));
		ArrayList<DateTime> schedule = getSchedule(Arrays.copyOfRange(lines, 15, lines.length), times, dtdates);
		
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
	
	
	private static ArrayList<String> getTimes(String[] input) {
		ArrayList<String> out = new ArrayList<String>();
		for(int i = 0; i < input.length; i++){
			if(input[i].contains(":00")){
				out.add(input[i]);
			}
		}
		return out;
	}

	private static ArrayList<String> parseDates(ArrayList<String> input){
		ArrayList<String> out = new ArrayList<String>();
		for(String line: input){
			String[] parts = line.split(". ");
			String dd = parts[0];
			int ddlen = dd.length();
			if(ddlen == 1) dd = "0" + dd;
			String mm = getmonthnumber(parts[1]);
			String yy = parts[2];
			out.add(yy+"-"+mm+"-"+dd);
		}
		return out;
	}

	private static ArrayList<DateTime>  getSchedule(String[] input, ArrayList<String> hour, ArrayList<String> day){
		ArrayList<DateTime> out = new ArrayList<DateTime>();
		for(int i = 0; i < input.length; i++){
			if(input[i].contains("Madis")){
				int a = i%8;
//				System.out.println(input[i]);
//				
//				System.out.println(hour.get(i/8));
//				System.out.println(day.get(a-1));
				out.add(new DateTime(day.get(a-1) + "T"+ hour.get(i/8) + ":00+03:00"));
			}
		}
		return out;
	}
	private static String getmonthnumber(String in){
		if(in.equals("jaanuar")){
			return "01";
		}
		else if(in.equals("veebruar")){
			return "02";
		}
		else if(in.equals("märts")){
			return "03";
		}
		else if(in.equals("aprill")){
			return "04";
		}
		else if(in.equals("mai")){
			return "05";
		}
		else if(in.equals("juuni")){
			return "06";
		}
		else if(in.equals("juuli")){
			return "07";
		}
		else if(in.equals("august")){
			return "08";
		}
		else if(in.equals("september")){
			return "09";
		}
		else if(in.equals("oktoober")){
			return "10";
		}
		else if(in.equals("november")){
			return "11";
		}
		else if(in.equals("detsember")){
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
				CalendarQuickstart.createEvent(first, second);
				first = input.get(i);
			}
		}
		DateTime second = input.get(input.size()-1);
		CalendarQuickstart.createEvent(first, second);

	}

}
