package com.trizzo.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trizzo.ppmtool.domain.Backlog;
import com.trizzo.ppmtool.domain.Project;
import com.trizzo.ppmtool.exceptions.ProjectIdException;
import com.trizzo.ppmtool.repositories.BacklogRepository;
import com.trizzo.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository; 
	
	@Autowired
	private BacklogRepository backlogRepository; 
	
	public Project saveOrUpdateProject(Project project) {
		
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			// will not accept null. Changed to 0L
			if(project.getId() == 0L) {
				Backlog backlog = new Backlog(); 
				project.setBacklog(backlog);
				backlog.setProject(project);
				backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			}
			
			if(project.getId() != 0L) {
				project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
			}
			
			return projectRepository.save(project);
		} 
		catch (Exception e) 
		{
			throw new ProjectIdException("Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already exists.");
		}
	}
	
	public Project findProjectByIdentifier(String projectId) 
	{
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

		if(project == null)
		{
			throw new ProjectIdException("Project ID '" + projectId + "' does not exist.");
		}
		
		return project;
	}
	
	public Iterable<Project> findAllProjects()
	{
		return projectRepository.findAll(); 
	}
	
	public void deleteProjectByIdentifier(String projectId)
	{
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		
		if(project == null) 
		{
			throw new ProjectIdException("Cannot delete project with ID '" + projectId + "'. This project does not exist");
		}
		
		projectRepository.delete(project);
	}

}

