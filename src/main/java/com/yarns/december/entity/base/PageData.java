package com.yarns.december.entity.base;

import org.apache.ibatis.type.Alias;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 入口参数的封装
 *
 * @author yarns
 * @data 2018-4-24 20:58:35
 */
@Alias("pd")
public class PageData extends HashMap<String, Object> implements Map<String, Object> {

	private static final long serialVersionUID = 1L;

	Map<String, Object> map;

	HttpServletRequest request;

	/**
	 * pageData的map封装
	 * @param request
	 */
	public PageData(HttpServletRequest request) {
		this.request = request;
		Map<String, String[]> properties = request.getParameterMap();
		Map<String, Object> returnMap = new HashMap<>();
		Iterator<Entry<String, String[]>> entries = properties.entrySet().iterator();
		Entry<String, String[]> entry;
		String value = "";
		while (entries.hasNext()) {
			entry = entries.next();
			String name = entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else {
				String[] values = (String[]) valueObj;
				for (String value1 : values) {
					value = value1 + ",";
				}
				value = value.substring(0, value.length() - 1);
			}
			returnMap.put(name, value);
		}
		map = returnMap;
	}

	public PageData() {
		map = new HashMap<>();
	}

	@Override
	public Object get(Object key) {
		Object obj;
		if (map.get(key) instanceof Object[]) {
			Object[] arr = (Object[]) map.get(key);
			obj = request == null ? arr : (request.getParameter((String) key) == null ? arr : arr[0]);
		} else {
			obj = map.get(key);
		}
		return obj;
	}

	public String getString(Object key) {
		return (String) get(key);
	}

	@Override
	public Object put(String key, Object value) {
		return map.put(key, value);
	}

	@Override
	public Object remove(Object key) {
		return map.remove(key);
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return map.containsValue(value);
	}

	@Override
	public Set<Entry<String, Object>> entrySet() {
		// TODO Auto-generated method stub
		return map.entrySet();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return map.isEmpty();
	}

	@Override
	public Set<String> keySet() {
		// TODO Auto-generated method stub
		return map.keySet();
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> t) {
		// TODO Auto-generated method stub
		map.putAll(t);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return map.size();
	}

	@Override
	public Collection<Object> values() {
		// TODO Auto-generated method stub
		return map.values();
	}

}
