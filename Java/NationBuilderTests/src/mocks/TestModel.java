package mocks;

import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.orm.BaseRubyModel;

/**
 * @author patrick.ekkel
 */
@Entity(tableName = "testmodel")
public class TestModel extends BaseRubyModel
{
	private String a;
	private String b;
	private String c;
	private String d;

	private int e;
	private int f;


	public TestModel() {

		this.a = "a";
		this.b = "b";
		this.c = "c";
		this.d = "d";


		this.e = 1;
		this.f = 2;
	}



}
