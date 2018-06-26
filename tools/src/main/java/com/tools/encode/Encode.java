package com.tools.encode;

import java.io.UnsupportedEncodingException;

public class Encode {
	public static void main(String[] args) {
		String utf8String = toUtf8String("��C��");
		try {
			String decode = decode(utf8String,"UTF-8");
			System.out.println(decode);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String decode(String s, String enc)
	        throws UnsupportedEncodingException{

	        boolean needToChange = false;
	        int numChars = s.length();
	        StringBuffer sb = new StringBuffer(numChars > 500 ? numChars / 2 : numChars);
	        int i = 0;

	        if (enc.length() == 0) {
	            throw new UnsupportedEncodingException ("URLDecoder: empty string enc parameter");
	        }

	        char c;
	        byte[] bytes = null;
	        while (i < numChars) {
	            c = s.charAt(i);
	            switch (c) {
	            case '+':
	                sb.append(' ');
	                i++;
	                needToChange = true;
	                break;
	            case '%':
	                try {
	                    if (bytes == null)
	                        bytes = new byte[(numChars-i)/3];
	                    int pos = 0;

	                    while ( ((i+2) < numChars) &&
	                            (c=='%')) {
	                        int v = Integer.parseInt(s.substring(i+1,i+3),16);
	                        if (v < 0)
	                            throw new IllegalArgumentException("URLDecoder: Illegal hex characters in escape (%) pattern - negative value");
	                        bytes[pos++] = (byte) v;
	                        i+= 3;
	                        if (i < numChars)
	                            c = s.charAt(i);
	                    }

	                    // A trailing, incomplete byte encoding such as
	                    // "%x" will cause an exception to be thrown

	                    if ((i < numChars) && (c=='%'))
	                        throw new IllegalArgumentException(
	                         "URLDecoder: Incomplete trailing escape (%) pattern");

	                    sb.append(new String(bytes, 0, pos, enc));
	                } catch (NumberFormatException e) {
	                    throw new IllegalArgumentException(
	                    "URLDecoder: Illegal hex characters in escape (%) pattern - "
	                    + e.getMessage());
	                }
	                needToChange = true;
	                break;
	            default:
	                sb.append(c);
	                i++;
	                break;
	            }
	        }

	        return (needToChange? sb.toString() : s);
	    }
	
	public static String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}
}
