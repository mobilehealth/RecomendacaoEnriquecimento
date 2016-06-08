package mobilehealth.client_desktop.testmanager;

import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import mobilehealth.prc.controller.ManagerServer;
import mobilehealth.core.domain.Context;
import mobilehealth.core.domain.Content;
import mobilehealth.core.domain.Device;
import mobilehealth.core.domain.Location;
import mobilehealth.core.domain.Person;


public class Main
{
	private static ManagerServer managerServer = ManagerServer.getInstance();
	
	// TODO CORRIGIR FALHA: nao se pode criar contexto para P2 (pois ele eh passivo)
	
	public static void main(String[] args) 
	{
		String opcaoStr = "";
		int opcaoInt = -1;
		
		do {
			opcaoStr = JOptionPane.showInputDialog(
					  "1 - FazerLogin \n"
					+ "2 - FazerLogout \n"		  
					+ "20 - Exibir Pessoa \n" 
					+ "21 - Criar Pessoa \n"
					+ "22 - Editar Pessoa \n"
					+ "23 - Remover Pessoa \n"
					+ "24 - Visualizar Pessoa \n"
					+ "25 - Rejeitar recomendacao de pessoa \n"
					+ "26 - Requsitar amizade \n"
					+ "27 - Rejeitar amizade \n"
					+ "28 - Aceitar amizade \n"
					+ "29 - Remover amizade \n"
					+ "200 - Cancelar requisitar amizade \n"
					+ "201 - Enviar Mensagem \n"
					+ "202 - Pesquisar Pessoa \n"
					+ "  30 - Exibir Conteudo \n "
					+ "  31 - Criar Conteudo \n "
					+ "  32 - Editar Conteudo \n "
					+ "  33 - Remover Conteudo \n"
					+ "  34 - Visualizar Conteudo \n "
					+ "  35 - Rejeitar recomendacao de conteudo \n "
					+ "  36 - Adicionar Conteudo \n"
					+ "  37 - Avaliar Conteudo \n "
					+ "  38 - Adicionar Comentario em Conteudo \n"
					+ "  39 - Pesquisar Conteudo \n"
					+ "40 - Exibir Lugar \n "
					+ "41 - Criar Lugar \n "
					+ "42 - Editar Lugar \n "
					+ "43 - Remover Lugar \n"
					+ "44 - Visualizar Lugar \n "
					+ "45 - Rejeitar recomendacao de lugar \n "
					+ "46 - Adicionar Lugar \n"
					+ "47 - Avaliar Lugar \n "
					+ "48 - Adicionar Comentario em Lugar \n"
					+ "49 - Pesquisar Lugar \n"
					+ "0 - SAIR \n"
					);
	
			opcaoInt = Integer.parseInt(opcaoStr);
			
			switch ( opcaoInt ) 
			{
				case 1: fazerLogin(); break;
				case 2: fazerLogout(); break;
	
				case 20: exibirPessoa(); break;
				case 21: criarPessoa(); break;
				case 22: editarPessoa(); break;
				case 23: removerPessoa(); break;
				case 24: visualizarPessoa(); break;
				case 25: rejeitarRecomendacaoPessoa(); break;
				case 26: requisitarAmizade(); break;
				case 27: rejeitarAmizade(); break;
				case 28: aceitarAmizade(); break;
				case 29: removerAmizade(); break;
				case 200: cancelarRequisitarAmizade(); break;
				case 201: enviarMensagem(); break;
				case 202: pesquisarPessoa(); break;
				
				case 30: exibirConteudo(); break; 
				case 31: criarConteudo(); break;
				case 32: editarConteudo(); break;
				case 33: removerConteudo(); break;
				case 34: visualizarConteudo(); break;
				case 35: rejeitarRecomendacaoConteudo(); break;
				case 36: adicionarConteudo(); break;
				case 37: avaliarConteudo(); break;
				case 38: adicionarComentarioConteudo(); break;
				case 39: pesquisarConteudo(); break;
				
				case 40: exibirLugar(); break;
				case 41: criarLugar(); break;
				case 42: editarLugar(); break;
				case 43: removerLugar(); break;
				case 44: visualizarLugar(); break;
				case 45: rejeitarRecomendacaoLugar(); break;
				case 46: adicionarLugar(); break;
				case 47: avaliarLugar(); break;
				case 48: adicionarComentarioLugar(); break;
				case 49: pesquisarLugar(); break;
				
				case 0: System.out.println("SAIR!!!");
				
				default: System.out.println("OPCAO ERRADA");
					break;
			}
		}
		while(opcaoInt != 0);
	}


	private static void pesquisarPessoa() 
	{
		int idPerson = Integer.parseInt( JOptionPane.showInputDialog("ID person?") );
		String palavra = JOptionPane.showInputDialog("palavra: ");
		String imeiDevice = "987654321"; // Android
		
    	// DEVICE (remover, quando for p/ Android)
		Device device = new Device();
		device.setImei(imeiDevice);

		// LOCATION (remover, quando for p/ Android)
    	Location location = new Location(); 
    	location.setLatitude(-5.17752);
    	location.setLongitude(-37.34093);
		
		// CONTEXTO
		Context contexto = new Context();
		contexto.setDevice(device);		// remover, quando for p/ Android
		contexto.setLocation(location);	// remover, quando for p/ Android
		contexto.setTimeStamp(Calendar.getInstance());	// remover, quando for p/ Android
		//contexto = fillOut.fillContext(contexto); // Descomentar quando for p/ Android
		
    	List<Person> listPerson = managerServer.searchPerson(idPerson, palavra, contexto);
    	System.out.println("listContent = " + listPerson);
    	showList(listPerson);
	}


