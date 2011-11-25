package ode._infraestruturaBase.ciu;

import org.zkoss.zul.Caption;
import org.zkoss.zul.Groupbox;

public class NucleoGroupbox extends Groupbox {

	private static final long serialVersionUID = 1L;
	
	private Caption caption;
	
	public NucleoGroupbox() {
		this.setMold("3d");
	}
	
	public NucleoGroupbox(String titulo) {
		this();
		this.caption = new Caption(titulo);
		this.caption.setParent(this);
	}
	
	public void setCaption(String titulo) {
		if(caption == null) {
			this.caption = new Caption();
			this.caption.setParent(this);
		}
		this.caption.setLabel(titulo);
	}
	
}
