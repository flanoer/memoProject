package com.narui.pin.home.config;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateException;

import javax.annotation.PostConstruct;

import org.bouncycastle.operator.OperatorCreationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import org.apache.commons.codec.binary.Base64;

@Service
public class Cfg {
	
	private static final Logger logger = LoggerFactory.getLogger(Cfg.class);
		
	public PublicKey encPublicKey;
	public PrivateKey encPrivateKey;
	public String encCertPem;
	
	@PostConstruct
	public void init() throws NoSuchAlgorithmException, OperatorCreationException, CertificateException{
		logger.debug("=========================Cfg.init(PostConstruct)====================");
		
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");		
        generator.initialize(2048);
 
        KeyPair keyPair = generator.genKeyPair();
        encPublicKey = keyPair.getPublic();
        encPrivateKey = keyPair.getPrivate();
		
    	String bgn = "-----BEGIN PUBLIC KEY-----\n";
		String end = "\n-----END PUBLIC KEY-----";
    	String pem = Base64.encodeBase64String(encPublicKey.getEncoded());
    	encCertPem = bgn + pem + end;
    	encCertPem = encCertPem.replaceAll("(\r\n|\r|\n|\n\r)", "");	//js에 전달시 개행문제해결
    	
    	logger.debug("=========================Cfg.init(PostConstruct)====================");
	}
			
}

