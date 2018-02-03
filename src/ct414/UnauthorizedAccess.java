package ct414;

public class UnauthorizedAccess extends Exception {
	
	private String reason;

	public UnauthorizedAccess(String reason) {
		super(reason);
		this.reason = reason;
	}
	
	public String getReason(){
		return this.reason;
	}
}

