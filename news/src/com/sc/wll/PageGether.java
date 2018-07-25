package com.sc.wll;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PageGether implements Runnable{
    private final int page;

    public PageGether(int page) {
        this.page = page;
    }

    @Override
    public void run() {
        try {
            gatherPage(this.page);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void gatherPage(int page) throws IOException, ParseException, SQLException {
        System.out.println(String.format("正在采集第%d页...",page));
        Database database = new Database();
        // "http://www.cnetnews.com.cn/list-0-0-330726-1-"+page+"-0.htm"
        Document document = Jsoup.connect("http://news.csdn.net/news/"+page).get();
        Elements lis = document.select(".news .unit");  // ul li
//        System.out.println("lalallal:" + lis);
        for (Element e : lis) {
            Article article = getArticle(e);
            int rowcount = database.insertArticle(article);
            System.out.println(article.getUrl()+" "+rowcount);
        }
    }

    private static Article getArticle(Element e) throws ParseException, IOException {
        DateFormat df1 = new SimpleDateFormat("(MM月dd日 HH:mm)yyyy");
        DateFormat df2 = new SimpleDateFormat("(yyyy年MM月dd日 HH:mm)");
        Article article = new Article();
//        article.setTitle(e.select("a").attr("title"));                      // li 里的a
        article.setTitle(e.select("h1").select("a").text());                      // li 里的a
        article.setUrl(e.select("h1").select("a").attr("href"));
        String url = e.select("h1").select("a").attr("href");
        String dateStr = e.select("h4").select(".ago").text();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date publishTime = format.parse(dateStr);
        article.setPublishTime(publishTime);
        article.setContent(getContent(article.getUrl()));
        return article;
    }

    private static String getContent(String url) throws IOException {
        Document contentDocument = Jsoup.connect(url).get();
        Elements ps = contentDocument.select(".news_content");
        String content = "";
        for (Element ce : ps) {
            content += ce.text() + "\n\n";
        }
        return content;
    }
}
