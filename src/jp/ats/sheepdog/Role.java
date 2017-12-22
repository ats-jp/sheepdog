package jp.ats.sheepdog;

/**
 * このクラスは auth.jar でも使用されるため、他のクラスに依存しないようにする必要があります。
 */
public enum Role {

	SYSTEM("システム管理者"),

	ADMIN("管理者"),

	USER("利用者");

	private final String name;

	private Role(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
