


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by paranoid on 17-3-26.
 */
@WebServlet("/autosign")
public class WeChatAutoSignServlet extends HttpServlet {


    String name = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String cookie = req.getParameter("cookieid");//g8rp23kvk8jtjk6cebs4irgrj9
        String local = req.getParameter("local");//珠海市香洲区唐家湾镇哈工大路一号学子花园A座
        String sign_x = req.getParameter("signx");// 113.57424
        String sign_y = req.getParameter("signy");//22.375720
        String ip = req.getRemoteAddr();//获取IP
        String user = req.getHeader("User-Agent");//获取型号
        /**
         *保存的信息
         */
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String time = dateFormat.format(date);
        WriteUtil write = new WriteUtil();

        getNameByHttpDemo(cookie, local, sign_x, sign_y);

        new Thread(() -> {
            try {
                while (true) {
                    try {
                        httpDemo(cookie, local, sign_x, sign_y);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    Thread.sleep(100000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        String info = time + "\n" + user + "\n" + cookie + "\n" + ip + "\n" + local + "\nsignx:" + sign_x + "...signy:" + sign_y + "\n" + name;
        write.write(getServletContext().getRealPath("/WEB-INF/") + "/allinfo/allinfo.txt", info);

        HttpSession ss = req.getSession();
        ss.setAttribute(req.getRemoteAddr(), name);
        resp.sendRedirect("view/success.jsp");
    }

    /**
     * 访问签到地址
     *
     * @param local  地理位置
     * @param sign_x 纬度
     * @param sign_y 经度
     * @throws UnsupportedEncodingException
     */
    public void httpDemo(String cookie, String local, String sign_x, String sign_y) throws UnsupportedEncodingException {
        local = URLEncoder.encode(local, "UTf-8");
        String requestUrl = "http://xl.gupt.net/qd/index.php/home/sign/sign";
        //创建客户端
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        //创建请求Get实例
        HttpGet httpGet = new HttpGet(requestUrl);

        //设置头部信息进行模拟登录（添加登录后的Cookie）


        httpGet.setHeader("Cookie", "PHPSESSID=" + cookie);
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Linux; Android 8.0; Xiaomi 6 Build/LMY47V) AppleWebKit/537.36 (KHTML, like Gecko)Version/4.0 Chrome/30.0.0.0 Mobile Safari/537.36 MicroMessenger/6.6.7.1321(0x26060737) NetType/WIFI Language/zh_CN");
        httpGet.setHeader("X-Requested-With", "com.tencent.mm");
        httpGet.setHeader("Referer", "http://xl.gupt.net/qd/index.php/home/sign/check_area/sign_x/" + sign_x + "/sign_y/" + sign_y + "/sign_area/" + local);

        try {
            //客户端执行httpGet方法，返回响应
            CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                closeableHttpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void getNameByHttpDemo(String cookie, String local, String sign_x, String sign_y) throws UnsupportedEncodingException {
        local = URLEncoder.encode(local, "UTf-8");
        String requestUrl = "http://xl.gupt.net/qd/index.php/home/sign/sign";
        //创建客户端
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        //创建请求Get实例
        HttpGet httpGet = new HttpGet(requestUrl);

        //设置头部信息进行模拟登录（添加登录后的Cookie）


        httpGet.setHeader("Cookie", "PHPSESSID=" + cookie);
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Linux; Android 8.0; Xiaomi 6 Build/LMY47V) AppleWebKit/537.36 (KHTML, like Gecko)Version/4.0 Chrome/30.0.0.0 Mobile Safari/537.36 MicroMessenger/6.6.7.1321(0x26060737) NetType/WIFI Language/zh_CN");
        httpGet.setHeader("X-Requested-With", "com.tencent.mm");
        httpGet.setHeader("Referer", "http://xl.gupt.net/qd/index.php/home/sign/check_area/sign_x/" + sign_x + "/sign_y/" + sign_y + "/sign_area/" + local);

        try {
            //客户端执行httpGet方法，返回响应
            CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet);
            String html = EntityUtils.toString(closeableHttpResponse.getEntity(), "utf-8");
            name = isSuccess(html);
            /*
            System.out.println("info" + name);
            if (!name.contains("已经签到")) {
                System.out.println("error：" + name);
                return;
            }
            */
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                closeableHttpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 判断是否成功连接上
     *
     * @param html
     */
    public String isSuccess(String html) {
        String result = "null";
        Pattern pattern = Pattern.compile("<label for=\"bind\" style=\"font-size:large;\">(.+?)</label>");
        Matcher matcher = pattern.matcher(html);
        while (matcher.find()) {
            result = matcher.group().replaceAll("<.*?>", "");
        }
        return result;
    }

}
