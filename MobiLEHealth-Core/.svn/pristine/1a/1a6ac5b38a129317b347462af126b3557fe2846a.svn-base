package mobilehealth.client_desktop.simulation;

import javax.swing.*;  

import mobilehealth.prc.api.external.FacadeLocal;
import mobilehealth.prc.api.external.IUbi;
import mobilehealth.core.domain.Content;
import mobilehealth.core.domain.Context;
import mobilehealth.core.domain.Frequency;
import mobilehealth.core.domain.Learning;
import mobilehealth.core.domain.Location;
import mobilehealth.core.domain.Person;
import mobilehealth.core.domain.RelatePersonContent;
import mobilehealth.core.domain.RelatePersonLocation;
import mobilehealth.core.domain.RelatePersonPerson;
import mobilehealth.prc.controller.ManagerServer;
import mobilehealth.prc.recommendation.SemelhancaSemantica;
import  mobilehealth.prc.recommendation.Ubiqua;

import java.awt.Font;
import java.awt.event.*; 
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;



public class ViewSimulacao extends JFrame implements ActionListener {

	IUbi iUbi = FacadeLocal.getInstance();
	//private static ManagerServer iUbi = ManagerServer.getInstance();

	
	private ArrayList<RelatePersonContent> resultadoRecomendacaoHibrida;
	private ArrayList<RelatePersonContent> resultadoRecomendacaoConjunta;
	
	private Context context;
	
	private JFrame Janela;  

	private static final long serialVersionUID = 1L;
	
	private int bottaoAnterior;
	
	//---------------------------------------------------------------------------------------------
	// Botões de Funcionalidades do Sistema
	//---------------------------------------------------------------------------------------------
	private JPanel panelBotoesControle;
	private JButton carregaBD;
	private JButton geraRelacoes;
	private JButton geraBaseColaboradores;
	private JButton geraBaseColaborativaPonderada;
	private JButton geraBaseBaseadaConteudo;
	private JButton geraRecomendacaoHibrida;
	private JButton geraRecomendacaoConjunta;
	private JButton resetaSimulacao;
	private JButton sairSistema;
	
	//---------------------------------------------------------------------------------------------
	// Campos do Painel de Configuração da Simulação
	//---------------------------------------------------------------------------------------------
	private JPanel dadosEntrada;
	private JTextField numeroPessoas;					// Numero de pessoas da simulação
	private JTextField numeroConteudos;					// Numero de conteúdos da simulação
	private JTextField numeroLocais;					// Numero de locais da simulação
	private JTextField numeroRelacoes;					// Numero de relações entre pessoas na simulação
	private JTextField numeroConteudosPorPessoa;		// Numero de máximo de conteúdo para cada pessoa na simulação
	private JTextField numeroLocaisPorPessoa;			// Numero de máximo de locais para cada pessoa na simulação
	private JTextField numeroLocaisPorConteudo;			// Numero de máximo de locais para cada conteúdo na simulação
	private JTextField tamanhoListaColaboradores;		// Tamanho da Lista de Colaboradores
	private JTextField tamanhoListaColaborativa;		// Tamanho da Lista Colaborativa Ponderada
	private JTextField tamanhoListaBaseadaConteudo;		// Tamanho da Lista Baseada em Conteúdo
	
	//---------------------------------------------------------------------------------------------
	// Painel que contem Person, Contents e Location
	//---------------------------------------------------------------------------------------------
	private JTabbedPane verDados;
	
	//---------------------------------------------------------------------------------------------	
	// Campos do Tabbed Person
	//---------------------------------------------------------------------------------------------
	private JPanel dadosPerson;
	private JComboBox<Person> comboPerson;
	private JLabel labelNomeFirstPerson;
	
	private JLabel labelNomeLastPerson;
	private JLabel labelEmailPerson;
	private JLabel labelIdadePerson;
	private JLabel labelSexoPerson;
	private JLabel labelRendaPerson;
	private JLabel labelReligiaoPerson;
	private JLabel labelCorPerson;
	private JLabel labelDoencaPerson;
	private JLabel labelEducacaoNivelPerson;
	private JLabel labelEstiloOrganizacionalPerson;
	private JLabel labelEstiloPercepcaoPerson;
	private JLabel labelEstiloProcessamentoPerson;
	private JLabel labelEstiloRetencaoPerson;
	private JLabel labelGrandeAreaPerson;
	private JLabel labelAreaPerson;
	private JLabel labellSubareaPerson;
	private JLabel labelCursoFormacaoPerson;
	private JLabel labelFreqPositivaDiaria;
	private JLabel labelFreqNegativaDiaria;
	private JLabel labelFreqPositivaSemanal;
	private JLabel labelFreqNegativaSemanal;
	private JTextField fieldNomeFirstPerson;
	private JTextField fieldNomeLastPerson;
	private JTextField fieldEmailPerson;
	private JTextField fieldIdadePerson;
	private JTextField fieldSexoPerson;
	private JTextField fieldRendaPerson;
	private JTextField fieldReligiaoPerson;
	private JTextField fieldCorPerson;
	private JTextField fieldDoencaPerson;
	private JTextField fieldEducacaoNivelPerson;
	private JTextField fieldEstiloOrganizacionalPerson;
	private JTextField fieldEstiloPercepcaoPerson;
	private JTextField fieldEstiloProcessamentoPerson;	
	private JTextField fieldEstiloRetencaoPerson;
	private JTextField fieldGrandeAreaPerson;
	private JTextField fieldAreaPerson;
	private JTextField fieldSubareaPerson;
	private JTextField fieldCursoFormacaoPerson;
	private JTextField fieldFreqPositivaDiaria;
	private JTextField fieldFreqNegativaDiaria;
	private JTextField fieldFreqPositivaSemanal;
	private JTextField fieldFreqNegativaSemanal;
	
	private JPanel jpanelMeusPersons; 
	private JPanel jpanelMeusContents;
	private JPanel jpanelMeusLocations;
	private JComboBox<Person> comboMeusAmigosPerson;
	private JComboBox<Content> comboMeusConteudosPerson;
	private JComboBox<Location> comboMeusLocaisPerson;
	private JPanel jpanelColaboradores; 
	private JPanel jpanelColaborativa;
	private JPanel jpanelBaseadaConteudo;
	private JComboBox<Person> comboColaboradores;
	private JComboBox<Content> comboColaborativa;
	private JComboBox<Content> comboBaseadaConteudo;
	private JPanel panelMinhasTagsPerson;
	private JComboBox<String> comboMinhasTagsPerson;
	
	private JButton buttonEditPerson;
	private JButton buttonNovoPerson;
	private JButton buttonSalvePerson;
	private JButton buttonCancelePerson;
	private JButton buttonDeletePerson;
	
	//---------------------------------------------------------------------------------------------
	// Campos do Tabbed Content
	//---------------------------------------------------------------------------------------------
	private JPanel dadosContent;
	private	JComboBox<Content> comboContent;
	private JLabel labelTituloContent;
	private JLabel labelTypeContent;
	private JLabel labelSubtypeContent;
	private JLabel labelVisibilityContent;
	private JLabel labelDescriptionContent;
	private JLabel labelAgeContent;
	private JLabel labelPersonContent;
	private JLabel labelAutorContent;
	private JLabel labelGenericLinkContent;
	private JLabel labelBytesContent;
	private JLabel labelDuracaoContent;
	private JLabel labelPercentualAceitacaoContent;
	private JLabel labelRateColabPonderContent;
	private JLabel labelFreqPositivaDiariaContent;
	private JLabel labelFreqNegativaDiariaContent;
	private JLabel labelFreqPositivaSemanalContent;
	private JLabel labelFreqNegativaSemanalContent;
	private JTextField fieldTituloContent;
	private JTextField fieldTypeContent;
	private JTextField fieldSubtypeContent;
	private JTextField fieldVisibilityContent;
	private JTextField fieldDescriptionContent;
	private JTextField fieldAgeContent;
	private JTextField fieldPersonContent;
	private JTextField fieldAutorContent;
	private JTextField fieldGenericLinkContent;
	private JTextField fieldBytesContent;	
	private JTextField fieldDuracaoContent;
	private JTextField fieldPercentualAceitacaoContent;
	private JTextField fieldRateColabPonderContent;
	private JTextField fieldFreqPositivaDiariaContent;
	private JTextField fieldFreqNegativaDiariaContent;
	private JTextField fieldFreqPositivaSemanalContent;
	private JTextField fieldFreqNegativaSemanalContent;
	
	private JPanel panelMinhasTagsContent; 
	private JPanel panelMeusLocationsContent;
	private JComboBox<String> comboMinhasTagsContent;
	private JComboBox<Location> comboMeusLocaisContent;

	private JButton buttonEditContent;
	private JButton buttonNovoContent;
	private JButton buttonSalveContent;
	private JButton buttonCanceleContent;
	private JButton buttonDeleteContent;
	
	//---------------------------------------------------------------------------------------------
	// Campos do Tabbed Location
	//---------------------------------------------------------------------------------------------
	private JPanel dadosLocation;
	private JComboBox<Location> comboLocation;
	private JLabel labelNomeLocation;
	private JLabel labelIdGoogleLocation;
	private JLabel labelLatidudeLocation;
	private JLabel labelLongitudeLocation;
	private JLabel labelAltitudeLocation;
	private JLabel labelPaisLocation; 
	private JLabel labelEstadoLocation; 
	private JLabel labelCidadeLocation; 
	private JLabel labelBairroLocation;
	private JLabel labelLogradouroLocation;
	private JLabel labelNumeroLocation;
	private JLabel labelComplementoLocation;
	private JLabel labelCEPLocation;
	private JLabel labelRatingLocation;
	private JLabel labelFreqPositivaDiariaLocation;
	private JLabel labelFreqNegativaDiariaLocation;
	private JLabel labelFreqPositivaSemanalLocation;
	private JLabel labelFreqNegativaSemanalLocation;
	private JTextField fieldNomeLocation;
	private JTextField fieldIdGoogleLocation;
	private JTextField fieldLatitudeLocation;
	private JTextField fieldLongitudeLocation;
	private JTextField fieldAltitudeLocation;
	private JTextField fieldPaisLocation; 
	private JTextField fieldEstadoLocation; 
	private JTextField fieldCidadeLocation; 
	private JTextField fieldBairroLocation;
	private JTextField fieldLogradouroLocation;
	private JTextField fieldNumeroLocation;
	private JTextField fieldComplementoLocation;
	private JTextField fieldCEPLocation;
	private JTextField fieldRatingLocation;
	private JTextField fieldFreqPositivaDiariaLocation;
	private JTextField fieldFreqNegativaDiariaLocation;
	private JTextField fieldFreqPositivaSemanalLocation;
	private JTextField fieldFreqNegativaSemanalLocation;
	
	private JPanel panelMinhasTagsLocation; 
	private JPanel panelMeusTiposLocation;
	private JComboBox<String> comboMinhasTagsLocation;
	private JComboBox<String> comboMeusTiposLocation;

	private JButton buttonEditLocation;
	private JButton buttonNovoLocation;
	private JButton buttonSalveLocation;
	private JButton buttonCanceleLocation;
	private JButton buttonDeleteLocation;
	
	//---------------------------------------------------------------------------------------------
	// Campos do Tabbed Relações
	//---------------------------------------------------------------------------------------------
/*	private JPanel dadosRelacao1;
	private JPanel dadosRelacao2;
	private JPanel dadosRelacao3;
	private JPanel dadosRelacao4;
	private JPanel dadosSistema;
	private JPanel relacaoPersonPerson;
	private JPanel relacaoPersonContent;
	private JPanel relacaoPersonLocation;
	private JPanel relacaoContentLocation;
	
	private JComboBox<Location> comboRelacaoPersonPerson;
	private JComboBox<Location> comboRelacaoPersonContent;
	private JComboBox<Location> comboRelacaoPersonLocation;
	private JComboBox<Location> comboRelacaoContentLocation;
	
	private JLabel labelRelatePPNomePerson1;
	private JLabel labelRelatePPNomePerson2;
	private JLabel labelRelatePPAfinidade;
	private JLabel labelRelatePPData;
	private JLabel labelRelatePPAmigosEmComum;
	
	private JTextField fieldRelatePPNomePerson1;
	private JTextField fieldRelatePPNomePerson2;
	private JTextField fieldRelatePPAfinidade;
	private JTextField fieldRelatePPData;
	private JTextField fieldRelatePPAmigosEmComum;
*/
	//---------------------------------------------------------------------------------------------
	// Campos do Painel Contexto
	//---------------------------------------------------------------------------------------------
	private JPanel dadosContexto;
	private JTextField dataContexto;				// Instante do evento
	private JTextField diaContexto;					// Instante do evento
	private JTextField horaContexto;				// Instante do evento
	private JTextField bateriaContexto;				// Percentual de carga da bateria
	private JTextField velocidadeConexaoContexto;	// Velocidade da conexão com a internet
	private JTextField velocidadePessoaContexto;	// Velocidade da pessoa no momento do evento
	private JTextField localContexto;				// Lugar onde a pessoa se encontra no momento do evento
	
	//---------------------------------------------------------------------------------------------
	// Campos do Painel do Resultado da Recomendação Híbrida 
	//---------------------------------------------------------------------------------------------
	private JPanel recomendacaoHibrida;
	private JLabel recomendadoPerson;
	private JComboBox<Content> comboResultRecomendacaoHibrida;
	private JLabel taxaHibrida;
	private JLabel pesoColaborativo;
	private JLabel pesoBaseadoConteudo;
	private JLabel pesoUbiquo; 
	
	//---------------------------------------------------------------------------------------------
	// Campos do Painel do Resultado da Recomendação Conjunta 
	//---------------------------------------------------------------------------------------------
	private JPanel recomendacaoConjunta;
	private JComboBox<Content> comboResultRecomendacaoConjunta;

	//---------------------------------------------------------------------------------------------
	// Dados do Painel de Configuração da Simulação
	//---------------------------------------------------------------------------------------------
	private JPanel log; 

	
	//---------------------------------------------------------------------------------------------
	// Função Principal do Sistema
	//---------------------------------------------------------------------------------------------
	public static void main(String args[]) {  
		new ViewSimulacao(); 	  
	}  
	
