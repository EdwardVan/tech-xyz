package tech.edwardvan.msspringcloudclient;

import org.springframework.cloud.openfeign.FeignClient;
import tech.edwardvan.msspringcloudproductcommon.api.ProductApi;

/**
 * @author EdwardVan
 */
@FeignClient(name = "product-service")
public interface ProductClient extends ProductApi {
}
