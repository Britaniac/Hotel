package ua.nure.koval.hotel.service;

public class ParamValidation {
	
	public boolean checkForMissing(String... params){
		boolean flag = false;
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
