package me.tatarka.sres.test
import me.tatarka.sres.ast.Attribute
import me.tatarka.sres.ast.Include
import me.tatarka.sres.ast.RootView
import me.tatarka.sres.ast.View
import spock.lang.Specification

import static me.tatarka.sres.test.helpers.SpecHelper.parse
/**
 * Created by evan on 4/22/14.
 */
class ParseSresSpec extends Specification {
    def "parses a single view"() {
        expect:
        parse('TextView') == RootView.of('TextView').build()
    }

    def "parses a view's width/height arguments"() {
        expect:
        parse("TextView($width, $height)") ==
                RootView.of('TextView')
                        .attribute(Attribute.LAYOUT_WIDTH, width)
                        .attribute(Attribute.LAYOUT_HEIGHT, height)
                        .build()

        where:
        width   | height
        'match' | 'wrap'
        '100dp' | '20px'
    }

    def "parses a view with a custom subclass"() {
        expect:
        parse('MyView<TextView') == RootView.of('TextView').subclasses('MyView').build()
    }

    def "parses an id"() {
        expect:
        parse('TextView { @+id/my_id }') ==
                RootView.of('TextView')
                        .attribute(Attribute.ID, '@+id/my_id')
                        .build()
    }

    def "parses an arbitrary attribute"() {
        expect:
        parse("TextView { $key = $value }") ==
                RootView.of('TextView')
                        .attribute(key, value)
                        .build()

        where:
        key        | value
        'text'     | 'Test'
        'textSize' | '4sp'
        'style'    | '@style/a_style'
        'class'    | 'test.Test'
    }

    def "parses a quoted attribute"() {
        expect:
        parse("TextView { $key = \"$value\" }") ==
                RootView.of('TextView')
                        .attribute(key, value)
                        .build()

        where:
        key        | value
        'text'     | 'Multiple Words'
    }

    def "parses nested views"() {
        expect:
        parse('FrameLayout { TextView }') ==
                RootView.of('FrameLayout')
                        .child(View.of('TextView'))
                        .build()
    }

    def "parses an include"() {
        expect:
        parse('LinearLayout { include(@layout/test) }') ==
                RootView.of('LinearLayout')
                        .child(Include.of('@layout/test'))
                        .build()
    }

    def "parses a data bind"() {
        expect:
        parse("TextView { bind:class = Test\nbind:text = $bind }") ==
                RootView.of('TextView')
                        .bindClass('Test')
                        .bind('text', bind)
                        .build()

        where:
        bind << ['text', 'text()']
    }
}
