package com.radams.util;
import com.radams.entity.Skill;
import com.radams.persistence.GenericDao;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Path("/skills")
public class SkillsREST {
    private GenericDao skillDao = new GenericDao(Skill.class);
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public Response getAllSkills() {
        // Return all skills
        List<Skill> skills = (List<Skill>) skillDao.getAll();
        String output = "Skills: ";
        for(Skill skill : skills) {
            output += skill.toString();
        }

        return Response.status(200).entity(output).build();
    }

    @GET
    @Produces("text/plain")
    @Path("{id}")
    public Response getSkillByQuery(@PathParam("id") String id) {
        Skill skill = (Skill) skillDao.getById(Integer.parseInt(id));
        String output = skill.toString();

        return Response.status(200).entity(output).build();
    }
}
