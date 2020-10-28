import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author rd-yyx
 * @version 1.0
 * @date 2020/10/27 3:52 下午
 */
public class HttpTest {
    public static void main(String[] args) throws IOException {
//        testGet("http://localhost:8801");
//        OkHttpClient client = new OkHttpClient();
//        try {
//            Request request = new Request.Builder().url("http://localhost:8801/").build();
//            Response response = client.newCall(request).execute();
//            byte[] bytes = response.body().bytes();
//            System.out.println(new String(bytes, "UTF-8"));
//        } finally {
//            client.clone();
//        }
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //2.声明get请求
        HttpGet httpGet = new HttpGet("http://localhost:8801");
        //3.发送请求
        CloseableHttpResponse response = httpClient.execute(httpGet);
        //4.判断状态码
        if(response.getStatusLine().getStatusCode()==200){
            HttpEntity entity = response.getEntity();
            //使用工具类EntityUtils，从响应中取出实体表示的内容并转换成字符串
            String string = EntityUtils.toString(entity, "utf-8");
            System.out.println(string);
        }
        //5.关闭资源
        response.close();
        httpClient.close();
    }
}
