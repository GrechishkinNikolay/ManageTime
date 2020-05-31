package com.example.managetime.Model.dao;

public class ProjectDAO {
    public ProjectDAO() {
    }

    public List<Project> getProjectsByCommunityId(int communityId) {
        return DBManager.getProjectsByCommunityId(communityId);
    }

    public void addProject(int communityId, String nameProject, String descriptionProject) {
        DBManager.addProject(communityId, nameProject, descriptionProject);
    }
}
