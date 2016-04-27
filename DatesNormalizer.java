import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ronald Erquiza on 4/7/2016.
 */
public class DatesNormalizer {
    private String newContent;
    final String REGEX_PATTERN = "((Jan(uary)?|Feb(ruary)?|Mar(ch)?|Apr(il)?|May|Jun(e)?" + //Month (Jan-Jun)
            "|Jul(y)?|Aug(ust)?|Sep(t(ember)?)?|Oct(ober)?|Nov(ember)?|Dec(ember)?)" + //Month (Jul-Dec)
            "(\\b\\s*([\\d]{1,2}(st|nd|rd|th)?\\b))?" + //Date
            "(,?\\s*([\\d]{4}))?)|" +                   //Year
            "(\\b([\\d]{1,2})?((st|nd|rd|th)\\s*of\\s*)?" + //Date (first) of
            "(Jan(uary)?|Feb(ruary)?|Mar(ch)?|Apr(il)?|May|Jun(e)?" + //Month (Jan-Jun)
            "|Jul(y)?|Aug(ust)?|Sep(t(ember)?)?|Oct(ober)?|Nov(ember)?|Dec(ember)?)" + //Month (Jul-Dec)
            "(\\s*([\\d]{4})))";                        //Year

    DatesNormalizer() {

    }

    public String normalize(String content) {
        return replaceString(content);
    }

    private String replaceString(String content) {
        Pattern r = Pattern.compile(REGEX_PATTERN);
        Matcher m = r.matcher(content);
        String wholeDate = new String();
        String dateFormat = new String();
        if (m.find()) {
            if (m.group(20) != null) {
                wholeDate = m.group(20);
                dateFormat = dateAssignment(m.group(21),getMonth(m.group(24).toUpperCase().substring(0, 3)),m.group(38));
            } else if (m.group(1) != null) {
                wholeDate = m.group(1);
                dateFormat = dateAssignment(m.group(16),getMonth(m.group(2).toUpperCase().substring(0, 3)),m.group(19));
            }
            newContent = replaceDate(wholeDate, content, dateFormat);
            replaceString(newContent);
        }
        return newContent;
    }

    private String dateAssignment(String date, String month, String year){
        return dateFormatting(year, month, date);
    }
    private String getMonth(String mon) {
        String month = "";
        switch (mon) {
            case "JAN":
                month = "JANUARY";
                break;
            case "FEB":
                month = "FEBRUARY";
                break;
            case "MAR":
                month = "MARCH";
                break;
            case "APR":
                month = "APRIL";
                break;
            case "MAY":
                month = "MAY";
                break;
            case "JUN":
                month = "JUNE";
                break;
            case "JUL":
                month = "JULY";
                break;
            case "AUG":
                month = "AUGUST";
                break;
            case "SEP":
                month = "SEPTEMBER";
                break;
            case "OCT":
                month = "OCTOBER";
                break;
            case "NOV":
                month = "NOVEMBER";
                break;
            case "DEC":
                month = "DECEMBER";
                break;
        }

        return month;
    }

    private String dateFormatting(String year, String month, String date) {
        if (month != null && year != null && date == null) {
            return year + "_" + month;
        }
        if (year == null && date != null && month != null) {
            return month + "_" + date;
        }
        if (year != null && date != null && month != null) {
            return year + "_" + month + "_" + date;
        }
        return "";
    }

    private String replaceDate(String datePattern, String content, String date) {
        Pattern r = Pattern.compile(datePattern);
        Matcher m = r.matcher(content);
        if (m.find()) {
            return content.substring(0, m.start()) + date + content.substring(m.end(), content.length());
        }
        return content;
    }
}
