package net.rockey.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyUtils {

	static class SingletonHolder {
		static PropertyUtils instance = new PropertyUtils();
	}

	private static String applicationProperties = "/application.properties";

	private static Map<String, Properties> propertiesMap = new HashMap<String, Properties>();

	private PropertyUtils() {
		// Empty.
	}

	// 线程安全
	public static PropertyUtils getInstance() {
		return SingletonHolder.instance;
	}

	public String getValue(String fileName, String key) {
		Properties prop = null;
		if (propertiesMap.containsKey(fileName)) {
			prop = propertiesMap.get(fileName);
		} else {
			prop = new Properties();
			InputStream in = PropertyUtils.class.getResourceAsStream(fileName);
			try {
				prop.load(in);
				propertiesMap.put(fileName, prop);
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return prop.isEmpty() ? null : prop.getProperty(key);
	}

	public String getValue(String key) {
		return this.getValue(applicationProperties, key);
	}

}
