package ode.pgds.cih;

import java.util.List;

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import ode._infraestruturaCRUD.ciu.GridDados;
import ode._infraestruturaCRUD.ciu.JanelaSimples;
import ode.pgds.cgt.AplImportarProjeto;

@SuppressWarnings("serial")
public class JanelaImportarProjeto extends JanelaSimples {
	AplImportarProjeto aplImportar = SpringUtil.getApplicationContext()
			.getBean(AplImportarProjeto.class);

	Combobox comboRepositorio = new Combobox();
	Textbox textboxProjeto = new Textbox();
	Textbox textboxEndereco = new Textbox();
	Button atualizar = new Button();
	Window janela = this;

	public JanelaImportarProjeto() {
		super();
		this.setTitle("Importar Projeto - PGDS");
		this.setWidth("600px");
		this.setHeight(null);
		this.setPosition("center");
		this.setSizable(true);

		GridDados grid = new GridDados();
		grid.setParent(this);
		
		Hbox hbox = new Hbox();
		textboxEndereco.setWidth("300px");
		textboxEndereco.setConstraint("no empty");
		textboxEndereco.setValue("http://192.168.1.103:8080/pgds");
		textboxEndereco.setParent(hbox);
		
		atualizar.setImage("/imagens/update.png");
		atualizar.setParent(hbox);
		atualizar.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				try{
					preencherComboRepositorio();
				}catch (Exception e) {
					e.printStackTrace();
					Messagebox.show("Endereço inválido. Não foi possível conectar ao PGDS.");
				}
			}
		});
		
		grid.adicionarLinha("Endereço", hbox);

		comboRepositorio.setReadonly(true);
		comboRepositorio.setWidth("100%");
		grid.adicionarLinha("Repositório", comboRepositorio);
		
		textboxProjeto.setWidth("100%");
		textboxProjeto.setConstraint("no empty");
		grid.adicionarLinha("Projeto", textboxProjeto);
		
		Hbox hbox2 = new Hbox();
		hbox2.setWidth("100%");
		hbox2.setPack("end");
		hbox2.setParent(this);
		
		Button importar = new Button("Importar");
		importar.setParent(hbox2);
		importar.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event arg0) throws Exception {
				try{
					aplImportar.recuperarDados(textboxEndereco.getValue(), (String) comboRepositorio.getSelectedItem().getValue(), textboxProjeto.getValue());
					Messagebox.show("Projeto importado com sucesso.");
					janela.onClose();
				}catch(Exception e){
					Messagebox.show("Erro ao importar projeto.");
					e.printStackTrace();
					return;
				}
			}
		});
		
		//preencherComboRepositorio();
	}

	public void preencherComboRepositorio() {
		comboRepositorio.getChildren().clear();

		List<String> lista = aplImportar.recuperarRepositorios(textboxEndereco.getValue());
		for (String string : lista) {
			Comboitem item = new Comboitem();

			item.setValue(string);
			item.setLabel(string);

			item.setParent(comboRepositorio);
		}

		if (lista.size() > 0)
			comboRepositorio.setSelectedIndex(0);
	}

}
