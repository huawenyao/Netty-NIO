package client;

import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.util.concurrent.Executors;

import com.xiaoleilu.hutool.util.CharsetUtil;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/** 
 
 * Netty 客户端代码 
 
 * 
 * @blog http://www.coderli.com 
 
 */  
  
public class HelloClient {  
  private final String host;
  private final int port;
  public HelloClient(String host ,int port){
	  this.port=port;
	  this.host=host;
  }
  public void start()throws Exception{
	  EventLoopGroup group=new NioEventLoopGroup();
	  try{
		  Bootstrap b=new Bootstrap();
		  b.group(group).channel(NioSocketChannel.class)
		  .remoteAddress(new InetSocketAddress(host,port))
		  .handler(new ChannelInitializer<SocketChannel>(){
			  @Override
			  public void initChannel(SocketChannel ch)throws Exception{
				  
			  }
		  });
	  }finally{
		  group.shutdownGracefully().sync();
	  }
  }
    public static void main(String args[]) throws Exception {  
  
        String host="127.0.0.1";
        int port=8080;
        new HelloClient(host,port).start();
  
    }  
  
   
  
    private static class HelloClientHandler extends SimpleChannelInboundHandler {  
    public CharSequence seq = "Netty connected";
   @Override
   public void channelActive(ChannelHandlerContext ctx){
	   ctx.writeAndFlush( Unpooled.copiedBuffer("Netty in Action rocks!", CharsetUtil.UTF_8););
   }

		@Override
		protected void channelRead0(ChannelHandlerContext paramChannelHandlerContext, Object paramI) throws Exception {
			  System.out.println("Hello world, I'm client.");  
		}  
		@Override
        public void exceptionCaught (ChannelHandlerContext ctx,Throwable cause) {  
    		cause.printStackTrace();
            System.out.println("Netty got exception");  
  
        }  
    }  
  
}  