	//---------------------------------------------------------------------------------------------
	// Formação Gráfica da Janela Principal
	//---------------------------------------------------------------------------------------------
	public ViewSimulacao() {  
		
		//this.dadosSimulados = new DataArray();
		// ------------------------------------------------------------------------------------------------------
		// Botões das Funcionalidades Principais	  
		// ------------------------------------------------------------------------------------------------------		
		panelBotoesControle = new JPanel();
		panelBotoesControle.setBorder( BorderFactory.createTitledBorder("Controles do Sistema"));
		panelBotoesControle.setLocation(10, 10);
		panelBotoesControle.setSize(250, 300);
		panelBotoesControle.setLayout(null);
		
		carregaBD = new JButton("Carregar Banco de Dados");  
		carregaBD.setLocation( 10, 30);  
		carregaBD.setSize(230, 30);  
		carregaBD.addActionListener(this);
		carregaBD.setToolTipText("Gera a pessoas, conteúdos e locais de acordo com os dados especificados no painel Dados da Simulação");
		  		
		geraBaseColaboradores = new JButton("Gerar Base de Colaboradores");  
		geraBaseColaboradores.setLocation( 10, 70);  
		geraBaseColaboradores.setSize(230, 30);  
		geraBaseColaboradores.addActionListener(this);  
		geraBaseColaboradores.setEnabled(false);
		geraBaseColaboradores.setToolTipText("Gera a lista de pessoas mais semlhentes a pessoa alvo de acordo com os dados especificados no painel Dados da Simulação");

		geraBaseColaborativaPonderada = new JButton("Gerar Base Colaborativa Ponderada");  
		geraBaseColaborativaPonderada.setLocation(10, 110 );  
		geraBaseColaborativaPonderada.setSize(230, 30);  
		geraBaseColaborativaPonderada.addActionListener(this);  
		geraBaseColaborativaPonderada.setEnabled(false);
		geraBaseColaborativaPonderada.setToolTipText("Gera a lista de conteúdos mais adequados a pessoa alvo, com base em seus colaboradores, de acordo com os dados especificados no painel Dados da Simulação");
		
		geraBaseBaseadaConteudo = new JButton("Gerar Base Baseada em Conteúdo");  
		geraBaseBaseadaConteudo.setLocation(10, 150 );  
		geraBaseBaseadaConteudo.setSize(230, 30);  
		geraBaseBaseadaConteudo.addActionListener(this);  
		geraBaseBaseadaConteudo.setEnabled(false);
		geraBaseBaseadaConteudo.setToolTipText("Gera a lista de conteúdos mais adequados a pessoa alvo, com base em seus metadados, de acordo com os dados especificados no painel Dados da Simulação");

		geraRecomendacaoHibrida = new JButton("Gerar Recomendação Híbrida");  
		geraRecomendacaoHibrida.setLocation(10, 190 );  
		geraRecomendacaoHibrida.setSize(230, 30);  
		geraRecomendacaoHibrida.addActionListener(this);  
		geraRecomendacaoHibrida.setEnabled(false);
		geraRecomendacaoHibrida.setToolTipText("Gera a lista de final de conteúdos mais adequados a pessoa alvo, com base nas listas colaborativa, baseada em conteúdo e com base no contexto");

		geraRecomendacaoConjunta = new JButton("Gerar Recomendação Conjunta");  
		geraRecomendacaoConjunta.setLocation(10, 230 );  
		geraRecomendacaoConjunta.setSize(230, 30);  
		geraRecomendacaoConjunta.addActionListener(this);  
		geraRecomendacaoConjunta.setEnabled(false);
		geraRecomendacaoConjunta.setToolTipText("Gera a lista de final de conteúdos mais adequados a serem recomendados em conjunto");

		
		panelBotoesControle.add(carregaBD);  
		panelBotoesControle.add(geraBaseColaboradores);
		panelBotoesControle.add(geraBaseColaborativaPonderada);
		panelBotoesControle.add(geraBaseBaseadaConteudo);
		panelBotoesControle.add(geraRecomendacaoHibrida); 
		panelBotoesControle.add(geraRecomendacaoConjunta); 
		
		resetaSimulacao = new JButton("Gera Banco de Dados");  
		resetaSimulacao.setLocation(10, 640 );  
		resetaSimulacao.setSize(250, 30);  
		resetaSimulacao.addActionListener(this);  
		resetaSimulacao.setEnabled(true);
		resetaSimulacao.setToolTipText("Gera banco de dados novo");

		sairSistema = new JButton("Sai do Sistema");  
		sairSistema.setLocation(10, 690);  
		sairSistema.setSize(250, 30);  
		sairSistema.addActionListener(this);  
		sairSistema.setToolTipText("Sai do Sistema de Recomendação e encerra o programa");
		// ------------------------------------------------------------------------------------------------------
		// Painel de entrada de dados da simulacao
		// ------------------------------------------------------------------------------------------------------
		dadosEntrada = new JPanel();
		dadosEntrada.setBorder( BorderFactory.createTitledBorder("Dados da Simulação"));
		dadosEntrada.setLocation(10, 320);
		dadosEntrada.setSize(250, 300);
		dadosEntrada.setLayout(null);
		
		// Campos do Painel de Entrada de Dados da Simulação	
		JLabel numeroPessoasRotulo = new JLabel("Número de Pessoas:");
		numeroPessoasRotulo.setLocation(20, 25);
		numeroPessoasRotulo.setSize(180, 25);	
		numeroPessoas = new JTextField("Wait...");				
		numeroPessoas.setLocation(180, 25);
		numeroPessoas.setSize(50, 25);
		numeroPessoas.setEditable(false);

		JLabel numeroConteudosRotulo = new JLabel("Número de Conteúdos:");
		numeroConteudosRotulo.setLocation(20, 50);
		numeroConteudosRotulo.setSize(180, 25);	
		numeroConteudos = new JTextField("Wait...");	
		numeroConteudos.setLocation(180, 50);
		numeroConteudos.setSize(50, 25);
		numeroConteudos.setEditable(false);
		 
		JLabel numeroLocaisRotulo = new JLabel("Número de Locais:");
		numeroLocaisRotulo.setLocation(20, 75);
		numeroLocaisRotulo.setSize(180, 25);			
		numeroLocais = new JTextField("Wait...");;		
		numeroLocais.setLocation(180, 75);
		numeroLocais.setSize(50, 25);
		numeroLocais.setEditable(false);
		
		JLabel numeroRelacoesRotulo = new JLabel("Número de Relações:");
		numeroRelacoesRotulo.setLocation(20, 100);
		numeroRelacoesRotulo.setSize(180, 25);	
		numeroRelacoes = new JTextField("1000");		
		numeroRelacoes.setLocation(180, 100);
		numeroRelacoes.setSize(50, 25);
		numeroRelacoes.setEditable(false);
		
		JLabel numeroConteudosPorPessoaRotulo = new JLabel("Max. Conteúdo/Pessoa:");
		numeroConteudosPorPessoaRotulo.setLocation(20, 125);
		numeroConteudosPorPessoaRotulo.setSize(180, 25);	
		numeroConteudosPorPessoa = new JTextField("25");	
		numeroConteudosPorPessoa.setLocation(180, 125);
		numeroConteudosPorPessoa.setSize(50, 25);
		numeroConteudosPorPessoa.setEditable(false);
		
		JLabel numeroLocaisPorPessoaRotulo = new JLabel("Max. Locais/Pessoa:");
		numeroLocaisPorPessoaRotulo.setLocation(20, 150);
		numeroLocaisPorPessoaRotulo.setSize(180, 25);	
		numeroLocaisPorPessoa = new JTextField("25");
		numeroLocaisPorPessoa.setLocation(180, 150);
		numeroLocaisPorPessoa.setSize(50, 25);
		numeroLocaisPorPessoa.setEditable(false);
		
		JLabel numeroLocaisPorConteudoRotulo = new JLabel("Max. Locais/Conteúdo:");
		numeroLocaisPorConteudoRotulo.setLocation(20, 175);
		numeroLocaisPorConteudoRotulo.setSize(180, 25);	
		numeroLocaisPorConteudo = new JTextField("25");			
		numeroLocaisPorConteudo.setLocation(180, 175);
		numeroLocaisPorConteudo.setSize(50, 25);
		numeroLocaisPorConteudo.setEditable(false);

		JLabel tamanhoListaColaboradoresRotulo = new JLabel("Tam. List. Colaboradores:");
		tamanhoListaColaboradoresRotulo.setLocation(20, 200);
		tamanhoListaColaboradoresRotulo.setSize(180, 25);	
		tamanhoListaColaboradores = new JTextField("10");	
		tamanhoListaColaboradores.setLocation(180, 200);
		tamanhoListaColaboradores.setSize(50, 25);
		tamanhoListaColaboradores.setEditable(false);
		
		JLabel tamanhoListaColaborativaRotulo = new JLabel("Tam. List. Colaborativa:");
		tamanhoListaColaborativaRotulo.setLocation(20, 225);
		tamanhoListaColaborativaRotulo.setSize(180, 25);	
		tamanhoListaColaborativa = new JTextField("10");
		tamanhoListaColaborativa.setLocation(180, 225);
		tamanhoListaColaborativa.setSize(50, 25);
		tamanhoListaColaborativa.setEditable(false);
		
		JLabel tamanhoListaBaseadaConteudoRotulo = new JLabel("Tam. List. Bas. Conteúdo");
		tamanhoListaBaseadaConteudoRotulo.setLocation(20, 250);
		tamanhoListaBaseadaConteudoRotulo.setSize(180, 25);	
		tamanhoListaBaseadaConteudo = new JTextField("10");			
		tamanhoListaBaseadaConteudo.setLocation(180, 250);
		tamanhoListaBaseadaConteudo.setSize(50, 25);
		tamanhoListaBaseadaConteudo.setEditable(false);
		
		dadosEntrada.add(numeroPessoasRotulo);
		dadosEntrada.add(this.numeroPessoas);
		dadosEntrada.add(numeroConteudosRotulo);
		dadosEntrada.add(this.numeroConteudos);
		dadosEntrada.add(numeroLocaisRotulo);
		dadosEntrada.add(this.numeroLocais);		
		dadosEntrada.add(numeroRelacoesRotulo);
		dadosEntrada.add(this.numeroRelacoes);
		dadosEntrada.add(numeroConteudosPorPessoaRotulo);
		dadosEntrada.add(this.numeroConteudosPorPessoa);
		dadosEntrada.add(numeroLocaisPorPessoaRotulo);
		dadosEntrada.add(this.numeroLocaisPorPessoa);		
		dadosEntrada.add(numeroLocaisPorConteudoRotulo);
		dadosEntrada.add(this.numeroLocaisPorConteudo);
		dadosEntrada.add(tamanhoListaColaboradoresRotulo);
		dadosEntrada.add(this.tamanhoListaColaboradores);
		dadosEntrada.add(tamanhoListaColaborativaRotulo);
		dadosEntrada.add(this.tamanhoListaColaborativa);
		dadosEntrada.add(tamanhoListaBaseadaConteudoRotulo);
		dadosEntrada.add(this.tamanhoListaBaseadaConteudo);	
		
		// ------------------------------------------------------------------------------------------------------
		// Campos do Painel Contexto	
		// ------------------------------------------------------------------------------------------------------
		dadosContexto = new JPanel();
		dadosContexto.setBorder( BorderFactory.createTitledBorder("Dados do Contexto"));
		dadosContexto.setLocation(830, 20);
		dadosContexto.setSize(430, 220);
		dadosContexto.setLayout(null);

		JLabel dataRotulo = new JLabel("Dada:");
		dataRotulo.setLocation(20, 25);
		dataRotulo.setSize(100, 25);	
		dataContexto = new JTextField();				// Instante do evento
		dataContexto.setLocation(120, 25);
		dataContexto.setSize(100, 25);
		dataContexto.setEditable(false);

		JLabel diaRotulo = new JLabel("Dia:");
		diaRotulo.setLocation(20, 50);
		diaRotulo.setSize(100, 25);	
		diaContexto = new JTextField();;				// Instante do evento
		diaContexto.setLocation(120, 50);
		diaContexto.setSize(100, 25);
		diaContexto.setEditable(false);
		 
		JLabel horaRotulo = new JLabel("Hora:");
		horaRotulo.setLocation(20, 75);
		horaRotulo.setSize(100, 25);			
		horaContexto = new JTextField();;				// Instante do evento
		horaContexto.setLocation(120, 75);
		horaContexto.setSize(100, 25);
		horaContexto.setEditable(false);
		
		JLabel bateriaRotulo = new JLabel("Bateria:");
		bateriaRotulo.setLocation(20, 100);
		bateriaRotulo.setSize(100, 25);	
		bateriaContexto = new JTextField();;			// Percentual de carga da bateria
		bateriaContexto.setLocation(120, 100);
		bateriaContexto.setSize(100, 25);
		bateriaContexto.setEditable(false);
		
		JLabel conexaoRotulo = new JLabel("Vel. Conexão:");
		conexaoRotulo.setLocation(20, 125);
		conexaoRotulo.setSize(100, 25);	
		velocidadeConexaoContexto = new JTextField();;	// Velocidade da conexão com a internet
		velocidadeConexaoContexto.setLocation(120, 125);
		velocidadeConexaoContexto.setSize(100, 25);
		velocidadeConexaoContexto.setEditable(false);
		
		JLabel movimentoRotulo = new JLabel("Vel. Pessoa:");
		movimentoRotulo.setLocation(20, 150);
		movimentoRotulo.setSize(100, 25);	
		velocidadePessoaContexto = new JTextField();;	// Velocidade da pessoa no momento do evento
		velocidadePessoaContexto.setLocation(120, 150);
		velocidadePessoaContexto.setSize(100, 25);
		velocidadePessoaContexto.setEditable(false);
		
		JLabel localRotulo = new JLabel("Local:");
		localRotulo.setLocation(20, 175);
		localRotulo.setSize(100, 25);	
		localContexto = new JTextField();;				// Lugar onde a pessoa se encontra no momento do evento
		localContexto.setLocation(120, 175);
		localContexto.setSize(100, 25);
		localContexto.setEditable(false);

		dadosContexto.add(dataRotulo);
		dadosContexto.add(this.dataContexto);
		dadosContexto.add(diaRotulo);
		dadosContexto.add(this.diaContexto);
		dadosContexto.add(horaRotulo);
		dadosContexto.add(this.horaContexto);		
		dadosContexto.add(bateriaRotulo);
		dadosContexto.add(this.bateriaContexto);
		dadosContexto.add(conexaoRotulo);
		dadosContexto.add(this.velocidadeConexaoContexto);
		dadosContexto.add(movimentoRotulo);
		dadosContexto.add(this.velocidadePessoaContexto);
		dadosContexto.add(localRotulo);
		dadosContexto.add(this.localContexto);	

		// ------------------------------------------------------------------------------------------------------
		// Painel de entrada do resultado da recomedação híbrida
		// ------------------------------------------------------------------------------------------------------
		recomendacaoHibrida = new JPanel();
		recomendacaoHibrida.setBorder( BorderFactory.createTitledBorder("Recomendação Híbrida"));
		recomendacaoHibrida.setLocation(830, 260);
		recomendacaoHibrida.setSize(430, 220);
		recomendacaoHibrida.setLayout(null);

		// Dados do Painel do Resultado da Recomendação Híbrida 
		recomendadoPerson = new JLabel();
		recomendadoPerson.setLocation(10, 25);
		recomendadoPerson.setSize(300, 25);		

		comboResultRecomendacaoHibrida  = new JComboBox<Content>();
		comboResultRecomendacaoHibrida.setLocation(10, 50);
		comboResultRecomendacaoHibrida.setSize(180, 25);		

		taxaHibrida  = new JLabel();
		taxaHibrida.setLocation(200, 50);
		taxaHibrida.setSize(300, 25);
		Font fonteTemp = new Font("Arial", Font.BOLD, 20); 
		taxaHibrida.setFont(fonteTemp);

		pesoColaborativo = new JLabel();
		pesoColaborativo.setLocation(10, 100);
		pesoColaborativo.setSize(300, 25);		

		pesoBaseadoConteudo = new JLabel();
		pesoBaseadoConteudo.setLocation(10, 125);
		pesoBaseadoConteudo.setSize(300, 25);		

		pesoUbiquo = new JLabel();
		pesoUbiquo.setLocation(10, 150);
		pesoUbiquo.setSize(300, 25);		
		
		recomendacaoHibrida.add(this.recomendadoPerson);
		recomendacaoHibrida.add(this.comboResultRecomendacaoHibrida);		
		recomendacaoHibrida.add(this.taxaHibrida);
		recomendacaoHibrida.add(this.pesoColaborativo);
		recomendacaoHibrida.add(this.pesoBaseadoConteudo);
		recomendacaoHibrida.add(this.pesoUbiquo);	

		// ------------------------------------------------------------------------------------------------------
		// Painel de entrada do resultado da recomedação Conjunta
		// ------------------------------------------------------------------------------------------------------
		recomendacaoConjunta = new JPanel();
		recomendacaoConjunta.setBorder( BorderFactory.createTitledBorder("Recomendação Conjunta"));
		recomendacaoConjunta.setLocation(830, 495);
		recomendacaoConjunta.setSize(430, 70);
		recomendacaoConjunta.setLayout(null);

		comboResultRecomendacaoConjunta  = new JComboBox<Content>();
		comboResultRecomendacaoConjunta.setLocation(10, 25);
		comboResultRecomendacaoConjunta.setSize(180, 25);		

		recomendacaoConjunta.add(this.comboResultRecomendacaoConjunta);
		// ------------------------------------------------------------------------------------------------------
		// Painel com o registro das atividades do sistema
		// ------------------------------------------------------------------------------------------------------
		log = new JPanel();
		log.setBorder( BorderFactory.createTitledBorder("Registro de Logs de Atividades"));
		log.setLocation(830, 580);
		log.setSize(430, 142);
		log.setLayout(null);

		// ------------------------------------------------------------------------------------------------------
		// Tabbed Panel que apresenta as pessoas, os conteúdos e os locais gerados
		// ------------------------------------------------------------------------------------------------------
		verDados = new JTabbedPane();
		verDados.setLocation(290, 20);
		verDados.setSize(520, 700);

		// ------------------------------------------------------------------------------------------------------				
		//Dados das pessoas
		// ------------------------------------------------------------------------------------------------------
		dadosPerson = new JPanel();
		dadosPerson.setLocation(0, 0);
		dadosPerson.setSize(500, 690);
		dadosPerson.setLayout(null);
		
		comboPerson = new JComboBox<Person>();
		comboPerson.setLocation(10, 10);
		comboPerson.setSize(180, 25);		
		
		labelNomeFirstPerson = new JLabel("Nome");
		labelNomeFirstPerson.setLocation(20, 50);
		labelNomeFirstPerson.setSize(50, 15);	

		fieldNomeFirstPerson = new JTextField("");
		fieldNomeFirstPerson.setLocation(135, 50);
		fieldNomeFirstPerson.setSize(200, 25);	 
		fieldNomeFirstPerson.setEditable(false);

		labelEmailPerson = new JLabel("Email");
		labelEmailPerson.setLocation(20, 75);
		labelEmailPerson.setSize(50, 15);	

		fieldEmailPerson = new JTextField("");
		fieldEmailPerson.setLocation(135, 75);
		fieldEmailPerson.setSize(200, 25);	 
		fieldEmailPerson.setEditable(false);

		labelIdadePerson = new JLabel("Idade");
		labelIdadePerson.setLocation(20, 100);
		labelIdadePerson.setSize(50, 15);	

		fieldIdadePerson = new JTextField("");
		fieldIdadePerson.setLocation(135, 100);
		fieldIdadePerson.setSize(200, 25);	 
		fieldIdadePerson.setEditable(false);
		
		labelSexoPerson = new JLabel("Sexo");
		labelSexoPerson.setLocation(20, 125);
		labelSexoPerson.setSize(50, 15);	

		fieldSexoPerson = new JTextField("");
		fieldSexoPerson.setLocation(135, 125);
		fieldSexoPerson.setSize(200, 25);	 
		fieldSexoPerson.setEditable(false);

		labelRendaPerson = new JLabel("Renda");
		labelRendaPerson.setLocation(20, 150);
		labelRendaPerson.setSize(50, 15);	

		fieldRendaPerson = new JTextField("");
		fieldRendaPerson.setLocation(135, 150);
		fieldRendaPerson.setSize(200, 25);	 
		fieldRendaPerson.setEditable(false);

		labelReligiaoPerson = new JLabel("Religião");
		labelReligiaoPerson.setLocation(20, 175);
		labelReligiaoPerson.setSize(50, 15);	

		fieldReligiaoPerson = new JTextField("");
		fieldReligiaoPerson.setLocation(135, 175);
		fieldReligiaoPerson.setSize(200, 25);	 
		fieldReligiaoPerson.setEditable(false);

		labelCorPerson = new JLabel("Cor/Raça");
		labelCorPerson.setLocation(20, 200);
		labelCorPerson.setSize(80, 15);	

		fieldCorPerson = new JTextField("");
		fieldCorPerson.setLocation(135, 200);
		fieldCorPerson.setSize(200, 25);	 
		fieldCorPerson.setEditable(false);

		labelDoencaPerson = new JLabel("Doença");
		labelDoencaPerson.setLocation(20, 225);
		labelDoencaPerson.setSize(100, 15);	

		fieldDoencaPerson = new JTextField("");
		fieldDoencaPerson.setLocation(135, 225);
		fieldDoencaPerson.setSize(200, 25);	 
		fieldDoencaPerson.setEditable(false);

		labelEducacaoNivelPerson = new JLabel("Nível Educacional");
		labelEducacaoNivelPerson.setLocation(20, 250);
		labelEducacaoNivelPerson.setSize(100, 15);	

		fieldEducacaoNivelPerson = new JTextField("");
		fieldEducacaoNivelPerson.setLocation(135, 250);
		fieldEducacaoNivelPerson.setSize(200, 25);	 
		fieldEducacaoNivelPerson.setEditable(false);

		labelEstiloOrganizacionalPerson = new JLabel("Organizacional");
		labelEstiloOrganizacionalPerson.setLocation(20, 275);
		labelEstiloOrganizacionalPerson.setSize(100, 15);	

		fieldEstiloOrganizacionalPerson = new JTextField("");
		fieldEstiloOrganizacionalPerson.setLocation(135, 275);
		fieldEstiloOrganizacionalPerson.setSize(200, 25);	 
		fieldEstiloOrganizacionalPerson.setEditable(false);

		labelEstiloPercepcaoPerson = new JLabel("Percepção");
		labelEstiloPercepcaoPerson.setLocation(20, 300);
		labelEstiloPercepcaoPerson.setSize(100, 15);	

		fieldEstiloPercepcaoPerson = new JTextField("");
		fieldEstiloPercepcaoPerson.setLocation(135, 300);
		fieldEstiloPercepcaoPerson.setSize(200, 25);	 
		fieldEstiloPercepcaoPerson.setEditable(false);

		labelEstiloProcessamentoPerson = new JLabel("Processamento");
		labelEstiloProcessamentoPerson.setLocation(20, 325);
		labelEstiloProcessamentoPerson.setSize(100, 15);	

		fieldEstiloProcessamentoPerson = new JTextField("");
		fieldEstiloProcessamentoPerson.setLocation(135, 325);
		fieldEstiloProcessamentoPerson.setSize(200, 25);	 
		fieldEstiloProcessamentoPerson.setEditable(false);

		labelEstiloRetencaoPerson = new JLabel("Retenção");
		labelEstiloRetencaoPerson.setLocation(20, 350);
		labelEstiloRetencaoPerson.setSize(100, 15);	

		fieldEstiloRetencaoPerson = new JTextField("");
		fieldEstiloRetencaoPerson.setLocation(135, 350);
		fieldEstiloRetencaoPerson.setSize(200, 25);	 
		fieldEstiloRetencaoPerson.setEditable(false);

		labelGrandeAreaPerson = new JLabel("Grande Áreas");
		labelGrandeAreaPerson.setLocation(20, 375);
		labelGrandeAreaPerson.setSize(100, 15);	

		fieldGrandeAreaPerson = new JTextField("");
		fieldGrandeAreaPerson.setLocation(135, 375);
		fieldGrandeAreaPerson.setSize(200, 25);	 
		fieldGrandeAreaPerson.setEditable(false);

		labelAreaPerson = new JLabel("Área");
		labelAreaPerson.setLocation(20, 400);
		labelAreaPerson.setSize(100, 15);	

		fieldAreaPerson = new JTextField("");
		fieldAreaPerson.setLocation(135, 400);
		fieldAreaPerson.setSize(200, 25);	 
		fieldAreaPerson.setEditable(false);

		labellSubareaPerson = new JLabel("Subárea");
		labellSubareaPerson.setLocation(20, 425);
		labellSubareaPerson.setSize(100, 15);	

		fieldSubareaPerson = new JTextField("");
		fieldSubareaPerson.setLocation(135, 425);
		fieldSubareaPerson.setSize(200, 25);	 
		fieldSubareaPerson.setEditable(false);

		labelCursoFormacaoPerson = new JLabel("Formação");
		labelCursoFormacaoPerson.setLocation(20, 450);
		labelCursoFormacaoPerson.setSize(100, 15);	

		fieldCursoFormacaoPerson = new JTextField("");
		fieldCursoFormacaoPerson.setLocation(135, 450);
		fieldCursoFormacaoPerson.setSize(200, 25);	 
		fieldCursoFormacaoPerson.setEditable(false);

		labelFreqPositivaSemanal = new JLabel("Freq. Pos. Semanal");
		labelFreqPositivaSemanal.setLocation(20, 475);
		labelFreqPositivaSemanal.setSize(110, 15);	

		fieldFreqPositivaSemanal = new JTextField("");
		fieldFreqPositivaSemanal.setLocation(135, 475);
		fieldFreqPositivaSemanal.setSize(200, 25);	 
		fieldFreqPositivaSemanal.setEditable(false);

		labelFreqNegativaSemanal = new JLabel("Freq. Neg. Semanal");
		labelFreqNegativaSemanal.setLocation(20, 500);
		labelFreqNegativaSemanal.setSize(110, 15);	

		fieldFreqNegativaSemanal = new JTextField("");
		fieldFreqNegativaSemanal.setLocation(135, 500);
		fieldFreqNegativaSemanal.setSize(200, 25);	 
		fieldFreqNegativaSemanal.setEditable(false);

		labelFreqPositivaDiaria = new JLabel("Freq. Pos. Diária");
		labelFreqPositivaDiaria.setLocation(20, 525);
		labelFreqPositivaDiaria.setSize(100, 15);	
		
		fieldFreqPositivaDiaria = new JTextField("");
		fieldFreqPositivaDiaria.setLocation(135, 525);
		fieldFreqPositivaDiaria.setSize(300, 25);	 
		fieldFreqPositivaDiaria.setEditable(false);

		labelFreqNegativaDiaria = new JLabel("Freq. Neg. Diária");
		labelFreqNegativaDiaria.setLocation(20, 550);
		labelFreqNegativaDiaria.setSize(500, 15);	

		fieldFreqNegativaDiaria = new JTextField("");
		fieldFreqNegativaDiaria.setLocation(135, 550);
		fieldFreqNegativaDiaria.setSize(300, 25);	 
		fieldFreqNegativaDiaria.setEditable(false);		
		
		panelMinhasTagsPerson = new JPanel();
		panelMinhasTagsPerson.setBorder( BorderFactory.createTitledBorder("Minhas Tags"));
		panelMinhasTagsPerson.setLocation(340, 40);
		panelMinhasTagsPerson.setSize(160, 60);
		panelMinhasTagsPerson.setLayout(null);

		comboMinhasTagsPerson = new JComboBox<String>();
		comboMinhasTagsPerson.setLocation(10, 20);
		comboMinhasTagsPerson.setSize(140, 25);		
		panelMinhasTagsPerson.add(this.comboMinhasTagsPerson);

		jpanelMeusPersons = new JPanel();
		jpanelMeusPersons.setBorder( BorderFactory.createTitledBorder("Meus Persons"));
		jpanelMeusPersons.setLocation(340, 110);
		jpanelMeusPersons.setSize(160, 60);
		jpanelMeusPersons.setLayout(null);
		
		comboMeusAmigosPerson = new JComboBox<Person>();
		comboMeusAmigosPerson.setLocation( 10, 20);
		comboMeusAmigosPerson.setSize(140, 25);		
		jpanelMeusPersons.add(this.comboMeusAmigosPerson);

		jpanelMeusContents = new JPanel();
		jpanelMeusContents.setBorder( BorderFactory.createTitledBorder("Meus Contents"));
		jpanelMeusContents.setLocation(340, 180);
		jpanelMeusContents.setSize(160, 60);
		jpanelMeusContents.setLayout(null);
		
		comboMeusConteudosPerson = new JComboBox<Content>();
		comboMeusConteudosPerson.setLocation( 10, 20);
		comboMeusConteudosPerson.setSize(140, 25);		
		jpanelMeusContents.add(this.comboMeusConteudosPerson);

		jpanelMeusLocations = new JPanel();
		jpanelMeusLocations.setBorder( BorderFactory.createTitledBorder("Meus Locations"));
		jpanelMeusLocations.setLocation(340, 250);
		jpanelMeusLocations.setSize(160, 60);
		jpanelMeusLocations.setLayout(null);

		comboMeusLocaisPerson = new JComboBox<Location>();
		comboMeusLocaisPerson.setLocation(10, 20);
		comboMeusLocaisPerson.setSize(140, 25);		
		jpanelMeusLocations.add(this.comboMeusLocaisPerson);

		jpanelColaboradores = new JPanel();
		jpanelColaboradores.setBorder( BorderFactory.createTitledBorder("Colaboradores"));
		jpanelColaboradores.setLocation(340, 320);
		jpanelColaboradores.setSize(160, 60);
		jpanelColaboradores.setLayout(null);

		comboColaboradores = new JComboBox<Person>();
		comboColaboradores.setLocation(10, 20);
		comboColaboradores.setSize(140, 25);		
		jpanelColaboradores.add(this.comboColaboradores);

		jpanelColaborativa = new JPanel();
		jpanelColaborativa.setBorder( BorderFactory.createTitledBorder("Colaborativa"));
		jpanelColaborativa.setLocation(340, 390);
		jpanelColaborativa.setSize(160, 60);
		jpanelColaborativa.setLayout(null);

		comboColaborativa = new JComboBox<Content>();
		comboColaborativa.setLocation(10, 20);
		comboColaborativa.setSize(140, 25);		
		jpanelColaborativa.add(this.comboColaborativa);

		jpanelBaseadaConteudo = new JPanel();
		jpanelBaseadaConteudo.setBorder( BorderFactory.createTitledBorder("Baseada em Conteúdo"));
		jpanelBaseadaConteudo.setLocation(340, 460);
		jpanelBaseadaConteudo.setSize(160, 60);
		jpanelBaseadaConteudo.setLayout(null);

		comboBaseadaConteudo = new JComboBox<Content>();
		comboBaseadaConteudo.setLocation(10, 20);
		comboBaseadaConteudo.setSize(140, 25);		
		jpanelBaseadaConteudo.add(this.comboBaseadaConteudo);
		
		buttonNovoPerson = new JButton("Novo");  
		buttonNovoPerson.setLocation(20, 600 );  
		buttonNovoPerson.setSize(90, 30);  
		buttonNovoPerson.addActionListener(this);  
		buttonNovoPerson.setToolTipText("Adiciona um novo usuário ao sistema");

		buttonEditPerson = new JButton("Edite");  
		buttonEditPerson.setLocation(117, 600 );  
		buttonEditPerson.setSize(90, 30);  
		buttonEditPerson.addActionListener(this);  
		buttonEditPerson.setToolTipText("Edite os dados do usuário");

		buttonSalvePerson = new JButton("Salvar");  
		buttonSalvePerson.setLocation(214, 600 );  
		buttonSalvePerson.setSize(90, 30); 
		buttonSalvePerson.setEnabled(false);
		buttonSalvePerson.addActionListener(this);  
		buttonSalvePerson.setToolTipText("Salva os dados do usuário no sistema");
		
		buttonCancelePerson = new JButton("Cancelar");  
		buttonCancelePerson.setLocation(311, 600 );  
		buttonCancelePerson.setSize(90, 30);
		buttonCancelePerson.setEnabled(false);
		buttonCancelePerson.addActionListener(this);  
		buttonCancelePerson.setToolTipText("Cancela a operação");

		buttonDeletePerson = new JButton("Apagar");  
		buttonDeletePerson.setLocation(408, 600 );  
		buttonDeletePerson.setSize(90, 30);  
		buttonDeletePerson.addActionListener(this);  
		buttonDeletePerson.setToolTipText("Apaga o usuário no sistema");
		
		dadosPerson.add(this.comboPerson);
		dadosPerson.add(this.labelNomeFirstPerson);
		dadosPerson.add(this.labelEmailPerson);
		dadosPerson.add(this.labelIdadePerson);
		dadosPerson.add(this.labelSexoPerson);
		dadosPerson.add(this.labelRendaPerson);
		dadosPerson.add(this.labelReligiaoPerson);
		dadosPerson.add(this.labelCorPerson);
		dadosPerson.add(this.labelDoencaPerson);		
		dadosPerson.add(this.labelEducacaoNivelPerson);
		dadosPerson.add(this.labelEstiloOrganizacionalPerson);
		dadosPerson.add(this.labelEstiloPercepcaoPerson);
		dadosPerson.add(this.labelEstiloProcessamentoPerson);
		dadosPerson.add(this.labelEstiloRetencaoPerson);
		dadosPerson.add(this.labelGrandeAreaPerson);
		dadosPerson.add(this.labelAreaPerson);
		dadosPerson.add(this.labellSubareaPerson);
		dadosPerson.add(this.labelCursoFormacaoPerson);
		dadosPerson.add(this.jpanelMeusPersons);
		dadosPerson.add(this.jpanelMeusContents);
		dadosPerson.add(this.jpanelMeusLocations);
		dadosPerson.add(this.labelFreqPositivaDiaria);
		dadosPerson.add(this.labelFreqNegativaDiaria);
		dadosPerson.add(this.labelFreqPositivaSemanal);
		dadosPerson.add(this.labelFreqNegativaSemanal);		
		dadosPerson.add(this.fieldNomeFirstPerson);
		dadosPerson.add(this.fieldEmailPerson);
		dadosPerson.add(this.fieldIdadePerson);
		dadosPerson.add(this.fieldSexoPerson);
		dadosPerson.add(this.fieldRendaPerson);
		dadosPerson.add(this.fieldReligiaoPerson);
		dadosPerson.add(this.fieldCorPerson);
		dadosPerson.add(this.fieldDoencaPerson);
		dadosPerson.add(this.fieldEducacaoNivelPerson);
		dadosPerson.add(this.fieldEstiloOrganizacionalPerson);
		dadosPerson.add(this.fieldEstiloPercepcaoPerson);
		dadosPerson.add(this.fieldEstiloProcessamentoPerson);
		dadosPerson.add(this.fieldEstiloRetencaoPerson);
		dadosPerson.add(this.fieldGrandeAreaPerson);
		dadosPerson.add(this.fieldAreaPerson);
		dadosPerson.add(this.fieldSubareaPerson);
		dadosPerson.add(this.fieldCursoFormacaoPerson);
		dadosPerson.add(this.fieldFreqPositivaDiaria);
		dadosPerson.add(this.fieldFreqNegativaDiaria);
		dadosPerson.add(this.fieldFreqPositivaSemanal);
		dadosPerson.add(this.fieldFreqNegativaSemanal);
		dadosPerson.add(this.jpanelColaboradores);
		dadosPerson.add(this.jpanelColaborativa);
		dadosPerson.add(this.jpanelBaseadaConteudo);
		dadosPerson.add(this.panelMinhasTagsPerson);
		dadosPerson.add(this.buttonNovoPerson);
		dadosPerson.add(this.buttonEditPerson);
		dadosPerson.add(this.buttonSalvePerson);
		dadosPerson.add(this.buttonCancelePerson);
		dadosPerson.add(this.buttonDeletePerson);
		
		
		// ------------------------------------------------------------------------------------------------------
		//Dados dos conteúdos
		// ------------------------------------------------------------------------------------------------------
		dadosContent = new JPanel();
		dadosContent.setLocation(0, 0);
		dadosContent.setSize(500, 690);
		dadosContent.setLayout(null);

		comboContent = new JComboBox<Content>();
		comboContent.setLocation(10, 10);
		comboContent.setSize(150, 25);		
		
		labelTituloContent = new JLabel("Título do Conteúdo:");
		labelTituloContent.setLocation(20, 50);
		labelTituloContent.setSize(140, 25);	 

		fieldTituloContent = new JTextField("");
		fieldTituloContent.setLocation(180, 50);
		fieldTituloContent.setSize(200, 25);	 
		fieldTituloContent.setEditable(false);

		labelTypeContent = new JLabel("Tipo de Conteúdo:");
		labelTypeContent.setLocation(20, 75);
		labelTypeContent.setSize(140, 25);

		fieldTypeContent = new JTextField();
		fieldTypeContent.setLocation(180, 75);
		fieldTypeContent.setSize(200, 25);	 
		fieldTypeContent.setEditable(false);

		labelSubtypeContent = new JLabel("Subitpo de Conteúdo:");
		labelSubtypeContent.setLocation(20, 100);
		labelSubtypeContent.setSize(140, 25);

		fieldSubtypeContent = new JTextField();
		fieldSubtypeContent.setLocation(180, 100);
		fieldSubtypeContent.setSize(200, 25);	 
		fieldSubtypeContent.setEditable(false);
		
		labelVisibilityContent = new JLabel("Visibilidade:");
		labelVisibilityContent.setLocation(20, 125);
		labelVisibilityContent.setSize(140, 25);
		
		fieldVisibilityContent = new JTextField();
		fieldVisibilityContent.setLocation(180, 125);
		fieldVisibilityContent.setSize(200, 25);	 
		fieldVisibilityContent.setEditable(false);
		
		labelDescriptionContent = new JLabel("Descrição:");
		labelDescriptionContent.setLocation(20, 150);
		labelDescriptionContent.setSize(140, 25);

		fieldDescriptionContent = new JTextField();
		fieldDescriptionContent.setLocation(180, 150);
		fieldDescriptionContent.setSize(200, 25);	 
		fieldDescriptionContent.setEditable(false);

		labelAgeContent = new JLabel("Idade da Publicação:");
		labelAgeContent.setLocation(20, 175);
		labelAgeContent.setSize(140, 25);
		
		fieldAgeContent = new JTextField();
		fieldAgeContent.setLocation(180, 175);
		fieldAgeContent.setSize(200, 25);	 
		fieldAgeContent.setEditable(false);

		labelPersonContent = new JLabel("Referenciador:");
		labelPersonContent.setLocation(20, 200);
		labelPersonContent.setSize(140, 25);

		fieldPersonContent = new JTextField();
		fieldPersonContent.setLocation(180, 200);
		fieldPersonContent.setSize(200, 25);	 
		fieldPersonContent.setEditable(false);

		labelAutorContent = new JLabel("Autor:");
		labelAutorContent.setLocation(20, 225);
		labelAutorContent.setSize(140, 25);

		fieldAutorContent = new JTextField();
		fieldAutorContent.setLocation(180, 225);
		fieldAutorContent.setSize(200, 25);	 
		fieldAutorContent.setEditable(false);

		labelGenericLinkContent = new JLabel("Link:");
		labelGenericLinkContent.setLocation(20, 250);
		labelGenericLinkContent.setSize(140, 25);

		fieldGenericLinkContent = new JTextField();
		fieldGenericLinkContent.setLocation(180, 250);
		fieldGenericLinkContent.setSize(200, 25);	 
		fieldGenericLinkContent.setEditable(false);

		labelBytesContent = new JLabel("Bytes:");
		labelBytesContent.setLocation(20, 275);
		labelBytesContent.setSize(140, 25);

		fieldBytesContent = new JTextField();
		fieldBytesContent.setLocation(180, 275);
		fieldBytesContent.setSize(200, 25);	 
		fieldBytesContent.setEditable(false);

		labelDuracaoContent = new JLabel("Duração:");
		labelDuracaoContent.setLocation(20, 300);
		labelDuracaoContent.setSize(140, 25);

		fieldDuracaoContent = new JTextField();
		fieldDuracaoContent.setLocation(180, 300);
		fieldDuracaoContent.setSize(200, 25);	 
		fieldDuracaoContent.setEditable(false);

		labelPercentualAceitacaoContent = new JLabel("Aceitação:");
		labelPercentualAceitacaoContent.setLocation(20, 325);
		labelPercentualAceitacaoContent.setSize(80, 25);

		fieldPercentualAceitacaoContent = new JTextField();
		fieldPercentualAceitacaoContent.setLocation(180, 325);
		fieldPercentualAceitacaoContent.setSize(200, 25);	 
		fieldPercentualAceitacaoContent.setEditable(false);

		labelRateColabPonderContent = new JLabel("Média Avaliativa:");
		labelRateColabPonderContent.setLocation(20, 350);
		labelRateColabPonderContent.setSize(140, 25);

		fieldRateColabPonderContent = new JTextField();
		fieldRateColabPonderContent.setLocation(180, 350);
		fieldRateColabPonderContent.setSize(200, 25);	 
		fieldRateColabPonderContent.setEditable(false);

		labelFreqPositivaSemanalContent = new JLabel("Freq. Semanal Positiva:");
		labelFreqPositivaSemanalContent.setLocation(20, 375);
		labelFreqPositivaSemanalContent.setSize(140, 25);

		fieldFreqPositivaSemanalContent = new JTextField();
		fieldFreqPositivaSemanalContent.setLocation(180, 375);
		fieldFreqPositivaSemanalContent.setSize(200, 25);	 
		fieldFreqPositivaSemanalContent.setEditable(false);

		labelFreqNegativaSemanalContent = new JLabel("Freq. Semanal Negativa:");
		labelFreqNegativaSemanalContent.setLocation(20, 400);
		labelFreqNegativaSemanalContent.setSize(140, 25);

		fieldFreqNegativaSemanalContent = new JTextField();
		fieldFreqNegativaSemanalContent.setLocation(180, 400);
		fieldFreqNegativaSemanalContent.setSize(200, 25);	 
		fieldFreqNegativaSemanalContent.setEditable(false);

		labelFreqPositivaDiariaContent = new JLabel("Freq. Diaria Positiva:");
		labelFreqPositivaDiariaContent.setLocation(20, 425);
		labelFreqPositivaDiariaContent.setSize(140, 25);

		fieldFreqPositivaDiariaContent = new JTextField();
		fieldFreqPositivaDiariaContent.setLocation(180, 425);
		fieldFreqPositivaDiariaContent.setSize(300, 25);	 
		fieldFreqPositivaDiariaContent.setEditable(false);

		labelFreqNegativaDiariaContent = new JLabel("Freq. Diaria Negativa:");
		labelFreqNegativaDiariaContent.setLocation(20, 450);
		labelFreqNegativaDiariaContent.setSize(140, 25);

		fieldFreqNegativaDiariaContent = new JTextField();
		fieldFreqNegativaDiariaContent.setLocation(180, 450);
		fieldFreqNegativaDiariaContent.setSize(300, 25);	 
		fieldFreqNegativaDiariaContent.setEditable(false);

		panelMinhasTagsContent = new JPanel();
		panelMinhasTagsContent.setBorder( BorderFactory.createTitledBorder("Minhas Tags"));
		panelMinhasTagsContent.setLocation(20, 490);
		panelMinhasTagsContent.setSize(160, 60);
		panelMinhasTagsContent.setLayout(null);

		comboMinhasTagsContent = new JComboBox<String>();
		comboMinhasTagsContent.setLocation(10, 20);
		comboMinhasTagsContent.setSize(140, 25);		
		panelMinhasTagsContent.add(this.comboMinhasTagsContent);

		panelMeusLocationsContent = new JPanel();
		panelMeusLocationsContent.setBorder( BorderFactory.createTitledBorder("Meus Locais"));
		panelMeusLocationsContent.setLocation(200, 490);
		panelMeusLocationsContent.setSize(160, 60);
		panelMeusLocationsContent.setLayout(null);

		comboMeusLocaisContent = new JComboBox<Location>();
		comboMeusLocaisContent.setLocation(10, 20);
		comboMeusLocaisContent.setSize(140, 25);		
		panelMeusLocationsContent.add(this.comboMeusLocaisContent);

		buttonNovoContent = new JButton("Novo");  
		buttonNovoContent.setLocation(20, 600 );  
		buttonNovoContent.setSize(90, 30);  
		buttonNovoContent.addActionListener(this);  
		buttonNovoContent.setToolTipText("Adiciona um novo conteúdo ao sistema");

		buttonEditContent = new JButton("Edite");  
		buttonEditContent.setLocation(117, 600 );  
		buttonEditContent.setSize(90, 30);  
		buttonEditContent.addActionListener(this);  
		buttonEditContent.setToolTipText("Edite os dados do conteúdo");

		buttonSalveContent = new JButton("Salvar");  
		buttonSalveContent.setLocation(214, 600 );  
		buttonSalveContent.setSize(90, 30);
		buttonSalveContent.setEnabled(false);
		buttonSalveContent.addActionListener(this);  
		buttonSalveContent.setToolTipText("Salva os dados do conteúdo no sistema");
		
		buttonCanceleContent = new JButton("Cancelar");  
		buttonCanceleContent.setLocation(311, 600 );  
		buttonCanceleContent.setSize(90, 30);
		buttonCanceleContent.setEnabled(false);
		buttonCanceleContent.addActionListener(this);  
		buttonCanceleContent.setToolTipText("Cancela a operação");

		buttonDeleteContent = new JButton("Apagar");  
		buttonDeleteContent.setLocation(408, 600 );  
		buttonDeleteContent.setSize(90, 30);  
		buttonDeleteContent.addActionListener(this);  
		buttonDeleteContent.setToolTipText("Apaga o conteúdo no sistema");

		
		dadosContent.add(comboContent);
		dadosContent.add(labelTituloContent);
		dadosContent.add(labelTypeContent);
		dadosContent.add(labelSubtypeContent);
		dadosContent.add(labelVisibilityContent);
		dadosContent.add(labelDescriptionContent);
		dadosContent.add(labelAgeContent);
		dadosContent.add(labelPersonContent);
		dadosContent.add(labelAutorContent);
		dadosContent.add(labelGenericLinkContent);
		dadosContent.add(labelBytesContent);
		dadosContent.add(labelDuracaoContent);
		dadosContent.add(labelPercentualAceitacaoContent);
		dadosContent.add(labelRateColabPonderContent);
		dadosContent.add(labelFreqPositivaDiariaContent);
		dadosContent.add(labelFreqNegativaDiariaContent);
		dadosContent.add(labelFreqPositivaSemanalContent);
		dadosContent.add(labelFreqNegativaSemanalContent);
		dadosContent.add(fieldTituloContent);
		dadosContent.add(fieldTypeContent);
		dadosContent.add(fieldSubtypeContent);
		dadosContent.add(fieldVisibilityContent);
		dadosContent.add(fieldDescriptionContent);
		dadosContent.add(fieldAgeContent);
		dadosContent.add(fieldPersonContent);
		dadosContent.add(fieldAutorContent);
		dadosContent.add(fieldGenericLinkContent);
		dadosContent.add(fieldBytesContent);
		dadosContent.add(fieldDuracaoContent);
		dadosContent.add(fieldPercentualAceitacaoContent);
		dadosContent.add(fieldRateColabPonderContent);
		dadosContent.add(fieldFreqPositivaDiariaContent);
		dadosContent.add(fieldFreqNegativaDiariaContent);
		dadosContent.add(fieldFreqPositivaSemanalContent);
		dadosContent.add(fieldFreqNegativaSemanalContent);
		dadosContent.add(panelMinhasTagsContent);
		dadosContent.add(panelMeusLocationsContent);
		dadosContent.add(this.buttonNovoContent);
		dadosContent.add(this.buttonEditContent);
		dadosContent.add(this.buttonSalveContent);
		dadosContent.add(this.buttonCanceleContent);
		dadosContent.add(this.buttonDeleteContent);
				
		// ------------------------------------------------------------------------------------------------------
		//Dados dos locais		
		// ------------------------------------------------------------------------------------------------------
		dadosLocation = new JPanel();
		dadosLocation.setLocation(0, 0);
		dadosLocation.setSize(500, 690);
		dadosLocation.setLayout(null);

		comboLocation = new JComboBox<Location>();
		comboLocation.setLocation(10, 10);
		comboLocation.setSize(150, 25);		

		labelNomeLocation = new JLabel("Nome do Local");
		labelNomeLocation.setLocation(20, 50);
		labelNomeLocation.setSize(140, 25);	 

		labelIdGoogleLocation = new JLabel("IDGoogle:");
		labelIdGoogleLocation.setLocation(20, 75);
		labelIdGoogleLocation.setSize(140, 25);	 

		labelLatidudeLocation = new JLabel("Latitude:");
		labelLatidudeLocation.setLocation(20,100);
		labelLatidudeLocation.setSize(140, 25);	 

		labelLongitudeLocation = new JLabel("Longitude:");
		labelLongitudeLocation.setLocation(20,125);
		labelLongitudeLocation.setSize(140, 25);	 

		labelAltitudeLocation = new JLabel("Altutude");
		labelAltitudeLocation.setLocation(20,150);
		labelAltitudeLocation.setSize(140, 25);	 

		labelPaisLocation = new JLabel("País:");
		labelPaisLocation.setLocation(20,175);
		labelPaisLocation.setSize(140, 25);	 

		labelEstadoLocation = new JLabel("Estado:");
		labelEstadoLocation.setLocation(20,200);
		labelEstadoLocation.setSize(140, 25);	 

		labelCidadeLocation = new JLabel("Cidade:");
		labelCidadeLocation.setLocation(20,225);
		labelCidadeLocation.setSize(140, 25);	 

		labelBairroLocation = new JLabel("Bairro:");
		labelBairroLocation.setLocation(20,250);
		labelBairroLocation.setSize(140, 25);	 

		labelLogradouroLocation = new JLabel("Logradouro:");
		labelLogradouroLocation.setLocation(20,275);
		labelLogradouroLocation.setSize(140, 25);	 

		labelNumeroLocation = new JLabel("Número:");
		labelNumeroLocation.setLocation(20,300);
		labelNumeroLocation.setSize(140, 25);	 

		labelComplementoLocation = new JLabel("Complemento:");
		labelComplementoLocation.setLocation(20,325);
		labelComplementoLocation.setSize(140, 25);	 

		labelCEPLocation = new JLabel("CEP:");
		labelCEPLocation.setLocation(20,350);
		labelCEPLocation.setSize(140, 25);	 

		labelRatingLocation = new JLabel("Média Avaliativa:");
		labelRatingLocation.setLocation(20,375);
		labelRatingLocation.setSize(140, 25);	 

		labelFreqPositivaSemanalLocation = new JLabel("Freq. Semanal Positiva:");
		labelFreqPositivaSemanalLocation.setLocation(20, 400);
		labelFreqPositivaSemanalLocation.setSize(140, 25);

		labelFreqNegativaSemanalLocation = new JLabel("Freq. Semanal Negativa:");
		labelFreqNegativaSemanalLocation.setLocation(20, 425);
		labelFreqNegativaSemanalLocation.setSize(140, 25);

		labelFreqPositivaDiariaLocation = new JLabel("Freq. Diaria Positiva:");
		labelFreqPositivaDiariaLocation.setLocation(20, 450);
		labelFreqPositivaDiariaLocation.setSize(140, 25);

		labelFreqNegativaDiariaLocation = new JLabel("Freq. Diaria Negativa:");
		labelFreqNegativaDiariaLocation.setLocation(20, 475);
		labelFreqNegativaDiariaLocation.setSize(140, 25);

		fieldNomeLocation = new JTextField();
		fieldNomeLocation.setLocation(180, 50);
		fieldNomeLocation.setSize(200, 25);	 
		fieldNomeLocation.setEditable(false);

		fieldIdGoogleLocation = new JTextField();
		fieldIdGoogleLocation.setLocation(180, 75);
		fieldIdGoogleLocation.setSize(200, 25);	 
		fieldIdGoogleLocation.setEditable(false);

		fieldLatitudeLocation = new JTextField();
		fieldLatitudeLocation.setLocation(180, 100);
		fieldLatitudeLocation.setSize(200, 25);	 
		fieldLatitudeLocation.setEditable(false);

		fieldLongitudeLocation = new JTextField();
		fieldLongitudeLocation.setLocation(180, 125);
		fieldLongitudeLocation.setSize(200, 25);	 
		fieldLongitudeLocation.setEditable(false);

		fieldAltitudeLocation = new JTextField();
		fieldAltitudeLocation.setLocation(180, 150);
		fieldAltitudeLocation.setSize(200, 25);	 
		fieldAltitudeLocation.setEditable(false);

		fieldPaisLocation = new JTextField();
		fieldPaisLocation.setLocation(180, 175);
		fieldPaisLocation.setSize(200, 25);	 
		fieldPaisLocation.setEditable(false);

		fieldEstadoLocation = new JTextField();
		fieldEstadoLocation.setLocation(180, 200);
		fieldEstadoLocation.setSize(200, 25);	 
		fieldEstadoLocation.setEditable(false);

		fieldCidadeLocation = new JTextField();
		fieldCidadeLocation.setLocation(180, 225);
		fieldCidadeLocation.setSize(200, 25);	 
		fieldCidadeLocation.setEditable(false);
		
		fieldBairroLocation = new JTextField();
		fieldBairroLocation.setLocation(180, 250);
		fieldBairroLocation.setSize(200, 25);	 
		fieldBairroLocation.setEditable(false);

		fieldLogradouroLocation = new JTextField();
		fieldLogradouroLocation.setLocation(180, 275);
		fieldLogradouroLocation.setSize(200, 25);	 
		fieldLogradouroLocation.setEditable(false);

		fieldNumeroLocation = new JTextField();
		fieldNumeroLocation.setLocation(180, 300);
		fieldNumeroLocation.setSize(200, 25);	 
		fieldNumeroLocation.setEditable(false);

		fieldComplementoLocation = new JTextField();
		fieldComplementoLocation.setLocation(180, 325);
		fieldComplementoLocation.setSize(200, 25);	 
		fieldComplementoLocation.setEditable(false);

		fieldCEPLocation = new JTextField();
		fieldCEPLocation.setLocation(180, 350);
		fieldCEPLocation.setSize(200, 25);	 
		fieldCEPLocation.setEditable(false);

		fieldRatingLocation = new JTextField();
		fieldRatingLocation.setLocation(180, 375);
		fieldRatingLocation.setSize(200, 25);	 
		fieldRatingLocation.setEditable(false);

		fieldFreqPositivaSemanalLocation = new JTextField();
		fieldFreqPositivaSemanalLocation.setLocation(180, 400);
		fieldFreqPositivaSemanalLocation.setSize(200, 25);	 
		fieldFreqPositivaSemanalLocation.setEditable(false);

		fieldFreqNegativaSemanalLocation = new JTextField();
		fieldFreqNegativaSemanalLocation.setLocation(180, 425);
		fieldFreqNegativaSemanalLocation.setSize(200, 25);	 
		fieldFreqNegativaSemanalLocation.setEditable(false);

		fieldFreqPositivaDiariaLocation = new JTextField();
		fieldFreqPositivaDiariaLocation.setLocation(180, 450);
		fieldFreqPositivaDiariaLocation.setSize(300, 25);	 
		fieldFreqPositivaDiariaLocation.setEditable(false);

		fieldFreqNegativaDiariaLocation = new JTextField();
		fieldFreqNegativaDiariaLocation.setLocation(180, 475);
		fieldFreqNegativaDiariaLocation.setSize(300, 25);	 
		fieldFreqNegativaDiariaLocation.setEditable(false);

		
		panelMinhasTagsLocation = new JPanel();
		panelMinhasTagsLocation.setBorder( BorderFactory.createTitledBorder("Minhas Tags"));
		panelMinhasTagsLocation.setLocation(20, 515);
		panelMinhasTagsLocation.setSize(160, 60);
		panelMinhasTagsLocation.setLayout(null);

		comboMinhasTagsLocation = new JComboBox<String>();
		comboMinhasTagsLocation.setLocation(10, 20);
		comboMinhasTagsLocation.setSize(140, 25);		
		panelMinhasTagsLocation.add(this.comboMinhasTagsLocation);

		panelMeusTiposLocation = new JPanel();
		panelMeusTiposLocation.setBorder( BorderFactory.createTitledBorder("Meus Tipos"));
		panelMeusTiposLocation.setLocation(200, 515);
		panelMeusTiposLocation.setSize(160, 60);
		panelMeusTiposLocation.setLayout(null);

		comboMeusTiposLocation = new JComboBox<String>();
		comboMeusTiposLocation.setLocation(10, 20);
		comboMeusTiposLocation.setSize(140, 25);		
		panelMeusTiposLocation.add(this.comboMeusTiposLocation);	

		buttonNovoLocation = new JButton("Novo");  
		buttonNovoLocation.setLocation(20, 600 );  
		buttonNovoLocation.setSize(90, 30);  
		buttonNovoLocation.addActionListener(this);  
		buttonNovoLocation.setToolTipText("Adiciona um novo conteúdo ao sistema");

		buttonEditLocation = new JButton("Edite");  
		buttonEditLocation.setLocation(117, 600 );  
		buttonEditLocation.setSize(90, 30);  
		buttonEditLocation.addActionListener(this);  
		buttonEditLocation.setToolTipText("Edite os dados do conteúdo");

		buttonSalveLocation = new JButton("Salvar");  
		buttonSalveLocation.setLocation(214, 600 );  
		buttonSalveLocation.setSize(90, 30); 
		buttonSalveLocation.setEnabled(false);
		buttonSalveLocation.addActionListener(this);  
		buttonSalveLocation.setToolTipText("Salva os dados do conteúdo no sistema");
		
		buttonCanceleLocation = new JButton("Cancelar");  
		buttonCanceleLocation.setLocation(311, 600 );  
		buttonCanceleLocation.setSize(90, 30);  
		buttonCanceleLocation.setEnabled(false);
		buttonCanceleLocation.addActionListener(this);  
		buttonCanceleLocation.setToolTipText("Cancela a operação");

		buttonDeleteLocation = new JButton("Apagar");  
		buttonDeleteLocation.setLocation(408, 600 );  
		buttonDeleteLocation.setSize(90, 30);  
		buttonDeleteLocation.addActionListener(this);  
		buttonDeleteLocation.setToolTipText("Apaga o conteúdo no sistema");

		dadosLocation.add(comboLocation);
		dadosLocation.add(labelNomeLocation);
		dadosLocation.add(labelIdGoogleLocation);
		dadosLocation.add(labelLatidudeLocation);
		dadosLocation.add(labelLongitudeLocation);
		dadosLocation.add(labelAltitudeLocation);
		dadosLocation.add(labelPaisLocation);
		dadosLocation.add(labelEstadoLocation);
		dadosLocation.add(labelCidadeLocation);
		dadosLocation.add(labelBairroLocation);
		dadosLocation.add(labelLogradouroLocation);
		dadosLocation.add(labelNumeroLocation);
		dadosLocation.add(labelComplementoLocation);
		dadosLocation.add(labelCEPLocation);
		dadosLocation.add(labelRatingLocation);		
		dadosLocation.add(labelFreqPositivaDiariaLocation);
		dadosLocation.add(labelFreqNegativaDiariaLocation);
		dadosLocation.add(labelFreqPositivaSemanalLocation);
		dadosLocation.add(labelFreqNegativaSemanalLocation);
		dadosLocation.add(fieldNomeLocation);
		dadosLocation.add(fieldIdGoogleLocation);
		dadosLocation.add(fieldLatitudeLocation);
		dadosLocation.add(fieldLongitudeLocation);
		dadosLocation.add(fieldAltitudeLocation);
		dadosLocation.add(fieldPaisLocation);
		dadosLocation.add(fieldEstadoLocation);
		dadosLocation.add(fieldCidadeLocation);
		dadosLocation.add(fieldBairroLocation);
		dadosLocation.add(fieldLogradouroLocation);
		dadosLocation.add(fieldNumeroLocation);
		dadosLocation.add(fieldComplementoLocation);
		dadosLocation.add(fieldCEPLocation);
		dadosLocation.add(fieldRatingLocation);
		dadosLocation.add(fieldFreqPositivaDiariaLocation);
		dadosLocation.add(fieldFreqNegativaDiariaLocation);
		dadosLocation.add(fieldFreqPositivaSemanalLocation);
		dadosLocation.add(fieldFreqNegativaSemanalLocation);
		dadosLocation.add(panelMinhasTagsLocation);
		dadosLocation.add(panelMeusTiposLocation);
		dadosLocation.add(this.buttonNovoLocation);
		dadosLocation.add(this.buttonEditLocation);
		dadosLocation.add(this.buttonSalveLocation);
		dadosLocation.add(this.buttonCanceleLocation);
		dadosLocation.add(this.buttonDeleteLocation);

		// ------------------------------------------------------------------------------------------------------
		// Painel de relações
		// ------------------------------------------------------------------------------------------------------
/*		relacaoPersonPerson = new JPanel();
		relacaoPersonPerson.setLocation(10, 0);
		relacaoPersonPerson.setSize(500, 165);
		relacaoPersonPerson.setLayout(null);

		comboRelacaoPersonPerson = new JComboBox<Location>();
		comboRelacaoPersonPerson.setLocation(10, 20);
		comboRelacaoPersonPerson.setSize(150, 25);		

		labelRelatePPNomePerson1 = new JLabel("Person 1");
		labelRelatePPNomePerson1.setLocation(40, 75);
		labelRelatePPNomePerson1.setSize(80, 25);	 

		labelRelatePPNomePerson2 = new JLabel("Person 2");
		labelRelatePPNomePerson2.setLocation(40, 105);
		labelRelatePPNomePerson2.setSize(80, 25);	 

		labelRelatePPAfinidade = new JLabel("Afinidade");
		labelRelatePPAfinidade.setLocation(40, 135);
		labelRelatePPAfinidade.setSize(80, 25);	 

		labelRelatePPData = new JLabel("Data");
		labelRelatePPData.setLocation(40, 170);
		labelRelatePPData.setSize(80, 25);	 

		fieldRelatePPNomePerson1 = new JTextField();
		fieldRelatePPNomePerson1.setLocation(120, 75);
		fieldRelatePPNomePerson1.setSize(100, 25);	 
		fieldRelatePPNomePerson1.setEditable(false);

		fieldRelatePPNomePerson2 = new JTextField();
		fieldRelatePPNomePerson2.setLocation(120, 105);
		fieldRelatePPNomePerson2.setSize(100, 25);	 
		fieldRelatePPNomePerson2.setEditable(false);

		fieldRelatePPAfinidade = new JTextField();
		fieldRelatePPAfinidade.setLocation(120, 135);
		fieldRelatePPAfinidade.setSize(100, 25);	 
		fieldRelatePPAfinidade.setEditable(false);

		fieldRelatePPData = new JTextField();
		fieldRelatePPData.setLocation(120, 170);
		fieldRelatePPData.setSize(100, 25);	 
		fieldRelatePPData.setEditable(false);
		
		relacaoPersonPerson.add(comboRelacaoPersonPerson);
		relacaoPersonPerson.add(labelRelatePPNomePerson1);
		relacaoPersonPerson.add(labelRelatePPNomePerson2);
		relacaoPersonPerson.add(labelRelatePPAfinidade);
		relacaoPersonPerson.add(labelRelatePPData);
		relacaoPersonPerson.add(fieldRelatePPNomePerson1);
		relacaoPersonPerson.add(fieldRelatePPNomePerson2);
		relacaoPersonPerson.add(fieldRelatePPAfinidade);
		relacaoPersonPerson.add(fieldRelatePPData);
		
		relacaoPersonContent = new JPanel();
		relacaoPersonContent.setBorder( BorderFactory.createTitledBorder("Person - Content"));
		relacaoPersonContent.setLocation(10, 165);
		relacaoPersonContent.setSize(500, 165);
		relacaoPersonContent.setLayout(null);

		relacaoPersonLocation = new JPanel();
		relacaoPersonLocation.setBorder( BorderFactory.createTitledBorder("Person - Location"));
		relacaoPersonLocation.setLocation(10, 330);
		relacaoPersonLocation.setSize(500, 165);
		relacaoPersonLocation.setLayout(null);

		relacaoContentLocation = new JPanel();
		relacaoContentLocation.setBorder( BorderFactory.createTitledBorder("Content - Location"));
		relacaoContentLocation.setLocation(10, 495);
		relacaoContentLocation.setSize(500, 165);
		relacaoContentLocation.setLayout(null);

		
		// ------------------------------------------------------------------------------------------------------
		// Adição dos paineis no Tabbed Panel
		// ------------------------------------------------------------------------------------------------------
		verDados.add("  Person-Person  ", relacaoPersonPerson);
		verDados.add("  Person-Content  ", dadosRelacao2);
		verDados.add(" Person-Location  ", dadosRelacao3);
		verDados.add(" Content-Location ", dadosRelacao4);*/
		verDados.add("      Pessoas      ", dadosPerson);
		verDados.add("      Conteúdos      ", dadosContent);
		verDados.add("      Locais      ", dadosLocation);
	//	verDados.add("      Configuração      ", dadosSistema);
		
		// ------------------------------------------------------------------------------------------------------
		// Janela Principal da Simulação
		// ------------------------------------------------------------------------------------------------------
		Janela = new JFrame();  
		
		Janela.setTitle("Sistema de Recomendação Híbrida - v. 2.0 Antonio Farias - PPgCC UFERSA 2013");  
		Janela.setLayout(null);  
		Janela.setExtendedState(MAXIMIZED_BOTH);
		Janela.add(resetaSimulacao);
		Janela.add(sairSistema);
		
		Janela.add(dadosEntrada);
		Janela.add(verDados);
		Janela.add(dadosContexto);
		Janela.add(recomendacaoHibrida);
		Janela.add(recomendacaoConjunta);
		Janela.add(log);
		Janela.add(panelBotoesControle);
		
		Janela.setLocation(50, 50);
		Janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		//Janela.setSize(800,600);  
		Janela.setVisible(true);  
		Janela.show();  	  
	} 
	
