package hu.zrs.lbs.api.command;

public class Command<P> {

	private final P request;

	public Command(final P request) {
		this.request = request;
	}

	public P getRequest() {
		return request;
	}

}
