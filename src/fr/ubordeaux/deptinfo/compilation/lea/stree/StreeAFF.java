package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.intermediate.*;
import fr.ubordeaux.deptinfo.compilation.lea.type.*;

public class StreeAFF extends Stree {

	private final Stm stm;
	private final Type type;

	public StreeAFF(Stree left, Stree right) throws StreeException, TypeException {
		super(left, right);
		this.stm = new MOVE(left.getExp(), right.getExp());
		this.type = new TypeExpression(Tag.SET);
	}

	@Override
	public Type getType() throws StreeException {
		return type;
	}

	@Override
	public Stm getStm(){
		return stm;
	}

	@Override
	public boolean checkType() throws StreeException {
		Type typeLeft = this.getLeft().getType();
		Type typeRight = this.getRight().getType();
		if ((typeLeft != null) && (typeRight != null))
			return typeLeft.assertEqual(typeRight);
		else
			throw new StreeException("Type error while checking null types !");

	}


}
