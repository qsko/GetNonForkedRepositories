package GithubAPIConsumer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Branch {
    private String name;
    private String sha;

    @JsonProperty("commit")
    private void unpackNested(Map<String,Object> commit) {
        this.sha = (String)commit.get("sha");
    }

    public String getName() {
        return name;
    }

    public String getSha() {
        return sha;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "name='" + name + '\'' +
                ", sha='" + sha + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Branch branch = (Branch) o;
        return Objects.equals(name, branch.name) && Objects.equals(sha, branch.sha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sha);
    }
}
