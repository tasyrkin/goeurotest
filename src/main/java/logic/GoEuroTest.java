package logic;

import static com.google.common.base.Predicates.notNull;

import static domain.EntityFunctions.ENTITY_JSON_TO_CSV_NULL_IF_DATA_MISSING;

import java.io.IOException;

import java.util.List;

import org.apache.log4j.Logger;

import com.google.common.base.Preconditions;
import com.google.common.collect.FluentIterable;

import domain.csv.Entity;

public class GoEuroTest {

    private static Logger LOGGER = Logger.getLogger(GoEuroTest.class);

    public static void main(final String[] args) {

        Preconditions.checkArgument(args.length == 1, "Single string parameter is expected");

        final String searchableTerm = args[0];

        //J-
        final List<Entity> entities =
                FluentIterable
                        .from(GoEuroRequester.query(searchableTerm))
                        .transform(ENTITY_JSON_TO_CSV_NULL_IF_DATA_MISSING)
                        .filter(notNull())
                        .toList();
        //J+

        try {
            CSVWriter.write(entities, searchableTerm);
        } catch (IOException e) {
            LOGGER.error(String.format("Unable to write csv for searchable term '%s'", searchableTerm), e);
        }
    }
}
