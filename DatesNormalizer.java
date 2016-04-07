import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ronald Erquiza on 4/7/2016.
 */
public class DatesNormalizer {
    String newString = new String();

    public DatesNormalizer(){
        newString = "";
    }
    public void normalize(String string){
        String pattern = "([\\d]{1,2})?" +
                "((st|nd|rd|th)\\s*of\\s*)?" +
                "(Jan(uary)?|Feb(ruary)?|Mar(ch)?|Apr(il)?|May|Jun(e)?|Jul(y)?" +
                "|Aug(ust)?|Sep(t(ember)?)?|Oct(ober)?|Nov(ember)?|Dec(ember)?)" +
                "(\\s*(([\\d]{1,2})" +
                "(st|nd|rd|th)?)[^a-z0-9])?" +
                "(,?\\s*([\\d]{4}))?" +
                "|(\\s*([\\d]{4}))";
        String month = "null";
        String date = "null";
        String year = "null";
        boolean changed = false;
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(string);
        if (m.find()) {
            date = m.group(1)+"";
            if(date.equals("null"))
                date = m.group(19)+"";
            month = m.group(4)+"";
            if(!month.equals("null"))
                month = getMonth(month.toUpperCase().substring(0,3))+"";

            year = m.group(22)+"";
            if(year.equals("null")){
                year = m.group(24)+"";
            }
        }



        if(!year.equals("null")&&!date.equals("null")&&!month.equals("null")){
            newString =  year+"_"+month+"_"+date;
            changed = true;
        }
        if(year.equals("null")&&!date.equals("null")&&!month.equals("null")) {
            newString = month+"_"+ date;
            changed = true;
        }
        if(!month.equals("null")&&!year.equals("null")&&date.equals("null")){
            newString = year + "_" + month;
            changed = true;
        }
        if(!month.equals("null")&&year.equals("null")&&!date.equals("null")){
            newString = month + "_" + date;
            changed = true;
        }
        if(changed){
            newString = string.substring(0,m.start())+ newString +string.substring(m.end(),string.length());
        }
        else{
            newString = string;
        }
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

    public String getText(){
        return newString;
    }

}