	private static void pesquisarConteudo() 
	{
		int idPerson = Integer.parseInt( JOptionPane.showInputDialog("ID person?") );
		String palavra = JOptionPane.showInputDialog("palavra: ");
		String imeiDevice = "987654321"; // Android
		
    	// DEVICE (remover, quando for p/ Android)
		Device device = new Device();
		device.setImei(imeiDevice);

		// LOCATION (remover, quando for p/ Android)
    	Location location = new Location(); 
    	location.setLatitude(-5.17752);
    	location.setLongitude(-37.34093);
		
		// CONTEXTO
		Context contexto = new Context();
		contexto.setDevice(device);		// remover, quando for p/ Android
		contexto.setLocation(location);	// remover, quando for p/ Android
		contexto.setTimeStamp(Calendar.getInstance());	// remover, quando for p/ Android
		//contexto = fillOut.fillContext(contexto); // Descomentar quando for p/ Android
		
    	List<Content> listContent = managerServer.searchContent(idPerson, palavra, Content.TYPE_POST, contexto);
    	System.out.println("listContent = " + listContent);
    	showList(listContent);
	}
	
	
	private static void pesquisarLugar() 
	{
		int idPerson = Integer.parseInt( JOptionPane.showInputDialog("ID person?") );
		String palavra = JOptionPane.showInputDialog("palavra: ");
		String imeiDevice = "987654321"; // Android
		
    	// DEVICE (remover, quando for p/ Android)
		Device device = new Device();
		device.setImei(imeiDevice);

		// LOCATION (remover, quando for p/ Android)
    	Location location = new Location(); 
    	location.setLatitude(-5.17752);
    	location.setLongitude(-37.34093);
		
		// CONTEXTO
		Context contexto = new Context();
		contexto.setDevice(device);		// remover, quando for p/ Android
		contexto.setLocation(location);	// remover, quando for p/ Android
		contexto.setTimeStamp(Calendar.getInstance());	// remover, quando for p/ Android
		//contexto = fillOut.fillContext(contexto); // Descomentar quando for p/ Android
				
    	List<Location> listLocation = managerServer.searchLocation(idPerson, palavra, contexto);
    	System.out.println("listLocation = " + listLocation);
    	showList(listLocation);
	}


	//-----------------------------------------------------------------------------------------------------
	// SESSION
	//-----------------------------------------------------------------------------------------------------
	private static void fazerLogin() 
    {
		// TODO Android
		int idPerson = Integer.parseInt( JOptionPane.showInputDialog("ID person?") );
		String email = "email"+idPerson; // gui
		String pass = "pass"+idPerson; // gui
		String imeiDevice = "987654321"; // Android
		System.out.println("email = " + email);
		System.out.println("pass = " + pass);
		
    	// Encripta senha (criptografar antes de enviar)
    	String passEncripted = SecurityUbi.encodeMD5(pass);
    	
    	// DEVICE (remover, quando for p/ Android)
		Device device = new Device();
		device.setImei(imeiDevice);

		// LOCATION (remover, quando for p/ Android)
    	Location location = new Location(); 
    	location.setLatitude(-5.17752);
    	location.setLongitude(-37.34093);
		
		// CONTEXTO
		Context contexto = new Context();
		contexto.setDevice(device);		// remover, quando for p/ Android
		contexto.setLocation(location);	// remover, quando for p/ Android
		contexto.setTimeStamp(Calendar.getInstance());	// remover, quando for p/ Android
		//contexto = fillOut.fillContext(contexto); // Descomentar quando for p/ Android
    	
    	// login
    	boolean flagLogin = managerServer.login(email, passEncripted, contexto);
    	System.out.println("flagLogin = " + flagLogin);	
    }
    
    private static void fazerLogout() 
    {
    	// TODO Android
    	int idPerson = Integer.parseInt( JOptionPane.showInputDialog("ID person?") );
    	String imeiDevice = "987654321"; // Android
    	
    	// DEVICE (remover, quando for p/ Android)
		Device device = new Device();
		device.setImei(imeiDevice);

		// LOCATION (remover, quando for p/ Android)
    	Location location = new Location(); 
    	location.setLatitude(-5.17752);
    	location.setLongitude(-37.34093);
		
		// CONTEXTO
		Context contexto = new Context();
		contexto.setDevice(device);		// remover, quando for p/ Android
		contexto.setLocation(location);	// remover, quando for p/ Android
		contexto.setTimeStamp(Calendar.getInstance());	// remover, quando for p/ Android
		//contexto = fillOut.fillContext(contexto); // Descomentar quando for p/ Android

		boolean flagOut = managerServer.logout(idPerson, contexto);
		System.out.println("flagOut = " + flagOut);	
    }
    
	//-----------------------------------------------------------------------------------------------------
	// PERSON
	//-----------------------------------------------------------------------------------------------------
    private static void criarPessoa()
    {
    	// TODO Android
    	int idPerson = Integer.parseInt( JOptionPane.showInputDialog("ID person?") );
    	String nameFirst = "Name First " + idPerson;
    	String nameLast = "Name Last " + idPerson;
    	String email = "email" + idPerson;
    	String pass =  "pass" + idPerson;
    	Calendar dateBirth = Calendar.getInstance();
    	dateBirth.set(1984, 4, 23);
    	String imeiDevice = "987654321";
    	
    	// PassEncripted (criptografar antes de enviar)
    	String passEncripted = SecurityUbi.encodeMD5(pass);
    	
    	// PERSON
    	Person person = new Person();
    	person.setNameFirst(nameFirst);
    	person.setNameLast(nameLast);
    	person.setEmail(email);
        person.setDateBirth(dateBirth);
        person.setPassword(passEncripted);
    	
    	// DEVICE (remover, quando for p/ Android)
		Device device = new Device();
		device.setImei(imeiDevice);

		// LOCATION (remover, quando for p/ Android)
    	Location location = new Location(); 
    	location.setLatitude(-5.17752);
    	location.setLongitude(-37.34093);
		
		// CONTEXTO
		Context contexto = new Context();
		contexto.setDevice(device);		// remover, quando for p/ Android
		contexto.setLocation(location);	// remover, quando for p/ Android
		contexto.setTimeStamp(Calendar.getInstance());	// remover, quando for p/ Android
		//contexto = fillOut.fillContext(contexto); // Descomentar quando for p/ Android
		
		boolean flagCreatePerson = managerServer.createPerson(person, contexto);
		System.out.println("flagCreatePerson = " + flagCreatePerson);	
    }
    
	private static void editarPessoa() 
	{
    	// TODO Android (muitas outras informacoes)
		int idPerson = Integer.parseInt( JOptionPane.showInputDialog("ID person?") );
    	String nameFirst = "Name First Editado";
    	String nameLast = "Name Last Editado";
    	
    	// PassEncripted (criptografar antes de enviar)
    	//String passEncripted = SecurityUbi.encodeMD5(pass);
    	
    	// PERSON
    	Person person = managerServer.getPerson(idPerson);
    	if(person != null) 
    	{
	    	person.setNameFirst(nameFirst);
	    	person.setNameLast(nameLast);
	    	//person.setEmail(email);
	        //person.setDateBirth(dateBirth);
	        //person.setPass(passEncripted);
	    	
	    	String imeiDevice = "987654321"; // Android
	    	
	    	// DEVICE (remover, quando for p/ Android)
			Device device = new Device();
			device.setImei(imeiDevice);	// temp (remover quando descomentar o fillDevice)

			// LOCATION (remover, quando for p/ Android)
	    	Location location = new Location(); 
	    	location.setLatitude(-5.17752);
	    	location.setLongitude(-37.34093);
			
			// CONTEXTO
			Context contexto = new Context();
			contexto.setDevice(device);		// remover, quando for p/ Android
			contexto.setLocation(location);	// remover, quando for p/ Android
			contexto.setTimeStamp(Calendar.getInstance());	// remover, quando for p/ Android
			//contexto = fillOut.fillContext(contexto); // Descomentar quando for p/ Android
	
			boolean flagEditPerson =  managerServer.editPerson(person, contexto);
			System.out.println("flagEditPerson = " + flagEditPerson);
    	}
	}
	
