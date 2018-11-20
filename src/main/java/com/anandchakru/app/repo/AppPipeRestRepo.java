package com.anandchakru.app.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import com.anandchakru.app.entity.AppPipe;

@RepositoryRestResource(path = "app-pipe")
public interface AppPipeRestRepo extends PagingAndSortingRepository<AppPipe, Long> {
	@RestResource(path = "byAppId", rel = "byAppId") //	http://localhost:8078/repo/app-pipe/search/byAppId?byAppId=1
	AppPipe findByApp_appId(@Param("byAppId") Long byAppId);
}