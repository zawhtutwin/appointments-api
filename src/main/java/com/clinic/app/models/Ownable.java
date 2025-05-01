package com.clinic.app.models;

import java.util.List;

public interface Ownable {
	User getCreatedBy();
	List<User> getSharedWith();
}
