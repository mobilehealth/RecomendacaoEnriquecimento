package mobilehealth.client_desktop.testmanager;


import mobilehealth.prc.controller.ManagerServer;

public class TesteSanidade 
{
	private static ManagerServer managerServer = ManagerServer.getInstance();
	
	public static void main(String[] args) 
	{
		System.out.println("Desktop - login");
		boolean result = managerServer.login("qualquercoisamaluca", "jgsjagshjgj", null);
		System.out.println("RESULT: " + result );
	}
}
