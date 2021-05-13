package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.*;
import fr.ubordeaux.deptinfo.compilation.lea.type.*;

public class StreePLUSAFF extends Stree {

	private final Stm stm;
	private final Type type;

	public StreePLUSAFF(Stree left, Stree right) throws TypeException, StreeException {
		super(left, right);
		this.stm = new MOVE(left.getExp(), (new StreePLUS(left, right)).getExp() );
		this.type = new TypeExpression(Tag.SET);
	}

	@Override
	public Stm getStm(){
		return stm;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public boolean checkType() throws StreeException {
		Type typeLeft = this.getLeft().getType();
		Type typeRight = this.getRight().getType();
		if ((typeLeft != null) && (typeRight != null)){
			return typeLeft.assertEqual(typeRight);
		}
		else{
			throw new StreeException("Type error while checking null types !");
		}
	}
}
