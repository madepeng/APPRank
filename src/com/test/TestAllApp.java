package com.test;

import java.util.HashMap;
import java.util.Map;

import com.main.Command;
import com.main.CompareMF;
import com.main.getApkSign;

public class TestAllApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		StringBuffer sumResult = new StringBuffer("");
		float sumScore = 0;
		float positiveScore = 0;
		float WaScore = 0;
		float CrScore = 0;
		
		
		//完全一样
		int I = 0;
		
		//高版本
		int highVersion = 0;
		
		//零修改
		int zeroModification = 0;
		
		//资源相关文件修改
		float resourceModi = 0;
		
		//低版本
		int lowVersion = 0;
		
		//报名不匹配
		int packNameMis = 0;
		
		//非资源文件被修改
		float nonResourceModi = 0;
		
		//签名被修改
		int signModi = 0;
		
		int T = 50;
		String officeFilePath = null;
		String filePath = null;
		String maniFilePath = null;
		String officeManiFilePath = null;
		//String[] appMarket = {"91","360","anzhi","anzhuo","baidu","dangle","jifeng","lestore","mumayi","sougou","wandoujia","xiaomi","yidong","yingyongbao","yingyonghui"};
		//String[] appNames = {"youxin","wangyiyouxiang","58tongcheng","aiqiyi","baiduditu","baidunuomi","baiduwaimai","chunyuyisheng","dazhongdianping","didichuxing","duomiyinyue","edaijia","ganji","jingdong","jinritoutiao","kugouyinyue","lanrentingshu","leshi","maimai","meilishuo","meituan","mojitianqi","momo","maoyandianying","qingtingfm","qq","qqkongjian","qqtongbuzhushou","qqyouxiang","saomiaoquannengwang","souhushipin","sougoushurufa","taobao","tengxunshipin","tianmao","ucliulanqi","wangxin","wangyigongkaike","wangyixinwen","wangyiyunketang","weixin","weizhibiji","xinlangweibo","yy","youdaocidian","youdaoyunbiji","youku","zhangyue","zhifubao","zuoyebang"};
		String[] appNames = {"youxin","wangyiyouxiang","58tongcheng","aiqiyi","baiduditu","baidunuomi","baiduwaimai","chunyuyisheng","dazhongdianping","didichuxing","duomiyinyue","edaijia","ganji","jingdong","jinritoutiao","kugouyinyue","lanrentingshu","leshi","maimai","meilishuo","meituan","mojitianqi","momo","maoyandianying","qingtingfm","qq","qqkongjian","qqtongbuzhushou","qqyouxiang","saomiaoquannengwang","souhushipin","sougoushurufa","taobao","tengxunshipin","tianmao","ucliulanqi","wangxin","wangyigongkaike","wangyixinwen","wangyiyunketang","weixin","weizhibiji","xinlangweibo","yy","youdaocidian","youdaoyunbiji","youku","zhangyue","zhifubao","zuoyebang"};
		System.out.println(appNames.length);
			for (int j = 0; j < appNames.length; j++) {
				//System.out.println(appNames[j]);
				filePath = "F:/app/"+"yingyonghui"+"/"+appNames[j]+".apk";
				maniFilePath = "F:/app/"+"yingyonghui"+"/"+appNames[j]+"/META-INF/MANIFEST.MF";
				
				officeFilePath = "F:/app/office/"+appNames[j]+".apk";
				officeManiFilePath = "F:/app/office/"+appNames[j]+"/META-INF/MANIFEST.MF";
				
				String result = null;

				Command command1 = new Command();
				Command command2 = new Command();
				
				String commandStr1 = "aapt d badging "+officeFilePath;
				String commandStr2 = "aapt d badging "+filePath;
				
				command1.exeCmd(commandStr1);
				command2.exeCmd(commandStr2);
				
				SHA256 sha2561 = new SHA256();
				SHA256 sha2562 = new SHA256();
				sha2561.SHA256(officeFilePath);
				sha2562.SHA256(filePath);
							
				//得到公钥
				getApkSign get1 = new getApkSign();
				getApkSign get2 = new getApkSign();
				get1.getApkSignInfo(officeFilePath);
			    get2.getApkSignInfo(filePath);
			    /*System.out.println(get1.publicKey);
			    System.out.println(get2.publicKey);*/
			    
			    //公钥比较
				boolean b1 = get1.publicKey.equals(get2.publicKey);
				
				//包命比较
				boolean b2 = command1.packageName.equals(command2.packageName);
				
				//版本比较
				boolean b3 = command1.versioncode.equals(command2.versioncode);
				
				//sha26比较
				boolean b4 = sha2561.sha256.equals(sha2562.sha256);
				
				//官方版本号
				Integer version11 = Integer.parseInt(command1.versioncode.trim());
				int version1 = version11.intValue();
				//应用商店版本号
				Integer version22 = Integer.parseInt(command2.versioncode.trim());
				int version2 = version22.intValue();
				
			    if (!b1) {
					result = "S";
					signModi ++;
					System.out.println(appNames[j]+"                      "+result);			
					sumResult.append(appNames[j]+"   level is  "+result+"\n");
					continue;
				}
			    
			    if (b2&&b3&&b4) {
					result = "I";
					I ++;
					//continue;
				}
				
				if (b2&&version1>version2) {
					result = "V";
					lowVersion ++;
					//continue;
				}
				
				if (b2&&version1<version2) {
					result = "W";
					highVersion ++;
					//continue;
				}
				
				//报名和版本都不同的时候，低版本的危险要大于报名的危险
				if (!b2&&version1>version2) {
					lowVersion++;
					result = "N+V";
				}
				
				if (!b2&&version1<version2) {
					highVersion++;
					result = "W";
				}
								
				CompareMF compareMF = new CompareMF();
				if ((version1==version2)&&!b2) {
					compareMF.Compare(officeManiFilePath, maniFilePath);
					if (compareMF.CrNum!=0 || compareMF.WaNum!=0) {
						nonResourceModi += compareMF.CrNum/(compareMF.CrNum+compareMF.WaNum);
						resourceModi += compareMF.WaNum/(compareMF.CrNum+compareMF.WaNum);
					}
					if (compareMF.CrNum!=0) {
						//nonResourceModi ++;
						result = "C";
					}else if (compareMF.WaNum!=0) {
						//resourceModi ++;
						result = "R";
					}else if (compareMF.label.equals("zero-modification")) {
						result = "N"+"    "+command2.packageName;
						packNameMis ++;
					}
				}
				
				if (b2&&(version1==version2)&&!b4) {

					compareMF.Compare(officeManiFilePath, maniFilePath);
					if (compareMF.CrNum!=0 || compareMF.WaNum!=0) {
						nonResourceModi += compareMF.CrNum/(compareMF.CrNum+compareMF.WaNum);
						resourceModi += compareMF.WaNum/(compareMF.CrNum+compareMF.WaNum);
					}
					if (compareMF.CrNum!=0) {
						//nonResourceModi++;
						result = "C";
					}else if (compareMF.WaNum!=0) {
						//resourceModi++;
						result = "R";
					}else if (compareMF.label.equals("zero-modification")) {
						result = "zero-modification";
						zeroModification++;
					}
				}
				
				System.out.println(appNames[j]+"                      "+result);			
				sumResult.append(appNames[j]+"   level is  "+result+"\n");
			}
			System.out.println(sumResult.toString());
			positiveScore = (I+highVersion+zeroModification)*100/T;
			CrScore = (nonResourceModi*60+signModi*100)/T;
			WaScore = (packNameMis*10+resourceModi*20+lowVersion*30)/T;
			sumScore = (positiveScore-WaScore-CrScore+100)/2;
			System.out.println("sumScore is  "+sumScore);
			
			/*System.out.println(I);
			System.out.println(highVersion);
			System.out.println(zeroModification);
			System.out.println(resourceModi);
			System.out.println(lowVersion);
			System.out.println(packNameMis);
			System.out.println(nonResourceModi);
			System.out.println(signModi);*/
			
		//}
	}

}
