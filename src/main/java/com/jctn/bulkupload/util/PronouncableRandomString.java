/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctn.bulkupload.util;

import org.apache.commons.lang.math.RandomUtils;

/**
 *
 * @author martin
 */
public class PronouncableRandomString {

	/**
	 * A random pronouncable string. This is based on the algorithm for this
	 * implementation http://www.geekpedia.com/code89_Pronounceable-Random-Password-Generator.html
	 * @return
	 */
	public String getRandomPronouncableString() {
		String[] c = {"b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "qu", "r", "s", "t", "v", "w", "ch", "cr", "fr", "nd", "ng", "nk", "nt", "ph", "pr", "rd", "sh", "sl", "sp", "st", "th", "tr"};
		String[] v = {"a", "e", "i", "o", "u", "y"};
		boolean f = true;
		String r = "";
		for (int i = 0; i < 8; i++) {
			r += (f ? c[RandomUtils.nextInt(c.length)] : v[RandomUtils.nextInt(v.length)]);
			f = !f;
		}
		return r;
	}

	/**
	 * Simple test driver.
	 * @param args
	 */
	public static void main(String[] args) {
		PronouncableRandomString instance = new PronouncableRandomString();
		System.out.println(instance.getRandomPronouncableString());
		System.out.println(instance.getRandomPronouncableString());
		System.out.println(instance.getRandomPronouncableString());
		System.out.println(instance.getRandomPronouncableString());
		System.out.println(instance.getRandomPronouncableString());
		System.out.println(instance.getRandomPronouncableString());
		System.out.println(instance.getRandomPronouncableString());
		System.out.println(instance.getRandomPronouncableString());
	}
}
