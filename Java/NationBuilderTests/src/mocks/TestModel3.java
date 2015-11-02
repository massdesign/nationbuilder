package mocks;

import java.util.ArrayList;
import java.util.List;
import nationbuilder.lib.Ruby.Association.annotation.OneToMany;
import nationbuilder.lib.Ruby.orm.BaseRubyModel;

/**
 * @author patrick.ekkel
 */
public class TestModel3 extends BaseRubyModel
{


	@OneToMany(mappedByClazz = TestModel2.class)
	private List<TestModel2> testmodels;

	public TestModel3() {

		this.testmodels = new ArrayList<>();
		this.testmodels.add(new TestModel2());
		this.testmodels.add(new TestModel2());
		this.testmodels.add(new TestModel2());
	}

	public List<TestModel2> getTestmodels()
	{
		return testmodels;
	}

	public void setTestmodels(List<TestModel2> testmodels)
	{
		this.testmodels = testmodels;
	}

}
