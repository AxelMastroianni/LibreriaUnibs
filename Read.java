package it.unibs.fp.mylib;

import java.io.File;
import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.plaf.synth.SynthSpinnerUI;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

public class Read {
	/**
	 * E' la classe che ci è stata fornita
	 * @author Andrea Rossi poi l'abbiamo modificata
	 * @version 2.0
	 */
	private Vector<String> dati=new Vector<String>(); //nostra aggiunta per memorizzare i dati letti man mano
	public Read() {
		
	}
	/**
	 * 
	 * @param filename
	 * https://docs.oracle.com/javase/tutorial/jaxp/stax/example.html
	 */
	public void explore(String filename) {
		try {
			
			XMLInputFactory xmlif=XMLInputFactory.newInstance();
	        XMLStreamReader xmlr = xmlif.createXMLStreamReader(filename,
	                                   new FileInputStream(filename));
	        while(xmlr.hasNext()) {
	            switch(xmlr.getEventType()) {
	            case XMLStreamConstants.START_DOCUMENT:
	            	System.out.println("Start Read Doc "+filename);
	            	break;
	            case XMLStreamConstants.START_ELEMENT:{
	            	System.out.print("Tag "+xmlr.getLocalName()+"\t");
	            	for(int i=0;i<xmlr.getAttributeCount();i++)
	            		System.out.print(xmlr.getAttributeValue(i)+"\t");
	            	System.out.println();
	            	break;
	            }
	            case XMLStreamConstants.NOTATION_DECLARATION:
	            	System.out.println("Inside "+xmlr.getText());
	            	break;
	            case XMLStreamConstants.ATTRIBUTE:
	            	System.out.println("Attribute: "+xmlr.getAttributeCount());
	            case XMLStreamConstants.CHARACTERS:
	            	if(xmlr.getText().trim().length()>0)
	            		System.out.println(xmlr.getText());
	            	break;
	            default:
	            	break;
	            }
	            xmlr.next();
	        }
	    }catch(Exception e){
	    	System.err.println("Error detected");
	    	System.err.println(e.getMessage());
	    }
	}
	/**
	 * metodo da noi implementato, legge l'xml e scrive tutti i dati su 
	 * un file di testo è necessario passargli quanti tag ci sono in 
	 * ogni riga per poterlo leggere correttamente dopo(guarda se hai aperto
	 * il progetto ParsingTrenord)
	 * @param nomeDocumento
	 * @param filename
	 * @param numeroTag
	 */
	public void exploreAndWrite(String nomeDocumento, String filename, int numeroTag) {
		File nuovoFile=new File(nomeDocumento);
		int i=0;
		if(nuovoFile.exists()) {
			try {
			FileOutputStream outPutNuovoFile;
			outPutNuovoFile = new FileOutputStream(nuovoFile,true);
			PrintWriter scrivi=new PrintWriter(outPutNuovoFile);
				XMLInputFactory xmlif=XMLInputFactory.newInstance();
				XMLStreamReader xmlr=xmlif.createXMLStreamReader(filename,new FileInputStream(filename));
				while(xmlr.hasNext()) {
					if(xmlr.getEventType()==XMLStreamReader.CHARACTERS) {
						scrivi.print(xmlr.getText()+","); //scrive sul file e separa con virgola
						i++;
						if(i%numeroTag==0) { //va a capo ogni volta che legge una serie di tag
							scrivi.println();
						}
					}
					xmlr.next();
				}
				scrivi.close();
			}catch(XMLStreamException e) {
				System.err.println("Error Detected");
				System.err.println(e.getMessage());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else
			try {
				if(nuovoFile.createNewFile()) {
					exploreAndWrite(nomeDocumento, filename,numeroTag);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	/**
	 * da noi implementato(guarda se il progetto si chiama NuovoParsingTrenord)
	 * è necessario(funziona anche senza ma bisogna modificare il numero di tag)
	 *  che legga anche le stringhe vuote per caricare correttamente il Vector di dati
	 * @param filename
	 */
	public void esploraEMemorizza(String filename) {
		try {
			XMLInputFactory xmlif=XMLInputFactory.newInstance();
	        XMLStreamReader xmlr = xmlif.createXMLStreamReader(filename,
	                                   new FileInputStream(filename));
	        while(xmlr.hasNext() ) {
	            switch(xmlr.getEventType()) {	            	
	            case XMLStreamConstants.CHARACTERS: //mi interessano solo i testi tra i tag
	            	if(xmlr.getText().trim().length()>=0) //mi interessano anche le stringhe vuote
	            		dati.add(xmlr.getText()); //riempio un'ArrayList di stringhe con tutti i dati del file xml appena letto
	            	break;
	            default:
	            	break;
	            }
	            xmlr.next();
	        }
	        System.out.println("Array riempito correttamente");
	    }catch(Exception e){
	    	System.err.println("Error detected");
	    	System.err.println(e.getMessage());
	    }
	}
	//metodo che prova la funzionalità del metodo sopra
	public void stampaDati() { 
		for(String s: dati)
			System.out.println(s);
	}
	public Vector<String> getDati(){
		return dati;
	}
	//svuota l'ArrayList
	public void pulisciDati() {
		while(!dati.isEmpty())
			dati.remove(0);
	}
	
	public void leggiAttributi(String filename) {
		try {
			XMLInputFactory xmlif=XMLInputFactory.newInstance();
	        XMLStreamReader xmlr = xmlif.createXMLStreamReader(filename,
	                                   new FileInputStream(filename));
	        while(xmlr.hasNext()) {
	            switch(xmlr.getEventType()) {
	            case XMLStreamConstants.START_ELEMENT:{
	            	System.out.print("Tag "+xmlr.getLocalName()+"\t");
	            	for(int i=0;i<xmlr.getAttributeCount();i++)
	            		System.out.print(xmlr.getAttributeValue(i)+"\t");
	            	System.out.println();
	            	break;
	            }
	            default:
	            	break;
	            }
	            xmlr.next();
	        }
		}catch(Exception e) {
			System.out.println("Ho trovato un errore nella lettura del file");
		}
	}
}