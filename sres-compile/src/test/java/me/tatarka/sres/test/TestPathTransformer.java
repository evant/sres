package me.tatarka.sres.test;

import com.google.common.base.CaseFormat;
import me.tatarka.sres.util.PathTransformer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by evan on 3/7/14.
 */
@RunWith(JUnit4.class)
public class TestPathTransformer {
    @Test
    public void testMirror() {
        String expected = "b/a/c/test";
        String actual = PathTransformer.of("a/b/c/test").mirror("a/b", "b/a").toString();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testExtension() {
        String expected = "a/test.txt";
        String actual = PathTransformer.of("a/test.exe").extension("txt").toString();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testFromNoExtension() {
        String expected = "a/test.txt";
        String actual = PathTransformer.of("a/test").extension("txt").toString();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testToNoExtension() {
        String expected = "a/test";
        String actual = PathTransformer.of("a/test.exe").extension("").toString();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void testChangeNameCase() {
        String expected = "a/ATest.test";
        String actual = PathTransformer.of("a/a_test.test").changeNameCase(CaseFormat.LOWER_UNDERSCORE, CaseFormat.UPPER_CAMEL).toString();

        assertThat(actual).isEqualTo(expected);
    }
}
