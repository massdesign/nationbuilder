package mocks;

import nationbuilder.lib.Ruby.Association.annotation.Column;
import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.Association.annotation.ID;
import nationbuilder.lib.Ruby.Association.annotation.InhiritanceStrategy;
import nationbuilder.lib.Ruby.orm.ReferenceMapping;

/**
 * @author patrick.ekkel
 */
@Entity(tableName = "tpc_testmodel2",strategy = InhiritanceStrategy.TablePerClass)
public class TPCTestmodel2 extends TPCTestModel1
{
	@Column
	private String testfield_subclass;
	public TPCTestmodel2() {
		testfield_subclass = "subclass_content";
	}


}
