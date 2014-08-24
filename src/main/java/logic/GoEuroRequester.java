package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import com.google.gson.Gson;

import domain.json.Entity;

public class GoEuroRequester {

    private static Logger LOGGER = Logger.getLogger(GoEuroRequester.class);

    private static final String QUERY_URL = "http://api.goeuro.com/api/v2/position/suggest/en/";

    public static List<Entity> query(final String stringToSearch) {

        Preconditions.checkArgument(!Strings.isNullOrEmpty(stringToSearch));

        final String jsonString;
        try {
            jsonString = fetchStringFromUrl(new URL(QUERY_URL + URLEncoder.encode(stringToSearch, "UTF-8")));
        } catch (IOException e) {
            throw new RuntimeException(String.format("Unable to query goeuro for '%s'", stringToSearch), e);
        }

        return stringToJson(jsonString);
    }

    @VisibleForTesting
    static List<Entity> stringToJson(final String jsonString) {
        if (Strings.isNullOrEmpty(jsonString)) {
            return new ArrayList<>();
        }

        final Entity[] jsonArray = new Gson().fromJson(jsonString, Entity[].class);

        return Arrays.asList(Objects.firstNonNull(jsonArray, new Entity[0]));
    }

    private static String fetchStringFromUrl(final URL url) throws IOException {

        final HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        LOGGER.debug(String.format("Requesting url [%s]", url.toString()));

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            final StringBuilder response = new StringBuilder();

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }

            return response.toString();
        }
    }
}
