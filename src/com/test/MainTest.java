package com.test;
import java.util.HashMap;
import java.util.Map;

import com.main.Command;
import com.main.CompareMF;
import com.main.getApkSign;

public class MainTest {

	public  String allapptest(String officeFilePath,String filePath,String officeManiFilePath,String maniFilePath) {
		// TODO Auto-generated method stub
		String result = null;

		Command command1 = new Command();
		Command command2 = new Command();
		
		/*String filePath1 = "C:/Users/hope/Desktop/testapp/qqyouyi.apk";
		String filePath2 = "C:/Users/hope/Desktop/testapp/qqbaidu.apk";*/
		String filePath1 = officeFilePath;
		String filePath2 = filePath;
		String commandStr1 = "aapt d badging "+filePath1;
		String commandStr2 = "aapt d badging "+filePath2;

		command1.exeCmd(commandStr1);
		command2.exeCmd(commandStr2);
		/*command1.SHA256(filePath1);
		command2.SHA256(filePath2);
		*/
		//得到公钥
		getApkSign get1 = new getApkSign();
		getApkSign get2 = new getApkSign();
		get1.getApkSignInfo(filePath1);
	    get2.getApkSignInfo(filePath2);
	    System.out.println(get1.publicKey);
	    System.out.println(get2.publicKey);
	    
	    if (!(get1.publicKey.equals(get2.publicKey))) {
			result = "S";
			System.exit(0);
		}

		/*System.out.println(command1.packageName);
		System.out.println(command2.packageName);
		System.out.println(command1.versioncode);
		System.out.println(command2.versioncode);
		System.out.println(command1.sha256);
		System.out.println(command2.sha256);*/
		
	/*	if ((command1.packageName.equals(command2.packageName))&&(command1.versioncode.equals(command2.versioncode))&&(command1.sha256.equals(command2.sha256))) {
			result = "I";
		}*/
		
		if ((Integer.parseInt(command1.versioncode.trim())>Integer.parseInt(command2.versioncode.trim()))) {
			result = "V";
		}
		
		if ((Integer.parseInt(command1.versioncode.trim())<Integer.parseInt(command2.versioncode.trim()))) {
			result = "W";
		}
		
		
		CompareMF compareMF = new CompareMF();
		if (!(command1.packageName.equals(command2.packageName))) {
			compareMF.Compare(officeManiFilePath, maniFilePath);
			//System.out.println(compareMF.label);
			if (compareMF.CrNum!=0) {
				result = "C";
			}else if (compareMF.WaNum!=0) {
				result = "R";
			}else if (compareMF.label.equals("zero-modification")) {
				result = "N";
			}else if (compareMF.label.equals("D")) {
				result = "D";
			}else if (compareMF.label.equals("X")) {
				result = "X";
			}
		}
		
/*		if (!(command1.sha256.equals(command2.sha256))) {

			compareMF.Compare(officeManiFilePath, maniFilePath);
			//System.out.println(compareMF.label);
			if (compareMF.CrNum!=0) {
				result = "C";
			}else if (compareMF.WaNum!=0) {
				result = "R";
			}else if (compareMF.label.equals("zero-modification")) {
				result = "zero-modification";
			}else if (compareMF.label.equals("D")) {
				result = "D";
			}else if (compareMF.label.equals("X")) {
				result = "X";
			}
		}
*/
		return result;
		//System.out.println(result);
	}

}