	private static void removerPessoa() 
	{
		// TODO Android
		int idPerson = Integer.parseInt( JOptionPane.showInputDialog("ID person?") );
    	String imeiDevice = "987654321"; // Android
    	
    	// DEVICE (remover, quando for p/ Android)
		Device device = new Device();
		device.setImei(imeiDevice);	// temp (remover quando descomentar o fillDevice)

		// LOCATION (remover, quando for p/ Android)
    	Location location = new Location(); 
    	location.setLatitude(-5.17752);
    	location.setLongitude(-37.34093);
		
		// CONTEXTO
		Context contexto = new Context();
		contexto.setDevice(device);		// remover, quando for p/ Android
		contexto.setLocation(location);	// remover, quando for p/ Android
		contexto.setTimeStamp(Calendar.getInstance());	// remover, quando for p/ Android
		//contexto = fillOut.fillContext(contexto); // Descomentar quando for p/ Android
    	
    	boolean flagRemovePerson = managerServer.removePerson(idPerson, contexto);
		System.out.println("flagRemovePerson = " + flagRemovePerson);	
	}
	
	private static void visualizarPessoa() 
	{
		// TODO Android
		int idPerson1 = Integer.parseInt( JOptionPane.showInputDialog("ID person 1 ?") );
		int idPerson2 = Integer.parseInt( JOptionPane.showInputDialog("ID person 2 ?") );
    	String imeiDevice = "987654321"; // Android
    	
    	// DEVICE (remover, quando for p/ Android)
		Device device = new Device();
		device.setImei(imeiDevice);

		// LOCATION (remover, quando for p/ Android)
    	Location location = new Location(); 
    	location.setLatitude(-5.17752);
    	location.setLongitude(-37.34093);
		
		// CONTEXTO
		Context contexto = new Context();
		contexto.setDevice(device);		// remover, quando for p/ Android
		contexto.setLocation(location);	// remover, quando for p/ Android
		contexto.setTimeStamp(Calendar.getInstance());	// remover, quando for p/ Android
		//contexto = fillOut.fillContext(contexto); // Descomentar quando for p/ Android
    	
    	boolean flagViewPerson = managerServer.viewPerson(idPerson1, idPerson2, contexto);
		System.out.println("flagViewPerson = " + flagViewPerson);
	}
    
	private static void rejeitarRecomendacaoPessoa() 
	{
		// TODO Android
		int idPerson1 = Integer.parseInt( JOptionPane.showInputDialog("ID person 1 ?") );
		int idPerson2 = Integer.parseInt( JOptionPane.showInputDialog("ID person 2 ?") );
    	String imeiDevice = "987654321"; // Android
    	
    	// DEVICE (remover, quando for p/ Android)
		Device device = new Device();
		device.setImei(imeiDevice);

		// LOCATION (remover, quando for p/ Android)
    	Location location = new Location(); 
    	location.setLatitude(-5.17752);
    	location.setLongitude(-37.34093);
		
		// CONTEXTO
		Context contexto = new Context();
		contexto.setDevice(device);		// remover, quando for p/ Android
		contexto.setLocation(location);	// remover, quando for p/ Android
		contexto.setTimeStamp(Calendar.getInstance());	// remover, quando for p/ Android
		//contexto = fillOut.fillContext(contexto); // Descomentar quando for p/ Android

		boolean flagRejectRecommPerson = managerServer.rejectRecommPerson(idPerson1, idPerson2, contexto);
		System.out.println("flagRejectRecommPerson = " + flagRejectRecommPerson);
	}
	
	private static void requisitarAmizade() 
	{
		// TODO Android
		int idPerson1 = Integer.parseInt( JOptionPane.showInputDialog("ID person 1 ?") );
		int idPerson2 = Integer.parseInt( JOptionPane.showInputDialog("ID person 2 ?") );
    	String imeiDevice = "987654321"; // Android
    	
    	// DEVICE (remover, quando for p/ Android)
		Device device = new Device();
		device.setImei(imeiDevice);

		// LOCATION (remover, quando for p/ Android)
    	Location location = new Location(); 
    	location.setLatitude(-5.17752);
    	location.setLongitude(-37.34093);
		
		// CONTEXTO
		Context contexto = new Context();
		contexto.setDevice(device);		// remover, quando for p/ Android
		contexto.setLocation(location);	// remover, quando for p/ Android
		contexto.setTimeStamp(Calendar.getInstance());	// remover, quando for p/ Android
		//contexto = fillOut.fillContext(contexto); // Descomentar quando for p/ Android

		boolean flagRequestFriendship = managerServer.requestFriendship(idPerson1, idPerson2, contexto);
		System.out.println("flagRequestFriendship = " + flagRequestFriendship);
	}
	
	private static void cancelarRequisitarAmizade() 
	{
		// TODO Android
		int idPerson1 = Integer.parseInt( JOptionPane.showInputDialog("ID person 1 ?") );
		int idPerson2 = Integer.parseInt( JOptionPane.showInputDialog("ID person 2 ?") );
    	String imeiDevice = "987654321"; // Android
    	
    	// DEVICE (remover, quando for p/ Android)
		Device device = new Device();
		device.setImei(imeiDevice);	

		// LOCATION (remover, quando for p/ Android)
    	Location location = new Location(); 
    	location.setLatitude(-5.17752);
    	location.setLongitude(-37.34093);
		
		// CONTEXTO
		Context contexto = new Context();
		contexto.setDevice(device);		// remover, quando for p/ Android
		contexto.setLocation(location);	// remover, quando for p/ Android
		contexto.setTimeStamp(Calendar.getInstance());	// remover, quando for p/ Android
		//contexto = fillOut.fillContext(contexto); // Descomentar quando for p/ Android

		boolean flagCancelRequestFriendship = managerServer.cancelRequestFriendship(idPerson1, idPerson2, contexto);
		System.out.println("flagCancelRequestFriendship = " + flagCancelRequestFriendship);
	}
	
