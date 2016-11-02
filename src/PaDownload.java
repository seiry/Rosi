import com.alibaba.fastjson.JSON;

import java.io.*;
import java.net.*;
import java.util.List;

/**
 * Created by qtfreet on 2016/11/2.
 */
public class PaDownload {
    public static void main(String[] args) throws IOException {
        File f = new File("D:/1111.txt");

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
        StringBuilder sb = new StringBuilder();
        String line = "";
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        RosiBean rosiBean = JSON.parseObject(sb.toString(), RosiBean.class);
        List<RosiBean.DataBean> data = rosiBean.getData();
        for (RosiBean.DataBean d : data) {
            String type = d.getType();
            System.out.println(type);
            System.out.println(d.getTitle());
            if (type == null || type.equals("1")) {
                continue;
            }
            if (d.getDownload().equals("null")) {
                continue;
            }
            //判断当前类型是否为图片，不是图片就先不下载，否则影响效率
            String[] urls = d.getDownload().split(",");
            String title = d.getTitle();
            for (String url : urls) {
                int index = url.lastIndexOf("/");
                String name = url.substring(index + 1);
                System.out.println(url);
                //设置代理服务器，不翻墙爬不到数据
                SocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 1080);
                Proxy proxy = new Proxy(Proxy.Type.SOCKS, socketAddress);
                URLConnection u = new URL(url).openConnection(proxy);
                DataInputStream dis = new DataInputStream(u.getInputStream());
                String path = "D:/rosi/" + title;
                File fir = new File(path);
                if (!fir.exists()) {
                    fir.mkdir();
                }
                //判断文件夹是否存在，不存在则创建一个
                String newImageName = fir.getAbsolutePath() + "/" + name;
                // 建立一个新的文件
                FileOutputStream fos = new FileOutputStream(new File(newImageName));
                byte[] buffer = new byte[1024];
                int length;
                // 开始填充数据
                while ((length = dis.read(buffer)) > 0) {
                    fos.write(buffer, 0, length);
                }
                dis.close();
                fos.close();

            }
        }
    }
}
