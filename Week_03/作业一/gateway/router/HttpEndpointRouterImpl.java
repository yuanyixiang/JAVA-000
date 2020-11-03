package gateway.router;

import java.util.List;
import java.util.Random;

/**
 * @author rd-yyx
 * @version 1.0
 * @date 2020/11/3 2:46 下午
 */
public class HttpEndpointRouterImpl implements HttpEndpointRouter {
    @Override
    public String route(final List<String> endpoints) {
        //随机
        if(endpoints != null){
            Random random = new Random();
            return endpoints.get(random.nextInt(endpoints.size()));
        }
        return null;
    }
}
