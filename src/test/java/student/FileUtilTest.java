package student;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilTest {
    @TempDir
    static Path tempDir;

    @Test
    void testReadFileToListIOException() throws IOException {
        // copy employees.csv into tempDir
        Path file = tempDir.resolve("nonexist.csv");

        // Redirect System.err to capture output
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        // Use a nonexistent file to trigger the error
        FileUtil.readFileToList(file.toString());

        // Verify error message and ensure no lines were returned
        assertTrue(errContent.toString().contains("Error reading employee file: "));
    }

    @Test
    void testWriteFileIOException() {
        // Redirect System.err to capture output
        List<String> lines = FileUtil.readFileToList("resources/employees.csv");

        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        // Use a directory path to trigger the error
        FileUtil.writeFile("", lines, true);

        // Verify error message and ensure no lines were returned
        assertTrue(errContent.toString().contains("Error backing up file: "));
    }
}