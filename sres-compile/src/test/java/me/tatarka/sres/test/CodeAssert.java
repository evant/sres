package me.tatarka.sres.test;

import com.google.testing.compile.JavaSourceSubjectFactory;
import org.fest.assertions.GenericAssert;
import org.junit.Assert;
import org.truth0.Truth;

/**
 * Created by evan on 2/27/14.
 */
public class CodeAssert extends GenericAssert<CodeAssert, Code> {
    /**
     * Creates a new <code>{@link org.fest.assertions.GenericAssert}</code>.
     *
     * @param selfType the "self type."
     * @param actual   the actual value to verify.
     */
    protected CodeAssert(Class<CodeAssert> selfType, Code actual) {
        super(selfType, actual);
    }

    public CodeAssert(Code actual) {
        super(CodeAssert.class, actual);
    }

    public static CodeAssert assertThat(Code actual) {
        return new CodeAssert(actual);
    }

    public CodeAssert isEqualTo(Code expected) {
        Truth.ASSERT.about(JavaSourceSubjectFactory.javaSource()).that(expected.getJavaFileObject())
                .compilesWithoutError();
        Truth.ASSERT.about(JavaSourceSubjectFactory.javaSource()).that(actual.getJavaFileObject())
                .compilesWithoutError();
        Assert.assertEquals(expected.code, actual.code);
        return this;
    }
}
