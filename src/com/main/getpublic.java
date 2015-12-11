package com.main;
import java.io.ByteArrayInputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;


public class getpublic {
     
	public static String getPublicKey(byte[] signature) {

			        try {

			 

			            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");

			            X509Certificate cert = (X509Certificate) certFactory.generateCertificate(new ByteArrayInputStream(signature));

			 

			            String publickey = cert.getPublicKey().toString();

			            publickey = publickey.substring(publickey.indexOf("modulus: ") + 9,

			                    publickey.indexOf("\n", publickey.indexOf("modulus:")));

			 


			            return publickey;

			        } catch (CertificateException e) {

			            e.printStackTrace();

			        }

		        return null;

			    }

			    
   
}