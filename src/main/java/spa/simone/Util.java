package spa.simone;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * @author Simone Spaccarotella {spa.simone@gmail.com}
 */
public class Util {

	public static void prova() throws ParserConfigurationException, SAXException, IOException {
		// parse an XML document into a DOM tree
		DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = parser.parse(new File("instance.xml"));

		// create a SchemaFactory capable of understanding WXS schemas
		SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

		// load a WXS schema, represented by a Schema instance
		Source schemaFile = new StreamSource(new File("mySchema.xsd"));
		Schema schema = factory.newSchema(schemaFile);

		// create a Validator instance, which can be used to validate an instance
		// document
		Validator validator = schema.newValidator();

		// validate the DOM tree

		validator.validate(new DOMSource(document));
	}

	public static String toXML(Object obj) {
		StringWriter writer = new StringWriter();
		try {
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(obj, writer);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return writer.toString();
	}

}
