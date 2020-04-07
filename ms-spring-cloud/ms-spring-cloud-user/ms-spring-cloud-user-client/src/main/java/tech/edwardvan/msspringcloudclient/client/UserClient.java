package tech.edwardvan.msspringcloudclient.client;

import org.springframework.cloud.openfeign.FeignClient;
import tech.edwardvan.msspringcloudusercommon.api.UserApi;

/**
 * @author EdwardVan
 */
@FeignClient(name = "user-service-provider")
public interface UserClient extends UserApi {
}
