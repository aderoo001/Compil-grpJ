package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.intermediate.Exp;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.CONST;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.NAME;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.temp.Label;
import fr.ubordeaux.deptinfo.compilation.lea.type.Tag;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeExpression;

public class StreeCHAR extends Stree {

	private Integer value;
	private Exp exp;
	private Type type;

	public StreeCHAR(Integer value) throws TypeException, StreeException {
		super();
		this.value = value;
		int val = Math.round(value);
		this.exp = new CONST(val);
		this.type = new TypeExpression(Tag.CHAR);
	}

	@Override
	public String toString() {
		return "StreeCHAR [value=" + value + "]";
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