	//------------------------------------------------------------------------------------------------------
	// EVENTOS
	//------------------------------------------------------------------------------------------------------
	public void actionPerformed(ActionEvent e)  
	{  
		if (e.getSource() == carregaBD) 
		{
			
			//SemelhancaSemantica SS = new SemelhancaSemantica();
			//float aparenciaSemantica = SS.getSemelhancaSemantica(1, 3006, 3008);
			// CENÁRIO: Dados provenientes do BD:
			this.numeroPessoas.setText(iUbi.getCountPerson()+"");
			this.numeroConteudos.setText(iUbi.getCountContent()+"");
			this.numeroLocais.setText(iUbi.getCountLocation()+"");
			
			carregaBD.setEnabled(false);
			geraBaseColaboradores.setEnabled(true);
			
			setBottaoAnterior(0);
			setPreparaDados();
			setVerDadosPerson();
			setVerDadosContent();
			setVerDadosLocation();
		}
		else if (e.getSource() == geraBaseColaboradores) 
		{  
			int tamanhoListaColaboradores = Integer.parseInt(this.tamanhoListaColaboradores.getText());
		
			iUbi.gerarListaColaboradores(tamanhoListaColaboradores);
			
			geraBaseColaborativaPonderada.setEnabled(true);
			geraBaseColaboradores.setEnabled(false);
			
			setVerDadosPerson();
			setVerDadosContent();
			setVerDadosLocation();
		}
		else if (e.getSource() == geraBaseColaborativaPonderada) 
		{  
			int tamanhoListaColaborativa = Integer.parseInt(this.tamanhoListaColaborativa.getText());

			iUbi.gerarListaColaborativa(tamanhoListaColaborativa);
			
			geraBaseBaseadaConteudo.setEnabled(true);
			geraBaseColaborativaPonderada.setEnabled(false);
			
			setVerDadosPerson();
			setVerDadosContent();
			setVerDadosLocation();
		}

		else if (e.getSource() == geraBaseBaseadaConteudo) 
		{  
			int tamanhoListaBaseadaConteudo = Integer.parseInt(this.tamanhoListaBaseadaConteudo.getText());
		
			iUbi.gerarListaBaseadaConteudo(tamanhoListaBaseadaConteudo);
			
			geraRecomendacaoHibrida.setEnabled(true);
			geraBaseBaseadaConteudo.setEnabled(false);
			
			setVerDadosPerson();
			setVerDadosContent();
			setVerDadosLocation();
		}
		else if (e.getSource() == geraRecomendacaoHibrida) 
		{  
			Simulation simulation = new Simulation();
			
			// Person alvo da recomendação
			Person person = (Person) this.comboPerson.getSelectedItem();
			
			// Produz contexto aleatório
			context = simulation.geraContext();
			
			// Atribui esse contexto à pessoa selecionada
			person.getListContexts().add(context);
			
			// Exibe os atributos de contexto na tela
			setVerContext();
			
			int tamanhoDaListaUbiqua = person.getListRelatePersonContent(RelatePersonContent.STATUS_RECOMMENDED).size();
			
			// Objeto da recomendação ubiqua
			Ubiqua ubiqua = new Ubiqua();
			
			// Geração da lista ubiqua (em tempo real devido a sensibilidade ao contexto)

			// Solicita Recomendação Híbrida
			resultadoRecomendacaoHibrida = iUbi.gerarListaHibrida(person, ubiqua.geraListaUbiqua(person, context, tamanhoDaListaUbiqua));
						
			geraRecomendacaoConjunta.setEnabled(true);			
			geraRecomendacaoHibrida.setEnabled(false);
			
			setPreparaResultadoHibrido();
		}
		else if (e.getSource() == geraRecomendacaoConjunta) 
		{  
			// Solicita Recomendação Conjunta
			resultadoRecomendacaoConjunta = iUbi.gerarListaConjunta(resultadoRecomendacaoHibrida);			
			geraRecomendacaoConjunta.setEnabled(false);
			
			setPreparaResultadoConjunto();
		}
		//-------------------------------------------------------------------------------------------------
		// CRUD Person
		//-------------------------------------------------------------------------------------------------
		else if (e.getSource() == comboPerson) 
		{  
			setVerDadosPerson();
		}
		else if (e.getSource() == buttonNovoPerson) 
		{	
			buttonNovoPerson.setEnabled(false);
			buttonEditPerson.setEnabled(false);
			buttonSalvePerson.setEnabled(true);
			buttonCancelePerson.setEnabled(true);
			buttonDeletePerson.setEnabled(false);
								
			setBottaoAnterior(1);
			
			preparaEdicaoPerson();		
			limpaEdicaoPerson();
		}
		else if (e.getSource() == buttonEditPerson) 
		{  
			buttonNovoPerson.setEnabled(false);
			buttonEditPerson.setEnabled(false);
			buttonSalvePerson.setEnabled(true);
			buttonCancelePerson.setEnabled(true);
			buttonDeletePerson.setEnabled(false);

			setBottaoAnterior(2);
			
			preparaEdicaoPerson();
		}
		else if (e.getSource() == buttonCancelePerson) 
		{
			buttonNovoPerson.setEnabled(true);
			buttonEditPerson.setEnabled(true);
			buttonSalvePerson.setEnabled(false);
			buttonCancelePerson.setEnabled(false);
			buttonDeletePerson.setEnabled(true);

			travaEdicaoPerson();
			setVerDadosPerson();
		}
		else if (e.getSource() == buttonSalvePerson) 
		{  	
			buttonNovoPerson.setEnabled(true);
			buttonEditPerson.setEnabled(true);
			buttonSalvePerson.setEnabled(false);
			buttonCancelePerson.setEnabled(false);
			buttonDeletePerson.setEnabled(true);

			salveEdicaoPerson();
			travaEdicaoPerson();
			setVerDadosPerson();
		}
		else if (e.getSource() == buttonDeletePerson) 
		{  	
			deletePerson();
			setVerDadosPerson();
		}
		//-------------------------------------------------------------------------------------------------
		// CRUD Content
		//-------------------------------------------------------------------------------------------------
		else if (e.getSource() == comboContent) 
		{
			setVerDadosContent();
		}
		else if (e.getSource() == buttonNovoContent) 
		{  	
			buttonNovoContent.setEnabled(false);
			buttonEditContent.setEnabled(false);
			buttonSalveContent.setEnabled(true);
			buttonCanceleContent.setEnabled(true);
			buttonDeleteContent.setEnabled(false);
								
			setBottaoAnterior(1);
	
			preparaEdicaoContent();		
			limpaEdicaoContent();
		}
		else if (e.getSource() == buttonEditContent) 
		{  
			buttonNovoContent.setEnabled(false);
			buttonEditContent.setEnabled(false);
			buttonSalveContent.setEnabled(true);
			buttonCanceleContent.setEnabled(true);
			buttonDeleteContent.setEnabled(false);

			setBottaoAnterior(2);
			
			preparaEdicaoContent();
		}
		else if (e.getSource() == buttonCanceleContent) 
		{  
			buttonNovoContent.setEnabled(true);
			buttonEditContent.setEnabled(true);
			buttonSalveContent.setEnabled(false);
			buttonCanceleContent.setEnabled(false);
			buttonDeleteContent.setEnabled(true);

			travaEdicaoContent();
			setVerDadosContent();
		}
		else if (e.getSource() == buttonSalveContent) 
		{  	
			buttonNovoContent.setEnabled(true);
			buttonEditContent.setEnabled(true);
			buttonSalveContent.setEnabled(false);
			buttonCanceleContent.setEnabled(false);
			buttonDeleteContent.setEnabled(true);

			salveEdicaoContent();
			travaEdicaoContent();
			setVerDadosContent();
		}
		else if (e.getSource() == buttonDeleteContent) 
		{  	
			deleteContent();
			setVerDadosContent();
		}
		//-------------------------------------------------------------------------------------------------
		// CRUD Location
		//-------------------------------------------------------------------------------------------------
		else if (e.getSource() == comboLocation) 
		{
			setVerDadosLocation();
		}
		else if (e.getSource() == buttonNovoLocation) 
		{  	
			buttonNovoLocation.setEnabled(false);
			buttonEditLocation.setEnabled(false);
			buttonSalveLocation.setEnabled(true);
			buttonCanceleLocation.setEnabled(true);
			buttonDeleteLocation.setEnabled(false);
								
			setBottaoAnterior(1);
			
			preparaEdicaoLocation();		
			limpaEdicaoLocation();
		}
		else if (e.getSource() == buttonEditLocation) 
		{
			buttonNovoLocation.setEnabled(false);
			buttonEditLocation.setEnabled(false);
			buttonSalveLocation.setEnabled(true);
			buttonCanceleLocation.setEnabled(true);
			buttonDeleteLocation.setEnabled(false);

			setBottaoAnterior(2);
			
			preparaEdicaoLocation();
		}
		else if (e.getSource() == buttonCanceleLocation) 
		{  
			buttonNovoLocation.setEnabled(true);
			buttonEditLocation.setEnabled(true);
			buttonSalveLocation.setEnabled(false);
			buttonCanceleLocation.setEnabled(false);
			buttonDeleteLocation.setEnabled(true);

			travaEdicaoLocation();
			setVerDadosLocation();
		}
		else if (e.getSource() == buttonSalveLocation) 
		{  	
			buttonNovoLocation.setEnabled(true);
			buttonEditLocation.setEnabled(true);
			buttonSalveLocation.setEnabled(false);
			buttonCanceleLocation.setEnabled(false);
			buttonDeleteLocation.setEnabled(true);

			salveEdicaoLocation();
			travaEdicaoLocation();
			setVerDadosLocation();
		}
		else if (e.getSource() == buttonDeleteLocation) 
		{  	
			deleteLocation();
			setVerDadosLocation();

		}
		//-------------------------------------------------------------------------------------------------
		//
		//-------------------------------------------------------------------------------------------------
		else if (e.getSource() == comboResultRecomendacaoHibrida) 
		{  
			setVerResultado();
		}
		else if (e.getSource() == resetaSimulacao) 
		{	
			// CENÁRIO: Dados provenientes da classe Simulation:
			int qtdPessoas = 50;
			int qtdConteudos = 50;
			int qtdLocais = 50;
			Simulation simulation = new Simulation();
			simulation.geraLocais(qtdLocais);
			simulation.geraPessoas(qtdPessoas);
			simulation.geraConteudos(qtdConteudos);	
			simulation.geraRelacaoPersonLocais( Integer.parseInt(numeroLocaisPorPessoa.getText()) );
			simulation.geraRelacaoPersonContent( Integer.parseInt(numeroConteudosPorPessoa.getText()) );
			simulation.geraRelacaoPersonPerson( Integer.parseInt(numeroRelacoes.getText()) );
			simulation.geraRelacaoContentLocais( Integer.parseInt(numeroLocaisPorConteudo.getText()) );	
			
			setPreparaDados();
			
			carregaBD.setEnabled(false);
			geraBaseColaboradores.setEnabled(true);
			
			setBottaoAnterior(0);
			
			setVerDadosPerson();
			setVerDadosContent();
			setVerDadosLocation();
			
		}
		else if (e.getSource() == sairSistema) 
		{  
			System.exit(0);
		}
	}  
 
