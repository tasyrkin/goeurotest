package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.List;

import org.apache.commons.io.FileUtils;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.io.Files;

import domain.csv.Entity;
import domain.csv.EntityBuilder;

public class CSVWriterIT {

    private File tmpDir = null;

    @Before
    public void setUp() {
        tmpDir = Files.createTempDir();
    }

    @After
    public void tearDown() throws IOException {
        FileUtils.deleteDirectory(tmpDir);
    }

    @Test
    public void testWrite() throws IOException {

        final File csvFile = new File(tmpDir, "test_file.csv");

        //J-
        List<Entity> entities =
                Lists.newArrayList(
                        new EntityBuilder().withName("name0").build(),
                        new EntityBuilder().withName("name1").build(),
                        new EntityBuilder().withName("name2").build());
        //J+

        CSVWriter.write(entities, csvFile);

        MatcherAssert.assertThat("csv file", csvFile.exists(), Matchers.is(true));

        int lines = 0;
        try(BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            while (reader.readLine() != null) {
                lines++;
            }
        }

        MatcherAssert.assertThat("csv number lines", lines, Matchers.is(entities.size() + 1));

    }
}
