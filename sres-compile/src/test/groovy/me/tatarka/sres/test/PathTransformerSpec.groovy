package me.tatarka.sres.test

import com.google.common.base.CaseFormat
import me.tatarka.sres.util.PathTransformer
import spock.lang.Specification

/**
 * Created by evan on 5/3/14.
 */
class PathTransformerSpec extends Specification {
    def "mirrors a path from one base directory to another"() {
        expect:
        PathTransformer.of("$oldPath/test").mirror(oldPath, newPath).toString() == "$newPath/test"

        where:
        oldPath | newPath
        'old'   | 'new'
    }

    def "adds or replaces an extension"() {
        expect:
        PathTransformer.of("a/$oldPath").extension(ext).toString() == "a/$newPath"

        where:
        oldPath   | ext  | newPath
        'test.exe'| 'txt'| 'test.txt'
        'test'    | 'txt'| 'test.txt'
        'test.exe'| ''   | 'test'
    }

    def "changes the case of just the base file name"() {
        expect:
        PathTransformer.of("a/${oldPath}.txt")
                .changeNameCase(CaseFormat.LOWER_UNDERSCORE, CaseFormat.UPPER_CAMEL)
                .toString() ==
        "a/${newPath}.txt"

        where:
        oldPath | newPath
        'a_test'| 'ATest'
    }
}
