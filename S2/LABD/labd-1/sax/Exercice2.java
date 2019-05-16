import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class Exercice2 extends DefaultHandler{	
	private float superficie;
	private int profondeur;
	private int profondeur_bloque;
	
	public void startDocument(){
		this.superficie = 0;
		this.profondeur = 0;
		this.profondeur_bloque = -1;
	}
	
	public void endDocument(){
		System.out.println();
	}
	
	public void startElement(String nameSpaceURI, 
							 String localName, 
							 String rawName, 
							 Attributes attributs) {
		String s_piece;
		this.profondeur++;
		if(localName.equals("maison")) {
			System.out.println("Maison "+ attributs.getValue("id")+":");
		}
		else if (((s_piece = attributs.getValue("surface-m2"))!=null) && (this.profondeur_bloque==-1 || this.profondeur<this.profondeur_bloque)) {
			this.superficie += Float.parseFloat(s_piece);
			this.profondeur_bloque = profondeur;
		}
	}
	
	public void endElement(	String nameSpaceURI, 
						   String localName, 
						   String rawName) {
		if(localName.equals("maison")) {
			System.out.println("    superficie totale : "+superficie+" m2");
			this.superficie = 0;
		}
		if(this.profondeur==this.profondeur_bloque) {
			this.profondeur_bloque=-1;
		}
		this.profondeur--;
	}
	
	public static void main(String[] args){
		try {
			XMLReader saxReader = XMLReaderFactory.createXMLReader();
			saxReader.setContentHandler(new Exercice2());
			saxReader.parse(args[0]);
		} catch (Exception t) {
			t.printStackTrace();
		}
	}
}
