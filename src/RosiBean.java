import java.util.List;

/**
 * Created by qtfreet00 on 2016/11/1.
 */
public class RosiBean {

    /**
     * name : rosi解析
     * time : 2016-11-1
     * data : [{"postimg":"http://www.rosi365.com/wp-content/themes/wmpic1/timthumb.php?src=http://www.rosi365.com/tu/NO.1759/d.jpg&h=226&w=340&zc=1","url":"http://www.rosi365.com/5165.html"},{"postimg":"http://www.rosi365.com/wp-content/themes/wmpic1/timthumb.php?src=http://www.rosi365.com/tu/NO.1758/d.jpg&h=226&w=340&zc=1","url":"http://www.rosi365.com/5164.html"},{"postimg":"http://www.rosi365.com/wp-content/themes/wmpic1/timthumb.php?src=http://www.rosi365.com/tu/NO.1757/d.jpg&h=226&w=340&zc=1","url":"http://www.rosi365.com/5163.html"}]
     */

    private String name;
    private String time;
    /**
     * postimg : http://www.rosi365.com/wp-content/themes/wmpic1/timthumb.php?src=http://www.rosi365.com/tu/NO.1759/d.jpg&h=226&w=340&zc=1
     * url : http://www.rosi365.com/5165.html
     */

    private List<DataBean> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String postimg;
        private String url;
        private String title;
        private String type;
        private String download;

        public String getPostimg() {
            return postimg;
        }

        public void setPostimg(String postimg) {
            this.postimg = postimg;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDownload() {
            return download;
        }

        public void setDownload(String download) {
            this.download = download;
        }
    }
}
