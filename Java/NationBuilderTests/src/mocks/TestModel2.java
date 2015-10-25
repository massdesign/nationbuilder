package mocks;

import nationbuilder.lib.Ruby.orm.BaseRubyModel;
import nationbuilder.lib.Ruby.orm.ID;
import nationbuilder.lib.Ruby.orm.ReferenceMapping;

/**
 * @author patrick.ekkel
 */
public class TestModel2 extends BaseRubyModel
{
	ReferenceMapping testmodel1;


	public TestModel2() {
		ID id = new ID();
		id.setId("12345");
		testmodel1 = new ReferenceMapping(id,TestModel1.class);
	}

}
