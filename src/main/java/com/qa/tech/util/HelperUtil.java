package com.qa.tech.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

public class HelperUtil {

	public static boolean compareSet(Set<?> set1, Set<?> set2) {
		if (set1 == null || set2 == null) {
			return false;
		}
		if (set1.size() != set2.size()) {
			return false;
		}
		return set1.containsAll(set2);
	}
	
	public static boolean compareList(List<?> list1, List<?> list2) {
		if (list1 == null || list2 == null) {
			return false;
		}
		if (list1.size() != list2.size()) {
			return false;
		}
		return list1.containsAll(list2);
	}
	
	public static String getDomainName(String url) throws URISyntaxException {
	    URI uri = new URI(url);
	    String domain = uri.getHost();
	    return domain.startsWith("www.") ? domain.substring(4) : domain;
	}
}
