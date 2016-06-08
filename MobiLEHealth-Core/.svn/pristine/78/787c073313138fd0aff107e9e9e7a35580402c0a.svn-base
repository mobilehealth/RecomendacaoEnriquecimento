package mobilehealth.client_desktop.simulation.data;

import java.io.FileNotFoundException;
import java.io.ObjectInputStream.GetField;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import mobilehealth.prc.controller.inputdata.SecurityUbi;
import mobilehealth.prc.controller.ManagerServer;
import mobilehealth.core.domain.Content;

public class GenerateContent_2 {

	private final int LIMITE = 120;
	
	private static ManagerServer managerServer = ManagerServer.getInstance();
	
	/***
	 * @param args
	 */
	public static void main(String[] args) {
		(new GenerateContent_2()).run();
				
		List<TitleDescriptionUrl> titleDescriptionUrl = new ArrayList<TitleDescriptionUrl>();
		
		titleDescriptionUrl.add(new TitleDescriptionUrl(94, 3, "O que é Esclerose Lateral Amiotrófica?","Informações sobre Esclerose Lateral Amiotrófica","http://www.minhavida.com.br/saude/temas/esclerose-lateral-amiotrofica#top1"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(95, 3, "Associação Brasileira de ELA","Ações da Associação Brasileira de ELA","http://www.abrela.org.br/"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(96, 3, "Esclerose Lateral Amiotrófica - ELA","Dodenças e Sintomas de ELA","http://drauziovarella.com.br/letras/e/esclerose-lateral-amiotrofica-ela/"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(97, 3, "Esclerose Lateral Amiotrófica","Tudo sobre ELA","https://www.tudosobreela.com.br/home/index.asp"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(98, 3, "Informações da Síndrome ELA","O que é Esclerose Lateral Amiotrófica?","http://www.abcdasaude.com.br/artigo.php?184"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(99, 3, "ELA e Células-Tronco","Tratamento de ELA com Células-Tronco","http://www.cordcell.com.br/noticias/primeiro-caso-sucesso-esclerose-lateral-amiotrofica-com-celulas-tronco"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(100, 3, "Associação Regional Esclerose Lateral Amiotrófica - ARELA","Informações sobre a ARELA","http://www.arela-rs.org.br/"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(101, 3, "Manual Interativo Brasileiro de ELA","Manual Interativo  de Orientações sobre ELA","http://www.todosporela.org.br/mibrela.html"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(102, 3, "Esclerose Lateral Amiotrófica","Entendendo a evolução da doença ELA","http://globotv.globo.com/rede-globo/bem-estar/v/entenda-como-e-a-evolucao-da-esclerose-lateral-amiotrofica/2681124/"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(103, 3, "Estudos sobre ELA","Informações de Centros de Pesquisa que estudam sobre ELA","http://genoma.ib.usp.br/?page_id=865"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(104, 3, "Artigos sobre ELA","Pesquisas e publicações sobre a doença ELA","http://www.scielo.org/cgi-bin/wxis.exe/applications/scielo-org/iah/?IsisScript=iah/iah.xis&base=article%5Edart.org&nextAction=lnk&lang=p&indexSearch=&exprSearch=ESCLEROSE%20LATERAL%20AMIOTROFICA"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(105, 3, "Conhecendo a doença ELA","Tudo o que envolve a doença ELA","http://saude.ig.com.br/minhasaude/enciclopedia/esclerose-lateral-amiotrofica/ref1238131523915.html"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(106, 3, "ELA - Sintomas e Tratamentos","Como Tratar a doença ELA","http://km-stressnet.blogspot.com.br/2008/01/esclerose-lateral-amiotrfica-sintomas-e.html"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(107, 3, "Diagnóstico de ELA","Reportagem de Diagnóstico de ELA","http://www.istoe.com.br/reportagens/81748_O+DRAMATICO+DIAGNOSTICO+DA+ELA"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(108, 3, "Dissertação sobre Esclerose Lateral Amiotrófica","Esclerose lateral amiotrófica - Revisão bibliográfica da patofisiologia","http://www.fcsaude.ubi.pt/thesis/upload/0/1208/a21636_4978pdf.pdf"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(109, 3, "Blog sobre Esclerose Lateral Amiotrófica","Informações sobre ELA","http://rafaelfontenelle.blogspot.com.br/2009/09/esclerose-lateral-amiotrofica.html"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(110, 3, "Tratamento para Portadores de Esclerose Lateral Amiotrófica","Marca-passo para portadores de ELA","http://www.pacemakerusers.com/index.php/category/news/item/181-marca-passo-para-esclerose-lateral-amiotrofica"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(111, 3, "Reportagem sobre Esclerose Lateral Amiotrófica","Reportagem - Alô Doutor(Parte 1): esclerose lateral amiotrófica","http://www.alterosa.com.br/app/divinopolis/videos/2014/02/17/interna-videos-dv,3264/alo-doutor-parte-1-esclerose-lateral-amiotrofica.shtml#.U0VQVlemXJs"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(112, 3, "Artigo de Revista sobre Esclerose Lateral Amiotrófica","Artigo - Avaliação do tratamento homeopático na esclerose lateral amiotrófica","http://www.aph.org.br/revista/index.php/aph/article/viewFile/109/177"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(113, 3, "Revista Informativa sobre ELA","Campanha de informações sobre ELA","http://revistacorpore.com.br/noticias/medicina//campanha-para-divulgar-informacoes-sobre-a-esclerose-lateral-amiotrofica-"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(114, 3, "Tratamento para portadores de ELA","Procedimento Inédito para trata portadores de ELA","http://jcrs.uol.com.br/site/noticia.php?codn=135519"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(115, 3, "Blog sobre Esclerose Lateral Amiotrófica","Blog informativo sobre ELA","http://drcarlosrey.blogspot.com.br/2012/11/esclerose-lateral-amiotrofica-ela.html"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(116, 3, "Artigo sobre ELA","Sintomatologia e Tratamento Fisioterapêutico na ELA","http://www.pergamum.univale.br/pergamum/tcc/Sintomatologiaetratamentofisioterapeuticonaescleroselateralamiotroficarevisaobibliografica.pdf"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(117, 3, "Escritor Portador de ELA","Portador de Esclerose Lateral Amiotrófica lança livro","http://www.radiocoracao.org/multimidia/videos/portador-de-esclerose-lateral-amiotrofica-lanca-livro"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(118, 3, "Artigo de Revisão - Reabilitação na ELA ","Reabilitação na Esclerose Lateral Amiotrofica: revisão da literatura","http://www.actafisiatrica.org.br/detalhe_artigo.asp?id=141"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(119, 3, "Cientistas identificam novo fator genético para esclerose lateral amiotrófica","Pesquisa científica sobre ELA","http://www.estadao.com.br/noticias/vidae,cientistas-identificam-novo-fator-genetico-para-esclerose-lateral-amiotrofica,600951,0.htm"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(120, 3, "Clínica Homeofisio em ELA","Informações para portadores de ELA","http://www.homefisio.com.br/t_esclerose_lateral.html"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(121, 3, "1º Encontro sobre Esclerose Lateral Amiotrófica","1º Encontro da Zona da Mata sobre Esclerose Lateral Amiotrófica","http://www.ufjf.br/secom/2011/06/30/hu-realiza-1%C2%BA-encontro-da-zona-da-mata-sobre-esclerose-lateral-amiotrofica/"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(122, 3, "Simpósio Internacional em Esclerose Lateral Amiotrófica","Publicação de Artigo em Simpósio Internacional em Esclerose Lateral Amiotrófica","http://api.ning.com/files/BaXT*qq6YEZIhKXLacLdFB7NNYSW1A0SHje9T9MbkuIRCAnnknyggkzWSqJ5EwiMo7kaaat99fjFO*TbGFMKOeezIOd07NAZ/elaresumo.pdf"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(123, 3, "I Congresso Iberoamericano de Doenças Raras ","Estudos apresentados no I Congresso Iberoamericano de Doenças Raras","http://compauta.com.br/4109/destaque/4109/"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(124, 3, "Blog sobre cura em Esclerose Múltipla de Órgãos","Testemunho sobre cura em Esclerose Múltipla de Órgãos","http://prleandroalmeida.wordpress.com/2009/06/17/testemunho-de-cura-esclerose-multipla-dos-orgaos/"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(125, 3, "Falando sobre ELA","Atuação Fonoaudiológica na Esclerose Lateral Amiotrófica","http://falandosobreela.blogspot.com.br/2012/10/atuacao-fonoaudiologica-na-esclerose.html"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(126, 3, "Informações nutricionais em pacientes com ELA","Quais são as alterações nutricionais em pacientes com esclerose lateral amiotrófica (ELA)?","http://www.nutritotal.com.br/perguntas/?acao=bu&id=676&categoria=12target=_blank"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(127, 3, "Fatos e Sintomas em ELA","Esclerose Lateral Amiotrófica: Fatos e sintomas da doença de Lou Gehrig - See more at: http://www.ciencia-online.net/2013/09/esclerose-lateral-amiotrofica.html#sthash.ADDb7kls.dpuf","http://www.ciencia-online.net/2013/09/esclerose-lateral-amiotrofica.html"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(128, 3, "Artigo Científico sobre uso de Tecnologias em ELA","Uso da Tecnologia para pacientes com esclerose lateral amiotrófica","http://www.revistaneurociencias.com.br/edicoes/2006/RN%2014%20SUPLEMENTO/Pages%20from%20RN%2014%20SUPLEMENTO-10.pdf"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(129, 3, "Site - todos por ELA","Site sobre Esclerose Lateral Amiotrófica (ELA)","http://www.vidamaislivre.com.br/noticias/noticia.php?id=679&/site_sobre_esclerose_lateral_amiotrofica_ela_e_lancado_"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(130, 3, "Projeto CLIC: destinado à pessoa com ELA","Projeto CLIC: destinado à pessoa com Esclerose Lateral Amiotrófica (ELA)","http://www.deficienteciente.com.br/2011/11/projeto-clic-destinado-a-pessoa-com-esclerose-lateral-amiotrofica-ela.html"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(131, 3, "Lei & Ordem - Software aplicado ao ELA","Software permite a advogada com Esclerose Lateral Amiotrófica escrever livro","http://www.leieordem.com.br/software-permite-a-advogada-com-esclerose-lateral-amiotrofica-escrever-livro.html"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(132, 3, "Artigo científico sobre ELA","Uso de acionadores na Esclerose Lateral Amiotrófica","http://www.ipg.org.br/ipg/project/ipg/public/uploads/2013/09/03/5225d18b48c96uso_de_acionadores_na_ela.pdf"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(133, 3, "Pesquisas sobre a cura em ELA","Pesquisadores brasileiros buscam a cura da Esclerose Lateral Amiotrófica (ELA) ","http://cienciaetec.wordpress.com/2013/04/18/pesquisadores-brasileiros-buscam-a-cura-da-esclerose-lateral-amiotrofica-ela/comment-page-1/"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(134, 3, "Reportagem sobre ELA","Esclerose lateral amiotrófica: o encontro dos cientistas e pacientes","http://veja.abril.com.br/blog/genetica/sem-categoria/esclerose-lateral-amiotrofica-o-encontro-dos-cientistas-e-pacientes/"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(135, 3, "Pesquisa científica sobre Esclerose Lateral Amiotrófica","Artigo - Comunicação alternativa em caso de esclerose lateral amiotrófica (ELA): uma experiencia educacional de mediação para a humanização","http://periodicos.uem.br/ojs/index.php/ActaSciEduc/article/view/14505"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(136, 3, "Informações sobre Esclerose Múltipla","Esclerose múltipla: obesidade e contracetivos hormonais podem aumentar risco","http://www.portalenf.com/2014/03/esclerose-multipla-obesidade-e-contracetivos-hormonais-podem-aumentar-risco/"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(137, 3, "Blog Academia da Dieta em ELA","Terapia Nutricional na Esclerose Lateral Amiotrófica ","http://academiadadieta.blogspot.com.br/2012/08/terapia-nutricional-na-esclerose.html"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(138, 3, "Fisioterapia em Esclerose Lateral Amiotrófica","Guia do Fisioterapeuta em Esclerose Lateral Amiotrófica","http://fisioterapiahumberto.blogspot.com.br/2009/04/esclerose-lateral-amiotrofica.html"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(139, 3, "Artigo sobre ESCLEROSE LATERAL AMIOTRÓFICA","Artigo: ESCLEROSE LATERAL AMIOTRÓFICA (E.L.A.)","http://gerasaude.blogspot.com.br/2011/09/artigo-esclerose-lateral-amiotrofica.html"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(140, 3, "Estudo Experimental em Esclerose Lateral Amiorófica - ELA","ESCLEROSE LATERAL AMIOTRÓFICA (ELA) Primeiro estudo experimental com células tronco será coordenado por pesquisadora do HCUSP de Ribeirão Preto.","http://jmarcosrs.wordpress.com/2012/03/12/esclerose-lateral-amiotrofica-ela-primeiro-estudo-experimental-com-celulas-tronco-sera-coordenado-por-pesquisadora-do-hcusp-de-ribeirao-preto/"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(141, 3, "Dissertação de Mestrado","Necessidades dos doentes portadores de esclerose lateral amiotrófica e seu cuidador principal : sua influência na qualidade de vida","http://repositorio.ul.pt/handle/10451/5523"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(142, 3, "Tecnologia faz paciente voltar a se comunicar","Pesquisas revelam que uma tecnologia desenvolvida fez paciente voltar a se comunicar","http://www.correiodeuberlandia.com.br/cidade-e-regiao/tecnologia-faz-paciente-voltar-a-se-comunicar/"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(143, 3, "Tecnologia a favor das pessoas portadoras de necessidades especiais","A revolução digital também promove a inclusão de portadores de deficiências no mundo tecnológico.","http://www,(tecmundo.com.br/software/2789-tecnologia-a-favor-das-pessoas-portadoras-de-necessidades-especiais.htm"));

		titleDescriptionUrl.add(new TitleDescriptionUrl(1, 2, "Site de auto-ajuda para doentes de Diabetes","Diabetes Mellitus","http://www.abcdasaude.com.br/artigo.php?127"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(2, 2, "Doença metabólica crônica caracterizada por hiperglicemia","Gestação e Diabetes","http://www.abcdasaude.com.br/artigo.php?209"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(3, 2, "Doença metabólica crônica caracterizada por hiperglicemia","RIM E DIABETE MELITO","http://www.abcdasaude.com.br/artigo.php?367"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(4, 2, "Problemas que aparecem na perna e, particularmente no pé dos diabéticos","PÉ DIABÉTICO","http://www.abcdasaude.com.br/artigo.php?492/"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(5, 2, "Doença pode afetar múltiplas funções orgânicas, inclusive a sexual","Transtornos neurológicos e Diabetes mellitus","http://www.abcdasaude.com.br/artigo.php?381"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(6, 2, "É o termo usado para designar as doenças degenerativas não inflamatórias da retina","RETINOPATIA","http://www.abcdasaude.com.br/artigo.php?364"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(7, 2, "No diabete melito, as altas taxas de açúcar sangüíneo, torna o paciente um grande bebedor de água","NOCTURIA","http://www.abcdasaude.com.br/artigo.php?297"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(8, 2, "Doença congênita (crianças) ou hereditárias (em até 20%) ou por infecções (rubéola, durante a gestação, é a causa mais freqüente) ou ainda por desordens metabólicas (diabetes)","CATARATA","http://www.abcdasaude.com.br/artigo.php?70"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(9, 2, "A insuficiência venosa crônica é a principal causa do desenvolvimento de úlceras de perna","ULCERA DE PERNA","http://www.abcdasaude.com.br/artigo.php?440"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(10, 2, "O diabete é um sério fator de risco para doença cardiovascular","Doenças Cardiacas","http://www.abcdasaude.com.br/artigo.php?196"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(11, 2, "A excreção de albumina na urina (proteinúria) é uma importante alteração pela qual as doenças renais se manifestam","PROTEINÚRIA","http://www.abcdasaude.com.br/artigo.php?350"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(12, 2, "Conjunto de sinais e sintomas do excesso da cortisona","SINDROME DE CUSHING","http://www.abcdasaude.com.br/artigo.php?392"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(13, 2, "Dormir bem pode ajudar a prevenir o diabetes","Diabetes Mellitus","http://www.diabetenet.com.br/conteudocompleto.asp?idconteudo=7938"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(14, 2, "Site de auto-ajuda para doentes","Diabetes Mellitus","http://www.diabete.com.br/fadiga-cronica-e-comum-no-diabetes-tipo-1s-diz-novo-estudo/"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(15, 2, "É associada a doenças imunológicas, tais como diabetes","VITILIGO","http://www.abcdasaude.com.br/artigo.php?456"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(16, 2, "O controle adequado da pressão arterial em quem tem pressão alta e do diabetes também são aspectos muito importantes no tratamento","Insuficiência Renal Crônica","http://www.abcdasaude.com.br/artigo.php?700"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(17, 2, "Doenças inflamatórias e/ou infecciosas da bexiga","INFECÇÃO URINÁRIA: CISTITE","http://www.abcdasaude.com.br/artigo.php?496"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(18, 2, "Conjunto de sinais e sintomas decorrentes da diminuição dos hormônios da tireóide","HIPOTIREOIDISMO","http://www.abcdasaude.com.br/artigo.php?248"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(19, 2, "Enfermidade caracterizada pelo acúmulo excessivo de gordura corporal","OBESIDADE","http://www.abcdasaude.com.br/artigo.php?303"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(20, 2, "Mais cinco medos comuns em Diabetes","Medos comuns em Diabetes","http://www.diabete.com.br/mais-cinco-medos-comuns-em-diabetes/"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(21, 2, "Tipos de Diabetes Mellitus","Diabetes Mellitus","http://www.abc.med.br/p/diabetes-mellitus/22360/diabetes+mellitus.htm"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(22, 2, "Prevenção contra o Diabetes","Previna-se contra o Diabetes","http://www.anutricionista.com/previna-se-contra-o-diabetes.html"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(23, 2, "Uma doença metabólica crônica caracterizada por hiperglicemia","O que é Diabetes","http://www.minhavida.com.br/saude/temas/diabetes"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(24, 2, "Dicas alimentares para o Diabetes","Alimentação para Diabeticos","http://www.anutricionista.com/alimentacao-para-diabeticos.html"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(25, 2, "Diferenciar os alimentos DIET e LIGHT","Diet e Light: Entenda a Diferença","http://www.anutricionista.com/diet-e-light-entenda-a-diferenca.html"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(26, 2, "A creatina proporciona ganho de força e energia","Suplementação com creatina no futebol","http://www.anutricionista.com/suplementacao-com-creatina-no-futebol.html"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(27, 2, "Diabetes Mellitus Tipo 1 e Tipo 2: Diferenças e Sintomas","Diabetes Mellitus Tipo 1 e Tipo 2","http://www.anutricionista.com/diabetes-mellitus-tipo-1-e-tipo-2-diferencas-e-sintomas.html"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(28, 2, "Comer frutas e evitar sucos industrializados ajuda a prevenir diabetes","Dicas alimentares","http://www.diabete.com.br/comer-frutas-e-evitar-sucos-industrializados-ajuda-a-prevenir-diabetes/"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(29, 2, "A Creatina controla os níveis de Diabetes Tipo 2","Diabetes Tipo 2 – Creatina ajuda a controlar?","http://www.anutricionista.com/diabetes-tipo-2-creatina-ajuda-a-controlar.html"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(30, 2, "Informações sobre Diabetes","Sobre diabetes","http://www.mudandodiabetes.com.br/mudandodiabetes/"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(31, 2, "Informações sobre os tipos de Diabetes","Informações de Diabetes","http://www.diabetes.med.br/diabetes.php"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(32, 2, "Quando há perda do paralelismo entre os olhos","ESTRABISMO","http://www.abcdasaude.com.br/artigo.php?191"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(33, 2, "O dia mundial do Diabetes","O Diabetes","http://www.diamundialdodiabetes.org.br/sobre-o-diabetes/"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(34, 2, "Tratamento precoce da diabetes pode reduzir o risco de doenças cardíacas","Tratamento precoce da diabetes","http://www.soudiabetico.com.br/noticias/tratamento-precoce-da-diabetes-pode-reduzir-o-risco-de-doencas-cardiacas"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(35, 2, "Site de auto-ajuda para Diabéticos","Aumento do risco de diabetes","http://www.soudiabetico.com.br/noticias/dormir-menos-de-seis-horas-por-noite-pode-aumentar-o-risco-de-diabetes"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(36, 2, "Chá preto pode reduzir risco de diabetes tipo 2","Reduzir risco de diabetes tipo 2","http://www.soudiabetico.com.br/noticias/cha-preto-pode-reduzir-risco-de-diabetes-tipo-2-diz-estudo"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(37, 2, "Comer lentamente poderia reduzir o risco de diabetes","Reduzir o risco de diabetes","http://www.soudiabetico.com.br/noticias/comer-lentamente-poderia-reduzir-o-risco-de-diabetes"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(38, 2, "Teste de glicemia obrigatório","Teste de glicemia","http://www.soudiabetico.com.br/especiais/teste-de-glicemia-obrigatorio"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(39, 2, "Passo a Passo no Pé Diabético 2013","Pé Diabético 2013","http://www.diabetes.org.br/sala-de-noticias/2374-passo-a-passo-no-pe-diabetico-2013"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(40, 2, "Site de auto-ajuda para doentes","O diabetes e a lipodistrofia","http://www.soudiabetico.com.br/educacional/o-diabetes-e-a-lipodistrofia"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(41, 2, "Sou Diabético","Mulher diabética grávida","http://www.soudiabetico.com.br/noticias/mulher-diabetica-gravida"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(42, 2, "Diabetes Mellitus é uma doença do metabolismo da glicose causada pela falta ou má absorção de insulina","Diabetes Mellitus","http://drauziovarella.com.br/diabetes/diabetes/"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(43, 2, "Sintomas do diabetes","Sintomas","http://medico.uol.com.br/br/question/quais-sao-os-sintomas-da-diabetes"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(44, 2, "Sintomas de Diabetes Tipos 1 e 2","Sintomas de Diabetes","http://www.tuasaude.com/sintomas-de-diabetes/"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(45, 2, "Software ajuda a diagnosticar a cegueira por diabetes","Cegueira em Diabéticos","http://www.soudiabetico.com.br/noticias/software-ajuda-a-diagnosticar-a-cegueira-por-diabetes"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(46, 2, "Prevenção e orientação","Dia Nacional do Diabetes","http://www.corpore.org.br/cws_exibeconteudogeral_4373.asp"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(47, 2, "Diabetes mellitus","ABOUT DIABETES","http://www.idf.org/about-diabetes"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(48, 2, "Luz para tratar o Diabetes","Tratamento de Diabetes","http://www.ebc.com.br/noticias/saude/galeria/videos/2012/11/tratamento-com-luz-ajuda-pacientes-diabeticos"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(49, 2, "10 Coisas que Você Precisa Saber Sobre Diabetes","Saber Sobre Diabetes","http://www.endocrino.org.br/10-coisas-que-voce-precisa-saber-sobre-diabetes/"));
		titleDescriptionUrl.add(new TitleDescriptionUrl(50, 2, "Sintomas na Fase Inicial","PRIMEIROS SINTOMAS DO DIABETES","http://www.mdsaude.com/2011/12/sintomas-diabetes.html"));

		/*
		for (int i = 0; i < 4; i++) {
			System.out.println(titleDescriptionUrl.get(i).getTitle() + "***" + titleDescriptionUrl.get(i).getDescription() + "***" + titleDescriptionUrl.get(i).getUrlOnline() );	
		}
		*/
		
	}
		
	private String randomDescription() {
		Random random = new Random();
		int num = random.nextInt(143); // 143 descrições
		List<String> lista;
		HashMap<String, String> listadupla;
		
		lista = new GenerateContent_2().getDescription();
		return lista.get(num);
				
	}
		
	private String randomTitle() {
		Random random = new Random();
		int num = random.nextInt(143); // 143 títulos
		List<String> lista;
		
		lista = new GenerateContent_2().getTitle();
		return lista.get(num);
	}
	
	
	private String randomUrlOnline() {
		Random random = new Random();
		int num = random.nextInt(143); // 143 links
		List<String> lista;
		
		lista = new GenerateContent_2().getUrlOnline();
		return lista.get(num);
	}
	
	
	public void run() {
		List<Content> con = new ArrayList<Content>();
		
		Random random = new Random();
		
		for (int i = 0; i < LIMITE; i++) {

			Content content = new Content();
			
			content.setBytesOnline( random.nextInt(1000) );
			content.setDateCreation( generateDate(i) );
			content.setDateEvent( generateDate(i) );
			content.setDescription( randomDescription() );
			content.setRateAcceptance( random.nextInt(1000) );
			content.setRateColabPonder( random.nextInt(1000) );
			content.setRating( random.nextInt(1000));
			content.setSecondsOnline( random.nextInt(1000) );
			//SUBTYPE_POST_PAGINA = 3;
			content.setSubtype(3);
			content.setTitle( randomTitle() );
			//TYPE_POST = 0;
			content.setType(0);
			content.setUrlOnline( randomUrlOnline() );
			content.setVisibility( random.nextInt(3) );
			content.setVisibilityGroup( random.nextInt(2));
			content.setFile(null);
			content.setFrequency(null);
						
			con.add(content);
		}
		
		saveServer(con);
		saveInFile(con);
	}

	private void saveServer(List<Content> con) {
		
		for(Content content : con) {
			managerServer.createContent(content);
		}
		
	}

	private void saveInFile(List<Content> con) {

		try {
			PrintWriter out = new PrintWriter("teste_content.txt");

			for (Content content : con) {

				String strdate = null;

				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

				if (content.getDateCreation() != null) {
					strdate = sdf.format(content.getDateCreation().getTime());
				}

				String imprimir = content.getDescription() + "|"
						+ content.getTitle() + "|" + content.getUrlOnline();

				out.println(imprimir);
			}
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	private static Calendar generateDate(int i) {

		int year, month, day;
		int age = 0;

		Calendar date = Calendar.getInstance();

		Calendar currentDate = Calendar.getInstance();
		int currentYear = currentDate.get(Calendar.YEAR);
		int currentMonth = currentDate.get(Calendar.MONTH);
		int currentDay = currentDate.get(Calendar.DAY_OF_MONTH);

		Random random = new Random();

		if (i < 50) { 
			age = random.nextInt(14) + 10;
		} else if (i < 100) { 
			age = random.nextInt(14) + 25;
		} else if (i < 150) { 
			age = random.nextInt(5) + 50;
		} else if (i < 200) { 
			age = random.nextInt(13) + 56;
			// > 70
		} else if (i < 300) { 
			age = random.nextInt(32) + 70;
		}

		year = currentYear - age;
		month = random.nextInt(currentMonth + 1);
		if (month == currentMonth) {
			day = random.nextInt(currentDay - 1) + 1;
		} else {
			date.set(year, month, 1);
			date.getActualMaximum(Calendar.DAY_OF_MONTH);
			day = date.get(Calendar.DAY_OF_MONTH);
		}

		date.set(year, month, day);

		return date;
	}
	
	private List<String> getDescription() {

		List<String> c = new ArrayList<String>();
		
		c.add("Site de auto-ajuda para doentes de Diabetes");
		c.add("Doença metabólica crônica caracterizada por hiperglicemia");
		c.add("Doença metabólica crônica caracterizada por hiperglicemia");
		c.add("Problemas que aparecem na perna e, particularmente no pé dos diabéticos");
		c.add("Doença pode afetar múltiplas funções orgânicas, inclusive a sexual");
		c.add("É o termo usado para designar as doenças degenerativas não inflamatórias da retina");
		c.add("No diabete melito, as altas taxas de açúcar sangüíneo, torna o paciente um grande bebedor de água");
		c.add("Doença congênita (crianças) ou hereditárias (em até 20%) ou por infecções (rubéola, durante a gestação, é a causa mais freqüente) ou ainda por desordens metabólicas (diabetes)");
		c.add("A insuficiência venosa crônica é a principal causa do desenvolvimento de úlceras de perna");
		c.add("O diabete é um sério fator de risco para doença cardiovascular");
		c.add("A excreção de albumina na urina (proteinúria) é uma importante alteração pela qual as doenças renais se manifestam");
		c.add("Conjunto de sinais e sintomas do excesso da cortisona");
		c.add("Dormir bem pode ajudar a prevenir o diabetes");
		c.add("Site de auto-ajuda para doentes");
		c.add("É associada a doenças imunológicas, tais como diabetes");
		c.add("O controle adequado da pressão arterial em quem tem pressão alta e do diabetes também são aspectos muito importantes no tratamento");
		c.add("Doenças inflamatórias e/ou infecciosas da bexiga");
		c.add("Conjunto de sinais e sintomas decorrentes da diminuição dos hormônios da tireóide");
		c.add("Enfermidade caracterizada pelo acúmulo excessivo de gordura corporal");
		c.add("Mais cinco medos comuns em Diabetes");
		c.add("Tipos de Diabetes Mellitus");
		c.add("Prevenção contra o Diabetes");
		c.add("Uma doença metabólica crônica caracterizada por hiperglicemia");
		c.add("Dicas alimentares para o Diabetes");
		c.add("Diferenciar os alimentos DIET e LIGHT");
		c.add("A creatina proporciona ganho de força e energia");
		c.add("Diabetes Mellitus Tipo 1 e Tipo 2: Diferenças e Sintomas");
		c.add("Comer frutas e evitar sucos industrializados ajuda a prevenir diabetes");
		c.add("A Creatina controla os níveis de Diabetes Tipo 2");
		c.add("Informações sobre Diabetes");
		c.add("Informações sobre os tipos de Diabetes");
		c.add("Quando há perda do paralelismo entre os olhos");
		c.add("O dia mundial do Diabetes");
		c.add("Tratamento precoce da diabetes pode reduzir o risco de doenças cardíacas");
		c.add("Site de auto-ajuda para Diabéticos");
		c.add("Chá preto pode reduzir risco de diabetes tipo 2");
		c.add("Comer lentamente poderia reduzir o risco de diabetes");
		c.add("Teste de glicemia obrigatório");
		c.add("Passo a Passo no Pé Diabético 2013");
		c.add("Site de auto-ajuda para doentes");
		c.add("Sou Diabético");
		c.add("Diabetes Mellitus é uma doença do metabolismo da glicose causada pela falta ou má absorção de insulina");
		c.add("Sintomas do diabetes");
		c.add("Sintomas de Diabetes Tipos 1 e 2");
		c.add("Software ajuda a diagnosticar a cegueira por diabetes");
		c.add("Prevenção e orientação");
		c.add("Diabetes mellitus");
		c.add("Luz para tratar o Diabetes");
		c.add("10 Coisas que Você Precisa Saber Sobre Diabetes");
		c.add("Sintomas na Fase Inicial");
		c.add("É uma doença crônica de desordem sensorial");
		c.add("Sintomas na Fase Inicial");
		c.add("É uma enfermidade do Intestino Delgado");
		c.add("É a inflamação da vesícula biliar provocada, geralmente, por cálculos");
		c.add("O rim pode ser atingido por doença de origem imunológica, inflamatória, infecciosa, neoplásica, degenerativa, congênita e hereditária");
		c.add("Problema nos rins que passam a não responder a presença do hormônio");
		c.add("Dicas para controlar o diabetes");
		c.add("A insulina, a glicose e o diabetes");
		c.add("Dúvidas Frequentes");
		c.add("Direitos dos diabeticos - tipo1");
		c.add("Perguntas e respostas sobre diabetes");
		c.add("Medicamentos para tratamento de Diabetes");
		c.add("Cuidados com a Diabetes");
		c.add("Dicionario dos alimentos");
		c.add("Novo código de ética médica");
		c.add("Site de auto ajuda aos portadores de diabetes");
		c.add("Diabetes: Controle e Prevenção");
		c.add("Dieta para diabetes");
		c.add("Doenças do Metabolismo – Diabetes Insipidus");
		c.add("Tratamento mais individualizado no combate a diabetes");
		c.add("Diabetes e Disfunção Erétil");
		c.add("Serviços e Medicamentos para Diabéticos");
		c.add("Medidor para controle de glicemia");
		c.add("Degeneração Macular");
		c.add("Espaço Diabetes e Hipertensão");
		c.add("Informações sobre os 4 sintomas do diabetes tipo 1");
		c.add("Informativo sobre Amputações em Diabéticos");
		c.add("Comportamento do portador de diabetes");
		c.add("Medicamento para Diabéticos em Portugal");
		c.add("Distribuição de medicamentos para diabetes e hipertensão");
		c.add("Comunidade para Diabéticos");
		c.add("Ajudando a entender sobre o Diabetes");
		c.add("Novidades no tratamento do Diabetes");
		c.add("Ajuda a Crianças com Diabetes");
		c.add("Pesquisas com celulas tronco no tratamento do diabetes");
		c.add("Aplicativo para o controle do Diabetes");
		c.add("Novidades na aplicação de insulina em diabéticos");
		c.add("Novidades em Diabetes Tipo 1");
		c.add("Novidades tecnológicas do congresso europeu de Diabetes");
		c.add("Site divertido para crianças com diabetes");
		c.add("Site sobre educação em diabetes");
		c.add("Novidades no tratamento do diabetes tipo 2");
		c.add("Controlar a diabetes");
		c.add("Introdução à Estrutura de dados");
		c.add("Armazenamento sequencial em Estrutura de dados");
		c.add("Vetores em Estrutura de dados");
		c.add("Listas ligadas em Estrutura de dados");
		c.add("Aprendendo sobre pilhas em Estrutura de dados");
		c.add("Filas em Estrutura de dados");
		c.add("Armazenamento sem repetição em Estrutura de dados");
		c.add("Tabelas de espelhamento em Estrutura de dados");
		c.add("Armazenamento Associativo em Estrutura de dados");
		c.add("Mapas com listas em Estrutura de dados");
		c.add("Mapas com espelhamento em Estrutura de dados");
		c.add("Algoritmos e Pilhas em Estrutura de dados");
		c.add("Apostila sobre Estrutura de dados");
		c.add("Apostila sobre Estrutura de dados");
		c.add("Pilhas em Estrutura de dados");
		c.add("Informações sobre Estrutura de dados");
		c.add("Apostila de curso sobre Estrutura de dados");
		c.add("Dinâmica em C - Estrutura de dados");
		c.add("Ordenação em Estrutura de dados - InsertionSort");
		c.add("Tipos de Estruturas em Estrutura de dados");
		c.add("Vetores em Estrutura de dados");
		c.add("Vetores em Estrutura de dados");
		c.add("Pilhas em Estrutura de dados - Capítulo 2");
		c.add("Listas em Estrutura de dados - Capítulo 2");
		c.add("Alocação Dinâmica em Estrutura de dados");
		c.add("Listas Encadeadas em Estrutura de dados - Capítulo 4");
		c.add("Árvore de Busca em Estrutura de dados");
		c.add("Estrutura de dados");
		c.add("Hashing em Estrutura de dados");
		c.add("Complexidade de Algoritmos em Estrutura de dados");
		c.add("Revisão e Introdução em Estrutura de dados");
		c.add("Estrutura de dados Composta Homogênea");
		c.add("Algoritmos de Ordenação em Estrutura de dados");
		c.add("Ponteiros em Estrutura de dados");
		c.add("Linguagem C em Estrutura de dados");
		c.add("Conceito de Árvores em Estrutura de dados");
		c.add("Pilha com Ponteiro em Estrutura de dados");
		c.add("Lista Encadeada em Estrutura de dados");
		c.add("Pilha em Estrutura de dados");
		c.add("Fila em Estrutura de dados");
		c.add("Árvore Binária em Estrutura de dados");
		c.add("Função e Ponteiro em Estrutura de dados");
		c.add("Introdução em Estrutura de dados");
		c.add("Exemplo de Pilha em Estrutura de dados");
		c.add("Árvores AVL em Estrutura de dados");
		c.add("Algoritmos de Ordenação em Estrutura de dados");
		c.add("Função em Estrutura de dados");
		c.add("Árvores binárias ordenadas em Estrutura de dados");
		c.add("Árvore BST em Estrutura de dados");
		c.add("Hashagem dupla em Estrutura de dados");

	
		return c;		
	}
	
	private List<String> getTitle() {

		List<String> c = new ArrayList<String>();
			
		c.add("Diabetes Mellitus");
		c.add("Gestação e Diabetes");
		c.add("RIM E DIABETE MELITO");
		c.add("PÉ DIABÉTICO");
		c.add("Transtornos neurológicos e Diabetes mellitus");
		c.add("RETINOPATIA");
		c.add("NOCTURIA");
		c.add("CATARATA");
		c.add("ULCERA DE PERNA");
		c.add("Doenças Cardiacas");
		c.add("PROTEINÚRIA");
		c.add("SINDROME DE CUSHING");
		c.add("Diabetes Mellitus");
		c.add("Diabetes Mellitus");
		c.add("VITILIGO");
		c.add("Insuficiência Renal Crônica");
		c.add("INFECÇÃO URINÁRIA: CISTITE");
		c.add("HIPOTIREOIDISMO");
		c.add("OBESIDADE");
		c.add("Medos comuns em Diabetes");
		c.add("Diabetes Mellitus");
		c.add("Previna-se contra o Diabetes");
		c.add("O que é Diabetes");
		c.add("Alimentação para Diabeticos");
		c.add("Diet e Light: Entenda a Diferença");
		c.add("Suplementação com creatina no futebol");
		c.add("Diabetes Mellitus Tipo 1 e Tipo 2");
		c.add("Dicas alimentares");
		c.add("Diabetes Tipo 2 – Creatina ajuda a controlar?");
		c.add("Sobre diabetes");
		c.add("Informações de Diabetes");
		c.add("ESTRABISMO");
		c.add("O Diabetes");
		c.add("Tratamento precoce da diabetes");
		c.add("Aumento do risco de diabetes");
		c.add("Reduzir risco de diabetes tipo 2");
		c.add("Reduzir o risco de diabetes");
		c.add("Teste de glicemia");
		c.add("Pé Diabético 2013");
		c.add("O diabetes e a lipodistrofia");
		c.add("Mulher diabética grávida");
		c.add("Diabetes Mellitus");
		c.add("Sintomas");
		c.add("Sintomas de Diabetes");
		c.add("Cegueira em Diabéticos");
		c.add("Dia Nacional do Diabetes");
		c.add("ABOUT DIABETES");
		c.add("Tratamento de Diabetes");
		c.add("Saber Sobre Diabetes");
		c.add("PRIMEIROS SINTOMAS DO DIABETES");
		c.add("A Síndrome das Pernas Inquietas (SPI)");
		c.add("CONSTIPAÇÃO INTESTINAL");
		c.add("DOENÇA CELÍACA");
		c.add("CÁLCULOS BILIARES, CÓLICA BILIAR, COLECISTITE AGUDA");
		c.add("INTRODUÇÃO ÀS DOENÇAS RENAIS");
		c.add("DIABETES INSIPIDUS");
		c.add("CONTROLE DO DIABETES");
		c.add("PREVENÇÃO E CAUSAS");
		c.add("SOU DIABÉTICO, E AGORA?");
		c.add("Direitos dos Diabéticos");
		c.add("Dúvidas sobre diabetes");
		c.add("Medicamentos para Diabéticos");
		c.add("Em dia com a saude");
		c.add("Alimentação para Diabéticos");
		c.add("Diabetes Brasil");
		c.add("Leve o Diabetes a sério");
		c.add("Diabetes");
		c.add("Diabetes");
		c.add("Diabetes Insipidus");
		c.add("Combate a Diabetes");
		c.add("Saúde Sexual do Diabetes");
		c.add("Medicamentos para diabéticos");
		c.add("Controlando a Glicemia");
		c.add("Degeneração Macular");
		c.add("Diabetes e hipertensao");
		c.add("Sintomas do diabetes tipo 1");
		c.add("Amputações e Diabetes");
		c.add("Comportamento do Diabetes");
		c.add("Medicamentos de Diabéticos");
		c.add("Medicação para diabetes e hipertensão");
		c.add("Diabetenet");
		c.add("Portal diabetes");
		c.add("Tratamento do Diabetes");
		c.add("Criança com Diabetes");
		c.add("Tratamento do Diabetes");
		c.add("Aplicativo para Diabetes");
		c.add("Diabetes: Aplicacao de insulina");
		c.add("Diabetes Tipo 1");
		c.add("Tecnologias novas para Diabéticos");
		c.add("Diabetes Weekend");
		c.add("Educação em Diabetes");
		c.add("Tratamento do diabetes tipo 2");
		c.add("Diabetes");
		c.add("Estrutura de Dados - Introdução");
		c.add("Estrutura de Dados - Armazenamento");
		c.add("Estrutura de Dados - Vetores");
		c.add("Estrutura de Dados - Listas Ligadas");
		c.add("Estrutura de Dados - Pilhas");
		c.add("Estrutura de Dados - Filas");
		c.add("Estrutura de Dados - Armazenamento sem repetição");
		c.add("Estrutura de Dados - Tabelas de Espelhamento");
		c.add("Estrutura de Dados - Armazenamento Associativo");
		c.add("Estrutura de Dados - Mapas com listas");
		c.add("Estrutura de Dados - Mapas com espelhamento");
		c.add("Estrutura de Dados - Algoritmos e Pilhas");
		c.add("Estrutura de Dados - Apostila");
		c.add("Apostila sobre Estrutura de Dados");
		c.add("Algoritmos e Estrutura de Dados - Pilhas");
		c.add("Estrutura de Dados");
		c.add("Estrutura de Dados - Curso");
		c.add("Estrutura de Dados - Dinâmica em C");
		c.add("Estrutura de Dados - Ordenação");
		c.add("Estrutura de Dados - Tipos de Estruturas");
		c.add("Estrutura de Dados - Introdução");
		c.add("Estrutura de Dados - Introdução");
		c.add("Estrutura de Dados - Capítulo 2");
		c.add("Estrutura de Dados - Capítulo 2");
		c.add("Estrutura de Dados - Alocação Dinâmica");
		c.add("Estrutura de Dados - Listas Encadeadas");
		c.add("Estrutura de Dados - Árvore de Busca");
		c.add("Estrutura de Dados");
		c.add("Estrutura de Dados - Hashing");
		c.add("Estrutura de Dados - Complexidade de Algoritmos");
		c.add("Estrutura de Dados - Revisão e Introdução");
		c.add("Estrutura de Dados Homogênea");
		c.add("Estrutura de Dados - Algoritmos de Ordenação");
		c.add("Estrutura de Dados - Ponteiros");
		c.add("Estrutura de Dados - Linguagem C");
		c.add("Estrutura de Dados - Conceito de Árvores");
		c.add("Estrutura de Dados - Pilha com Ponteiro");
		c.add("Estrutura de Dados - Lista Encadeada");
		c.add("Estrutura de Dados - Sobre Pilha");
		c.add("Estrutura de Dados - Fila");
		c.add("Estrutura de Dados - Árvore Binária");
		c.add("Estrutura de Dados - Função e Ponteiro");
		c.add("Estrutura de Dados - Introdução");
		c.add("Estrutura de Dados - Exemplo de Pilha");
		c.add("Estrutura de Dados - Árvores AVL");
		c.add("Estrutura de Dados - Algoritmos de Ordenação");
		c.add("Estrutura de Dados - Função");
		c.add("Estrutura de Dados - Árvores binárias ordenadas");
		c.add("Estrutura de Dados - Árvore BST");
		c.add("Estrutura de Dados - Hashagem dupla");


		return c;		
	}
	
	private List<String> getUrlOnline() {

		List<String> c = new ArrayList<String>();

		c.add("http://www.abcdasaude.com.br/artigo.php?127");
		c.add("http://www.abcdasaude.com.br/artigo.php?209");
		c.add("http://www.abcdasaude.com.br/artigo.php?367");
		c.add("http://www.abcdasaude.com.br/artigo.php?492/");
		c.add("http://www.abcdasaude.com.br/artigo.php?381");
		c.add("http://www.abcdasaude.com.br/artigo.php?364");
		c.add("http://www.abcdasaude.com.br/artigo.php?297");
		c.add("http://www.abcdasaude.com.br/artigo.php?70");
		c.add("http://www.abcdasaude.com.br/artigo.php?440");
		c.add("http://www.abcdasaude.com.br/artigo.php?196");
		c.add("http://www.abcdasaude.com.br/artigo.php?350");
		c.add("http://www.abcdasaude.com.br/artigo.php?392");
		c.add("http://www.diabetenet.com.br/conteudocompleto.asp?idconteudo=7938");
		c.add("http://www.diabete.com.br/fadiga-cronica-e-comum-no-diabetes-tipo-1s-diz-novo-estudo/");
		c.add("http://www.abcdasaude.com.br/artigo.php?456");
		c.add("http://www.abcdasaude.com.br/artigo.php?700");
		c.add("http://www.abcdasaude.com.br/artigo.php?496");
		c.add("http://www.abcdasaude.com.br/artigo.php?248");
		c.add("http://www.abcdasaude.com.br/artigo.php?303");
		c.add("http://www.diabete.com.br/mais-cinco-medos-comuns-em-diabetes/");
		c.add("http://www.abc.med.br/p/diabetes-mellitus/22360/diabetes+mellitus.htm");
		c.add("http://www.anutricionista.com/previna-se-contra-o-diabetes.html");
		c.add("http://www.minhavida.com.br/saude/temas/diabetes");
		c.add("http://www.anutricionista.com/alimentacao-para-diabeticos.html");
		c.add("http://www.anutricionista.com/diet-e-light-entenda-a-diferenca.html");
		c.add("http://www.anutricionista.com/suplementacao-com-creatina-no-futebol.html");
		c.add("http://www.anutricionista.com/diabetes-mellitus-tipo-1-e-tipo-2-diferencas-e-sintomas.html");
		c.add("http://www.diabete.com.br/comer-frutas-e-evitar-sucos-industrializados-ajuda-a-prevenir-diabetes/");
		c.add("http://www.anutricionista.com/diabetes-tipo-2-creatina-ajuda-a-controlar.html");
		c.add("http://www.mudandodiabetes.com.br/mudandodiabetes/");
		c.add("http://www.diabetes.med.br/diabetes.php");
		c.add("http://www.abcdasaude.com.br/artigo.php?191");
		c.add("http://www.diamundialdodiabetes.org.br/sobre-o-diabetes/");
		c.add("http://www.soudiabetico.com.br/noticias/tratamento-precoce-da-diabetes-pode-reduzir-o-risco-de-doencas-cardiacas");
		c.add("http://www.soudiabetico.com.br/noticias/dormir-menos-de-seis-horas-por-noite-pode-aumentar-o-risco-de-diabetes");
		c.add("http://www.soudiabetico.com.br/noticias/cha-preto-pode-reduzir-risco-de-diabetes-tipo-2-diz-estudo");
		c.add("http://www.soudiabetico.com.br/noticias/comer-lentamente-poderia-reduzir-o-risco-de-diabetes");
		c.add("http://www.soudiabetico.com.br/especiais/teste-de-glicemia-obrigatorio");
		c.add("http://www.diabetes.org.br/sala-de-noticias/2374-passo-a-passo-no-pe-diabetico-2013");
		c.add("http://www.soudiabetico.com.br/educacional/o-diabetes-e-a-lipodistrofia");
		c.add("http://www.soudiabetico.com.br/noticias/mulher-diabetica-gravida");
		c.add("http://drauziovarella.com.br/diabetes/diabetes/");
		c.add("http://medico.uol.com.br/br/question/quais-sao-os-sintomas-da-diabetes");
		c.add("http://www.tuasaude.com/sintomas-de-diabetes/");
		c.add("http://www.soudiabetico.com.br/noticias/software-ajuda-a-diagnosticar-a-cegueira-por-diabetes");
		c.add("http://www.corpore.org.br/cws_exibeconteudogeral_4373.asp");
		c.add("http://www.idf.org/about-diabetes");
		c.add("http://www.ebc.com.br/noticias/saude/galeria/videos/2012/11/tratamento-com-luz-ajuda-pacientes-diabeticos");
		c.add("http://www.endocrino.org.br/10-coisas-que-voce-precisa-saber-sobre-diabetes/");
		c.add("http://www.mdsaude.com/2011/12/sintomas-diabetes.html");
		c.add("http://www.abcdasaude.com.br/artigo.php?723");
		c.add("http://www.abcdasaude.com.br/artigo.php?94");
		c.add("http://www.abcdasaude.com.br/artigo.php?148");
		c.add("http://www.abcdasaude.com.br/artigo.php?88");
		c.add("http://www.abcdasaude.com.br/artigo.php?270");
		c.add("http://www.mdsaude.com/2009/04/diabetes-insipidus.html");
		c.add("https://www.starbem.com.br/Diabetes/FormasControle");
		c.add("https://www.starbem.com.br/Diabetes/PrevencaoCausas");
		c.add("http://diabetesbrasilia.com.br/sou-diabetico-e-agora/");
		c.add("http://direitosdosdiabeticostipo1.blogspot.com.br");
		c.add("http://www.diabetes.org.br/voce-pergunta-e-a-sbd-responde/1600-duvidas-sobre-diabetes--71-a-100");
		c.add("http://loja.diabetescenter.com.br/Categoria--MEDICAMENTOS-P-DIABETES-30.aspx");
		c.add("http://www.einstein.br/einstein-saude/em-dia-com-a-saude/Paginas/diabetes-em-crescimento-acelerado.aspx");
		c.add("http://www.diabetes.org.br/dicionario-dos-alimentos");
		c.add("http://www.adj.org.br/site/noticias_read.asp?id=999&tipo=5");
		c.add("http://www.futurosobmedida.com.br/Saude/leveodiabetesaserio.php#.UtK5qNJDtHE");
		c.add("http://saude.sapo.pt/saude-medicina/medicacao-doencas/doencas/diabetes-controlo-e-prevencao.html");
		c.add("http://www.bemestaragora.com.br/dieta-para-diabetes/");
		c.add("http://www.medicamentosesaude.com/doencas-do-metabolismo-diabetes-insipidus/");
		c.add("http://www.correiobraziliense.com.br/app/noticia/ciencia-e-saude/2013/07/03/interna_ciencia_saude,374756/tratamento-mais-individualizado-e-nova-tendencia-no-combate-a-diabetes.shtml");
		c.add("http://bostonmedicalgroup.com.br/saude-sexual-diabetes-no-brasil-e-a-disfuncao-eretil/?gclid=CImvh43_-LsCFSRk7Aodm3YAgQ");
		c.add("http://www.diabeteservice.com.br/?gclid=CJC3z6SA-bsCFSEV7AodHwMACg");
		c.add("http://www.onetouchla.com/br?gclid=CLPVgaaA-bsCFfPm7AodtV0Aiw");
		c.add("http://sentirbem.uol.com.br/?modulo=entrevistas&id=4&tipo=");
		c.add("http://extrafarma.wordpress.com/2013/09/04/espaco-diabetes-e-hipertensao/");
		c.add("http://www.diabeticool.com/voce-conhece-os-4-sintomas-do-diabetes-tipo-1/");
		c.add("http://www.ribeiraopretoonline.com.br/coluna-julieta-ueta/coluna-da-dra-julieta-ueta-os-membros-que-o-diabetes-amputa-como-salva-los/35980");
		c.add("http://www.accu-chek.com.br/br/especiais/comportamento-acampamento.html");
		c.add("http://web.allstoreplace.biz/diabetes-medicina-portugal.html");
		c.add("http://www.iasep.pa.gov.br/?q=programa-distribui-medica%C3%A7%C3%A3o-diabetes-e-hipertens%C3%A3o");
		c.add("http://www.diabetenet.com.br/conteudocompleto.asp?idconteudo=880");
		c.add("http://www.portaldiabetes.com.br/");
		c.add("http://vivendocomdiabetes.blogspot.com.br/2008/02/novidades-sobre-o-tratamento-do.html");
		c.add("http://www.icdrs.org.br/");
		c.add("http://www.endocrino.org.br/pesquisas-com-celulas-tronco-no-tratamento-do-diabetes/");
		c.add("http://convergenciadigital.uol.com.br/cgi/cgilua.exe/sys/start.htm?infoid=34670&sid=17#.UtLAsNJDtHE");
		c.add("http://www.glicemiasonline.com.br/blog/diabetes-novidades-na-aplicacao-de-insulina/");
		c.add("http://durval.site.med.br/index.asp?PageName=novidades-20em-20diabetes-20tipo-201");
		c.add("http://www.grupedh.com.br/index.php?option=com_content&view=article&id=157%3Anovidades-tecnologicas-do-congresso-europeu-de-diabetes-&catid=41%3Anoticias&Itemid=1");
		c.add("http://www.diabetes.med.br/");
		c.add("http://www.tvaberta.tv.br/programacao/106/anad-educacao-em-diabetes");
		c.add("http://vivercomdiabetes.com/2010/02/28/novidades-no-tratamento-do-diabetes-tipo-2/");
		c.add("http://controlaradiabetes.com/");
		c.add("http://www.caelum.com.br/apostila-java-estrutura-dados/introducao/");
		c.add("http://www.caelum.com.br/apostila-java-estrutura-dados/armazenamento-sequencial/");
		c.add("http://www.caelum.com.br/apostila-java-estrutura-dados/vetores/");
		c.add("http://www.caelum.com.br/apostila-java-estrutura-dados/listas-ligadas/");
		c.add("http://www.caelum.com.br/apostila-java-estrutura-dados/pilhas/");
		c.add("http://www.caelum.com.br/apostila-java-estrutura-dados/filas/");
		c.add("http://www.caelum.com.br/apostila-java-estrutura-dados/armazenamento-sem-repeticao-com-busca-rapida/");
		c.add("http://www.caelum.com.br/apostila-java-estrutura-dados/tabelas-de-espalhamento/");
		c.add("http://www.caelum.com.br/apostila-java-estrutura-dados/armazenamento-associativo/");
		c.add("http://www.caelum.com.br/apostila-java-estrutura-dados/mapas-com-lista/");
		c.add("http://www.caelum.com.br/apostila-java-estrutura-dados/mapas-com-espalhamento/");
		c.add("http://www.ime.usp.br/~pf/algoritmos/aulas/pilha.html");
		c.add("http://www.inf.ufpr.br/cursos/ci055/apostila.pdf");
		c.add("http://www.dca.fee.unicamp.br/cursos/EA876/apostila/HTML/node10.html");
		c.add("http://pt.wikibooks.org/wiki/Algoritmos_e_Estruturas_de_Dados/Pilhas");
		c.add("http://pt.wikipedia.org/wiki/Estrutura_de_dados");
		c.add("http://inf.uri.com.br/neilor/apostila-ED2.pdf");
		c.add("http://www.cprogressivo.net/2013/10/Estrutura-de-dados-dinamica-em-C-Listas-Filas-Pilhas-Arvores.html");
		c.add("http://www.ufjf.br/jairo_souza/files/2009/12/2-Ordenacao-InsertionSort.pdf");
		c.add("http://www.javaprogressivo.net/2012/09/introducao-aos-tipos-de-estrutura-de.html");
		c.add("http://livraria.folha.com.br/livros/bancos-de-dados/estruturas-dados-usando-c-aaron-tenenbaum-yedidyah-1143481.html");
		c.add("http://www.inf.ufsc.br/~ine5384-hp/estrut08.html");
		c.add("http://www.inf.ufsc.br/~ine5384-hp/Capitulo2/EstruturasPilha.html");
		c.add("http://www.inf.ufsc.br/~ine5384-hp/Capitulo2/EstruturasLista.html");
		c.add("http://www.inf.ufsc.br/~ine5384-hp/Estruturas.AlocDinamica.html");
		c.add("http://www.inf.ufsc.br/~ine5384-hp/Capitulo4/EstruturasListaEncadeada.html");
		c.add("http://www.inf.ufsc.br/~ine5384-hp/Estruturas.ArvBusca.html");
		c.add("http://www.inf.ufsc.br/~ine5384-hp/estru8-3.html");
		c.add("http://www.inf.ufsc.br/~ine5384-hp/Hashing/");
		c.add("http://www.di.ufpb.br/lucidio/complexest.htm");
		c.add("http://www.gpec.ucdb.br/pistori/disciplinas/ed/aulas_I/index.html");
		c.add("http://cae.ucb.br/conteudo/programar/algoritmobsi/new_estruturadedadoshomogeneabsi.html");
		c.add("http://pt.wikiversity.org/wiki/Introdu%C3%A7%C3%A3o_%C3%A0s_Estruturas_de_Dados/Algoritmos_de_Ordena%C3%A7%C3%A3o");
		c.add("http://programando-ads.blogspot.com.br/2012/07/estrutura-de-dados-ponteiros.html");
		c.add("http://www.linguagemc.xpg.com.br/estrutura.html");
		c.add("http://bloginfogeeks.blogspot.com.br/2010/12/conceitos-de-arvores.html");
		c.add("http://www.matbra.com/2009/04/28/pilha-com-ponteiros-estrutura-de-dados/");
		c.add("http://www.numerofilia.com.br/2011/10/estrutura-de-dados-1-lista-encadeada.html");
		c.add("http://www.numerofilia.com.br/2011/10/estrutura-de-dados-2-pilha.html");
		c.add("http://www.numerofilia.com.br/2011/11/estrutura-de-dados-3-fila.html");
		c.add("http://www.numerofilia.com.br/2011/11/estrutura-de-dados-4-arvore-binaria.html");
		c.add("http://forum.programacaobrasil.com/t288-estrutura-de-dados-com-funcao-e-ponteiro");
		c.add("http://www.jonathanribas.com/blog/introducao-a-estrutura-de-dados/");
		c.add("http://blog.tiagopassos.com/2010/09/30/exemplo-de-pilha-estrutura-de-dados/");
		c.add("http://w3.ualg.pt/~hshah/ped/aula%2012/Arvores%20AVL.htm");
		c.add("http://w3.ualg.pt/~hshah/ped/Aula%2013/Aula%2013.htm");
		c.add("http://w3.ualg.pt/~hshah/ped/Aula%208/aula8/aula_8.html");
		c.add("http://w3.ualg.pt/~hshah/ped/Aula%209/index.html");
		c.add("http://w3.ualg.pt/~hshah/ped/Aula%2011/A11/aula11.html");
		c.add("http://w3.ualg.pt/~hshah/ped/Aula%2020/TRABALHO1.htm");

			
		return c;
		
	}
	
}
