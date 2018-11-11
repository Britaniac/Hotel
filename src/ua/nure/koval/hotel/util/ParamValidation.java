package ua.nure.koval.hotel.util;

import java.util.List;

public class ParamValidation {
	
	public boolean checkForMissing(List<String> params){
		boolean flag = false;
		if (params.size() == 0) {
			return flag;
		}
		for (String p: params) {
			if (isMissing(p)) {
				flag = true;
			}
		}
		return flag;
	}
	
	private boolean isMissing(String value) {
	    return((value == null) || (value.trim().equals("")));
	}
}
