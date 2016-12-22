import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.api.RootApi;
import io.swagger.client.model.CatalogResponse;
import io.swagger.client.model.HalLink;

public class DwollaAccountUrlFetcher {
    
    private static final String RESOURCE_NAME = "config.properties";
    private static final String ACCESS_TOKEN = "accessToken";
    private static final String BASE_PATH = "dwollaApiClientBasePath";

    public static void main(String[] args) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try(InputStream resourceStream = loader.getResourceAsStream(RESOURCE_NAME)) {
            props.load(resourceStream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        String accessToken = props.getProperty(ACCESS_TOKEN);
        String dwollaApiClientBasePath = props.getProperty(BASE_PATH);
        
        if (accessToken != null && dwollaApiClientBasePath != null) {
            ApiClient apiClient = new ApiClient();
            apiClient.setBasePath(dwollaApiClientBasePath);
            apiClient.setAccessToken(accessToken);

            RootApi rootApi = new RootApi(apiClient);
            try {
                CatalogResponse catalogResp = rootApi.root();
                HalLink accountHalLink = catalogResp.getLinks().get("account");
                String accountUrl = accountHalLink.getHref();
                System.out.println("The corresponding account url:");
                System.out.println(accountUrl);
            } catch (ApiException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {
            System.out.println("Please enter the values in config.properties");
        }
    }

}