	private static void rejeitarAmizade() 
	{
		// TODO Android
		int idPerson1 = Integer.parseInt( JOptionPane.showInputDialog("ID person 1 ?") );
		int idPerson2 = Integer.parseInt( JOptionPane.showInputDialog("ID person 2 ?") );
    	String imeiDevice = "987654321"; // Android
    	
    	// DEVICE (remover, quando for p/ Android)
		Device device = new Device();
		device.setImei(imeiDevice);

		// LOCATION (remover, quando for p/ Android)
    	Location location = new Location(); 
    	location.setLatitude(-5.17752);
    	location.setLongitude(-37.34093);
		
		// CONTEXTO
		Context contexto = new Context();
		contexto.setDevice(device);		// remover, quando for p/ Android
		contexto.setLocation(location);	// remover, quando for p/ Android
		contexto.setTimeStamp(Calendar.getInstance());	// remover, quando for p/ Android
		//contexto = fillOut.fillContext(contexto); // Descomentar quando for p/ Android

		boolean flagRejectFriendship = managerServer.rejectFriendship(idPerson1, idPerson2, contexto);
		System.out.println("flagRejectFriendship = " + flagRejectFriendship);
	}
	
	private static void aceitarAmizade() 
	{
		// TODO Android
		int idPerson1 = Integer.parseInt( JOptionPane.showInputDialog("ID person 1 ?") );
		int idPerson2 = Integer.parseInt( JOptionPane.showInputDialog("ID person 2 ?") );
    	String imeiDevice = "987654321"; // Android
    	
    	// DEVICE (remover, quando for p/ Android)
		Device device = new Device();
		device.setImei(imeiDevice);

		// LOCATION (remover, quando for p/ Android)
    	Location location = new Location(); 
    	location.setLatitude(-5.17752);
    	location.setLongitude(-37.34093);
		
		// CONTEXTO
		Context contexto = new Context();
		contexto.setDevice(device);		// remover, quando for p/ Android
		contexto.setLocation(location);	// remover, quando for p/ Android
		contexto.setTimeStamp(Calendar.getInstance());	// remover, quando for p/ Android
		//contexto = fillOut.fillContext(contexto); // Descomentar quando for p/ Android

		boolean flagAcceptFriendship = managerServer.acceptFriendship(idPerson1, idPerson2, contexto);	
		System.out.println("flagAcceptFriendship = " + flagAcceptFriendship);
	}
	
	private static void removerAmizade() 
	{
		// TODO Android
		int idPerson1 = Integer.parseInt( JOptionPane.showInputDialog("ID person 1 ?") );
		int idPerson2 = Integer.parseInt( JOptionPane.showInputDialog("ID person 2 ?") );
    	String imeiDevice = "987654321"; // Android
    	
    	// DEVICE (remover, quando for p/ Android)
		Device device = new Device();
		device.setImei(imeiDevice);

		// LOCATION (remover, quando for p/ Android)
    	Location location = new Location(); 
    	location.setLatitude(-5.17752);
    	location.setLongitude(-37.34093);
		
		// CONTEXTO
		Context contexto = new Context();
		contexto.setDevice(device);		// remover, quando for p/ Android
		contexto.setLocation(location);	// remover, quando for p/ Android
		contexto.setTimeStamp(Calendar.getInstance());	// remover, quando for p/ Android
		//contexto = fillOut.fillContext(contexto); // Descomentar quando for p/ Android

		boolean flagRemoveFriendship = managerServer.removeFriendship(idPerson1, idPerson2, contexto);
		System.out.println("flagRemoveFriendship = " + flagRemoveFriendship);
	}
	
	private static void enviarMensagem() 
	{
		// TODO Android
		int idPerson1 = Integer.parseInt( JOptionPane.showInputDialog("ID 1 ?") );
		int idPerson2 = Integer.parseInt( JOptionPane.showInputDialog("ID 2 ?") );
    	String msg = "Alow testando";
    	String imeiDevice = "987654321"; // Android
    	
    	// DEVICE (remover, quando for p/ Android)
		Device device = new Device();
		device.setImei(imeiDevice);

		// LOCATION (remover, quando for p/ Android)
    	Location location = new Location(); 
    	location.setLatitude(-5.17752);
    	location.setLongitude(-37.34093);
		
		// CONTEXTO
		Context contexto = new Context();
		contexto.setDevice(device);		// remover, quando for p/ Android
		contexto.setLocation(location);	// remover, quando for p/ Android
		contexto.setTimeStamp(Calendar.getInstance());	// remover, quando for p/ Android
		//contexto = fillOut.fillContext(contexto); // Descomentar quando for p/ Android

		boolean flagSendMessage = managerServer.sendMessage(idPerson1, idPerson2, msg, contexto);
		System.out.println("flagSendMessage = " + flagSendMessage);
	}

	//-----------------------------------------------------------------------------------------------------
	// CONTENT
	//-----------------------------------------------------------------------------------------------------
	private static void criarConteudo() 
	{
		// TODO Android
		int idPerson = Integer.parseInt( JOptionPane.showInputDialog("ID person ?") );
		int idContent = Integer.parseInt( JOptionPane.showInputDialog("ID content ?") );
		String title = "Title " + idContent;
		String description = "Description #legal #matematica" + idContent;
		
		// CONTENT
		Content content = new Content();
		content.setTitle(title);
		content.setDescription(description);
		content.setType(Content.TYPE_POST);
		content.setSubtype(Content.SUBTYPE_POST_TEXT);	// IMPORTANTE!
		
    	String imeiDevice = "987654321"; // Android
    	
    	// DEVICE (remover, quando for p/ Android)
		Device device = new Device();
		device.setImei(imeiDevice);

		// LOCATION (remover, quando for p/ Android)
    	Location location = new Location(); 
    	location.setLatitude(-5.17752);
    	location.setLongitude(-37.34093);
		
		// CONTEXTO
		Context contexto = new Context();
		contexto.setDevice(device);		// remover, quando for p/ Android
		contexto.setLocation(location);	// remover, quando for p/ Android
		contexto.setTimeStamp(Calendar.getInstance());	// remover, quando for p/ Android
		//contexto = fillOut.fillContext(contexto); // Descomentar quando for p/ Android
		
		boolean flagCreateContent = managerServer.createContent(idPerson, content, contexto);
		System.out.println("flagCreateContent = " + flagCreateContent);
	}
	
