package fr.ubordeaux.deptinfo.compilation.lea.stree;

import fr.ubordeaux.deptinfo.compilation.lea.intermediate.SEQ;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.Stm;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.temp.Label;
import fr.ubordeaux.deptinfo.compilation.lea.intermediate.temp.LabelList;
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
		Label label1 = new Label();
		Label label2 = new Label();
		Label label3 = new Label();

		LabelList labelList3 = new LabelList(label3);
		LabelList labelList2 = new LabelList(label2, labelList3);
		LabelList labelList1 = new LabelList(label1, labelList2);

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
