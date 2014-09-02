package net.rockey.core.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * Shiro utility
 */
public class ShiroUtils {

	/**
	 * Obtain current subject of shiro.
	 * 
	 * @return
	 */
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	/**
	 * Obtain current session of shiro.
	 * 
	 * @return
	 */
	public static Session getSession() {
		return getSubject().getSession();
	}

	/**
	 * Obtain attribute of current session.
	 * 
	 * @param key
	 * @return
	 */
	public static Object getAttribute(String key) {
		return getSession().getAttribute(key);
	}

	/**
	 * Setter attribute of current session.
	 * 
	 * @param key
	 * @param value
	 */
	public static void setAttribute(String key, Object value) {
		getSession().setAttribute(key, value);
	}

}
