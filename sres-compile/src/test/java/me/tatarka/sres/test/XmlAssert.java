package me.tatarka.sres.test;

import org.fest.assertions.GenericAssert;
import org.xml.sax.SAXException;

import java.io.IOException;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;

/**
 * Created by evan on 2/27/14.
 */
public class XmlAssert extends GenericAssert<XmlAssert, Xml> {
    /**
     * Creates a new <code>{@link org.fest.assertions.GenericAssert}</code>.
     *
     * @param selfType the "self type."
     * @param actual   the actual value to verify.
     */
    protected XmlAssert(Class<XmlAssert> selfType, Xml actual) {
        super(selfType, actual);
    }

    public XmlAssert(Xml actual) {
        super(XmlAssert.class, actual);
    }

    public static XmlAssert assertThat(Xml actual) {
        return new XmlAssert(actual);
    }

    public XmlAssert isEqualTo(Xml expected) {
        try {
            assertXMLEqual(expected.xml, actual.xml);

        } catch (SAXException e) {
            fail(e.getMessage(), e);
        } catch (IOException e) {
            fail(e.getMessage(), e);
        }
        return this;
    }
}
