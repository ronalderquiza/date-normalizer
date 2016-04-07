/**
 * Created by Ronald Erquiza on 4/7/2016.
 */
public class Testing {
    public static void main(String[] args){
        DatesNormalizer normalizer = new DatesNormalizer();
        normalizer.normalize("Happy Independence day! 12th of July 1867");
        System.out.println(normalizer.getText());
    }
}
