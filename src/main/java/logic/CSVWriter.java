package logic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.text.SimpleDateFormat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

import domain.csv.Entity;

public class CSVWriter {

    private static Logger LOGGER = Logger.getLogger(CSVWriter.class);

    private static final String FILE_PATTERN = "goeuro_test_%s_%s.csv";
    private static final char CHAR_SEPARATOR = ',';
    private static final String[] HEADER = {"id", "name", "type", "latitude", "longitude"};

    private static final Function<Entity, String[]> ENTITY_TO_STRING_ARRAY = new Function<Entity, String[]>() {
        @Override
        public String[] apply(final Entity entity) {
            if (entity == null) {
                return new String[0];
            }

            String[] result = new String[5];
            int index = 0;
            result[index++] = entity.getId();
            result[index++] = entity.getName();
            result[index++] = entity.getType();
            result[index++] = entity.getLatitude();
            result[index++] = entity.getLongitude();

            return result;
        }
    };

    private static String buildFileName(final String fileNameIdentifier) {
        return String.format(FILE_PATTERN, fileNameIdentifier, new SimpleDateFormat("yyyyMMddHHmmssS").format(
                    new Date()));
    }

    public static void write(final List<Entity> entities, final String fileNameIdentifier) throws IOException {
        write(entities, new File(buildFileName(fileNameIdentifier)));
    }

    @VisibleForTesting
    static void write(final List<Entity> entities, final File csvFile) throws IOException {
        au.com.bytecode.opencsv.CSVWriter writer = null;
        try {
            writer = new au.com.bytecode.opencsv.CSVWriter(new FileWriter(csvFile), CHAR_SEPARATOR);

            writer.writeNext(HEADER);

            List<String[]> csvStringArrays = Lists.transform(entities, ENTITY_TO_STRING_ARRAY);

            for (String[] csvStringArray : csvStringArrays) {
                LOGGER.debug(String.format("Persisting entity %s", Arrays.toString(csvStringArray)));
            }

            writer.writeAll(csvStringArrays);

        } finally {
            if (writer != null) {
                writer.close();
            }
        }

    }

}