	public void setVerContext() 
	{	
		this.dataContexto.setText(""+context.getTimeStamp().getTime().getDate());
		this.diaContexto.setText(""+context.getTimeStamp().getTime().getDay());
		this.horaContexto.setText(""+context.getTimeStamp().getTime().getHours());		
		this.bateriaContexto.setText(""+context.getBattery());
		this.velocidadeConexaoContexto.setText(""+context.getConnectionSpeed());
		this.velocidadePessoaContexto.setText(""+context.getPersonSpeed());
		this.localContexto.setText(""+context.getLocation().getTitle());	
	}
	
	public void setPreparaResultadoHibrido() 
	{
		this.comboResultRecomendacaoHibrida.removeAllItems();
		
		for(int i = 0; i < this.resultadoRecomendacaoHibrida.size(); i++)
		{
			RelatePersonContent relatePersonContent = this.resultadoRecomendacaoHibrida.get(i);
			this.comboResultRecomendacaoHibrida.addItem(relatePersonContent.getContent());
		}
		
		this.comboResultRecomendacaoHibrida.addActionListener(this);
		setVerResultado();		
	}

	public void setPreparaResultadoConjunto() 
	{

		this.comboResultRecomendacaoConjunta.removeAllItems();
		
		for(int i = 0; i < this.resultadoRecomendacaoConjunta.size(); i++)
		{
			RelatePersonContent relatePersonContent = this.resultadoRecomendacaoConjunta.get(i);
			this.comboResultRecomendacaoConjunta.addItem(relatePersonContent.getContent());
		}

		this.comboResultRecomendacaoConjunta.addActionListener(this);
		
		setVerResultado();		
	}

