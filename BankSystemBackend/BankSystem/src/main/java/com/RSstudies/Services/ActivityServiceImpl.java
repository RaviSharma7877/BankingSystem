package com.RSstudies.Services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RSstudies.Repository.ActivityRepository;
import com.RSstudies.models.Account;
import com.RSstudies.models.Activity;

@Service
public class ActivityServiceImpl implements ActivityService {
	
	@Autowired
	private ActivityRepository activityRepo;

	@Override
	public void logActivity(Account account, String actionType, String actionDescription) {
	    // Create a new Activity object
	    Activity activity = new Activity();
	    activity.setAccount(account);
	    activity.setActionType(actionType);
	    activity.setActionDescription(actionDescription);
	    activity.setCreatedAt(LocalDateTime.now());
	    
	    // Save the activity log to the repository
	    activityRepo.save(activity);
	}

	@Override
	public List<Activity> getActivities(Account account) {
		// TODO Auto-generated method stub
		return activityRepo.findAll();
	}

}
