package com.RSstudies.Services;

import java.util.List;

import com.RSstudies.models.Account;
import com.RSstudies.models.Activity;

public interface ActivityService {
	void logActivity(Account account, String actionType, String actionDescription);
	List<Activity> getActivities(Account account);
}
