package com.test;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

public class SHA256 {
	public String sha256;
	
	//读取apk文件，以字节方式读入
		public  byte[] readFileByBytes(String fileName) {
			
			byte[] content = null;
			try {
				BufferedInputStream in = new BufferedInputStream(new FileInputStream(fileName));        
	            ByteArrayOutputStream out = new ByteArrayOutputStream(1024);        

	            //System.out.println("Available bytes:" + in.available());        

	            byte[] temp = new byte[1024];        
	            int size = 0;        
	            while ((size = in.read(temp)) != -1) {        
	                    out.write(temp, 0, size);        
	            }        
	            in.close();        

	            content = out.toByteArray();        
	            //System.out.println("Readed bytes count:" + content.length);    
			} catch (Exception e) {
				// TODO: handle exception
			}
			return content;
			
		}
		
		//得到apk文件的SHA-256校验值
		@SuppressWarnings("finally")
		public String SHA256(String filePath) {
	        //String text = "123456123456";
			
			MessageDigest digest;
	        try {
	            digest = MessageDigest.getInstance("SHA-256");
	            //byte[] hash = digest.digest(readApk.readFileByBytes("F:/firefoxdownload/1315968.apk"));
	            byte[] hash = digest.digest(readFileByBytes(filePath));
	            sha256 = Hex.encodeHexString(hash);
	            //System.out.println(sha256);
	        } catch (NoSuchAlgorithmException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }finally{
	        	 //System.out.println(sha256);
	        	 return sha256;
	        }

	}

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub

		SHA256 sha256 = new SHA256();
		sha256.SHA256("F:/app/91/58tongcheng.apk");
		System.out.println(sha256.sha256);
	}*/

}