	public void setVerResultado() 
	{
		Person person = (Person) this.comboPerson.getSelectedItem();
		
		this.recomendadoPerson.setText("Usuário: "+ person.getNameFirst());		
		this.taxaHibrida.setText("Taxa Híbrida: "+ this.resultadoRecomendacaoHibrida.get(this.comboResultRecomendacaoHibrida.getSelectedIndex()).getRatePerson());
		this.pesoColaborativo.setText("Peso Colaborativo: "+ this.resultadoRecomendacaoHibrida.get(this.comboResultRecomendacaoHibrida.getSelectedIndex()).getI1());
		this.pesoBaseadoConteudo.setText("Peso Baseado em Conteúdo: "+ this.resultadoRecomendacaoHibrida.get(this.comboResultRecomendacaoHibrida.getSelectedIndex()).getI2());
		this.pesoUbiquo.setText("Peso Ubíquo: "+ this.resultadoRecomendacaoHibrida.get(this.comboResultRecomendacaoHibrida.getSelectedIndex()).getI3());	
	}
	
	public void setVerDadosPerson() 
	{
		// OBTEM OBJETO
		Person person = (Person) this.comboPerson.getSelectedItem();
		
		if(person != null)
		{
			this.fieldNomeFirstPerson.setText(person.getNameFirst());
			this.fieldEmailPerson.setText(person.getEmail());
			this.fieldIdadePerson.setText(person.getAge()+"");
			this.fieldSexoPerson.setText(person.getGender()+"");
			this.fieldRendaPerson.setText(person.getIncome()+"");
			this.fieldReligiaoPerson.setText(person.getReligion()+"");
			this.fieldCorPerson.setText(person.getRace()+"");
			this.fieldDoencaPerson.setText(person.getDisease());
			this.fieldEducacaoNivelPerson.setText(person.getLearning().getEducationalLevel()+"");
			this.fieldEstiloOrganizacionalPerson.setText(person.getLearning().getEstiloDimenOrganizacao()+"");
			this.fieldEstiloPercepcaoPerson.setText(person.getLearning().getEstiloDimenPercepcao()+"");
			this.fieldEstiloProcessamentoPerson.setText(person.getLearning().getEstiloDimenProcessamento()+"");
			this.fieldEstiloRetencaoPerson.setText(person.getLearning().getEstiloDimenRetencao()+"");
			this.fieldGrandeAreaPerson.setText("Grande Área: " + person.getLearning().getFormationGrandeArea());
			this.fieldAreaPerson.setText(person.getLearning().getFormationArea()+"");
			this.fieldSubareaPerson.setText("Subárea: " + person.getLearning().getFormationSubarea());
			this.fieldCursoFormacaoPerson.setText(person.getLearning().getFormationCourse());		
			this.fieldFreqPositivaDiaria.setText(person.getFrequency().getH24Positive()+" ");
			this.fieldFreqNegativaDiaria.setText(person.getFrequency().getH24Negative()+" ");
			this.fieldFreqPositivaSemanal.setText(person.getFrequency().getH7Positive()+" ");
			this.fieldFreqNegativaSemanal.setText(person.getFrequency().getH7Negative()+" ");
			
			this.comboMeusAmigosPerson.removeAllItems();
			for(int i = 0; i < person.getListRelatePersonPerson(RelatePersonPerson.STATUS_FRIEND).size(); i++){
				this.comboMeusAmigosPerson.addItem(person.getListRelatePersonPerson(RelatePersonPerson.STATUS_FRIEND).get(i).getPerson2());
			}
			
			this.comboMeusConteudosPerson.removeAllItems();
			for(int i = 0; i < person.getListRelatePersonContent(RelatePersonContent.STATUS_CONTENT_FAVORITE).size(); i++){
				this.comboMeusConteudosPerson.addItem(person.getListRelatePersonContent(RelatePersonContent.STATUS_CONTENT_FAVORITE).get(i).getContent());
			}

			this.comboMeusLocaisPerson.removeAllItems();
			for(int i = 0; i < person.getListRelatePersonLocation(RelatePersonLocation.STATUS_LOCATION_FAVORITE).size(); i++){
				this.comboMeusLocaisPerson.addItem(person.getListRelatePersonLocation(RelatePersonLocation.STATUS_LOCATION_FAVORITE).get(i).getLocation());
			}
			
			this.comboMinhasTagsPerson.removeAllItems();
			for(int i = 0; i < person.getListRelatePersonTag().size(); i++){
				this.comboMinhasTagsPerson.addItem(person.getListRelatePersonTag().get(i).getTag().getName());
			}
				
			this.comboColaboradores.removeAllItems();
			List<RelatePersonPerson> Temp = person.getListRelatePersonPerson(RelatePersonPerson.STATUS_COLLABS);
			for(int i = 0; i < Temp.size(); i++){
				this.comboColaboradores.addItem(Temp.get(i).getPerson2());
			}
			
			int sizeTemp = person.getListRelatePersonContent(RelatePersonContent.STATUS_RECOMMENDED).size();
			this.comboColaborativa.removeAllItems();
			for(int i = 0; i < sizeTemp; i++){			
				if(person.getListRelatePersonContent(RelatePersonContent.STATUS_RECOMMENDED).get(i).getSuggestedMethod() == RelatePersonContent.SUGG_METH_PONDER)  {
					this.comboColaborativa.addItem(person.getListRelatePersonContent(RelatePersonContent.STATUS_RECOMMENDED).get(i).getContent());
				}
			}

			this.comboBaseadaConteudo.removeAllItems();
			for(int i = 0; i < sizeTemp; i++){				
				if(person.getListRelatePersonContent(RelatePersonContent.STATUS_RECOMMENDED).get(i).getSuggestedMethod() == RelatePersonContent.SUGG_METH_CONTENT)  {
					this.comboBaseadaConteudo.addItem(person.getListRelatePersonContent(RelatePersonContent.STATUS_RECOMMENDED).get(i).getContent());
				}
				
			}
		}
	}

