package logic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import org.junit.Test;

import domain.json.Entity;

public class GoEuroRequesterIT {

    private static String fetchStringFromFile() throws IOException {

        System.out.println(System.getProperty("user.dir"));

        return readFromStream(new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/test.json"));
    }

    private static String readFromStream(final InputStream inputStream) throws IOException {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String inputLine;
            final StringBuilder response = new StringBuilder();

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }

            return response.toString();
        }
    }

    @Test
    public void testStringToJson() throws IOException {
        final String jsonString = fetchStringFromFile();

        List<Entity> entities = GoEuroRequester.stringToJson(jsonString);

        MatcherAssert.assertThat("Collection non empty", CollectionUtils.isNotEmpty(entities));
        MatcherAssert.assertThat("Collection size", entities.size(), Matchers.is(6));

    }
}
