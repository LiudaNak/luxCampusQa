package AutomationTests.tests;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import AutomationTests.testClients.ModelClient;

public interface UsersInterface {

    @GET("/public/v1/users/{id}")
    Call<ModelClient> getUser(@Path("id") String id,
                              @Header("Authorization") String token);
}