	public void setVerDadosContent() 
	{	
		// OBTEM OBJETO
		Content content = (Content) this.comboContent.getSelectedItem();

		if(content != null) 
		{
			this.fieldTituloContent.setText(content.getTitle());
			this.fieldTypeContent.setText(content.getType()+"");
			this.fieldSubtypeContent.setText(content.getSubtype()+"");
			this.fieldVisibilityContent.setText(content.getVisibility()+"");			
			this.fieldDescriptionContent.setText(content.getTitle()+"");
			this.fieldAgeContent.setText(content.getAge()+"");
			this.fieldAutorContent.setText(content.getAuthor()+"");
			this.fieldGenericLinkContent.setText(content.getUrlOnline()+"");
			this.fieldBytesContent.setText(content.getBytesOnline()+"");
			this.fieldDuracaoContent.setText(content.getSecondsOnline()+"");
			this.fieldPercentualAceitacaoContent.setText(content.getRateAcceptance()+"");
			this.fieldRateColabPonderContent.setText(content.getRateColabPonder()+"");
			this.fieldFreqPositivaDiariaContent.setText(content.getFrequency().getH24Positive());
			this.fieldFreqNegativaDiariaContent.setText(content.getFrequency().getH24Negative());
			this.fieldFreqPositivaSemanalContent.setText(content.getFrequency().getH7Positive());
			this.fieldFreqNegativaSemanalContent.setText(content.getFrequency().getH7Negative());
			
			this.comboMinhasTagsContent.removeAllItems();
			for(int i = 0; i < content.getListRelateContentTag().size(); i++){
				this.comboMinhasTagsContent.addItem(content.getListRelateContentTag().get(i).getTag().getName());
			}
	
			this.comboMeusLocaisContent.removeAllItems();
			for(int i = 0; i < content.getListRelateContentLocation().size(); i++){
				this.comboMeusLocaisContent.addItem(content.getListRelateContentLocation().get(i).getLocation());
			}
		} 
	}

	public void setVerDadosLocation() 
	{

		// OBTEM OBJETO
		Location location = (Location) this.comboLocation.getSelectedItem();
				
		if(location != null) 
		{	
			this.fieldNomeLocation.setText(location.getTitle());
			this.fieldIdGoogleLocation.setText(location.getIdGoogle());
			this.fieldLatitudeLocation.setText(location.getLatitude()+"");
			this.fieldLongitudeLocation.setText(location.getLongitude()+ "");
			this.fieldAltitudeLocation.setText(location.getAltitude()+ "");
			this.fieldPaisLocation.setText(location.getCountry());
			this.fieldEstadoLocation.setText(location.getState());
			this.fieldCidadeLocation.setText(location.getCity()+"");
			this.fieldBairroLocation.setText(location.getNeighborhood()+ "");
			this.fieldLogradouroLocation.setText(location.getStreetName()+ "");
			this.fieldNumeroLocation.setText(location.getStreetNumber()+ "");
			this.fieldComplementoLocation.setText(location.getDescription());
			this.fieldCEPLocation.setText(location.getCEP()+"");
			this.fieldRatingLocation.setText(location.getRating()+ "");			
			this.fieldFreqPositivaDiariaLocation.setText(location.getFrequency().getH24Positive());
			this.fieldFreqNegativaDiariaLocation.setText(location.getFrequency().getH24Negative());
			this.fieldFreqPositivaSemanalLocation.setText(location.getFrequency().getH7Positive());
			this.fieldFreqNegativaSemanalLocation.setText(location.getFrequency().getH7Negative());
	
			this.comboMinhasTagsLocation.removeAllItems();
					
			for(int i = 0; i < location.getListRelateLocationTag().size(); i++){
				this.comboMinhasTagsLocation.addItem(location.getListRelateLocationTag().get(i).getTag().getName());
			}
	
			this.comboMeusTiposLocation.removeAllItems();
			for(int i = 0; i < location.getListTypes().size(); i++){
				this.comboMeusTiposLocation.addItem(location.getListTypes().get(i));
			}
		}
	}

	public void setPreparaDados() 
	{		
		comboPerson.removeAll();
	
		List<Person> pessoas = iUbi.getAllPerson();
		
		for(int i = 0; i < pessoas.size(); i++)
		{
			this.comboPerson.addItem(pessoas.get(i));
		}
		
		comboContent.removeAll();
		
		List<Content> conteudos = iUbi.getAllContent();
		
		for(int i = 0; i < conteudos.size(); i++)
		{
			this.comboContent.addItem(conteudos.get(i));
		}
		
		comboLocation.removeAll();
		
		List<Location> locais = iUbi.getAllLocation();
		
		for(int i = 0; i < locais.size(); i++)
		{
			this.comboLocation.addItem(locais.get(i));
		}
		
		this.Janela.repaint();
		this.comboPerson.addActionListener(this);
		this.comboContent.addActionListener(this);
		this.comboLocation.addActionListener(this);
		
		setVerDadosPerson();
		setVerDadosContent();
		setVerDadosLocation();
		
	}

	public void preparaEdicaoPerson(){
		
		this.fieldNomeFirstPerson.setEditable(true);
		//this.fieldNomeLastPerson.setEditable(true);
		this.fieldEmailPerson.setEditable(true);
		this.fieldIdadePerson.setEditable(true);
		this.fieldSexoPerson.setEditable(true);
		this.fieldRendaPerson.setEditable(true);
		this.fieldReligiaoPerson.setEditable(true);
		this.fieldCorPerson.setEditable(true);
		this.fieldDoencaPerson.setEditable(true);
		this.fieldEducacaoNivelPerson.setEditable(true);
		this.fieldEstiloOrganizacionalPerson.setEditable(true);
		this.fieldEstiloPercepcaoPerson.setEditable(true);
		this.fieldEstiloProcessamentoPerson.setEditable(true);
		this.fieldEstiloRetencaoPerson.setEditable(true);
		this.fieldGrandeAreaPerson.setEditable(true);
		this.fieldAreaPerson.setEditable(true);
		this.fieldSubareaPerson.setEditable(true);
		this.fieldCursoFormacaoPerson.setEditable(true);
		this.fieldFreqPositivaDiaria.setEditable(true);
		this.fieldFreqNegativaDiaria.setEditable(true);
		this.fieldFreqPositivaSemanal.setEditable(true);
		this.fieldFreqNegativaSemanal.setEditable(true);
		
	}
	
	public void limpaEdicaoPerson() {

		this.fieldNomeFirstPerson.setText("");
		//this.fieldNomeLastPerson.setText("");
		this.fieldEmailPerson.setText("");
		this.fieldIdadePerson.setText("");
		this.fieldSexoPerson.setText("");
		this.fieldRendaPerson.setText("");
		this.fieldReligiaoPerson.setText("");
		this.fieldCorPerson.setText("");
		this.fieldDoencaPerson.setText("");
		this.fieldEducacaoNivelPerson.setText("");
		this.fieldEstiloOrganizacionalPerson.setText("");
		this.fieldEstiloPercepcaoPerson.setText("");
		this.fieldEstiloProcessamentoPerson.setText("");
		this.fieldEstiloRetencaoPerson.setText("");
		this.fieldGrandeAreaPerson.setText("");
		this.fieldAreaPerson.setText("");
		this.fieldSubareaPerson.setText("");
		this.fieldCursoFormacaoPerson.setText("");
		this.fieldFreqPositivaDiaria.setText("");
		this.fieldFreqNegativaDiaria.setText("");
		this.fieldFreqPositivaSemanal.setText("");
		this.fieldFreqNegativaSemanal.setText("");
		
	}
	
	public void salveEdicaoPerson() {	
		
		Random gerador = new Random();        
		
		Person pessoa = new Person();;

		if ( getBottaoAnterior() == 2){
			pessoa = (Person) this.comboPerson.getSelectedItem();
		}
		
		Learning learningTemp = new Learning();
		
		Calendar dataTemp = Calendar.getInstance();
		
		pessoa.setNameFirst(this.fieldNomeFirstPerson.getText());
		pessoa.setNameLast("Silva");
		pessoa.setEmail(this.fieldEmailPerson.getText());
		pessoa.setPassword("senha");
		dataTemp.set(gerador.nextInt(113)+1900, gerador.nextInt(12), gerador.nextInt(28)+1);
		pessoa.setDateBirth(dataTemp);
		pessoa.setGender(Integer.valueOf(this.fieldSexoPerson.getText()));
		pessoa.setIncome(Float.valueOf(this.fieldRendaPerson.getText()));
		pessoa.setReligion(Integer.valueOf(this.fieldReligiaoPerson.getText()));
		pessoa.setRace(Integer.valueOf(this.fieldCorPerson.getText()));
		pessoa.setDisease(this.fieldDoencaPerson.getText()); 
				
		learningTemp.setEducationalLevel(Integer.valueOf(this.fieldEducacaoNivelPerson.getText()));
		learningTemp.setEstiloDimenOrganizacao(Integer.valueOf(this.fieldEstiloOrganizacionalPerson.getText()));
		learningTemp.setEstiloDimenPercepcao(Integer.valueOf(this.fieldEstiloPercepcaoPerson.getText()));
		learningTemp.setEstiloDimenProcessamento(Integer.valueOf(this.fieldEstiloProcessamentoPerson.getText()));
		learningTemp.setEstiloDimenRetencao(Integer.valueOf(this.fieldEstiloRetencaoPerson.getText()));
		learningTemp.setFormationGrandeArea(0);
		learningTemp.setFormationArea(Integer.valueOf(this.fieldAreaPerson.getText()));
		learningTemp.setFormationSubarea(0);
		learningTemp.setFormationCourse(this.fieldCursoFormacaoPerson.getText());
		
		pessoa.setLearning(learningTemp);
		
		if ( getBottaoAnterior() == 1)
		{
			Frequency  frequency = new Frequency();
			pessoa.setFrequency(frequency);	
			iUbi.createPerson(pessoa);	
			this.comboPerson.addItem(pessoa);
		}
		else if ( getBottaoAnterior() == 2)
		{
//			iUbi.editPerson(pessoa);
		}
		
		setBottaoAnterior(0);								
	}
	
