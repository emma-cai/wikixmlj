package edu.jhu.nlp.wikipedia;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * 
 * A SAX Parser for Wikipedia XML dumps.  This parser is event driven, so it
 * can't provide a page iterator.
 * 
 * @author Jason Smith
 *
 */
public class WikiXMLSAXParser extends WikiXMLParser {
	
	private XMLReader xmlReader;
	private PageCallbackHandler pageHandler = null;
		
	public WikiXMLSAXParser(String fileName){
		super(fileName);
		try {
			xmlReader = XMLReaderFactory.createXMLReader();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Set a callback handler. The callback is executed every time a
	 * page instance is detected in the stream. Custom handlers are
	 * implementations of {@link PageCallbackHandler}
	 * @param handler
	 * @throws Exception
	 */
	public void setPageCallback(PageCallbackHandler handler) throws Exception {
		pageHandler = handler;
	}
	
	/**
	 * The main parse method.
	 * @throws Exception
	 */
	public void parse()  throws Exception  {
		
		xmlReader.setContentHandler(new SAXPageCallbackHandler(pageHandler));
		xmlReader.parse(getInputSource());
	}
}