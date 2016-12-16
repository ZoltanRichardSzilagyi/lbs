package hu.zrs.lbs.api.command;

public class Command<P> {

	private final P request;

	private final Integer id;

	public Command(final P request, final Integer id) {
		this.request = request;
		this.id = id;
	}

	public P getRequest() {
		return request;
	}

	public Integer getId() {
		return id;
	}

}
