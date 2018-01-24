package netty.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.ImmediateEventExecutor;

import java.net.InetSocketAddress;

/**
 * Created with IntelliJ IDEA.
 * User: za-lvjian
 * Date: 2018/1/24 16:30
 * DESC:
 */
public class ChatServer {

    public static void main(String[] args) throws InterruptedException {

        ChannelGroup channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        NioEventLoopGroup group = new NioEventLoopGroup();
        serverBootstrap.group(group).channel(NioServerSocketChannel.class)
                .childHandler(new ChatServerInitializer(channelGroup));

        int port = 9090;
        ChannelFuture channelFuture = serverBootstrap.bind(new InetSocketAddress(port)).sync();
        System.out.println("服务端启动完成 ,监听端口是" + port);
        channelFuture.channel().closeFuture().addListener(ChannelFutureListener.CLOSE);
    }
}
