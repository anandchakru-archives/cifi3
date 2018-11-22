package com.anandchakru.app.repo;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import com.anandchakru.app.entity.App;

@RepositoryRestResource(path = "app")
public interface AppRestRepo extends PagingAndSortingRepository<App, Long> {
	@RestResource(path = "byappname", rel = "byappname") //	http://localhost:8078/repo/app/search/byappname?appname=jrvite
	List<App> findByAppName(@Param("appname") String appName);
}