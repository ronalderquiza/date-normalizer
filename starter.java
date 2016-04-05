/**
 * Created by Ronald Erquiza on 4/5/2016.
 */

import java.util.regex.*;
import java.util.Scanner;

public class starter{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String s = "fsfff";
        System.out.print("INPUT: ");
        String string = in.nextLine();
        string = " "+string;
        String pattern = "([\\d]{1,2})?" +
                "((st|nd|rd|th)\\s*of\\s*)?" +
                "(Jan(uary)?|Feb(ruary)?|Mar(ch)?|Apr(il)?|May|Jun(e)?|" +
                "Jul(y)?|Aug(ust)?|Sep(t(ember)?)?|Oct(ober)?|Nov(ember)?|Dec(ember)?)" +
                "(\\s*([\\d]{4}))?" +
                "(\\s*([\\d]{1,2}))?" +
                "(,?\\s*([\\d]{4}))?" +
                "|(\\s*([\\d]{4}))";
        String answer = "null";
        String month = "null";
        String date = "null";
        String year = "null";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(string);
            if (m.find()) {
                date = m.group(1)+"";
                if(date.equals("null"))
                    date = m.group(20)+"";
                month = m.group(4)+"";
                if(!month.equals("null"))
                    month = getMonth(month.toUpperCase().substring(0,3))+"";

                year = m.group(18)+"";
                if(year.equals("null")) {
                    year = m.group(22)+"";
                }
                if(year.equals("null")){
                    year = m.group(24)+"";
                }
            }

        if(!date.equals("null")&&!month.equals("null")) {
            answer = date + "_";
        }
        if(year.equals("null")&&!date.equals("null")&&!month.equals("null")){
            answer =  answer+month;
        }
        if(!month.equals("null")&&!year.equals("null")&&date.equals("null")){
            answer = month + "_" + year;
        }
        if(!month.equals("null")&&!year.equals("null")&&!date.equals("null")){
            answer = answer + month + "_" + year;
        }
        if(!year.equals("null")&&date.equals("null")&&month.equals("null")){
            answer =  year;
        }
        System.out.println("OUTPUT: " + string.substring(0,m.start())+ answer +string.substring(m.end(),string.length()));

    }


    public static String getMonth(String mon){
        String month = new String();
        if(mon.equals("JAN")){
            month =  "JANUARY";
        }
        else if(mon.equals("FEB")){
            month =  "FEBRUARY";
        }
        else if(mon.equals("MAR")){
            month =  "MARCH";
        }
        else if(mon.equals("APR")){
            month =  "APRIL";
        }
        else if(mon.equals("MAY")){
            month =  "MAY";
        }
        else if(mon.equals("JUN")){
            month =  "JUNE";
        }
        else if(mon.equals("JUL")){
            month =  "JULY";
        }
        else if(mon.equals("AUG")){
            month =  "AUGUST";
        }
        else if(mon.equals("SEP")){
            month =  "SEPTEMBER";
        }
        else if(mon.equals("OCT")){
            month =  "OCTOBER";
        }
        else if(mon.equals("NOV")){
            month =  "NOVEMBER";
        }
        else if(mon.equals("DEC")){
            month = "DECEMBER";
        }

        return month;

    }
}