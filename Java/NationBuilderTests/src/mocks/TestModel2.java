package mocks;

import nationbuilder.lib.Ruby.orm.BaseRubyModel;
import nationbuilder.lib.Ruby.orm.ID;
import nationbuilder.lib.Ruby.orm.ReferenceMapping;

/**
 * @author patrick.ekkel
 */
public class TestModel2 extends BaseRubyModel
{

	private ReferenceMapping testModel1;

	public TestModel2() {
		ID id = new ID();
		id.setId("12345");
		testModel1 = new ReferenceMapping(id,TestModel1.class);
	}

	public ReferenceMapping getTestmodel1()
	{
		return testModel1;
	}

	public void setTestmodel1(ReferenceMapping testmodel1)
	{
		this.testModel1 = testmodel1;
	}

}
