package com.naruint.insutok;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.naruint.insutok.Cfg;
import com.naruint.insutok.StrUtil;

@Controller
public class HomeController {
	
	private static Logger logger = Logger.getLogger(HomeController.class);
	
	// 서버 운영 설정 세팅
	private String serverMode = "dev";
	
	@Autowired
	private Cfg cfg;

	@Autowired
	HomeComponent homeComponent;
	
	/**
	 * 키패드 샘플 페이지
	 * @Author : HP
	 * @Date : 2020. 11. 23.
	 * @Return : String
	 */
	@RequestMapping("/pinMain")
	public String goMain(HttpServletRequest req, HttpServletResponse resp) {
		return "main";
	}

	/**
	 * 숫자별로 할당할 랜덤문자열 생성하여 return
	 * @Author : HP
	 * @Date : 2020. 11. 23.
	 * @Return : Map<String,Object>
	 */
	@RequestMapping("/getRandomPinCode")
	@ResponseBody
	public Map<String,Object> getRandomPinCode(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession(false);
		if(session == null) {
			session = req.getSession();
		}
		Map<String,Object> resultMap = new HashMap<String, Object>();
		Map<String,Object> sessionMap = new HashMap<String, Object>();

		String pinCode = homeComponent.randomPatternNum();
		sessionMap.put("pinCode", pinCode);
		
		String token = UUID.randomUUID().toString();
		session.setAttribute(token, sessionMap);
		
		resultMap.put("token", token);	// 세션 조회용 token
		resultMap.put("pinCode", pinCode);	// 키패드 code 문자열
		resultMap.put("encCertPK",Base64.encodeBase64String(cfg.encPublicKey.getEncoded()));	// 암호화용 공개키
		
		String [] fileNames = {"num0.png","num1.png","num2.png","num3.png","num4.png","num5.png","num6.png","num7.png","num8.png","num9.png","none.png"};
		List<String> pinImgList = new ArrayList<String>();
		for(String fileName : fileNames) {
			FileInputStream fis;
			ClassPathResource resource = new ClassPathResource("pinImage/"+fileName);
			try {
				File file = resource.getFile();
				if(file.exists()) {
					byte[] bArr = new byte[(int)file.length()];
					fis = new FileInputStream(file);
					fis.read(bArr);
					fis.close();
					String b64EncStr = Base64.encodeBase64String(bArr);
					logger.info("bArr == "+new String(bArr));
					logger.info("b64EncStr == "+b64EncStr);
					pinImgList.add(b64EncStr);
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				if("dev".equals(serverMode)) e.printStackTrace();
			}
		}
		
		logger.info("pinB64ImgStrList size == "+pinImgList.size());
		resultMap.put("pinImgList", pinImgList);
		
		return resultMap;
	}
	
	/**
	 * 토큰값 받아서 client 에서 보낸 pinData decode
	 * @Author : HP
	 * @Date : 2020. 11. 24.
	 * @Return : String
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/decPinCode")
	@ResponseBody
	public String decPinCode(HttpServletRequest req, HttpServletResponse resp, @RequestBody Map<String,Object> params) {
		HttpSession session = req.getSession(false);
		Map<String,Object> sessionMap = new HashMap<String, Object>();
		sessionMap.putAll((Map<String, Object>) session.getAttribute(StrUtil.objStrNvl(params.get("token"))) );
		String decPinCode = "";
		String pinData = StrUtil.objStrNvl(params.get("pinData"));
		try {
			String pinCode = StrUtil.objStrNvl(sessionMap.get("pinCode"));
			if(!StringUtils.isEmpty(pinCode)) {
				// 비밀키로 암호화 데이터 복호화
				if("dev".equals(serverMode)) logger.debug("pinData check >> "+pinData);
				byte [] encByte = Base64.decodeBase64(pinData);
				String decData = new String(homeComponent.decrypt(encByte));
				if("dev".equals(serverMode)) logger.debug("decData check >> "+decData);
				decPinCode = homeComponent.decRandomPattern(pinCode, decData);
				if("dev".equals(serverMode)) logger.debug("decPinCode check >> "+decPinCode);
			} else {
				logger.error("세션에 pinCode Full String 이 정상적으로 저장되지 않음.");
				decPinCode = "error";
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			if("dev".equals(serverMode)) {
				e.printStackTrace();
			}
			decPinCode = "exception";
		}
		return decPinCode;
	}
}
