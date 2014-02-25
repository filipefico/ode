package ode.agenda.ciu;

import java.util.List;

import ode._infraestruturaBase.util.NucleoContexto;
import ode.agenda.cdp.Contact;

import org.zkoss.zul.Column;
import org.zkoss.zul.Columns;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Window;

public class JanContatos extends Window {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JanContatos(CtrlContatos ctrl, List<Contact> contatos){
		super();
		
		this.setTitle("Contatos");
		this.setWidth("700px");
		this.setPosition("&quot;center;&quot;;");
		this.setClosable(true);
		
		this.setParent(NucleoContexto.recuperarJanelaPrincipal());
		
		Grid gridContatos = new Grid();
		gridContatos.setMold("paging");
		gridContatos.setAutopaging(true);
		gridContatos.setHeight("210px");
		gridContatos.setVflex(true);
		
			Columns colunas = new Columns();
				Column colunaNome = new Column("Nome");
				colunaNome.setParent(colunas);
				Column colunaEmail = new Column("Email");
				colunaEmail.setParent(colunas);
				Column colunaTel = new Column("Telefone");
				colunaTel.setParent(colunas);
			colunas.setParent(gridContatos);
			
			Rows linhas = new Rows();
			
				for(Contact contato : contatos) {
					Row linha = new Row();
						Label labelNome = new Label(contato.getNome());
						labelNome.setParent(linha);
						
						Label labelEmail = new Label();
						if(contato.getEmail() != null){
							labelEmail.setValue(contato.getEmail().get(0));
							if(contato.getEmail().size() > 1) {
								labelEmail.setValue(labelEmail.getValue() + " +" + (contato.getEmail().size() - 1));
							}
						}
						labelEmail.setParent(linha);
						
						Label labelTelefone = new Label();
						if(contato.getTelefone() != null){
							labelTelefone.setValue(contato.getTelefone().get(0));
						}
						labelTelefone.setParent(linha);
					linha.setParent(linhas);
				}
			
			linhas.setParent(gridContatos);
		
		gridContatos.setParent(this);
		
	}

	public void mostrar(){
		doOverlapped();
	}

}
