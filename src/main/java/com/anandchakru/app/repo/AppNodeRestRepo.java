package com.anandchakru.app.repo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import com.anandchakru.app.entity.AppNode;

@RepositoryRestResource(path = "app-node")
public interface AppNodeRestRepo extends PagingAndSortingRepository<AppNode, Long> {
	@RestResource(path = "by", rel = "by") //	http://localhost:8078/repo/app-node/search/by?appId=1&appNodeId=2
	AppNode findByApp_appIdAndAppNodeId(@Param("appId") Long appId, @Param("appNodeId") Long appNodeId);
}