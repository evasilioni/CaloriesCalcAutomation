package UnitTests;

import org.testng.annotations.Test;
import utilities.RandomEmailGenerator;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;


public class RandomEmailGeneratorTest {
    @Test
    public void test_create_new_user() {

        assertThat(RandomEmailGenerator.getEmail(), containsString("@example.com"));
    }

}
