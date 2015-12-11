package com.test;

public class TestSHA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] appNames = {"58tongcheng","aiqiyi","baiduditu","baidunuomi","baiduwaimai","chunyuyisheng","dazhongdianping","didichuxing","duomiyinyue","edaijia","ganji","jingdong","jinritoutiao","kugouyinyue","lanrentingshu","leshi","maimai","meilishuo","meituan","mojitianqi","momo","maoyandianying","qingtingfm","qq","qqkongjian","qqtongbuzhushou","qqyouxiang","saomiaoquannengwang","souhushipin","sougoushurufa","taobao","tengxunshipin","tianmao","ucliulanqi","wangxin","wangyigongkaike","wangyixinwen","wangyiyouxiang","wangyiyunketang","weixin","weizhibiji","xinlangweibo","youdaocidian","youdaoyunbiji","youku","youxin","yy","zhangyue","zhifubao","zuoyebang"};
		//for (int i = 0; i < appMarket.length; i++) {
		
			for (int j = 0; j < appNames.length; j++) {
			String	filePath = "F:/app/"+91+"/"+appNames[j]+".apk";
			SHA256 sha256 = new SHA256();
			sha256.SHA256(filePath);
			System.out.println(filePath+sha256.sha256);
			}
	}

}