	private static void editarConteudo() 
	{
		// TODO Android
		int idPerson = Integer.parseInt( JOptionPane.showInputDialog("ID person ?") );
		int idContent = Integer.parseInt( JOptionPane.showInputDialog("ID content ?") );
		String title = "Title Editado " + idContent;
		String description = "Description Editado " + idContent;
		
		// CONTENT
		Content content = managerServer.getContent(idContent);
		content.setTitle(title);
		content.setDescription(description);
		
    	String imeiDevice = "987654321"; // Android
    	
    	// DEVICE (remover, quando for p/ Android)
		Device device = new Device();
		device.setImei(imeiDevice);	

		// LOCATION (remover, quando for p/ Android)
    	Location location = new Location(); 
    	location.setLatitude(-5.17752);
    	location.setLongitude(-37.34093);
		
		// CONTEXTO
		Context contexto = new Context();
		contexto.setDevice(device);		// remover, quando for p/ Android
		contexto.setLocation(location);	// remover, quando for p/ Android
		contexto.setTimeStamp(Calendar.getInstance());	// remover, quando for p/ Android
		//contexto = fillOut.fillContext(contexto); // Descomentar quando for p/ Android
		
		boolean flagEditContent = managerServer.editContent(idPerson, content, contexto);
		System.out.println("flagEditContent = " + flagEditContent);
	}
	
	private static void visualizarConteudo() 
	{
		// TODO Android
		int idPerson = Integer.parseInt( JOptionPane.showInputDialog("ID person ?") );
		int idContent = Integer.parseInt( JOptionPane.showInputDialog("ID content ?") );
    	String imeiDevice = "987654321"; // Android
    	
    	// DEVICE (remover, quando for p/ Android)
		Device device = new Device();
		device.setImei(imeiDevice);	

		// LOCATION (remover, quando for p/ Android)
    	Location location = new Location(); 
    	location.setLatitude(-5.17752);
    	location.setLongitude(-37.34093);
		
		// CONTEXTO
		Context contexto = new Context();
		contexto.setDevice(device);		// remover, quando for p/ Android
		contexto.setLocation(location);	// remover, quando for p/ Android
		contexto.setTimeStamp(Calendar.getInstance());	// remover, quando for p/ Android
		//contexto = fillOut.fillContext(contexto); // Descomentar quando for p/ Android
		
		boolean flagViewContent = managerServer.viewContent(idPerson, idContent, contexto);
		System.out.println("flagViewContent = " + flagViewContent);
	}
	
	private static void rejeitarRecomendacaoConteudo() 
	{
		// TODO Android
		int idPerson = Integer.parseInt( JOptionPane.showInputDialog("ID person ?") );
		int idContent = Integer.parseInt( JOptionPane.showInputDialog("ID content ?") );
    	String imeiDevice = "987654321"; // Android
    	
    	// DEVICE (remover, quando for p/ Android)
		Device device = new Device();
		device.setImei(imeiDevice);	

		// LOCATION (remover, quando for p/ Android)
    	Location location = new Location(); 
    	location.setLatitude(-5.17752);
    	location.setLongitude(-37.34093);
		
		// CONTEXTO
		Context contexto = new Context();
		contexto.setDevice(device);		// remover, quando for p/ Android
		contexto.setLocation(location);	// remover, quando for p/ Android
		contexto.setTimeStamp(Calendar.getInstance());	// remover, quando for p/ Android
		//contexto = fillOut.fillContext(contexto); // Descomentar quando for p/ Android
		
		boolean flagRejectRecommContent = managerServer.rejectRecommContent(idPerson, idContent, contexto);
		System.out.println("flagRejectRecommContent = " + flagRejectRecommContent);
	}
	
	private static void adicionarConteudo() 
	{
		// TODO Android
		int idPerson = Integer.parseInt( JOptionPane.showInputDialog("ID person ?") );
		int idContent = Integer.parseInt( JOptionPane.showInputDialog("ID content ?") );
    	String imeiDevice = "987654321"; // Android
    	
    	// DEVICE (remover, quando for p/ Android)
		Device device = new Device();
		device.setImei(imeiDevice);	

		// LOCATION (remover, quando for p/ Android)
    	Location location = new Location(); 
    	location.setLatitude(-5.17752);
    	location.setLongitude(-37.34093);
		
		// CONTEXTO
		Context contexto = new Context();
		contexto.setDevice(device);		// remover, quando for p/ Android
		contexto.setLocation(location);	// remover, quando for p/ Android
		contexto.setTimeStamp(Calendar.getInstance());	// remover, quando for p/ Android
		//contexto = fillOut.fillContext(contexto); // Descomentar quando for p/ Android
		
		boolean flagAddContent = managerServer.addContent(idPerson, idContent, contexto);
		System.out.println("flagAddContent = " + flagAddContent);
	}
	
	private static void removerConteudo() 
	{
		// TODO Android
		int idPerson = Integer.parseInt( JOptionPane.showInputDialog("ID person ?") );
		int idContent = Integer.parseInt( JOptionPane.showInputDialog("ID content ?") );
    	String imeiDevice = "987654321"; // Android
    	
    	// DEVICE (remover, quando for p/ Android)
		Device device = new Device();
		device.setImei(imeiDevice);	

		// LOCATION (remover, quando for p/ Android)
    	Location location = new Location(); 
    	location.setLatitude(-5.17752);
    	location.setLongitude(-37.34093);
		
		// CONTEXTO
		Context contexto = new Context();
		contexto.setDevice(device);		// remover, quando for p/ Android
		contexto.setLocation(location);	// remover, quando for p/ Android
		contexto.setTimeStamp(Calendar.getInstance());	// remover, quando for p/ Android
		//contexto = fillOut.fillContext(contexto); // Descomentar quando for p/ Android
		
		boolean flagRemoveContent = managerServer.removeContent(idPerson, idContent, contexto);
		System.out.println("flagRemoveContent = " + flagRemoveContent);
	}
	
	private static void avaliarConteudo() 
	{
		// TODO Android
		int idPerson = Integer.parseInt( JOptionPane.showInputDialog("ID person ?") );
		int idContent = Integer.parseInt( JOptionPane.showInputDialog("ID content ?") );
		int rate = 4;
    	String imeiDevice = "987654321"; // Android
    	
    	// DEVICE (remover, quando for p/ Android)
		Device device = new Device();
		device.setImei(imeiDevice);	

		// LOCATION (remover, quando for p/ Android)
    	Location location = new Location(); 
    	location.setLatitude(-5.17752);
    	location.setLongitude(-37.34093);
		
		// CONTEXTO
		Context contexto = new Context();
		contexto.setDevice(device);		// remover, quando for p/ Android
		contexto.setLocation(location);	// remover, quando for p/ Android
		contexto.setTimeStamp(Calendar.getInstance());	// remover, quando for p/ Android
		//contexto = fillOut.fillContext(contexto); // Descomentar quando for p/ Android
		
		boolean flagRateContent = managerServer.rateContent(idPerson, idContent, rate, contexto);
		System.out.println("flagRateContent = " + flagRateContent);
	}
	
