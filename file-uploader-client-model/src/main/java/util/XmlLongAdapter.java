package util;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class XmlLongAdapter extends XmlAdapter<String, Long> {

	@Override
	public String marshal(Long l) throws Exception {
		return Long.toString(l);
	}

	@Override
	public Long unmarshal(String str) throws Exception {
		return Long.valueOf(str);
	}

}