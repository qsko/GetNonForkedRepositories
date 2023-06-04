package com.example.getrepositories;

import GithubAPIConsumer.Branch;
import GithubAPIConsumer.GithubApiService;
import GithubAPIConsumer.GithubRepository;
import Utils.JsonReader;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class GithubApiServiceTest {

    private final GithubApiService githubApiService = new GithubApiService(new RestTemplateBuilder());

    private Method getUserRepositoriesMethod() throws NoSuchMethodException {
        Method method = GithubApiService.class.getDeclaredMethod("getUserRepositories",
                String.class);
        method.setAccessible(true);
        return method;
    }

    private Method getRepositoryBranchesMethod() throws NoSuchMethodException {
        Method method = GithubApiService.class.getDeclaredMethod("getRepositoryBranches",
                String.class, String.class);
        method.setAccessible(true);
        return method;
    }

    @Test
    void shouldGetRepositories() throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<GithubRepository> returnedRepositories = (List<GithubRepository>) getUserRepositoriesMethod()
                .invoke(githubApiService,"octocat");
        List<GithubRepository> repositories = JsonReader.readRepositoriesFromFile();

        assertEquals(repositories,returnedRepositories);
    }

    @Test
    void shouldGetBranches() throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        List<Branch> returnedBranches = (List<Branch>) getRepositoryBranchesMethod()
                .invoke(githubApiService,"octocat", "Hello-World");
        List<Branch> expectedBranches = JsonReader.readBranchesFromFile();

        assertEquals(expectedBranches,returnedBranches);
    }
}