	private static void adicionarComentarioConteudo() 
	{
		// TODO Android
		int idPerson = Integer.parseInt( JOptionPane.showInputDialog("ID person ?") );
		int idContent = Integer.parseInt( JOptionPane.showInputDialog("ID content ?") );
		String comment = "Meu comentario #legal de teste eh #massa tb";
    	String imeiDevice = "987654321"; // Android
    	
    	// DEVICE (remover, quando for p/ Android)
		Device device = new Device();
		device.setImei(imeiDevice);	

		// LOCATION (remover, quando for p/ Android)
    	Location location = new Location(); 
    	location.setLatitude(-5.17752);
    	location.setLongitude(-37.34093);
		
		// CONTEXTO
		Context contexto = new Context();
		contexto.setDevice(device);		// remover, quando for p/ Android
		contexto.setLocation(location);	// remover, quando for p/ Android
		contexto.setTimeStamp(Calendar.getInstance());	// remover, quando for p/ Android
		//contexto = fillOut.fillContext(contexto); // Descomentar quando for p/ Android
		
		boolean flagCommentContent = managerServer.commentContent(idPerson, idContent, comment, contexto);
		System.out.println("flagCommentContent = " + flagCommentContent);
	}
	
	//-----------------------------------------------------------------------------------------------------
	// LOCATION
	//-----------------------------------------------------------------------------------------------------
	private static void criarLugar() 
	{
		// TODO Android
		int idPerson = Integer.parseInt( JOptionPane.showInputDialog("ID person ?") );
		int idLocation = Integer.parseInt( JOptionPane.showInputDialog("ID location ?") );
		String titile = "Title " + idLocation;
		String description = "Description #lugar " + idLocation;
		
		// LOCATION
		Location locationCreated = new Location();
		locationCreated.setTitle(titile);
		locationCreated.setDescription(description);
		// location = fillOut.fillLocation(location); // TODO Android
		
    	String imeiDevice = "987654321"; // Android
    	
    	// DEVICE (remover, quando for p/ Android)
		Device device = new Device();
		device.setImei(imeiDevice);	

		// LOCATION (remover, quando for p/ Android)
    	Location location = new Location(); 
    	location.setLatitude(-5.17752);
    	location.setLongitude(-37.34093);
		
		// CONTEXTO
		Context contexto = new Context();
		contexto.setDevice(device);		// remover, quando for p/ Android
		contexto.setLocation(location);	// remover, quando for p/ Android
		contexto.setTimeStamp(Calendar.getInstance());	// remover, quando for p/ Android
		//contexto = fillOut.fillContext(contexto); // Descomentar quando for p/ Android
		
		// TODO Duvida: Local criado (pelo usuario) eh diferente do local armazenado em context?
		
		boolean flagCreateLocation = managerServer.createLocation(idPerson, locationCreated, contexto);
		System.out.println("flagCreateLocation = " + flagCreateLocation);
	}
	
	private static void editarLugar() 
	{
		// TODO Android
		int idPerson = Integer.parseInt( JOptionPane.showInputDialog("ID person ?") );
		int idLocation = Integer.parseInt( JOptionPane.showInputDialog("ID location ?") );
		String titile = "Title Editado";
		String description = "Description Editado";
		
		// LOCATION
		Location locationEdited = managerServer.getLocation(idLocation);
		locationEdited.setTitle(titile);
		locationEdited.setDescription(description);
		// location = fillOut.fillLocation(location); // TODO Android
		
    	String imeiDevice = "987654321"; // Android
    	
    	// DEVICE (remover, quando for p/ Android)
		Device device = new Device();
		device.setImei(imeiDevice);	

		// LOCATION (remover, quando for p/ Android)
    	Location location = new Location(); 
    	location.setLatitude(-5.17752);
    	location.setLongitude(-37.34093);
		
		// CONTEXTO
		Context contexto = new Context();
		contexto.setDevice(device);		// remover, quando for p/ Android
		contexto.setLocation(location);	// remover, quando for p/ Android
		contexto.setTimeStamp(Calendar.getInstance());	// remover, quando for p/ Android
		//contexto = fillOut.fillContext(contexto); // Descomentar quando for p/ Android
		
		boolean flagEditLocation = managerServer.editLocation(idPerson, locationEdited, contexto);
		System.out.println("flagEditLocation = " + flagEditLocation);
	}
	
	private static void visualizarLugar() 
	{
		// TODO Android
		int idPerson = Integer.parseInt( JOptionPane.showInputDialog("ID person ?") );
		int idLocation = Integer.parseInt( JOptionPane.showInputDialog("ID location ?") );
		
    	String imeiDevice = "987654321"; // Android
    	
    	// DEVICE (remover, quando for p/ Android)
		Device device = new Device();
		device.setImei(imeiDevice);	

		// LOCATION (remover, quando for p/ Android)
    	Location location = new Location(); 
    	location.setLatitude(-5.17752);
    	location.setLongitude(-37.34093);
		
		// CONTEXTO
		Context contexto = new Context();
		contexto.setDevice(device);		// remover, quando for p/ Android
		contexto.setLocation(location);	// remover, quando for p/ Android
		contexto.setTimeStamp(Calendar.getInstance());	// remover, quando for p/ Android
		//contexto = fillOut.fillContext(contexto); // Descomentar quando for p/ Android
		
		boolean flagViewLocation = managerServer.viewLocation(idPerson, idLocation, contexto);
		System.out.println("flagViewLocation = " + flagViewLocation);
	}
	
