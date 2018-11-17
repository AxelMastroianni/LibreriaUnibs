package it.unibs.fp.mylib;

import java.io.FileWriter;
import java.util.Vector;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;


public class Write {
	public Write() {
		
	}
	public boolean write(Vector<User> diary,String filename){
		System.out.println("Scrittura su file");
		XMLOutputFactory output = XMLOutputFactory.newInstance();
		XMLStreamWriter writer;
		try {
			writer = output.createXMLStreamWriter(new FileWriter(filename));

			writer.writeComment("data saved");
			writer.writeStartDocument("utf-8","1.0");
			writer.writeStartElement("diary");
			for(User i: diary){
				writer.writeStartElement("user"); // End User
				writer.writeAttribute("index",""+i.hashCode());
				writer.writeStartElement("name");
				writer.writeCharacters(i.getName());
				writer.writeEndElement();
				writer.writeStartElement("surname");
				writer.writeCharacters(i.getSurname());
				writer.writeEndElement();
				writer.writeEndElement(); // End User
			}
			writer.writeEndElement(); // End Diary
			writer.writeEndDocument(); //End Document
			writer.flush();
			writer.close();
			System.out.println("End!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.print("Vecchio, problema!");
			e.printStackTrace();
			return false;
		}
		return true;
}
}