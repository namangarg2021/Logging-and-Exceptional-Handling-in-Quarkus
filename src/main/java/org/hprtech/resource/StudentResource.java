package org.hprtech.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.hprtech.entity.Student;
import org.hprtech.exception.BusinessException;
import org.hprtech.exception.TechnicalException;
import org.hprtech.repository.StudentRepository;

import java.net.URI;
import java.util.List;

@Path("")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentResource {
    @Inject
    StudentRepository studentRepository;

    @POST
    @Path("addStudent")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addStudent(@RequestBody Student student) throws BusinessException {
        if(student == null || student.getName()==null || student.getName().length()==0)
        {
            throw new BusinessException(Response.Status.BAD_REQUEST.getStatusCode(),"Student Name cannot be empty...");
        }

        studentRepository.persist(student);
        if (studentRepository.isPersistent(student)) {
            return Response.created(URI.create("/student/" + student.getStudentId())).build();
        } else {
            return Response.ok(Response.Status.BAD_REQUEST).build();
        }
    }
    @GET
    @Path("student/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentById(@PathParam("id")Long id) throws BusinessException {
        Student student = studentRepository.findById(id);
        if (student == null) {
            throw new BusinessException(Response.Status.NOT_FOUND.getStatusCode(),"Student with Id: "+id+" not exist in DB");
            //return Response.ok(Response.status(Response.Status.NOT_FOUND)).build();
        } else {
            return Response.ok(student).build();
        }
    }

    @GET
    @Path("getAllStudent")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentList(){
        List<Student> studentList =studentRepository.listAll();
        return Response.ok(studentList).build();
    }


    @GET
    @Path("test_api/{i}")
    public Response testApi(@PathParam("i")int i) throws TechnicalException {
        try{
            int out = 8/i;
            return Response.ok("Output after dividing "+out).build();
        }catch(Exception e){
            throw new TechnicalException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),e.getMessage());
        }
        //return Response.ok("").build();
    }
}
