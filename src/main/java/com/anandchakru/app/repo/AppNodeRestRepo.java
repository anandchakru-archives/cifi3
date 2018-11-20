package com.anandchakru.app.repo;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import com.anandchakru.app.entity.AppNode;

@RepositoryRestResource(path = "app-node")
public interface AppNodeRestRepo extends PagingAndSortingRepository<AppNode, Long> {
	@RestResource(path = "byappid", rel = "byappid") //	http://localhost:8078/repo/app-node/search/byappid?appId=1
	List<AppNode> findByApp_appId(@Param("appId") Long appId);
	@RestResource(path = "byids", rel = "byids") //	http://localhost:8078/repo/app-node/search/byids?appId=1&appNodeId=2
	AppNode findByApp_appIdAndAppNodeId(@Param("appId") Long appId, @Param("appNodeId") Long appNodeId);
	@RestResource(path = "byname", rel = "byname") //	http://localhost:8078/repo/app-node/search/byname?appname=cifi3&nodename=akash001
	AppNode findByApp_appNameAndNodeName(@Param("appname") String appName, @Param("nodename") String nodeName);
	@RestResource(path = "byidandselfurl", rel = "byidandselfurl") //	http://localhost:8078/repo/app-node/search/byidandselfurl?appId=1&selfurl=akash001
	AppNode findByApp_appIdAndSelfUrl(@Param("appId") Long appId, @Param("selfurl") String selfUrl);
	@RestResource(path = "byselfurl", rel = "byselfurl") //	http://localhost:8078/repo/app-node/search/byselfurl?selfurl=akash001
	AppNode findBySelfUrl(@Param("selfurl") String selfUrl);
}