package com.main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class CompareMF {

	//标签
	public static String label = "";
	
	//警告级别的修改数目
	public float WaNum = 0;
	
	//危险级别的修改数目
	public float CrNum = 0;
	
	public Map<String, String> map;
	
	public  void readTxtFile(String filePath){
        try {
        		map = new HashMap<String, String>();
                String encoding="UTF-8";
                File file=new File(filePath);
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
                    	//System.out.println(j);
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
     
    }
     
    public  void Compare(String filePath1,String filePath2){
        //String filePath1 = "C:/Users/hope/Desktop/testapp/qqbaidu/META-INF/MANIFEST.MF";
       // String filePath2 = "C:/Users/hope/Desktop/testapp/qqyouyi/META-INF/MANIFEST.MF";
        
        CompareMF read1 = new CompareMF();
        read1.readTxtFile(filePath1);
        //System.out.println(read1.map);
        Set<String> set1 = read1.map.keySet();
       //System.out.println(set1);
       
        
        CompareMF read2 = new CompareMF();
        read2.readTxtFile(filePath2);
        //System.out.println(read2.map);
        Set<String> set2 = read2.map.keySet();
      //System.out.println(set2);
        /*System.out.println(set1.size());
        System.out.println(set2.size());*/
        
        //result为set1和set2的交集
        Set<String> result = new HashSet<String>();
        result.clear();
		result.addAll(set1);
		result.retainAll(set2);
		
		  //result1为set1和set2的交集
        Set<String> result1 = new HashSet<String>();
		result1.clear();
		result1.addAll(set1);
		result1.removeAll(set2);
		//System.out.println("差集："+result);
		//System.out.println(result.size());
		
		  //result2为set2和set1的交集
        Set<String> result2 = new HashSet<String>();
		result2.clear();
		result2.addAll(set2);
		result2.removeAll(set1);
		
		
		//System.out.println(result);
		//System.out.println(result.size());
		
		if ((result1.size()==0)&&(result2.size()==0)&&(set1.equals(set2))&&read1.map.equals(read2.map)) {
			//System.out.println("same");
			label = "zero-modification";
		}
		
		if (result1.size()>0) {
			//System.out.println("shanchu");
			//label = "D";
				for (String string : result1) {
				//System.out.println(string);
				//System.out.println(read1.map.get(string));
					//System.out.println("sdf");
					//System.out.println(string);
					if (string.contains("AndroidManifest.xml")) {
						//System.out.println("AndroidManifest.xml modify");
						CrNum ++;
						if (label.contains("AndroidManifest.xml delete")) {
							;
						}else {
							label += "AndroidManifest.xml delete";
						}
						
					}
					if (string.contains(".so")) {
						//System.out.println(".so modify");
						CrNum ++;
	                      if (label.contains(".so delete")) {
							;
						}else {
							label += ".so delete";
						}
					}
					if (string.contains(".dex")) {
						//System.out.println(".dex modify");
						CrNum ++;
	                   if (label.contains(".dex delete")) {
							;
						}else {
							label += ".dex delete";
						}
					}
					if (string.contains("assets/") || string.contains("res/") || string.contains("resources.arsc") || string.contains("r/")) {
						//System.out.println("AndroidManifest.xml modify");
						WaNum ++;
						if (label.contains("resources-related delete")) {
							;
						}else {
							label += "resources-related delete";
						}
						
					}
				
			}
			
		}else if (result2.size()>0) {
			//System.out.println("zengjiia");
			//label = "X";
			for (String string : result2) {
				//System.out.println(string);
				//System.out.println(read1.map.get(string));
					//System.out.println("sdf");
					//System.out.println(string);
					if (string.contains("AndroidManifest.xml")) {
						//System.out.println("AndroidManifest.xml modify");
						CrNum ++;
						if (label.contains("AndroidManifest.xml add")) {
							;
						}else {
							label += "AndroidManifest.xml add";
						}
						
					}
					if (string.contains(".so")) {
						//System.out.println(".so modify");
						CrNum ++;
	                      if (label.contains(".so add")) {
							;
						}else {
							label += ".so add";
						}
					}
					if (string.contains(".dex")) {
						//System.out.println(".dex modify");
						CrNum ++;
	                   if (label.contains(".dex add")) {
							;
						}else {
							label += ".dex add";
						}
					}
					if (string.contains("assets/") || string.contains("res/") || string.contains("resources.arsc") || string.contains("r/")) {
						//System.out.println("AndroidManifest.xml modify");
						WaNum ++;
						if (label.contains("resources-related add")) {
							;
						}else {
							label += "resources-related add";
						}
						
					}
				
			}
		}
		
		for (String string : result) {
			//System.out.println(string);
			//System.out.println(read1.map.get(string));
			if (!(read1.map.get(string).equals(read2.map.get(string)))) {
				//System.out.println("sdf");
				//System.out.println(string);
				if (string.contains("AndroidManifest.xml")) {
					//System.out.println("AndroidManifest.xml modify");
					CrNum ++;
					if (label.contains("AndroidManifest.xml modify")) {
						;
					}else {
						label += "AndroidManifest.xml modify";
					}
					
				}
				if (string.contains(".so")) {
					//System.out.println(".so modify");
					CrNum ++;
                      if (label.contains(".so modify")) {
						;
					}else {
						label += ".so modify";
					}
				}
				if (string.contains(".dex")) {
					//System.out.println(".dex modify");
					CrNum ++;
                   if (label.contains(".dex modify")) {
						;
					}else {
						label += ".dex modify";
					}
				}
				if (string.contains("assets/") || string.contains("r/") || string.contains("resources.arsc") || string.contains("res/")) {
					//System.out.println("AndroidManifest.xml modify");
					WaNum ++;
					if (label.contains("resources-related modify")) {
						;
					}else {
						label += "resources-related modify";
					}
					
				}
			}
		}
		
	/*System.out.println(label);	
	System.out.println(CrNum);
	System.out.println(WaNum);*/
    }
    
}
