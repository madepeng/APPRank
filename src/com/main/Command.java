package com.main;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;


public class Command {  
	
	public String packageName;
	public String versioncode;
	//public String sha256;
	
	/*//读取apk文件，以字节方式读入
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
            System.out.println(sha256);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
        	 //System.out.println(sha256);
        	 return sha256;
        }

}*/
	
    public  void exeCmd(String commandStr) {  
        BufferedReader br = null;  
        try {  
            Process p = Runtime.getRuntime().exec(commandStr);  
            br = new BufferedReader(new InputStreamReader(p.getInputStream(),"utf-8"));  
            //br = new BufferedReader(new InputStreamReader(in, cs));  
            String line = null;  
            
           // StringBuilder sb = new StringBuilder();  
            //读取所有的东西
          /*  while ((line = br.readLine()) != null) {  
                sb.append(line + "\n");  
            }
            System.out.println(sb.toString());  */
            
            String packageNameandVersion = br.readLine();
            //System.out.println(packageNameandVersion);
            String packageNameL = packageNameandVersion.split(" versionCode")[0];
            packageName = packageNameL.split("=")[1];
           //System.out.println(packageName);
            String versioncodeL = packageNameandVersion.split(" versionName")[0];
            versioncode = (versioncodeL.split(" ")[2]).split("=")[1];
            versioncode = versioncode.substring(1, versioncode.length()-1);
            //System.out.println(versioncode);
            
        } catch (Exception e) {  
            e.printStackTrace();  
        }   
        finally  
        {  
            if (br != null)  
            {  
                try {  
                    br.close();  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }  
  
    
    
   /* public static void main(String[] args) {  
       String commandStr = "aapt d badging F:\\firefoxdownload\\sample-00.apk";  
       String packageName = "aapt d badging F:\\firefoxdownload\\sample-00.apk";  
       
      //  String commandStr = "ipconfig";  
        Command.exeCmd(commandStr);  
    }  */
}  
