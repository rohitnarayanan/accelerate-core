package accelerate.commons.util;

import static accelerate.commons.constant.CommonTestConstants.BEAN_ID_FIELD;
import static accelerate.commons.constant.CommonTestConstants.BEAN_ID_VALUE;
import static accelerate.commons.constant.CommonTestConstants.BEAN_NAME_FIELD;
import static accelerate.commons.constant.CommonTestConstants.BEAN_NAME_VALUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import accelerate.commons.exception.ApplicationException;

/**
 * {@link Test} class for {@link XMLUtils}
 * 
 * @version 1.0 Initial Version
 * @author Rohit Narayanan
 * @since June 26, 2019
 */
@SuppressWarnings("static-method")
public class XMLUtilsTests {
	/**
	 * {@link Document} instance for this test class
	 */
	public static final Document testXMLDocument = XMLUtils
			.loadXML("classpath:/accelerate/commons/util/XMLUtilsTests.xml");

	/**
	 * Test method for {@link XMLUtils#loadXML(String)}.
	 */
	@Test
	void testLoadXML() {
		assertThrows(ApplicationException.class, () -> XMLUtils.loadXML(null));

		assertEquals("TestDataBean", XMLUtils.loadXML("classpath:/accelerate/commons/util/XMLUtilsTests.xml")
				.getDocumentElement().getNodeName());
	}

	/**
	 * Test method for {@link XMLUtils#stringToXML(String)}.
	 */
	@Test
	void testStringToXML() {
		assertThrows(ApplicationException.class, () -> XMLUtils.stringToXML(null));

		assertEquals("TestDataBean", XMLUtils
				.stringToXML(StreamUtils.readInputStream("classpath:/accelerate/commons/util/XMLUtilsTests.xml"))
				.getDocumentElement().getNodeName());
	}

	/**
	 * Test method for {@link XMLUtils#serialzeXML(Document)}.
	 */
	@Test
	void testSerialzeXML() {
		assertThat(XMLUtils.serialzeXML(testXMLDocument)).contains("</TestDataBean>");
	}

	/**
	 * Test method for {@link XMLUtils#xPathNodeList(String, Node)}.
	 */
	@Test
	void testXPathNodeList() {
		assertThrows(ApplicationException.class, () -> XMLUtils.xPathNodeList("#", null));

		assertEquals(1, XMLUtils.xPathNodeList("/TestDataBean", testXMLDocument).getLength());
	}

	/**
	 * Test method for {@link XMLUtils#xPathNode(String, Node)}.
	 */
	@Test
	void testXPathNode() {
		assertThrows(ApplicationException.class, () -> XMLUtils.xPathNode("#", null));

		assertEquals(BEAN_NAME_FIELD,
				XMLUtils.xPathNode("/TestDataBean/" + BEAN_NAME_FIELD, testXMLDocument).getNodeName());
	}

	/**
	 * Test method for {@link XMLUtils#xPathNodeValue(String, Node)}.
	 */
	@Test
	void testXPathNodeValue() {
		assertThrows(ApplicationException.class, () -> XMLUtils.xPathNodeValue("#", null));

		assertEquals(BEAN_NAME_VALUE, XMLUtils.xPathNodeValue("/TestDataBean/" + BEAN_NAME_FIELD, testXMLDocument));
	}

	/**
	 * Test method for {@link XMLUtils#xPathNodeAttribute(String, String, Node)}.
	 */
	@Test
	void testXPathNodeAttribute() {
		assertThrows(ApplicationException.class, () -> XMLUtils.xPathNodeAttribute("#", null, null));

		assertEquals(BEAN_ID_VALUE, XMLUtils.xPathNodeAttribute("/TestDataBean", BEAN_ID_FIELD, testXMLDocument));
	}
}