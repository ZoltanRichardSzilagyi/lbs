package hu.zrs.lbs.api.audit;

import java.time.LocalDateTime;

import hu.zrs.lbs.api.user.User;

public interface Auditable {

	public User getCreatedBy();

	public User getLasModifiedBy();

	public LocalDateTime getCreationDate();

	public LocalDateTime getLastModificationDate();

}
