package assign.resources;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import assign.domain.Meeting;
import assign.domain.Project;
import assign.services.DBLoader;

@Path("/myeavesdrop")
public class ProjectResource {
	
	DBLoader dbLoader;
	
	public ProjectResource() {
		dbLoader = new DBLoader();		
	}
	
	@POST
	@Path("/projects")
	@Consumes(MediaType.APPLICATION_XML)
	public Response postProject(Project p) throws Exception {
		
		if(p.getDescription().equals("") || p.getName().equals("") || p.getName().trim().length() == 0 || p.getDescription().trim().length() == 0){
			return Response.status(400).build();
		}
		
		//need to add project to db using dbloader
		dbLoader.addProject(p);
		
		URI uri = new URI("http://localhost:8080/assignment5/myeavesdrop/projects/" + p.getId());
		return Response.created(uri).build();
	}
	
	@POST
	@Path("/projects/{projectId}/meetings")
	@Consumes(MediaType.APPLICATION_XML)
	public Response postMeeting(Meeting m, @PathParam("projectId") Long projectId) throws Exception{
		
		if(m.getName().equals("") || m.getYear() == null || m.getName().trim().length() == 0){
			return Response.status(400).build();
		}else if(!dbLoader.projectIdExists(projectId)){
			return Response.status(404).build();
		}
		
		dbLoader.addMeeting(m, projectId);
		
		URI uri = new URI("http://localhost:8080/assignment5/myeavesdrop/projects/" + projectId + "/meetings/" + m.getId());
		
		return Response.created(uri).build();
	}
	
	@GET
	@Path("/projects/{projectId}")
	@Produces("application/xml")
	public Response getProject(@PathParam("projectId") Long projectId) throws Exception {
		
		if(!dbLoader.projectIdExists(projectId)){
			return Response.status(404).build();
		}
		
		Project p = dbLoader.getProject(projectId);
		
		return Response.ok(p, MediaType.APPLICATION_XML).build();
	}
	
	@PUT
	@Path("/projects/{projectId}/meetings/{meetingId}")
	@Consumes(MediaType.APPLICATION_XML)
	public Response updateMeeting(@PathParam("projectId") Long projectId, @PathParam("meetingId") Long meetingId, Meeting m) throws Exception{
		
		if(m.getName().equals("") || m.getYear() == null || m.getName().trim().length() == 0){
			return Response.status(400).build();
		}else if(!dbLoader.projectIdExists(projectId) || !dbLoader.projectHasMeeting(meetingId, projectId)){
			return Response.status(404).build();
		}
		
		dbLoader.updateMeeting(projectId, meetingId, m);
		
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/projects/{projectId}")
	public Response deleteProject(@PathParam("projectId") Long projectId) throws Exception{
		
		if(!dbLoader.projectIdExists(projectId)){
			return Response.status(404).build();
		}
		
		dbLoader.deleteProject(projectId);
		
		return Response.ok().build();
		
	}
}