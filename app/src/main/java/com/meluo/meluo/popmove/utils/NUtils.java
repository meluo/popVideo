package com.meluo.meluo.popmove.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.os.Handler;

/**
 * 网络操作的工具类
 * @author apple
 *
 */
public class NUtils {

	public static final int TYPE_TXT=0;
	public static final int TYPE_IMG=1;

	public interface Callback{
		//判断当前下载的图片所显示的控件是否被其它的图片复用
		//如果被其它图片占用时，则取消下载当前的图片
		public boolean isCancelled(String url);
		
		//下载完成后将数据回传给调用者
		public void response(String url, byte[] bytes);
	}
	
	public static class AbsCallback implements Callback{
		@Override
		public boolean isCancelled(String url) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public void response(String url, byte[] bytes) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private static Handler mHandler =new Handler();
	private static ExecutorService service=Executors.newFixedThreadPool(5);
	
	public static void get(final int type,final String url,final Callback callback){
		service.execute(new Runnable() {
			
			@Override
			public void run() {
				// TODO 下载网络资源
				try{
					HttpURLConnection conn=(HttpURLConnection) new URL(url).openConnection();
					InputStream is=conn.getInputStream();
					byte[] buffer=new byte[10*1024];
					int len=-1;
					
					ByteArrayOutputStream baos=new ByteArrayOutputStream();
					
					if(conn.getResponseCode()==200){
						while((len=is.read(buffer))!=-1){
							baos.write(buffer, 0, len);
							
							//判断是否取消下载
							if(callback.isCancelled(url)){
								return;
							}
						}
						
						final byte[] bytes=baos.toByteArray();

						
						mHandler.post(new Runnable(){
							@Override
							public void run() {
								// TODO Auto-generated method stub
								callback.response(url, bytes);
							}
						});
					}
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
	}
	
}
