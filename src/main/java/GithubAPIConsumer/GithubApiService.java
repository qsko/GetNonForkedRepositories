package GithubAPIConsumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GithubApiService {

    public static final String HTTPS_API_GITHUB_COM = "https://api.github.com/";
    public static final String REPOS = "repos";
    public static final String BRANCHES = "branches";
    public static final String USERS = "users";
    public static final String SLASH = "/";

    private final RestTemplate restTemplate;

    public GithubApiService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    private List<GithubRepository> getUserRepositories(String user) throws JsonProcessingException {

        String uri = HTTPS_API_GITHUB_COM + USERS + SLASH + user + SLASH + REPOS;
        String response =
                restTemplate.getForObject(uri, String.class);

       List<GithubRepository> repositories = new ObjectMapper()
                .readValue(response, new TypeReference<List<GithubRepository>>(){});

       return repositories;
    }

    private List<Branch> getRepositoryBranches(String user, String repo) throws JsonProcessingException {

        String uri = HTTPS_API_GITHUB_COM + REPOS + SLASH + user + SLASH + repo + SLASH + BRANCHES;
        String response =
                restTemplate.getForObject(uri, String.class);

        List<Branch> branches = new ObjectMapper()
                .readValue(response, new TypeReference<List<Branch>>(){});

        return branches;
    }

    public List<GithubRepository> getNotForkedRepository(String user) throws JsonProcessingException {

        //get all repositories for user
        List<GithubRepository> repositories = getUserRepositories(user);
        //filter repositories based on boolean property fork
        List<GithubRepository> filteredRepositories = repositories.stream().filter(r -> !r.isFork()).toList();
        //for all repositories find corresponding branches and add them
        for(GithubRepository repo : filteredRepositories){
            List<Branch> repoBranches = getRepositoryBranches(user, repo.getName());
            repo.setBranches(repoBranches);
        }
        return filteredRepositories;
    }
}
