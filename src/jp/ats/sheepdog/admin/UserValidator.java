package jp.ats.sheepdog.admin;

import jp.ats.liverwort.sql.Condition;
import jp.ats.liverwort.sql.ConditionFactory;
import jp.ats.liverwort.sql.binder.StringBinder;
import jp.ats.sheepdog.dataobjects.t_user;
import jp.ats.sheepdog.dataobjects.t_userDAO;
import jp.ats.webkit.dexter.Validator;

public class UserValidator implements Validator {

	private String value;

	@Override
	public String getName() {
		return "���[���A�h���X�d������";
	}

	@Override
	public String getDescription() {
		return "���[���A�h���X���d�����Ă��邩�������܂�";
	}

	@Override
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public boolean validate() {
		Condition condition = ConditionFactory.createCondition(
			t_user.mail_address,
			new StringBinder(value));
		return new t_userDAO().count(condition) == 0;
	}

	@Override
	public String getValidationMessage() {
		return value + " �͊��Ɏg�p����Ă��܂�";
	}

	@Override
	public void reset() {
		value = null;
	}
}
