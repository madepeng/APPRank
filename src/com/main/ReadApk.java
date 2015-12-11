package com.main;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


public class ReadApk {

	    /**
	     * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
	     */
	 /*   public  InputStream readFileByBytes(String fileName) {
	        File file = new File(fileName);
	        InputStream in = null;
	        try {
	            System.out.println("以字节为单位读取文件内容，一次读多个字节：");
	            // 一次读多个字节
	            byte[] tempbytes = new byte[100];
	            int byteread = 0;
	            in = new FileInputStream(fileName);
	            // 读入多个字节到字节数组中，byteread为一次读入的字节数
	            while ((byteread = in.read(tempbytes)) != -1) {
	                System.out.write(tempbytes, 0, byteread);
	            }
	        } catch (Exception e1) {
	            e1.printStackTrace();
	        } finally {
	            if (in != null) {
	                try {
	                    in.close();
	                } catch (IOException e1) {
	                }
	            }
	        }
			return in;
	    }
	*/

	public  byte[] readFileByBytes(String fileName) {
			
		byte[] content = null;
		try {
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(fileName));        
            ByteArrayOutputStream out = new ByteArrayOutputStream(1024);        

            System.out.println("Available bytes:" + in.available());        

            byte[] temp = new byte[1024];        
            int size = 0;        
            while ((size = in.read(temp)) != -1) {        
                    out.write(temp, 0, size);        
            }        
            in.close();        

            content = out.toByteArray();        
            System.out.println("Readed bytes count:" + content.length);    
		} catch (Exception e) {
			// TODO: handle exception
		}
		return content;
		
	}

	/*	public static void main(String[] args) {
	        String fileName = "F:/firefoxdownload/1315968.apk";
	        ReadFromFile.readFileByBytes(fileName);
			ReadApk readApk = new ReadApk();
			readApk.readFileByBytes("F:/firefoxdownload/1315968.apk");
			
			
	      
	    }
	*/
	 
}
