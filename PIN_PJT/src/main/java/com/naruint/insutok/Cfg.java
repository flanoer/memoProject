package com.naruint.insutok;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateException;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.bouncycastle.operator.OperatorCreationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.itextpdf.io.codec.Base64;

@Service
public class Cfg {
	
	private static final Logger logger = LoggerFactory.getLogger(Cfg.class);
		
	@Autowired
	ServletContext servletContext;
				
	@Value("#{pin['rootCa.certPath']}")
    private String rootCaCertPath;	
	
	@Value("#{pin['rootCa.keyPath']}")
    private String rootCaKeyPath;	
	
	@Value("#{pin['rootCa.password']}")
    private String rootCaPassword;	
	
	public PublicKey encPublicKey;
	public PrivateKey encPrivateKey;
	public String encCertPem;
	
	@PostConstruct
	public void init() throws NoSuchAlgorithmException, OperatorCreationException, CertificateException{
		logger.debug("=========================init====================");
		
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");		
        generator.initialize(2048);
 
        KeyPair keyPair = generator.genKeyPair();
        encPublicKey = keyPair.getPublic();
        encPrivateKey = keyPair.getPrivate();
		
    	String bgn = "-----BEGIN PUBLIC KEY-----\n";
		String end = "\n-----END PUBLIC KEY-----";
    	String pem = Base64.encodeBytes(encPublicKey.getEncoded());
    	encCertPem = bgn + pem + end;
    	encCertPem = encCertPem.replaceAll("(\r\n|\r|\n|\n\r)", "");	//js에 전달시 개행문제해결
	}
			
	public String getServletPath(String filePath) { 
		return servletContext.getRealPath(filePath);
	}
	
	//root
	public String rootCaCertPath() {
		return  getServletPath(rootCaCertPath);
	}	
	
	public String rootCaKeyPath() {
		return  getServletPath(rootCaKeyPath);
	}
			
	public String rootCaPassword() {
		return  rootCaPassword;
	}
	
}

