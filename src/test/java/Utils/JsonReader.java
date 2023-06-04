package Utils;

import GithubAPIConsumer.Branch;
import GithubAPIConsumer.GithubRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonReader {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static final String SRC_TEST_JAVA_RESOURCE = "src/test/java/resource/";


    private static List<Branch> readBranchFromFile(String filename) throws IOException {
        return objectMapper.readValue(new File(SRC_TEST_JAVA_RESOURCE + filename),
                new TypeReference<>(){});
    }
    private static List<GithubRepository> readRepositoryFromFile(String filename) throws IOException {
        return  objectMapper.readValue(new File(SRC_TEST_JAVA_RESOURCE + filename),
                new TypeReference<>(){});
    }

    public static List<Branch> readBranchesFromFile() throws IOException {
        return readBranchFromFile("branches.json");
    }

    public static List<Branch> readExampleBranchFromFile() throws IOException {
        return readBranchFromFile("exampleBranch.json");
    }

    public static List<GithubRepository> readRepositoriesFromFile() throws IOException {
        return readRepositoryFromFile("repositories.json");
    }

    public static List<GithubRepository> readExampleRepositoryFromFile() throws IOException {
        return readRepositoryFromFile("exampleRepository.json");
    }
}
