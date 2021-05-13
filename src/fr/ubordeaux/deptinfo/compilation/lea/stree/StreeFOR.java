package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.intermediate.SEQ;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.Stm;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;

public class StreeFOR extends Stree {

	private final Stm stm;

	public StreeFOR(Stree left, Stree stree) throws TypeException, StreeException {
		super(left, stree);
		this.stm = generateIntermediateCode();
	}

	@Override
	public Stm generateIntermediateCode() throws StreeException {
	    return new SEQ(
	    		getLeft().getStm(),
				getRight().getStm()
		);
    }

	@Override
	public boolean checkType() throws StreeException {
		Type typeLeft = getLeft().getType();
		Type typeRight = getRight().getType();
		if (typeLeft != null && typeRight != null)
			return true;
		else
			throw new StreeException("Type error while checking null types !");
	}

	@Override
	public Stm getStm() {
		return stm;
	}
}
