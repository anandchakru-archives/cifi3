package com.anandchakru.app.service;

import static com.anandchakru.app.model.constants.Cifi3.NO_SIGN;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.stereotype.Service;

@Service
public class SignVerifyService {
	public boolean verify(String source, String signature, String token) {
		if (token == null || token.length() == 0 || source == null || source.length() == 0) {
			return false;
		}
		if (StringUtils.equals(token, NO_SIGN)) { //For Tests & local
			return true;
		}
		return sign(source, token).equals(signature);
	}
	public String sign(String source, String token) {
		return "sha1=" + new HmacUtils(HmacAlgorithms.HMAC_SHA_1, token).hmacHex(source);
	}
}