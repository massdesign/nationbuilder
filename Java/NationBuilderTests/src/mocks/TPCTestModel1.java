package mocks;

import nationbuilder.lib.Ruby.Association.annotation.Column;
import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.Association.annotation.ID;
import nationbuilder.lib.Ruby.Association.annotation.InhiritanceStrategy;
import nationbuilder.lib.Ruby.orm.BaseRubyModel;
import nationbuilder.lib.Ruby.orm.ReferenceMapping;

/**
 * @author patrick.ekkel
 */

// Table per class testmodel
@Entity(tableName = "tpc_testmodel1",strategy = InhiritanceStrategy.TablePerClass)
public class TPCTestModel1 extends BaseRubyModel
{
	public ReferenceMapping getTpctestmodel2_id()
	{
		return tpctestmodel2;
	}

	public void setTpctestmodel2_id(ReferenceMapping tpctestmodel2_id)
	{
		this.tpctestmodel2 = tpctestmodel2_id;
	}

	public String getSuperclass_testfield()
	{
		return superclass_testfield;
	}

	public void setSuperclass_testfield(String superclass_testfield)
	{
		this.superclass_testfield = superclass_testfield;
	}

	@ID()
	ReferenceMapping tpctestmodel2;
	@Column
	private String superclass_testfield;

	public TPCTestModel1() {
		this.superclass_testfield = "superclass_content";
	}


}
