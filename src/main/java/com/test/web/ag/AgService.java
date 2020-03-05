package com.test.web.ag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class AgService {
	public boolean braceTest(String temp) {
		List<String> list = new ArrayList<String>();
		for(String x : temp.split("")) {
			if(Arrays.asList("[","{","(").contains(x)) {
				list.add(x);
			}else if(Arrays.asList("]","}",")").contains(x)) {
				if(list.size()==0) return false;
				if(list.get(list.size()-1).equals(this.braceReverse(x))) {
					list.remove(list.size()-1);
				}else return false;
			}
		}	
		return list.size() == 0;
	}
	
	public String braceReverse(String x) {
		String temp = "";
		switch (x) {
			case "]": temp = "["; break;
			case "}": temp = "{"; break;
			case ")": temp = "("; break;
		}
		return temp;
	}
}
