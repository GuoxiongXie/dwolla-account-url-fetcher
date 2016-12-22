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

    public static void main(String[] args) {
        Properties prop = new Properties();
        InputStream input = null;

        String accessToken = null;
        String dwollaApiClientBasePath = null;

        try {

            input = new FileInputStream("config.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            accessToken = prop.getProperty("accessToken");
            dwollaApiClientBasePath = prop.getProperty("dwollaApiClientBasePath");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

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

        }
    }

}
