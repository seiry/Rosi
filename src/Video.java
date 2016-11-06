import com.alibaba.fastjson.JSON;

import java.io.*;
import java.net.*;
import java.util.List;

/**
 * Created by qtfreet on 2016/11/3.
 */
public class Video {
    public static void main(String[] args) throws IOException {
        File f = new File("D:/rosi.txt");

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
           // System.out.println(d.getTitle());
            if (type!=null&&type.equals("1")) {
                System.out.println(d.getDownload());
            }


        }
    }
}

