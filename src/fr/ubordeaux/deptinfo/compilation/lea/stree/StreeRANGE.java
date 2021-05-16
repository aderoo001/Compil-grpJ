package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.type.Tag;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeExpression;

public class StreeRANGE extends Stree {

	private final Type type;

	public StreeRANGE(Stree left, Stree right) throws TypeException, StreeException {
		super(left, right);
		this.type = new TypeExpression(Tag.RANGE, left.getType(), right.getType());
	}

	@Override
	public Type getType() throws StreeException {
		return this.type;
	}

	@Override
	public boolean checkType() throws StreeException {
		return true;
	}
}
