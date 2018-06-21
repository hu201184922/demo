package com.fairyland.jdp.framework.security.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fairyland.jdp.framework.util.ValidateCode;

@Controller
@RequestMapping(value = "/admin/sec/captcha")
public class ValidateCodeController {
	/**
	 * 生成验证码
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET)
	public void validateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("Cache-Control", "no-cache");
		String verifyCode = ValidateCode.generateTextCode(ValidateCode.TYPE_NUM_ONLY, 4, null);
		SecurityUtils.getSubject().getSession().setAttribute("captcha", verifyCode);
		response.setContentType("image/jpeg");
		BufferedImage bim = ValidateCode.generateImageCode(verifyCode, 90, 30, 3, true, Color.WHITE, Color.BLACK, null);
		ImageIO.write(bim, "JPEG", response.getOutputStream());
	}

}