	private static void adicionarLugar() 
	{
		// TODO Android
		int idPerson = Integer.parseInt( JOptionPane.showInputDialog("ID person ?") );
		int idLocation = Integer.parseInt( JOptionPane.showInputDialog("ID location ?") );
		
    	String imeiDevice = "987654321"; // Android
    	
    	// DEVICE (remover, quando for p/ Android)
		Device device = new Device();
		device.setImei(imeiDevice);	

		// LOCATION (remover, quando for p/ Android)
    	Location location = new Location(); 
    	location.setLatitude(-5.17752);
    	location.setLongitude(-37.34093);
		
		// CONTEXTO
		Context contexto = new Context();
		contexto.setDevice(device);		// remover, quando for p/ Android
		contexto.setLocation(location);	// remover, quando for p/ Android
		contexto.setTimeStamp(Calendar.getInstance());	// remover, quando for p/ Android
		//contexto = fillOut.fillContext(contexto); // Descomentar quando for p/ Android
		
		boolean flagAddLocation = managerServer.addLocation(idPerson, idLocation, contexto);
		System.out.println("flagAddLocation = " + flagAddLocation);
	}
	
	
	private static void removerLugar() 
	{
		// TODO Android
		int idPerson = Integer.parseInt( JOptionPane.showInputDialog("ID person ?") );
		int idLocation = Integer.parseInt( JOptionPane.showInputDialog("ID location ?") );
		
    	String imeiDevice = "987654321"; // Android
    	
    	// DEVICE (remover, quando for p/ Android)
		Device device = new Device();
		device.setImei(imeiDevice);	

		// LOCATION (remover, quando for p/ Android)
    	Location location = new Location(); 
    	location.setLatitude(-5.17752);
    	location.setLongitude(-37.34093);
		
		// CONTEXTO
		Context contexto = new Context();
		contexto.setDevice(device);		// remover, quando for p/ Android
		contexto.setLocation(location);	// remover, quando for p/ Android
		contexto.setTimeStamp(Calendar.getInstance());	// remover, quando for p/ Android
		//contexto = fillOut.fillContext(contexto); // Descomentar quando for p/ Android
		
		boolean flagRemoveLocation = managerServer.removeLocation(idPerson, idLocation, contexto);
		System.out.println("flagRemoveLocation = " + flagRemoveLocation);
	}
	
	private static void avaliarLugar() 
	{
		// TODO Android
		int idPerson = Integer.parseInt( JOptionPane.showInputDialog("ID person ?") );
		int idLocation = Integer.parseInt( JOptionPane.showInputDialog("ID location ?") );
		int rate = 2;
		
    	String imeiDevice = "987654321"; // Android
    	
    	// DEVICE (remover, quando for p/ Android)
		Device device = new Device();
		device.setImei(imeiDevice);	

		// LOCATION (remover, quando for p/ Android)
    	Location location = new Location(); 
    	location.setLatitude(-5.17752);
    	location.setLongitude(-37.34093);
		
		// CONTEXTO
		Context contexto = new Context();
		contexto.setDevice(device);		// remover, quando for p/ Android
		contexto.setLocation(location);	// remover, quando for p/ Android
		contexto.setTimeStamp(Calendar.getInstance());	// remover, quando for p/ Android
		//contexto = fillOut.fillContext(contexto); // Descomentar quando for p/ Android
		
		boolean flagRateLocation = managerServer.rateLocation(idPerson, idLocation, rate, contexto);
		System.out.println("flagRateLocation = " + flagRateLocation);
	}

	private static void rejeitarRecomendacaoLugar() 
	{
		// TODO Android
		int idPerson = Integer.parseInt( JOptionPane.showInputDialog("ID person ?") );
		int idLocation = Integer.parseInt( JOptionPane.showInputDialog("ID location ?") );
		
    	String imeiDevice = "987654321"; // Android
    	
    	// DEVICE (remover, quando for p/ Android)
		Device device = new Device();
		device.setImei(imeiDevice);	

		// LOCATION (remover, quando for p/ Android)
    	Location location = new Location(); 
    	location.setLatitude(-5.17752);
    	location.setLongitude(-37.34093);
		
		// CONTEXTO
		Context contexto = new Context();
		contexto.setDevice(device);		// remover, quando for p/ Android
		contexto.setLocation(location);	// remover, quando for p/ Android
		contexto.setTimeStamp(Calendar.getInstance());	// remover, quando for p/ Android
		//contexto = fillOut.fillContext(contexto); // Descomentar quando for p/ Android
		
		boolean flagRejectRecommLocation = managerServer.rejectRecommLocation(idPerson, idLocation, contexto);
		System.out.println("flagRejectRecommLocation = " + flagRejectRecommLocation);
	}

	private static void adicionarComentarioLugar() 
	{
		// TODO Android
		int idPerson = Integer.parseInt( JOptionPane.showInputDialog("ID person ?") );
		int idLocation = Integer.parseInt( JOptionPane.showInputDialog("ID location ?") );
		String comment = "Meu #irado comentario de #LEGAL teste para esse lugar #legal";
		
    	String imeiDevice = "987654321"; // Android
    	
    	// DEVICE (remover, quando for p/ Android)
		Device device = new Device();
		device.setImei(imeiDevice);

		// LOCATION (remover, quando for p/ Android)
    	Location location = new Location(); 
    	location.setLatitude(-5.17752);
    	location.setLongitude(-37.34093);
		
		// CONTEXTO
		Context contexto = new Context();
		contexto.setDevice(device);		// remover, quando for p/ Android
		contexto.setLocation(location);	// remover, quando for p/ Android
		contexto.setTimeStamp(Calendar.getInstance());	// remover, quando for p/ Android
		//contexto = fillOut.fillContext(contexto); // Descomentar quando for p/ Android
		
		boolean flagCommentLocation = managerServer.commentLocation(idPerson, idLocation, comment, contexto);
		System.out.println("flagCommentLocation = " + flagCommentLocation);
	}
	

	
    private static <E> void showList(List<E> list)
    {
    	for (Object object : list) {
    		System.out.println( object.toString() );
		}
    }

