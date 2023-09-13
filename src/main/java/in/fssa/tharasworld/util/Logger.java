package in.fssa.tharasworld.util;

public class Logger {
	
	public static void error(Exception e) {
		
		e.printStackTrace();
		
	}
	
	public static void info(String msg) {
		
		System.out.println(msg);
		
	}

}
