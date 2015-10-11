package com.meluo.meluo.popmove.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.os.Handler;

/**
 * ��������Ĺ�����
 * @author apple
 *
 */
public class NUtils {

	public static final int TYPE_TXT=0;
	public static final int TYPE_IMG=1;

	public interface Callback{
		//�жϵ�ǰ���ص�ͼƬ����ʾ�Ŀؼ��Ƿ�������ͼƬ����
		//���������ͼƬռ��ʱ����ȡ�����ص�ǰ��ͼƬ
		public boolean isCancelled(String url);
		
		//������ɺ����ݻش���������
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
				// TODO ����������Դ
				try{
					HttpURLConnection conn=(HttpURLConnection) new URL(url).openConnection();
					InputStream is=conn.getInputStream();
					byte[] buffer=new byte[10*1024];
					int len=-1;
					
					ByteArrayOutputStream baos=new ByteArrayOutputStream();
					
					if(conn.getResponseCode()==200){
						while((len=is.read(buffer))!=-1){
							baos.write(buffer, 0, len);
							
							//�ж��Ƿ�ȡ������
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
