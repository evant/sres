package me.tatarka.sres.test
import groovy.xml.MarkupBuilder
import me.tatarka.sres.ast.Attribute
import me.tatarka.sres.ast.Include
import me.tatarka.sres.ast.RootView
import me.tatarka.sres.ast.View
import me.tatarka.sres.test.helpers.Xml
import spock.lang.Specification

import static me.tatarka.sres.test.helpers.SpecHelper.toXml
/**
 * Created by evan on 4/22/14.
 */
class LayoutGeneratorXmlSpec extends Specification {

    def "generates a single view"() {
        expect:
        toXml('test', RootView.of('TextView').build()) == xml{it.'test.Test'()}
    }

    def "generates a view with attributes"() {
        expect:
        toXml('test', RootView.of('TextView')
                .attribute(Attribute.LAYOUT_WIDTH, 'match')
                .attribute(Attribute.LAYOUT_HEIGHT, 'wrap')
                .build()) ==
        xml{it.'test.Test'('xmlns:android': androidNS,
                           'android:layout_width': 'match_parent',
                           'android:layout_height': 'wrap_content')}
    }

    def "generates a subclass view"() {
        expect:
        toXml('test', RootView.of('TextView').subclasses('test.MyView').build()) ==
        xml{it.'test.MyView'()}
    }

    def "generates a view with an id"() {
        expect:
        toXml('test', RootView.of('TextView').id('@+id/test').build()) ==
        xml{it.'test.Test'('xmlns:android': androidNS, 'android:id': '@+id/test')}
    }

    def "generates a view with a child"() {
        expect:
        toXml('test', RootView.of('FrameLayout').child(View.of('TextView')).build()) ==
        xml{it.'test.Test'(){'TextView'()}}
    }

    def "generates an include"() {
        expect:
        toXml('test', RootView.of('LinearLayout').child(Include.of('@layout/test')).build()) ==
        xml{it.'test.Test'(){
            'include'('layout':'@layout/test')
        }}
    }

    def "generates a binding"() {
        expect:
        toXml('test', RootView.of('TextView').bindClass('MyClass').bind('text', bind).build()) ==
        xml{it.'test.Test'('xmlns:bind': bindNs, 'bind:class': 'MyClass', 'bind:text': bind)}

        where:
        bind << ['text', 'text()']
    }

    String androidNS = 'http://schemas.android.com/apk/res/android'
    String bindNs = 'http://schemas.android.com/apk/lib/me.tatarka.sres'

    def Xml xml(Closure c) {
        StringWriter w = new StringWriter()
        c(new MarkupBuilder(w))
        return new Xml(w.toString())
    }
}
