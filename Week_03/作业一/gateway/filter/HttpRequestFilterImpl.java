package gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author rd-yyx
 * @version 1.0
 * @date 2020/11/3 2:31 下午
 */
public class HttpRequestFilterImpl implements HttpRequestFilter {
    @Override
    public void filter(final FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        if (fullRequest != null) {
            fullRequest.headers().set("nio", "yuanyixiang");
        }
    }
}
