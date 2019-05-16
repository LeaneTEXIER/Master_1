package com.tp2;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Root resource (exposed at "myresource" path)
 * 
 * @authors Celine, Marine et Leane
 */
@Path("myresource")
public class MyResource {

	protected Ftp ftp;

	/**
	 * Instantiate my resource and associate a FTPSClient
	 */
	public MyResource() {
		this.ftp = new Ftp(ParamConf.mode, ParamConf.adress, ParamConf.port, ParamConf.username, ParamConf.password);
	}

	/**
	 * Get the current directory
	 * 
	 * @return String indicating the current directory
	 */
	@GET
	@Path("pwd")
	@Produces(MediaType.TEXT_PLAIN)
	public Response pwd() {
		return Response.ok(this.ftp.pwd(), MediaType.TEXT_PLAIN).build();
	}

	/**
	 * Show list of files
	 * 
	 * @return list of files
	 */
	@GET
	@Path("ls/{path: .*}")
	@Produces(MediaType.TEXT_HTML)
	public Response ls(@PathParam("path") String path) {
		return Response.ok(this.ftp.ls(path), MediaType.TEXT_HTML).build();
	}

	/**
	 * Shows list of files
	 * 
	 * @return list of files
	 */
	@GET
	@Path("ls")
	@Produces(MediaType.TEXT_HTML)
	public Response ls() {
		return Response.ok(this.ftp.ls(""), MediaType.TEXT_HTML).build();
	}

	/**
	 * Add a directory
	 * 
	 * @param directory the new directory
	 * @return the response
	 */
	@POST
	@Path("addDirectory")
	@Produces(MediaType.TEXT_PLAIN)
	public Response addDirectory(@QueryParam("directory") String directory) {
		return Response.ok(this.ftp.createDirectory(directory), MediaType.TEXT_PLAIN).build();
	}

	/**
	 * Delete a file
	 * 
	 * @param pathname the pathname of the file
	 * @return the response
	 */
	@DELETE
	@Path("deleteFile")
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteFile(@QueryParam("file") String pathname) {
		return Response.ok(this.ftp.deleteFile(pathname), MediaType.TEXT_PLAIN).build();
	}

	/**
	 * Delete a directory
	 * 
	 * @param pathname the pathname of the directory
	 * @return the response
	 */
	@DELETE
	@Path("deleteDirectory")
	@Produces(MediaType.TEXT_PLAIN)
	public Response delete(@QueryParam("directory") String pathname) {
		return Response.ok(this.ftp.deleteDirectory(pathname), MediaType.TEXT_PLAIN).build();
	}

	/**
	 * Rename a file
	 * 
	 * @param file the file
	 * @param name the new name
	 * @return the response
	 */
	@PUT
	@Path("rename")
	@Produces(MediaType.TEXT_PLAIN)
	public Response rename(@QueryParam("file") String file, @QueryParam("name") String name) {
		return Response.ok(this.ftp.rename(file, name), MediaType.TEXT_PLAIN).build();
	}

	/**
	 * Download a file
	 * 
	 * @param file the file
	 * @return the response
	 */
	@POST
	@Path("download")
	@Produces(MediaType.TEXT_PLAIN)
	public Response download(@QueryParam("file") String file, @QueryParam("path") String path) {
		return Response.ok(this.ftp.download(file, path), MediaType.TEXT_PLAIN).build();
	}

	/**
	 * Upload a file
	 * 
	 * @param file the file
	 * @return the response
	 */
	@POST
	@Path("upload")
	public Response up(@QueryParam("file") String file) {
		return Response.ok(this.ftp.upload(file), MediaType.TEXT_PLAIN).build();
	}

}
