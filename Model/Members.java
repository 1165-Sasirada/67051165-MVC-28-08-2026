package Model;
public class Members {
	private static int member_count = 0;

	private String id;
	private String name;
	private Role role;
	private boolean active;

	public Members(String name, Role role) {
		member_count++;

		this.id = nextId();
		this.name = name;
		this.role = role;
		this.active = true;
	}

	private static String nextId() {
		String new_id = String.format("M%02d", member_count);
		return new_id;
	}

	public String get_id() {
		return this.id;
	}

	public String get_name() {
		return this.name;
	}

	public Role get_role() {
		return this.role;
	}

	public boolean get_status() {
		return this.active;
	}

	public void deactivate() {
		this.active = false;
	}
}
