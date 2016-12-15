package hu.zrs.lbs.api.project.execution;

public class Command<P> {

	private final P request;

	public Command(final P request) {
		this.request = request;
	}

	public P getRequest() {
		return request;
	}

}
