package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;

import fr.ubordeaux.deptinfo.compilation.lea.intermediate.CONST;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.Exp;
import fr.ubordeaux.deptinfo.compilation.lea.type.Tag;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeExpression;


public class StreeFLOAT extends Stree {

	private Float value;
	private Exp exp;
	private Type type;

	public StreeFLOAT(Float value) throws TypeException, StreeException {
		super();
		this.value = value;
		this.exp = new CONST(value);
		this.type = new TypeExpression(Tag.INTEGER);
	}

	@Override
	public String toString() {
		return "StreeFLOAT [value=" + value + "]";
	}

	@Override
	public Exp getExp(){
		return exp;
	}
	
	@Override
	public boolean checkType() {
		return true;
	}

	public Type getType() throws StreeException {
		return type;
	}

}