	public void travaEdicaoPerson()
	{
		this.fieldNomeFirstPerson.setEditable(false);
		//this.fieldNomeLastPerson.setEditable(false);
		this.fieldEmailPerson.setEditable(false);
		this.fieldIdadePerson.setEditable(false);
		this.fieldSexoPerson.setEditable(false);
		this.fieldRendaPerson.setEditable(false);
		this.fieldReligiaoPerson.setEditable(false);
		this.fieldCorPerson.setEditable(false);
		this.fieldDoencaPerson.setEditable(false);
		this.fieldEducacaoNivelPerson.setEditable(false);
		this.fieldEstiloOrganizacionalPerson.setEditable(false);
		this.fieldEstiloPercepcaoPerson.setEditable(false);
		this.fieldEstiloProcessamentoPerson.setEditable(false);
		this.fieldEstiloRetencaoPerson.setEditable(false);
		this.fieldGrandeAreaPerson.setEditable(false);
		this.fieldAreaPerson.setEditable(false);
		this.fieldSubareaPerson.setEditable(false);
		this.fieldCursoFormacaoPerson.setEditable(false);
		this.fieldFreqPositivaDiaria.setEditable(false);
		this.fieldFreqNegativaDiaria.setEditable(false);
		this.fieldFreqPositivaSemanal.setEditable(false);
		this.fieldFreqNegativaSemanal.setEditable(false);
		
	}
	
	public void deletePerson()
	{	
		Person pessoaDeletada = (Person) this.comboPerson.getSelectedItem();

//		iUbi.removePerson(pessoaDeletada, contexto);
		
		this.comboPerson.removeItem(pessoaDeletada);
		
	}
	
	public void preparaEdicaoContent(){
		
		this.fieldTituloContent.setEditable(true);
		this.fieldTypeContent.setEditable(true);
		this.fieldSubtypeContent.setEditable(true);
		this.fieldVisibilityContent.setEditable(true);
		this.fieldDescriptionContent.setEditable(true);
		this.fieldAgeContent.setEditable(true);
		this.fieldPersonContent.setEditable(true);
		this.fieldAutorContent.setEditable(true);
		this.fieldGenericLinkContent.setEditable(true);
		this.fieldBytesContent.setEditable(true);
		this.fieldDuracaoContent.setEditable(true);
		this.fieldPercentualAceitacaoContent.setEditable(true);
		this.fieldRateColabPonderContent.setEditable(true);
		this.fieldFreqPositivaDiariaContent.setEditable(true);
		this.fieldFreqNegativaDiariaContent.setEditable(true);
		this.fieldFreqPositivaSemanalContent.setEditable(true);
		this.fieldFreqNegativaSemanalContent.setEditable(true);
		
	}
	
	public void limpaEdicaoContent() {

		this.fieldTituloContent.setText("");
		this.fieldTypeContent.setText("");
		this.fieldSubtypeContent.setText("");
		this.fieldVisibilityContent.setText("");
		this.fieldDescriptionContent.setText("");
		this.fieldAgeContent.setText("");
		this.fieldPersonContent.setText("");
		this.fieldAutorContent.setText("");
		this.fieldGenericLinkContent.setText("");
		this.fieldBytesContent.setText("");
		this.fieldDuracaoContent.setText("");
		this.fieldPercentualAceitacaoContent.setText("");
		this.fieldRateColabPonderContent.setText("");
		this.fieldFreqPositivaDiariaContent.setText("");
		this.fieldFreqNegativaDiariaContent.setText("");
		this.fieldFreqPositivaSemanalContent.setText("");
		this.fieldFreqNegativaSemanalContent.setText("");
		
	}
	
	public void salveEdicaoContent() {	
		
		Content content = new Content();;
		
		if ( getBottaoAnterior() == 2){
			content = (Content) this.comboContent.getSelectedItem();
		}
				
		Calendar dataTemp = Calendar.getInstance();
		
		content.setType(Integer.valueOf(this.fieldTypeContent.getText()));
		content.setSubtype(Integer.valueOf(this.fieldSubtypeContent.getText()));		// Tipo: Texto = 0; Página = 1; imagem = 2; vídeo = 3; Indefinido = 4; 
		content.setVisibility(Integer.valueOf(this.fieldVisibilityContent.getText()));
		content.setTitle(this.fieldTituloContent.getText());
		content.setDescription("Descrição do Conteúdo");		
		content.setDateCreation(dataTemp);		
		content.setUrlOnline(this.fieldGenericLinkContent.getText());
		content.setBytesOnline(Integer.valueOf(this.fieldBytesContent.getText()));
		content.setSecondsOnline(Integer.valueOf(this.fieldDuracaoContent.getText()));
		content.setRateAcceptance(Float.valueOf(this.fieldPercentualAceitacaoContent.getText()));
		content.setRateColabPonder(Float.valueOf(this.fieldRateColabPonderContent.getText()));
		
		if ( getBottaoAnterior() == 1)
		{
			Frequency  frequency = new Frequency();
			content.setFrequency(frequency);
	//		iUbi.createContent(content);
			this.comboContent.addItem(content);
		}
		else if ( getBottaoAnterior() == 2)
		{
		//	iUbi.setContent(content);
		}
		setBottaoAnterior(0);
		
	}
	
	public void travaEdicaoContent()
	{
		this.fieldTituloContent.setEditable(false);
		this.fieldTypeContent.setEditable(false);
		this.fieldSubtypeContent.setEditable(false);
		this.fieldVisibilityContent.setEditable(false);
		this.fieldDescriptionContent.setEditable(false);
		this.fieldAgeContent.setEditable(false);
		this.fieldPersonContent.setEditable(false);
		this.fieldAutorContent.setEditable(false);
		this.fieldGenericLinkContent.setEditable(false);
		this.fieldBytesContent.setEditable(false);
		this.fieldDuracaoContent.setEditable(false);
		this.fieldPercentualAceitacaoContent.setEditable(false);
		this.fieldRateColabPonderContent.setEditable(false);
		this.fieldFreqPositivaDiariaContent.setEditable(false);
		this.fieldFreqNegativaDiariaContent.setEditable(false);
		this.fieldFreqPositivaSemanalContent.setEditable(false);
		this.fieldFreqNegativaSemanalContent.setEditable(false);
	}
	
	public void deleteContent()
	{	
		Content conteudoDeletado = (Content) this.comboContent.getSelectedItem();

//		iUbi.removeContent(conteudoDeletado.getId());
		
		this.comboContent.removeItem(conteudoDeletado);
	}

	public void preparaEdicaoLocation()
	{
		this.fieldNomeLocation.setEditable(true);
		this.fieldIdGoogleLocation.setEditable(true);
		this.fieldLatitudeLocation.setEditable(true);
		this.fieldLongitudeLocation.setEditable(true);
		this.fieldAltitudeLocation.setEditable(true);
		this.fieldPaisLocation.setEditable(true);
		this.fieldEstadoLocation.setEditable(true);
		this.fieldCidadeLocation.setEditable(true);
		this.fieldBairroLocation.setEditable(true);
		this.fieldLogradouroLocation.setEditable(true);
		this.fieldNumeroLocation.setEditable(true);
		this.fieldComplementoLocation.setEditable(true);
		this.fieldCEPLocation.setEditable(true);
		this.fieldRatingLocation.setEditable(true);
		this.fieldFreqPositivaDiariaLocation.setEditable(true);
		this.fieldFreqNegativaDiariaLocation.setEditable(true);
		this.fieldFreqPositivaSemanalLocation.setEditable(true);
		this.fieldFreqNegativaSemanalLocation.setEditable(true);
	}
	
	public void limpaEdicaoLocation() 
	{
		this.fieldNomeLocation.setText("");
		this.fieldIdGoogleLocation.setText("");
		this.fieldLatitudeLocation.setText("");
		this.fieldLongitudeLocation.setText("");
		this.fieldAltitudeLocation.setText("");
		this.fieldPaisLocation.setText("");
		this.fieldEstadoLocation.setText("");
		this.fieldCidadeLocation.setText("");
		this.fieldBairroLocation.setText("");
		this.fieldLogradouroLocation.setText("");
		this.fieldNumeroLocation.setText("");
		this.fieldComplementoLocation.setText("");
		this.fieldCEPLocation.setText("");
		this.fieldRatingLocation.setText("");
		this.fieldFreqPositivaDiariaLocation.setText("");
		this.fieldFreqNegativaDiariaLocation.setText("");
		this.fieldFreqPositivaSemanalLocation.setText("");
		this.fieldFreqNegativaSemanalLocation.setText("");
	}
	
	public void salveEdicaoLocation() 
	{			
		Location local = new Location();

		if ( getBottaoAnterior() == 2){
			local = (Location) this.comboLocation.getSelectedItem();
		}

		local.setIdGoogle(this.fieldIdGoogleLocation.getText());
		local.setTitle(this.fieldNomeLocation.getText());
		local.setCountry(this.fieldPaisLocation.getText());
		local.setState(this.fieldEstadoLocation.getText());
		local.setCity(this.fieldCidadeLocation.getText());
		local.setNeighborhood(this.fieldBairroLocation.getText());
		local.setStreetName(this.fieldLogradouroLocation.getText());
		local.setStreetNumber(Integer.valueOf(this.fieldNumeroLocation.getText()));
		local.setDescription(this.fieldComplementoLocation.getText());
		local.setCEP(this.fieldCEPLocation.getText());	
		local.setRating(Float.valueOf(this.fieldRatingLocation.getText()));
		local.setLatitude(Float.valueOf(this.fieldLatitudeLocation.getText()));
		local.setLongitude(Float.valueOf(this.fieldLongitudeLocation.getText()));
		local.setAltitude(Float.valueOf(this.fieldAltitudeLocation.getText()));		
		
	//	local.setListTags(null);	
		local.setListTypes(null);

		if ( getBottaoAnterior() == 1){
			Frequency  frequency = new Frequency();
			local.setFrequency(frequency);	
//			iUbi.createLocation(local);	
			this.comboLocation.addItem(local);
		}
		else if ( getBottaoAnterior() == 2)
		{
	//		iUbi.setLocation(local);
		}
		setBottaoAnterior(0);
		
	}
	
	public void travaEdicaoLocation(){

		this.fieldNomeLocation.setEditable(false);
		this.fieldIdGoogleLocation.setEditable(false);
		this.fieldLatitudeLocation.setEditable(false);
		this.fieldLongitudeLocation.setEditable(false);
		this.fieldAltitudeLocation.setEditable(false);
		this.fieldPaisLocation.setEditable(false);
		this.fieldEstadoLocation.setEditable(false);
		this.fieldCidadeLocation.setEditable(false);
		this.fieldBairroLocation.setEditable(false);
		this.fieldLogradouroLocation.setEditable(false);
		this.fieldNumeroLocation.setEditable(false);
		this.fieldComplementoLocation.setEditable(false);
		this.fieldCEPLocation.setEditable(false);
		this.fieldRatingLocation.setEditable(false);
		this.fieldFreqPositivaDiariaLocation.setEditable(false);
		this.fieldFreqNegativaDiariaLocation.setEditable(false);
		this.fieldFreqPositivaSemanalLocation.setEditable(false);
		this.fieldFreqNegativaSemanalLocation.setEditable(false);
		
	}
	
	public void deleteLocation()
	{	
		Location localDeletado = (Location) this.comboLocation.getSelectedItem();

//		iUbi.removeLocation(localDeletado.getId());
		
		this.comboLocation.removeItem(localDeletado);
	}

	
	
	
	public JTabbedPane getVerDados() {
		return verDados;
	}
	
	public JButton getGeraSimulacao() {
		return carregaBD;
	}

	public void setGeraSimulacao(JButton geraSimulacao) {
		this.carregaBD = geraSimulacao;
	}

	public JButton getGeraRelacoes() {
		return geraRelacoes;
	}

	public void setGeraRelacoes(JButton geraRelacoes) {
		this.geraRelacoes = geraRelacoes;
	}

	public JButton getGeraBaseRecomendacao() {
		return geraBaseColaboradores;
	}

	public void setGeraBaseRecomendacao(JButton geraBaseRecomendacao) {
		this.geraBaseColaboradores = geraBaseRecomendacao;
	}

	public JButton getGeraRecomendacaoHibrida() {
		return geraRecomendacaoHibrida;
	}

	public void setGeraRecomendacaoHibrida(
			JButton geraRecomendacaoHibrida) {
		this.geraRecomendacaoHibrida = geraRecomendacaoHibrida;
	}

	public JButton getGeraBaseColaboradores() {
		return geraBaseColaboradores;
	}

	public void setGeraBaseColaboradores(JButton geraBaseColaboradores) {
		this.geraBaseColaboradores = geraBaseColaboradores;
	}

	public JButton getGeraBaseColaborativaPonderada() {
		return geraBaseColaborativaPonderada;
	}

	public void setGeraBaseColaborativaPonderada(
			JButton geraBaseColaborativaPonderada) {
		this.geraBaseColaborativaPonderada = geraBaseColaborativaPonderada;
	}

	public JButton getGeraBaseBaseadaConteudo() {
		return geraBaseBaseadaConteudo;
	}

	public void setGeraBaseBaseadaConteudo(JButton geraBaseBaseadaConteudo) {
		this.geraBaseBaseadaConteudo = geraBaseBaseadaConteudo;
	}

	public JPanel getDadosContent() {
		return dadosContent;
	}

	public void setDadosContent(JPanel dadosContent) {
		this.dadosContent = dadosContent;
	}

	public JPanel getDadosLocation() {
		return dadosLocation;
	}

	public void setDadosLocation(JPanel dadosLocation) {
		this.dadosLocation = dadosLocation;
	}

	public JLabel getEmail() {
		return labelEmailPerson;
	}

	public void setEmail(JLabel email) {
		this.labelEmailPerson = email;
	}

	public JLabel getIdade() {
		return labelIdadePerson;
	}

	public void setIdade(JLabel idade) {
		this.labelIdadePerson = idade;
	}

	public JLabel getSexo() {
		return labelSexoPerson;
	}

	public void setSexo(JLabel sexo) {
		this.labelSexoPerson = sexo;
	}

	public JLabel getRenda() {
		return labelRendaPerson;
	}

	public void setRenda(JLabel renda) {
		this.labelRendaPerson = renda;
	}

	public JLabel getReligiao() {
		return labelReligiaoPerson;
	}

	public void setReligiao(JLabel religiao) {
		this.labelReligiaoPerson = religiao;
	}

	public JLabel getCor() {
		return labelCorPerson;
	}

	public void setCor(JLabel cor) {
		this.labelCorPerson = cor;
	}

	public JLabel getProfissao() {
		return labelDoencaPerson;
	}

	public void setDoenca(JLabel doenca) {
		this.labelDoencaPerson = doenca;
	}

	public JLabel getEducacaoNivel() {
		return labelEducacaoNivelPerson;
	}

	public void setEducacaoNivel(JLabel educacaoNivel) {
		this.labelEducacaoNivelPerson = educacaoNivel;
	}

	public JLabel getEstiloOrganizacional() {
		return labelEstiloOrganizacionalPerson;
	}

	public void setEstiloOrganizacional(JLabel estiloOrganizacional) {
		this.labelEstiloOrganizacionalPerson = estiloOrganizacional;
	}

	public JLabel getEstiloPercepcao() {
		return labelEstiloPercepcaoPerson;
	}

	public void setEstiloPercepcao(JLabel estiloPercepcao) {
		this.labelEstiloPercepcaoPerson = estiloPercepcao;
	}

	public JLabel getEstiloProcessamento() {
		return labelEstiloProcessamentoPerson;
	}

	public void setEstiloProcessamento(JLabel estiloProcessamento) {
		this.labelEstiloProcessamentoPerson = estiloProcessamento;
	}

	public JLabel getEstiloRetencao() {
		return labelEstiloRetencaoPerson;
	}

	public void setEstiloRetencao(JLabel estiloRetencao) {
		this.labelEstiloRetencaoPerson = estiloRetencao;
	}

	public JLabel getGrandeArea() {
		return labelGrandeAreaPerson;
	}

	public void setGrandeArea(JLabel grandeArea) {
		this.labelGrandeAreaPerson = grandeArea;
	}

	public JLabel getArea() {
		return labelAreaPerson;
	}

	public void setArea(JLabel area) {
		this.labelAreaPerson = area;
	}

	public JLabel getSubarea() {
		return labellSubareaPerson;
	}

	public void setSubarea(JLabel subarea) {
		this.labellSubareaPerson = subarea;
	}

	public JLabel getCursoFormacao() {
		return labelCursoFormacaoPerson;
	}

	public void setCursoFormacao(JLabel cursoFormacao) {
		this.labelCursoFormacaoPerson = cursoFormacao;
	}

	public JComboBox<Person> getComboAmigosPerson() {
		return comboMeusAmigosPerson;
	}

	public void setComboAmigosPerson(JComboBox<Person> comboAmigosPerson) {
		this.comboMeusAmigosPerson = comboAmigosPerson;
	}

	public JComboBox<Content> getComboConteudosPerson() {
		return comboMeusConteudosPerson;
	}

	public void setComboConteudosPerson(JComboBox<Content> comboConteudosPerson) {
		this.comboMeusConteudosPerson = comboConteudosPerson;
	}

	public JComboBox<Location> getComboLocaisPerson() {
		return comboMeusLocaisPerson;
	}

	public void setComboLocaisPerson(JComboBox<Location> comboLocaisPerson) {
		this.comboMeusLocaisPerson = comboLocaisPerson;
	}

	public JLabel getFreqPositivaDiaria() {
		return labelFreqPositivaDiaria;
	}

	public void setFreqPositivaDiaria(JLabel freqPositivaDiaria) {
		this.labelFreqPositivaDiaria = freqPositivaDiaria;
	}

	public JLabel getFreqNegativaDiaria() {
		return labelFreqNegativaDiaria;
	}

	public void setFreqNegativaDiaria(JLabel freqNegativaDiaria) {
		this.labelFreqNegativaDiaria = freqNegativaDiaria;
	}

