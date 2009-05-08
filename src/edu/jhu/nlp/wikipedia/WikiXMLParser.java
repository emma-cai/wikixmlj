package edu.jhu.nlp.wikipedia;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

import org.apache.tools.bzip2.CBZip2InputStream;
import org.xml.sax.InputSource;

/**
 * 
 * 
 * @author Delip Rao
 *
 */
public abstract class WikiXMLParser {
	
	private String wikiXMLFile = null;
	
	public WikiXMLParser(String fileName){
		wikiXMLFile = fileName;
	}
	
	/**
	 * Set a callback handler. The callback is executed every time a
	 * page instance is detected in the stream. Custom handlers are
	 * implementations of {@link PageCallbackHandler}
	 * @param handler
	 * @throws Exception
	 */
	public abstract void setPageCallback(PageCallbackHandler handler) throws Exception;
	
	/**
	 * The main parse method.
	 * @throws Exception
	 */
	public abstract void parse() throws Exception;
	
	/**
	 * 
	 * @return An InputSource created from wikiXMLFile
	 * @throws Exception
	 */
	protected InputSource getInputSource() throws Exception
	{
		BufferedReader br = null;
		
		if(wikiXMLFile.endsWith(".gz")) {
			br = new BufferedReader(new InputStreamReader(
					new GZIPInputStream(new FileInputStream(wikiXMLFile))));
		} else if(wikiXMLFile.endsWith(".bz2")) {
			FileInputStream fis = new FileInputStream(wikiXMLFile);
			byte [] ignoreBytes = new byte[2];
			fis.read(ignoreBytes); //"B", "Z" bytes from commandline tools
			br = new BufferedReader(new InputStreamReader(
					new CBZip2InputStream(fis)));
		} else {
			br = new BufferedReader(new InputStreamReader( 
					new FileInputStream(wikiXMLFile)));
		}
		
		return new InputSource(br);
	}
}