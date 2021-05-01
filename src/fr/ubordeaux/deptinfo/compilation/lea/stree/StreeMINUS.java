package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.intermediate.BINOP;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.Exp;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;

public class StreeMINUS extends Stree {

	private Exp exp;
	private Type type;

	public StreeMINUS(Stree left, Stree right) throws StreeException, TypeException {
		super(left, right);
		this.exp = new BINOP(BINOP.Code.MINUS, left.getExp(), right.getExp());
	}

	public StreeMINUS(Stree left) throws StreeException, TypeException {
		super(left);
		this.exp = new BINOP(BINOP.Code.MINUS, left.getExp(), null);
	}

	@Override
	public Exp getExp(){
		return exp;
	}

	@Override
	public Type getType(){
		return new TypeExpression(Tag.BOOLEAN);
	}

	@Override
	public boolean checkType() throws StreeException {
		Type typeLeft = getLeft().getType();
		Type typeRight = getRight().getType();
		type = typeLeft;
		if ((typeLeft != null) && (typeRight != null))
			return typeLeft.assertEqual(typeRight);
		else if ((typeLeft != null))
			return typeLeft.assertBoolean(); // pas sure de assertBoolean ici
		else
			throw new StreeException("Type error while checking null types !");
	}

}
