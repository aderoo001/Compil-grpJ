package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.intermediate.*;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.temp.Label;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;

public class StreeWHILE extends Stree {

	private Stm stm;

	public StreeWHILE(Stree left, Stree right) throws StreeException, TypeException {
		super(left, right);
		this.stm = generateIntermediateCode();
	}

	private CJUMP.Op opSelector(String memo) {
		switch (memo) {
			case "!=":
				return CJUMP.Op.NE;
			case "<":
				return CJUMP.Op.LT;
			case ">":
				return CJUMP.Op.GT;
			case "<=":
				return CJUMP.Op.LE;
			case ">=":
				return CJUMP.Op.GE;
			default:
				return CJUMP.Op.EQ;
		}
	}

	@Override
	public Stm generateIntermediateCode() throws StreeException {
		Label label1 = new Label();
		Label label2 = new Label();
		Label label3 = new Label();
		// label1:
		// 	code du test
		//  goto label2 si == 0 sinon goto label3
		// label2:
		//  corps de la boucle
		//  goto label 1
		// label3:
		//  fin
		return new SEQ(
				new LABEL(label1),
				new SEQ(
						new CJUMP(
								this.opSelector(getLeft().getType().getName()),
								getLeft().getLeft().getExp(),
								getLeft().getRight().getExp(),
								label2,
								label3
						),
						new SEQ(
								new LABEL(label2),
								new SEQ(
										getRight().getStm(),
										new SEQ(
												new JUMP(label1),
												new LABEL(label3)
										)
								)
						)
				)
		);

	}

	@Override
	public boolean checkType() throws StreeException {
		Type typeLeft = getLeft().getType();
		if (typeLeft != null)
			return typeLeft.assertBoolean();
		else
			throw new StreeException("Type error while checking null types !");
	}

	@Override
	public Stm getStm() {
		return stm;
	}

}
