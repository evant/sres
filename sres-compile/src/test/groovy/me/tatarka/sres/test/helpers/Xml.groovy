package me.tatarka.sres.test.helpers
import groovy.transform.Immutable
import org.custommonkey.xmlunit.Diff
import org.custommonkey.xmlunit.XMLUnit
/**
 * Created by evan on 5/3/14.
 */
@Immutable
class Xml {
    static { XMLUnit.ignoreWhitespace = true }

    String xml

    @Override
    boolean equals(Object other) {
        if (other == null || !(other instanceof Xml)) {
            return false
        }

        try {
            new Diff(xml, other.xml).similar()
        } catch (ignored) {
            false
        }
    }
}