	public JLabel getFreqPositivaSemanal() {
		return labelFreqPositivaSemanal;
	}

	public void setFreqPositivaSemanal(JLabel freqPositivaSemanal) {
		this.labelFreqPositivaSemanal = freqPositivaSemanal;
	}

	public JLabel getFreqNegativaSemanal() {
		return labelFreqNegativaSemanal;
	}

	public void setFreqNegativaSemanal(JLabel freqNegativaSemanal) {
		this.labelFreqNegativaSemanal = freqNegativaSemanal;
	}

	public JPanel getDadosEntrada() {
		return dadosEntrada;
	}

	public void setDadosEntrada(JPanel dadosEntrada) {
		this.dadosEntrada = dadosEntrada;
	}

	public JPanel getDadosContexto() {
		return dadosContexto;
	}

	public void setDadosContexto(JPanel dadosContexto) {
		this.dadosContexto = dadosContexto;
	}

	public JPanel getRecomendacaoHibrida() {
		return recomendacaoHibrida;
	}

	public void setRecomendacaoHibrida(JPanel recomendacaoHibrida) {
		this.recomendacaoHibrida = recomendacaoHibrida;
	}

	public JPanel getLog() {
		return log;
	}

	public void setLog(JPanel log) {
		this.log = log;
	}

	public JPanel getMeusPersons() {
		return jpanelMeusPersons;
	}

	public void setMeusPersons(JPanel meusPersons) {
		this.jpanelMeusPersons = meusPersons;
	}

	public JPanel getMeusContents() {
		return jpanelMeusContents;
	}

	public void setMeusContents(JPanel meusContents) {
		this.jpanelMeusContents = meusContents;
	}

	public JComboBox<Person> getComboColaboradores() {
		return comboColaboradores;
	}

	public void setComboColaboradores(JComboBox<Person> comboColaboradores) {
		this.comboColaboradores = comboColaboradores;
	}

	public JComboBox<Content> getComboColaborativa() {
		return comboColaborativa;
	}

	public void setComboColaborativa(JComboBox<Content> comboColaborativa) {
		this.comboColaborativa = comboColaborativa;
	}

	public JComboBox<Content> getComboBaseadaConteudo() {
		return comboBaseadaConteudo;
	}

	public void setComboBaseadaConteudo(JComboBox<Content> comboBaseadaConteudo) {
		this.comboBaseadaConteudo = comboBaseadaConteudo;
	}

	public JPanel getPainelColaboradores() {
		return jpanelColaboradores;
	}

	public void setPainelcoColaboradores(JPanel painelColaboradores) {
		this.jpanelColaboradores = painelColaboradores;
	}

	public JPanel getPainelColaborativa() {
		return jpanelColaborativa;
	}

	public void setPainelColaborativa(JPanel painelColaborativa) {
		this.jpanelColaborativa = painelColaborativa;
	}

	public JPanel getPainelBaseadaConteudo() {
		return jpanelBaseadaConteudo;
	}

	public void setPainelBaseadaConteudo(JPanel painelBaseadaConteudo) {
		this.jpanelBaseadaConteudo = painelBaseadaConteudo;
	}

	public ArrayList<RelatePersonContent> getResultadoRecomendacaoHibrida() {
		return resultadoRecomendacaoHibrida;
	}

	public void setResultadoRecomendacaoHibrida(ArrayList<RelatePersonContent> resultadoRecomendacaoHibrida) {
		this.resultadoRecomendacaoHibrida = resultadoRecomendacaoHibrida;
	}

	public JComboBox<Content> getComboResultRecomendacaoHibrida() {
		return comboResultRecomendacaoHibrida;
	}

	public void setComboResultRecomendacaoHibrida(
			JComboBox<Content> comboResultRecomendacaoHibrida) {
		this.comboResultRecomendacaoHibrida = comboResultRecomendacaoHibrida;
	}

	public JLabel getRecomendadoPerson() {
		return recomendadoPerson;
	}

	public void setRecomendadoPerson(JLabel recomendadoPerson) {
		this.recomendadoPerson = recomendadoPerson;
	}

	public JLabel getTaxaAdequacao() {
		return taxaHibrida;
	}

	public void setTaxaAdequacao(JLabel taxaAdequacao) {
		this.taxaHibrida = taxaAdequacao;
	}

	public JLabel getPesoColaborativo() {
		return pesoColaborativo;
	}

	public void setPesoColaborativo(JLabel pesoColaborativo) {
		this.pesoColaborativo = pesoColaborativo;
	}

	public JLabel getPesoBaseadoConteudo() {
		return pesoBaseadoConteudo;
	}

	public void setPesoBaseadoConteudo(JLabel pesoBaseadoConteudo) {
		this.pesoBaseadoConteudo = pesoBaseadoConteudo;
	}

	public JLabel getPesoUbiquo() {
		return pesoUbiquo;
	}

	public void setPesoUbiquo(JLabel pesoUbiquo) {
		this.pesoUbiquo = pesoUbiquo;
	}

	public JTextField getData() {
		return dataContexto;
	}

	public void setData(JTextField data) {
		this.dataContexto = data;
	}

	public JTextField getDiaSemana() {
		return diaContexto;
	}

	public void setDiaSemana(JTextField diaSemana) {
		this.diaContexto = diaSemana;
	}

	public JTextField getHora() {
		return horaContexto;
	}

	public void setHora(JTextField hora) {
		this.horaContexto = hora;
	}

	public JTextField getBateria() {
		return bateriaContexto;
	}

	public void setBateria(JTextField bateria) {
		this.bateriaContexto = bateria;
	}

	public JTextField getVelocidadeConexao() {
		return velocidadeConexaoContexto;
	}

	public void setVelocidadeConexao(JTextField velocidadeConexao) {
		this.velocidadeConexaoContexto = velocidadeConexao;
	}

	public JTextField getVelocidadePessoa() {
		return velocidadePessoaContexto;
	}

	public void setVelocidadePessoa(JTextField velocidadePessoa) {
		this.velocidadePessoaContexto = velocidadePessoa;
	}

	public JTextField getLocal() {
		return localContexto;
	}

	public void setLocal(JTextField local) {
		this.localContexto = local;
	}

	public JTextField getNumeroPessoas() {
		return numeroPessoas;
	}

	public void setNumeroPessoas(JTextField numeroPessoas) {
		this.numeroPessoas = numeroPessoas;
	}

	public JTextField getNumeroConteudos() {
		return numeroConteudos;
	}

	public void setNumeroConteudos(JTextField numeroConteudos) {
		this.numeroConteudos = numeroConteudos;
	}

	public JTextField getNumeroLocais() {
		return numeroLocais;
	}

	public void setNumeroLocais(JTextField numeroLocais) {
		this.numeroLocais = numeroLocais;
	}

	public JTextField getNumeroRelacoes() {
		return numeroRelacoes;
	}

	public void setNumeroRelacoes(JTextField numeroRelacoes) {
		this.numeroRelacoes = numeroRelacoes;
	}

	public JTextField getNumeroConteudosPorPessoa() {
		return numeroConteudosPorPessoa;
	}

	public void setNumeroConteudosPorPessoa(JTextField numeroConteudosPorPessoa) {
		this.numeroConteudosPorPessoa = numeroConteudosPorPessoa;
	}

	public JTextField getNumeroLocaisPorPessoa() {
		return numeroLocaisPorPessoa;
	}

	public void setNumeroLocaisPorPessoa(JTextField numeroLocaisPorPessoa) {
		this.numeroLocaisPorPessoa = numeroLocaisPorPessoa;
	}

	public JTextField getNumeroLocaisPorConteudo() {
		return numeroLocaisPorConteudo;
	}

	public void setNumeroLocaisPorConteudo(JTextField numeroLocaisPorConteudo) {
		this.numeroLocaisPorConteudo = numeroLocaisPorConteudo;
	}

	public JTextField getTamanhoListaColaboradores() {
		return tamanhoListaColaboradores;
	}

	public void setTamanhoListaColaboradores(JTextField tamanhoListaColaboradores) {
		this.tamanhoListaColaboradores = tamanhoListaColaboradores;
	}

	public JTextField getTamanhoListaColaborativa() {
		return tamanhoListaColaborativa;
	}

	public void setTamanhoListaColaborativa(JTextField tamanhoListaColaborativa) {
		this.tamanhoListaColaborativa = tamanhoListaColaborativa;
	}

	public JTextField getTamanhoListaBaseadaConteudo() {
		return tamanhoListaBaseadaConteudo;
	}

	public void setTamanhoListaBaseadaConteudo(
			JTextField tamanhoListaBaseadaConteudo) {
		this.tamanhoListaBaseadaConteudo = tamanhoListaBaseadaConteudo;
	}

	public JLabel getLabelTituloContent() {
		return labelTituloContent;
	}

	public void setLabelTituloContent(JLabel labelTituloContent) {
		this.labelTituloContent = labelTituloContent;
	}

	public JTextField getFieldTituloContent() {
		return fieldTituloContent;
	}

	public void setFieldTituloContent(JTextField fieldTituloContent) {
		this.fieldTituloContent = fieldTituloContent;
	}

	public JLabel getLabelTypeContent() {
		return labelTypeContent;
	}

	public void setLabelTypeContent(JLabel labelTypeContent) {
		this.labelTypeContent = labelTypeContent;
	}

	public JLabel getLabelSubtypeContent() {
		return labelSubtypeContent;
	}

	public void setLabelSubtypeContent(JLabel labelSubtypeContent) {
		this.labelSubtypeContent = labelSubtypeContent;
	}

	public JTextField getFieldSubtypeContent() {
		return fieldSubtypeContent;
	}

	public void setFieldSubtypeContent(JTextField fieldSubtypeContent) {
		this.fieldSubtypeContent = fieldSubtypeContent;
	}

	public JLabel getLabelVisibilityContent() {
		return labelVisibilityContent;
	}

	public void setLabelVisibilityContent(JLabel labelVisibilityContent) {
		this.labelVisibilityContent = labelVisibilityContent;
	}

	public JTextField getFieldVisibilityContent() {
		return fieldVisibilityContent;
	}

	public void setFieldVisibilityContent(JTextField fieldVisibilityContent) {
		this.fieldVisibilityContent = fieldVisibilityContent;
	}

	public JLabel getLabelDescriptionContent() {
		return labelDescriptionContent;
	}

	public void setLabelDescriptionContent(JLabel labelDescriptionContent) {
		this.labelDescriptionContent = labelDescriptionContent;
	}

	public JTextField getFieldDescriptionContent() {
		return fieldDescriptionContent;
	}

	public void setFieldDescriptionContent(JTextField fieldDescriptionContent) {
		this.fieldDescriptionContent = fieldDescriptionContent;
	}

	public JLabel getLabelAgeContent() {
		return labelAgeContent;
	}

	public void setLabelAgeContent(JLabel labelAgeContent) {
		this.labelAgeContent = labelAgeContent;
	}

	public JTextField getFieldAgeContent() {
		return fieldAgeContent;
	}

	public void setFieldAgeContent(JTextField fieldAgeContent) {
		this.fieldAgeContent = fieldAgeContent;
	}

	public JTextField getFieldDuracaoContent() {
		return fieldDuracaoContent;
	}

	public void setFieldDuracaoContent(JTextField fieldDuracaoContent) {
		this.fieldDuracaoContent = fieldDuracaoContent;
	}

	public JTextField getFieldBytesContent() {
		return fieldBytesContent;
	}

	public void setFieldBytesContent(JTextField fieldBytesContent) {
		this.fieldBytesContent = fieldBytesContent;
	}

	public JLabel getLabelBytesContent() {
		return labelBytesContent;
	}

	public void setLabelBytesContent(JLabel labelBytesContent) {
		this.labelBytesContent = labelBytesContent;
	}

	public JLabel getLabelDuracaoContent() {
		return labelDuracaoContent;
	}

	public void setLabelDuracaoContent(JLabel labelDuracaoContent) {
		this.labelDuracaoContent = labelDuracaoContent;
	}

	public JLabel getLabelPersonContent() {
		return labelPersonContent;
	}

	public void setLabelPersonContent(JLabel labelPersonContent) {
		this.labelPersonContent = labelPersonContent;
	}

	public JTextField getFieldPersonContent() {
		return fieldPersonContent;
	}

	public void setFieldPersonContent(JTextField fieldPersonContent) {
		this.fieldPersonContent = fieldPersonContent;
	}

	public JLabel getLabelAutorContent() {
		return labelAutorContent;
	}

	public void setLabelAutorContent(JLabel labelAutorContent) {
		this.labelAutorContent = labelAutorContent;
	}

	public JTextField getFieldAutorContent() {
		return fieldAutorContent;
	}

	public void setFieldAutorContent(JTextField fieldAutorContent) {
		this.fieldAutorContent = fieldAutorContent;
	}

	public JLabel getLabelGenericLinkContent() {
		return labelGenericLinkContent;
	}

	public void setLabelGenericLinkContent(JLabel labelGenericLinkContent) {
		this.labelGenericLinkContent = labelGenericLinkContent;
	}

	public JTextField getFieldGenericLinkContent() {
		return fieldGenericLinkContent;
	}

	public void setFieldGenericLinkContent(JTextField fieldGenericLinkContent) {
		this.fieldGenericLinkContent = fieldGenericLinkContent;
	}

	public JComboBox<Location> getComboMeusLocaisContent() {
		return comboMeusLocaisContent;
	}

	public void setComboMeusLocaisContent(JComboBox<Location> comboMeusLocaisContent) {
		this.comboMeusLocaisContent = comboMeusLocaisContent;
	}

	public JLabel getLabelFreqPositivaSemanalContent() {
		return labelFreqPositivaSemanalContent;
	}

	public void setLabelFreqPositivaSemanalContent(JLabel labelFreqPositivaSemanalContent) {
		this.labelFreqPositivaSemanalContent = labelFreqPositivaSemanalContent;
	}

	public JTextField getFieldFreqPositivaDiariaContent() {
		return fieldFreqPositivaDiariaContent;
	}

	public void setFieldFreqPositivaDiariaContent(JTextField fieldFreqPositivaDiariaContent) {
		this.fieldFreqPositivaDiariaContent = fieldFreqPositivaDiariaContent;
	}

	public JLabel getLabelFreqPositivaDiariaContent() {
		return labelFreqPositivaDiariaContent;
	}

	public void setLabelFreqPositivaDiariaContent(JLabel labelFreqPositivaDiariaContent) {
		this.labelFreqPositivaDiariaContent = labelFreqPositivaDiariaContent;
	}

	public JTextField getFieldFreqNegativaSemanalContent() {
		return fieldFreqNegativaSemanalContent;
	}

	public void setFieldFreqNegativaSemanalContent(JTextField fieldFreqNegativaSemanalContent) {
		this.fieldFreqNegativaSemanalContent = fieldFreqNegativaSemanalContent;
	}

	public JComboBox<String> getComboMinhasTagsContent() {
		return comboMinhasTagsContent;
	}

	public void setComboMinhasTagsContent(JComboBox<String> comboMinhasTagsContent) {
		this.comboMinhasTagsContent = comboMinhasTagsContent;
	}

	public JTextField getFieldPercentualAceitacaoContent() {
		return fieldPercentualAceitacaoContent;
	}

	public void setFieldPercentualAceitacaoContent(JTextField fieldPercentualAceitacaoContent) {
		this.fieldPercentualAceitacaoContent = fieldPercentualAceitacaoContent;
	}

	public JLabel getLabelPercentualAceitacaoContent() {
		return labelPercentualAceitacaoContent;
	}

	public void setLabelPercentualAceitacaoContent(JLabel labelPercentualAceitacaoContent) {
		this.labelPercentualAceitacaoContent = labelPercentualAceitacaoContent;
	}

	public JLabel getLabelRateColabPonderContent() {
		return labelRateColabPonderContent;
	}

	public void setLabelRateColabPonderContent(JLabel labelRateColabPonderContent) {
		this.labelRateColabPonderContent = labelRateColabPonderContent;
	}

	public JTextField getFieldRateColabPonderContent() {
		return fieldRateColabPonderContent;
	}

	public void setFieldRateColabPonderContent(JTextField fieldRateColabPonderContent) {
		this.fieldRateColabPonderContent = fieldRateColabPonderContent;
	}

	public JLabel getLabelFreqNegativaDiariaContent() {
		return labelFreqNegativaDiariaContent;
	}

	public void setLabelFreqNegativaDiariaContent(JLabel labelFreqNegativaDiariaContent) {
		this.labelFreqNegativaDiariaContent = labelFreqNegativaDiariaContent;
	}

	public JTextField getFieldFreqNegativaDiariaContent() {
		return fieldFreqNegativaDiariaContent;
	}

	public void setFieldFreqNegativaDiariaContent(JTextField fieldFreqNegativaDiariaContent) {
		this.fieldFreqNegativaDiariaContent = fieldFreqNegativaDiariaContent;
	}

	public JTextField getFieldFreqPositivaSemanalContent() {
		return fieldFreqPositivaSemanalContent;
	}

	public void setFieldFreqPositivaSemanalContent(JTextField fieldFreqPositivaSemanalContent) {
		this.fieldFreqPositivaSemanalContent = fieldFreqPositivaSemanalContent;
	}

	public JLabel getLabelFreqNegativaSemanalContent() {
		return labelFreqNegativaSemanalContent;
	}

	public void setLabelFreqNegativaSemanalContent(JLabel labelFreqNegativaSemanalContent) {
		this.labelFreqNegativaSemanalContent = labelFreqNegativaSemanalContent;
	}

	public JPanel getPanelMinhasTagsContent() {
		return panelMinhasTagsContent;
	}

	public void setPanelMinhasTagsContent(JPanel panelMinhasTagsContent) {
		this.panelMinhasTagsContent = panelMinhasTagsContent;
	}

	public JPanel getPanelMeusLocationsContent() {
		return panelMeusLocationsContent;
	}

	public void setPanelMeusLocationsContent(JPanel panelMeusLocationsContent) {
		this.panelMeusLocationsContent = panelMeusLocationsContent;
	}
	public int getBottaoAnterior() {
		return bottaoAnterior;
	}
	public void setBottaoAnterior(int valor) {
		this.bottaoAnterior = valor;
	}
	
	
}
