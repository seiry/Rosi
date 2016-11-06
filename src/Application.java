import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;

/**
 * Created by qtfreet on 2016/11/3.
 */
public class Application {

    public static Document get(String url) {
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
