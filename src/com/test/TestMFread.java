package com.test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class TestMFread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		
	

			
			
			 Map<String, String> map = null ;
			
		
		        try {
		        		map = new HashMap<String, String>();
		                String encoding="UTF-8";
		                File file=new File("C:/Users/hope/Desktop/testapp/qqbaidu/META-INF/MANIFEST.MF");
		                if(file.isFile() && file.exists()){ //判断文件是否存在
		                    InputStreamReader read = new InputStreamReader(
		                    new FileInputStream(file),encoding);//考虑到编码格式
		                    BufferedReader bufferedReader = new BufferedReader(read);
		                    String lineTxt = null;
		                  /*  while((lineTxt = bufferedReader.readLine()) != null){
		                        System.out.println(lineTxt);
		                    }*/
		                    String Name = null;
		                    String Digest = null;
		                    for (int j = 0; (lineTxt = bufferedReader.readLine()) != null; j++) {
		                    	System.out.println(j);
		                    	if (j>2) {
		                    		if (lineTxt.length() == 0) {
										continue;
									}
		                    		
		                    		/*if (j%3 == 0) {
		                    			Name = lineTxt.trim().split(": ")[1];
		                        		//map.put(Name, null);
		                        		//System.out.println(lineTxt);
									}else {
										Digest = lineTxt.split(": ")[1];
		                        		map.put(Name, Digest);
		                        		
									}*/
		                    		if (lineTxt.startsWith("Name")) {
		                    			Name = lineTxt.trim().split(": ")[1];
									}else if (lineTxt.startsWith(" ")) {
										Name += lineTxt.split(" ")[1];
									}else if (lineTxt.startsWith("SHA1")) {
										Digest = lineTxt.split(": ")[1];
		                        		map.put(Name, Digest);
									}
		                    		
								}
		                    	//System.out.println(lineTxt);
		                    }
		                    //System.out.println(map);
		                    read.close();
		        }else{
		            System.out.println("找不到指定的文件");
		        }
		        } catch (Exception e) {
		            System.out.println("读取文件内容出错");
		            e.printStackTrace();
		        }
		     
		    
		     
		    System.out.println(map);
		    System.out.println(map.size());
		    
		

	}

}
