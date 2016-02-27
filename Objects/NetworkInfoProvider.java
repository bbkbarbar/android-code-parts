
/**
 *	PERMISSION:
 *	<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
 *
 * 	Version: 1.0
 * 	2011.09.17.
 * 
 * 	by.: Boór András
 */

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkInfoProvider {
	
	public static String TYPENAME_MOBILE = "MOBILE";
	public static String TYPENAME_WIFI = "WIFI";
	public static String TYPENAME_MMS = "MOBILE_MMS";
	public static String TYPENAME_SUPL = "MOBILE_SUPL";
	public static String TYPENAME_DUN = "MOBILE_DUN";
	public static String TYPENAME_HIPRI = "MOBILE_HIPRI";
	
	public static String STATE_CONNECTED = "CONNECTED";
	public static String STATE_CONNECTING = "CONNECTING";
	public static String STATE_IDLE = "IDLE";
	public static String STATE_DISCONNECTED = "DISCONNECTED";
	
	
	
	Activity myParentActivity;
	ConnectivityManager myConnectivityManager;
	NetworkInfo[] networkInfo;
	
	ArrayList<String> names;
	ArrayList<String> states;
	
	public NetworkInfoProvider(Activity parentActivity){
		myParentActivity = parentActivity;
		myConnectivityManager = (ConnectivityManager) myParentActivity.getSystemService(Context.CONNECTIVITY_SERVICE); 
		networkInfo = myConnectivityManager.getAllNetworkInfo();
		
		readInfos();
	}
	
	public void readInfos(){
		names = new ArrayList<String>();
        states = new ArrayList<String>();
        
        for(int i=0; i<networkInfo.length; i++){
        	String strNetworkName  = networkInfo[i].getTypeName();
        	String strNetworkState = networkInfo[i].getDetailedState() + "";
        	
        	names.add(strNetworkName);
        	states.add(strNetworkState);
        }
	}
	
	public ArrayList<String> getTypeNames(){
		readInfos();
		return this.names;
	}
	
	public ArrayList<String> getStates(){
		readInfos();
		return this.states;
	}
	
	
	/*
	 * Return a HashMap<String, String> when 
	 *  - the key means the name of networktype
	 *  - the values means the state of that networktype 
	 */
	public HashMap<String, String> getInfos(){
		readInfos(); // read actually informations
		HashMap<String, String> toReturn = new HashMap<String, String>();
		
		for(int i=0; i<names.size(); i++){
			toReturn.put(names.get(i), states.get(i));
        }
		
		return toReturn;
	}
	
	/*
	 * Returns the state of gift networktype OR
	 * retur null when the gift networktype is not in the list
	 */
	public String getState(String networkType){
		readInfos(); // read actually informations
		
		// try the with original input
		if( this.getInfos().get(networkType) != null )
			return this.getInfos().get(networkType);
		else{
			// try with only UPPERCASE
			if( this.getInfos().get(networkType.toUpperCase()) != null ) 
				return this.getInfos().get(networkType.toUpperCase());
			else{
				// try with only lowercase
				if( this.getInfos().get(networkType.toLowerCase()) != null ) 
					return this.getInfos().get(networkType.toLowerCase());
				else 
					return null;
			}
				
		}
	}
	
	/*
	 * Return TRUE if wifi OR Mobilnet is conncted
	 * else FALSE
	 */
	public boolean isConnectedNetwork(){
		HashMap<String, String> states = this.getInfos();
		
		if(states.get(TYPENAME_MOBILE).equals(STATE_CONNECTED))
			return true;
		else
			if(states.get(TYPENAME_WIFI).equals(STATE_CONNECTED)){
				return true;
			}else
				return false;
	}
}
