package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.intermediate.*;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.temp.Label;
import fr.ubordeaux.deptinfo.compilation.lea.type.Tag;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeExpression;

public class StreeFORCONT extends Stree {

	private final Stm stm;

	public StreeFORCONT(Stree left, Stree right) throws TypeException, StreeException {
		super(left, right);
		stm = generateIntermediateCode();
	}

	@Override
	public Type getType() {
		return new TypeExpression(Tag.VOID);
	}

	@Override
	public Stm getStm() {
		return stm;
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

		if (!this.getLeft()
				.getType()
				.getTag()
				.equals(Tag.BOOLEAN)) {
			return new SEQ(
					new LABEL(label2),
					new SEQ(
							getLeft().getStm(),
							new SEQ(
									getRight().getStm(),
									new SEQ(
											new JUMP(label1),
											new LABEL(label3)
									)
							)
					)
			);
		} else if (this.getRight()
				.getLeft()
				.getType()
				.getTag()
				.equals(Tag.SET)) {
			return new SEQ(
					new LABEL(label1),
					new SEQ(
							new CJUMP(
									this.opSelector(getLeft().getType().getName()),
									this.getLeft().getLeft().getExp(),
									this.getLeft().getRight().getExp(),
									label2,
									label3
							),
							getRight().getStm()
					)
			);
		} else {
			return new SEQ(
					new LABEL(label1),
					new SEQ(
							new CJUMP(
									this.opSelector(getLeft().getType().getName()),
									this.getLeft().getLeft().getExp(),
									this.getLeft().getRight().getExp(),
									label2,
									label3
							),
							new SEQ(
									getRight().getStm(),
									new SEQ(
											new JUMP(label1),
											new LABEL(label3)
									)
							)
					)
			);
		}
	}

	@Override
	public boolean checkType() throws StreeException {
		Type typeLeft = this.getLeft().getType();
		Type typeRight = this.getRight().getType();
		if (typeLeft != null && typeRight != null)
			return true;
		else
			throw new StreeException("Type error while checking null types !");
	}
}
