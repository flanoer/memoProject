package com.narui.pin.home.component;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.narui.pin.home.config.Cfg;

@Component
public class HomeComponent {
	
	@Autowired
	public Cfg cfg;
	
	private static final int PIN_DIGIT = 4;
	private static final String ALGORITHM = "RSA/ECB/PKCS1Padding";

	// 가상기패드용 랜덤문자열
	public String randomPatternNum() {
		return randomPatternNum(10);
	}	
	
	public String randomPatternNum(int size) {
		final char[] characterTable = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 
										'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 
										'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
		Random random = new Random(System.currentTimeMillis());
		int tablelength = characterTable.length;
				
		HashSet<String> luckyNumbers = new HashSet<String>();		 
		// 이 HashSet에 수가 size개 채워질 때 까지 반복합니다.
		// 중복 처리가 자동으로 이루어지므로 삽입만 하면 됩니다.
		// 하지만 중복된 수는 삽입이 거부되므로 반복 횟수가 늘어날 수 있습니다.
		
		while (luckyNumbers.size() < size){
			StringBuffer temp = new StringBuffer();
			for (int i = 0; i < PIN_DIGIT; i++) {
        		temp.append(characterTable[random.nextInt(tablelength)]);
    		}			
		    luckyNumbers.add(temp.toString());
		}
        String scCode = "";
        for (String s : luckyNumbers) {
        	scCode += s;
        }		
		return scCode;
	}
	
	// 가상키패드 문자열 복호화
	public String decRandomPattern(String scCode, String enc) {
		String rtn = "";
		int scCodeSize = scCode.length() / PIN_DIGIT;		
		ArrayList<String> scCodeAL = new ArrayList<String>();		
		for (int i=0; i<scCodeSize; i++) {			
			scCodeAL.add(scCode.substring(i*PIN_DIGIT, (i+1)*PIN_DIGIT));
		}
		
		int encSize = enc.length() / PIN_DIGIT;
		for (int i=0; i<encSize; i++) {		
			String encA = enc.substring(i*PIN_DIGIT, (i+1)*PIN_DIGIT);
			rtn = rtn + scCodeAL.indexOf(encA);
		}		
		
    	return rtn;
    }
	
	// 공개키로 암호화된 데이터 비밀키로 복호화
	public byte[] decrypt(byte[] encByte) 
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		Cipher decrypt=Cipher.getInstance(ALGORITHM);
		decrypt.init(Cipher.DECRYPT_MODE, cfg.encPrivateKey);
		return decrypt.doFinal(encByte);
	}
	
	// 비밀키로 데이터 암호화
	public byte[] encrypt(byte[] bArr) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		Cipher encrypt=Cipher.getInstance(ALGORITHM);
		encrypt.init(Cipher.ENCRYPT_MODE, cfg.encPublicKey);
		return encrypt.doFinal(bArr);
	}
}
