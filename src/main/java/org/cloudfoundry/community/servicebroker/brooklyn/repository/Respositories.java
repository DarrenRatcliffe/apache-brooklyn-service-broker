package org.cloudfoundry.community.servicebroker.brooklyn.repository;

import javax.ws.rs.core.Response;

import brooklyn.rest.client.BrooklynApi;
import brooklyn.rest.domain.ApplicationSummary;
import brooklyn.rest.domain.TaskSummary;

public class Respositories {

	public static void createRepositories(BrooklynApi brooklynApi) {
		String name = "service-broker-records";
		String bindingRepoName = "service-instance-binding-repository";
		String instanceRepoName = "service-instance-repository";
		
		if(!repositoryExists(brooklynApi, name)){
			Response response = brooklynApi.getApplicationApi().createFromForm(
				String.format("{ \"name\": \"%s\","
						+ "\"services\": [{"
						+ "\"type\": \"brooklyn.entity.basic.BasicApplication\","
						+ "\"brooklyn.children\":[{\"name\": \"%s\", \"type\": \"brooklyn.entity.basic.BasicEntity\"},"
						+ "{\"name\": \"%s\", \"type\": \"brooklyn.entity.basic.BasicEntity\"}]"
						+ "}],"
						+ "\"locations\":[\"localhost\"]"
						+ "}", name, bindingRepoName, instanceRepoName));
			TaskSummary entity = BrooklynApi.getEntity(response, TaskSummary.class);
		}
	}
	
	private static boolean repositoryExists(BrooklynApi brooklynApi, String name) {
		try {
			ApplicationSummary applicationSummary = brooklynApi.getApplicationApi().get(name);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
