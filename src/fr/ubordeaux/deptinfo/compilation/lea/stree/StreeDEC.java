package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.intermediate.MOVE;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.Stm;
import fr.ubordeaux.deptinfo.compilation.lea.type.Tag;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeExpression;

public class StreeDEC extends Stree {

	public static final Boolean RIGHT = false;
	public static final Boolean LEFT = true;
	private Boolean rank;
	private final Stm stm;
	private final Type type;

	public StreeDEC(Stree left, Boolean rank) throws TypeException, StreeException {
		super(left);
		this.rank = rank;
		this.stm = generateIntermediateCode();
		this.type = new TypeExpression(Tag.SET);
	}

	@Override
	public Stm getStm() {
		return stm;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public Stm generateIntermediateCode() throws StreeException, TypeException {
		return new MOVE(
				getLeft().getExp(),
				new StreeMINUS(
						getLeft(),
						new StreeINTEGER(1)
				).getExp()
		);
	}

	@Override
	public boolean checkType() throws StreeException {
		Type typeLeft = this.getLeft().getType();
		if (typeLeft != null) {
			return typeLeft.assertEqual(new TypeExpression(Tag.INTEGER));
		}
		return false;
	}
}
