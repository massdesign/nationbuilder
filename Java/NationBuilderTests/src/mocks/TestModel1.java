package mocks;

import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.Association.annotation.ManyToOne;
import nationbuilder.lib.Ruby.Association.annotation.OneToMany;
import nationbuilder.lib.Ruby.Association.annotation.OneToOne;
import nationbuilder.lib.Ruby.orm.BaseRubyModel;

/**
 * @author patrick.ekkel
 */
@Entity(tableName = "testmodel")
public class TestModel1 extends BaseRubyModel
{
	@OneToOne(mapIdTo = "test12345")
	private String a;
	private String b;
	private String c;
	@OneToMany(mapIdTo = "dikkelul")
	private String d;

	private String test12345;

	private int e;
	private int f;
	@OneToOne(mappedByClazz = TestModel2.class,mappedBy = "testmapping1")
	private TestModel2 testModel1;
	@OneToMany(mappedBy = "testmapping2")
	private TestModel2 testModel2;
	@ManyToOne(mappedBy = "testmapping3", mappedByClazz = TestModel2.class)
	private TestModel2 testModel3;


	public TestModel1() {

		this.a = "a";
		this.b = "b";
		this.c = "c";
		this.d = "d";


		this.e = 1;
		this.f = 2;
	}


	public String getA()
	{
		return a;
	}

	public void setA(String a)
	{
		this.a = a;
	}

	public String getB()
	{
		return b;
	}

	public void setB(String b)
	{
		this.b = b;
	}

	public String getC()
	{
		return c;
	}

	public void setC(String c)
	{
		this.c = c;
	}

	public String getD()
	{
		return d;
	}

	public void setD(String d)
	{
		this.d = d;
	}

	public String getTest12345()
	{
		return test12345;
	}

	public void setTest12345(String test12345)
	{
		this.test12345 = test12345;
	}

	public TestModel2 getTestModel1()
	{
		return testModel1;
	}

	public void setTestModel1(TestModel2 testModel1)
	{
		this.testModel1 = testModel1;
	}

	public TestModel2 getTestModel2()
	{
		return testModel2;
	}

	public void setTestModel2(TestModel2 testModel2)
	{
		this.testModel2 = testModel2;
	}
}
