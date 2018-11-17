package it.unibs.fp.mylib;
import java.io.*;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jdom2.*;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class XMLFileOperation {
	
	
	public static void scriviSUlDocumento(String nomeDocumento,int quantiPianeti,int quanteLune) {
		Document documento=new Document();
		Element radice=new Element("sole");
		documento.setRootElement(radice);
		for(int i=0;i<quantiPianeti;i++) {
			Element pianeta=new Element("pianeta");
			for(int j=0;j<quanteLune;j++) {
				Element luna=new Element("luna");
				Attribute massa=new Attribute("massa","0");
				Attribute coordinataX=new Attribute("coordinataX","0"); Attribute coordinataY=new Attribute("coordinataY","0");
				luna.setAttribute(massa); luna.setAttribute(coordinataX); luna.setAttribute(coordinataY);
				pianeta.addContent(luna);
			}
			Attribute massa=new Attribute("massa","0"); Attribute coordinataX=new Attribute("coordinataX", "0");
			Attribute coordinataY=new Attribute("coordinataY","0");
			pianeta.setAttribute(massa); pianeta.setAttribute(coordinataX); pianeta.setAttribute(coordinataY);
			radice.addContent(pianeta);
		}
		XMLOutputter fileDiUscita=new XMLOutputter(Format.getPrettyFormat());
		try {
			fileDiUscita.output(documento, new FileOutputStream(new File("./src/"+nomeDocumento)));
			System.out.println("Ho scritto sul file :)");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
