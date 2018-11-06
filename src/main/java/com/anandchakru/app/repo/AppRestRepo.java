package com.anandchakru.app.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.anandchakru.app.entity.App;

@RepositoryRestResource(path = "app")
public interface AppRestRepo extends PagingAndSortingRepository<App, Long> {
	/*
	@RestResource(path = "by", rel = "by") //	http://localhost:8078/repo/app/search/by?name=jrvite
	List<App> findByAppName(@Param("name") String appName);
	*/}