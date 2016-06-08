/*
 * Essa interface terá a funcionalidade de calcular o nível de similaridade de usuários
 * e recursos em relação às ontologias de domínio. Esse cálculo será utilizado para
 * relações entre Usuários -> Dominio e Recrusos -> Domínio
 */

package mobilehealth.prc.analyzer.semantic.indexer;

public interface RelationCalc {

	public float getResourceDomainRelation(
			String resourceURI, 
			String domainURI, 
			boolean recursive, 
			String profileOntotext, 
			String domainOntoText);

	public float getUserInterest(
			String userID, 
			String domainURI, 
			boolean recursive, 
			String profileOntoText, 
			String domainOntoText);
	
	public void finalize();
}
