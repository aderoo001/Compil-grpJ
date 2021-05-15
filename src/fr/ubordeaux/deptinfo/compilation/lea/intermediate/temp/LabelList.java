package fr.ubordeaux.deptinfo.compilation.lea.intermediate.temp;

public class LabelList {
	private Label head;
	private LabelList tail;

	public LabelList(Label head, LabelList tail) {
		this.head = head;
		this.tail = tail;
	}

	public Label getHead() {
		return head;
	}

	public LabelList getTail() {
		return tail;
	}

	public LabelList(Label head) {
		this(head, null);
	}
}
