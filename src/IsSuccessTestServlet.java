//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

@WebServlet({"/s4a65d4a6s5d4a6sdw99"})
public class IsSuccessTestServlet extends HttpServlet {
    String result = "";

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        try {
            while (true) {
                this.httpDemo();
                out.println(this.result);
                System.out.println(this.result);
                Thread.sleep(1000);
            }
        } catch (InterruptedException var5) {
            var5.printStackTrace();
        }
    }

    public void destroy() {
        super.destroy();
    }

    public void httpDemo() throws UnsupportedEncodingException {
        String local = URLEncoder.encode("珠海市香洲区唐家湾镇哈工大路一号学子花园A座", "UTf-8");
        String sign_x = "113.88987";
        String sign_y = "22.984650";
        String requestUrl = "http://xl.gupt.net/qd/index.php/home/sign/sign";
        String takeUrl = "http://xl.gupt.net/qd/index.php/home/sign/check_area/sign_x/" + sign_x + "/sign_y/" + sign_y + "/sign_area/" + local;
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(requestUrl);
        httpGet.setHeader("Cookie", "PHPSESSID=g8rp23kvk8jtjk6cebs4irgrj9");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Linux; Android 5.1.1; OPPO R9s Build/LMY47V) AppleWebKit/537.36 (KHTML, like Gecko)Version/4.0 Chrome/30.0.0.0 Mobile Safari/537.36 MicroMessenger/6.6.7.1321(0x26060737) NetType/WIFI Language/zh_CN");
        httpGet.setHeader("X-Requested-With", "com.tencent.mm");
        httpGet.setHeader("Referer", takeUrl);

        try {
            CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet);
            String s = EntityUtils.toString(closeableHttpResponse.getEntity(), "utf-8");
            this.isSuccess(s);
        } catch (IOException var18) {
            var18.printStackTrace();
        } finally {
            try {
                closeableHttpClient.close();
            } catch (IOException var17) {
                var17.printStackTrace();
            }

        }

    }

    public void isSuccess(String html) {
        Pattern pattern = Pattern.compile("<label for=\"bind\" style=\"font-size:large;\">(.+?)</label>");

        for (Matcher matcher = pattern.matcher(html);
             matcher.find();
             this.result = matcher.group().replaceAll("<.*?>", "")) {
            ;
        }

    }
}
