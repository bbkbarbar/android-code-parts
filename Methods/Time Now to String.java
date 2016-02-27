public String getTimeNow(String accurancy, String separator){
		String toReturn = "";
		Time now = new Time();
		now.setToNow();
		
		if(accurancy.equals("h")){
			toReturn = toReturn + Integer.toString(now.hour);
		}else
		if(accurancy.equals("hm")){
			toReturn = toReturn + Integer.toString(now.hour)
					+ separator + Integer.toString(now.minute)
			;
		}else
		if(accurancy.equals("hms")){
			toReturn = toReturn + Integer.toString(now.hour)
					+ separator + Integer.toString(now.minute)
					+ separator + Integer.toString(now.second)
			;
		}
		
		return toReturn;
}