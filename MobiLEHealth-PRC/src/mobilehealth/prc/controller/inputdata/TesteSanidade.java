package mobilehealth.prc.controller.inputdata;

import mobilehealth.prc.controller.ManagerServer;
import mobilehealth.core.domain.Context;

public class TesteSanidade 
{
	private static ManagerServer managerServer = ManagerServer.getInstance();
	
	public static void main(String[] args) 
	{
		System.out.println("Desktop - login");
		boolean result = managerServer.login("qualquercoisamaluca", "jgsjagshjgj", new Context() );
		System.out.println("RESULT: " + result );
	}
}
