package com.ch19.class04.step02;

public class Expression {
	public interface Expr{
		Expr accept(SimplifyExprVisitor v);
	}

	public static class Number implements Expr{
		private final int value;

		public Number(int value) {
			this.value = value;
		}

		@Override
		public Expr accept(SimplifyExprVisitor v) {
			return v.visit(this);
		}

		@Override
		public String toString() {
			return Integer.toString(value);
		}
	}

	public static class BinOp implements Expr{
		private final String opName;
		private final Expr left;
		private final Expr right;

		public BinOp(String opName, Expr left, Expr right) {
			this.opName = opName;
			this.left = left;
			this.right = right;
		}

		@Override
		public Expr accept(SimplifyExprVisitor v) {
			return v.visit(this);
		}

		@Override
		public String toString() {
			return String.format("(%s%s%s)", left, opName, right);
		}
	}

	public static class SimplifyExprVisitor{
		public Expr visit(Number number){
			return number;
		}

		public Expr visit(BinOp binOp){
			Expr left = binOp.left.accept(this);
			Expr right = binOp.right.accept(this);

			if (left instanceof Number && right instanceof Number){
				if ("+".equals(binOp.opName)){
					int leftValue = ((Number)left).value;
					int rightValue = ((Number)right).value;
					if (rightValue == 0){
						return new Number(leftValue);
					}
				}
			}
			return new BinOp(binOp.opName, left, right);
		}
	}

	public static void main(String[] args) {
		Expr seven = new BinOp("+", new Number(7), new Number(0));
		Expr five = new BinOp("+", new Number(2), new Number(3));
		// (7 + 0) + (2 + 3)
		Expr twelve = new BinOp("+", seven, five);
		SimplifyExprVisitor visitor = new SimplifyExprVisitor();
		System.out.println("Original Expression: " + twelve);
		Expr simplifiedExpression = twelve.accept(visitor);
		System.out.println("Simplified Expression: " + simplifiedExpression);
	}
}