    private static void exibirPessoa() // OK
    {
		int idPerson = Integer.parseInt( JOptionPane.showInputDialog("ID person ?") );
    	Person person = managerServer.getPerson(idPerson);
    	System.out.println("person = " + person);
    	
    	System.out.println( "person.getAge = " 			+ person.getAge() );
    	System.out.println( "person.getEmail = " 		+ person.getEmail() );
    	System.out.println( "person.getGenre = " 		+ person.getGender() );
    	System.out.println( "person.getIncome = " 		+ person.getIncome() );
    	System.out.println( "person.getNameFirst = " 	+ person.getNameFirst() );
    	System.out.println( "person.getNameLast = " 	+ person.getNameLast() );
    	System.out.println( "person.getPass = " 		+ person.getPassword() );
    	System.out.println( "person.getPhone = " 		+ person.getPhone() );
    	System.out.println( "person.getDisease = " 		+ person.getDisease() );
    	System.out.println( "person.getRace = " 		+ person.getRace() );
    	System.out.println( "person.getRateDurationContents = " + person.getRateDurationContents() );
    	System.out.println( "person.getRating = " 		+ person.getRating() );
    	System.out.println( "person.getReligion = " 	+ person.getReligion() );
    	System.out.println( "person.getId = " 			+ person.getId() );
    	
    	System.out.println( "person.getDateBirth = " 	+ person.getDateBirth() );
    	System.out.println( "person.getDateRegister = " + person.getDateRegister() );
    	System.out.println( "person.getFile = " 		+ person.getFile() );
    	System.out.println( "person.getFrequency = " 	+ person.getFrequency() );
    	System.out.println( "person.getLearning = " 	+ person.getLearning() );
    	
    	System.out.println( "person.getListContexts = " + person.getListContexts().size() ); 
    	showList( person.getListContexts() );
    	
    	System.out.println( "person.getListDevices = " 	+ person.getListDevices().size() );
    	showList( person.getListDevices() );
    	
    	System.out.println( "person.getListExternalAccounts = " + person.getListExternalAccounts().size()  );
    	showList( person.getListExternalAccounts() );
    	
    	System.out.println( "person.getListRelatePersonContent = " + person.getListRelatePersonContent().size()  );
    	showList( person.getListRelatePersonContent() );
    	
    	System.out.println( "person.getListRelatePersonLocation = " + person.getListRelatePersonLocation().size()  );
    	showList( person.getListRelatePersonLocation() );
    	
    	System.out.println( "person.getListRelatePersonPerson = " + person.getListRelatePersonPerson().size()  );
    	showList( person.getListRelatePersonPerson() );
    	
    	System.out.println( "person.getListRelatePersonTag = " 	+ person.getListRelatePersonTag().size()  );
    	showList( person.getListRelatePersonTag() );
    	
    	System.out.println( "Mensagens de chat: ");
    	for (int i = 0; i < person.getListRelatePersonPerson().size() ; i++) 
    	{
    		System.out.println("RelatePersonPerson: " + 
    				person.getListRelatePersonPerson().get(i).getPerson1().getId() + " " + 
    				person.getListRelatePersonPerson().get(i).getPerson2().getId() + " : ");
    		//showList( person.getListRelatePersonPerson().get(i).getListMessages() );
		}
    	
    	System.out.println( "Comentarios em Conteudos: ");
    	for (int i = 0; i < person.getListRelatePersonContent().size() ; i++) 
    	{
    		System.out.println("RelatePersonContent: " + 
    				person.getListRelatePersonContent().get(i).getPerson().getId() + " " + 
    				person.getListRelatePersonContent().get(i).getContent().getId() + " : ");
    		//showList( person.getListRelatePersonContent().get(i).getListComments() );
		}
    	
    	
	}
    

	private static void exibirConteudo() 
	{
		int idContent = Integer.parseInt( JOptionPane.showInputDialog("ID content ?") );
    	Content content = managerServer.getContent(idContent);
    	System.out.println("content = " + content);
		
    	System.out.println( "content.getAge = " + content.getAge() );
    	System.out.println( "content.getBytesOnline = " + content.getBytesOnline() );
    	System.out.println( "content.getDescription = " + content.getDescription() );
    	System.out.println( "content.getRateAcceptance = " + content.getRateAcceptance() );
    	System.out.println( "content.getRateColabPonder = " + content.getRateColabPonder() );
    	System.out.println( "content.getRating = " + content.getRating() );
    	System.out.println( "content.getSecondsOnline = " + content.getSecondsOnline() );
    	System.out.println( "content.getSubtype = " + content.getSubtype() );
    	System.out.println( "content.getTitle = " + content.getTitle() );
    	System.out.println( "content.getType = " + content.getType() );
    	System.out.println( "content.getUrlOnline = " + content.getUrlOnline() );
    	System.out.println( "content.getVisibility = " + content.getVisibility() );
    	System.out.println( "content.getVisibilityGroup = " + content.getVisibilityGroup() );
    	
    	System.out.println( "content.getId = " + content.getId() );
    	System.out.println( "content.getAutor = " + content.getAuthor() );
    	System.out.println( "content.getDateCreation = " + content.getDateCreation() );
    	System.out.println( "content.getDateEvent = " + content.getDateEvent() );
    	System.out.println( "content.getFile = " + content.getFile() );
    	System.out.println( "content.getFrequency = " + content.getFrequency() );
    	
    	//System.out.println( "content.getListAlternatives = " + content.getListAlternatives().size() );
    	//showList( content.getListAlternatives() );
    	
    	System.out.println( "content.getListRelateContentLocation = " + content.getListRelateContentLocation().size() );
    	showList( content.getListRelateContentLocation() );
    	
    	System.out.println( "content.getListRelatePersonContent = " + content.getListRelatePersonContent().size() );
    	showList( content.getListRelatePersonContent() );
    	
    	System.out.println( "content.getListRelateContentTag = " + content.getListRelateContentTag().size() );
    	showList( content.getListRelateContentTag() );
	}
	
	
	private static void exibirLugar() 
	{
		int idLocation = Integer.parseInt( JOptionPane.showInputDialog("ID location ?") );
    	Location location = managerServer.getLocation(idLocation);
    	System.out.println("location = " + location);
		
    	System.out.println( "location.getAltitude = " + location.getAltitude() );
    	System.out.println( "location.getCEP = " + location.getCEP() );
    	System.out.println( "location.getCity = " + location.getCity() );
    	System.out.println( "location.getCountry = " + location.getCountry() );
    	System.out.println( "location.getDescription = " + location.getDescription() );
    	System.out.println( "location.getIdGoogle = " + location.getIdGoogle() );
    	System.out.println( "location.getLatitude = " + location.getLatitude() );
    	System.out.println( "location.getLongitude = " + location.getLongitude() );
    	System.out.println( "location.getNeighborhood = " + location.getNeighborhood() );
    	System.out.println( "location.getRateAcceptanceHere = " + location.getRateAcceptanceHere() );
    	System.out.println( "location.getRateRatingHere = " + location.getRateRatingHere() );
    	System.out.println( "location.getRating = " + location.getRating() );
    	System.out.println( "location.getState = " + location.getState() );
    	System.out.println( "location.getStreetName = " + location.getStreetName() );
    	System.out.println( "location.getStreetNumber = " + location.getStreetNumber() );
    	System.out.println( "location.getTitle = " + location.getTitle() );
    	System.out.println( "location.getId = " + location.getId() );
    	System.out.println( "location.getDateCreation = " + location.getDateCreation() );
    	System.out.println( "location.getFrequency = " + location.getFrequency() );
    	
    	System.out.println( "location.getListRelateContentLocation = " + location.getListRelateContentLocation().size() );
    	showList( location.getListRelateContentLocation() );
    	
    	System.out.println( "location.getListRelateLocationTag = " + location.getListRelateLocationTag().size() );
    	showList( location.getListRelateLocationTag() );
    	
    	System.out.println( "location.getListTypes = " + location.getListTypes().size() );
    	showList( location.getListTypes() );
	}    

}
