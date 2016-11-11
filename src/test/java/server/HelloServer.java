package server;

import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.util.concurrent.Executors;

import com.common.utils.CharUtil;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

public class HelloServer {  
	  public final int port;
	   public HelloServer(int port){
		   this.port=port;
	   }
	 public void start()throws Exception {
		 final HelloServerHandler serverHandler=new HelloServerHandler();
		 EventLoopGroup group =new NioEventLoopGroup();
		 try{
			 ServerBootstrap b=new ServerBootstrap();
			 b.group(group)
			 .channel(NioServerSocketChannel.class)
			 .localAddress(new InetSocketAddress(port))
			 .childHandler(new ChannelInitializer<SocketChannel>(){
				 @Override
				 public void initChannel(SocketChannel ch)throws Exception {
					 ch.pipeline().addLast(serverHandler);
				 }
			 });
			 ChannelFuture f=b.bind().sync();
			 f.channel().closeFuture().sync();
	
		 }catch(Exception e){
			 
		 }finally{
			 group.shutdownGracefully().sync();
		 }
	 }
    public static void main(String args[]) throws Exception {  
  
        // Server服务启动器  
    if (args.length !=1){
    	System.err.println("Usage:"+HelloServer.class.getSimpleName()+"<port>");
    }
    int port=Integer.parseInt(args[0]);
    new HelloServer(port).start();
     
        // 开放8000端口供客户端访问。  
  
     //   bootstrap.bind(new InetSocketAddress(8000));  
  
    }  
  
   
  
    private static class HelloServerHandler extends ChannelInboundHandlerAdapter {  
  
   
  
        /** 
 
         * 当有客户端绑定到服务端的时候触发，打印"Hello world, I'm server." 
         */  
    	@Override
        public void channelRead(ChannelHandlerContext ctx,   Object msg) {  
    		ByteBuf  in=(ByteBuf) msg;
  
            System.out.println("Hello world, I'm server."+in.toString(CharsetUtil.UTF_8));  
  
        }  
    	@Override
        public void channelReadComplete(ChannelHandlerContext ctx) {  
    		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
            System.out.println("Netty channel completed");  
  
        }  
    	@Override
        public void exceptionCaught (ChannelHandlerContext ctx,Throwable cause) {  
    		cause.printStackTrace();
            System.out.println("Netty got exception");  
  
        }  
  
    }  
  
}  
  