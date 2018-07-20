import javax.swing.text.Document;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.jsoup.Jsoup;

public class GatherMain {

    public static void main(String[] args) throws IOException {
        DateFormat df = new SimpleDateFormat("(MM月dd日 HH:mm)yyyy");
        Document document = Jsoup.connect("http://roll.mil.news.sina.com.cn/col/zgjq/index.shtml").get();


//        Document document = Jsoup.connect("http://roll.mil.news.sina.com.cn/col/zgjq/index.shtml").get();
    }
}
