package AutomationTests.testClients;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UsersModel {
    @JsonProperty("meta")
    private String meta;
    @JsonProperty("data")
    private ModelClient data;
}
