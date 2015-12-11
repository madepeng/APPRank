package com.main;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


public class getApkSign {
	
	 public String publicKey;

	public  String getApkSignInfo(String apkFilePath){
		byte[] readBuffer = new byte[8192];
		java.security.cert.Certificate[] certs = null;
		try{
			JarFile jarFile = new JarFile(apkFilePath);
			Enumeration entries = jarFile.entries();
			while(entries.hasMoreElements()){
				JarEntry je = (JarEntry)entries.nextElement();
			    if(je.isDirectory()){
			        continue;
			    }
			    if(je.getName().startsWith("META-INF/")){
			    	continue;
			    }
			    java.security.cert.Certificate[] localCerts = loadCertificates(jarFile,je,readBuffer);
			  //  System.out.println("File " + apkFilePath + " entry " + je.getName()+ ": certs=" + certs + " ("+ (certs != null ? certs.length : 0) + ")");
			    if (certs == null) {
				    certs = localCerts;
				}else{
					for(int i=0; i<certs.length; i++){
					    boolean found = false;
					    for (int j = 0; j < localCerts.length; j++) {
					    	if (certs[i] != null && certs[i].equals(localCerts[j])) {
					    		  found = true;
					    		  break;
					        }
					    }
					    if (!found || certs.length != localCerts.length) {
					    	  jarFile.close();
					    	  return null;
					    }
					}
				}
			}
			jarFile.close();
			//Log.i("wind cert=",certs[0].toString());
			//return certs[0].getPublicKey().toString();
			publicKey = certs[0].getPublicKey().toString();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
private  java.security.cert.Certificate[] loadCertificates(JarFile jarFile, JarEntry je, byte[] readBuffer) {
		try {
			InputStream is = jarFile.getInputStream(je);
			while(is.read(readBuffer,0,readBuffer.length)!=-1) {					
			}
			is.close();
			return (java.security.cert.Certificate[])(je!=null?je.getCertificates():null);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Exception reading "+je.getName()+" in "+jarFile.getName()+": "+e);
		}
		return null;
	}

/*public static void main(String[] args) {
	getApkSign get1 = new getApkSign();
	getApkSign get2 = new getApkSign();
	get1.getApkSignInfo("C:/Users/hope/Desktop/testapp/qqyingyongbao.apk");
    get2.getApkSignInfo("C:/Users/hope/Desktop/testapp/qqbaidu.apk");
	System.out.println(get1.publicKey);
	System.out.println(get2.publicKey);
	if (get1.publicKey.equals(get2.publicKey)) {
		System.out.println("true");
	}
}*/


}
