package controllers;

import GithubAPIConsumer.GithubApiService;
import GithubAPIConsumer.GithubRepository;
import MyError.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class RepositoryController {

    @Autowired
    private GithubApiService githubApiService;

    @GetMapping(value = "/repos")
    @ResponseBody
    public List<GithubRepository> getUserNotForkedRepositories(@RequestParam String username,
                                                               @RequestHeader(value = "Accept") String acceptHeader) throws JsonProcessingException, NotAcceptableException {

        if(acceptHeader.contains(MediaType.APPLICATION_XML_VALUE)){
            throw new NotAcceptableException(HttpStatus.NOT_ACCEPTABLE.toString());
        }
        return githubApiService.getNotForkedRepository(username);
    }
}
