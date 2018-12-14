package com.trizzo.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trizzo.ppmtool.domain.Project;
import com.trizzo.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository; 
	
	public Project saveOrUpdateProject(Project project) {
		
		// Logic
		
		return projectRepository.save(project);
	}

}

