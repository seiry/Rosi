import com.alibaba.fastjson.JSON;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by qtfreet00 on 2016/11/1.
 */
public class PaChong {
    public static void main(String[] args) throws IOException {
        Document pages = get("http://www.rosi365.com/rosi/rosi");
        Element first = pages.getElementsByClass("last").first();
        String getPage = first.attr("href");
        int count = Integer.valueOf(getPage.replace("http://www.rosi365.com/rosi/rosi/page/", ""));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd H:m:s");
        Date date = new Date();
        Pattern p = Pattern.compile("[a-zA-z]+://[^\\s]*");
        RosiBean rosibean = new RosiBean();
        rosibean.setName("rosi套图");
        rosibean.setTime(format.format(date));
        List<RosiBean.DataBean> list = new ArrayList<>();
        for (int i = 1; i < count + 1; i++) {
            System.out.println("第" + i + "轮开始");
            Document document = get("http://www.rosi365.com/rosi/rosi/page/" + i);
            Elements select = document.select("div#loop");
            Elements postimg = select.select("div.postimg");
            for (Element e : postimg) {
                Matcher img = p.matcher(e.attr("style"));
                Matcher url = p.matcher(e.html());
                String title = e.getElementsByClass("png").last().html();
                if (img.find() && url.find() && !title.isEmpty()) {
                    RosiBean.DataBean data = new RosiBean.DataBean();
                    String postImg = img.group(0).replace("')", "");
                    String postUrl = url.group(0).replace("\"", "");
                    Document imgs = get(postUrl);
                    if (title.startsWith("No.")) {
                        data.setType("0");
                        String urls = imgs.select("div#forumcode").html().replace("[/img]", "").replace("[img]", ",").replace(postUrl, "").trim();
                        int index = urls.indexOf("http");
                        if (index != -1) {
                            data.setDownload(urls.substring(index));
                        } else {
                            data.setDownload("null");
                        }
                    } else if (title.startsWith("VNO.")) {
                        data.setType("1");
                        data.setDownload(imgs.select("div.part").select("a").last().attr("href"));
                    } else if (title.startsWith("QINGQU.")) {
                        data.setType("2");
                        String urls = imgs.select("div#forumcode").html().replace("[/img]", "").replace("[img]", ",").replace(postUrl, "").trim();
                        int index = urls.indexOf("http");
                        if (index != -1) {
                            data.setDownload(urls.substring(index));
                        } else {
                            data.setDownload("null");
                        }
                    } else if (title.startsWith("QQNO.")) {
                        data.setType("3");
                        String urls = imgs.select("div#forumcode").html().replace("[/img]", "").replace("[img]", ",").replace(postUrl, "").trim();
                        int index = urls.indexOf("http");
                        if (index != -1) {
                            data.setDownload(urls.substring(index));
                        } else {
                            data.setDownload("null");
                        }
                    }
                    data.setPostimg(postImg);
                    data.setUrl(postUrl);
                    data.setTitle(title);
                    list.add(data);
                } else {
                    System.out.println("失败了");
                }
            }
        }
        rosibean.setData(list);
        String s = JSON.toJSONString(rosibean, true);
        File f = new File("D:/2222.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        bw.write(s);
        bw.close();
    }

    private static Document get(String url) {
        SocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 1080);
        Proxy proxy = new Proxy(Proxy.Type.SOCKS, socketAddress);

        try {
            Document pages = Jsoup.connect(url).timeout(20000).proxy(proxy).get();
            return pages;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
