package spa.simone;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Date;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import spa.simone.mapping.Person;

/**
 * @author Simone Spaccarotella {spa.simone@gmail.com}
 */
public class Main {

	public static void main(String[] args) {
		Person person = new Person();
		person.setFirstName("Simone");
		person.setLastName("Spaccarotella");
		person.setBirthDate(new Date());
		try {
			toXML(person);
		} catch (JAXBException | SAXException e) {
			e.printStackTrace();
		}
	}

	public static void toXML(Object obj) throws JAXBException, SAXException {
		JAXBContext context = JAXBContext.newInstance(obj.getClass());
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.setProperty(Marshaller.JAXB_ENCODING, Charset.defaultCharset().displayName());
		m.setSchema(getSchema());
		m.marshal(obj, System.out);
	}

	public static Schema getSchema() throws SAXException {
		SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Source schemaFile = new StreamSource(new File("files/schema.xsd"));
		return factory.newSchema(schemaFile);
	}

}
