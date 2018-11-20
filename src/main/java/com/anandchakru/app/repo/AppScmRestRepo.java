package com.anandchakru.app.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import com.anandchakru.app.entity.AppScm;

@RepositoryRestResource(path = "app-scm")
public interface AppScmRestRepo extends PagingAndSortingRepository<AppScm, Long> {
	@RestResource(path = "byScmAppId", rel = "byScmAppId") //	http://localhost:8078/repo/app-scm/search/byScmAppId?scmAppId=89962281
	AppScm findByScmAppId(@Param("scmAppId") String scmAppId);
	@RestResource(path = "byAppId", rel = "byAppId") //	http://localhost:8078/repo/app-scm/search/byAppId?appId=3
	AppScm findByApp_appId(@Param("appId") Long appId);
}