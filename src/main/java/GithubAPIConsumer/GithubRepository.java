package GithubAPIConsumer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubRepository {
    private String name;
    private String ownerName;
    @JsonIgnore
    private boolean isFork;
    private List<Branch> branches = null;

    @JsonProperty("owner")
    private void unpackNested(Map<String,Object> owner) {
        this.ownerName = (String)owner.get("login");
    }
    public String getName() {
        return name;
    }

    public String getOwnerName() {
        return ownerName;
    }

    @JsonIgnore
    public boolean isFork() {
        return isFork;
    }
    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    @Override
    public String toString() {
        return "GithubRepository{" +
                "name='" + name + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", isFork=" + isFork +
                ", branches=" + branches +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GithubRepository that = (GithubRepository) o;
        return isFork == that.isFork && Objects.equals(name, that.name) && Objects.equals(ownerName, that.ownerName) && Objects.equals(branches, that.branches);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, ownerName, isFork, branches);
    }
}
